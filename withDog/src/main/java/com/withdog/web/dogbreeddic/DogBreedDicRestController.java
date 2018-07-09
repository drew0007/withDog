package com.withdog.web.dogbreeddic;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(value = "json/getDogBreedKO")
	public JSONObject getDogBreedKO(@RequestBody DogBreedDic dogBreedDic  ) throws Exception{
		System.out.println("/dogBreedDic/json/getDogBreedKO");
		
		String dogBreedKO = dogBreedDicService.getDogBreedKO(dogBreedDic.getDogBreedEN());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("key", dogBreedKO);
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

}
