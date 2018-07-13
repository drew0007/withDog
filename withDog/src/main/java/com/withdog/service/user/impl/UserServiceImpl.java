package com.withdog.service.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.domain.User;
import com.withdog.service.user.UserDAO;
import com.withdog.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	///Field
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	@Override
	public void addUser(User user) throws Exception {
		userDAO.addUser(user);
	}

	@Override
	public User getUser(String userId) throws Exception {
			return userDAO.getUser(userId);
	}
	

	@Override
	public void updateUser(User user) throws Exception {
		 userDAO.updateUser(user);
	}

	@Override
	public void updatePassword(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public User loginUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logoutUser(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkUserId(String userId) throws Exception {
		
			boolean result=true;
			User user=userDAO.getUser(userId);
			
			if(user != null) {
				result=false;
			}
			return result;
	}

	@Override
	public User findUserId(User user) throws Exception {
		return 	userDAO.findUserId(user);
	}

	@Override
	public void findUserPassword(User user) throws Exception {
		 

	}

	@Override
	public Map<String, Object> getUserListAdmin() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecentlyDate(String userId) throws Exception {
		userDAO.updateRecentlyDate(userId);
	}

}
