package com.withdog.web.dogbreeddic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.dogbreeddic.DogBreedDicService;

@RestController
@RequestMapping("/dogBreedDic/*")
public class DogBreedDicRestController {
	
	@Autowired
	@Qualifier("")
	private DogBreedDicService dogBreedDicService;
	
	public DogBreedDicRestController(){
		System.out.println(this.getClass());
		
	}

}
