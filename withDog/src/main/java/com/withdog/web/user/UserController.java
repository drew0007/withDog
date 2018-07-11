package com.withdog.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	///Page 
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	///Constructor
	public UserController(){
		System.out.println(this.getClass());
	}
	
	///Method
	//�α��� ȭ�� GET  (�α��� Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="loginUser", method=RequestMethod.GET )
	public String loginUser ()  throws Exception {

		System.out.println("�α��� ȭ������ /user/loginUser : GET");
		
		return "forward:/user/loginUser.jsp";
	} 
	
	//�α��� POST
	@RequestMapping( value="loginUser", method=RequestMethod.POST )
	public String loginUser (@ModelAttribute("user") User user , HttpSession session)  throws Exception {

		System.out.println("�α��� /user/loginUser : POST");
		
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
			
			userService.updateRecentlyDate(dbUser.getUserId());
		}
		
		return "redirect:/common/index.jsp";
	}
	
	///�α׾ƿ� GET
	@RequestMapping( value="logoutUser", method=RequestMethod.GET )
	public String logoutUser(HttpSession session ) throws Exception{
		
		System.out.println("�α׾ƿ� /user/logout : GET");
		
		session.invalidate();
		
		return "redirect:/common/index.jsp";
	}
	
	//ȸ������ ȭ�� GET (ȸ������ Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception {

		System.out.println("ȸ������ �Է�â :: /user/addUser : GET");
		
		return "redirect:/user/addUser.jsp";
	}
	
	//ȸ������ POST (ȸ������â���� �����ϱ� ������ ����)
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser (@ModelAttribute("user") User user)  throws Exception {

		System.out.println("ȸ������ :: /user/addUser : POST");
		
		//Business Logic
		userService.addUser(user);
		
		return "redirect:/user/getUser.jsp";
	}

	//ȸ������ ��ȸ GET 
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser (HttpSession session, Model model)  throws Exception {

		System.out.println("ȸ������ ��ȸ :: /user/getUser : GET");
	
		User user =  (User) session.getAttribute("user");
	
		String userId;
		
		if(user==null) {
			userId  ="aaa";
		}else {
			userId  = user.getUserId();
		}
		
		//Business Logic
		 user = userService.getUser(userId);
		// Model �� View ����
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}
	
	//ȸ������ ����ȭ�� GET  (�α��� Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="upateUser", method=RequestMethod.GET )
	public String updateUser (HttpSession session, Model model)  throws Exception {

		System.out.println("ȸ������ ����ȭ�� :: /user/updateUser : GET");
	
		return "forward:/user/updateUser.jsp";
	}
	
	

	//��й�ȣ ���� ȭ�� GET
	@RequestMapping( value="updatePassword", method=RequestMethod.GET )
	public String updatePassword ()  throws Exception {

		System.out.println("��й�ȣ ���� ȭ������ /user/updatePassword : GET");
		
		return "forward:/user/updatePassword.jsp";
	} 
	
	//��й�ȣ ���� POST
	@RequestMapping( value="updatePassword", method=RequestMethod.POST )
	public String updatePassword (User user)  throws Exception {

		System.out.println("��й�ȣ ���� /user/updatePassword : POST");
		
		//Business Logic
		
		return "forward:/mypage/myPageMain.jsp";
	} 
	
	///ȸ��Ż�� GET
	@RequestMapping( value="deleteUser", method=RequestMethod.GET )
	public String deleteUser() throws Exception{
		
		System.out.println("ȸ��Ż�� ȭ������ /user/deleteUser : GET");
		return "forward:/user/deleteUser.jsp";
	}
	
	///ȸ��Ż�� POST
	@RequestMapping( value="deleteUser", method=RequestMethod.POST )
	public String deleteUser(User user, HttpSession session ) throws Exception{
		
		System.out.println("ȸ��Ż�� /user/deleteUser : POST");
		System.out.println("User ���� Ȯ��"+user);
		System.out.println("User ���� Ȯ��"+user.getLeaveReason());
		return "redirect:/common/index.jsp";
	}
	

}//end of class
