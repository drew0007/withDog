package com.withdog.service.fund;

import java.util.List;

import com.withdog.service.domain.Fund;



//==> ȸ���������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface FundDAO {
	
	public List<Fund> getFundList() throws Exception;
	
	public Fund getFund(int fundNo) throws Exception;
		
	//public Map<String,Object> getFundResultList() throws Exception;
	
	//public Map<String,Object> getFundUserList() throws Exception;
	
	public int addFund(Fund fund) throws Exception;
	
	public void updateFund(Fund fund) throws Exception;
	
	public String deleteFund() throws Exception;
		
	public void fundPay() throws Exception;
	
	public void listMyFund() throws Exception;
	
	public void kakaoPay() throws Exception;
		
}