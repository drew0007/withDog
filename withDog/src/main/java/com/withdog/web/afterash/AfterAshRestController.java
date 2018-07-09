package com.withdog.web.afterash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.afterash.AfterAshService;

@RestController
@RequestMapping("/afterAsh/*")
public class AfterAshRestController {
	
	@Autowired
	@Qualifier("")
	private AfterAshService afterAshService;
	
	public AfterAshRestController() {
		System.out.println(this.getClass());
	}

}
