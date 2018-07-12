package com.withdog.service.doginfo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.withdog.common.Search;
import com.withdog.service.dogbreeddic.DogBreedDicDAO;
import com.withdog.service.doginfo.DogInfoDAO;
import com.withdog.service.doginfo.DogInfoService;
import com.withdog.service.domain.DogInfo;

@Service("dogInfoServiceImpl")
public class DogInfoServiceImpl implements DogInfoService {

	@Autowired
	@Qualifier("dogInfoDAOImpl")
	private DogInfoDAO dogInfoDAO;

	public DogInfoServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addDogInfo(DogInfo dogInfo) throws Exception {
		dogInfoDAO.addDogInfo(dogInfo);
	}

	@Override
	public DogInfo getDogInfo(int dogInfoNo) throws Exception {
		return dogInfoDAO.getDogInfo(dogInfoNo);
	}

	@Override
	public void updateDogInfo(DogInfo dogInfo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String,Object> getDogInfoList(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String,Object>();
		List<DogInfo> list = dogInfoDAO.getDogInfoList(search);
		int totalCount = dogInfoDAO.getTotalCount();
		
		map.put("list", list);
		map.put("totalCount", totalCount);
		System.out.println("서비스 끝");
		
		return map;
	}

	@Override
	public void deleteDogInfo(int dogInfoNo) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRecommend(DogInfo dogInfo) throws Exception {
		// TODO Auto-generated method stub

	}
}
