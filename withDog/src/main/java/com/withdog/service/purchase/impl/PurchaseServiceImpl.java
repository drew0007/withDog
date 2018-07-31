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


//==> ���Ű��� ���� ����
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
			System.out.println(" purchase service impl ������ ȣ��");
		}
		
		///Method
		//���ſ�û
		public int addPurchase(Purchase purchase) throws Exception{
			System.out.println(" purchase service impl ����");
			
			return purchaseDAO.addPurchase(purchase);
		}
		
		//���Ǳ��ų���
		@Override
		public Map<String,Object> getMyPurchaseList(Search search, User user) throws Exception {
			
			Map<String,Object> map = new HashMap<String,Object>();
			List<Purchase> list = purchaseDAO.getMyPurchaseList(search, user);
			
			System.out.println("MyFundlist ����"+list.size());
			
			int totalCount = purchaseDAO.getTotalCount(user);
			System.out.println("MyFundlist Count ����"+totalCount);
			map.put("list", list);
			map.put("totalCount",totalCount);
			
			return map;
		}
		
		//���Ǳ��Ż�����
		@Override
		public Purchase getMyPurchase(int purchaseNo) throws Exception {
			return purchaseDAO.getMyPurchase(purchaseNo);
		}
		
//		//���Ÿ����ȸ
//		public Map<String,Object> getPurchaseList(Search search, String buyerId) throws Exception{  
//			
//			System.out.println("��ü�̽��������ø�����");
//			List<Purchase>list = purchaseDao.getPurchaseList(search,buyerId);
//			int totalCount = purchaseDao.getTotalCount(buyerId);
//			//return productDao.getProductList(search); //��ǰ����� ���ϰ��̸� //������ ���� ���ÿ��� �ٷ� dao.~~() ȣ��
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("list", list );
//			map.put("totalCount", new Integer(totalCount));
//			
//			return map;
//		}
//		
//		//���� ����ȸ
//		public Purchase getPurchase(int tranNo) throws Exception{
//			return purchaseDao.getPurchase(tranNo);
//		}
//		
//		//���ż�����ȸ
//		public void updatePurchase(Purchase purchase) throws Exception{
//			purchaseDao.updatePurchase(purchase);
//		}
//		
//		//�����̷� ��ȸ ���ǵ��� ��������
//		public void updateTranCode(Purchase purchaseVO) throws Exception{
//			purchaseDao.updateTranCode(purchaseVO);
//		}
//		
//		//Admin ��ǰ�������� ����ϱ⸦ ��������
//		public void updateTranCodeByProd(Purchase purchase) throws Exception{
//			purchaseDao.updateTranCodeByProd(purchase);
//		}
		
}//end of class
