package com.withdog.web.ash;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.withdog.service.ash.AshService;
import com.withdog.service.common.CommonService;
import com.withdog.service.dogbreeddic.DogBreedDicService;
import com.withdog.service.domain.AfterAsh;
import com.withdog.service.domain.Ash;
import com.withdog.service.domain.HealingDog;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;

@Controller
@RequestMapping("/ash/*")
public class AshController {

	@Autowired
	@Qualifier("ashServiceImpl")
	private AshService ashService;
	
	@Autowired
	@Qualifier("dogBreedDicServiceImpl")
	private DogBreedDicService dogBreedDicService;
	
	@Autowired
	@Qualifier("commonServiceImpl")
	private CommonService commonService;

	public AshController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['healingDogPageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['healingDogPageSize']}")
	int pageSize;

	@Value("#{commonProperties['healingDogfilePath']}")
	String healingDogfilePath;
	
	@RequestMapping(value = "addHealingDog", method= RequestMethod.GET)
	public String addHealingDog(Model model) throws Exception {
		System.out.println("/addHealingDog : GET");
		model.addAttribute("dog", dogBreedDicService.getAllDogBreedInfoList());
		return "forward:/animal/addHealingDog.jsp";
	}
	
	@RequestMapping(value = "addHealingDog", method= RequestMethod.POST)
	public String addHealingDog(@ModelAttribute("healingDog") HealingDog healingDog, @RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("/addHealingDog : POST");
		System.out.println("입력받은 도그 출력 : " + healingDog);
		System.out.println();
		String fileName ="";
		
		if(file.getSize()==0) {
			System.out.println("파일비었음");
			healingDog.setHealingDogimage("");
		}else {
			System.out.println("저장된 파일 : " + file.getOriginalFilename());
			File f = new File(healingDogfilePath + (file.getOriginalFilename()));
			fileName = file.getOriginalFilename();
			file.transferTo(f);
			healingDog.setHealingDogimage(fileName);
		}
//		Thread.sleep(3000);
		
		ashService.addHealingDog(healingDog);
		System.out.println("add완료");
		
		return "forward:/animal/addHealingDogView.jsp";
	}
	
	@RequestMapping(value = "listHealingDog")
	public String getHealingDogList(@ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception {
		System.out.println("/listHealingDog");
		
		System.out.println("서치 출력 ; " + search);

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = ashService.getHealingDogList(search);
		System.out.println("맵확인 : " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		System.out.println("리스트 넘기기전 리스트 확인 : " + map.get("list"));
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search); // 생략가능
		
		return "forward:/animal/listHealingDog.jsp";
	}
	
	@RequestMapping(value = "getHealingDog", method = RequestMethod.GET)
	public String getHealingDog(@RequestParam("healingDogNo") int healingDogNo, Model model) throws Exception {
		System.out.println("/getHealingDog : GET");
		// Business Logic
		HealingDog healingDog =ashService.getHealingDog(healingDogNo);
		model.addAttribute("healingDog", healingDog);
		System.out.println("get하기전 도메인확인 : " + healingDog);

		return "forward:/animal/getHealingDog.jsp";
	}
	
	@RequestMapping(value = "updateHealingDog", method = RequestMethod.GET)
	public String updateHealingDog(@RequestParam("healingDogNo") int healingDogNo, Model model) throws Exception {
		System.out.println("/updateHealingDog : GET");
		// Business Logic
		HealingDog healingDog =ashService.getHealingDog(healingDogNo);
		model.addAttribute("dog", dogBreedDicService.getAllDogBreedInfoList());
		model.addAttribute("healingDog", healingDog);
		System.out.println("get하기전 도메인확인 : " + healingDog);
		
		return "forward:/animal/updateHealingDog.jsp";
	}
	
	@RequestMapping(value = "updateHealingDog", method= RequestMethod.POST)
	public String updateHealingDog(@ModelAttribute("healingDog") HealingDog healingDog, @RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("/updateHealingDog : POST");
		System.out.println("입력받은 도그 출력 : " + healingDog);
		System.out.println();
		String fileName ="";
		
		if(file.getSize()==0) {
			System.out.println("업데이트 시 이미지 수정 안함");
			healingDog.setHealingDogimage(ashService.getHealingDog(healingDog.getHealingDogNo()).getHealingDogimage());
		}else {
			System.out.println("업데이트 시 이미지 수정 함");
			File f = new File(healingDogfilePath + (file.getOriginalFilename()));
			file.transferTo(f);
			fileName = file.getOriginalFilename();
			healingDog.setHealingDogimage(fileName);
		}
		System.out.println("업데이트 하기 전 도그 : " + healingDog);
		ashService.updateHealingDog(healingDog);
		
		return "redirect:/ash/getHealingDog?healingDogNo=" +healingDog.getHealingDogNo();
	}
	
	@RequestMapping(value = "listAsh")
	public String getAshList(@ModelAttribute("search") Search search, Model model, HttpSession session) throws Exception {
		System.out.println("/listAsh");
		
		System.out.println("서치 출력 ; " + search);

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = ashService.getHealingDogList(search);
		System.out.println("맵확인 : " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		System.out.println("리스트 넘기기전 리스트 확인 : " + map.get("list"));
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search); // 생략가능
		
		return "forward:/animal/listReservationASH.jsp";
	}
	
	@RequestMapping(value = "getCheckReservationASH")
	public String getCheckReservationASH(HttpServletRequest request,Model model)throws Exception{
		int healingDogNo = Integer.parseInt(request.getParameter("healingDogNo"));
		String reservationDate = request.getParameter("reservationDate");
		System.out.println("넘어온 힐링독 넘버 : " + healingDogNo);
		System.out.println("넘어온 날짜 : " + reservationDate);
		
		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		Ash ash = ashService.getAshReservationTime(healingDogNo, reservationDate);
			
		
		System.out.println("힐링독은? : " + healingDog);
		System.out.println("예약은? : " + ash);
		model.addAttribute("healingDog", healingDog);
		model.addAttribute("ash", ash);
		model.addAttribute("reservationDate", reservationDate);
		
		return "forward:/animal/getCheckReservationASH.jsp";
	}
	
	@RequestMapping(value = "addReservationASH")
	public String addReservationASH(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		int healingDogNo = Integer.parseInt(request.getParameter("healingDogNo"));
		String ashReservationDate = request.getParameter("ashReservationDate");
		String ashReservationTime = request.getParameter("ashReservationTime");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date();
		String mTime = simpleDateFormat.format ( currentTime );
		System.out.println ( mTime );
		
		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		
		Point point = new Point();
		point.setUser((User)session.getAttribute("user"));
		
		int currentPoint = commonService.getCurrentPoint(point);
		
		model.addAttribute("mTime", mTime);
		model.addAttribute("currentPoint", currentPoint);
		model.addAttribute("healingDog", healingDog);
		model.addAttribute("ashReservationDate", ashReservationDate);
		model.addAttribute("ashReservationTime", ashReservationTime);
		
		return "forward:/animal/addReservationASH.jsp";
	}

}
