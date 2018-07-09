//package com.withdog.service.user.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import com.withdog.service.domain.User;
//import com.withdog.service.user.UserDAO;
////
////@Repository("userDaoImpl")
//public class UserDaoImpl implements UserDAO {
//
////	@Autowired
////	@Qualifier("sqlSessionTemplate")
//	private SqlSession sqlSession;
//
//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
//
//	public UserDaoImpl() {
//		System.out.println(this.getClass());
//	}
//
//	@Override
//	public void addUser(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public User getUser(String userId) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateUser(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void updatePassword(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteUser(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public List<User> getUserListAdmin1() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int getTotalCount() throws Exception {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public User loginUser(User user) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void logoutUser(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public boolean duplicationUserId(String userId) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public User findUserId(User user) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void findUserPassword(User user) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public Map<String, Object> getUserListAdmin() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
