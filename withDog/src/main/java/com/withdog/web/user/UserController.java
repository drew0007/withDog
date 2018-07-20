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
	//�α��� ȭ�� GET  (�α��� Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="loginUser", method=RequestMethod.GET )
	public String loginUser ()  throws Exception {

		System.out.println("�α��� ȭ������ /user/loginUser : GET");
		
		return "forward:/user/loginUser.jsp";
	} 
	
	//�α��� POST
	@RequestMapping( value="loginUser", method=RequestMethod.POST )
	public String loginUser (@ModelAttribute("user") User user , HttpSession session, HttpServletResponse response)  throws Exception {

		System.out.println("�α��� /user/loginUser : POST");
		
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		System.out.println("���̵� üũ"+user.getUserId());
		if(dbUser==null){
			
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('���̵� Ȥ�� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.'); </script>");
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
	
	//�α׾ƿ� GET changeUserCon
	@RequestMapping( value="logoutUser", method=RequestMethod.GET )
	public String logoutUser(HttpSession session ) throws Exception{
		
		System.out.println("�α׾ƿ� /user/logout : GET");
		
		session.invalidate();
		
		return "forward:/user/loginUser.jsp";
	}
	
	//ȸ������ ȭ�� GET (ȸ������ Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception {

		System.out.println("ȸ������ �Է�â :: /user/addUser : GET");
		
		return "forward:/user/addUser.jsp";
	}
	
	//ȸ������ POST (ȸ������â���� �����ϱ� ������ ����)
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser(@ModelAttribute("user") User user, HttpSession session)
														throws Exception {

		System.out.println("ȸ������ :: /user/addUser : POST");

		//Business Logic
		userService.addUser(user);
		
		user = userService.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		return "forward:/user/getUser.jsp";
	}

	//ȸ������ ��ȸ GET  :: 
	/* ȸ������ �� ��������Ʈ�� serviceImpl���� ó�� ( �ٸ������� userService.getUser(userId) �θ��� ����Ʈ ����� ������)
	 * ���� ȸ�������� ���ǿ��� ���̵� Ȯ�� / �����ڴ� �ٹ������ ���� */
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser (HttpSession session, Model model,Point point)  throws Exception {

		System.out.println("ȸ������ ��ȸ :: /user/getUser : GET");
	
		User user =  (User) session.getAttribute("user");
		String userId  = user.getUserId();
		
		//Business Logic
		 user = userService.getUser(userId);

		 // Model �� View ����
		model.addAttribute("user", user);
		System.out.println("���������� ������ Ȯ��"+user);
		//myPageMain  :: �����ڵ� ::   8 ȸ���� , 88 ȸ����������, 9 ��й�ȣ ����, 10 ȸ��Ż��
		model.addAttribute("myPageState",8);
		
		return "forward:/mypage/myPageMain.jsp";
	}
	
	//ȸ������ ����ȭ�� GET  (�α��� Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser (HttpSession session, Model model, User user)  throws Exception {

		System.out.println("ȸ������ ����ȭ�� :: /user/updateUser : GET");
		
		//Business Logic
		user = (User)session.getAttribute("user");
		user  = userService.getUser(user.getUserId());
		
		 // Model �� View ����
		model.addAttribute("user", user);
		
		//myPageMain   :: �����ڵ� ::   8 ȸ���� , 88 ȸ����������, 9 ��й�ȣ ����, 10 ȸ��Ż��
		model.addAttribute("myPageState",88);
	
		return "forward:/mypage/myPageMain.jsp";
		}	
	
	//ȸ������ ����ȭ�� POST 
	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser (@ModelAttribute("user") User user, Model model,HttpServletResponse response)  throws Exception {

		System.out.println("ȸ������ ����ȭ�� :: /user/updateUser : POST");
		
		//Business Logic
		userService.updateUser(user);
		user = userService.getUser(user.getUserId());
		/*response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('���������� �Ϸ� �Ǿ����ϴ�'); </script>");
		out.flush();
		*/
		//myPageMain  �Ǵ� adminPageMain ������ ���� :: �����ڵ� ::   888 ȸ����������Ʈ,  8 ȸ����, 88 ȸ����������,  9 ��й�ȣ ����,  10 ȸ��Ż��
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
	
	//��й�ȣ ���� ȭ�� GET
	@RequestMapping( value="updatePassword", method=RequestMethod.GET )
	public String updatePassword (Model model)  throws Exception {

		System.out.println("��й�ȣ ���� ȭ������ /user/updatePassword : GET");
		
		//myPageMain :: �����ڵ� ::   8 ȸ���� , 88 ȸ����������, 9 ��й�ȣ ����, 10 ȸ��Ż��
		model.addAttribute("myPageState",9);
		
		return "forward:/mypage/myPageMain.jsp";
	} 
	
	//��й�ȣ ���� POST
	@RequestMapping( value="updatePassword", method=RequestMethod.POST )
	public String updatePassword (User user, HttpServletResponse response)  throws Exception {

		System.out.println("��й�ȣ ���� /user/updatePassword : POST");
		System.out.println("����Ȯ��"+user);
		
		//Business Logic
		userService.updateUser(user);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('��й�ȣ ���� �Ϸ� �Ǿ����ϴ�'); </script>");
		out.flush();

		return "forward:/mypage/myPageMain.jsp";
	} 
	
	///ȸ��Ż�� GET
	@RequestMapping( value="deleteUser", method=RequestMethod.GET )
	public String deleteUser(HttpServletRequest request,HttpSession session,User user,Point point,Model model) throws Exception{
		
		System.out.println("ȸ��Ż�� ȭ������ /user/deleteUser : GET");
		
		user =(User)session.getAttribute("user");
		
		//Business Logic
		user  = userService.getUser(user.getUserId());
		
		 // Model �� View ����
		model.addAttribute("user", user);
		
		//����������
		request.setAttribute("myPageState",10);
		
		return "forward:/mypage/myPageMain.jsp";
	}
	
	///ȸ��Ż�� POST
	@RequestMapping( value="deleteUser", method=RequestMethod.POST )
	public String deleteUser(User user, HttpSession session,HttpServletResponse response ) throws Exception{
		
		System.out.println("ȸ��Ż�� /user/deleteUser : POST");
			
		userService.deleteUser(user);
		session.invalidate();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('ȸ��Ż�� �Ϸ� �Ǿ����ϴ�'); </script>");
		out.flush();
		
		return "forward:/common/mainPage";
	}
	
	//ID ã�� GET (�α��� Ŭ������ �� �ܼ��׺���̼�)
		@RequestMapping( value="findUser", method=RequestMethod.GET )
		public String findUser() throws Exception{
			
			System.out.println("���̵�ã�� ȭ������ /user/findUser : GET");
			
			return "forward:/user/findUser.jsp";
		}
		
		//ȸ������ ����Ʈ Admin 
		@RequestMapping( value="getUserListAdmin" )
		public String getUserListAdmin(@ModelAttribute("search") Search search,HttpSession session,Model model) throws Exception{
			
			System.out.println("ȸ������ ����Ʈ���� /user/getUserListAdmin ");
			
			
			if(search.getCurrentPage() ==0 ){
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			// Business logic ����
			Map<String , Object> map=userService.getUserListAdmin(search);
			Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			// Model �� View ����
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			
			//amdinPageMain  :: ���� �ڵ� ::  888 ȸ������ ����Ʈ, 8 ȸ���� , 88 ȸ����������
			model.addAttribute("myPageState",888);
			
			return "forward:/mypage/adminPageMain.jsp";
		}
	
		//ȸ����������Ʈ �� ::  ���̵� �ٹ�� 
		@RequestMapping( value="getUserAdmin", method=RequestMethod.GET )
		public String getUserAdmin (@RequestParam("userId") String userId,Model model,User user)  throws Exception {

			System.out.println("ȸ������ ��ȸ :: /user/getUserAdmin : GET");
		
			System.out.println("Ȯ����"+userId);
			//Business Logic
			 user = userService.getUser(userId);
			 System.out.println("Ȯ����222222222"+user);
			 // Model �� View ����
			model.addAttribute("user", user);
			
			/*return "forward:/user/getUser.jsp";*/
			
			//amdinPageMain  :: ���� �ڵ� ::  888 ȸ������ ����Ʈ, 8 ȸ���� , 88 ȸ����������
			model.addAttribute("myPageState",8);
			
			return "forward:/mypage/adminPageMain.jsp";
		}
		
			//ȸ������ _���� :: ���̵� �ٹ��
			@RequestMapping( value="updateUserAdmin", method=RequestMethod.GET )
			public String updateUserAdmin (@RequestParam("userId") String userId,Model model,User user)  throws Exception {

				System.out.println("ȸ������ ��ȸ :: /user/updateUserAdmin : GET");
			
				System.out.println("Ȯ����"+userId);
				//Business Logic
				 user = userService.getUser(userId);
				 
				 // Model �� View ����
				model.addAttribute("user", user);
				
				//amdinPageMain  :: ���� �ڵ� ::  888 ȸ������ ����Ʈ, 8 ȸ���� , 88 ȸ����������
				model.addAttribute("myPageState",88);
				
				return "forward:/mypage/adminPageMain.jsp";
			
		}
		
		//�޸����� �������� �ܼ� �׺���̼�
			@RequestMapping( value="changeUserCon", method=RequestMethod.GET )
			public String changeUserCon( ) throws Exception{
				
				System.out.println("�޸����� �ܼ� �׺���̼�");
					
					return "forward:/user/changeUserCon.jsp";
			}	
		
			
			//ȸ����������Ʈ_���� :: �޸�ȸ�� ����:: 1 �� �α��� ���� ���� ��� �޸�ȸ������ (user_condition ='2')
			@RequestMapping( value="updateUserList", method=RequestMethod.GET )
			public String updateUserList( ) throws Exception{
				
				System.out.println("�޸鼳�� :: updateUserList");
				
				userService.updateUserList();
				
				return "forward:/user/getUserListAdmin";
			}	

		
		//����� �׽�Ʈ 2
		@RequestMapping( value="test", method=RequestMethod.GET )
		public String test() throws Exception{
			
			System.out.println("ddddd");
			
			return "forward:/user/test.jsp";
		}
		
		//html �ޱ�
		@RequestMapping( value="test2", method=RequestMethod.POST )
		public String  html (String testa, ModelMap model) throws Exception{
			
			System.out.println("html �׽�Ʈ >> "+testa);
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("testa", testa);
//			mav.setViewName("forward:/user/test3.jsp");
			model.addAttribute("testa", testa);

			return "forward:/user/test2.jsp";
		}

}//end of class
