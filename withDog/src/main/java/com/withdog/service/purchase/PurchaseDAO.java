package com.withdog.service.purchase;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Purchase;



public interface PurchaseDAO {

	// INSERT ��ǰ����
	public void addPurchase(Purchase  purchase) throws Exception ;

//	// SELECT ONE ���� ����ȸ 
//	public Purchase getPurchase(int purchaseNo) throws Exception ;
//
//	// SELECT LIST ���Ÿ����ȸ
//	public  List<Purchase> getPurchaseList(Search search,String userId) throws Exception ;
//
//	// UPDATE �������� ����
//	public void updatePurchase(Purchase purchase) throws Exception ;
//	
//	// SELECT ONE �ǸŰ�������ȸ 
//	public Purchase getSalesAdmin(int purchaseNo) throws Exception ;
//	
//	// SELECT LIST �ǸŰ��������ȸ
//	public  List<Purchase> getSalesListAdmin(Search search, String userId) throws Exception ;
// 
//	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
//	public int getTotalCount(String userId) throws Exception ;
//
//	//�����ڵ� ���� 
//	public void updatePurchaseCondition(Purchase purchase) throws Exception ;

	
}
