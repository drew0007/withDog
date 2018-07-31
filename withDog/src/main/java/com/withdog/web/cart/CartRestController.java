package com.withdog.web.cart;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.cart.CartService;
import com.withdog.service.domain.Cart;
import com.withdog.service.domain.Product;
import com.withdog.service.domain.User;

@RestController
@RequestMapping("/cart/*")
public class CartRestController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	public CartRestController() {
		System.out.println(this.getClass());
	}
	
//	//장바구니담기
//	@RequestMapping( value = "json/addCart", method=RequestMethod.POST)
//	public String addCart(@RequestBody Map<String, Object> map,
//						  HttpSession session) throws Exception{
//
//		String prodNo = map.get("prodNo").toString();
//		String purchaseQuantity = map.get("purchaseQuantity").toString();
//		
//		User user = (User)session.getAttribute("user");
//		
//		Product product = new Product();
//		product.setProdNo(Integer.parseInt(prodNo));
//		
//		Cart cart = cartService.getCart(user.getUserId(), Integer.parseInt(prodNo));
//
//		if(cart == null) {
//			cart = new Cart();
//			cart.setCartQuantity(Integer.parseInt(purchaseQuantity));
//			cart.setProduct(product);
//			cart.setUser(user);
//			
//			cartService.addCart(cart);
//		}else {
//			cart.setCartCnt(Integer.parseInt(tranCnt));
//			
//			cartService.updateCart(cart);
//		}
//		
//		return "1";
//	}

}
