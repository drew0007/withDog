package com.withdog.web.abanddog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dogBreedDic/*")
public class AbanddogRestController {
	
	public AbanddogRestController() {
		System.out.println(this.getClass());
	}

}
