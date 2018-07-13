package com.withdog.web.afterash;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.service.afterash.AfterAshService;
import com.withdog.service.domain.AfterAsh;
import com.withdog.service.domain.User;

@Controller
@RequestMapping("/afterAsh/*")
public class AfterAshController {
	
	@Autowired
	@Qualifier("afterAshServiceImpl")
	private AfterAshService afterAshService;
	
	public AfterAshController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@Value("#{commonProperties['dogInfofilePath']}")
	String dogInfofilePath;
	
	@RequestMapping(value = "addAfterAsh", method = RequestMethod.GET)
	public String addAfterAsh() throws Exception {
		System.out.println("/addAfterAsh : GET");

		return "forward:/community/addAfterASH.jsp";
	}

	@RequestMapping(value = "addAfterAsh", method = RequestMethod.POST)
	public String addAfterAsh(@ModelAttribute("afterAsh") AfterAsh afterAsh,@RequestParam("file") MultipartFile[] file, HttpSession session) throws Exception{
		System.out.println("/addAfterAsh : POST");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		String a = "";
		
		if(file[0].getSize()==0) {
			System.out.println("파일비었음");
			afterAsh.setAfterASHImage("");
		}else {
			for (MultipartFile multipartFile : file) {
				if (multipartFile.getOriginalFilename().equals("") || multipartFile == null) {
					break;
				}
				a += (dateFormat.format(date)+multipartFile.getOriginalFilename()) + (multipartFile.getOriginalFilename().equals("") ? "" : ",");
			
				System.out.println("저장된 파일들 : " + multipartFile.getOriginalFilename());
				File f = new File(dogInfofilePath + (dateFormat.format(date)+multipartFile.getOriginalFilename()).toString());
				System.out.println(dogInfofilePath);
				
				multipartFile.transferTo(f); // 위의 경로에 파일 저장
			}
			afterAsh.setAfterASHImage(a.substring(0, a.length()-1));
		}
		
	
		System.out.println(afterAsh);
		User user = (User)session.getAttribute("user");
		afterAsh.setUser(user);
		System.out.println("세션유져 : " + user);
		
		afterAshService.addAfterAsh(afterAsh);
		System.out.println("add 완료");
		
		
		return "forward:/community/addAfterASHView.jsp";
	}

}
