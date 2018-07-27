package com.withdog.web.user;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.withdog.service.user.impl.UserServiceImpl;

@Controller
@RequestMapping("/user/*")
public class UserController extends HttpServlet{
	
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
	
	public void InitBinder() throws ServletException{
		super.init();
	}
	
	///Method
	//�α��� ȭ�� GET  (�α��� Ŭ������ �� �ܼ��׺���̼�)
	@RequestMapping( value="loginUser", method=RequestMethod.GET )
	public String loginUser ()  throws Exception {

		System.out.println("�α��� ȭ������ /user/loginUser : GET");
		
		return "forward:/user/loginUser.jsp";
	} 
	
	/* ����Ʈ�� �ű�
	//�α��� POST
	@RequestMapping( value="loginUser", method=RequestMethod.POST )
	public String loginUser (@ModelAttribute("user") User user , HttpSession session, HttpServletResponse response)  throws Exception {
		ServletContext a = this.getServletContext();
		a.setAttribute("aa", "gdgd");
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
	*/
	
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
		System.out.println("��Ư�ѷ� Ȯ��"+user.getEmail());
		//Business Logic
		userService.addUser(user);
		
		user = userService.getUser(user.getUserId());
		session.setAttribute("user", user);
		
		return "forward:/mypage/getUser.jsp";
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
		System.out.println("���� ���� ��ȸ"+user);
		
		return "forward:/mypage/getUser.jsp";
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
	
		return "forward:/mypage/updateUser.jsp";
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

		return "forward:/mypage/getUser.jsp";
	}
	
	//��й�ȣ ���� ȭ�� GET
	@RequestMapping( value="updatePassword", method=RequestMethod.GET )
	public String updatePassword (Model model)  throws Exception {

		System.out.println("��й�ȣ ���� ȭ������ �ܼ��׺���̼� /user/updatePassword : GET");
		
		return "forward:/mypage/updatePassword.jsp";
	} 
	
	//��й�ȣ ���� POST
	@RequestMapping( value="updatePassword", method=RequestMethod.POST )
	public String updatePassword (User user, HttpServletResponse response)  throws Exception {

		System.out.println("��й�ȣ ���� /user/updatePassword : POST");
		System.out.println(" ������ ����Ȯ��"+user);
		
		//Business Logic
		userService.updateUser(user);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('��й�ȣ ���� �Ϸ� �Ǿ����ϴ�'); </script>");
		out.flush();

		return "forward:/mypage/getUser.jsp";
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
		
		return "forward:/mypage/deleteUser.jsp";
	}
	
	/*����Ʈ�� �ű�
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
	*/
	
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
			
			return "forward:/admin/listUserAdmin.jsp";
		}
	
		//ȸ����������Ʈ_�� ::  ���̵� �ٹ�� 
		@RequestMapping( value="getUserAdmin", method=RequestMethod.GET )
		public String getUserAdmin (@RequestParam("userId") String userId,Model model,User user)  throws Exception {

			System.out.println("ȸ������ ��ȸ :: /user/getUserAdmin : GET");
		
			System.out.println("���� ����Ʈ �� Ȯ����"+userId);
			
			//Business Logic
			 user = userService.getUser(userId);
			 
			 // Model �� View ����
			model.addAttribute("user", user);
			
			return "forward:/mypage/getUser.jsp";
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
				
				return "forward:/mypage/updateUser.jsp";
			
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

			//Sns ���� ȸ������ ȭ�� GET 
			@RequestMapping( value="addSnsUser", method=RequestMethod.GET )
			public String addSnsUser() throws Exception {

				System.out.println("ȸ������ �Է�â :: /user/addSnsUser : GET");
				
				return "forward:/user/addSnsUser.jsp";
			}
			
			

			
			
			
			
			
			
			
			
			
			
			
			
		///////īī�� �α���//////
			@RequestMapping(value = "/kakaoLogin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
			public String kakaoLogin(@RequestParam("code") String code , HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

				 JsonNode token = UserServiceImpl.getAccessToken(code);

				  JsonNode profile = UserServiceImpl.getKakaoUserInfo(token.path("access_token").toString());
				  System.out.println("īī������ ������"+profile);
				  User user = UserServiceImpl.changeData(profile);
				 // user.setUserId(userId);
				  //vo.setUser_snsId("ka"+vo.getUser_snsId());
				  
				  
				  ////////////////////////////////////////
				  String userId = "ka"+user.getUserId();
				  Boolean check = false; 
				  
				  System.out.println("�츮��񰡱��� userId"+userId);
				  
				  //����� userId�� �츮 DB�� ��ϵǾ��ִ��� Ȯ��
				  User dbUser =  userService.getUser(userId);
				  
				  System.out.println("�������"+dbUser);
				  
				  //db������ null�̶�� ������ �ȵ� ���� >> ȸ������â����
				  if(dbUser.getUserId()!=null) {

					  System.out.println("��� ���������� �̰��� Ÿ����");
					  session.setAttribute("user", dbUser);
					  check = true;
					  
				  }else {

					  //"ka"������ ���̵� ����
					  user.setUserId(userId);
					  session.setAttribute("user", user);
					  System.out.println("īī�� ���̵� ��� ������"+user);
				  }
				  
				  
				  session.setAttribute("user", user);
				  System.out.println("������ִ°�Ȯ��"+user.toString());

				 //user = service.kakaoLogin(vo);  
					return "forward:/user/addSnsUser.jsp";
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/////////////////////////////////////////////////////////
		
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
