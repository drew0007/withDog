package com.withdog.web.dogbreeddic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.service.dogbreeddic.DogBreedDicService;

@Controller
@RequestMapping("/dogBreedDic/*")
public class DogBreedDicController {
	
	@Autowired
	@Qualifier("")
	private DogBreedDicService dogBreedDicService;
	
	public DogBreedDicController() {
		System.out.println(this.getClass());
	}
	
	

}
