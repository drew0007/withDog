package com.withdog.service.ash.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
import com.withdog.service.ash.AshDAO;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.HealingDog;

@Repository("ashDAOImpl")
public class AshDAOImpl implements AshDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public AshDAOImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addHealingDog(HealingDog healingDog) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HealingDog getHealingDog(int healingDogNo) throws Exception {
		return sqlSession.selectOne("AshMapper.getHealingDog",healingDogNo);
	}

	@Override
	public void updateHealingDog(HealingDog healingDog) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HealingDog> getHealingDogList() throws Exception {
		return sqlSession.selectList("AshMapper.getAllHealingDog");
	}

	@Override
	public void addConsulting(Consulting consulting) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consulting getConsulting(int consultingNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAshReservation(Ash ash) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ash getAshReservation(int ashReservationNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ash> getAshReservationList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ash> getAshReservationAdminList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ash getAshReservationAdmin(int ashReservationNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAshReservationAdmin(Ash ash) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ash> getAshMyReservationList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ash getAshMyReservation(Ash ash) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAshMyReservation(Ash ash) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
