package com.withdog.web.abanddog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.service.domain.AbandDog;

@Controller
@RequestMapping("/abandDog/*")
public class AbanddogController {
	
	public AbanddogController() {
		System.out.println(this.getClass());
	}

}
