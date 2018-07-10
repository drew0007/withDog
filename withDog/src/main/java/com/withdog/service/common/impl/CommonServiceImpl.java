package com.withdog.service.common.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.common.CommonDAO;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Point;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  

@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService{

	@Autowired
	@Qualifier("CommonDAOImpl")
	private CommonDAO commonDAO;
	
	
	
	@Override
	public Map<String, Object> getMyPointList(Point point) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void addPointinfo(Point point) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("addPoint Start");
		if(point.getUsePoint()!=0) {
			commonDAO.usePoint(point);
		}else {
			commonDAO.savePoint(point);
		}
	}
	
	
		
}