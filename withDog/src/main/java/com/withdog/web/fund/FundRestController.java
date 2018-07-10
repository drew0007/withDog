package com.withdog.web.fund;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import com.withdog.service.domain.Fund;
import com.withdog.service.fund.FundService;

@RestController
@RequestMapping("/fund/*")
public class FundRestController {
	
	@Autowired
	@Qualifier("fundServiceImpl")
	private FundService fundService;

	public FundRestController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="json/kakaoPay")
	private String kakaoPay(@ModelAttribute("Fund") Fund fund,HttpSession session,HttpServletRequest req) throws Exception{
		
		System.out.println("kakaoPay Start==================================");
		System.out.println(req.getParameter("usePoint"));
		System.out.println(fund.toString());
		
		String HOST = "https://kapi.kakao.com";
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // 서버로 요청할 내용 (Body)
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id","admin");
	    params.add("partner_user_id","user01");
	    params.add("item_name",fund.getFundTitle());
	    params.add("quantity", "1");//수량
	    params.add("total_amount", new String(fund.getFundMyPrice()+"").trim());
	    params.add("tax_free_amount", "0");//세금
	    params.add("approval_url", "http://192.168.0.42:8080/fund/getFundList");
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
	    JSONObject jobj = (JSONObject)JSONValue.parse(response);
	    System.out.println(jobj.get("tid"));
	    String url = (String)jobj.get("next_redirect_pc_url");
	    System.out.println(url);
	    session.setAttribute("url", url);
	    params.add("cid", "TC0ONETIME");
	    params.add("tid", (String)jobj.get("tid"));
	    params.add("partner_order_id", "1001");
	    params.add("partner_user_id", "test@koitt.com");
	    params.add("item_name", "갤럭시S9");
	    
	    
	    ///addPurchase.jsp로 callback 되는지
	    session.setAttribute("fund", fund);
	    
	    //구매완료 되면 add시키고 이동시키기
	    
	    //fundService.addFund(fund);
	    
	    
	  return "forward:/sns/kakaoPay.jsp";
	    
    }

}
