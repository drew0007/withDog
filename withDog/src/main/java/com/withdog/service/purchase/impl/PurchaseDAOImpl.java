//package com.withdog.service.purchase.impl;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import com.withdog.service.domain.Purchase;
//import com.withdog.service.purchase.PurchaseDAO;
//
//
//@Repository("purchaseDAOImpl")
//public class PurchaseDAOImpl implements PurchaseDAO{
//	
//	@Autowired
//	@Qualifier("sqlSessionTemplate")
//	private SqlSession sqlSession;
//	
//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
//	
//	public PurchaseDAOImpl() {
//		System.out.println(this.getClass());
//	}
//	
//	public void addPurchase(Purchase purchase) throws Exception {
//		System.out.println("펄체이스 다오 시작");
//		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
//	}
//}
