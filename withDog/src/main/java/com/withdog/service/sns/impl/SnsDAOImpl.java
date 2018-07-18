package com.withdog.service.sns.impl;

import java.net.URI;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.withdog.service.domain.Point;
import com.withdog.service.sns.SnsDAO;

@Service("snsServiceDAOImpl")
public class SnsDAOImpl implements SnsDAO {

	@Override
	public JSONObject kakaoPay(Point point,String uri) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=========================SNSDAO Start=========================");
		
		JSONObject jobj = new JSONObject();
		
		if(point.getFund().getFundTitle()!=null) {
			System.out.println("Fund purchase Start");
			if(point.getFund().getFundMyPrice()!=0) {
			String HOST = "https://kapi.kakao.com";
		    RestTemplate restTemplate = new RestTemplate();
		    
		    // 서버로 요청할 내용 (Body)
		    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		    params.add("cid", "TC0ONETIME");
		    params.add("partner_order_id","admin");
		    params.add("partner_user_id",point.getUser().getUserId());
		    params.add("item_name",point.getFund().getFundTitle());
		    params.add("quantity", "1");//수량
		    params.add("total_amount", new String(point.getFund().getFundMyPrice()+"").trim());
		    params.add("tax_free_amount", "0");//세금
		    params.add("approval_url", "http://192.168.0.42:8080/fund/fundReceipt?title="+point.getFund().getFundTitle()+"&price="+point.getFund().getFundMyPrice()+"&usePoint="+point.getUsePoint());
		    params.add("cancel_url", "http://127.0.0.1:8080/purchase/json/paycancel");
		    params.add("fail_url", "http://127.0.0.1:8080/purchase/json/fail");
		
		    // 서버로 요청할 Header
		    HttpHeaders headers = new HttpHeaders();
		    headers.add("Authorization", "KakaoAK " + "e429428556e2835e02b9dcd4f7f55174");
		    //headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		    headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		    String response = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), request, String.class);
		    System.out.println("여기까지");
		    System.out.println(response);
		    System.out.println("여기부터다시");
		    ObjectMapper obj = new ObjectMapper();
		   jobj = (JSONObject)JSONValue.parse(response);
		    System.out.println(jobj.get("tid"));
		  		    
		  
			
			}
			
		}else {
			jobj = null;	
			}
		return jobj;
	}
	
	
}