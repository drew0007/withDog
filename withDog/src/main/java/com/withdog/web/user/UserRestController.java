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

//==> 噺据淫軒 RestController
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
	public JSONObject loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("薦戚充 稽益昔 /user/loginUser : POST");
		System.out.println("user鎧遂 溌昔"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser鎧遂 溌昔"+dbUser);
		
		String userCondition = "4" ;
		
		if(dbUser!=null){
			
			//妃檎,  纏盗 食採 端滴 ::  珍巨芝 坪球 0 妃檎 1 舛雌 2 纏盗  4焼戚巨 搾腰 災析帖
			userCondition = dbUser.getUserCondition();
			if(userCondition.equals("0")) {
				userCondition ="0";
				//妃檎噺据析 井酔 焼戚巨葵幻 隔奄
				String userId = user.getUserId();
				session.setAttribute("userCon0", userId);

			}else if(userCondition.equals("2")){
				userCondition ="2";

			}else {
				
				//搾腔腰硲 析帖 食採 
				if( user.getPassword().equals(dbUser.getPassword())){
					session.setAttribute("user", dbUser);
					userService.updateRecentlyDate(dbUser.getUserId());
					userCondition ="1";
					
				}//end of if :: userPassword 端滴
				
				 //userCondition ="4"; 噺据珍巨芝 1昔汽 搾腰堂鍵 井酔
				
			}//end of if :: userCondition 端滴
			
		}//end of  if  :: dbUser null 端滴
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userCondition", userCondition);
		
		System.out.println("稽益昔 陥獣 左鎧奄穿拭"+jsonObject.toString());
		return jsonObject;

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
		pwNo= Math.abs(pwNo);
		
		String temp = pwNo+"";
		temp = temp.substring(3);
		
		pwNo =Integer.parseInt(temp);
		
		System.out.println("績獣搾腔腰硲pwNo溌昔"+pwNo);
		return pwNo;
	}


	
	
	//噺据淫軒軒什闘 娃繰左奄
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.POST )
	public User getUser( @PathVariable String userId,User user,Point point ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		
		//政煽 舛左 繕噺
		user = userService.getUser(userId);
		
		 //匂昔闘 繕噺;
		user.setUserId(userId);
		 point.setUser(user);
		
		 int userPoint= commonService.getCurrentPoint(point);
		 user.setCurrentPoint(userPoint);
	
		 
		return  user;
	}


	
	//妃檎 > 析鋼  背薦 :: 戚硯引 穿鉢腰硲稽 伊事 板 析帖馬檎 昔装腰硲 穿勺
	@RequestMapping( value="json/changeUserCon", method=RequestMethod.POST )
	public JSONObject changeUserCon(@RequestBody User user ) throws Exception{
		
		System.out.println("妃檎域舛 傾什闘 珍闘継君");
		JSONObject jsonObject = new JSONObject();
	
		//1. 戚硯引 穿鉢腰硲稽 伊事
		User dbUser = userService.checkPhone(user);
		
		//2. 妃企肉生稽 昔装腰硲 降勺
	
			if(dbUser!=null) {
				
				String userPhone = dbUser.getPhone();
				
				//2.1  昔装腰硲 沓棋腰硲稽 持失
				int  textNum = createNo();
				
				jsonObject.put("textNum", textNum);
				jsonObject.put("check", true);	
			
				/*
				//庚切稽 左鎧奄
			 	String api_key = "NCSKQ1ATROR9MMJC";
			    String api_secret = "HBXDI5ETRJMCRCDKAZHV1CWVF0L6DOKI";
			    //import  掻差生稽 鳶徹走誤猿走 
			    net.nurigo.java_sdk.api.Message coolsms = new net.nurigo.java_sdk.api.Message(api_key, api_secret);

			    // 4 params(to, from, type, text) are mandatory. must be filled
			    HashMap<String, String> params = new HashMap<String, String>();
			    params.put("to", userPhone); // 呪重腰硲
			    params.put("from", "01095885027"); // 降重腰硲
			    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
			    params.put("text", "[敗臆拝鯵] 沙昔溌昔 昔装腰硲["+textNum+"]研 鉢檎拭 脊径背爽室推"); // 庚切鎧遂    
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
			*/
			}
			System.out.println("昔装腰硲 降勺"+jsonObject.toJSONString());
			
			return jsonObject;
			
	}	

	
	//庚切稽 閤精 昔装腰硲 析帖 板 妃檎 > 舛雌生稽 穿発 /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserCondition", method=RequestMethod.POST )
	public void checkTextNum(@RequestParam("userId") String userId ) throws Exception{
		
		System.out.println("妃檎域舛聖 背薦");
	
		userService.updateUserCon(userId);

	}	
	
	//庚切稽 閤精 昔装腰硲 析帖 板 妃檎 > 舛雌生稽 穿発 /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserConditionTest", method=RequestMethod.POST )
	public void changeUserConditionTest(@PathVariable String userId ) throws Exception{
		
		System.out.println("妃檎域舛聖 背薦");
	
		userService.updateUserCon(userId);

	}	
	
	//亜脊獣 戚五析 昔装  :: 壱政腰硲 室芝 + 五析 穿勺
	@RequestMapping( value="json/checkEmail", method=RequestMethod.POST )
	public JSONObject checkEmail (@RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("亜脊獣 戚五析 昔装");
		
		//1.昔装腰硲 沓棋腰硲稽 持失
		int  textNum = createNo();
		
		//2. 昔装腰硲 室芝拭 隔奄
		session.setAttribute("textNum", textNum);
		
		//3. 戚五析 元滴 穿勺
		
		//4. 壱政腰硲 
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
		

	

	
