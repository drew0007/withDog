package com.withdog.web.doginfo;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.service.doginfo.DogInfoService;
import com.withdog.service.domain.DogInfo;

@Controller
@RequestMapping("/dogInfo/*")
public class DogInfoController {

	@Autowired
	@Qualifier("dogInfoServiceImpl")
	private DogInfoService dogInfoService;

	public DogInfoController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@Value("#{commonProperties['filePath']}")
	String dogInfofilePath;
	

	@RequestMapping(value = "addDogInfo", method = RequestMethod.GET)
	public String addDogInfo() throws Exception {

		return "forward:/community/addDogInfo.jsp";
	}

	@RequestMapping(value = "addDogInfo", method = RequestMethod.POST)
	public String addDogInfo(@ModelAttribute("dogInfo") DogInfo dogInfo, @RequestParam("file") MultipartFile[] file) throws Exception{
		System.out.println("/addDogInfo : POST");
		String a = "";
		
//		for (MultipartFile multipartFile : file) {
//			if (multipartFile.getOriginalFilename().equals("") || multipartFile == null) {
//				break;
//			}
//			a += multipartFile.getOriginalFilename() + (multipartFile.getOriginalFilename().equals("") ? "" : ",");
//			
//		
//			System.out.println("저장된 파일들 : " + multipartFile.getOriginalFilename());
//			File f = new File(dogInfofilePath + multipartFile.getOriginalFilename().toString());
//			System.out.println(dogInfofilePath);
//			System.out.println(pageSize);
//			System.out.println(pageUnit);
//			System.out.println(f.isDirectory());
//			System.out.println(f.toString());
//			System.out.println(f.exists());
//			System.out.println(f.mkdirs());
//			
//			multipartFile.transferTo(f); // 위의 경로에 파일 저장
//		}
//		dogInfo.setDogInfoImage(a.substring(0, a.length()-1));
//		System.out.println(dogInfo);
		
		dogInfoService.addDogInfo(dogInfo);
		
		return "forward:/community/getDogInfo.jsp";
	}

}
