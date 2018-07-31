package com.withdog.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Fund;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;
import com.withdog.service.purchase.PurchaseDAO;
import com.withdog.service.purchase.PurchaseService;


//==> 구매관리 서비스 구현
@Service("purchaseServiceImpl")

public class PurchaseServiceImpl implements PurchaseService  {
		
	///Field
		@Autowired
		@Qualifier("purchaseDAOImpl")
		 PurchaseDAO  purchaseDAO;
		
		public void setPurchaseDAO(PurchaseDAO purchaseDAO){
			this.purchaseDAO=purchaseDAO;
		}

		///Constructor
		public PurchaseServiceImpl() {
			System.out.println(" purchase service impl 생성자 호출");
		}
		
		///Method
		//구매요청
		public int addPurchase(Purchase purchase) throws Exception{
			System.out.println(" purchase service impl 시작");
			
			return purchaseDAO.addPurchase(purchase);
		}
		
		//나의구매내역
		@Override
		public Map<String,Object> getMyPurchaseList(Search search, User user) throws Exception {
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<Purchase> list = purchaseDAO.getMyPurchaseList(search, user);
			
			System.out.println("MyFundlist 받음"+list.size());
			
			int totalCount = purchaseDAO.getTotalCount(user);
			System.out.println("MyFundlist Count 받음"+totalCount);
			map.put("list", list);
			map.put("totalCount",totalCount);
			
			return map;
		}
		
		//나의구매상세정보
		@Override
		public Purchase getMyPurchase(int purchaseNo) throws Exception {
			return purchaseDAO.getMyPurchase(purchaseNo);
		}
		
//		//구매목록조회
//		public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception{  
//			
//			System.out.println("펄체이스서비스임플리먼츠");
//			List<Purchase>list = purchaseDao.getPurchaseList(search,buyerId);
//			int totalCount = purchaseDao.getTotalCount(buyerId);
//			//return productDao.getProductList(search); //상품목록은 리턴값이맵 //기존의 서비스 임플에서 바로 dao.~~() 호출
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("list", list );
//			map.put("totalCount", new Integer(totalCount));
//			
//			return map;
//		}
//		
//		//구매 상세조회
//		public Purchase getPurchase(int tranNo) throws Exception{
//			return purchaseDao.getPurchase(tranNo);
//		}
//		
//		//구매수정조회
//		public void updatePurchase(Purchase purchase) throws Exception{
//			purchaseDao.updatePurchase(purchase);
//		}
//		
//		//구매이력 조회 물건도착 눌렀을때
//		public void updateTranCode(Purchase purchaseVO) throws Exception{
//			purchaseDao.updateTranCode(purchaseVO);
//		}
//		
//		//Admin 상품관리에서 배송하기를 눌렀을때
//		public void updateTranCodeByProd(Purchase purchase) throws Exception{
//			purchaseDao.updateTranCodeByProd(purchase);
//		}
		
}//end of class
