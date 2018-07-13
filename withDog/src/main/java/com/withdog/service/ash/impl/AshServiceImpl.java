package com.withdog.service.ash.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.ash.AshDAO;
import com.withdog.service.ash.AshService;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.HealingDog;

@Service("ashServiceImpl")
public class AshServiceImpl implements AshService{

	@Autowired
	@Qualifier("ashDAOImpl")
	private AshDAO ashDAO;

	public AshServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addHealingDog(HealingDog healingDog) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HealingDog getHealingDog(int healingDogNo) throws Exception {
		return ashDAO.getHealingDog(healingDogNo);
	}

	@Override
	public void updateHealingDog(HealingDog healingDog) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HealingDog> getHealingDogList() throws Exception {
		return ashDAO.getHealingDogList();
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
