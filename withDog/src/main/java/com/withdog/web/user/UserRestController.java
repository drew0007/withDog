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



//==> 噺据淫軒 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	//戚五析 html 蟹掻拭 鉱生稽 皐左切
	//@Value("#{commonProperties['emailHtml']}")
	//String emailHtml;
	
	///Constructor
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	
	///Method
	//稽益昔 POST
	@RequestMapping( value="json/loginUser", method=RequestMethod.POST )
	public boolean loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("薦戚充 稽益昔 /user/loginUser : POST");
		System.out.println("user鎧遂 溌昔"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser鎧遂 溌昔"+dbUser);
		
		boolean check = false;
		if(dbUser!=null){
			
			if( user.getPassword().equals(dbUser.getPassword())){
				session.setAttribute("user", dbUser);
				userService.updateRecentlyDate(dbUser.getUserId());
				check= true;
			}
			
		}
		System.out.println("check 溌昔"+check);
		return check;

	}//end 稽益昔
	
	
	
	//搾腔腰硲 痕井 凪戚走拭辞 奄糎 搾腔腰硲 溌昔
	@RequestMapping( value="json/checkPassword", method=RequestMethod.POST )
	public boolean checkPassword(	@RequestBody User user,
																						HttpSession session ) throws Exception{
	
		System.out.println("薦戚充生稽 /user/json/checkPassword");
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		boolean check = false;
		if( user.getPassword().equals(dbUser.getPassword())){
			check = true;
		}
		return check;
	}//end of checkPassword

	//ID 掻差溌昔
	//噺据亜脊獣 id 掻差端滴
	@RequestMapping( value="json/checkUserId/{userId}", method=RequestMethod.GET )
	public boolean duplicationUser(@PathVariable String userId) throws Exception{
		
		System.out.println(userId);
		
		boolean check = false;
		if(!userId.equals("")) {
			check = userService.checkUserId(userId);
		}
		
		return check;
	}
	
	//ID 達奄
	//焼戚巨/搾腔腰硲 達奄拭辞 id 達奄
	@RequestMapping( value="json/findUserId", method=RequestMethod.POST )
	public User  findUserId (@RequestBody User user) throws Exception{
		
		System.out.println("焼戚巨 達奄 薦戚充 ");
		//戚硯,持析 析帖拝依
		user = userService.findUserId(user);
		if(user==null) {
			user = new User();
		}
		return user;
	}

	//PW 達奄
	//焼戚巨/搾腔腰硲 達奄拭辞 PW達奄
	@RequestMapping( value="json/findUserPW", method=RequestMethod.POST )
	public boolean  findUserPW (@RequestBody User user) throws Exception{
		
		System.out.println("搾腰 達奄 薦戚充 ");
		boolean check = false;
		
		//ID人 五析 析帖拝依
		//email : 紫遂切亜 脊径廃 戚五析 爽社 emilDB : 脊径廃 焼戚巨稽 巨搾 陥橿紳 戚五析爽社
		String email = user.getEmail1()+user.getEmail2();
		
		user = userService.getUser(user.getUserId());
		String emilDB = user.getEmail1()+user.getEmail2();
		
		///壱梓戚 脊径廃 戚五析引 DB戚五析戚 旭陥檎 
		if(email.equals(emilDB)) {
			
			  //績獣 搾腔腰硲 持失, 巨搾拭 績獣 搾腔腰硲稽 郊荷奄
			   int pwNo = createNo();
			   user.setPassword(pwNo+"");
			   //搾腰 郊荷奄穿 user 端滴
			   System.out.println("搾腰しししししししししし "+user);
			   userService.updateUser(user);
			   System.out.println("郊駕 鳶什趨球 端滴");
			//戚五析 左鎧奄 古辞球拭 左馨 戚五析 爽社人 , 績獣 搾腔腰硲 左骸奄
			boolean sendOk = sendEmail(emilDB,pwNo);
			
			if(sendOk) {
			check=true;
			}
		}
		return check;
		
	}//end PW達奄
	
	//戚五析 左鎧奄 1. 切郊 五析 lib 蓄亜 2. 左鎧澗 紫寓 五析 imap買遂 
	//@RequestMapping( value="json/emailTest", method=RequestMethod.GET )
	public boolean sendEmail(String emilDB,int pwNo) throws Exception{
		
			  String host     = "smtp.naver.com";
			  String user   = "hyuny0126@naver.com"; //左鎧澗 紫寓 戚五析 爽社
			  String password  = "gogosing2014"; //左鎧澗 紫寓 搾腔腰硲

			  String to     =  emilDB; //閤澗紫寓 戚五析 爽社
			  
			  //左馨 html
			  String emailHtml = "<HTML>" +
					    "<HEAD><TITLE></TITLE></HEAD>" +
					    "<BODY>" +
					    "<h3>照括馬室推.壱梓還 </h3>" +
					     "<h4>[敗臆拝鯵] 拭辞 降勺廃 績獣 搾腔腰硲脊艦陥.</h4></br>"+
					     "<h5>績獣 搾腔腰硲 : "+ pwNo +"</h5>"+
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
			   message.setSubject("[敗臆拝鯵] 績獣搾腔腰硲 降勺馬心柔艦陥.");
			  
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
		
	}//end 戚五析 砺什闘

	//搾腔腰硲 績獣 幻級奄
	public int createNo(){
		
		Random random = new Random();
		
		int pwNo = random.nextInt();
		//箭企葵生稽 幻級奄
		Math.abs(pwNo);
		System.out.println("績獣搾腔腰硲pwNo溌昔"+pwNo);
		return pwNo;
	}

	//舌据戚 砺什闘
	//焼戚巨/搾腔腰硲 達奄拭辞 id 達奄
	@RequestMapping( value="json/test", method=RequestMethod.GET )
	public JSONObject  findUserId () throws Exception{
		
		System.out.println("薦戚充 砺什闘 ");
		//戚硯,持析 析帖拝依
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
		

	

	
