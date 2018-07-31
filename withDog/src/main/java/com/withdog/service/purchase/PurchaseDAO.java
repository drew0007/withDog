package com.withdog.service.purchase;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;



public interface PurchaseDAO {

	// INSERT ��ǰ����
	public int addPurchase(Purchase  purchase) throws Exception ;

	// SELECT ONE ���Ǳ��ų��� ����ȸ 
	public Purchase getMyPurchase(int purchaseNo) throws Exception ;

	// SELECT LIST ���Ǳ��ų�������Ʈ
	public  List<Purchase> getMyPurchaseList(Search search, User user) throws Exception ;

//	// UPDATE �������� ����
//	public void updatePurchase(Purchase purchase) throws Exception ;
//	
//	// SELECT ONE �ǸŰ�������ȸ 
//	public Purchase getSalesAdmin(int purchaseNo) throws Exception ;
//	
//	// SELECT LIST �ǸŰ��������ȸ
//	public  List<Purchase> getSalesListAdmin(Search search, String userId) throws Exception ;
 
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	public int getTotalCount(User user) throws Exception ;

//	//�����ڵ� ���� 
//	public void updatePurchaseCondition(Purchase purchase) throws Exception ;

	
}
