package com.withdog.web.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.common.Search;
import com.withdog.service.common.CommonService;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;

@Controller
@RequestMapping("/common/*")
public class CommonController {
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;
	
	public CommonController() {
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value="/getMyPointList")
	public String getMyPointList(HttpServletRequest request)throws Exception{
		
		System.out.println("MyPointList : Start");
		Search search = new Search();
		String userId = "user01";
		
		Point point = new Point();
		User user = new User();
		user.setUserId(userId);
		point.setUser(user);
		
		int currentPoint = commonService.getCurrentPoint(point);
		List<Point> list = commonService.getMyPointList(search,userId);
		
		List<Point> resultList = new ArrayList<Point>(); 
		for (int i = 0; i <list.size(); i++) {
			System.out.println(list.get(i).toString());
			point=list.get(i);
			if(point.getFund().getFundMyPrice()!=0) {
				point.setPointHistory(0);
			}
			else if(point.getAsh().getAshReservationNo()!=0) {
				point.setPointHistory(1);
			}
			else if(point.getPurchase().getPurchaseNo()!=0) {
				point.setPointHistory(2);
			}
			
			resultList.add(point);
		}
		
		request.setAttribute("currentPoint", currentPoint);
		request.setAttribute("resultList", resultList);
		
		
		return "forward:/mypage/listMyPoint.jsp";
	}

}
