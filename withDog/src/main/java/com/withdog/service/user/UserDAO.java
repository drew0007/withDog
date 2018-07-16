package com.withdog.service.user;

import java.util.List;
import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.User;

public interface UserDAO {
	
	//ȸ������ INSERT
	public void addUser(User user) throws Exception ;

	// ȸ������ ��ȸ SELECT ONE
	public User getUser(String userId) throws Exception ;

	// ȸ������ ���� UPDATE
	public void updateUser(User user) throws Exception ;
	
	// ��й�ȣ ���� UPDATE
	public void updatePassword(User user) throws Exception ;
	
	// ȸ�� Ż�� UPDATE
	public void deleteUser(User user) throws Exception ;

	
	// �α��� SELECT ONE
	public User loginUser(User user) throws Exception;
	
	// �α׾ƿ� 
	public void logoutUser(User user) throws Exception;
	
	// ȸ�� ID ã�� SELECT ONE
	public User findUserId(User user) throws Exception;
	
	// ȸ�� ��й�ȣ ã�� 
	public void findUserPassword(User user) throws Exception;
	
	// ȸ����������Ʈ Admin 
	public List<User> getUserListAdmin(Search search) throws Exception ;
	
	// �Խ��� Page ó���� ���� ��üRow(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
	
	// �����Ϻ���
	public void updateRecentlyDate(String userId) throws Exception;
	
	
}