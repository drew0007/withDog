package com.withdog.web.afterash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.service.afterash.AfterAshService;

@Controller
@RequestMapping("/afterAsh/*")
public class AfterAshController {
	
	@Autowired
	@Qualifier("")
	private AfterAshService afterAshService;
	
	public AfterAshController() {
		System.out.println(this.getClass());
	}

}
