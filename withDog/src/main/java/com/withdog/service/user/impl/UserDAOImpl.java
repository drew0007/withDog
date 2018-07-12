package com.withdog.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.service.domain.User;
import com.withdog.service.user.UserDAO;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDAOImpl() {
		System.out.println(this.getClass());
	}

	///Method
	@Override
	public void addUser(User user) throws Exception {
		sqlSession.insert("UserMapper.addUser", user);
	}

	@Override
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	

	@Override
	public void updateUser(User user) throws Exception {
		sqlSession.update("UserMapper.updateUser", user);

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
	public List<User> getUserListAdmin1() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean duplicationUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findUserId(User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void findUserPassword(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getUserListAdmin() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecentlyDate(String userId) throws Exception {
		sqlSession.update("UserMapper.updateRecentlyDate", userId);
		
	}
}
