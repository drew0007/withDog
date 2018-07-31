package com.withdog.web.user;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequest;
import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;
import com.withdog.service.user.impl.UserServiceImpl;

//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	//이메일 html 나중에 밖으로 빼보자
	//@Value("#{commonProperties['emailHtml']}")
	//String emailHtml;
	
	///Constructor
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	
	///Method
	//로그인 POST
	@RequestMapping( value="json/loginUser", method=RequestMethod.POST )
	public JSONObject loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response, HttpServletRequest request)  throws Exception {
		
		System.out.println("제이슨 로그인 /user/loginUser : POST");
		
		System.out.println("입력힌user내용 확인"+user);
		
		//Business Logic
		
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser내용 확인"+dbUser);
		
		//jsp에서 확인하는 유저컨디션 값
		String userConCheck = "3" ;
		
		if(dbUser!=null){
			
			//휴면,  탈퇴 여부 체크 ::  컨디션 코드 0휴면 1 정상 2 탈퇴  3아이디 비번 불일치
			String userCondition = dbUser.getUserCondition();
			
			if(userCondition.equals("0")) {
				
				//휴면회원일 경우 유저컨디션 값 
				userConCheck ="0";
				
				//휴면회원일 경우 세션에 아이디값만 넣기
				String userId = user.getUserId();
				session.setAttribute("userCon0", userId);

			}else if(userCondition.equals("2")){
				
				//탈퇴회원일경우 유저컨디션 값
				userConCheck ="2";

			}else {
				
				//비밀번호 일치 여부 
				if( user.getPassword().equals(dbUser.getPassword())){
				
					//정상회원일경우 유저컨디션 값
					userConCheck ="1";
					
					//정상회원일 경우 세션에 유저 도메인 넣기
					session.setAttribute("user", dbUser);
					
					// 정상회원일 경우 최근접속일 업데이트
					userService.updateRecentlyDate(dbUser.getUserId());
					
				}//end of if :: userPassword 체크
				
				 //userCondition ="4"; 회원컨디션 1인데 비번틀린 경우
				
			}//end of if :: userCondition 체크
			
		}//end of  if  :: dbUser null 체크

		// 로그인 이전 페이지
		String prevPage = session.getAttribute("prevPage").toString();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userConCheck", userConCheck);
		jsonObject.put("prevPage", prevPage);
		
		System.out.println("로그인 다시 보내기전에"+jsonObject.toString());
		return jsonObject;

	}//end 로그인
	
	
	
	//비밀번호 변경 페이지에서 기존 비밀번호 확인
	@RequestMapping( value="json/checkPassword", method=RequestMethod.POST )
	public boolean checkPassword(	@RequestBody User user,
																						HttpSession session ) throws Exception{
	
		System.out.println("제이슨으로 /user/json/checkPassword");
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		boolean check = false;
		if( user.getPassword().equals(dbUser.getPassword())){
			check = true;
		}
		return check;
	}//end of checkPassword

	//ID 중복확인
	//회원가입시 id 중복체크
	@RequestMapping( value="json/checkUserId/{userId}", method=RequestMethod.GET )
	public boolean duplicationUser(@PathVariable String userId) throws Exception{
		
		System.out.println(userId);
		//체크값이 true이면 DB에 유저정보 없는것
		boolean check = false;
		if(!userId.equals("")) {
			check = userService.checkUserId(userId);
		}
		
		return check;
	}
	
	//ID 찾기
	//아이디/비밀번호 찾기에서 id 찾기
	@RequestMapping( value="json/findUserId", method=RequestMethod.POST )
	public User  findUserId (@RequestBody User user) throws Exception{
		
		System.out.println("아이디 찾기 제이슨 ");
		//이름,생일 일치할것
		user = userService.findUserId(user);
		if(user==null) {
			user = new User();
		}
		return user;
	}

	//PW 찾기
	//아이디/비밀번호 찾기에서 PW찾기
	@RequestMapping( value="json/findUserPW", method=RequestMethod.POST )
	public boolean  findUserPW (@RequestBody User user) throws Exception{
		
		System.out.println("비번 찾기 제이슨 ");
		boolean check = false;
		
		//ID와 메일 일치할것
		//email : 사용자가 입력한 이메일 주소 emilDB : 입력한 아이디로 디비 다녀온 이메일주소
		String email = user.getEmail();
		
		user = userService.getUser(user.getUserId());
		String emilDB = user.getEmail();
		
		///고객이 입력한 이메일과 DB이메일이 같다면 
		if(email.equals(emilDB)) {
			
			  //임시 비밀번호 생성, 디비에 임시 비밀번호로 바꾸기
			   int tempNo = createTempNo();
			   user.setPassword(tempNo+"");
			   
			   //비번 바꾸기전 user 체크
			   System.out.println("비번ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ "+user);
			   
			   userService.updateUser(user);
			   User user2 = userService.getUser(user.getUserId());
			   
			   System.out.println("바뀐 패스워드 체크"+user2);
			
			   //이메일 보내기 매서드에 보낼 이메일 주소와 , 임시 비밀번호 보낵기
//			     String htmlText = " <h2>안녕하세요</h2><img src=\\\"cid:my-image\\\">";
			   //String htmlText = " <h2>안녕하세요</h2><img src=\"cid:my-image\"> ";
			   String url ="http://192.168.0.41:8080/user/loginUser";
			  String htmlText = " <h2>안녕하세요</h2><img src=\"cid:my-image\"><h3>고객님의 요청으로  <span style=\"color:red;\">임시비밀번호</span>가 발급되었습니다.<br> 임시비밀번호 &nbsp;"+tempNo+" 입니다. </h3>"
			  		+ "<br><h3>로그인 후 비밀번호를 변경해주세요. </h3>"
			  		+ "<br><a href="+url+" target=\"_blank\"><img src=\"http://192.168.0.41:8080/images/icon/withdog.jpg\" /></a>";
	
			     String userEmail = emilDB;
			 boolean sendOk =userService.sendEmail(userEmail, htmlText);
			
			if(sendOk) {
			check=true;
			}
		}
		return check;
		
	}//end PW찾기
	
		
	//비밀번호 임시 만들기
	public int createTempNo(){
		
		Random random = new Random();
		
		int tempNo = random.nextInt();
		//절대값으로 만들기
		tempNo= Math.abs(tempNo);
		
		String temp = tempNo+"";
		temp = temp.substring(3);
		
		tempNo =Integer.parseInt(temp);
		
		System.out.println("임시비밀번호pwNo확인"+tempNo);
		return tempNo;
	}

	
	
	//회원관리리스트 간략보기
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.POST )
	public User getUser( @PathVariable String userId,User user,Point point ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//유저 정보 조회
		user = userService.getUser(userId);
		
		 //포인트 조회;
		user.setUserId(userId);
		 point.setUser(user);
		
		 int userPoint= commonService.getCurrentPoint(point);
		 user.setCurrentPoint(userPoint);
	
		return  user;
	}

	
	//휴면 > 일반  해제 :: 이름과 전화번호로 검색 후 일치하면 인증번호 전송
	@RequestMapping( value="json/changeUserCon", method=RequestMethod.POST )
	public JSONObject changeUserCon(@RequestBody User user ) throws Exception{
		
		System.out.println("휴면계정 레스트 컨트롤러");
		JSONObject jsonObject = new JSONObject();
	
		//1. 이름과 전화번호로 검색
		User dbUser = userService.checkPhone(user);
		
		//2. 휴대폰으로 인증번호 발송
	
			if(dbUser!=null) {
				
				//2.1  인증번호 랜덤번호로 생성 후 문자 내용 작성
				int  tempNo = createTempNo();
				String conText = "[함께할개] 본인확인 인증번호["+tempNo+"]를 화면에 입력해주세요";
				
				//2.2 보낼 전화번호
				String userPhoneNo = dbUser.getPostNo();
				
				//2.3 문자보내기 메서드
				userService.sendText(userPhoneNo, conText);
				
				jsonObject.put("tempNo", tempNo);
				jsonObject.put("check", true);	
				
		
				/*
				//문자로 보내기
			 	String api_key = "NCSKQ1ATROR9MMJC";
			    String api_secret = "HBXDI5ETRJMCRCDKAZHV1CWVF0L6DOKI";
			    //import  중복으로 패키지명까지 
			    net.nurigo.java_sdk.api.Message coolsms = new net.nurigo.java_sdk.api.Message(api_key, api_secret);

			    // 4 params(to, from, type, text) are mandatory. must be filled
			    HashMap<String, String> params = new HashMap<String, String>();
			    params.put("to", userPhone); // 수신번호
			    params.put("from", "01095885027"); // 발신번호
			    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
			    params.put("text", "[함께할개] 본인확인 인증번호["+textNum+"]를 화면에 입력해주세요"); // 문자내용    
			    params.put("app_version", "JAVA SDK v1.2"); // application name and version
				
			    try {
				      JSONObject obj = (JSONObject) coolsms.send(params);
				      System.out.println(obj.toString());
				    } catch (CoolsmsException e) {
				      System.out.println(e.getMessage());
				      System.out.println(e.getCode());
				    }
				    
				    */
			}else {
				jsonObject.put("check",false );	
			}
			
			System.out.println("인증번호 발송"+jsonObject.toJSONString());
			return jsonObject;
			
	}	

	
	//문자로 받은 인증번호 일치 후 휴면 > 정상으로 전환 /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserCondition", method=RequestMethod.POST )
	public boolean checkTextNum(@RequestParam("userId") String userId ) throws Exception{
		
		System.out.println("휴면계정을 해제");
	  
		userService.updateUserCon(userId);
		boolean check= true;
		return check;
	}	
	
	//문자로 받은 인증번호 일치 후 휴면 > 정상으로 전환 /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserConditionTest/{userId}", method=RequestMethod.GET )
	public boolean changeUserConditionTest(@PathVariable String userId ) throws Exception{
		
		System.out.println("휴면계정을 해제");
	
		userService.updateUserCon(userId);
		boolean check= true;
		return check;
	}	
	
	//가입시 이메일 인증  :: 고유번호 세션 + 메일 전송
	@RequestMapping( value="json/checkEmail", method=RequestMethod.POST )
	public JSONObject checkEmail (@RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("가입시 이메일 인증");
		
		//1.인증번호 랜덤번호로 생성
		int  textNum = createTempNo();
		
		//2. 인증번호 세션에 넣기
		session.setAttribute("textNum", textNum);
		
		//3. 이메일 링크 전송
		
		//4. 고유번호 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("textNum", textNum);
		
		System.out.println(jsonObject.toJSONString());
			
		return jsonObject;
	}	
	
	///회원탈퇴 POST
	@RequestMapping( value="json/deleteUser", method=RequestMethod.POST )
	public Boolean deleteUser(@RequestBody User user, HttpSession session ) throws Exception{
		
		System.out.println("회원탈퇴 /json/user/deleteUser : POST");

		userService.deleteUser(user);
		session.invalidate();
	
		return true;
	}
	
	/*
	@RequestMapping( value="/json/checkUserMail", method=RequestMethod.GET )
	public String checkMail(@RequestParam("user") String userId,
							@RequestParam("code") String emailCode
							, Model model) throws Exception{
		System.out.println(userId + "/" + emailCode);
		User user = new User();
		user = userService.getUser(userId);
		
		if(user != null && user.getEmailCode() != null) {
			if(user.getEmailCode().equals(emailCode) && user.getUserStatusCode().equals("3")) {
				user.setUserStatusCode("1");
				userService.updateStatusCode(user);
				model.addAttribute("checkUserMail", true);
				
				return "forward:/index.jsp";
			}
		}
		
		return "redirect:/index.jsp";
	}
	
	*/
	
	
	//SNS간편가입시 해당 아이디 가입 체크 :: 0 카카오 1 네이버 2 구글 3 페이스북
	@RequestMapping( value="json/checkSnsUserId", method=RequestMethod.POST)
	public Boolean checkUser(@RequestBody User user,HttpSession session) throws Exception{
		
		//유저아이디 뽑기
		String userId = user.getUserId();
		
		//snsType 
		System.out.println("확인+++++++++++++++++++++"+user);
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType에 맞도록 해당 snsId로 set
		user = setSnsId(snsType,user,userId);
		
		//true = 디비에 있으므로 로그인 시켜줌 false = sns 간편회원가입 해야함
		Boolean check = false;
		//User dbuser = userService.getUser(userId);
		System.out.println("find들어가기전"+user);
		
		User dbUser = userService.findSnsId(user);
		
		System.out.println("db유저확인"+dbUser);
		
		
		if(dbUser!=null) {
			
			System.out.println("유저가 널이 아님");
			// 예) k864371031 아이디가 있다면 true를 리턴, 세션에 유저 넣기(로그인 성공) 
			session.setAttribute("user", dbUser);
			check= true;
			//로그인시 접속일 업데이트
			userService.updateRecentlyDate(userId);
			
		}else {
			
			// 예) k864371031 아이디가 없다면 false를 리턴, 세션에 카카오 ID 넣기
			System.out.println("유저가 널이다");
			check= false;
			session.setAttribute("tempUser", user);
		}
		
		return check;
	}
	
	
	//Sns 간편 회원가입 화면 POST 
	@RequestMapping( value="json/addSnsUser", method=RequestMethod.POST )
	public Boolean addSnsUser(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("회원가입 입력창 :: /user/json/addSnsUser : POST");
		
		System.out.println("회원가입전에 "+user);
		
		//userId
		String userId = user.getUserId();
		//snsType 
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType에 맞도록 해당 snsId로 set
		user = setSnsId(snsType,user,userId);
		
		//Business Logic
		userService.addUser(user);
		
		user = userService.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		System.out.println("세션에 담은 유저"+user);
		return  true;
		
	}
	
	//SNS연동 :: 먼저 DB조회하고 없다면 sns컬럼업데이트 해주기
	@RequestMapping( value="json/connectSnsId", method=RequestMethod.POST)
	public Boolean connectSnsId(@RequestBody User user,HttpSession session) throws Exception{
		
		System.out.println("sns 연동 : POST");
		//유저아이디 뽑기
		String userId = user.getUserId();
		
		//snsType 
		System.out.println("+"+user);
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType에 맞도록 해당 snsId로 set
		user = setSnsId(snsType,user,userId);
		
		//DB조회 후 해당 아이디가 있다면 연동불가 false / 아이디 없다면 true  해당 snsId로업데이트함
		Boolean check = false;
		
		//User dbuser = userService.getUser(userId);
		
		User dbUser = userService.findSnsId(user);
		
		
		if(dbUser!=null) {
			
			System.out.println("유저가 널이 아님");
			//해당 아이디로는 연동이 안됨
			
		}else {
			
			//int snsType =Integer.parseInt(snsType2);
			user = setSnsId(snsType,user,userId);
			
			//세션에서 뽑은 userId추가
			User user2 = (User)session.getAttribute("user");
			String userId2 = user2.getUserId();
			user.setUserId(userId2);
			System.out.println("DB가기전"+user);
			userService.updateSnsId(user);
			
			check= true;
		
		}
		
		return check;
	}
	
	
	//snType에 따른 setSns~Id 입력 :: 0 카카오 1 네이버 2 구글 3 페이스북
	public User setSnsId(int snsType, User user, String userId) {
		
		if(snsType==0) {
			
			user.setSnsKakaoId(userId);
						
		}else if(snsType==1) {
			
			user.setSnsNaverId(userId);
			
		}else if(snsType==2) {
			
			user.setSnsGoogleId(userId);
			
		}else {
			user.setSnsFacebookId(userId);
		}
		
		return user;
		
	}
	
	//네이버 로그인
	@RequestMapping( value="json/loginWithNaver", method=RequestMethod.GET)
	public JSONObject loginWithNaver() throws Exception{ 
	    String clientId = "FCLaJ11V_c1179DGKDU1";//애플리케이션 클라이언트 아이디값";
	    String redirectURI = URLEncoder.encode("http://localhost:8080/user/loginWithNaver", "UTF-8");
	    SecureRandom random = new SecureRandom();
	    String state = new BigInteger(130, random).toString();
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    //session.setAttribute("state", state);
	    JSONObject jsonobj = new JSONObject();
	    jsonobj.put("apiURL", apiURL);
	    System.out.println("jsonobj : "+jsonobj);
	    
		return jsonobj;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////
	//장원이 테스트
	//아이디/비밀번호 찾기에서 id 찾기
	@RequestMapping( value="json/test", method=RequestMethod.GET )
	public JSONObject  findUserId () throws Exception{
		
		System.out.println("제이슨 테스트 ");
		//이름,생일 일치할것
		List<String> aaa = new ArrayList<String>() ;

		aaa.add(0, "10");
		aaa.add(1, "20");
		aaa.add(2, "30");
		aaa.add(3, "40");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", aaa);
		
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject;
	}
	

	
	
}//end of class
		

	

	
