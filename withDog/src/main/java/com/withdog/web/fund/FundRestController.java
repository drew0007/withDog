package com.withdog.web.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.service.fund.FundService;

@RestController
@RequestMapping("/fund/*")
public class FundRestController {
	
	@Autowired
	@Qualifier("")
	private FundService fundService;

	public FundRestController() {
		System.out.println(this.getClass());
	}

}
