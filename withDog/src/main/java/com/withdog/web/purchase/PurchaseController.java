package com.withdog.web.purchase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.withdog.common.Page;
import com.withdog.common.Search;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Fund;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.Product;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;
import com.withdog.service.product.ProductService;
import com.withdog.service.purchase.PurchaseService;
import com.withdog.service.sns.SnsService;

@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	@Autowired
	@Qualifier("snsServiceImpl")
	private SnsService snsService;


	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	@RequestMapping(value="addPurchaseView", method=RequestMethod.POST)
	public String addPurchaseView(@RequestParam("prodNo") int prodNo, @ModelAttribute Purchase purchase, HttpServletRequest request, Model model, HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchaseView : POST");
		
		System.out.println(purchase);
		
		//���δ�Ʈ ��ü�� ��ü�̽� �ʵ忡 ���
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		System.out.println("���δ�Ʈ��ü======================="+product);
		
		if(session.getAttribute("user")!=null) {
			System.out.println("������");
			//���ǿ��� user���� ������ user��ü�� ��� purchase �ʵ忡 �ɱ�
			User user = (User)session.getAttribute("user");
			purchase.setUser(user);
			System.out.println(user.getUserId());
			
			//�ӽ�
			Point point = new Point();
			point.setUser(user);
			
			int currentPoint=commonService.getCurrentPoint(point);
			
			model.addAttribute("currentPoint", currentPoint);
		}
		
		System.out.println("��ü�̽���ü=========================" +purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:/store/addPurchase.jsp";
	}
	
	@RequestMapping(value="addPurchase")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase, @RequestParam("prodNo") int prodNo, HttpSession session, HttpServletRequest req,  Model model) throws Exception{
		
		System.out.println("/purchase/addPurchase : POST");
		
		System.out.println(purchase);
		
		
		//���ǿ��� user���� ������ user��ü�� ��� purchase �ʵ忡 �ɱ�
		User user = (User)session.getAttribute("user");
		purchase.setUser(user);
		
		//���δ�Ʈ ��ü�� ��ü�̽� �ʵ忡 ���
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		System.out.println("��������" + purchase);
		purchaseService.addPurchase(purchase);		
		
		System.out.println("��񰫴ٿӴ�" + purchase);
		//��ǰ����
		int prodQuantity = product.getProdQuantity();
		
		//���ż���
		int purchaseQuantity = purchase.getPurchaseQuantity();
		//��ǰ����-���ż��� = ��ǰ����
		prodQuantity = prodQuantity - purchaseQuantity;
		//����� ��ǰ������ ���δ�Ʈ ��ü�� ���
		product.setProdQuantity(prodQuantity);
		
		//���δ�Ʈ����������Ʈ
		productService.updateProductAdmin(product);
		
		//snsKakaoPay�� ���� ����
		Point pointPurchase = new Point();
		pointPurchase.setPurchase(purchase);
		
		int price =0;
		int usePoint =0;
		
		if(pointPurchase.getPurchase().getPurchasePrice()!=0) {
			price=pointPurchase.getPurchase().getPurchasePrice();
		}
		if(Integer.parseInt(req.getParameter("usePoint"))!=0) {
			pointPurchase.setUsePoint(Integer.parseInt(req.getParameter("usePoint")));
			usePoint=Integer.parseInt(req.getParameter("usePoint"));
		}
		
		//����Ʈ
		Point point = new Point();
		
		//���ǿ� �ִ� ���������� ������ ĳ�����ؼ� ����Ʈ�� �ִ� �����ʵ忡 ���
		point.setUser((User) session.getAttribute("user"));
		
		//��� ������ ������ �ִ� ��������Ʈ�� currentPoint�� ����
		int currentPoint = commonService.getCurrentPoint(point);
		
		point.setUsePoint(pointPurchase.getUsePoint());
		
		model.addAttribute("currentPoint", currentPoint);
		model.addAttribute("purchase", purchase);
		model.addAttribute("usePoint", point.getUsePoint());
		
		return "forward:/store/addPurchaseConfirm.jsp";
	}
	
	
	@RequestMapping(value = "kakaoPay")
	private String kakaoPay(@ModelAttribute Purchase purchase, @RequestParam("prodNo") int prodNo, @RequestParam("usePoint") String usePoint, HttpSession session, HttpServletRequest request, Model model) throws Exception {
		
		System.out.println("/purchase/kakaoPay Start==================================");
		System.out.println("�Ѿ�� �������Ʈ" +usePoint);
		System.out.println("�Ѿ�� ��ü" + purchase);
		
		//���ǿ��� user���� ������ user��ü�� ��� purchase �ʵ忡 �ɱ�
		User user = (User)session.getAttribute("user");
		purchase.setUser(user);
		
		//���δ�Ʈ ��ü�� ��ü�̽� �ʵ忡 ���
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		/*System.out.println("��������" + purchase);
		purchaseService.addPurchase(purchase);		*/
		
		//��ǰ����
		int prodQuantity = product.getProdQuantity();
		
		//���ż���
		int purchaseQuantity = purchase.getPurchaseQuantity();
		//��ǰ����-���ż��� = ��ǰ����
		prodQuantity = prodQuantity - purchaseQuantity;
		//����� ��ǰ������ ���δ�Ʈ ��ü�� ���
		product.setProdQuantity(prodQuantity);
		
		//���δ�Ʈ����������Ʈ
		productService.updateProductAdmin(product);

		int kakaoUsePoint = 0;

		/// ������.jsp�� ī�� ������ callback �Ǵ���
		String forwardUri = "forward:/sns/kakaoPay.jsp";
		// snsKakaoPay�� ���� ����
		Point pointPurchase = new Point();
		pointPurchase.setUser(user);
		pointPurchase.setPurchase(purchase);
		
		System.out.println(usePoint + "111");
		if (Integer.parseInt(usePoint) != 0) { // ����� ����Ʈ�� 0�� �ƴ϶��
			pointPurchase.setUsePoint(Integer.parseInt(usePoint));
			kakaoUsePoint = Integer.parseInt(usePoint);
		}
		
		if (pointPurchase.getPurchase().getPurchasePrice() != 0) { // ���� �� ���ݾ��� 0�� �ƴ϶��?
			String uri = "http://localhost:8080/purchase/addPurchaseDone?state=";
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

			JSONObject jobj = snsService.PurchaseKakaoPay(pointPurchase, uri); // īī������ �ٳ�ͼ� �����͸� �޴� ��ü

			System.out.println(jobj.get("tid"));
			String url = (String) jobj.get("next_redirect_pc_url");
			System.out.println("next_redirect_pc_url ? " + url); // ����â
			session.setAttribute("url", url);
			
		} else { // ����Ʈ�θ� ������ īī�����̳Ѿ���ʰ� �ٷ� �����Ϸ�â
			forwardUri = "forward:/purchase/addPurchaseDone?state=0";
		}

		// �ӽ�ó��
		Point point = new Point();

		// ����Ʈ �̰͸� �ܱ�
		purchase.setUser(user);
		point.setUser(user);// userid
		point.setPurchase(purchase);
		System.out.println("����� ����Ʈ�� ? : " + pointPurchase.getUsePoint());
		point.setUsePoint(pointPurchase.getUsePoint());

		System.out.println("���Ǵ��������Ʈ" +point);
		session.setAttribute("pointPurchase", point);

		return forwardUri;

	}
	
	
	
	@RequestMapping(value = "addPurchaseDone")//���� ������ �����Ϸ�â
	public String addPurchaseDone(HttpServletRequest request) throws Exception {
		System.out.println("/purchase/addPurchaseDone : ��������");

		Point point = new Point();
		Purchase purchase = new Purchase();
		User user = new User();

		HttpSession session = request.getSession(false);

		session.removeAttribute("url");

		String state = request.getParameter("state");

		if(state!=null) {
			if(state.equals("2")) {
				System.out.println("��������");
				request.setAttribute("state", "2");
			
			}
			else if(state.equals("1")) {
				System.out.println("�������");
				request.setAttribute("state", "1");
				
			}
			else if(state.equals("0")) {
				System.out.println("��������");
				request.setAttribute("state", "0");
				
				
				if(session.getAttribute("pointPurchase")!=null) {
					point = (Point)session.getAttribute("pointPurchase");
					purchase = point.getPurchase();
					user = point.getUser();
					
					
				    //���� ��ȣ ��������
					System.out.println("��񰡱���" + purchase);
					int purchaseNo = purchaseService.addPurchase(purchase);
					purchase.setPurchaseNo(purchaseNo);
					System.out.println("����Ʈ ������"+purchase);
				    point.setPurchase(purchase);//�Ŀ�,����,���� ������ ����
				    
				    double savePoint = purchase.getPurchasePrice()*0.01;
				    System.out.println("��������Ʈ"+savePoint);
				    int resultpoint = (int)savePoint;
				    point.setPoint(resultpoint);
				    
				   	commonService.addPointinfo(point);
				}
				
			}else {
				request.setAttribute("state", "3");
				
				if(session.getAttribute("pointPurchase")!=null) {
					point = (Point)session.getAttribute("pointPurchase");
					purchase = point.getPurchase();
					user = point.getUser();
					
					
				    //���� ��ȣ ��������
				    point.setPurchase(purchase);//�Ŀ�,����,���� ������ ����
				    
				    double savePoint = purchase.getPurchasePrice()*0.01;
				    System.out.println("��������Ʈ"+savePoint);
				    int resultpoint = (int)savePoint;
				    point.setPoint(resultpoint);
				    
				}
				
				
			}
	}
		System.out.println("������Ʈ���������Ʈ" +point);
		request.setAttribute("pointPurchase", point);
		System.out.println("ȭ��ѷ�������" + purchase);
		return "forward:/store/addPurchaseConfirm.jsp";
}
	
	
	@RequestMapping(value="/getMyPurchaseList")
	public String getMyPurchaseList(@ModelAttribute("search") Search search, HttpServletRequest request,HttpSession session) throws Exception {

		System.out.println("/purchase/getMyPurchaseList : ���Ǳ��ų�������Ʈ");
		//Business Logic
		User user = new User();
		if(session.getAttribute("user")!=null) {
		user = (User)session.getAttribute("user");
		}
	 	if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println(search);
		Map<String,Object> map = purchaseService.getMyPurchaseList(search, user); 	
		
		System.out.println("MAP üũ ===========================");
		System.out.println(map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		request.setAttribute("list", map.get("list"));
		
		System.out.println(map.get("list"));
		
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/mypage/listMyPurchase.jsp";
	}
	
	
	@RequestMapping(value = "getMyPurchase")
	public String getMyPurchase(@RequestParam("purchaseNo") int purchaseNo, HttpServletRequest request, HttpSession session, Model model) throws Exception{ 
		
		System.out.println("/purchase/getMyPurchase : ���Ǳ��Ż�����");
		
		System.out.println(purchaseNo + "�Ѿ�³ѹ�111111");

		Purchase purchase = purchaseService.getMyPurchase(purchaseNo);
		
		System.out.println(purchase + "���񽺰��ٿ���ü�̽�");
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/mypage/getMyPurchase.jsp";
	}

}
