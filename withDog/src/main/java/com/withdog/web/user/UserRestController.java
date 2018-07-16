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



//==> ȸ������ RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
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
	public boolean loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("���̽� �α��� /user/loginUser : POST");
		System.out.println("user���� Ȯ��"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser���� Ȯ��"+dbUser);
		
		boolean check = false;
		if(dbUser!=null){
			
			if( user.getPassword().equals(dbUser.getPassword())){
				session.setAttribute("user", dbUser);
				userService.updateRecentlyDate(dbUser.getUserId());
				check= true;
			}
			
		}
		System.out.println("check Ȯ��"+check);
		return check;

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
		Math.abs(pwNo);
		System.out.println("�ӽú�й�ȣpwNoȮ��"+pwNo);
		return pwNo;
	}

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
		

	

	
