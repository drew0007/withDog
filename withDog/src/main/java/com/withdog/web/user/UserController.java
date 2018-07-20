package com.withdog.web.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

import com.withdog.common.Page;
import com.withdog.common.Search;
import com.withdog.service.common.CommonService;
import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.Point;
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
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	
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
	public String loginUser (@ModelAttribute("user") User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("로그인 /user/loginUser : POST");
		
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("아이디 체크"+user.getUserId());
		if(dbUser==null){
			
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('아이디 혹은 비밀번호를 잘못 입력하셨습니다.'); </script>");
			out.flush();
			return "/user/loginUser.jsp";
			
		}else {
			
			if( user.getPassword().equals(dbUser.getPassword())){
				session.setAttribute("user", dbUser);
				userService.updateRecentlyDate(dbUser.getUserId());
			}
			
		}
		
		return "forward:/common/index.jsp";

	}
	
	//로그아웃 GET changeUserCon
	@RequestMapping( value="logoutUser", method=RequestMethod.GET )
	public String logoutUser(HttpSession session ) throws Exception{
		
		System.out.println("로그아웃 /user/logout : GET");
		
		session.invalidate();
		
		return "forward:/user/loginUser.jsp";
	}
	
	//회원가입 화면 GET (회원가입 클릭했을 때 단순네비게이션)
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception {

		System.out.println("회원가입 입력창 :: /user/addUser : GET");
		
		return "forward:/user/addUser.jsp";
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

	//회원정보 조회 GET  :: 
	/* 회원정보 중 현재포인트는 serviceImpl에서 처리 ( 다른곳에서 userService.getUser(userId) 부르면 포인트 담겨져 가도록)
	 * 유저 회원정보시 세션에서 아이디 확인 / 관리자는 겟방식으로 받음 */
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser (HttpSession session, Model model,Point point)  throws Exception {

		System.out.println("회원정보 조회 :: /user/getUser : GET");
	
		User user =  (User) session.getAttribute("user");
		String userId  = user.getUserId();
		
		//Business Logic
		 user = userService.getUser(userId);

		 // Model 과 View 연결
		model.addAttribute("user", user);
		System.out.println("마이페이지 가기전 확인"+user);
		//myPageMain  :: 연결코드 ::   8 회원상세 , 88 회원정보수정, 9 비밀번호 수정, 10 회원탈퇴
		model.addAttribute("myPageState",8);
		
		return "forward:/mypage/myPageMain.jsp";
	}
	
	//회원정보 수정화면 GET  (로그인 클릭했을 때 단순네비게이션)
	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser (HttpSession session, Model model, User user)  throws Exception {

		System.out.println("회원정보 수정화면 :: /user/updateUser : GET");
		
		//Business Logic
		user = (User)session.getAttribute("user");
		user  = userService.getUser(user.getUserId());
		
		 // Model 과 View 연결
		model.addAttribute("user", user);
		
		//myPageMain   :: 연결코드 ::   8 회원상세 , 88 회원정보수정, 9 비밀번호 수정, 10 회원탈퇴
		model.addAttribute("myPageState",88);
	
		return "forward:/mypage/myPageMain.jsp";
		}	
	
	//회원정보 수정화면 POST 
	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser (@ModelAttribute("user") User user, Model model,HttpServletResponse response)  throws Exception {

		System.out.println("회원정보 수정화면 :: /user/updateUser : POST");
		
		//Business Logic
		userService.updateUser(user);
		user = userService.getUser(user.getUserId());
		/*response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('정보수정이 완료 되었습니다'); </script>");
		out.flush();
		*/
		//myPageMain  또는 adminPageMain 페이지 연결 :: 연결코드 ::   888 회원관리리스트,  8 회원상세, 88 회원정보수정,  9 비밀번호 수정,  10 회원탈퇴
		String resultJsp ;
		String role = user.getRole();
		
		if(role.equals("admin")) {
			model.addAttribute("myPageState",8);
			model.addAttribute("user");
			resultJsp ="forward:/mypage/adminPageMain.jsp";
		}else {
			model.addAttribute("myPageState",8);
			model.addAttribute("user");
			resultJsp ="forward:/mypage/myPageMain.jsp";
		}
		return resultJsp;
	}
	
	//비밀번호 수정 화면 GET
	@RequestMapping( value="updatePassword", method=RequestMethod.GET )
	public String updatePassword (Model model)  throws Exception {

		System.out.println("비밀번호 수정 화면으로 /user/updatePassword : GET");
		
		//myPageMain :: 연결코드 ::   8 회원상세 , 88 회원정보수정, 9 비밀번호 수정, 10 회원탈퇴
		model.addAttribute("myPageState",9);
		
		return "forward:/mypage/myPageMain.jsp";
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
	public String deleteUser(HttpServletRequest request,HttpSession session,User user,Point point,Model model) throws Exception{
		
		System.out.println("회원탈퇴 화면으로 /user/deleteUser : GET");
		
		user =(User)session.getAttribute("user");
		
		//Business Logic
		user  = userService.getUser(user.getUserId());
		
		 // Model 과 View 연결
		model.addAttribute("user", user);
		
		//마이페이지
		request.setAttribute("myPageState",10);
		
		return "forward:/mypage/myPageMain.jsp";
	}
	
	///회원탈퇴 POST
	@RequestMapping( value="deleteUser", method=RequestMethod.POST )
	public String deleteUser(User user, HttpSession session,HttpServletResponse response ) throws Exception{
		
		System.out.println("회원탈퇴 /user/deleteUser : POST");
			
		userService.deleteUser(user);
		session.invalidate();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원탈퇴가 완료 되었습니다'); </script>");
		out.flush();
		
		return "forward:/common/mainPage";
	}
	
	//ID 찾기 GET (로그인 클릭했을 때 단순네비게이션)
		@RequestMapping( value="findUser", method=RequestMethod.GET )
		public String findUser() throws Exception{
			
			System.out.println("아이디찾기 화면으로 /user/findUser : GET");
			
			return "forward:/user/findUser.jsp";
		}
		
		//회원관리 리스트 Admin 
		@RequestMapping( value="getUserListAdmin" )
		public String getUserListAdmin(@ModelAttribute("search") Search search,HttpSession session,Model model) throws Exception{
			
			System.out.println("회원관리 리스트으로 /user/getUserListAdmin ");
			
			
			if(search.getCurrentPage() ==0 ){
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			// Business logic 수행
			Map<String , Object> map=userService.getUserListAdmin(search);
			Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			// Model 과 View 연결
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			
			//amdinPageMain  :: 연결 코드 ::  888 회원관리 리스트, 8 회원상세 , 88 회원정보수정
			model.addAttribute("myPageState",888);
			
			return "forward:/mypage/adminPageMain.jsp";
		}
	
		//회원관리리스트 상세 ::  아이디 겟방식 
		@RequestMapping( value="getUserAdmin", method=RequestMethod.GET )
		public String getUserAdmin (@RequestParam("userId") String userId,Model model,User user)  throws Exception {

			System.out.println("회원정보 조회 :: /user/getUserAdmin : GET");
		
			System.out.println("확인중"+userId);
			//Business Logic
			 user = userService.getUser(userId);
			 System.out.println("확인중222222222"+user);
			 // Model 과 View 연결
			model.addAttribute("user", user);
			
			/*return "forward:/user/getUser.jsp";*/
			
			//amdinPageMain  :: 연결 코드 ::  888 회원관리 리스트, 8 회원상세 , 88 회원정보수정
			model.addAttribute("myPageState",8);
			
			return "forward:/mypage/adminPageMain.jsp";
		}
		
			//회원수정 _어드민 :: 아이디 겟방식
			@RequestMapping( value="updateUserAdmin", method=RequestMethod.GET )
			public String updateUserAdmin (@RequestParam("userId") String userId,Model model,User user)  throws Exception {

				System.out.println("회원정보 조회 :: /user/updateUserAdmin : GET");
			
				System.out.println("확인중"+userId);
				//Business Logic
				 user = userService.getUser(userId);
				 
				 // Model 과 View 연결
				model.addAttribute("user", user);
				
				//amdinPageMain  :: 연결 코드 ::  888 회원관리 리스트, 8 회원상세 , 88 회원정보수정
				model.addAttribute("myPageState",88);
				
				return "forward:/mypage/adminPageMain.jsp";
			
		}
		
		//휴면해제 페이지로 단순 네비게이션
			@RequestMapping( value="changeUserCon", method=RequestMethod.GET )
			public String changeUserCon( ) throws Exception{
				
				System.out.println("휴면해제 단순 네비게이션");
					
					return "forward:/user/changeUserCon.jsp";
			}	
		
			
			//회원관리리스트_어드민 :: 휴면회원 설정:: 1 년 로그인 하지 않은 경우 휴면회원으로 (user_condition ='2')
			@RequestMapping( value="updateUserList", method=RequestMethod.GET )
			public String updateUserList( ) throws Exception{
				
				System.out.println("휴면설정 :: updateUserList");
				
				userService.updateUserList();
				
				return "forward:/user/getUserListAdmin";
			}	

		
		//장원이 테스트 2
		@RequestMapping( value="test", method=RequestMethod.GET )
		public String test() throws Exception{
			
			System.out.println("ddddd");
			
			return "forward:/user/test.jsp";
		}
		
		//html 받기
		@RequestMapping( value="test2", method=RequestMethod.POST )
		public String  html (String testa, ModelMap model) throws Exception{
			
			System.out.println("html 테스트 >> "+testa);
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("testa", testa);
//			mav.setViewName("forward:/user/test3.jsp");
			model.addAttribute("testa", testa);

			return "forward:/user/test2.jsp";
		}

}//end of class
