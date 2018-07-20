package com.withdog.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.domain.Purchase;
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
		public void addPurchase(Purchase purchase) throws Exception{
			System.out.println(" purchase service impl ����");
			purchaseDAO.addPurchase(purchase);
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
