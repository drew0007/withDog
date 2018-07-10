package com.withdog.web.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.domain.User;
import com.withdog.service.user.UserService;



//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	///Constructor
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	///Method
//비밀번호 변경 페이지에서 기존 비밀번호 확인
	@RequestMapping( value="json/checkPassword/", method=RequestMethod.POST )
	public boolean login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("제이슨으로 /user/json/checkPassword");
		System.out.println("user 확인"+user);
		//Business Logic
		User dbUser=userService.getUser(user.getUserId());
		boolean check = false;
		if( user.getPassword().equals(dbUser.getPassword())){
			check = true;
		}
		System.out.println("체크확인"+check);
		return check;
	}

}
		

	

	
