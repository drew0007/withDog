package com.withdog.service.ash;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.HealingDog;

public interface AshDAO {
	
	public void addHealingDog(HealingDog healingDog) throws Exception;

	public HealingDog getHealingDog(int healingDogNo) throws Exception;

	public void updateHealingDog(HealingDog healingDog) throws Exception;

	public List<HealingDog> getHealingDogList() throws Exception;

	// ÄÁ¼³ÆÃ
	public void addConsulting(Consulting consulting) throws Exception;

	public Consulting getConsulting(int consultingNo) throws Exception;

	//
	public void addAshReservation(Ash ash) throws Exception;

	public Ash getAshReservation(int ashReservationNo) throws Exception;

	public List<Ash> getAshReservationList(Search search) throws Exception;

	public List<Ash> getAshReservationAdminList(Search search) throws Exception;

	public Ash getAshReservationAdmin(int ashReservationNo) throws Exception;

	public void updateAshReservationAdmin(Ash ash) throws Exception;

	public List<Ash> getAshMyReservationList(String userId) throws Exception;

	public Ash getAshMyReservation(Ash ash) throws Exception;

	public void updateAshMyReservation(Ash ash) throws Exception;

}
