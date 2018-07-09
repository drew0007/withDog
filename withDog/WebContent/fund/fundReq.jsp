<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

<jsp:include page="/common/css.jsp" />


<title>펀딩신청문의</title>

</head>


<body>


	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/703_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/704_tit.png"></h1>
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
                <div class="row">
                    <div class="col-md-5 col-sm-8 col-xs-11 center-col xs-no-padding">
                        <form>
                            <div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">신청자 이름</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <input type="text" name="username" id="username" class="input-round big-input">
                                <!-- end input  -->
                            </div>

                            <div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">신청 기관</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <input type="text" name="" id="" class="input-round big-input">
                                <!-- end input  -->
                            </div>

							<div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">전화 번호</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <input type="text" name="" id="" class="input-round big-input">
                                <!-- end input  -->
                            </div>

 							<div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">신청사유</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <textarea name="" class="input-round" placeholder=""></textarea>
                                <!-- end input  -->
                            </div>

 							<div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">계좌번호</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <input type="text" name="" id="" class="input-round big-input">
                                <!-- end input  -->
                            </div>

							 <div class="form-group no-margin-bottom">
                                <!-- label  -->
                                <label class="text-uppercase">파일첨부</label>
                                <!-- end label  -->
                                <!-- input  -->
                                <input type="file" name="file" id="exampleInputFile" class="input-round big-input">
								<!-- end input  -->
							</div>
                                
                        </form>
                       	<div class="text-center">
                           <button class="btn highlight-button-dark btn-medium btn-round margin-five no-margin-right" type="submit">신청하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- end content section -->

        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>