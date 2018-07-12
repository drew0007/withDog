package com.withdog.service.doginfo;

import java.util.List;
import java.util.Map;

import com.withdog.common.Search;
import com.withdog.service.domain.DogInfo;

public interface DogInfoService {
	public void addDogInfo(DogInfo dogInfo) throws Exception;

	public DogInfo getDogInfo(int dogInfoNo) throws Exception;

	public void updateDogInfo(DogInfo dogInfo) throws Exception;

	public Map<String,Object> getDogInfoList(Search search) throws Exception;

	public void deleteDogInfo(int dogInfoNo) throws Exception;

	public void updateRecommend(DogInfo dogInfo) throws Exception;
}
