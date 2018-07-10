package com.withdog.service.common.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.service.common.CommonDAO;
import com.withdog.service.domain.Point;

//==> ȸ���������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition

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
		 if(point.getAsh()!=null) {
		sqlsession.insert("CommonMapper.addAshPoint",point);
		}
		else if(point.getPurchase()!=null) {
		sqlsession.insert("CommonMapper.addPurchasePoint",point);
		}
	}



	@Override
	public void usePoint(Point point) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("addPointDAO STart");
		if(point.getFund()!=null) {
		System.out.println("FundPoint");
				
		System.out.println("check:"+point.getUser().getUserId());
						
		int currentPoint=sqlsession.selectOne("CommonMapper.currentPoint", point);;
		
		System.out.println(":::::"+currentPoint);
		int resultPoint = currentPoint - point.getUsePoint();
		System.out.println(resultPoint);
		point.setCurrentPoint(resultPoint);
		
		System.out.println(point.toString());
		sqlsession.insert("CommonMapper.addFundPoint",point);
		
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



	
	

		
}