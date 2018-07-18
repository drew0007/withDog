package com.withdog.service.sns.impl;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.domain.Point;
import com.withdog.service.sns.SnsDAO;
import com.withdog.service.sns.SnsService;
import com.withdog.service.user.UserDAO;

@Service("snsServiceImpl")
public class SnsServiceImpl implements SnsService{

	@Autowired
	@Qualifier("snsServiceDAOImpl")
	private SnsDAO snsDAO;
	
	@Override
	public JSONObject kakaoPay(Point point,String uri) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sns KakaoPay Start service");
		return snsDAO.kakaoPay(point, uri);
	}
	
	
		
}