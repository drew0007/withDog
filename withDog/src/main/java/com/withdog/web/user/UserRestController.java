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

//==> ȸ������ RestController
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
	
	//�̸��� html ���߿� ������ ������
	//@Value("#{commonProperties['emailHtml']}")
	//String emailHtml;
	
	///Constructor
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	
	///Method
	//�α��� POST
	@RequestMapping( value="json/loginUser", method=RequestMethod.POST )
	public JSONObject loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("���̽� �α��� /user/loginUser : POST");
		System.out.println("user���� Ȯ��"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser���� Ȯ��"+dbUser);
		
		String userCondition = "4" ;
		
		if(dbUser!=null){
			
			//�޸�,  Ż�� ���� üũ ::  ����� �ڵ� 0 �޸� 1 ���� 2 Ż��  4���̵� ��� ����ġ
			userCondition = dbUser.getUserCondition();
			if(userCondition.equals("0")) {
				userCondition ="0";
				//�޸�ȸ���� ��� ���̵𰪸� �ֱ�
				String userId = user.getUserId();
				session.setAttribute("userCon0", userId);

			}else if(userCondition.equals("2")){
				userCondition ="2";

			}else {
				
				//��й�ȣ ��ġ ���� 
				if( user.getPassword().equals(dbUser.getPassword())){
					session.setAttribute("user", dbUser);
					userService.updateRecentlyDate(dbUser.getUserId());
					userCondition ="1";
					
				}//end of if :: userPassword üũ
				
				 //userCondition ="4"; ȸ������� 1�ε� ���Ʋ�� ���
				
			}//end of if :: userCondition üũ
			
		}//end of  if  :: dbUser null üũ
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userCondition", userCondition);
		
		System.out.println("�α��� �ٽ� ����������"+jsonObject.toString());
		return jsonObject;

	}//end �α���
	
	
	
	//��й�ȣ ���� ���������� ���� ��й�ȣ Ȯ��
	@RequestMapping( value="json/checkPassword", method=RequestMethod.POST )
	public boolean checkPassword(	@RequestBody User user,
																						HttpSession session ) throws Exception{
	
		System.out.println("���̽����� /user/json/checkPassword");
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		boolean check = false;
		if( user.getPassword().equals(dbUser.getPassword())){
			check = true;
		}
		return check;
	}//end of checkPassword

	//ID �ߺ�Ȯ��
	//ȸ�����Խ� id �ߺ�üũ
	@RequestMapping( value="json/checkUserId/{userId}", method=RequestMethod.GET )
	public boolean duplicationUser(@PathVariable String userId) throws Exception{
		
		System.out.println(userId);
		
		boolean check = false;
		if(!userId.equals("")) {
			check = userService.checkUserId(userId);
		}
		
		return check;
	}
	
	//ID ã��
	//���̵�/��й�ȣ ã�⿡�� id ã��
	@RequestMapping( value="json/findUserId", method=RequestMethod.POST )
	public User  findUserId (@RequestBody User user) throws Exception{
		
		System.out.println("���̵� ã�� ���̽� ");
		//�̸�,���� ��ġ�Ұ�
		user = userService.findUserId(user);
		if(user==null) {
			user = new User();
		}
		return user;
	}

	//PW ã��
	//���̵�/��й�ȣ ã�⿡�� PWã��
	@RequestMapping( value="json/findUserPW", method=RequestMethod.POST )
	public boolean  findUserPW (@RequestBody User user) throws Exception{
		
		System.out.println("��� ã�� ���̽� ");
		boolean check = false;
		
		//ID�� ���� ��ġ�Ұ�
		//email : ����ڰ� �Է��� �̸��� �ּ� emilDB : �Է��� ���̵�� ��� �ٳ�� �̸����ּ�
		String email = user.getEmail1()+user.getEmail2();
		
		user = userService.getUser(user.getUserId());
		String emilDB = user.getEmail1()+user.getEmail2();
		
		///���� �Է��� �̸��ϰ� DB�̸����� ���ٸ� 
		if(email.equals(emilDB)) {
			
			  //�ӽ� ��й�ȣ ����, ��� �ӽ� ��й�ȣ�� �ٲٱ�
			   int pwNo = createNo();
			   user.setPassword(pwNo+"");
			   //��� �ٲٱ��� user üũ
			   System.out.println("����������������������� "+user);
			   userService.updateUser(user);
			   System.out.println("�ٲ� �н����� üũ");
			//�̸��� ������ �ż��忡 ���� �̸��� �ּҿ� , �ӽ� ��й�ȣ ������
			boolean sendOk = sendEmail(emilDB,pwNo);
			
			if(sendOk) {
			check=true;
			}
		}
		return check;
		
	}//end PWã��
	
	//�̸��� ������ 1. �ڹ� ���� lib �߰� 2. ������ ��� ���� imap��� 
	//@RequestMapping( value="json/emailTest", method=RequestMethod.GET )
	public boolean sendEmail(String emilDB,int pwNo) throws Exception{
		
			  String host     = "smtp.naver.com";
			  String user   = "hyuny0126@naver.com"; //������ ��� �̸��� �ּ�
			  String password  = "gogosing2014"; //������ ��� ��й�ȣ

			  String to     =  emilDB; //�޴»�� �̸��� �ּ�
			  
			  //���� html
			  String emailHtml = "<HTML>" +
					    "<HEAD><TITLE></TITLE></HEAD>" +
					    "<BODY>" +
					    "<h3>�ȳ��ϼ���.���� </h3>" +
					     "<h4>[�Բ��Ұ�] ���� �߼��� �ӽ� ��й�ȣ�Դϴ�.</h4></br>"+
					     "<h5>�ӽ� ��й�ȣ : "+ pwNo +"</h5>"+
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
			   message.setSubject("[�Բ��Ұ�] �ӽú�й�ȣ �߼��Ͽ����ϴ�.");
			  
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
		
	}//end �̸��� �׽�Ʈ

	//��й�ȣ �ӽ� �����
	public int createNo(){
		
		Random random = new Random();
		
		int pwNo = random.nextInt();
		//���밪���� �����
		pwNo= Math.abs(pwNo);
		
		String temp = pwNo+"";
		temp = temp.substring(3);
		
		pwNo =Integer.parseInt(temp);
		
		System.out.println("�ӽú�й�ȣpwNoȮ��"+pwNo);
		return pwNo;
	}


	
	
	//ȸ����������Ʈ ��������
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.POST )
	public User getUser( @PathVariable String userId,User user,Point point ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//���� ���� ��ȸ
		user = userService.getUser(userId);
		
		 //����Ʈ ��ȸ;
		user.setUserId(userId);
		 point.setUser(user);
		
		 int userPoint= commonService.getCurrentPoint(point);
		 user.setCurrentPoint(userPoint);
	
		return  user;
	}

	
	//�޸� > �Ϲ�  ���� :: �̸��� ��ȭ��ȣ�� �˻� �� ��ġ�ϸ� ������ȣ ����
	@RequestMapping( value="json/changeUserCon", method=RequestMethod.POST )
	public JSONObject changeUserCon(@RequestBody User user ) throws Exception{
		
		System.out.println("�޸���� ����Ʈ ��Ʈ�ѷ�");
		JSONObject jsonObject = new JSONObject();
	
		//1. �̸��� ��ȭ��ȣ�� �˻�
		User dbUser = userService.checkPhone(user);
		
		//2. �޴������� ������ȣ �߼�
	
			if(dbUser!=null) {
				
				String userPhone = dbUser.getPhone();
				
				//2.1  ������ȣ ������ȣ�� ����
				int  textNum = createNo();
				
				jsonObject.put("textNum", textNum);
				jsonObject.put("check", true);	
			
				//���ڷ� ������
			 	String api_key = "NCSKQ1ATROR9MMJC";
			    String api_secret = "HBXDI5ETRJMCRCDKAZHV1CWVF0L6DOKI";
			    //import  �ߺ����� ��Ű������� 
			    net.nurigo.java_sdk.api.Message coolsms = new net.nurigo.java_sdk.api.Message(api_key, api_secret);

			    // 4 params(to, from, type, text) are mandatory. must be filled
			    HashMap<String, String> params = new HashMap<String, String>();
			    params.put("to", userPhone); // ���Ź�ȣ
			    params.put("from", "01095885027"); // �߽Ź�ȣ
			    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
			    params.put("text", "[�Բ��Ұ�] ����Ȯ�� ������ȣ["+textNum+"]�� ȭ�鿡 �Է����ּ���"); // ���ڳ���    
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
			System.out.println("������ȣ �߼�"+jsonObject.toJSONString());
			return jsonObject;
			
	}	

	
	//���ڷ� ���� ������ȣ ��ġ �� �޸� > �������� ��ȯ /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserCondition", method=RequestMethod.POST )
	public boolean checkTextNum(@RequestParam("userId") String userId ) throws Exception{
		
		System.out.println("�޸������ ����");
	  
		userService.updateUserCon(userId);
		boolean check= true;
		return check;
	}	
	
	//���ڷ� ���� ������ȣ ��ġ �� �޸� > �������� ��ȯ /user/json/changeUserCondition
	@RequestMapping( value="json/changeUserConditionTest/{userId}", method=RequestMethod.GET )
	public boolean changeUserConditionTest(@PathVariable String userId ) throws Exception{
		
		System.out.println("�޸������ ����");
	
		userService.updateUserCon(userId);
		boolean check= true;
		return check;
	}	
	
	//���Խ� �̸��� ����  :: ������ȣ ���� + ���� ����
	@RequestMapping( value="json/checkEmail", method=RequestMethod.POST )
	public JSONObject checkEmail (@RequestBody User user , HttpSession session) throws Exception{
		
		System.out.println("���Խ� �̸��� ����");
		
		//1.������ȣ ������ȣ�� ����
		int  textNum = createNo();
		
		//2. ������ȣ ���ǿ� �ֱ�
		session.setAttribute("textNum", textNum);
		
		//3. �̸��� ��ũ ����
		
		//4. ������ȣ 
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
	//����� �׽�Ʈ
	//���̵�/��й�ȣ ã�⿡�� id ã��
	@RequestMapping( value="json/test", method=RequestMethod.GET )
	public JSONObject  findUserId () throws Exception{
		
		System.out.println("���̽� �׽�Ʈ ");
		//�̸�,���� ��ġ�Ұ�
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
		

	

	
