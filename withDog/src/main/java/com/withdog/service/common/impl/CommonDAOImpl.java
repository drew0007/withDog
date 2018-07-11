package com.withdog.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
import com.withdog.service.common.CommonDAO;
import com.withdog.service.domain.Point;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition

@Repository("CommonDAOImpl")
public class CommonDAOImpl implements CommonDAO {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlsession;
	
	
	
	@Override
	public Map<String, Object> getMyPointList(Point point) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void savePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
	
			System.out.println("FundPointSAVE");
			
			System.out.println("check:"+point.getUser().getUserId());
			
			//현재포인트가 없는 사람도 적립은 되야함
			int currentPoint=0;
			
			if(sqlsession.selectOne("CommonMapper.currentPoint", point)!=null) {
				currentPoint=sqlsession.selectOne("CommonMapper.currentPoint", point);
			}
			
			System.out.println(":::::"+currentPoint);
			int resultPoint = currentPoint + point.getPoint();
			System.out.println(resultPoint);
			point.setCurrentPoint(resultPoint);
			
			
			System.out.println(point.toString());
			sqlsession.insert("CommonMapper.addPointSave",point);
			System.out.println("FundPointSAVE END");
		
	}



	@Override
	public void usePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("addPointDAO STart");
		if(point.getFund()!=null) {
		System.out.println("FundPoint");
				
		System.out.println("check:"+point.getUser().getUserId());
						
		int currentPoint=sqlsession.selectOne("CommonMapper.currentPoint", point);
		
		System.out.println(":::::"+currentPoint);
		int resultPoint = currentPoint - point.getUsePoint();
		System.out.println(resultPoint);
		point.setCurrentPoint(resultPoint);
		
		
		System.out.println(point.toString());
		sqlsession.insert("CommonMapper.addPointUse",point);
		
		}
		else if(point.getAsh()!=null) {
			System.out.println("AshPoint");
		sqlsession.insert("CommonMapper.addAshPoint",point);
		}
		else if(point.getPurchase()!=null) {
			System.out.println("PurchasePoint");
		sqlsession.insert("CommonMapper.addPurchasePoint",point);
		}
	}

	
	


	@Override
	public int getCurrentPoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		int currentPoint =(sqlsession.selectOne("CommonMapper.currentPoint", point)!=null? sqlsession.selectOne("CommonMapper.currentPoint", point) : 0); 
		return currentPoint;
	}



	@Override
	public List<Point> getMyPointList(Search search , String userId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PointList Start");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("search", search);
		return sqlsession.selectList("CommonMapper.myPointList",map);
	}



	
	
	

		
}