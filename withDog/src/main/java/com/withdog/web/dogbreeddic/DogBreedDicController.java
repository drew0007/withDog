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
	
	@RequestMapping(value = "getDogBreedInfo", method = RequestMethod.POST)
	public String getDogBreedInfo (@ModelAttribute("dogBreedDic") DogBreedDic dogBreedDic, HttpServletRequest request) throws Exception {
		System.out.println("/getDogBreedInfo");
		System.out.println(dogBreedDic);
		// Business Logic
		List<DogBreedDic> list = dogBreedDicService.getDogBreedInfoList(dogBreedDic.getDogBreedKO());
		// Model 과 View 연결
		DogBreedDic dogBreedDic2 = new DogBreedDic();
		System.out.println("리스트는 ? " + list);
		request.setAttribute("normalSearch", list.get(0));

		return "forward:/community/dogBreedDicSearch.jsp";
	}

}
