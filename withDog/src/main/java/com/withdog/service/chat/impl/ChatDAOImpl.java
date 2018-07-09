package com.withdog.service.chat.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.service.chat.ChatDAO;
import com.withdog.service.domain.Chat;

@Repository("chatDAOImpl")
public class ChatDAOImpl implements ChatDAO{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public int addChatRoom(Chat chat) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Chat getChat(int chatRoomNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateChat(Chat chat) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getChatRoomList(Chat chat) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFund() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	
		
}
