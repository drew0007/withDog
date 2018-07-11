<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="/common/css.jsp" />

<title>애견상식 상세</title>
<script type="text/javascript">
$(function () {
	$("input[name=send]").on("click", function () {
		history.go(-1)
	})
	
	$("input[name=updateDogInfo]").on("click", function () {
		self.location="/dogInfo/updateDogInfo?dogInfoNo=${dogInfo.dogInfoNo}"
	})
})
</script>

</head>

<body>


	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/602_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/602_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section class="wow fadeIn">
            <div class="container">
            
            
            
                <!-- comment form -->
                <div class="row">
                        
                        <!-- info title -->
                        <div class="margin-four">
			            	<p class="col-md-10 col-sm-12 text-center center-col info-detail-title black-text  no-margin-bottom">${dogInfo.dogInfoTitle}</p>
			        	    <p class="no-margin text-center"><span class="light-gray-text">회원ID</span> ${dogInfo.user.userId} &nbsp; | &nbsp;<span class="light-gray-text"> 조회수</span> ${dogInfo.viewCount} </p>
			            </div>
			            <!-- end into title -->
            
                        <div class="col-md-10 col-sm-12 bg-white center-col border-top text-center">
                        
                        <!-- post details text -->
                        <p class="text-large" style="padding-top:60px;"><img  src = "/images/uploadFiles/dogInfo/${dogInfo.dogInfoImageList[0]}" width="400px" height="200px" alt=""/></p>
                        <p class="no-margin">${dogInfo.dogInfoContent}</p>
                        <!-- end post details text -->
                        
                        <!-- like icon -->
                        <div class="text-center border-bottom border-top margin-ten padding-four no-margin-bottom">
                            <a href="#" class="info-like margin-three-right"><i class="fa fa-thumbs-o-up small-icon"></i>${dogInfo.recommended}</a>
                            <a href="#" class="info-dislike"><i class="fa fa-thumbs-o-down small-icon"></i>${dogInfo.notRecommended}</a>
                        </div>
                        <!-- end line icon -->
                        
                         <!-- button  -->
                         <input type="submit" name="send" value="목록으로" class="highlight-button-dark btn btn-medium no-margin-bottom">
                         <c:if test="${dogInfo.user.userId==user.userId}">
                         <input type="submit" name="updateDogInfo" value="수정" class="highlight-button-dark btn btn-medium no-margin-bottom">
                         </c:if>
                         
                         <!-- end button  -->
                    </div>
                    
                    
                </div>
                <!-- end content  -->


		    </div>
        </section>
        <!-- end content section -->
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>