package com.withdog.service.fund.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.service.domain.Fund;
import com.withdog.service.fund.FundDAO;

//@Repository("fundDAOImpl")
public class FundDAOImpl implements FundDAO{
	
//	@Autowired
//	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

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
	public String deleteFund() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
