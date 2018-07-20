package com.withdog.service.user;

import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.User;

public interface UserService {

	// 회원가입
	public void addUser(User user) throws Exception;

	// 내정보확인
	public User getUser(String userId) throws Exception;

	// 회원정보수정
	public void updateUser(User user) throws Exception;

	// 비밀번호 수정
	public void updatePassword(User user) throws Exception;

	// 회원탈퇴
	public void deleteUser(User user) throws Exception;

	// 로그인 
	public User loginUser(User user) throws Exception;

	// 로그아웃
	public void logoutUser(User user) throws Exception;

	// 회원 ID 중복 확인
	public boolean checkUserId(String userId) throws Exception;

	// 회원 ID 찾기
	public User findUserId(User user) throws Exception;

	// 회원 비밀번호 찾기
	public void findUserPassword(User user) throws Exception;

	// 회원정보리스트 Admin
	public Map<String, Object> getUserListAdmin(Search search) throws Exception;
	
	//로그인시 접속일 변경
	public void updateRecentlyDate(String userId) throws Exception;
	
	//휴면 계정 핸드폰, 이름 확인
	public User checkPhone(User user) throws Exception;
	
	//휴면 회원 설정 Admin
	public void updateUserList() throws Exception;
	
	//휴면 회원 해제 :: 휴면 > 정상 
	public void updateUserCon(String userId) throws Exception;
}