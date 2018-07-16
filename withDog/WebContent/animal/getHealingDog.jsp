<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="/common/css.jsp" />

<title>치유견 상세정보	</title>
</head>

<body>

	<jsp:include page="/layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/302_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">HealingDog Detail</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Details of HealingDog.</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        

        <!-- content section -->
        <section>
            <div class="container">
            	
                <div class="row">
                
                    <!-- product images -->
                    <div class="col-md-6 col-sm-12  sm-margin-bottom-ten">
                        <img src="http://placehold.it/700x583" alt=""/>
                    </div>
                    <!-- end product images -->
                    
                    <div class="col-md-5 col-sm-12 col-md-offset-1">
                    
                        <div class="separator-line bg-black no-margin-lr margin-five no-margin-top"></div>
                        
                        <!-- healingDog detail 01 -->
                        <p class="no-margin-bottom">이름</p>
                        <div class="fund-sub-title ">치유견 이름</div>
                        <!-- end detail  -->
                        
                        <!-- healingDog detail 02 -->
                        <p class="no-margin-bottom">생년월일</p>
                        <div class="fund-sub-title">2018.8.20</div>
                        <!-- end detail  -->
                        
                        <!-- healingDog detail 03 -->
                        <p class="no-margin-bottom">성별</p>
                        <div class="fund-sub-title">여아</div>
                        <!-- end detail  -->
                        
                        <!-- healingDog detail 04 -->
                        <p class="no-margin-bottom">특징</p>
                        <div class="fund-sub-title">치유견특징.치유견특징.치유견특징.치유견특징.치유견특징.치유견특징.치유견특징.치유견특징.치유견특징. </span></div>
                        <!-- end detail  -->
                        
                        <!-- healingDog detail 05 -->
                        <p class="no-margin-bottom">담당치유사</p>
                        <div class="fund-sub-title">이장원</div>
                        <!-- end detail  -->
                        
		                
                        
                    </div>
                </div>
            </div>
        </section>

        <section class="border-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                    
                    		<!-- section title -->
		                    <div class="col-md-12 text-center corporate-slider sm-margin-bottom-seven">
		                        <p class="sub-title deep-red-text letter-spacing-4 no-margin-top xs-letter-spacing-2">HealingDog Schedule</p><br>
		                    </div>
		                    <!-- end section title -->
                            
                                <!-- content -->
                                <div class="med-text fade in">
                                    <div class="row">
                                        <div class="col-md-11 col-sm-12 center-col">
                                            <img src="../images/temp-calendar.jpg" class="margin-tem-top" alt=""/>
                                        </div>
                                    </div>
                                </div>
                                <!-- end content -->
                                 
                        </div>
                        
                        <div class="text-center">
							<a href="#" class="highlight-button-dark btn btn-medium">치유견 정보 수정</a>
						</div>
                    </div>
                </div>
            </div>
        </section>


	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>