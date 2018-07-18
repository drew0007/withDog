package com.withdog.service.ash.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.ash.AshDAO;
import com.withdog.service.ash.AshService;
import com.withdog.service.domain.AfterAsh;
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
		ashDAO.addHealingDog(healingDog);
		
	}

	@Override
	public HealingDog getHealingDog(int healingDogNo) throws Exception {
		return ashDAO.getHealingDog(healingDogNo);
	}

	@Override
	public void updateHealingDog(HealingDog healingDog) throws Exception {
		ashDAO.updateHealingDog(healingDog);
	}

	@Override
	public List<HealingDog> getHealingDogList() throws Exception {
		return ashDAO.getHealingDogList();
	}
	
	@Override
	public List<HealingDog> getHealingDogListByDate(String ashReservationDate) throws Exception {
		// TODO Auto-generated method stub
		return ashDAO.getHealingDogListByDate(ashReservationDate);
	}
	
	@Override
	public Map<String, Object> getHealingDogList(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		List<HealingDog> list = ashDAO.getHealingDogList(search);
		int totalCount = ashDAO.getTotalCount(search);
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		return map;
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
	public List<Ash> getAshReservationByHealingDog(int healingDogNo) throws Exception {
		// TODO Auto-generated method stub
		return ashDAO.getAshReservationByHealingDog(healingDogNo);
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
	public List<Ash> getAllAshReservationList() throws Exception { //모든 예약현황 가져오기
		return ashDAO.getAllAshReservationList();
	}




	@Override
	public Ash getAshReservationAdmin(int ashReservationNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Ash getAshReservationTime(int healingDogNo, String ashReservationDate) throws Exception {
		// TODO Auto-generated method stub
		return ashDAO.getAshReservationTime(healingDogNo, ashReservationDate);
	}
	
	@Override
	public Ash getAshReservationTimeCount(int healingDogNo, String ashReservationDate) throws Exception {
		// TODO Auto-generated method stub
		return ashDAO.getAshReservationTimeCount(healingDogNo, ashReservationDate);
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
