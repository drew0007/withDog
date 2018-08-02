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
import com.withdog.service.cart.CartService;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Cart;
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
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
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
	
	
//	//하나씩 구매시 로직 
//	@RequestMapping(value="addPurchaseView", method=RequestMethod.POST)
//	public String addPurchaseView(@RequestParam("prodNo") int prodNo, @ModelAttribute Purchase purchase, HttpServletRequest request, Model model, HttpSession session) throws Exception{
//		
//		System.out.println("/purchase/addPurchaseView : POST");
//		
//		System.out.println(purchase);
//		
//		//프로덕트 객체를 펄체이스 필드에 담기
//		Product product = productService.getProduct(prodNo);
//		purchase.setProduct(product);
//		
//		System.out.println("프로덕트객체======================="+product);
//		
//		if(session.getAttribute("user")!=null) {
//			System.out.println("이프문");
//			//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
//			User user = (User)session.getAttribute("user");
//			purchase.setUser(user);
//			System.out.println(user.getUserId());
//			
//			//임시
//			Point point = new Point();
//			point.setUser(user);
//			
//			int currentPoint=commonService.getCurrentPoint(point);
//			
//			model.addAttribute("currentPoint", currentPoint);
//		}
//		
//		System.out.println("펄체이스객체=========================" +purchase);
//		model.addAttribute("purchase", purchase);
//		
//		return "forward:/store/addPurchase.jsp";
//	}
	
	//장바구니에서 리스트가지고 넘어왔을때 로직
	@RequestMapping(value="addPurchaseView")
	public String addPurchaseView(@RequestParam(value="prodNo", required=false) String prodNo, 
									@ModelAttribute Purchase purchase, 
									@RequestParam(value="cartList", required=false) String cartList, 
									Model model, 
									HttpSession session) throws Exception{
		
		System.out.println("/purchase/addPurchaseView : 구매화면");
		
		//장바구니에서 구매로 넘어올때는 cartNo로 넘어오고, 바로구매에서 넘어올때는 prodNo로 넘어오기때문에  
		//prodNo가 0이 아닐때 (바로구매시)
		if(prodNo != null) {
			
			System.out.println("/purchase/addPurchaseView : 바로구매" + prodNo);
			Product product = productService.getProduct(Integer.parseInt(prodNo));
			
			Cart cart = new Cart();
			cart.setProduct(product);
			cart.setCartQuantity(purchase.getPurchaseQuantity());
			
			List<Cart> list = new ArrayList<Cart>();
			
			list.add(cart);
			User user = (User)session.getAttribute("user");
			
			//현재포인트
			Point point = new Point();
			point.setUser(user);
			int currentPoint=commonService.getCurrentPoint(point);
			
			model.addAttribute("currentPoint", currentPoint);
			model.addAttribute("list", list);
			
		}else {
			
			System.out.println("/purchase/addPurchaseView : 장바구니에서 구매");
			
			Map<String, Object> map = cartService.getSelectCartList(cartList);
			
			//현재포인트
			Point point = new Point();
			User user = (User)session.getAttribute("user");
			point.setUser(user);
			
			int currentPoint=commonService.getCurrentPoint(point);
			
			model.addAttribute("currentPoint", currentPoint);
			model.addAttribute("list", map.get("list"));
		}
		
		
		return "forward:/store/addPurchase.jsp";
	}
	
	
	/*@RequestMapping(value="addPurchase")
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
	}*/
	
	
	@RequestMapping(value = "kakaoPay")
	private String kakaoPay(@ModelAttribute Purchase purchase, @RequestParam("prodNo") String prodNo,
							@RequestParam("cartQuantity") String cartQuantity, 
							//@RequestParam("price") String price, 
							@RequestParam("cartNo") String cartNo, @RequestParam("usePoint") String usePoint,
							HttpSession session, HttpServletRequest request, Model model) throws Exception {
		
		System.out.println("/purchase/kakaoPay Start==================================");
		System.out.println("넘어온 사용포인트" +usePoint);
		System.out.println("넘어온 객체" + purchase);
		System.out.println("cartNo" + cartNo);
		//System.out.println("price" + price);
		System.out.println("cartQuantity" + cartQuantity);
		
		//,,로 들고온 정보들 파싱
		String[] parseProdNo = prodNo.split(",");
		String[] parseCartQuantity = cartQuantity.split(",");
		//String[] parsePrice = price.split(",");
		String[] parseCartNo = cartNo.split(",");
		
		for(int i=0; i<parseProdNo.length; i++) {
			System.out.println(parseProdNo[i] + "," + parseCartQuantity[i] + "," + parseCartNo[i]);
		}
		
		List<Purchase> list = new ArrayList<Purchase>();
		for(int i=0; i<parseProdNo.length; i++) {
			Purchase pur = new Purchase();
			
			Product pro = productService.getProduct(Integer.parseInt(parseProdNo[i]));
			pur.setProduct(pro);
			
			User user = (User)session.getAttribute("user");
			pur.setUser(user);
			
			pur.setReceiverName(purchase.getReceiverName());
			pur.setReceiverPhone(purchase.getReceiverPhone());
			pur.setReceiverAddr1(purchase.getReceiverAddr1());
			pur.setReceiverAddr2(purchase.getReceiverAddr2());
			pur.setDivyRequest(purchase.getDivyRequest());
			pur.setPurchaseQuantity(Integer.parseInt(parseCartQuantity[i]));
			pur.setPurchasePrice(purchase.getPurchasePrice());		
			if(Integer.parseInt(parseCartNo[i]) == 0) {
				pur.setCartNo(purchaseService.addCartSeq());
			}else {
				pur.setCartNo(Integer.parseInt(parseCartNo[i]));
			}
			
			list.add(pur);
		}
		
		for(int i = 0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		
		//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
		User user = (User)session.getAttribute("user");
		
		int kakaoUsePoint = 0;

		/// 영수증.jsp로 카드 결제시 callback 되는지
		String forwardUri = "forward:/sns/kakaoPay.jsp";
		// snsKakaoPay를 위한 로직
		Point pointPurchase = new Point();
		pointPurchase.setUser(user);
		pointPurchase.setPurchaseList(list);
		
		System.out.println(usePoint + "111");
		if (Integer.parseInt(usePoint) != 0) { // 사용한 포인트가 0이 아니라면
			pointPurchase.setUsePoint(Integer.parseInt(usePoint));
			kakaoUsePoint = Integer.parseInt(usePoint);
		}
		
		if (pointPurchase.getPurchaseList().get(0).getPurchasePrice() != 0) { // 결제 시 사용금액이 0이 아니라면?
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
		point.setPurchaseList(list);
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
		List<Purchase> list = new ArrayList<>();
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
					list = point.getPurchaseList();
					user = point.getUser();
					
					int nextPurchaseNo = purchaseService.addPurchaseSeq();
					
					for(int i=0; i<list.size(); i++) {
						purchase = list.get(i);
						purchase.setPurchaseNo(nextPurchaseNo);
						purchaseService.addPurchase(purchase);
						list.get(i).setPurchaseNo(nextPurchaseNo);
						
						Product product = new Product();
						product = productService.getProduct(purchase.getProduct().getProdNo());
						
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
						
						int cartNo = purchase.getCartNo();
						cartService.deleteCart(cartNo);
					}
					
				    point.setPurchaseList(list);//후원,구매,예약 구분을 위해
				    point.setPurchase(purchase);
				    
				    double savePoint = list.get(0).getPurchasePrice()*0.01;
				    System.out.println("적립포인트"+savePoint);
				    int resultpoint = (int)savePoint;
				    point.setPoint(resultpoint);

				   	commonService.addPointinfo(point);
				}
				
			}else {
				request.setAttribute("state", "3");
				
				if(session.getAttribute("pointPurchase")!=null) {
					point = (Point)session.getAttribute("pointPurchase");
					list = point.getPurchaseList();
					user = point.getUser();
					
					
				    //구매 번호 가져오기
				    point.setPurchaseList(list);//후원,구매,예약 구분을 위해
				    
				    double savePoint = list.get(0).getPurchasePrice()*0.01;
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
