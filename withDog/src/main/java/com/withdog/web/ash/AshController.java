package com.withdog.web.ash;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.withdog.service.domain.Consulting;
import com.withdog.service.domain.Fund;
import com.withdog.service.domain.HealingDog;
import com.withdog.service.domain.Point;
import com.withdog.service.domain.User;
import com.withdog.service.sns.SnsDAO;
import com.withdog.service.sns.SnsService;

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

	@Autowired
	@Qualifier("snsServiceImpl")
	private SnsService snsService;

	public AshController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['healingDogPageUnit']}")
	int pageUnit;

	@Value("#{commonProperties['healingDogPageSize']}")
	int pageSize;

	@Value("#{commonProperties['healingDogfilePath']}")
	String healingDogfilePath;

	@RequestMapping(value = "addHealingDog", method = RequestMethod.GET)
	public String addHealingDog(Model model) throws Exception {
		System.out.println("/addHealingDog : GET");
		model.addAttribute("dog", dogBreedDicService.getAllDogBreedInfoList());
		return "forward:/animal/addHealingDog.jsp";
	}

	@RequestMapping(value = "addHealingDog", method = RequestMethod.POST)
	public String addHealingDog(@ModelAttribute("healingDog") HealingDog healingDog,
			@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("/addHealingDog : POST");
		System.out.println("입력받은 도그 출력 : " + healingDog);
		System.out.println();
		String fileName = "";

		if (file.getSize() == 0) {
			System.out.println("파일비었음");
			healingDog.setHealingDogimage("");
		} else {
			System.out.println("저장된 파일 : " + file.getOriginalFilename());
			File f = new File(healingDogfilePath + (file.getOriginalFilename()));
			fileName = file.getOriginalFilename();
			file.transferTo(f);
			healingDog.setHealingDogimage(fileName);
		}
		// Thread.sleep(3000);

		ashService.addHealingDog(healingDog);
		System.out.println("add완료");

		return "forward:/animal/addHealingDogView.jsp";
	}

	@RequestMapping(value = "listHealingDog")
	public String getHealingDogList(@ModelAttribute("search") Search search, Model model, HttpSession session)
			throws Exception {
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
		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		model.addAttribute("healingDog", healingDog);
		System.out.println("get하기전 도메인확인 : " + healingDog);

		return "forward:/animal/getHealingDog.jsp";
	}

	@RequestMapping(value = "updateHealingDog", method = RequestMethod.GET)
	public String updateHealingDog(@RequestParam("healingDogNo") int healingDogNo, Model model) throws Exception {
		System.out.println("/updateHealingDog : GET");
		// Business Logic
		HealingDog healingDog = ashService.getHealingDog(healingDogNo);
		model.addAttribute("dog", dogBreedDicService.getAllDogBreedInfoList());
		model.addAttribute("healingDog", healingDog);
		System.out.println("get하기전 도메인확인 : " + healingDog);

		return "forward:/animal/updateHealingDog.jsp";
	}

	@RequestMapping(value = "updateHealingDog", method = RequestMethod.POST)
	public String updateHealingDog(@ModelAttribute("healingDog") HealingDog healingDog,
			@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println("/updateHealingDog : POST");
		System.out.println("입력받은 도그 출력 : " + healingDog);
		System.out.println();
		String fileName = "";

		if (file.getSize() == 0) {
			System.out.println("업데이트 시 이미지 수정 안함");
			healingDog.setHealingDogimage(ashService.getHealingDog(healingDog.getHealingDogNo()).getHealingDogimage());
		} else {
			System.out.println("업데이트 시 이미지 수정 함");
			File f = new File(healingDogfilePath + (file.getOriginalFilename()));
			file.transferTo(f);
			fileName = file.getOriginalFilename();
			healingDog.setHealingDogimage(fileName);
		}
		System.out.println("업데이트 하기 전 도그 : " + healingDog);
		ashService.updateHealingDog(healingDog);

		return "redirect:/ash/getHealingDog?healingDogNo=" + healingDog.getHealingDogNo();
	}

	@RequestMapping(value = "listAsh")
	public String getAshList(@ModelAttribute("search") Search search, Model model, HttpSession session)
			throws Exception {
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
	public String getCheckReservationASH(HttpServletRequest request, Model model) throws Exception {
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
	public String addReservationASH(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		int healingDogNo = Integer.parseInt(request.getParameter("healingDogNo"));
		String ashReservationDate = request.getParameter("ashReservationDate");
		String ashReservationTime = request.getParameter("ashReservationTime");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date currentTime = new Date();
		String mTime = simpleDateFormat.format(currentTime);
		System.out.println(mTime);

		HealingDog healingDog = ashService.getHealingDog(healingDogNo);

		Point point = new Point();
		point.setUser((User) session.getAttribute("user"));

		int currentPoint = commonService.getCurrentPoint(point);

		model.addAttribute("mTime", mTime);
		model.addAttribute("currentPoint", currentPoint);
		model.addAttribute("healingDog", healingDog);
		model.addAttribute("ashReservationDate", ashReservationDate);
		model.addAttribute("ashReservationTime", ashReservationTime);

		return "forward:/animal/addReservationASH.jsp";
	}

	@RequestMapping(value = "kakaoPay/{reqUsePoint}")
	private String kakaoPay(@ModelAttribute("ash") Ash ash, @PathVariable String reqUsePoint, HttpSession reqSession,
			HttpServletRequest req) throws Exception {
		// Ash ash = new Ash();
		System.out.println("kakaoPay Start==================================");
		System.out.println("넘어온 ash는? " + ash);
		System.out.println(reqUsePoint);
		HttpSession session = req.getSession(false);
		User user = new User();
		if (session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
		}

		int price = 0;
		int usePoint = 0;

		/// 영수증.jsp로 카드 결제시 callback 되는지
		String forwardUri = "forward:/sns/kakaoPay.jsp";
		// snsKakaoPay를 위한 로직
		Point pointAsh = new Point();
		pointAsh.setUser(user);
		pointAsh.setAsh(ash);
		System.out.println(1);

		if (pointAsh.getAsh().getAshReservationPrice() == 0) { // 결제 시 사용금액이 0이라면?
			System.out.println("사용한 결제금액이 없다면");
			price = pointAsh.getAsh().getAshReservationPrice(); // price에 0을 넣는다.
		}
		if (Integer.parseInt(reqUsePoint) != 0) { // 사용한 포인트가 0이 아니라면
			pointAsh.setUsePoint(Integer.parseInt(reqUsePoint));
			usePoint = Integer.parseInt(reqUsePoint);
		}
		System.out.println(price + "::" + usePoint);

		if (pointAsh.getAsh().getAshReservationPrice() != 0) { // 결제 시 사용금액이 0이 아니라면?
			System.out.println(123);
			String uri = "http://localhost:8080/ash/addReservationASHView?state=";
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

			JSONObject jobj = snsService.AshKakaoPay(pointAsh, uri); // 카카오페이 다녀와서 데이터를 받는 객체

			System.out.println(jobj.get("tid"));
			String url = (String) jobj.get("next_redirect_pc_url");
			System.out.println("next_redirect_pc_url ? " + url); // r결제창
			session.setAttribute("url", url);
		} else { // 포인트로만 결제시
			forwardUri = "forward:/ash/addReservationASHView";
		}

		// 임시처리

		Point point = new Point();

		// 포인트 이것만 긁기
		ash.setUser(user);
		point.setUser(user);// userid
		point.setAsh(ash);
		System.out.println("사용한 포인트는 ? : " + pointAsh.getUsePoint());
		point.setUsePoint(pointAsh.getUsePoint());

		session.setAttribute("ashPoint", point);

		System.out.println(session.getAttribute("ashPoint"));

		return forwardUri;

	}

	@RequestMapping(value = "addReservationASHView")//결제 성공시 결제완료창
	public String addReservationASHView(HttpServletRequest request, Model model) throws Exception {
		System.out.println("/addReservationASHView");

		Point point = new Point();
		Ash ash = new Ash();
		User user = new User();

		System.out.println(request.getParameter("state"));

		HttpSession session = request.getSession(false);

		session.removeAttribute("url");

		System.out.println(session.getAttribute("ashPoint"));

		String state = request.getParameter("state");

		System.out.println("상태체크!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(state);
		System.out.println("상태체크!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		if (state.equals("2")) {
			System.out.println("결제실패");
			request.setAttribute("state", "2");

		} else if (state.equals("1")) {
			System.out.println("결제취소");
			request.setAttribute("state", "1");

		} else if (state.equals("0")) {
			System.out.println("결제성공");
			request.setAttribute("state", "0");
			if (session.getAttribute("ashPoint") != null) {
				point = (Point) session.getAttribute("ashPoint");
				ash = point.getAsh();
				user = point.getUser();

				ashService.addAshReservation(ash);

				// 동치 예약 번호 가져오기
				System.out.println("가기전 ash : " + ash);
				System.out.println("가기전 유저 : " + user.getUserId());
				Ash reseultAsh = ashService.getAshMyReservationByUser(ash, user.getUserId());
				System.out.println("//////////////////////////////////////////////");
				System.out.println(reseultAsh);

				ash.setAshReservationNo(reseultAsh.getAshReservationNo());

				point.setAsh(ash);// 후원,구매,예약 구분을 위해

				double savePoint = ash.getAshReservationPrice() * 0.01;
				System.out.println("적립포인트" + savePoint);
				int resultpoint = (int) savePoint;
				point.setPoint(resultpoint);

				commonService.addPointinfo(point);
			}
		} else {

			model.addAttribute("state", request.getParameter("state"));
			ash = ashService.getAshMyReservation(Integer.parseInt(request.getParameter("ashReservationNo")));
		}
		model.addAttribute("ash", ash);
		System.out.println("state확인 : " + request.getParameter("state"));
		System.out.println("a 확인 :  " + request.getParameter("ashReservationNo"));
		session.removeAttribute("ashPoint");

		System.out.println("카카오페이 끝났고, 이동합니다.");

		return "forward:/animal/addReservationASHView.jsp";
	}

	@RequestMapping(value = "getMyReservationASHList")  //나의 예약리스트
	public String getMyReservationASHList(@ModelAttribute("search") Search search, Model model, HttpSession session)
			throws Exception {
		System.out.println("/getMyReservationASHList");
		System.out.println("서치 출력 ; " + search);

		User user = (User) session.getAttribute("user");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
			search.setSearchStartDay("");
			search.setSearchEndDay("");
			search.setSearchKeyword("");
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = ashService.getAshMyReservationList(search, user.getUserId());
		System.out.println("맵확인 : " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		System.out.println("리스트 넘기기전 리스트 확인 : " + map.get("list"));

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/animal/listMyReservationASH.jsp";
	}
	
	@RequestMapping(value = "getMyReservationASH/{ashReservationNo}")  //나의 예약리스트
	public String getMyReservationASH(@PathVariable int ashReservationNo, HttpSession session, Model model) throws Exception{ //나의 예약 상세확인
		System.out.println("/getMyReservationASH");
		Ash ash = ashService.getAshMyReservation(ashReservationNo);
		User user = (User)session.getAttribute("user");
		
		Ash resultAsh = ashService.getAshMyReservationByUser(ash, user.getUserId());
		
		model.addAttribute("ash", resultAsh);
		
		return "forward:/animal/getMyReservationASH.jsp";
	}
	
	@RequestMapping(value = "updateMyReservationCondition/{ashReservationNo}")  //나의 예약컨디션 수정
	public String updateMyReservationCondition(@PathVariable int ashReservationNo,Model model) throws Exception{ //나의 예약 상세확인
		System.out.println("/updateMyReservationCondition");
		Ash ash = ashService.getAshMyReservation(ashReservationNo);
		ash.setAshReservationCondition("3");
		ashService.updateMyReservationCondition(ash);
		System.out.println("업데이트된 컨디션 : " + ash.getAshReservationCondition());
		
		model.addAttribute("ash", ash);
		
		return "forward:/animal/getMyReservationASH.jsp";
	}
	
	@RequestMapping(value = "updateAshMyReservation/{ashReservationNo}", method= RequestMethod.GET)  //나의 예약 수정
	public String updateAshMyReservation(@PathVariable int ashReservationNo,Model model) throws Exception{ //나의 예약 상세확인
		System.out.println("/updateAshMyReservation : GET");
		Ash ash = ashService.getAshMyReservation(ashReservationNo);
		
		model.addAttribute("ash", ash);
		
		return "forward:/animal/updateMyReservationASH.jsp";
	}
	
	@RequestMapping(value = "updateAshMyReservation/{ashReservationNo}")  //나의 예약 수정
	public String updateAshMyReservation(@PathVariable int ashReservationNo,Model model,HttpServletRequest request) throws Exception{ //나의 예약 상세확인
		System.out.println("/updateAshMyReservation : POST");
		Ash ash = ashService.getAshMyReservation(ashReservationNo);
		ash.setAshReservationAddress1(request.getParameter("ashReservationAddress1"));
		ash.setAshReservationAddress2(request.getParameter("ashReservationAddress2"));
		ash.setAshReservationPhone(request.getParameter("ashReservationPhone"));
		ash.setAshReservationEtc(request.getParameter("ashReservationEtc"));
		
		ashService.updateAshMyReservation(ash);
		
		System.out.println("업데이트된 ash : " + ash);
		
		model.addAttribute("ash", ash);
		
		return "forward:/animal/getMyReservationASH.jsp";
	}
	
	@RequestMapping(value = "getAshReservationAdminList")  //관리자의 회원예약리스트확인
	public String getAshReservationAdminList(@ModelAttribute("search") Search search, Model model, HttpSession session)
			throws Exception {
		System.out.println("/getAshReservationAdminList");
		System.out.println("서치 출력 ; " + search);

		User user = (User) session.getAttribute("user");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
			search.setSearchStartDay("");
			search.setSearchEndDay("");
			search.setSearchKeyword("");
			search.setSearchCondition("0");
		}
		search.setPageSize(pageSize);

		Map<String, Object> map = ashService.getAshReservationAdminList(search);
		System.out.println("맵확인 : " + map);
		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);
		System.out.println("리스트 넘기기전 리스트 확인 : " + map.get("list"));

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/animal/listReservationASHAdmin.jsp";
	}
	
	
	
//	컨설팅
	
	@RequestMapping(value="getConsultingDogList", method=RequestMethod.GET)
	public String getConsultingDogList(Model model) throws Exception{ 
		
		System.out.println("/ash/getConsultingDogList : GET");
		
		List<HealingDog> list = ashService.getConsultingDogList();
		
		System.out.println("list : "+list);
		
		model.addAttribute("list", list);
		
		return "forward:/animal/addConsulting.jsp";
	}
	
	@RequestMapping(value="addConsulting", method=RequestMethod.GET)
	public String addConsulting(@RequestParam("healingDogNo") int healingDogNo, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception{ 
		
		System.out.println("/ash/addConsulting : GET");

		System.out.println("영상상담 치유견번호 : "+healingDogNo);
		
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("user");
		
		HealingDog healingDog = new HealingDog();
		healingDog.setHealingDogNo(healingDogNo);
		
		Consulting consulting = new Consulting();
		consulting.setUser(user);
		consulting.setHealingDog(healingDog);
		
		System.out.println("consulting : "+consulting);
		
		ashService.addConsulting(consulting);
		
		//httpServletResponse.sendRedirect("https://withdog.herokuapp.com/gettoken/"+user.getUserId()+"/token");
		return "forward:/ash/getMyConsultingList";
		
	}

	@RequestMapping(value="getMyConsultingList")
	public String getMyConsultingList(@ModelAttribute("search") Search search, HttpServletRequest request, Model model) throws Exception{ 
		
		System.out.println("/ash/getMyConsultingList ");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("user");
		
		System.out.println("여기까진 왓나1");
		
		Map<String, Object> map = ashService.getMyConsultingList(search, user.getUserId());
		System.out.println("여기까진 왓나2");
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("list : "+map.get("list"));
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		
		return "forward:/animal/listMyConsulting.jsp";
	}
	
	@RequestMapping(value="updateConsultingState")
	public String updateConsultingState(@RequestParam("consultingNo") int consultingNo, @RequestParam("consultingState") String consultingState, HttpServletRequest request, Model model) throws Exception{
		System.out.println("/ash/updateConsultingState ");
		String url="";
		
		HttpSession session = request.getSession(false);
		User user = new User();
		user = (User)session.getAttribute("user");
		
		if(user.getRole() != null) {
			if(user.getRole().equals("admin")) {
				url = "redirect:/ash/getConsultingAdminList";
			}else {
				url = "redirect:/ash/getMyConsultingList";
			}
		}
		
		Consulting consulting = new Consulting();
		consulting.setConsultingNo(consultingNo);
		consulting.setConsultingState(consultingState);
		
		System.out.println(consulting);
		
		ashService.updateConsultingState(consulting);
		
		return url;
	}

	@RequestMapping(value="getConsultingAdminList")
	public String getConsultingAdminList(@ModelAttribute("search") Search search, Model model) throws Exception{ 
		
		System.out.println("/ash/getConsultingAdminList ");
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = ashService.getConsultingAdminList(search);
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("list : "+map.get("list"));
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		
		return "forward:/animal/listConsultingAdmin.jsp";
	}
	
//	end컨설팅

}
