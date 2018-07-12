<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<jsp:include page="../common/css.jsp" />
	<title>회원탈퇴</title>
	
	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
		$( function() {
			
			//회원탈퇴 연결
			$("#delete").on("click" , function() {
				
				//라디오 체크 여부
				if(chk_radio()){
					$("form").attr("method","POST").attr("action","/user/deleteUser").submit();
				}
			});
			
		});
		
		//라디오 체크되었는지
		function chk_radio() { 
			num_temp = document.all.leaveReason.length; 
		 	for (i=0;i<num_temp ;i++) { 
		  	
		 		if(document.all.leaveReason[i].checked == true){ 
		  			break; 
		  		} 
		 	} 
		 	if (i == num_temp) { 
			  	alert("사유를 선택해주세요"); 
			  	return false;
		 	} 
		 	return true;
		} 

	</script>	
	
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
                        <h1 class="white-text">회원탈퇴</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">with Dog</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
      	<!-- content section /// careers.htm /// form.html-->
        <section class="wow fadeIn">
            <div class="container"><!-- container -->
            		<div class="col-md-8 col-sm-6 col-md-offset-1 career-form xs-margin-top-ten">
            		<!-- 포인트 -->
            		<h2 class="font-weight-600">회원 탈퇴 </h2>
		                   <div class="separator-line bg-black no-margin-lr"></div>
		                   <p class="text-large black-text letter-spacing-2 no-margin-bottom">현재 고객님의 사용가능한 포인트는<strong> 900 point</strong>입니다.</p>
            		</div>
                    <!-- 내용 -->
                    <div class="col-md-8 col-sm-6 col-md-offset-1 bg-gray career-form xs-margin-top-ten">
                    	<h2 class="font-weight-600">회원탈퇴 사유확인 </h2>
                        <!--  <p class="text-med margin-five">회원 탈퇴 사유를 선택해주세요</p>-->
	                        <!-- form -->
	                        <form>
	                            <div class="col-md-8 col-sm-4">
	                                <!-- radio button  -->
	                                <label class="radio-inline"><input type="radio" name="leaveReason" id="inlineRadio1" value="option1">동물교감치유 서비스 불만 </label>
	                                <!-- end radio button  -->
	                                <!-- radio button  -->
	                                <label class="radio-inline"><input type="radio" name="leaveReason" id="inlineRadio2" value="option2">이용빈도 낮음</label>
	                                <!-- end radio button  -->
	                             </div>   
	                             <div class="col-md-8 col-sm-4">   
	                                <!-- radio button  -->
	                                <label class="radio-inline"><input type="radio" name="leaveReason" id="inlineRadio3" value="option3">개인정보유출 우려</label>
	                                 <!-- end radio button  -->
	                                <label class="radio-inline"><input type="radio" name="leaveReason" id="inlineRadio4" value="option4">스토어상품 배송 지연</label>
	                               	 <!-- end radio button  -->
	                            </div>
	                            <input type="hidden" name="userId" value="${sessionScope.user.userId}">
	                        </form>
	                        <!-- end form -->
                    	</div>
                	</div>
                	<!-- 내용 -->
            		<!-- button  -->
            		<div class="text-center">
						<button class="highlight-button btn post-search" id="change">회원탈퇴</button>
                    </div>
                    <!-- end button  -->
            </div><!-- container -->
        </section>
        <!-- end content section -->       
                
        
<jsp:include page="../layout/footer.jsp" />

<jsp:include page="../common/js.jsp" />
</body>
</html>