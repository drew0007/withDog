package com.withdog.service.quick.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.service.quick.QuickDAO;
import com.withdog.service.quick.QuickService;

@Service("quickServiceImpl")
public class QuickServiceImpl implements QuickService{
	
	@Autowired
	@Qualifier("quickDAOImpl")
	private QuickDAO quickDAO;
}