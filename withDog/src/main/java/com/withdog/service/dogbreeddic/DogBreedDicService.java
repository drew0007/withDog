package com.withdog.service.dogbreeddic;

import java.util.List;

import com.withdog.service.domain.DogBreedDic;

public interface DogBreedDicService {
	
	public DogBreedDic getDogBreedInfo(int dogNo) throws Exception;

	public List<DogBreedDic> getDogBreedInfoList(String dogBreedEN) throws Exception;
	
	public int addDogBreedInfo(DogBreedDic dogBreedDic) throws Exception;
	
	public int updateDogBreedInfo(DogBreedDic dogBreedDic) throws Exception;
	
	public int deleteDogBreedInfo(int dogNo) throws Exception;
}