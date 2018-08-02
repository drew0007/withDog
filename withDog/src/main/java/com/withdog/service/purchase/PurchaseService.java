package com.withdog.service.purchase;

import java.util.List;
import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;

public interface PurchaseService {

	public int addPurchase(Purchase purchase) throws Exception;
	
	// 다음 purchaseNo
	public int addPurchaseSeq() throws Exception ;

	// 다음 cartNo
	public int addCartSeq() throws Exception ;
	
	public Map<String,Object> getMyPurchaseList(Search search,User user) throws Exception;
	
	//구매 목록 조회에서 no링크타고 내가 구매한 상품 보는것
	public Purchase getMyPurchase(int purchaseNo) throws Exception;
	
//	public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
//	
//	//구매요청을 수정
//	public void updatePurchase(Purchase purchase) throws Exception;
//
//	//판매관리리스트
//	public Map<String,Object> getSalesListAdmin(Search search, String userId) throws Exception;
//	
//	public Purchase getSalesAdmin(int purchaseNo) throws Exception;
//	
//	//구매상태변경 
//	public void updatePurchaseCondition(Purchase purchase) throws Exception;
	
	
}