package com.withdog.web.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("dogBreedDicServiceImpl")
	private DogBreedDicService dogBreedDicService;
	
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
	//로그인 화면 GET  (로그인 클릭했을 때 단순네비게이션)
	@RequestMapping( value="loginUser", method=RequestMethod.GET )
	public String loginUser ()  throws Exception {

		System.out.println("로그인 화면으로 /user/loginUser : GET");
		
		return "forward:/user/loginUser.jsp";
	} 
	
	//로그인 POST
	@RequestMapping( value="loginUser", method=RequestMethod.POST )
	public String loginUser (@ModelAttribute("user") User user , HttpSession session)  throws Exception {

		System.out.println("로그인 /user/loginUser : POST");
		
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());

		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
			userService.updateRecentlyDate(dbUser.getUserId());
		}
		
		return "redirect:/mypage/myPageMain.jsp";
	}
	
	///로그아웃 GET
	@RequestMapping( value="logoutUser", method=RequestMethod.GET )
	public String logoutUser(HttpSession session ) throws Exception{
		
		System.out.println("로그아웃 /user/logout : GET");
		
		session.invalidate();
		
		return "redirect:/common/index.jsp";
	}
	
	//회원가입 화면 GET (회원가입 클릭했을 때 단순네비게이션)
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception {

		System.out.println("회원가입 입력창 :: /user/addUser : GET");
		
		return "redirect:/user/addUser.jsp";
	}
	
	//회원가입 POST (회원가입창에서 가입하기 눌러서 전송)
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser(@ModelAttribute("user") User user, HttpSession session)
														throws Exception {

		System.out.println("회원가입 :: /user/addUser : POST");

		//Business Logic
		
		userService.addUser(user);
		
		user = userService.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}

	//회원정보 조회 GET 
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser (HttpSession session, Model model)  throws Exception {

		System.out.println("회원정보 조회 :: /user/getUser : GET");
	
		User user =  (User) session.getAttribute("user");
		String userId  = user.getUserId();
		
		
		//Business Logic
		 user = userService.getUser(userId);
		 
		 // Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}
	
	//회원정보 수정화면 GET  (로그인 클릭했을 때 단순네비게이션)
	@RequestMapping( value="upateUser", method=RequestMethod.GET )
	public String updateUser (HttpSession session, Model model)  throws Exception {

		System.out.println("회원정보 수정화면 :: /user/updateUser : GET");
	
		return "forward:/user/updateUser.jsp";
	}
	

	//비밀번호 수정 화면 GET
	@RequestMapping( value="updatePassword", method=RequestMethod.GET )
	public String updatePassword ()  throws Exception {

		System.out.println("비밀번호 수정 화면으로 /user/updatePassword : GET");
		
		return "forward:/user/updatePassword.jsp";
	} 
	
	//비밀번호 수정 POST
	@RequestMapping( value="updatePassword", method=RequestMethod.POST )
	public String updatePassword (User user, HttpServletResponse response)  throws Exception {

		System.out.println("비밀번호 수정 /user/updatePassword : POST");
		System.out.println("유저확인"+user);
		
		//Business Logic
		userService.updateUser(user);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('비밀번호 변경 완료 되었습니다'); </script>");
		out.flush();

		return "forward:/mypage/myPageMain.jsp";
	} 
	
	///회원탈퇴 GET
	@RequestMapping( value="deleteUser", method=RequestMethod.GET )
	public String deleteUser() throws Exception{
		
		System.out.println("회원탈퇴 화면으로 /user/deleteUser : GET");
		return "forward:/user/deleteUser.jsp";
	}
	
	///회원탈퇴 POST
	@RequestMapping( value="deleteUser", method=RequestMethod.POST )
	public String deleteUser(User user, HttpSession session ) throws Exception{
		
		System.out.println("회원탈퇴 /user/deleteUser : POST");
		System.out.println("User 정보 확인"+user);
		System.out.println("User 정보 확인"+user.getLeaveReason());
		return "redirect:/common/index.jsp";
	}
	

}//end of class
