package com.withdog.service.fund.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.domain.Fund;
import com.withdog.service.fund.FundDAO;
import com.withdog.service.fund.FundService;




@Service("fundServiceImpl")
public class FundServiceImpl implements FundService{
	
	
	
	@Autowired
	@Qualifier("fundDAOImpl")
	private FundDAO fundDAO;

	
	
	
	@Override
	public Fund getFund(int fundNo) throws Exception {
		// TODO Auto-generated method stub
		return fundDAO.getFund(fundNo);
	}

	@Override
	public int addFund(Fund fund) throws Exception {
		// TODO Auto-generated method stub
		return fundDAO.addFund(fund);
	}

	@Override
	public void updateFund(Fund fund) throws Exception {
		// TODO Auto-generated method stub
	    fundDAO.updateFund(fund);
		
	}

	@Override
	public void deleteFund() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFundRaising() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listMyFund() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kakaoPay() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fund> getFundList() throws Exception {
		// TODO Auto-generated method stub
		return fundDAO.getFundList();
	}

	@Override
	public void addFundRaising(Fund fund) throws Exception {
		// TODO Auto-generated method stub
		
		fundDAO.addFundRaising(fund);
	}

	@Override
	public Fund getMinFund() throws Exception {
		// TODO Auto-generated method stub
		return fundDAO.getMinFund();
	}

	@Override
	public List<Fund> getFundResultList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return fundDAO.getFundResultList(search);
	}

	
	
	
	
	

}
