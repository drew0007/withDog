package com.withdog.service.afterash.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.afterash.AfterAshDao;
import com.withdog.service.afterash.AfterAshService;
import com.withdog.service.ash.AshDAO;
import com.withdog.service.domain.AfterAsh;

@Service("afterAshServiceImpl")
public class AfterAshServiceImpl implements AfterAshService {

	@Autowired
	@Qualifier("afterAshDaoImpl")
	private AfterAshDao afterAshDao;

	public AfterAshServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addAfterAsh(AfterAsh afterAsh) throws Exception {
		afterAshDao.addAfterAsh(afterAsh);

	}

	@Override
	public AfterAsh getAfterAsh(int afterAshNo) throws Exception {
		return afterAshDao.getAfterAsh(afterAshNo);
	}

	@Override
	public void updateAfterAsh(AfterAsh afterAsh) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AfterAsh> getAfterAshList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAfterAsh(AfterAsh afterAsh) throws Exception {
		// TODO Auto-generated method stub

	}

}
