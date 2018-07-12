package com.withdog.web.fund;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.withdog.common.Page;
import com.withdog.common.Search;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Fund;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;
import com.withdog.service.fund.FundService;







//==> 회원관리 Controller

@Controller
@RequestMapping("/fund/*")
public class FundController {
	
	///Field
	@Autowired
	@Qualifier("fundServiceImpl")
	private FundService fundService;
		
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	//setter Method 구현 않음
		
	public FundController(){
		System.out.println("FundController 시작"+this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['fundpageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['fundpageSize']}")
	int pageSize;
	
	@Value("#{commonProperties['fundfilePath']}")
	String path;
	
	@RequestMapping(value="/addFundView", method=RequestMethod.GET)
	public String addFundView() throws Exception {

		System.out.println("/addFundView :GET Start");
		
					
		return "forward:/fund/addFund.jsp";
	}
	
	@RequestMapping(value="/addFund",method=RequestMethod.POST)
	public String addFund(@ModelAttribute("fund") Fund fund,HttpServletRequest request,@RequestParam("fundImagePath") MultipartFile fileName ) throws Exception {

		System.out.println("/addFund : POST Start");
		//Business Logic
		
		/*Properties properties = new Properties();
		properties.load(new FileInputStream("C:/common.properties"));*/
		
		/*String path = properties.getProperty("filepath");*/
		String filetemp = fileName.getOriginalFilename();
		
		File file = new File(path+filetemp);
		fileName.transferTo(file);
		
		
		fund.setFundImage(filetemp);
		
		System.out.println(fund.toString());
		

				
		fundService.addFund(fund);
						
		
		return "forward:/fund/getFundList";
		
	}
	
	@RequestMapping(value="/updateFund", method=RequestMethod.GET)
	public String updateFundView(@RequestParam("fundNo") int fundNo,HttpServletRequest request) throws Exception {

		System.out.println("/updateFundView :GET Start");
		
	    Fund fund = fundService.getFund(fundNo);
	    
	    request.setAttribute("fund", fund);
	    
	    String Term = fund.getFundTerm();
	    String[] temp = new String[2]; 
	    temp = Term.split("~");
	    
	    request.setAttribute("temp", temp);
		
		
		
					
		return "forward:/fund/updateFund.jsp";
	}
	
	@RequestMapping(value="/updateFund",method=RequestMethod.POST)
	public String updateFund(@ModelAttribute("fund") Fund fund,HttpServletRequest request,@RequestParam("fundImagePath") MultipartFile fileName ) throws Exception {

		System.out.println("/updateFund : POST Start");
		//Business Logic
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("C:/common.properties"));
		
		String path="";
		String filetemp="";
		System.out.println(1);
		
		if(fileName.getSize() != 0) {
		System.out.println(2);
		path = properties.getProperty("filepath");
		filetemp = fileName.getOriginalFilename();
		
		
		File file = new File(path+filetemp);
		fileName.transferTo(file);
		}
		else {
		System.out.println(3);
		Fund before=fundService.getFund(fund.getFundNo());
			
		filetemp = before.getFundImage();	
		}
		
		fund.setFundImage(filetemp);
		
		System.out.println(fund.toString());
		

				
		fundService.updateFund(fund);
						
		
		return "forward:/fund/getFund?fundNo="+fund.getFundNo();
		
	}
	
	
	
	@RequestMapping(value="/getFund")
	public String getFund(@RequestParam("fundNo")  int fundNo,HttpServletRequest request) throws Exception {

		System.out.println("/getFund : Start");
		//Business Logic
		
		
		
		HttpSession session = request.getSession(false);
		User user = new User();
		
		Fund fund = fundService.getFund(fundNo);
		
		if(session.getAttribute("user")!=null) {
			user = (User)session.getAttribute("user");
			System.out.println(user.getUserId());
			
			//임시
			Point point = new Point();
			point.setUser(user);
			
			int currentPoint=commonService.getCurrentPoint(point);
			
			
			request.setAttribute("currentPoint", currentPoint);
		}
		
		
		
		request.setAttribute("fund", fund);
		
		return "forward:/fund/getFund.jsp";
	}
	
	
	@RequestMapping(value="/getFundList")
	public String getFundList(HttpServletRequest request,HttpSession session) throws Exception{
		 
	 	
	 	System.out.println("/FundList : Start");
	 	
	 	User user = (User)session.getAttribute("user");
		
	 	List<Fund> list = fundService.getFundList(user); 	
		
		for (int i = 0; i < list.size(); i++) {
		
			System.out.println(list.get(i).toString());
		}
				
		request.setAttribute("list", list);
		
		Fund fund = new Fund();
		fund=fundService.getMinFund();
		System.out.println(fund.toString());
		
		request.setAttribute("fund", fund);
		
		System.out.println("FundList End=================================");
		
		return "forward:/fund/listFund.jsp";
		
		
	 }
	
	@RequestMapping(value="/getFundResultList")
	public String getFundResultList(@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session) throws Exception{
		 
	 	
	 	System.out.println("/FundResultList : Start");
		System.out.println(search.toString());
		User user = new User();
		if(session.getAttribute("user")!=null) {
		user = (User)session.getAttribute("user");
		}
	 	if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
	 		 	
		Map<String,Object> map = fundService.getFundResultList(search,user); 	
		
		System.out.println("MAP 체크 ===========================");
		System.out.println(map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
				
		
		return "forward:/fund/listFundResult.jsp";
		
		
	 }
	
	
	@RequestMapping(value="kakaoPay")
	private String kakaoPay(@ModelAttribute("fund") Fund fund,HttpServletRequest req) throws Exception{
		
		System.out.println("kakaoPay Start==================================");
		System.out.println(req.getParameter("usePoint"));
		HttpSession session = req.getSession(false);
		User user = new User();
		if(session.getAttribute("user")!=null) {
		user = (User)session.getAttribute("user");
		System.out.println(user.getUserId());
		}else {
		user.setUserId("temp");	
		}
		
		///영수증.jsp로 callback 되는지
	    String forwardUri="forward:/sns/kakaoPay.jsp";
		
		String title = fund.getFundTitle();
		int price=0;
		int usePoint=0;
		if(fund.getFundMyPrice()!=0) {
		price = fund.getFundMyPrice();
		}
		
		if(req.getParameter("usePoint")!=null) {
		usePoint=Integer.parseInt(req.getParameter("usePoint"));
		}
		
		
		if(price!=0) {
		String HOST = "https://kapi.kakao.com";
	    RestTemplate restTemplate = new RestTemplate();
	    
	    // 서버로 요청할 내용 (Body)
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("partner_order_id","admin");
	    params.add("partner_user_id",user.getUserId());
	    params.add("item_name",fund.getFundTitle());
	    params.add("quantity", "1");//수량
	    params.add("total_amount", new String(fund.getFundMyPrice()+"").trim());
	    params.add("tax_free_amount", "0");//세금
	    params.add("approval_url", "http://192.168.0.42:8080/fund/fundReceipt?title="+title+"&price="+price+"&usePoint="+usePoint);
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
	    
	    
		}
		else {
		forwardUri="forward:/fund/fundReceipt?title="+title+"&price="+price+"&usePoint="+usePoint;	
		}
	    
	    
	    //임시처리
	    
	    Point point = new Point();
	        
	    //포인트 이것만 긁기
	    fund.setUser(user);
	    point.setUser(user);//userid
	    point.setFund(fund);//후원,구매,예약 구분을 위해
	    
	    //후원완료 되면 add시키고 이동시키기
	    //fund테이블에 후원금액 추가
	    int raising = price+usePoint;
	    fund.setFundRaising(raising);
	    System.out.println("후원금액 : "+raising);
	    fundService.addFundRaising(fund);
	    
	    
	    
	    double savePoint = price*0.01;
	    System.out.println("적립포인트"+savePoint);
	    int resultpoint = (int)savePoint;
	    point.setPoint(resultpoint);
	    point.setUsePoint(usePoint);
	   	commonService.addPointinfo(point);
	    	    
	 
	  return forwardUri;
	    
    }
	
	@RequestMapping(value="/fundReceipt")
	public String getfundReceipt(HttpServletRequest req) throws Exception {

		System.out.println("/getFundReceipt : Start");
		
		System.out.println(req.getParameter("title"));
		System.out.println(req.getParameter("price"));
		System.out.println(req.getParameter("usePoint"));
		
		req.setAttribute("title", req.getParameter("title"));
		req.setAttribute("price", req.getParameter("price"));
		req.setAttribute("usePoint", req.getParameter("usePoint"));
		
		
		
		return "forward:/fund/fundReceipt.jsp";
	}
	
	@RequestMapping(value="/deleteFund")
	public String deleteFund(@ModelAttribute("fund") Fund fund) throws Exception {

		System.out.println("/deleteFUnd : Start");
	
		/*Fund ofund = fundService.getFund(fund.getFundNo());*/
		
		fundService.deleteFund(fund);
					
		return "forward:/fund/getFundList";
	}
	
	
	@RequestMapping(value="/fundGuid")
	public String getFundGuid() throws Exception {

		System.out.println("/getFundGuid : Start");
		
					
		return "forward:/fund/fundGuid.jsp";
	}
	
	@RequestMapping(value="/fundReq")
	public String getfundReq() throws Exception {

		System.out.println("/getFundReq : Start");
		
					
		return "forward:/fund/fundReq.jsp";
	}
	
	
	
		
	
}