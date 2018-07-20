package com.withdog.service.user.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
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
		System.out.println("업데이트 하기전에"+user);
		sqlSession.update("UserMapper.updateUser", user);

	}

	@Override
	public void updatePassword(User user) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) throws Exception {
		sqlSession.update("UserMapper.deleteUser", user);

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
	public User findUserId(User user) throws Exception {
		return sqlSession.selectOne("UserMapper.findUserId", user);
	}

	@Override
	public void findUserPassword(User user) throws Exception {
		// TODO Auto-generated method stub

	}



	@Override
	public void updateRecentlyDate(String userId) throws Exception {
		sqlSession.update("UserMapper.updateRecentlyDate", userId);
		
	}

	//회원관리리스트 어드민
	@Override
	public List<User> getUserListAdmin(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.getUserList", search);
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
	}

	@Override
	public User checkPhone(User user) throws Exception {
		return sqlSession.selectOne("UserMapper.checkPhone", user);
	}

	@Override
	public void updateUserList() throws Exception {
		sqlSession.update("UserMapper.updateUserList");
	}

	@Override
	public void updateUserCon(String userId) throws Exception {
		sqlSession.update("UserMapper.updateUserCon",userId);
	}
	
}//end of class
