package com.withdog.service.dogbreeddic;

import java.util.List;

import com.withdog.service.domain.DogBreedDic;

public interface DogBreedDicDao {
	
	public DogBreedDic getDogBreedInfo(int dogNo) throws Exception;

	public DogBreedDic getDogBreedKO(String dogBreedEN) throws Exception;
	
	public List<DogBreedDic> getDogBreedInfoList(String dogBreedKO) throws Exception;
	
	public int addDogBreedInfo(DogBreedDic dogBreedDic) throws Exception;
	
	public int updateDogBreedInfo(DogBreedDic dogBreedDic) throws Exception;
	
	public int deleteDogBreedInfo(int dogNo) throws Exception;
}