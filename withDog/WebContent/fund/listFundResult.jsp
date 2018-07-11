<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="/common/css.jsp" />


<title>펀딩 리스트</title>

</head>


<body>


	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/700_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/702_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
   <!--    <span class="about-number1 font-weight-400 letter-spacing-2 xs-no-border xs-no-padding-left xs-display-none">당신의 후원을 기다립니다.</span> -->  
        
        <!-- content section -->
        <section class="wow fadeIn">
        
                          
        
            <div class="container">
                <div class="row">
                    <!-- content  -->
                   
                                       
                    <!-- 펀딩 item 01 -->
                    <c:forEach var="list" items="${list}">
                    <div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="getFund?fundNo=${list.fundNo}"><img src="../images/fund/${list.fundImage}" alt=""></a></div>
                            <div class="post-details">
                                <a href="" class="fund-title">${list.fundTitle}</a>
                                <span class="fund-center">${list.fundCenter}</span>
                                <div class="borderline"></div>
                                <span class="fund-term">후원모집기간 : ${list.fundTerm}</span><br/>
                                <span class="fund-raising">모인금액 : ${list.fundRaising}</span>
                                
                            </div>
                                <a class="highlight-button btn-medium button margin-five" href="getFund?fundNo=${list.fundNo}">후원하기</a>
                        </div>
                    </div>
                    </c:forEach>
                   
                </div>
        		<div align="right">
        		     <a class="highlight-button btn-medium button margin-five" href="addFundView">등록하기</a>
        		</div>        
            </div>
        </section>
        <!-- end content section -->
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>