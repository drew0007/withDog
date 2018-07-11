package com.withdog.service.fund;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Fund;



//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  
public interface FundDAO {
	
	public List<Fund> getFundList() throws Exception;
	
	public Fund getFund(int fundNo) throws Exception;
		
	public List<Fund> getFundResultList(Search search) throws Exception;
	
	//public Map<String,Object> getFundUserList() throws Exception;
	
	public int addFund(Fund fund) throws Exception;
	
	public void updateFund(Fund fund) throws Exception;
	//최저 후원금액 펀딩 추출
	public Fund getMinFund() throws Exception;
	
	public String deleteFund() throws Exception;
		
	public void updateFundRaising() throws Exception;
	
	public void listMyFund() throws Exception;
	
	public void kakaoPay() throws Exception;
	
	public void addFundRaising(Fund fund) throws Exception;
	
	
		
}