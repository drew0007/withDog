package com.withdog.service.user;

import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.User;

public interface UserService {

	// ȸ������
	public void addUser(User user) throws Exception;

	// ������Ȯ��
	public User getUser(String userId) throws Exception;

	// ȸ����������
	public void updateUser(User user) throws Exception;

	// ��й�ȣ ����
	public void updatePassword(User user) throws Exception;

	// ȸ��Ż��
	public void deleteUser(User user) throws Exception;

	// �α��� 
	public User loginUser(User user) throws Exception;

	// �α׾ƿ�
	public void logoutUser(User user) throws Exception;

	// ȸ�� ID �ߺ� Ȯ��
	public boolean checkUserId(String userId) throws Exception;

	// ȸ�� ID ã��
	public User findUserId(User user) throws Exception;

	// ȸ�� ��й�ȣ ã��
	public void findUserPassword(User user) throws Exception;

	// ȸ����������Ʈ Admin
	public Map<String, Object> getUserListAdmin(Search search) throws Exception;
	
	//�α��ν� ������ ����
	public void updateRecentlyDate(String userId) throws Exception;
	
	//�޸� ���� �ڵ���, �̸� Ȯ��
	public User checkPhone(User user) throws Exception;
	
	//�޸� ȸ�� ���� Admin
	public void updateUserList() throws Exception;
	
	//�޸� ȸ�� ���� :: �޸� > ���� 
	public void updateUserCon(String userId) throws Exception;
}