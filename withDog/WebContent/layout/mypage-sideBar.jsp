<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<jsp:include page="../common/css.jsp" />
	<title>Admin page</title>
	
	
	 <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
	$(function () {
		
		$( "a:contains('나의 문의내역')" ).on("click" , function() {
			$(self.location).attr("href","/inquiry/getMyInquiryList");
 		});
		
		$( "a:contains('나의 후원내역')" ).on("click" , function() {
			$(self.location).attr("href","/fund/getMyFundList");
 		});
		
	 	///나의 정보 확인 연결
	 	$( "a:contains('나의 정보 확인')" ).on("click" , function() {
			$(self.location).attr("href","/user/getUser");
 		});
	 	
	 	///비밀번호 수정 연결
	 	$( "a:contains('비밀번호 수정')" ).on("click" , function() {
			$(self.location).attr("href","/user/updatePassword");
		});
	 	
	 	///회원탈퇴 연결
	 	$( "a:contains('회원탈퇴')" ).on("click" , function() {
			$(self.location).attr("href","/user/deleteUser");
		});
	 		 	
	 	$("a:contains('나의 포인트 내역')").on("click",function(){
	 		$(self.location).attr("href","/common/getMyPointList");	 			 		
	 	});
<<<<<<< HEAD
	});
	
	</script>

</head>

<body>
        
                       
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 쇼핑</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 구매내역</a></li>
              <li><a href="#">취소/반품내역</a></li>
              <li><a href="#">나의 문의내역</a></li>
              <li><a href="#">장바구니</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
   <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 후원</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 후원내역</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 예약</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="">나의 예약내역</a></li>
              <li><a href="">영상상담신청내역</a></li>
=======
	 	
	 	$("a:contains('영상상담신청내역')").on("click",function(){
	 		$(self.location).attr("href","/ash/getMyConsultingList");	 			 		
	 	});
	});
	
	</script>

</head>

<body>
        
                       
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 쇼핑</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 구매내역</a></li>
              <li><a href="#">취소/반품내역</a></li>
              <li><a href="#">나의 문의내역</a></li>
              <li><a href="#">장바구니</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
   <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 후원</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 후원내역</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 예약</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 예약내역</a></li>
              <li><a href="#">영상상담신청내역</a></li>
>>>>>>> refs/heads/master
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 정보</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 정보 확인</a></li>
              <li><a href="#">비밀번호 수정</a></li>
              <li><a href="#">회원탈퇴</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
  
  <!-- widget  -->
  <div class="widget">
      <h5 class="widget-title font-alt">My 포인트</h5>
      <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
      <div class="widget-body">
          <ul class="category-list">
              <li><a href="#">나의 포인트 내역</a></li>
          </ul>
      </div>
  </div>
  <!-- end widget  -->
                       
	
</body>
</html>