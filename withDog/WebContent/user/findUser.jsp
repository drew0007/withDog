<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="../common/css.jsp" />
<title>아이디 / 비밀번호 찾기</title>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	
	<!-- 제이쿼리 달력  -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<link rel="stylesheet" href="/resources/demos/style.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	<!-- 제이쿼리 달력 :: 크롬에서 년도 안에 글씨 작게 -->
	<style type="text/css">
	.ui-datepicker .ui-datepicker-title select {
    font-size: 12px;
	}
	</style>
	<!-- 제이쿼리 달력 기본값 세팅 -->
	<script src="../js/datepicker.js"></script>
	<!-- end 제이쿼리 달력 -->
	
	<script type="text/javascript">
	
	$( function() {
		
		$("#userName").focus();
		
		//아이디 중복검사 
		$("#findUserId").on("click", function(){
			
			$.ajax({
				url : "/user/json/findUserId",
				method : "POST",
				dataType : "json",
				data: JSON.stringify({
					userName:$("#userName").val(),
					birth:$("#datepicker").val(),
				}),
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(JSONData , status){
					
					var check = JSONData.userId;
					
					if(check==null){
						$(".spanId").html("해당 정보 확인이 되지 않습니다.").css('color','red');
					}else{
						
						//원래 아이디 길이
						var showIdNo = check.length;
						showIdNo = showIdNo-3;
						//아이디 앞에서 3개
						var showId = check.substring(0,3);
						
						for(i=0;i<showIdNo;i++){
							showId+="*";
						}
						
						$(".spanId").html("고객님의 ID는 "+showId+"입니다.").css('color','blue');	
					}
				}
			});// end ajax
			 
		 });//end 아이디 중복검사
	
		
		 
		 
		//이메일 입력방식 선택 
		$("#email2").change(function(){ 
			$("#email2 option:selected").each(function () { 
				if($(this).val()== '1'){ //직접입력일 경우 
					$("#emailText").val(''); //값 초기화
					$("#emailText").attr("disabled",false); //활성화 
				}else{ //직접입력이 아닐경우 
					$("#emailText").val($(this).text()); //선택값 입력
					$("#emailText").attr("disabled",true); //비활성화
				} 		
				
			}); 
			
		});//end 이메일 입력방식
		
		
		//비밀번호 찾기 연결
		$("#findPW").on("click" , function() {
			
			
			//넘기기전 체크사항   email
			///1.이메일 입력 :: 직접 입력 선택시 option value값이 1=> emailText 입력값으로 ,email2 밸류에 @추가해주기
			if($("#email2").val()=='1' ){
 				
 				var email2= '@'+$("#emailText").val();
 				$("#email2 option:selected").val(email2);
 				
 			}else{
 				
 				var email2= '@'+$("#email2").val();
 				$("#email2 option:selected").val(email2);
 			}
			
			$("input[name='email']").val($("#email1").val()+$("#email2").val());
			
		
			$.ajax({
				url : "/user/json/findUserPW",
				method : "POST",
				dataType : "json",
				data: JSON.stringify({
					userId:$("#userId").val(),
					email:$("input[name='email']").val(),
				}),
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(JSONData , status){
					
					var check = JSONData;
					
					if(!check){
						$(".spanId2").html("해당 정보 확인이 되지 않습니다.").css('color','red');
					}else{
						$(".spanId2").html("고객님의 이메일로 임시 비밀번호가 전송되었습니다.").css('color','blue');	
					}
				}
			});// end ajax
			
		});//end 비밀번호 찾기 연결

	});	
	</script>	


</head>
<body>
		
		<jsp:include page="../layout/header2.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/603_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">아이디 / 비밀번호 찾기</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">가입시 입력했던 정보를 입력해주세요.</span>
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
                        <p class="black-text font-weight-600 text-uppercase text-large">아이디 찾기</p>
                        
                        <form id="findIdForm">
                        
                            <div class="col-md-12 no-padding">
                                <label>이름 :</label>
                                <input type="text" name="userName" id="userName">
                            </div>
                            
                             <div class="col-md-12 no-padding">
                                <label>생년월일 :</label>
                                <input type="text" name="birth" id="datepicker">
                                <span class="spanId"></span>
                            </div>
                             
                             
                         </form> 
                         
                         <div class="text-center">
                         	<button class="highlight-button btn no-margin post-search" id="findUserId">아이디 찾기</button>
                         </div>
                         
                    </div>
                </div><!-- row1 -->
                
                <div class="row margin-five no-margin-top"><!-- row1 -->
                    <div class="col-md-6 col-sm-12 center-col sm-margin-bottom-seven">
                        <p class="black-text font-weight-600 text-uppercase text-large">비밀번호 찾기</p>
                        
                        <form id="findPWForm">
                           
                            <div class="col-md-12 no-padding">
                                <label>아이디 :</label>
                                <input type="text" name="userId" id="userId">
                            </div>
                            
			   		    	<div class="form-group">
						    	<p>이메일 :  </p>  
					        	<input type="text" name="email1" class="col-md-4" id="email1">
				                <input type="text" class="col-md-1 no-border" placeholder="@">
				                <input type="text" name="emailText"class="col-md-3" id="emailText">
					                <div class="col-md-4">
										<select name="email2" id="email2">
											<option value="1">직접입력</option>
											<option value="naver.com">naver.com</option>
											<option value="daum.net">daum.net</option>
											<option value="gmail.com">gmail.com</option>
											<option value="hotmail.com">hotmail.com</option>
											<option value="nate.com">nate.com</option>
										</select>
						            </div>
						           <span class="spanId2"></span>  
						           <input type="hidden" name="email">
					        </div> 
                            
                        </form> 
                        
                        <div class="text-center">
                       		<button class="highlight-button btn no-margin post-search" id="findPW">비밀번호 찾기</button>
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