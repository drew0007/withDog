package com.withdog.web.dogbreeddic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.DogBreedDic;

@Controller
@RequestMapping("/dogBreedDic/*")
public class DogBreedDicController {
	
	@Autowired
	@Qualifier("dogBreedDicServiceImpl")
	private DogBreedDicService dogBreedDicService;
	
	public DogBreedDicController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value = "getDogBreed", method = RequestMethod.GET)
	public String getDogBreed () throws Exception {
		System.out.println("/getDogBreed");
		return "forward:/community/dogBreedDicSearch.jsp";
	}

}
