package com.withdog.web.abanddog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.withdog.common.Search;
import com.withdog.service.domain.AbandDog;

@Controller
@RequestMapping("/abandDog/*")
public class AbanddogController {
	
	public AbanddogController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	

	@RequestMapping(value="getAbandDogList", method=RequestMethod.GET)
	public String getAbandDogList() throws Exception{ 
		
		return "forward:/abandDog/listAbandDog.jsp";
	}
	

	@RequestMapping(value="getAbandDog")
	public String getAbandDog(@ModelAttribute("abandDog") AbandDog abandDog) throws Exception{ 
		System.out.println("getAbandDog Ω√¿€");
		System.out.println("abandDog : "+abandDog);
		
		return "forward:/abandDog/getAbandDog.jsp";
	}

}
