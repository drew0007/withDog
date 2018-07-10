<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<jsp:include page="../common/css.jsp" />
	<title>My page</title>
	
	
	 <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
	$(function () {
	
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
	});
	
	</script>

</head>

<body>
		
		<jsp:include page="../layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/604_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">My page</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Register and modify user information.</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        <!-- content section -->
        <section class="wow fadeIn">
            <div class="container">
                <div class="row">
                
                    <!-- sidebar  -->
                    <div class="col-md-3 col-sm-4 sidebar">
                       
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="widget-title font-alt">My 쇼핑</h5>
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
                            <h5 class="widget-title font-alt">My 후원</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body">
                                <ul class="category-list">
                                    <li><a href="">나의 후원내역</a></li>
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
                                    <li><a href="">나의 영상상담신청내역</a></li>
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
                                    <li><a href="/common/getMyPointList">나의 포인트 내역</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end widget  -->
                        
					</div>
					<!-- end sidebar  -->
        <!-- end content section -->
        
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>