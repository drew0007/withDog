//package com.withdog.web.purchase;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.withdog.service.domain.Product;
//import com.withdog.service.domain.Purchase;
//import com.withdog.service.domain.User;
//import com.withdog.service.product.ProductService;
//import com.withdog.service.purchase.PurchaseService;
//
//@Controller
//@RequestMapping("/purchase/*")
//public class PurchaseController {
//	
//	@Autowired
//	@Qualifier("purchaseServiceImpl")
//	private PurchaseService purchaseService;
//	
//	@Autowired
//	@Qualifier("productServiceImpl")
//	private ProductService productService;
//
//	public PurchaseController() {
//		System.out.println(this.getClass());
//	}
//	
//	
//	@Value("#{commonProperties['pageUnit']}")
//	int pageUnit;
//	
//	@Value("#{commonProperties['pageSize']}")
//	int pageSize;
//	
//	
//	@RequestMapping(value="addPurchase", method=RequestMethod.GET)
//	public String addPurchase(@RequestParam("prodNo") int prodNo, Model model) throws Exception{
//		
//		System.out.println("/purchase/addPurchase : GET");
//		
//		Product product = productService.getProduct(prodNo);
//		
//		// Model 과 View 연결
//		model.addAttribute("product", product);
//		
//		return "forward:/store/addPurchase.jsp";
//	}
//	
//	
//	@RequestMapping(value="addPurchase", method=RequestMethod.POST)
//	public String addPurchase(@RequestParam("prodNo") int prodNo, @ModelAttribute("purchase") Purchase purchase, HttpSession session) throws Exception{
//		
//		System.out.println("/purchase/addPurchase : POST");
//		
//		//세션에서 user정보 꺼내서 user객체에 담고 purchase 필드에 심기
//		User user = (User)session.getAttribute("user");
//		purchase.setUser(user);
//		
//		//프로덕트 객체를 펄체이스 필드에 담기
//		Product product = productService.getProduct(prodNo);
//		purchase.setProduct(product);
//		
//		purchaseService.addPurchase(purchase);		
//		
//		//상품수량
//		int prodQuantity = product.getProdQuantity();
//		
//		//구매수량
//		int purchaseQuantity = purchase.getPurchaseQuantity();
//		prodQuantity = prodQuantity - purchaseQuantity;
//		product.setProdQuantity(prodQuantity);
//		
//		productService.updateProductAdmin(product);
//		
//		return "forward:/purchase/addPurchaseConfirm.jsp";
//	}
//	
//	
//	
//
//}
