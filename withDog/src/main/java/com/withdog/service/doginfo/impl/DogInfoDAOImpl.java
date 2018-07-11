package com.withdog.service.doginfo.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
import com.withdog.service.doginfo.DogInfoDAO;
import com.withdog.service.domain.DogInfo;

@Repository("dogInfoDAOImpl")
public class DogInfoDAOImpl implements DogInfoDAO {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public DogInfoDAOImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addDogInfo(DogInfo dogInfo) throws Exception {
		sqlSession.insert("DogInfoMapper.addDogInfo",dogInfo);
	}

	@Override
	public DogInfo getDogInfo(int dogInfoNo) throws Exception {
		return sqlSession.selectOne("DogInfoMapper.getDogInfo",dogInfoNo);
	}

	@Override
	public void updateDogInfo(DogInfo dogInfo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DogInfo> getDogInfoList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("DogInfoMapper.getDogInfoList",search);
	}

	@Override
	public void deleteDogInfo(int dogInfoNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRecommend(DogInfo dogInfo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRecommendInfo(int dogInfoNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTotalCount() throws Exception {
		return sqlSession.selectOne("DogInfoMapper.getTotalCount");
	}
}
