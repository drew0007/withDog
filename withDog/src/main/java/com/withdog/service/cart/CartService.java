package com.withdog.service.cart;

import com.withdog.service.domain.Cart;

public interface CartService {
	
	//장바구니 담기
	public void addCart(Cart cart) throws Exception;

}
