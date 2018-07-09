package com.withdog.service.purchase;

import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;

public interface PurchaseService {

	public void addPurchase(Purchase purchase) throws Exception;

	public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
	
	//���� ��� ��ȸ���� no��ũŸ�� ���� ������ ��ǰ ���°�
	public Purchase getPurchase(int purchaseNo) throws Exception;
	
	//���ſ�û�� ����
	public void updatePurchase(Purchase purchase) throws Exception;

	//�ǸŰ�������Ʈ
	public Map<String,Object> getSalesListAdmin(Search search, String userId) throws Exception;
	
	public Purchase getSalesAdmin(int purchaseNo) throws Exception;
	
	//���Ż��º��� 
	public void updatePurchaseCondition(Purchase purchase) throws Exception;
	
	
}