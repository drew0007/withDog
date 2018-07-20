package com.withdog.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;
import com.withdog.service.user.UserDAO;
import com.withdog.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	///Field
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
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
		
			User user = userDAO.getUser(userId);
			
			 //포인트 조회;
			 Point point =new Point();
			 point.setUser(user);
			 int currentPoint= commonService.getCurrentPoint(point);
			 
			 user.setCurrentPoint(currentPoint);
			return  user;
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
		 userDAO.deleteUser(user);

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
	public Map<String, Object> getUserListAdmin(Search search) throws Exception {
		
		List<User> list= userDAO.getUserListAdmin(search);
		int totalCount = userDAO.getTotalCount(search);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public void updateRecentlyDate(String userId) throws Exception {
		userDAO.updateRecentlyDate(userId);
	}

	@Override
	public User checkPhone(User user) throws Exception {
		return userDAO.checkPhone(user);
		
	}

	@Override
	public void updateUserList() throws Exception {
		userDAO.updateUserList();
		
	}

	@Override
	public void updateUserCon(String userId) throws Exception {
		userDAO.updateUserCon(userId);
		
	}

}
