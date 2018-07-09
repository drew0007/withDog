<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="/common/css.jsp" />
<title>펀딩상세정보</title>
</head>

<body>

	<jsp:include page="/layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/700_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">Fund Detail</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Details of Crowd funding.</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        

        <!-- content section -->
        <section>
            <div class="container">
            
            	<!-- fund title -->
            	<p class="col-md-10 col-sm-8 col-xs-11 text-center center-col fund-detail-title black-text">크라우드펀딩 제목</p>
            	<!-- end fund title -->
            	
                <div class="row">
                
                    <!-- product images -->
                    <div class="col-md-6 col-sm-12 zoom-gallery sm-margin-bottom-ten">
                        <a href="http://placehold.it/700x583"><img src="http://placehold.it/700x583" alt=""/></a>
                    </div>
                    <!-- end product images -->
                    
                    <div class="col-md-5 col-sm-12 col-md-offset-1">
                    
                        <!-- fund No -->
                         <span class="rating-text text-uppercase pull-right">펀딩번호: <span class="black-text">10000</span></span>
                        <div class="separator-line bg-black no-margin-lr margin-five no-margin-top"></div>
                        <!-- end fund No -->
                        <!-- fund detail 01 -->
                        <p class="no-margin-bottom">펀딩요청기관</p>
                        <div class="fund-sub-title ">WithDog</div>
                        <!-- end detail  -->
                        <!-- fund detail 02 -->
                        <p class="no-margin-bottom">펀딩기간</p>
                        <div class="fund-sub-title">2018.7.9 ~ 2018.8.20</div>
                        <!-- end detail  -->
                        <!-- fund detail 03 -->
                        <p class="no-margin-bottom">현재후원금액</p>
                        <div class="fund-sub-title">800,000<span class="fund-small">원</span></div>
                        <!-- end detail  -->
                        <!-- fund detail 03 -->
                        <p class="no-margin-bottom">후원자수</p>
                        <div class="fund-sub-title">146<span class="fund-small">명</span></div>
                        <!-- end detail  -->
                        
                        <!-- 후원하기 모달팝업 -->
	                        <div class="col-md-9 col-sm-9 no-padding margin-five">
	                        
	                            <!-- button -->
		                        <a class="highlight-button btn btn-medium button no-margin-right popup-with-zoom-anim no-margin-bottom" href="#modal-popup2">후원하기</a>
	                            <!-- end button -->
	                            
	                            <!-- modal popup -->
		                        <div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
			                        <!-- modal popup -->
			                        <div id="modal-popup2" class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
			                            <span class="fund-modal slider-subtitle5 black-text no-margin-bottom">후원하기</span>
			                            <div class="borderline-gray"></div>
			                            
			                            <form>
                        
				                            <div class="form-group no-margin-bottom">
				                                <!-- label  -->
				                                <label class="pull-left">후원할 금액</label>
				                                <!-- end label  -->
				                                <!-- input  -->
				                                <input type="text" name="fundPrice"  class="big-input">
				                                <!-- end input  -->
				                            </div>
				                            
				                            <div class="form-group" style="margin-bottom:40px;">
				                                <!-- label  -->
				                                <label class="pull-left">사용할 포인트</label>
				                                <!-- end label  -->
				                                <!-- input  -->
				                                <input type="text" name="usePoint"  class="big-input no-margin-bottom">
				                                <div class="pull-right">[현재 1000 point 보유]</div>
				                                
				                                <!-- end input  -->
				                            </div>
				                            
			                            	
				                            <div class="form-group" style="margin-bottom:40px;">
				                            	<p class="borderline-gray"></p>
				                                <label class="pull-left">결제수단</label>
				                                <p class="pull-right"><input type="radio" name="kakaoPay" id="kakaoPay" value="kakaoPay" checked>카카오페이</p>
				                            </div>
				                            
				                       </form>
				                       	
				                            <a class="highlight-button btn-small button center-col" href="#">후원금결제</a>
				                       
			                        </div>
			                        <!-- end modal popup -->
			                    </div>
		                        <!-- end modal popup -->
		                        
	                        </div>
		                <!-- end 후원하기 모닾팝업 --> 
		                
                        
                    </div>
                </div>
            </div>
        </section>

        <section class="border-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <!-- tab -->
                        <div class="tab-style1">
                            <div class="col-md-12 col-sm-12 no-padding">
                                <!-- tab navigation -->
                                <ul class="nav nav-tabs nav-tabs-light">
                                    <li class="active"><a href="#tab_sec1" data-toggle="tab">펀딩설명</a></li>
                                </ul>
                                <!-- tab end navigation -->
                            </div>
                            <!-- tab content section -->
                            <div class="tab-content">
                            
                            
                                <!-- 펀딩설명 -->
                                <div class="tab-pane med-text fade in active" id="tab_sec1">
                                    <div class="row">
                                        <div class="col-md-11 col-sm-12 center-col">
                                            <p>펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명펀딩설명</p>
                                            <img src="http://placehold.it/1200x800" alt="" />
                                        </div>
                                    </div>
                                </div>
                                <!-- end 펀딩설명 -->
                                
                                 
                            </div>
                            <!-- end tab content section -->
                        </div>
                        <!-- end tab -->
                    </div>
                </div>
            </div>
        </section>

        
        
        
        


	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
</body>
</html>