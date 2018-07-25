package com.withdog.web.ash;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.ash.AshService;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.HealingDog;

@RestController
@RequestMapping("/ash/*")
public class AshRestController {

	@Autowired
	@Qualifier("ashServiceImpl")
	private AshService ashService;

	public AshRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value = "json/getAllHealingDogList")
	public JSONObject getAllHealingDogList() throws Exception{
		System.out.println("/json/getAllHealingDogList");
		List<HealingDog> healingDogs =  ashService.getHealingDogList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("healingDogs", healingDogs);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getHealingDogListByDate/{ashReservationDate}")
	public JSONObject getHealingDogListByDate(@PathVariable String ashReservationDate) throws Exception{
		System.out.println("/json/getHealingDogListByDate");
		System.out.println("선택한 날짜는? : " + ashReservationDate);
		List<HealingDog> healingDogs =  ashService.getHealingDogListByDate(ashReservationDate);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("healingDogs", healingDogs);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getHealingDog/{healingDogNo}")
	public JSONObject getHealingDog(@PathVariable int healingDogNo) throws Exception{
		System.out.println("/json/getHealingDog");
		HealingDog healingDog =  ashService.getHealingDog(healingDogNo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("healingDog", healingDog);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getAllReservationASHList")
	public String getAllReservationASHList() throws Exception{
		System.out.println("/json/getAllReservationASHList");
		
		List<Ash> list = ashService.getAllAshReservationList();
		JSONArray jsonArray = new JSONArray();
		
		System.out.println("list 출력 : " + list);
		JSONObject jsonObject = new JSONObject();
		
		for (Ash ash : list) {
			
			jsonObject.put("id", ash.getAshReservationNo()); // 예약번호
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //예약견 이름 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //예약견 사진 
			jsonObject.put("start", ash.getAshReservationDate()); // 날짜
			switch(ash.getHealingDog().getHealingDogNo()) {
			case 10015 : jsonObject.put("color", "#FDED87"); //달력 색
				break;
			case 10016 :jsonObject.put("color", "GREEN"); //달력 색
				break;
			case 10017:jsonObject.put("color", "BLUE"); //달력 색
				break;
			case 10018 :jsonObject.put("color", "GRAY"); //달력 색
				break;
			case 10019 :jsonObject.put("color", "YELLOW"); //달력 색
				break;
			case 10020 :jsonObject.put("color", "PURPLE"); //달력 색
				break;
			}
			
			if(ash.getAshReservationTime().equals("0")) { // 시간 0이면 오전 1이면 오후
				jsonObject.put("time", "[오전]");
			}else {
				jsonObject.put("time", "[오후]");
			}
			jsonArray.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
		Date d = new Date();
		String today = sim.format(d);
		jsonObject2.put("id", "지난날"); // 예약번호
		jsonObject2.put("title", "지난견"); //예약견 이름 
		jsonObject2.put("start", "2005-07-22"); // 날짜
		jsonObject2.put("end", today); // 날짜
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray.toString());
		
		
		return  jsonArray.toString();
	}
	
	@RequestMapping(value = "json/getAshReservationByHealingDog/{healingDogNo}")
	public String getAshReservationByHealingDog(@PathVariable int healingDogNo) throws Exception{
		System.out.println("/json/getAshReservationByHealingDog");
		List<Ash> list = ashService.getAshReservationByHealingDog(healingDogNo);
		JSONArray jsonArray = new JSONArray();
		
		System.out.println("list 출력 : " + list);
		
		for (Ash ash : list) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", ash.getAshReservationNo()); // 예약번호
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //예약견 이름 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //예약견 사진 
			jsonObject.put("start", ash.getAshReservationDate()); // 날짜
			switch(ash.getHealingDog().getHealingDogNo()) {
			case 10015 : jsonObject.put("color", "#FDED87"); //달력 색
				break;
			case 10016 :jsonObject.put("color", "GREEN"); //달력 색
				break;
			case 10017:jsonObject.put("color", "BLUE"); //달력 색
				break;
			case 10018 :jsonObject.put("color", "GRAY"); //달력 색
				break;
			case 10019 :jsonObject.put("color", "YELLOW"); //달력 색
				break;
			case 10020 :jsonObject.put("color", "PURPLE"); //달력 색
				break;
			}
			
			if(ash.getAshReservationTime().equals("0")) { // 시간 0이면 오전 1이면 오후
				jsonObject.put("time", "[오전]");
			}else {
				jsonObject.put("time", "[오후]");
			}
			jsonArray.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
		Date d = new Date();
		String today = sim.format(d);
		jsonObject2.put("id", "지난날"); // 예약번호
		jsonObject2.put("title", "지난견"); //예약견 이름 
		jsonObject2.put("start", "2005-07-22"); // 날짜
		jsonObject2.put("end", today); // 날짜
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray.toString());
		
		
		return  jsonArray.toString();
	}
	
	
	@RequestMapping(value = "/json/getAshReservationTimeCount/{date}")
	public JSONObject getAshReservationTimeCount(@PathVariable String date, @RequestParam("healingDogNo") int healingDogNo)throws Exception{
//		int healingDogNo = Integer.parseInt(request.getParameter("healingDogNo"));
		System.out.println("넘어온 힐링독 넘버 : " + healingDogNo);
		System.out.println("넘어온 날짜 : " + date);
		
//		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		Ash ash = ashService.getAshReservationTimeCount(healingDogNo, date);
		
//		System.out.println("힐링독은? : " + healingDog);
		System.out.println("예약은? : " + ash);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ash", ash);
				
		
		return jsonObject;
	}
	
//	컨설팅 
	
	@RequestMapping(value = "json/updateConsultingState/{consultingNo}/{consultingState}")
	public int updateConsultingState(@PathVariable int consultingNo, @PathVariable String consultingState) throws Exception{
		System.out.println("/json/updateConsultingState");
		
		Consulting consulting = new Consulting();
		consulting.setConsultingNo(consultingNo);
		consulting.setConsultingState(consultingState);
		
		System.out.println(consulting);
		
		ashService.updateConsultingState(consulting);
		
		return 1;
	}

	//end 컨설팅
	

}
