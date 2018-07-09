package com.withdog.service.doginfo;

import java.util.List;

import com.withdog.common.Search;
import com.withdog.service.domain.DogInfo;

public interface DogInfoDAO {
	public void addDogInfo(DogInfo dogInfo) throws Exception;

	public DogInfo getDogInfo(int dogInfoNo) throws Exception;

	public void updateDogInfo(DogInfo dogInfo) throws Exception;

	public List<DogInfo> getDogInfoList(Search search) throws Exception;

	public void deleteDogInfo(int dogInfoNo) throws Exception;

	public void updateRecommend(DogInfo dogInfo) throws Exception;

	public void updateRecommendInfo(int dogInfoNo) throws Exception;

}
