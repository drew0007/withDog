package com.withdog.service.purchase;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;



public interface PurchaseDAO {

	// INSERT 상품구매
	public int addPurchase(Purchase  purchase) throws Exception ;

	// SELECT ONE 나의구매내역 상세조회 
	public Purchase getMyPurchase(int purchaseNo) throws Exception ;

	// SELECT LIST 나의구매내역리스트
	public  List<Purchase> getMyPurchaseList(Search search, User user) throws Exception ;

//	// UPDATE 구매정보 수정
//	public void updatePurchase(Purchase purchase) throws Exception ;
//	
//	// SELECT ONE 판매관리상세조회 
//	public Purchase getSalesAdmin(int purchaseNo) throws Exception ;
//	
//	// SELECT LIST 판매관리목록조회
//	public  List<Purchase> getSalesListAdmin(Search search, String userId) throws Exception ;
 
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(User user) throws Exception ;

//	//구매코드 변경 
//	public void updatePurchaseCondition(Purchase purchase) throws Exception ;

	
}
