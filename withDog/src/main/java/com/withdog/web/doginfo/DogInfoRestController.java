package com.withdog.web.doginfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.doginfo.DogInfoService;

@RestController
@RequestMapping("/dogInfo/*")
public class DogInfoRestController {
	
	@Autowired
	@Qualifier("dogInfoServiceImpl")
	private DogInfoService dogInfoService;
	
	public DogInfoRestController() {
		System.out.println(this.getClass());
	}

}
