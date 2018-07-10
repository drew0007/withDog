<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="../common/css.jsp" />
<title>회원가입</title>

</head>
<body>
		
		<jsp:include page="../layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/100_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">User Registration</h1>
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
        <section>
            <div class="container clearfix"><!-- container1 -->
                <div class="row margin-five no-margin-top"><!-- row1 -->
                    <div class="col-md-6 col-sm-12 center-col sm-margin-bottom-seven">
                        <p class="black-text font-weight-600 text-uppercase text-large">가입 정보</p>
                        <form id="billing-form" method="post" action="javascript:void(0)" name="billing-form">
                            <div class="col-md-12 no-padding">
                                <label>아이디:</label>
                                <input type="text" name="userId">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>비밀번호:</label>
                                <input type="password" name="password">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>비밀번호 확인:</label>
                                <input type="password" name="passwordCheck">
                            </div>
                           	<div class="col-md-12 no-padding">
                                <label>이름:</label>
                                <input type="text" name="userName">
                            </div>
                            
                             <div class="col-md-12 no-padding">
                                <label>생년월일:</label>
                                <input type="text" name="birth" placeholder="예)1995.10.02">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <p style="margin-bottom:10px">이메일:</p>
                                <input type="text" name="email" class="col-md-6">
                             	<div class="col-md-6 input-round ">
									<select>
										<option value="">직접입력</option>
										<option value="">@naver.com</option>
										<option value="">@daum.net</option>
										<option value="">@google.com</option>
									</select>
                            	 </div>
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>휴대전화:</label> 
                                <input type="text" name="phone">
                                
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>주소:</label>
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <input type="text" name="Address1" class="col-md-8">
                                <button class="highlight-button btn no-margin pull-right post-search">우편번호 검색</button>
                                <input type="text" name="Address2">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>좋아하는 견종:</label>
                                <input type="text" name="dogNo">
                            </div>
                            
                          </form> 
                          
                          <div class="text-center">
                          <button class="highlight-button btn no-margin post-search">회원가입하기</button>
                          </div>
                    </div>
                </div><!-- row1 -->
            </div><!-- container1 -->
            
        </section>
        <!-- end content section -->
        
		
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>