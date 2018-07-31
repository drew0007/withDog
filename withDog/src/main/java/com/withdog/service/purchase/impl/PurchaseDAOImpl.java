package com.withdog.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Fund;
import com.withdog.service.domain.Purchase;
import com.withdog.service.domain.User;
import com.withdog.service.purchase.PurchaseDAO;


@Repository("purchaseDAOImpl")
public class PurchaseDAOImpl implements PurchaseDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDAOImpl() {
		System.out.println(this.getClass());
	}
	
	//구매
	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		System.out.println("펄체이스 다오 시작");
		//시퀀스넘버를 가져옴 
		int purchaseNo = sqlSession.selectOne("PurchaseMapper.addPurchaseSeq");
		purchase.setPurchaseNo(purchaseNo);
		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
		return purchaseNo;
	}
	
	//나의구매내역리스트
	@Override
	public  List<Purchase> getMyPurchaseList(Search search, User user) throws Exception{
		System.out.println("purchaseDAOImpl : getMyPurchaseList Start");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("search",search);
		map.put("user", user);
		
		System.out.println(map);
		return sqlSession.selectList("PurchaseMapper.getMyPurchaseList",map);
	}
	
	//나의구매상세정보
	@Override
	public Purchase getMyPurchase(int purchaseNo) throws Exception {
		System.out.println("펄체이스넘버"+purchaseNo);
		return sqlSession.selectOne("PurchaseMapper.getMyPurchase", purchaseNo);
	}
	
	@Override
	public int getTotalCount(User user) throws Exception {
		Map<String , Object>  map = new HashMap<String, Object>();
		map.put("user", user);
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
	}
	
	
	
}
