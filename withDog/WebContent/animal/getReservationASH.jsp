<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<jsp:include page="/common/css.jsp" />

	<title>예약시간 선택</title>
</head>
<script type="text/javascript">

</script>
<body>
		
	<jsp:include page="../layout/header.jsp" />

	<!-- head section -->
	<section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	   <img class="parallax-background-img" src="../images/sub/303_bg.jpg" alt="" />
	   <div class="container">
	       <div class="row">
	           <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
	               <!-- page title -->
	                <h1 class="white-text">서비스 예약일 &시간 선택</h1>
	               <!-- end page title -->
	               <!-- page title tagline -->
	               <span class="white-text xs-display-none">Reservation</span>
	               <!-- end title tagline -->
	            </div>
	        </div>
	    </div>
	</section>
	<!-- end head section -->
        
	<!--  내용  시작  -->
		<!--  예약 상단  -->
        <section class="no-padding-bottom no-padding-top margin-three">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-10 text-center center-col">
                        <span class="margin-one no-margin-top display-block letter-spacing-2">Reservation</span>
                        <h1>예약시간 선택</h1>
                        <div class="separator-line bg-black margin-two no-margin-bottom"></div>
                    </div>
                </div>
            </div>
        </section>
			<!--  치유견 정보  -->
			<div class="container">
			<div class="row no-margin-bottom">
				<div class="col-md-6 col-sm-6 text-center xs-margin-bottom-ten">
					<img src="http://placehold.it/300x300" alt=""/>
				</div>
				<div class="col-md-6 col-sm-6 sm-margin-bottom-ten">
					<div class="col-md-8 col-sm-12 no-padding">
						<ul class="list-line margin-ten text-med">
							<li id="dogBreed"><span class="font-weight-600">치유견종:</span> 몰키</li>
							<li id="dogName"><span class="font-weight-600">치유견 이름:</span>복이 | 여아</li>
							<li id="dogAge"><span class="font-weight-600">치유견 나이: </span>2세 </li>
							<li id="dogChar"><span class="font-weight-600">치유견 성격:</span> ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ</li>
							<li id="healer"><span class="font-weight-600">담당 치유사:</span> ㅇㅇㅇ</li>
						</ul>
					</div>
				</div>
			</div>
			</div>
			<!--  치유견 정보 끝-->
		<!--  예약 상단 끝 -->
				
		<!--  예약 하단 시작 -->
		<section class="bg-gray margin-three padding-three">
            <div class="container">
                <div class="row">
                    <!-- section title -->
                     <div class="col-md-8 col-sm-8 center-col text-center">
                        <div>
                            <!--  <i class="icon-hotairballoon large-icon"></i> -->
                             <img class="width-10" src="../images/icon/icon_100_02.png">
                        	
                            <h1>치유견 스케줄</h1>
                            <span class="margin-one no-margin-top display-block letter-spacing-2">Healing dog schedule</span>
                        	<div class="separator-line bg-black margin-two no-margin-bottom"></div>
                        </div>
                    </div>
                    <!-- end section title -->
                </div>
                <div class="row margin-five no-margin-bottom">
                    <!-- chef  -->
                    <div class="col-md-12">
                        <div class="col-md-6 chef-text bg-white">
                       		<form>
                        	<h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>서비스 예약일</strong></h6>
                        	<input type="text" id="textbox" name="name" class="input-round big-input" value="2018-08-17">
                            <div class="form-group no-margin-bottom">
                                <!-- label  -->
                               <h6 class="black-text no-margin-top margin-one no-letter-spacing"><strong>시간선택</strong></h6>
                                <!-- end label  -->
                                <div class="select-style input-round big-input">
                                    <select>
                                        <option value="">오전 10:00~ 13:00</option>
                                        <option value="">오후 14:00~ 17:00</option>

                                    </select>
                                </div>
                            </div>
							<a href="addReservationASH.jsp" class="highlight-button btn btn-medium button xs-margin-bottom-five">예약하러가기</a>
       						</form>
       					</div>	
       					<div class="col-md-6 chef-img">
                            <div class="img-border" style="border:2px solid red;">
                            	<img src="../images/sub/calendar.png" />
                            </div>
                        </div>
				   </div>
				</div>  
			</div>	 
	 </section> 
	<!--  예약 하단 끝 -->
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>