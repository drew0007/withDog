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
	 		 	
	 	$("a:contains('영상상담신청내역')").on("click",function(){
	 		$(self.location).attr("href","/ash/getConsultingAdminList");	 			 		
	 	});
	});
	
	</script>

</head>

<body>
		
	<jsp:include page="/layout/common-header.jsp" />
		
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/305_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/myPage_tit.png"></h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <!-- 서브타이틀 <span class="white-text">1234</span>-->
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->

        
        <!-- content section -->
        <section class="wow fadeIn">
            <div class="container">
                <div class="row col-md-3 col-sm-4">
                
                    <!-- sidebar  -->
                    <div class="col-md-8 col-sm-8 sidebar">
                       
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="widget-title font-alt">스토어</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body">
                                <ul class="category-list">
                                    <li><a href="">나의 구매내역</a></li>
                                    <li><a href="">취소/반품내역</a></li>
                                    <li><a href="">나의 문의 내역</a></li>
                                    <li><a href="../store/listCart.jsp">장바구니</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end widget  -->
                        
                         <!-- widget  -->
                        <div class="widget">
                            <h5 class="widget-title font-alt">크라우드펀딩</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body">
                                <ul class="category-list">
                                    <li><a href="#">회원별 후원내역</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end widget  -->
                        
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="widget-title font-alt">동물교감치유</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body">
                                <ul class="category-list">
                                    <li><a href="/ash/getAshReservationAdminList">회원별 예약내역</a></li>
                                    <li><a href="#">영상상담신청내역</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end widget  -->
                        
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="widget-title font-alt">회원</h5>
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
                       
                       
                        </div>
					</div>
					<input type="hidden" id="myPageState" name="myPageState" value="">
					
					<c:if test="${myPageState =='5'}">
						<div class="col-md-3 col-sm-4 ">
					   		<jsp:include page="../fund/listMyFund.jsp" />
						</div>
					</c:if>
					
					<c:if test="${myPageState =='8'}">
						<div class="col-md-3 col-sm-4 ">
					   		<jsp:include page="../user/getUser.jsp" />
						</div>
					</c:if>
					
					<c:if test="${myPageState =='9'}">
						<div class="col-md-3 col-sm-4 ">
					   		<jsp:include page="../user/updatePassword.jsp" />
						</div>
					</c:if>
					
					
					<c:if test="${myPageState =='10'}">
						<div class="col-md-3 col-sm-4 ">
					   		<jsp:include page="../user/deleteUser.jsp" />
						</div>
					</c:if>
										
					<c:if test="${empty myPageState || myPageState =='11'}">
						<div class="col-md-3 col-sm-4 ">
					   		<jsp:include page="../fund/listFundUserAdmin.jsp" />
						</div>
					</c:if>
					
					</div>
					<!-- end sidebar  -->
		</section>			
        <!-- end content section -->
        
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>