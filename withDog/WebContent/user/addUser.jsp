<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

	<title>회원가입</title>
	
	<jsp:include page="../common/css.jsp" />
	
	<!--  ///////////////////////// JavaScript ////////////////////////// -->

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
	
	<!-- 오토컴플릿 -->
	  <style>
	  .custom-combobox {
	    position: relative;
	    display: block;
	    width : 500px;
	  }
	  .custom-combobox-toggle {
	    position: absolute;
	    top: 0;
	    bottom: 0;
	    margin-left: -1px;
	    padding: 0;
	  }
	  .custom-combobox-input {
	    margin: 0;
	    padding: 5px 10px;
	  }
	  
	    .ui-autocomplete {
	    max-height: 200px;
	    overflow-y: auto;
	    /* prevent horizontal scrollbar */
	    overflow-x: hidden;
  	  }


		* html .ui-autocomplete {
	    height: 200px;
	  }
  	</style>
  	<script src="../js/autocomplete.js"></script>
	<!-- end 오토컴플릿 -->
	
	
	<!--  이메일 선택 -->
	<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>-->

	<script type="text/javascript">
	
	//좋아하는 견종
	$(function () {

	
		$.ajax({
			url : "/dogBreedDic/json/getAllBreedInfoListByKo",
			method : "GET",
			datatype : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function (data) {
							
				//console.log(data)
				//console.log(data.allDogBreedInfo[2].dogNo)
				for(var i = 0; i<data.allDogBreedInfo.length; i++){
					$("#combobox").append($('<option value='+data.allDogBreedInfo[i].dogNo+'>'+data.allDogBreedInfo[i].dogBreedKO+'</option>'));
					
				}
			}
			
		}); //end of ajax	
	
	});
	
	//로딩시 제이쿼리 시작
	$( function() {

		$("#userId").focus();
		
		//아이디 중복검사 
		$("input[name=userId]").on("keyup", function(){
			
			// 잆력 값 저장
	       	var userId = $("input[name=userId]").val();
			
			 // 입력값 길이 저장
	        var textlength = userId.length;
			 
	        if(textlength < 5 ){
                $(".changeSpanId").html('아이디는 4자리 이상 입력해주세요').css('color','red');
                return false;
        	}
	        
	        if(textlength > 9){
                $(".changeSpanId").html('아이디는 8자리 이하로 입력해주세요').css('color','red');
                // 제한 글자 길이만큼 값 재 저장
                $("input[name=userId]").val(userId.substr(0,8));
                return false;
	        }
			
			
			if(userId == ""){
				$("#userId").focus();
			}else{
				$.ajax(
						{
							url : "/user/json/checkUserId/"+userId,
							method : "GET",
							dataType : "json",
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
						success : function(JSONData , status){
							var check = JSONData;
							if(check){
								$(".changeSpanId").html("사용 가능한 아이디입니다.").css('color','blue');
							}else{
								$(".changeSpanId").html("중복 아이디입니다.").css('color','red');
							}
						}
					});
			}//end 아이디 중복 검사
		});
		
		
		//비밀번호 확인
		$("#passwordCheck").on("keyup",function(){ 
			
			
			var pw=$("input[name='password']").val();
			var pwCheck=$("input[name='passwordCheck']").val();
			
			if(pw!=pwCheck){
				$(".changeSpanPW").html("비밀번호가 일치하지 않습니다.").css('color','red');
			}else{
				
				$(".changeSpanPW").html("비밀번호 일치").css('color','blue');
			}		
			
		});//end 비밀번호 확인
		
		
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
		
		//이메일 인증 checkEmail
		
		$("#checkEmail").on("click",function(){ 
			
			var email1 = $("").val;
			var email2 = $("").val;
			
			
			$.ajax(
					{
						url : "/user/json/checkEmail",
						method : "post",
						dataType : "json",
						data: JSON.stringify({
							email1 : email1,
							email2 : email2,
						}),
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
					success : function(JSONData , status){
						
						//이메일 발송 >>링크 클릭시 인증으로 됨
						var check = JSONData;
						if(check){
							$(".changeSpanId").html("사용 가능한 아이디입니다.").css('color','blue');
						}else{
							$(".changeSpanId").html("중복 아이디입니다.").css('color','red');
						}
					}
				});

			
		});//end 이메일 인증 checkEmail

		//도로명 주소  우편번호 검색 클릭시
		$("#searchPost").on("click" , function() {
			var pop = window.open("http://localhost:8080/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		});
						
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			//alert(roadFullAddr);
			$("#postNo").val(zipNo);
			$("#address1").val(roadAddrPart1);
			$("#address2").val(addrDetail);

		}	
		window.jusoCallBack = jusoCallBack;
		
		
		//회원가입 연결
		$("#join").on("click" , function() {
			 
 			alert(2222);
			//가입으로 넘기기전 체크사항   email
			///1.이메일 입력 :: 직접 입력 선택시 option value값이 1=> emailText 입력값으로 , @ 더해주기
 			if($("#email2").val()=='1' ){
 				var email2= '@'+$("#emailText").val();
 				$("#email2 option:selected").val(email2);
 			}else{
 				
 				var email2= '@'+$("#email2").val();
 				$("#email2 option:selected").val(email2);
 				alert(email2+"email2");
 			}
			
			///2.좋아하는 견종 :: option value값이 String => 도메인이 int 이므로 바꿔서
 			var dogNo = $("#combobox").val()*1;
 			$("#combobox option:selected").val(dogNo);
 			
						
			addUser();
			
			
		}); 
		
	});//end 제이쿼리
	
	//가입시 유효성 체크
	function addUser() {
	
		var id=$("input[name='userId']").val();
		var pw=$("input[name='password']").val();
		var pwCheck=$("input[name='passwordCheck']").val();
		var name=$("input[name='userName']").val();
		
		
		if(id == null || id.length <1){
			alert("아이디는 반드시 입력하셔야 합니다.");
			return;
		}
	
		if(pw == null || pw.length <1){
			alert("비밀번호는  반드시 입력하셔야 합니다.");
			return;
		}
		
		if(pwCheck == null || pwCheck.length <1){
			alert("비밀번호확인은  반드시 입력하셔야 합니다.");
			return;
		}
		if(name == null || name.length <1){
			alert("이름은  반드시 입력하셔야 합니다.");
			return;
		}			 
		
		if( pw != pwCheck ) {				
			alert("비밀번호 확인이 일치하지 않습니다.");
			$("input:text[name='password2']").focus();
			return;
		}
		
		/*이메일 합치기
		var value = "";	
		if( $("input:text[name='phone2']").val() != ""  &&  $("input:text[name='phone3']").val() != "") {
			var value = $("option:selected").val() + "-" 
								+ $("input[name='phone2']").val() + "-" 
								+ $("input[name='phone3']").val();
		}
	
		$("input:hidden[name='phone']").val( value );
		*/
		$("form").attr("method","POST").attr("action","/user/addUser").submit();
	
	}//end addUser()

	</script>	
	
</head>

<body>
		<jsp:include page="../layout/common-header.jsp" />
		
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/join_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/join_tit.png"></h1>
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
      <!-- content section -->
        <section>
            <div class="container clearfix"><!-- container1 -->
                <div class="row margin-five no-margin-top"><!-- row1 -->
                    <div class="col-md-6 col-sm-12 center-col sm-margin-bottom-seven">
                        <p class="black-text font-weight-600 text-uppercase text-large">가입 정보</p>
                        
				<!-- form  -->
				<form>
					
					<div class="form-group">
				        <label for="textbox" class="text-uppercase">아이디 :</label>
				        <input type="text" id="userId" name="userId" class="input-round big-input" placeholder="아이디는 공백없이  4자 이상 ~8자 이하">
				          <span class="changeSpanId"></span>
				    </div>
					
				    <div class="form-group">
				        <label for="textbox" class="text-uppercase">비밀번호 :</label>
				        <input type="password" id="" name="password" class="input-round big-input">
				    </div>
				
				    <div class="form-group">
				        <label for="errortextbox" class="text-uppercase">비밀번호 확인 :</label>
				        <input type="password" id="passwordCheck" name="passwordCheck" class="input-round big-input" >
				        <span class="changeSpanPW"></span>
				    </div>
				    
				    <div class="form-group">
				        <label for="errortextbox" class="text-uppercase">이름 :</label>
				        <input type="text" id="" name="userName" class="input-round big-input">
				    </div>
				                   
				   	<div class="form-group">
				        <label for="errortextbox" class="text-uppercase">생년월일 :</label>
				        <input type="text" id="datepicker" name="birth" class="input-round big-input">
				    </div>     
				              
					<div class="form-group">
				        <label for="errortextbox" class="text-uppercase">휴대전화 :</label>
				        <input type="text" id="" name="phone" class="input-round big-input" placeholder="'-'제외하고 숫자만 입력 " >
				    </div>
				    
				    <div class="form-group">
				    	<p>이메일 :     <a class="pull-right" id="checkEmail">인증하기</a></p>  
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
				        
			         </div> 
				    
				    <div class="form-group">
				        <label for="errortextbox" class="text-uppercase">주소 :</label>
				        <div>
				        	<input type="text" id="postNo" name="postNo" class="input-round big-input col-md-4" >
				        	<input type="button" id="searchPost" value="우편번호검색" style ="width:200px" class="pull-right">
				        </div>
				        <input type="text" id="address1" name="address1" class="input-round big-input">
				        <input type="text" id="address2" name="address2" class="input-round big-input">
				    </div>
				    
				    <div class="form-group">
					    <div class="ui-widget">
						  <label>좋아하는 견종: </label><span style="color:gray">*입력 또는 선택이 가능합니다.</span>
						  <select id="combobox" name="dogNo">
						    <option value="">Select one...</option>
						  </select>
						</div>
					</div>
			                    
			    </form>
			    <!-- end form  -->  
        
		         <div class="text-center">
                          <button class="highlight-button-dark btn no-margin post-search" id="join" type="submit">회원가입하기</button>
                 </div>
			             
            </div>
        </div><!-- row1 -->
    </div><!-- container1 -->
    
</section>
<!-- end content section -->
	    
        
  <jsp:include page="../common/js.jsp" />

	<jsp:include page="../layout/footer.jsp" />
</body>
</html>