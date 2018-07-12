package com.withdog.web.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.withdog.common.Page;
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
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pointpageSize']}")
	int pageSize;
	
	
	
	@RequestMapping(value="/mainPage")
	public String getMainPage() throws Exception {
		
		return "forward:/common/index.jsp";
	}
	
	@RequestMapping(value="/myPageMain")
	public String getMyPageMain(HttpServletRequest request,HttpSession session) throws Exception {
		
		String uri = "";
		int myPageState;
		User user = (User)session.getAttribute("user");

		if(request.getParameter("myPageState")==null ) {
			uri="/common/getMyPointList";
		}
		/*else {
			myPageState=Integer.parseInt(request.getParameter("myPageState"));
			
			switch(myPageState) {
				case 1 :
					uri="";
				break;	
				case 2 :
					uri="";
				break;
				case 3 :
					uri="";
				break;
				case 4 :
					uri="";
				break;
				case 5 :
					uri="";
				break;	
				case 6 :
					uri="";
				break;
				case 7 :
					uri="";
				break;
				case 8 :
					uri="";
				break;
				case 9 :
					uri="";
				break;
				case 10 :
					uri="";
				break;
				case 11 :
					uri="/common/getMyPointList";
				break;
			
			}
		}*/
		
		
		
		
		return "forward:"+uri;
	}
	
	
	@RequestMapping(value="/getMyPointList")
	public String getMyPointList(@ModelAttribute("search") Search search,HttpServletRequest request,HttpSession session)throws Exception{
		
		System.out.println("MyPointList : Start");
		
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}		
		search.setPageSize(pageSize);
		
		Point point = new Point();
		User user = (User)session.getAttribute("user");
		
		point.setUser(user);
		
		int currentPoint = commonService.getCurrentPoint(point);
		Map<String,Object> map = commonService.getMyPointList(search,user.getUserId());
		List<Point> list = (List<Point>)map.get("list");
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
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("currentPoint", currentPoint);
		request.setAttribute("resultList", resultList);
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		request.setAttribute("myPageState", "11");
		
		
		return "forward:/mypage/myPageMain.jsp";
	}

}
