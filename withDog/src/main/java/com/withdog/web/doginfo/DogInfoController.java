package com.withdog.web.doginfo;

import java.io.File;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.withdog.common.Page;
import com.withdog.common.Search;
import com.withdog.service.doginfo.DogInfoService;
import com.withdog.service.domain.DogInfo;
import com.withdog.service.domain.User;

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

	@Value("#{commonProperties['dogInfofilePath']}")
	String dogInfofilePath;
	

	@RequestMapping(value = "addDogInfo", method = RequestMethod.GET)
	public String addDogInfo() throws Exception {

		return "forward:/community/addDogInfo.jsp";
	}

	@RequestMapping(value = "addDogInfo", method = RequestMethod.POST)
	public String addDogInfo(@ModelAttribute("dogInfo") DogInfo dogInfo, @RequestParam("file") MultipartFile[] file, HttpSession session) throws Exception{
		System.out.println("/addDogInfo : POST");
		String a = "";
		
		if(file==null || file.equals("")) {
			System.out.println("���Ϻ����");
			dogInfo.setDogInfoImage("");
		}else {
			for (MultipartFile multipartFile : file) {
				if (multipartFile.getOriginalFilename().equals("") || multipartFile == null) {
					break;
				}
				a += multipartFile.getOriginalFilename() + (multipartFile.getOriginalFilename().equals("") ? "" : ",");
			
				System.out.println("����� ���ϵ� : " + multipartFile.getOriginalFilename());
				File f = new File(dogInfofilePath + multipartFile.getOriginalFilename().toString());
				System.out.println(dogInfofilePath);
				
				multipartFile.transferTo(f); // ���� ��ο� ���� ����
			}
			dogInfo.setDogInfoImage(a.substring(0, a.length()-1));
		}
		
	
		System.out.println(dogInfo);
		User user = (User)session.getAttribute("user");
		dogInfo.setUser(user);
		
		dogInfoService.addDogInfo(dogInfo);
		System.out.println("add �Ϸ�");
		
		return "forward:/community/getDogInfo.jsp";
	}
	
	@RequestMapping(value = "listDogInfo")
	public String getDogInfoList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request) throws Exception {
		System.out.println("/listDogInfo");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

//		if (request.getParameter("radio") != null) {
//			System.out.println("üũ�ڽ� ����? " + request.getParameter("radio"));
//			if (request.getParameter("radio").equals("1")) {
//				search.setPriceUpDown(1);
//			}
//			if (request.getParameter("radio").equals("2")) {
//				search.setPriceUpDown(2);
//			}
//			if (request.getParameter("radio").equals("3")) {
//				search.setRecently(1);
//			}
//			if (request.getParameter("radio").equals("4")) {
//				search.setRecently(2);
//			}
//		}

		Map<String, Object> map = dogInfoService.getDogInfoList(search);
		System.out.println("��Ȯ�� : " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		System.out.println("����Ʈ �ѱ���� ����Ʈ Ȯ�� : " + map.get("list"));

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search); // ��������
		
		
		return "forward:/community/listDogInfo.jsp";
	}
	
	@RequestMapping(value = "getDogInfo", method = RequestMethod.GET)
	public String getProduct(@RequestParam("dogInfoNo") int dogInfoNo, Model model, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		System.out.println("/getDogInfo.do");
		// Business Logic
		DogInfo dogInfo = dogInfoService.getDogInfo(dogInfoNo);

		model.addAttribute("dogInfo", dogInfo);
		System.out.println("get�ϱ��� ������Ȯ�� : " + dogInfo);

		return "forward:/community/getDogInfo.jsp";
	}
	
	@RequestMapping(value = "updateDogInfo", method = RequestMethod.GET)
	public String updateProductView(@RequestParam("dogInfoNo") int dogInfoNo, Model model) throws Exception {

		System.out.println("/updateDogInfoView.do");
		// Business Logic
		DogInfo dogInfo = dogInfoService.getDogInfo(dogInfoNo);
		// Model �� View ����
		model.addAttribute("dogInfo", dogInfo);

		return "forward:/community/updateDogInfo.jsp";
	}

//	@RequestMapping(value = "updateDogInfo", method = RequestMethod.POST)
//	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session)
//			throws Exception {
//
//		System.out.println("/updateProduct.do");
//		// Business Logic
//		productService.updateProduct(product);
//
//		return "redirect:/product/getProduct?prodNo=" + product.getProdNo() + "&menu=manage";
//	}

}
