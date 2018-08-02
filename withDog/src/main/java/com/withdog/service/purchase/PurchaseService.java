package com.withdog.service.purchase;

import java.util.List;
import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;

public interface PurchaseService {

	public int addPurchase(Purchase purchase) throws Exception;
	
	// ���� purchaseNo
	public int addPurchaseSeq() throws Exception ;

	// ���� cartNo
	public int addCartSeq() throws Exception ;
	
	public Map<String,Object> getMyPurchaseList(Search search,User user) throws Exception;
	
	//���� ��� ��ȸ���� no��ũŸ�� ���� ������ ��ǰ ���°�
	public Purchase getMyPurchase(int purchaseNo) throws Exception;
	
//	public Map<String,Object> getPurchaseList(Search search, String userId) throws Exception;
//	
//	//���ſ�û�� ����
//	public void updatePurchase(Purchase purchase) throws Exception;
//
//	//�ǸŰ�������Ʈ
//	public Map<String,Object> getSalesListAdmin(Search search, String userId) throws Exception;
//	
//	public Purchase getSalesAdmin(int purchaseNo) throws Exception;
//	
//	//���Ż��º��� 
//	public void updatePurchaseCondition(Purchase purchase) throws Exception;
	
	
}