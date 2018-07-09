//package com.withdog.service.user;
//
//import java.util.List;
//import java.util.Map;
//
//import com.withdog.service.domain.User;
//
//public interface UserDAO {
//	
//	//회원가입 INSERT
//	public void addUser(User user) throws Exception ;
//
//	// 회원정보 조회 SELECT ONE
//	public User getUser(String userId) throws Exception ;
//
//	// 회원정보 수정 UPDATE
//	public void updateUser(User user) throws Exception ;
//	
//	// 비밀번호 수정 UPDATE
//	public void updatePassword(User user) throws Exception ;
//	
//	// 회원 탈퇴 UPDATE
//	public void deleteUser(User user) throws Exception ;
//	
//	// 회원관리 리스트 Admin SELECT LIST
//	public List<User> getUserListAdmin1() throws Exception ;
//	
//	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
//	public int getTotalCount() throws Exception ;
//	
//	// 로그인 SELECT ONE
//	public User loginUser(User user) throws Exception;
//	
//	// 로그아웃 
//	public void logoutUser(User user) throws Exception;
//	
//	// 회원 ID 중복 확인  SELECT 
//	public boolean duplicationUserId(String userId) throws Exception;
//	
//	// 회원 ID 찾기 SELECT ONE
//	public User findUserId(User user) throws Exception;
//	
//	// 회원 비밀번호 찾기 
//		public void findUserPassword(User user) throws Exception;
//	
//	// 회원정보리스트 Admin 
//	public Map<String , Object> getUserListAdmin() throws Exception;
//	
//}