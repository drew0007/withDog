package com.withdog.service.fund;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Fund;



//==> ȸ���������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface FundDAO {
	
	public List<Fund> getFundList() throws Exception;
	
	public Fund getFund(int fundNo) throws Exception;
		
	public List<Fund> getFundResultList(Search search) throws Exception;
	
	//public Map<String,Object> getFundUserList() throws Exception;
	
	public int addFund(Fund fund) throws Exception;
	
	public void updateFund(Fund fund) throws Exception;
	//���� �Ŀ��ݾ� �ݵ� ����
	public Fund getMinFund() throws Exception;
	
	public String deleteFund() throws Exception;
		
	public void updateFundRaising() throws Exception;
	
	public void listMyFund() throws Exception;
	
	public void kakaoPay() throws Exception;
	
	public void addFundRaising(Fund fund) throws Exception;
	
	
		
}