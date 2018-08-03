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
		System.out.println("선택한 날짜는? : " + ashReservationDate);
		List<HealingDog> healingDogs =  ashService.getHealingDogListByDate(ashReservationDate);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("healingDogs", healingDogs);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getAshReservationTimeCountByAndroid/{ashReservationDate}")
	public JSONObject getAshReservationTimeCountByAndroid(@PathVariable String ashReservationDate) throws Exception{
		System.out.println("/json/getAshReservationTimeCountByAndroid");
		System.out.println("선택한 날짜는? : " + ashReservationDate);
		List<Ash> ash =  ashService.getAshReservationTimeCountByAndroid(ashReservationDate);
		System.out.println("결과 : " + ash);
		System.out.println("결과222 : " + ash.size());
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
		
		System.out.println("list 출력 : " + list);
		
		for (Ash ash : list) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("id", ash.getAshReservationNo()); // 예약번호
			jsonObject.put("title", ash.getHealingDog().getHealingDogName()); //예약견 이름 
			jsonObject.put("image", ash.getHealingDog().getHealingDogimage()); //예약견 사진 
			jsonObject.put("start", ash.getAshReservationDate()); // 날짜
			System.out.println("날짜출력 : " + ash.getAshReservationDate());
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

	//end 컨설팅
	@RequestMapping(value="json/android/kakaoPay")
	private JSONObject paymentReady2() throws RestClientException, URISyntaxException,Exception {
		
		System.out.println("kakaoPay Start==================================");
		
		String HOST = "https://kapi.kakao.com";
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // 서버로 요청할 내용 (Body)
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id", "1007");
	    params.add("partner_user_id", "user01");
	    params.add("item_name", "꽃개펀딩");
	    params.add("quantity", "0");//수량
	    params.add("total_amount", "150000");
	    params.add("tax_free_amount", "0");//세금
	    //params.add("approval_url", "http://127.0.0.1:8080/withdog/addPurchasedog.jsp");
	    params.add("approval_url", "http://192.168.0.42:8080/fund/fundReceipt?state=0");
	    //params.add("approval_url", "http://192.168.0.35:8080/withdog/addPurchasedog.jsp");
	    params.add("cancel_url", "http://192.168.0.42:8080/fund/fundReceipt?state=1");
	    params.add("fail_url", "http://192.168.0.42:8080/fund/fundReceipt?state=2");
	
	    // 서버로 요청할 Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "e429428556e2835e02b9dcd4f7f55174");
	    /*headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);*/
	    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

	    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	    String response = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, String.class);
	    System.out.println("여기까지");
	    System.out.println(response);
	    System.out.println("여기부터다시");
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
