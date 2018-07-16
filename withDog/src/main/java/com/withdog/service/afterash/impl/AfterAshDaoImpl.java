package com.withdog.service.afterash.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.withdog.common.Search;
import com.withdog.service.afterash.AfterAshDao;
import com.withdog.service.domain.AfterAsh;

@Repository("afterAshDaoImpl")
public class AfterAshDaoImpl implements AfterAshDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public AfterAshDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addAfterAsh(AfterAsh afterAsh) throws Exception {
		sqlSession.insert("AfterAshMapper.addAfterAsh",afterAsh);
	}

	@Override
	public AfterAsh getAfterAsh(int afterAshNo) throws Exception {
		return sqlSession.selectOne("AfterAshMapper.getAfterAsh",afterAshNo);
	}

	@Override
	public void updateAfterAsh(AfterAsh afterAsh) throws Exception {
		sqlSession.update("AfterAshMapper.updateAfterAsh",afterAsh);

	}

	@Override
	public List<AfterAsh> getAfterAshList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("AfterAshMapper.getAfterAshList", search);
	}

	@Override
	public void deleteAfterAsh(AfterAsh afterAsh) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("AfterAshMapper.getTotalCount", search);
	}

	@Override
	public void updateViewCount(AfterAsh afterAsh) throws Exception {
		sqlSession.update("AfterAshMapper.updateViewCount", afterAsh);
	}

	@Override
	public List<AfterAsh> getAfterAshListByViewCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("AfterAshMapper.getAfterAshListByViewCount");
	}

}
