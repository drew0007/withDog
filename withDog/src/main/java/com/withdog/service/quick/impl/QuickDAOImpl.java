package com.withdog.service.quick.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.service.quick.QuickDAO;

@Repository("quickDAOImpl")
public class QuickDAOImpl implements QuickDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
}