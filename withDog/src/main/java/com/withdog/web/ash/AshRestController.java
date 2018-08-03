package com.withdog.web.ash;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.withdog.service.ash.AshService;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.HealingDog;
import com.withdog.service.domain.User;

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
	
	@RequestMapping(value = "json/getAshReservationTimeCountByAndroid/{ashReservationDate}")
	public JSONObject getAshReservationTimeCountByAndroid(@PathVariable String ashReservationDate) throws Exception{
		System.out.println("/json/getAshReservationTimeCountByAndroid");
		System.out.println("������ ��¥��? : " + ashReservationDate);
		List<Ash> ash =  ashService.getAshReservationTimeCountByAndroid(ashReservationDate);
		System.out.println("��� : " + ash);
		System.out.println("���222 : " + ash.size());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ash", ash);
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
		
		for (Ash ash : list) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", ash.getAshReservationNo()); // �����ȣ
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //����� �̸� 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //����� ���� 
			jsonObject.put("start", ash.getAshReservationDate()); // ��¥
			System.out.println("��¥��� : " + ash.getAshReservationDate());
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
	
	@RequestMapping(value = "json/addConsulting/{healingDogNo}")
	public int addConsulting(@PathVariable int healingDogNo, HttpServletRequest request) throws Exception{
		System.out.println("/json/addConsulting");
		
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("user");
		
		HealingDog healingDog = new HealingDog();
		healingDog.setHealingDogNo(healingDogNo);
		
		Consulting consulting = new Consulting();
		consulting.setUser(user);
		consulting.setHealingDog(healingDog);
		
		System.out.println(consulting);

		ashService.addConsulting(consulting);
		
		return 1;
	}

	//end ������
	@RequestMapping(value="json/android/kakaoPay")
	private JSONObject paymentReady2() throws RestClientException, URISyntaxException,Exception {
		
		System.out.println("kakaoPay Start==================================");
		
		String HOST = "https://kapi.kakao.com";
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // ������ ��û�� ���� (Body)
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id", "1007");
	    params.add("partner_user_id", "user01");
	    params.add("item_name", "�ɰ��ݵ�");
	    params.add("quantity", "0");//����
	    params.add("total_amount", "150000");
	    params.add("tax_free_amount", "0");//����
	    //params.add("approval_url", "http://127.0.0.1:8080/withdog/addPurchasedog.jsp");
	    params.add("approval_url", "http://192.168.0.42:8080/fund/fundReceipt?state=0");
	    //params.add("approval_url", "http://192.168.0.35:8080/withdog/addPurchasedog.jsp");
	    params.add("cancel_url", "http://192.168.0.42:8080/fund/fundReceipt?state=1");
	    params.add("fail_url", "http://192.168.0.42:8080/fund/fundReceipt?state=2");
	
	    // ������ ��û�� Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "e429428556e2835e02b9dcd4f7f55174");
	    /*headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);*/
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	    String response = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, String.class);
	    System.out.println("�������");
	    System.out.println(response);
	    System.out.println("������ʹٽ�");
	    ObjectMapper obj = new ObjectMapper();
	    JSONObject jobj = (JSONObject)JSONValue.parse(response);
	    System.out.println(jobj.get("tid"));
	    String url = (String)jobj.get("next_redirect_app_url");
	    String url2 = (String)jobj.get("android_app_scheme");
	    String url3 = (String)jobj.get("next_redirect_mobile_url");
	    System.out.println(url);
	    System.out.println(url2.substring(url2.indexOf("h"))+1);
	  	JSONObject jsonUrl = new JSONObject();    
	    jsonUrl.put("url", url);
	    
	  return jsonUrl;
	    
    }

}
