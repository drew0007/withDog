package com.withdog.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.common.CommonService;
import com.withdog.service.domain.DogBreedDic;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;

import java.util.HashMap;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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
	public JSONObject loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("제이슨 로그인 /user/loginUser : POST");
		System.out.println("user내용 확인"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser내용 확인"+dbUser);
		
		String userCondition = "4" ;
		
		if(dbUser!=null){
			
			//휴면,  탈퇴 여부 체크 ::  컨디션 코드 0 휴면 1 정상 2 탈퇴  4아이디 비번 불일치
			userCondition = dbUser.getUserCondition();
			if(userCondition.equals("0")) {
				userCondition ="0";
				//휴면회원일 경우 아이디값만 넣기
				String userId = user.getUserId();
				session.setAttribute("userCon0", userId);

			}else if(userCondition.equals("2")){
				userCondition ="2";

			}else {
				
				//비밀번호 일치 여부 
				if( user.getPassword().equals(dbUser.getPassword())){
					session.setAttribute("user", dbUser);
					userService.updateRecentlyDate(dbUser.getUserId());
					userCondition ="1";
					
				}//end of if :: userPassword 체크
				
				 //userCondition ="4"; 회원컨디션 1인데 비번틀린 경우
				
			}//end of if :: userCondition 체크
			
		}//end of  if  :: dbUser null 체크
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userCondition", userCondition);
		
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
		String email = user.getEmail1()+user.getEmail2();
		
		user = userService.getUser(user.getUserId());
		String emilDB = user.getEmail1()+user.getEmail2();
		
		///고객이 입력한 이메일과 DB이메일이 같다면 
		if(email.equals(emilDB)) {
			
			  //임시 비밀번호 생성, 디비에 임시 비밀번호로 바꾸기
			   int pwNo = createNo();
			   user.setPassword(pwNo+"");
			   //비번 바꾸기전 user 체크
			   System.out.println("비번ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ "+user);
			   userService.updateUser(user);
			   System.out.println("바뀐 패스워드 체크");
			//이메일 보내기 매서드에 보낼 이메일 주소와 , 임시 비밀번호 보낵기
			boolean sendOk = sendEmail(emilDB,pwNo);
			
			if(sendOk) {
			check=true;
			}
		}
		return check;
		
	}//end PW찾기
	
	//이메일 보내기 1. 자바 메일 lib 추가 2. 보내는 사람 메일 imap허용 
	//@RequestMapping( value="json/emailTest", method=RequestMethod.GET )
	public boolean sendEmail(String emilDB,int pwNo) throws Exception{
		
			  String host     = "smtp.naver.com";
			  String user   = "hyuny0126@naver.com"; //보내는 사람 이메일 주소
			  String password  = "gogosing2014"; //보내는 사람 비밀번호

			  String to     =  emilDB; //받는사람 이메일 주소
			  
			  //보낼 html
			  String emailHtml = "<HTML>" +
					    "<HEAD><TITLE></TITLE></HEAD>" +
					    "<BODY>" +
					    "<h3>안녕하세요.고객님 </h3>" +
					     "<h4>[함께할개] 에서 발송한 임시 비밀번호입니다.</h4></br>"+
					     "<h5>임시 비밀번호 : "+ pwNo +"</h5>"+
					     " <br>"+
					     "<img src=\"https://bit.ly/2Nbd1nf\">"+
					     "<br>"+
					     "</BODY>" +
					    "</HTML>";
	
			  // Get the session object
			  Properties props = new Properties();
			  props.put("mail.smtp.host", host);
			  props.put("mail.smtp.port", 587);
			  props.put("mail.smtp.auth", "true");

			  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(user, password);
			   }
			  });

			  // Compose the message
			  try {
			   MimeMessage message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(user));
			   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			   // Subject
			   message.setSubject("[함께할개] 임시비밀번호 발송하였습니다.");
			  
			   message.setContent(emailHtml	, "text/html; charset=euc-kr");
			  
			   // Text
			   //message.setText(, "text/html");
			   

			   // send the message
			   Transport.send(message);
			   System.out.println("message sent successfully...");

			  } catch (MessagingException e) {
			   e.printStackTrace();
			  }
			 			
		return true;
		
	}//end 이메일 테스트

	//비밀번호 임시 만들기
	public int createNo(){
		
		Random random = new Random();
		
		int pwNo = random.nextInt();
		//절대값으로 만들기
		pwNo= Math.abs(pwNo);
		
		String temp = pwNo+"";
		temp = temp.substring(3);
		
		pwNo =Integer.parseInt(temp);
		
		System.out.println("임시비밀번호pwNo확인"+pwNo);
		return pwNo;
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
				
				String userPhone = dbUser.getPhone();
				
				//2.1  인증번호 랜덤번호로 생성
				int  textNum = createNo();
				
				jsonObject.put("textNum", textNum);
				jsonObject.put("check", true);	
			
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
		int  textNum = createNo();
		
		//2. 인증번호 세션에 넣기
		session.setAttribute("textNum", textNum);
		
		//3. 이메일 링크 전송
		
		//4. 고유번호 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("textNum", textNum);
		
		System.out.println(jsonObject.toJSONString());
			
		return jsonObject;
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
		

	

	
