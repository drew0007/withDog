package com.withdog.service.chat.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.chat.ChatService;
import com.withdog.service.domain.Chat;
import com.withdog.service.domain.Point;


@Service("chatServiceImpl")
public class ChatServiceImpl implements ChatService{
	
	
	
	@Autowired
	@Qualifier("chatDaoImpl")
	private Point point;

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
