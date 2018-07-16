package com.withdog.web.quick;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.service.quick.QuickService;

@Controller
@RequestMapping("/quick/*")
public class QuickController {
	
	@Autowired
	@Qualifier("quickServiceImpl")
	private QuickService quickService;
	
	public QuickController() {
		System.out.println(this.getClass());
	}
	@RequestMapping(value="ARMap")
	public String ARNavigation()throws Exception {
		
			
		return "forward:/quick/ARMap.jsp";
	}
	
	@RequestMapping(value="ARGeo")
	public String ARGeo(HttpServletRequest request)throws Exception {
		
			System.out.println("/////////////////////////////ARGeo Start");
			if(request.toString()!=null) {
			System.out.println(request.toString());
			System.out.println(request.getParameter("lat"));
			System.out.println(request.getParameter("lng"));
			
			
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			
			
			
			request.setAttribute("lat", lat);
			request.setAttribute("lng", lng);
			
			}
			
			
		return "forward:/quick/GoogleMapGeo.jsp";
	}
	
	
	@RequestMapping(value="ARNavigation")
	public String ARNavigation(HttpServletRequest request)throws Exception {
		
			System.out.println("/////////////////////////////ARNavigation Start");
			if(request.toString()!=null) {
			System.out.println(request.toString());
			System.out.println(request.getParameter("lat"));
			System.out.println(request.getParameter("lng"));
			System.out.println(request.getParameter("type"));
			
			String lat = request.getParameter("lat");
			String lng = request.getParameter("lng");
			String type = request.getParameter("type");
			
			
			request.setAttribute("lat", lat);
			request.setAttribute("lng", lng);
			request.setAttribute("type", type);
			}
			
			
		return "forward:/quick/GoogleMapSearch.jsp";
	}

}
