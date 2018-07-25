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
		System.out.println("������ ��¥��? : " + ashReservationDate);
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
		
		System.out.println("list ��� : " + list);
		JSONObject jsonObject = new JSONObject();
		
		for (Ash ash : list) {
			
			jsonObject.put("id", ash.getAshReservationNo()); // �����ȣ
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //����� �̸� 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //����� ���� 
			jsonObject.put("start", ash.getAshReservationDate()); // ��¥
			switch(ash.getHealingDog().getHealingDogNo()) {
			case 10015 : jsonObject.put("color", "#FDED87"); //�޷� ��
				break;
			case 10016 :jsonObject.put("color", "GREEN"); //�޷� ��
				break;
			case 10017:jsonObject.put("color", "BLUE"); //�޷� ��
				break;
			case 10018 :jsonObject.put("color", "GRAY"); //�޷� ��
				break;
			case 10019 :jsonObject.put("color", "YELLOW"); //�޷� ��
				break;
			case 10020 :jsonObject.put("color", "PURPLE"); //�޷� ��
				break;
			}
			
			if(ash.getAshReservationTime().equals("0")) { // �ð� 0�̸� ���� 1�̸� ����
				jsonObject.put("time", "[����]");
			}else {
				jsonObject.put("time", "[����]");
			}
			jsonArray.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
		Date d = new Date();
		String today = sim.format(d);
		jsonObject2.put("id", "������"); // �����ȣ
		jsonObject2.put("title", "������"); //����� �̸� 
		jsonObject2.put("start", "2005-07-22"); // ��¥
		jsonObject2.put("end", today); // ��¥
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray.toString());
		
		
		return  jsonArray.toString();
	}
	
	@RequestMapping(value = "json/getAshReservationByHealingDog/{healingDogNo}")
	public String getAshReservationByHealingDog(@PathVariable int healingDogNo) throws Exception{
		System.out.println("/json/getAshReservationByHealingDog");
		List<Ash> list = ashService.getAshReservationByHealingDog(healingDogNo);
		JSONArray jsonArray = new JSONArray();
		
		System.out.println("list ��� : " + list);
		
		for (Ash ash : list) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", ash.getAshReservationNo()); // �����ȣ
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //����� �̸� 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //����� ���� 
			jsonObject.put("start", ash.getAshReservationDate()); // ��¥
			switch(ash.getHealingDog().getHealingDogNo()) {
			case 10015 : jsonObject.put("color", "#FDED87"); //�޷� ��
				break;
			case 10016 :jsonObject.put("color", "GREEN"); //�޷� ��
				break;
			case 10017:jsonObject.put("color", "BLUE"); //�޷� ��
				break;
			case 10018 :jsonObject.put("color", "GRAY"); //�޷� ��
				break;
			case 10019 :jsonObject.put("color", "YELLOW"); //�޷� ��
				break;
			case 10020 :jsonObject.put("color", "PURPLE"); //�޷� ��
				break;
			}
			
			if(ash.getAshReservationTime().equals("0")) { // �ð� 0�̸� ���� 1�̸� ����
				jsonObject.put("time", "[����]");
			}else {
				jsonObject.put("time", "[����]");
			}
			jsonArray.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-dd");
		Date d = new Date();
		String today = sim.format(d);
		jsonObject2.put("id", "������"); // �����ȣ
		jsonObject2.put("title", "������"); //����� �̸� 
		jsonObject2.put("start", "2005-07-22"); // ��¥
		jsonObject2.put("end", today); // ��¥
		jsonArray.add(jsonObject2);
		System.out.println(jsonArray.toString());
		
		
		return  jsonArray.toString();
	}
	
	
	@RequestMapping(value = "/json/getAshReservationTimeCount/{date}")
	public JSONObject getAshReservationTimeCount(@PathVariable String date, @RequestParam("healingDogNo") int healingDogNo)throws Exception{
//		int healingDogNo = Integer.parseInt(request.getParameter("healingDogNo"));
		System.out.println("�Ѿ�� ������ �ѹ� : " + healingDogNo);
		System.out.println("�Ѿ�� ��¥ : " + date);
		
//		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		Ash ash = ashService.getAshReservationTimeCount(healingDogNo, date);
		
//		System.out.println("��������? : " + healingDog);
		System.out.println("������? : " + ash);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ash", ash);
				
		
		return jsonObject;
	}
	
//	������ 
	
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

	//end ������
	

}
