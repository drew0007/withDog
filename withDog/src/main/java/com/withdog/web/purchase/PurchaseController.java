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
		
		//프로덕트 객체를 펄체이스 필드에 담기
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		System.out.println("프로덕트객체======================="+product);
		
		if(session.getAttribute("user")!=null) {
			System.out.println("이프문");
			//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
			User user = (User)session.getAttribute("user");
			purchase.setUser(user);
			System.out.println(user.getUserId());
			
			//임시
			Point point = new Point();
			point.setUser(user);
			
			int currentPoint=commonService.getCurrentPoint(point);
			
			model.addAttribute("currentPoint", currentPoint);
		}
		
		System.out.println("펄체이스객체=========================" +purchase);
		model.addAttribute("purchase", purchase);
		
		return "forward:/store/addPurchase.jsp";
	}
	
	@RequestMapping(value="addPurchase")
	public String addPurchase(@ModelAttribute("purchase") Purchase purchase, @RequestParam("prodNo") int prodNo, HttpSession session, HttpServletRequest req,  Model model) throws Exception{
		
		System.out.println("/purchase/addPurchase : POST");
		
		System.out.println(purchase);
		
		
		//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
		User user = (User)session.getAttribute("user");
		purchase.setUser(user);
		
		//프로덕트 객체를 펄체이스 필드에 담기
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		System.out.println("디비들어가기전" + purchase);
		purchaseService.addPurchase(purchase);		
		
		System.out.println("디비갓다왓니" + purchase);
		//상품수량
		int prodQuantity = product.getProdQuantity();
		
		//구매수량
		int purchaseQuantity = purchase.getPurchaseQuantity();
		//상품수량-구매수량 = 상품수량
		prodQuantity = prodQuantity - purchaseQuantity;
		//연산된 상품수량을 프로덕트 객체에 담기
		product.setProdQuantity(prodQuantity);
		
		//프로덕트정보업데이트
		productService.updateProductAdmin(product);
		
		//snsKakaoPay를 위한 로직
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
		
		//포인트
		Point point = new Point();
		
		//세션에 있는 유저정보를 유저로 캐스팅해서 포인트에 있는 유저필드에 담기
		point.setUser((User) session.getAttribute("user"));
		
		//담긴 유저가 가지고 있는 현재포인트를 currentPoint에 빼기
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
		System.out.println("넘어온 사용포인트" +usePoint);
		System.out.println("넘어온 객체" + purchase);
		
		//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
		User user = (User)session.getAttribute("user");
		purchase.setUser(user);
		
		//프로덕트 객체를 펄체이스 필드에 담기
		Product product = productService.getProduct(prodNo);
		purchase.setProduct(product);
		
		/*System.out.println("디비들어가기전" + purchase);
		purchaseService.addPurchase(purchase);		*/
		
		//상품수량
		int prodQuantity = product.getProdQuantity();
		
		//구매수량
		int purchaseQuantity = purchase.getPurchaseQuantity();
		//상품수량-구매수량 = 상품수량
		prodQuantity = prodQuantity - purchaseQuantity;
		//연산된 상품수량을 프로덕트 객체에 담기
		product.setProdQuantity(prodQuantity);
		
		//프로덕트정보업데이트
		productService.updateProductAdmin(product);

		int kakaoUsePoint = 0;

		/// 영수증.jsp로 카드 결제시 callback 되는지
		String forwardUri = "forward:/sns/kakaoPay.jsp";
		// snsKakaoPay를 위한 로직
		Point pointPurchase = new Point();
		pointPurchase.setUser(user);
		pointPurchase.setPurchase(purchase);
		
		System.out.println(usePoint + "111");
		if (Integer.parseInt(usePoint) != 0) { // 사용한 포인트가 0이 아니라면
			pointPurchase.setUsePoint(Integer.parseInt(usePoint));
			kakaoUsePoint = Integer.parseInt(usePoint);
		}
		
		if (pointPurchase.getPurchase().getPurchasePrice() != 0) { // 결제 시 사용금액이 0이 아니라면?
			String uri = "http://localhost:8080/purchase/addPurchaseDone?state=";
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

			JSONObject jobj = snsService.PurchaseKakaoPay(pointPurchase, uri); // 카카오페이 다녀와서 데이터를 받는 객체

			System.out.println(jobj.get("tid"));
			String url = (String) jobj.get("next_redirect_pc_url");
			System.out.println("next_redirect_pc_url ? " + url); // 결제창
			session.setAttribute("url", url);
			
		} else { // 포인트로만 결제시 카카오페이넘어가지않고 바로 결제완료창
			forwardUri = "forward:/purchase/addPurchaseDone?state=0";
		}

		// 임시처리
		Point point = new Point();

		// 포인트 이것만 긁기
		purchase.setUser(user);
		point.setUser(user);// userid
		point.setPurchase(purchase);
		System.out.println("사용한 포인트는 ? : " + pointPurchase.getUsePoint());
		point.setUsePoint(pointPurchase.getUsePoint());

		System.out.println("세션담기전포인트" +point);
		session.setAttribute("pointPurchase", point);

		return forwardUri;

	}
	
	
	
	@RequestMapping(value = "addPurchaseDone")//결제 성공시 결제완료창
	public String addPurchaseDone(HttpServletRequest request) throws Exception {
		System.out.println("/purchase/addPurchaseDone : 결제성공");

		Point point = new Point();
		Purchase purchase = new Purchase();
		User user = new User();

		HttpSession session = request.getSession(false);

		session.removeAttribute("url");

		String state = request.getParameter("state");

		if(state!=null) {
			if(state.equals("2")) {
				System.out.println("결제실패");
				request.setAttribute("state", "2");
			
			}
			else if(state.equals("1")) {
				System.out.println("결제취소");
				request.setAttribute("state", "1");
				
			}
			else if(state.equals("0")) {
				System.out.println("결제성공");
				request.setAttribute("state", "0");
				
				
				if(session.getAttribute("pointPurchase")!=null) {
					point = (Point)session.getAttribute("pointPurchase");
					purchase = point.getPurchase();
					user = point.getUser();
					
					
				    //구매 번호 가져오기
					System.out.println("디비가기전" + purchase);
					int purchaseNo = purchaseService.addPurchase(purchase);
					purchase.setPurchaseNo(purchaseNo);
					System.out.println("포인트 가기전"+purchase);
				    point.setPurchase(purchase);//후원,구매,예약 구분을 위해
				    
				    double savePoint = purchase.getPurchasePrice()*0.01;
				    System.out.println("적립포인트"+savePoint);
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
					
					
				    //구매 번호 가져오기
				    point.setPurchase(purchase);//후원,구매,예약 구분을 위해
				    
				    double savePoint = purchase.getPurchasePrice()*0.01;
				    System.out.println("적립포인트"+savePoint);
				    int resultpoint = (int)savePoint;
				    point.setPoint(resultpoint);
				    
				}
				
				
			}
	}
		System.out.println("리퀘스트담기전포인트" +point);
		request.setAttribute("pointPurchase", point);
		System.out.println("화면뿌려지기전" + purchase);
		return "forward:/store/addPurchaseConfirm.jsp";
}
	
	
	@RequestMapping(value="/getMyPurchaseList")
	public String getMyPurchaseList(@ModelAttribute("search") Search search, HttpServletRequest request,HttpSession session) throws Exception {

		System.out.println("/purchase/getMyPurchaseList : 나의구매내역리스트");
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
		
		System.out.println("MAP 체크 ===========================");
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
		
		System.out.println("/purchase/getMyPurchase : 나의구매상세정보");
		
		System.out.println(purchaseNo + "넘어온넘버111111");

		Purchase purchase = purchaseService.getMyPurchase(purchaseNo);
		
		System.out.println(purchase + "서비스갔다온펄체이스");
		
		model.addAttribute("purchase", purchase);
		
		return "forward:/mypage/getMyPurchase.jsp";
	}

}
