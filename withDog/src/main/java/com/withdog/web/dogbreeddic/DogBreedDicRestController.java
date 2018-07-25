package com.withdog.web.dogbreeddic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.DogBreedDic;

@RestController
@RequestMapping("/dogBreedDic/*")
public class DogBreedDicRestController {
	
	@Autowired
	@Qualifier("dogBreedDicServiceImpl")
	private DogBreedDicService dogBreedDicService;
	
	public DogBreedDicRestController(){
		System.out.println(this.getClass());
		
	}
	@RequestMapping(value = "json/getDogBreed")
	public JSONObject getDogBreed(@RequestBody DogBreedDic dogBreedDic,HttpSession session) throws Exception{
		
		System.out.println("/dogBreedDic/json/getDogBreed");
		System.out.println("확인중"+dogBreedDic);
		DogBreedDic dogBreed = dogBreedDicService.getDogBreed(dogBreedDic.getDogBreedEN());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", dogBreed);
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getDogBreed2") //소현누나꺼
	public JSONObject getDogBreed2(@RequestBody DogBreedDic dogBreedDic  ) throws Exception{
		
		System.out.println("/dogBreedDic/json/getDogBreed");
		System.out.println("확인중"+dogBreedDic);
		DogBreedDic dogBreed = dogBreedDicService.getDogBreedInfo(dogBreedDic.getDogNo());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", dogBreed);
		System.out.println(jsonObject.toJSONString());
		
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getDogBreedInfoList")
	public JSONObject getDogBreedInfoList(@RequestBody DogBreedDic dogBreedDic) throws Exception{
		System.out.println("/dogBreedDic/json/getDogBreedInfo");
		List<DogBreedDic> dogBreedInfo = dogBreedDicService.getDogBreedInfoList(dogBreedDic.getDogBreedKO());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("dogBreedInfo", dogBreedInfo);
		System.out.println(jsonObject.toJSONString());
		return jsonObject;
	}
	@RequestMapping(value = "json/getAllBreedInfoList")
	public JSONObject getAllDogBreedInfoList() throws Exception{
		System.out.println("/dogBreedDic/json/getAllDogBreedInfo");
		List<DogBreedDic> allDogBreedInfo = dogBreedDicService.getAllDogBreedInfoList();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDogBreedInfo", allDogBreedInfo);
		return jsonObject;
	}
	
	@RequestMapping(value = "json/getAllBreedInfoListByKo")
	public JSONObject getAllDogBreedInfoListByKo() throws Exception{
		System.out.println("/dogBreedDic/json/getAllDogBreedInfo");
		List<DogBreedDic> allDogBreedInfo = dogBreedDicService.getAllDogBreedInfoListByKo();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("allDogBreedInfo", allDogBreedInfo);
		return jsonObject;
	}

}
