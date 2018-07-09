package com.withdog.service.fund.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.domain.Fund;
import com.withdog.service.fund.FundDAO;
import com.withdog.service.fund.FundService;


@Service("fundServiceImpl")
public class FundServiceImpl implements FundService{
	
	
	
	@Autowired
	@Qualifier("productDaoImpl")
	private FundDAO fundDAO;

	@Override
	public Fund getFund(int fundNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addFund(Fund fund) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateFund(Fund fund) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFund() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fundPay() throws Exception {
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
	
	

}
