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
	
	});// end 제이쿼리
	
	//로딩시 제이쿼리 시작
	$( function() {

		$("#userId").focus();
		
		//아이디 중복검사 
		$("input[name=userId]").on("keyup", function(){
			
			// 잆력 값 저장
	       	var userId = $("input[name=userId]").val();
			
			 // 입력값 길이 저장
	        var textlength = userId.length;
			 
	        if(textlength < 3 ){
                $(".changeSpanId").html('아이디는 3자리 이상 입력해주세요').css('color','red');
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
							//체크값이 true이면 DB에 유저정보 없는것
							if(check){
								$(".changeSpanId").html("사용 가능한 아이디입니다.").css('color','blue');
							}else{
								$(".changeSpanId").html("중복 아이디입니다.").css('color','red');
							}
						}
					});
			}
		});//end 아이디 중복 검사
		
		
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
		


		//도로명 주소  우편번호 검색 클릭시
		$("#searchPost").on("click" , function() {
			var pop = window.open("http://localhost:8080/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		});
						
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
// 			alert(roadFullAddr);
			$("#postNo").val(zipNo);
			$("#address1").val(roadAddrPart1);
			$("#address2").val(addrDetail);

		}	
		window.jusoCallBack = jusoCallBack;
		
		
		//회원가입 연결
		$("#join").on("click" , function() {
			 
			//가입으로 넘기기전 체크사항   email
			///1.이메일 입력 :: 직접 입력 선택시 option value값이 1=> emailText 입력값으로 , @ 더해주기
 			if($("#email2").val()=='1' ){
 				
 				var email2= '@'+$("#emailText").val();
 				$("#email2 option:selected").val(email2);
 				
 			}else{
 				
 				var email2= '@'+$("#email2").val();
 				$("#email2 option:selected").val(email2);
 			}
			
			var email1 = $("#email1").val();
			var email2 = $("#email2").val();
			$("#email").val(email1+email2);
			
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
		var emailCheck= $("#tempNo").val();
		
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
		
		if( emailCheck != 100 ) {				
			alert("이메일 인증이 완료되지 않았습니다.");
			$("input:text[name='email']").focus();
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

	
	//영문 키워드
	$(document).ready(function(){

		$("#userId").css("ime-mode", "disabled");

	});
</script>	

<script type="text/javascript">
	
	//이메일 인증
	$( function() {

		
		//이메일 인증
		$("#checkEmail").on("click" , function() {
			
			 
			$.ajax({
				
				url : "/user/json/checkEmail",
				method : "POST",
				datatype : "json",
				data: JSON.stringify({
					email :$("#email").val()
				}),
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(JSONData , status){
					
					var check = JSONData.check;
					var tempNo = JSONData.tempNo;
					
					if(!check){
						$(".changeDiv1").html("해당 이메일로 인증이 불가합니다.").css('color','red').css('font-size','12px');
					}else{
						//고유번호 숨기기
						$("#tempNo").val(tempNo);
						$(".changeDiv1").html('<div class="col-md-12"><p class="col-md-4">인증코드 입력 :</p><input type="text" id="userTextNum" class="col-md-4"><button type="button" class="highlight-button col-md-4 pull-right" id="checkTextNum" >확인</button></div');
						$(".changeDiv2").html("인증코드를 입력해주세요!").css('color','blue').css('font-size','14px');	
					}

				}
			
			});//end of Ajax
						
			
			
		}); //end 이메일 인증
		
	});//end 제이쿼리
	
	//발송한 숫자와 고객이 입력한 숫자 비교
	
	$(document).on("click","#checkTextNum",function() {
		//숨겨놓은 발송 숫자
		var textNum = $("#tempNo").val();
		alert("textNum"+textNum);
		//고객이 입력한 값
		var userTextNum = $("#userTextNum").val();
		alert("userTextNum"+userTextNum);
		if(textNum!=userTextNum){
			alert("인증번호를 잘못 입력하였습니다.");
			$(".changeDiv1").remove();
			$(".changeDiv2").remove();
			
		}else{
			alert("이메일 인증이 완료되었습니다.");
			//이메일 인증완료시  
			var textNum = $("#tempNo").val(100);
			$(".changeDiv1").remove();
			$(".changeDiv2").remove();
			$("#checkEmail").val("재인증");
			$(".spanChange").append("<span style=\"color:red;\">인증완료</span>");
		}//end of if
	});
	
</script>	



	
</head>

<body>

	<jsp:include page="../layout/common-header2.jsp" />


		
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
				        <input type="text" id="userId" name="userId" class="input-round big-input" placeholder="아이디는 공백없이  3자 이상 ~8자 이하"  style="ime-mode:disabled">
				          <span class="changeSpanId"></span>
				    </div>
					
				    <div class="form-group">
				        <label for="textbox" class="text-uppercase">비밀번호 :</label>
				        <input type="password"name="password" class="input-round big-input">
				    </div>
				
				    <div class="form-group">
				        <label for="errortextbox" class="text-uppercase">비밀번호 확인 :</label>
				        <input type="password" id="passwordCheck" name="passwordCheck" class="input-round big-input" >
				        <span class="changeSpanPW"></span>
				    </div>
				    
				    <div class="form-group">
				        <label for="errortextbox" class="text-uppercase">이름 :</label>
				        <input type="text" name="userName" class="input-round big-input">
				    </div>
				                   
				   	<div class="form-group">
				        <label for="errortextbox" class="text-uppercase">생년월일 :</label>
				        <input type=text id="datepicker" name="birth" class="input-round big-input" readOnly>
				    </div>     
				              
   				    <div class="form-group">
					    <div class="ui-widget">
						  <label>좋아하는 견종: </label><span style="color:gray">*입력 또는 선택이 가능합니다.</span>
						  <select id="combobox" name="dogNo">
						    <option value="">Select one...</option>
						  </select>
						</div>
					</div>
						          
					<div class="form-group">
				        <label for="errortextbox" class="text-uppercase">휴대전화 :</label>
				        <input type="text" name="phone" class="input-round big-input" placeholder="'-'제외하고 숫자만 입력 " >
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
				    	<p ><span class="spanChange">이메일 :</span> <input type="button" class="pull-right col-md-2" id="checkEmail" value="인증하기"></p>
				    	<input type="text" class="col-md-6" id="email">
			    	  	<span class="changeDiv1"></span>
                        <span class="changeDiv2"></span>
                        <input type="hidden" id="tempNo" value=""/>
			       </div> 

			                    
			    </form>
			    <!-- end form  -->  
        
		         <div class="text-center">
                 	<input type="button" class="highlight-button-dark btn no-margin post-search" id="join" value="회원가입하기"/>
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