package com.withdog.web.inquiry;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.withdog.common.Page;
import com.withdog.common.Search;
import com.withdog.service.domain.Inquiry;
import com.withdog.service.domain.Product;
import com.withdog.service.domain.User;
import com.withdog.service.inquiry.InquiryService;

@RestController
@RequestMapping("/inquiry/*")
public class InquiryRestController {
	
	@Autowired
	@Qualifier("inquiryServiceImpl")
	private InquiryService inquiryService;
	
	public InquiryRestController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping(value = "json/addInquiry/{prodNo}")
	public int addInquiry(@RequestBody Inquiry inquiry, @PathVariable int prodNo, HttpServletRequest request) throws Exception{
		System.out.println("/inquiry/json/addInquiry : POST");
		System.out.println("inquiry : "+inquiry);
		System.out.println("prodNo : "+prodNo);
		
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("user");
		
		Product product = new Product();
		product.setProdNo(prodNo);
		
		inquiry.setUser(user);
		inquiry.setProduct(product);

		System.out.println("문의정보 : "+inquiry);
		inquiryService.addInquiry(inquiry);
		
		return 1;
	}
	
	@RequestMapping(value = "json/getInquiryList/{prodNo}/{currentPage}")
	public JSONObject getInquiryList(@PathVariable int prodNo, @ModelAttribute("Search") Search search) throws Exception{
		System.out.println("/inquiry/json/getInquiryList : GET");
		System.out.println("문의리스트 대상 상품번호 : "+prodNo);
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = inquiryService.getInquiryList(search, prodNo);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", map.get("list"));
		jsonObject.put("resultPage", resultPage);
		
		System.out.println(jsonObject);

		return jsonObject;
	}
}
