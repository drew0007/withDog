<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<jsp:include page="/common/css.jsp" />

<title>예약 정보 입력</title>
</head>
<body>

	<jsp:include page="../layout/header.jsp" />
		
	<!-- head section -->
	<section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	   <img class="parallax-background-img" src="../images/sub/300_bg.jpg" alt="" />
	   <div class="container">
	       <div class="row">
	           <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
	               <!-- page title -->
	               <h1 class="white-text">동물교감 치유 서비스 예약 정보 입력</h1>
	               <!-- end page title -->
	               <!-- page title tagline -->
	               <span class="white-text xs-display-none">Register and modify product information.</span>
	               <!-- end title tagline -->
	            </div>
	        </div>
	    </div>
	</section>
	<!-- end head section -->
		        
    <!-- content section -->
    <!-- 상단  -->
        <section class="no-padding-bottom no-padding-top margin-three">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-10 text-center center-col">
                        <span class="margin-one no-margin-top display-block letter-spacing-2">Register information</span>
                        <h1>예약내역</h1>
                        <div class="separator-line bg-black margin-two no-margin-bottom"></div>
                        <p class="text-p width-120 center-col margin-two no-margin-bottom">치유견 이름 : 복이 | 담당 치유사 : 박소현 </p>
                    </div>
                </div>
            </div>
        </section>
        <!-- end 상단  -->

		<!-- 정보1   -->
		<div class="container">
			<div class="row no-margin-bottom">
				<div class="col-md-6 col-sm-6 text-center xs-margin-bottom-ten">
					<img src="http://placehold.it/300x300" alt=""/>
				</div>
			
				<div class="col-md-6 col-sm-6 sm-margin-bottom-ten">
					<div class="col-md-8 col-sm-12 no-padding">
						<ul class="list-line margin-ten text-med">
							<li id="dogBreed"><span class="font-weight-600">예약자 이름:</span> 홍길동</li>
							<li id="dogAge"><span class="font-weight-600">예약일시: </span> 2018.08.17 오전10:00~13:00 </li>
							<li id="dogName"><span class="font-weight-600">서비스 이용금액:</span>100,000원</li>
							<li id="dogChar"><span class="font-weight-600">예상적립 포인트:</span>1,000point적립예정</li>
							<li id="healer"><span class="font-weight-600">신청일:</span>2018.07.15 </li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- 정보1 끝   -->
		
	
		<!--  예약 하단 시작 -->
		<section class="bg-gray margin-three padding-three">
            <div class="container">
                <div class="row">
                    <!-- section title -->
                     <div class="col-md-8 col-sm-8 center-col text-center">
                        <div>
                            <i class="icon-hotairballoon large-icon"></i>
                        	<span class="margin-one no-margin-top display-block letter-spacing-2">Healing dog schedule</span>
                            <h1>결제정보</h1>
                        	<div class="separator-line bg-black margin-two no-margin-bottom"></div>
                        </div>
                    </div>
                    <!-- end section title -->
                </div>
                
                <div class="row margin-five no-margin-bottom">
                    <div class="col-md-12">
                        <div class="col-md-6 col-sm-8 center-col">
                       		<!--form-->
                       		<form>  
                            	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>주소</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="서울 강남구 도곡동 ">
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="타워팰리스 102동 607호">
	                        	
	                        	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>전화번호</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="010-1234-5678">
                            
                            	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>특이사항</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input"  placeholder="20자 미만으로 입력헤주세요">
	                        	
	                        	
	                        	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>*총금액</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="100,000원" readonly>
	                        	
	                        	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>사용할 포인트</strong><span> &nbsp;(현재 보유 포인트 : 10,000 point)</span></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" >
	                        	
	                        	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>*결제수단</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="카카오페이">
                            
                            	 <h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>*최종결금액</strong></h6>
	                        	<input type="text" id="textbox" name="" class="input-round big-input" value="90,000원" readonly>
                            
                            </form>
                            <!--end form-->
                          
                            <div class="text-center margin-five">
                             	<a href="addReservationASHView.jsp" class="btn-small-black-border-light btn btn-medium btn-round button xs-margin-bottom-five">결제하기</a>
                     		</div>
                     		
       					</div>	

				   </div>
				</div>  
			</div>	<!-- end container --> 

       	
	</section>


	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />

</body>
</html>