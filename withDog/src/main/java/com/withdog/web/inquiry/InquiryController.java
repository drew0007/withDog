package com.withdog.web.inquiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.service.inquiry.InquiryService;

@Controller
@RequestMapping("/inquiry/*")
public class InquiryController {
	
	@Autowired
	@Qualifier("")
	private InquiryService inquiryService;
	
	public InquiryController() {
		System.out.println(this.getClass());
	}

}
