package com.withdog.service.purchase;

import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;

public interface PurchaseService {

	public void addPurchase(Purchase purchase) throws Exception;

	public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
	
	//구매 목록 조회에서 no링크타고 내가 구매한 상품 보는것
	public Purchase getPurchase(int purchaseNo) throws Exception;
	
	//구매요청을 수정
	public void updatePurchase(Purchase purchase) throws Exception;

	//판매관리리스트
	public Map<String,Object> getSalesListAdmin(Search search, String userId) throws Exception;
	
	public Purchase getSalesAdmin(int purchaseNo) throws Exception;
	
	//구매상태변경 
	public void updatePurchaseCondition(Purchase purchase) throws Exception;
	
	
}