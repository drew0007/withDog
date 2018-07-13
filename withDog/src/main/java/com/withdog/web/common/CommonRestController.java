package com.withdog.web.common;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.service.common.CommonService;

@RestController
@RequestMapping("/common/*")
public class CommonRestController {
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	public CommonRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pointpageSize']}")
	int pageSize;
	
	@Value("#{commonProperties['sommernoteFilePath']}")
	String path;
	
	@RequestMapping(value="json/imageUpload")
	public void  imageUpload(MultipartFile file,HttpServletResponse response) throws Exception{
		System.out.println("jsonimageStart");
		
		/*response.setContentType("text/html;charset=utf-8");*/
		PrintWriter out = response.getWriter();
		System.out.println(1);
		String fileName= file.getOriginalFilename();
		
		System.out.println(fileName);
		File fileSave = new File(path+fileName);
		file.transferTo(fileSave);
		
		System.out.println(2);
		response.setContentType("application/json");
		/*out.println("../images/sommernote/"+fileName);*/
		String imagePath ="../images/sommernote/"+fileName;
		System.out.println(3);
			
		JSONObject jobj = new JSONObject();
		jobj.put("url", imagePath);
		
		System.out.println(jobj);
		
		out.print(jobj.toJSONString());
		
		/*return jobj;*/
	
	}

}
