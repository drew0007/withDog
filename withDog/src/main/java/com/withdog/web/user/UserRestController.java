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
	public JSONObject loginUser (@RequestBody User user , HttpSession session, HttpServletResponse response, HttpServletRequest request)  throws Exception {
		
		System.out.println("���̽� �α��� /user/loginUser : POST");
		
		System.out.println("�Է���user���� Ȯ��"+user);
		
		//Business Logic
		
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("dbUser���� Ȯ��"+dbUser);
		
		//jsp���� Ȯ���ϴ� ��������� ��
		String userConCheck = "3" ;
		
		if(dbUser!=null){
			
			//�޸�,  Ż�� ���� üũ ::  ����� �ڵ� 0�޸� 1 ���� 2 Ż��  3���̵� ��� ����ġ
			String userCondition = dbUser.getUserCondition();
			
			if(userCondition.equals("0")) {
				
				//�޸�ȸ���� ��� ��������� �� 
				userConCheck ="0";
				
				//�޸�ȸ���� ��� ���ǿ� ���̵𰪸� �ֱ�
				String userId = user.getUserId();
				session.setAttribute("userCon0", userId);

			}else if(userCondition.equals("2")){
				
				//Ż��ȸ���ϰ�� ��������� ��
				userConCheck ="2";

			}else {
				
				//��й�ȣ ��ġ ���� 
				if( user.getPassword().equals(dbUser.getPassword())){
				
					//����ȸ���ϰ�� ��������� ��
					userConCheck ="1";
					
					//����ȸ���� ��� ���ǿ� ���� ������ �ֱ�
					session.setAttribute("user", dbUser);
					
					// ����ȸ���� ��� �ֱ������� ������Ʈ
					userService.updateRecentlyDate(dbUser.getUserId());
					
				}//end of if :: userPassword üũ
				
				 //userCondition ="4"; ȸ������� 1�ε� ���Ʋ�� ���
				
			}//end of if :: userCondition üũ
			
		}//end of  if  :: dbUser null üũ

		// �α��� ���� ������
		String prevPage = session.getAttribute("prevPage").toString();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userConCheck", userConCheck);
		jsonObject.put("prevPage", prevPage);
		
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
		//üũ���� true�̸� DB�� �������� ���°�
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
		String email = user.getEmail();
		
		user = userService.getUser(user.getUserId());
		String emilDB = user.getEmail();
		
		///���� �Է��� �̸��ϰ� DB�̸����� ���ٸ� 
		if(email.equals(emilDB)) {
			
			  //�ӽ� ��й�ȣ ����, ��� �ӽ� ��й�ȣ�� �ٲٱ�
			   int tempNo = createTempNo();
			   user.setPassword(tempNo+"");
			   
			   //��� �ٲٱ��� user üũ
			   System.out.println("����������������������� "+user);
			   
			   userService.updateUser(user);
			   User user2 = userService.getUser(user.getUserId());
			   
			   System.out.println("�ٲ� �н����� üũ"+user2);
			
			   //�̸��� ������ �ż��忡 ���� �̸��� �ּҿ� , �ӽ� ��й�ȣ ������
//			     String htmlText = " <h2>�ȳ��ϼ���</h2><img src=\\\"cid:my-image\\\">";
			   //String htmlText = " <h2>�ȳ��ϼ���</h2><img src=\"cid:my-image\"> ";
			   String url ="http://192.168.0.41:8080/user/loginUser";
			  String htmlText = " <h2>�ȳ��ϼ���</h2><img src=\"cid:my-image\"><h3>������ ��û����  <span style=\"color:red;\">�ӽú�й�ȣ</span>�� �߱޵Ǿ����ϴ�.<br> �ӽú�й�ȣ &nbsp;"+tempNo+" �Դϴ�. </h3>"
			  		+ "<br><h3>�α��� �� ��й�ȣ�� �������ּ���. </h3>"
			  		+ "<br><a href="+url+" target=\"_blank\"><img src=\"http://192.168.0.41:8080/images/icon/withdog.jpg\" /></a>";
	
			     String userEmail = emilDB;
			 boolean sendOk =userService.sendEmail(userEmail, htmlText);
			
			if(sendOk) {
			check=true;
			}
		}
		return check;
		
	}//end PWã��
	
		
	//��й�ȣ �ӽ� �����
	public int createTempNo(){
		
		Random random = new Random();
		
		int tempNo = random.nextInt();
		//���밪���� �����
		tempNo= Math.abs(tempNo);
		
		String temp = tempNo+"";
		temp = temp.substring(3);
		
		tempNo =Integer.parseInt(temp);
		
		System.out.println("�ӽú�й�ȣpwNoȮ��"+tempNo);
		return tempNo;
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
				
				//2.1  ������ȣ ������ȣ�� ���� �� ���� ���� �ۼ�
				int  tempNo = createTempNo();
				String conText = "[�Բ��Ұ�] ����Ȯ�� ������ȣ["+tempNo+"]�� ȭ�鿡 �Է����ּ���";
				
				//2.2 ���� ��ȭ��ȣ
				String userPhoneNo = dbUser.getPostNo();
				
				//2.3 ���ں����� �޼���
				userService.sendText(userPhoneNo, conText);
				
				jsonObject.put("tempNo", tempNo);
				jsonObject.put("check", true);	
				
		
				/*
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
				    
				    */
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
		int  textNum = createTempNo();
		
		//2. ������ȣ ���ǿ� �ֱ�
		session.setAttribute("textNum", textNum);
		
		//3. �̸��� ��ũ ����
		
		//4. ������ȣ 
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("textNum", textNum);
		
		System.out.println(jsonObject.toJSONString());
			
		return jsonObject;
	}	
	
	///ȸ��Ż�� POST
	@RequestMapping( value="json/deleteUser", method=RequestMethod.POST )
	public Boolean deleteUser(@RequestBody User user, HttpSession session ) throws Exception{
		
		System.out.println("ȸ��Ż�� /json/user/deleteUser : POST");

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
	
	
	//SNS�����Խ� �ش� ���̵� ���� üũ :: 0 īī�� 1 ���̹� 2 ���� 3 ���̽���
	@RequestMapping( value="json/checkSnsUserId", method=RequestMethod.POST)
	public Boolean checkUser(@RequestBody User user,HttpSession session) throws Exception{
		
		//�������̵� �̱�
		String userId = user.getUserId();
		
		//snsType 
		System.out.println("Ȯ��+++++++++++++++++++++"+user);
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType�� �µ��� �ش� snsId�� set
		user = setSnsId(snsType,user,userId);
		
		//true = ��� �����Ƿ� �α��� ������ false = sns ����ȸ������ �ؾ���
		Boolean check = false;
		//User dbuser = userService.getUser(userId);
		System.out.println("find������"+user);
		
		User dbUser = userService.findSnsId(user);
		
		System.out.println("db����Ȯ��"+dbUser);
		
		
		if(dbUser!=null) {
			
			System.out.println("������ ���� �ƴ�");
			// ��) k864371031 ���̵� �ִٸ� true�� ����, ���ǿ� ���� �ֱ�(�α��� ����) 
			session.setAttribute("user", dbUser);
			check= true;
			//�α��ν� ������ ������Ʈ
			userService.updateRecentlyDate(userId);
			
		}else {
			
			// ��) k864371031 ���̵� ���ٸ� false�� ����, ���ǿ� īī�� ID �ֱ�
			System.out.println("������ ���̴�");
			check= false;
			session.setAttribute("tempUser", user);
		}
		
		return check;
	}
	
	
	//Sns ���� ȸ������ ȭ�� POST 
	@RequestMapping( value="json/addSnsUser", method=RequestMethod.POST )
	public Boolean addSnsUser(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("ȸ������ �Է�â :: /user/json/addSnsUser : POST");
		
		System.out.println("ȸ���������� "+user);
		
		//userId
		String userId = user.getUserId();
		//snsType 
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType�� �µ��� �ش� snsId�� set
		user = setSnsId(snsType,user,userId);
		
		//Business Logic
		userService.addUser(user);
		
		user = userService.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		System.out.println("���ǿ� ���� ����"+user);
		return  true;
		
	}
	
	//SNS���� :: ���� DB��ȸ�ϰ� ���ٸ� sns�÷�������Ʈ ���ֱ�
	@RequestMapping( value="json/connectSnsId", method=RequestMethod.POST)
	public Boolean connectSnsId(@RequestBody User user,HttpSession session) throws Exception{
		
		System.out.println("sns ���� : POST");
		//�������̵� �̱�
		String userId = user.getUserId();
		
		//snsType 
		System.out.println("+"+user);
		int snsType= user.getSnsType();
		
		// ::userId >> sns~~Id  :: snsType�� �µ��� �ش� snsId�� set
		user = setSnsId(snsType,user,userId);
		
		//DB��ȸ �� �ش� ���̵� �ִٸ� �����Ұ� false / ���̵� ���ٸ� true  �ش� snsId�ξ�����Ʈ��
		Boolean check = false;
		
		//User dbuser = userService.getUser(userId);
		
		User dbUser = userService.findSnsId(user);
		
		
		if(dbUser!=null) {
			
			System.out.println("������ ���� �ƴ�");
			//�ش� ���̵�δ� ������ �ȵ�
			
		}else {
			
			//int snsType =Integer.parseInt(snsType2);
			user = setSnsId(snsType,user,userId);
			
			//���ǿ��� ���� userId�߰�
			User user2 = (User)session.getAttribute("user");
			String userId2 = user2.getUserId();
			user.setUserId(userId2);
			System.out.println("DB������"+user);
			userService.updateSnsId(user);
			
			check= true;
		
		}
		
		return check;
	}
	
	
	//snType�� ���� setSns~Id �Է� :: 0 īī�� 1 ���̹� 2 ���� 3 ���̽���
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
	
	//���̹� �α���
	@RequestMapping( value="json/loginWithNaver", method=RequestMethod.GET)
	public JSONObject loginWithNaver() throws Exception{ 
	    String clientId = "FCLaJ11V_c1179DGKDU1";//���ø����̼� Ŭ���̾�Ʈ ���̵�";
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
		

	

	
