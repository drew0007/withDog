package com.withdog.web.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.domain.DogBreedDic;
import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;



//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
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
	public boolean loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("제이슨 로그인 /user/loginUser : POST");
		System.out.println("user내용 확인"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser내용 확인"+dbUser);
		
		boolean check = false;
		if(dbUser!=null){
			
			if( user.getPassword().equals(dbUser.getPassword())){
				session.setAttribute("user", dbUser);
				userService.updateRecentlyDate(dbUser.getUserId());
				check= true;
			}
			
		}
		System.out.println("check 확인"+check);
		return check;

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
		Math.abs(pwNo);
		System.out.println("임시비밀번호pwNo확인"+pwNo);
		return pwNo;
	}

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
		

	

	
