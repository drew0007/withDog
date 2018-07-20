<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

	<title>로그인</title>
	<jsp:include page="../common/css.jsp" />
	<!--  ///////////////////////// JavaScript ////////////////////////// -->

	<!-- 제이쿼리 달력  -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	
	<script type="text/javascript">
	
	///////견종////////
	$(function () {
		
		//좋아하는 견종 셀렉박스
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
		
		
		//좋아하는 견종 dogNo => dogBreedKO
		var dogNum  = ${user.dogNo};
		$.ajax(
			{
			url : "/dogBreedDic/json/getDogBreed2",
			method : "post",
			dataType : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			data : JSON.stringify({
				dogNo : dogNum
			}),
			success : function(JSONData , status) {
				
				var displayValue = JSONData.key.dogBreedKO;
				$( "#dogName" ).text(displayValue);
			}
		
		});// end of ajax
	
	});
	
	//로딩시 제이쿼리 시작
	$(function () {
				
				//도로명 주소  우편번호 검색 클릭시
				$("#searchPost").on("click" , function() {
					alert(1111);
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
				
				//수정 넘기기전 유효성 체크
				updateUser();
				
				//회원정보 수정 이벤트
				$("#change").on("click" , function() {
					
					$("form").attr("method","POST").attr("action","/user/updateUser").submit();
					
				});
				
				
		});//end 제이쿼리
		
	function updateUser() {
		
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
			
	};

	</script>	
	
</head>

<body>

	<jsp:include page="../layout/header2.jsp" /> 
	
	<!-- head section -->
	 <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	   <img class="parallax-background-img" src="../images/sub/300_bg.jpg" alt="" />
	   <div class="container">
	       <div class="row">
	           <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
	                <h1 class="white-text">회원정보 수정</h1>
	               <span class="white-text xs-display-none">Register and modify user information.</span>
	            </div>
	        </div>
	    </div>
	</section> 
	<!-- end head section -->	
	
        <!-- form option #2 -->  
        <section class="wow fadeIn" >
            <div class="container">
                <div class="row">
                	<div class="col-md-2 col-sm-3 sidebar">
						<jsp:include page="/layout/mypage-sideBar.jsp" />
					</div>
                    <div class="col-md-6 col-sm-10" style="margin-top:-50px">
                    	<h3 style="margin-bottom:20px">회원정보수정</h3>
                        <form>
                        	
                        	<div class="form-group">
                                <!-- label -->
                                <label for="textbox" class="text-uppercase">아이디</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="userId" class="input-round big-input" value="${user.userId}" readonly>
                                <!-- end input -->
                            </div>
                        	
                            <div class="form-group">
                                <!-- label -->
                                <label for="textbox" class="text-uppercase">이름</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="userName" class="input-round big-input" value="${user.userName}" readonly>
                                <!-- end input -->
                            </div>

                            <div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">생년월일</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="datepicker" name="birth" required class="input-round big-input" value="${user.birth}">
                                <!-- end input -->
                            </div>
                            
                            <div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">이메일</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="email1" required class="input-round big-input" value="${user.email1}" >
                                <!-- end input -->
                            </div>
                            
  							<div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">전화번호</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="phone" required class="input-round big-input" value="${user.phone}" >
                                <!-- end input -->
                            </div>
                            
                            <div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">주소</label>
                                <!-- end label -->
                                <!-- input -->
                                <div>
                                	<input type="text" id="postNo" name="postNo" required class="input-round big-input col-md-4 " value="${user.postNo}" >
                                	<input type="button" class="col-md-4 pull-right" id="searchPost" value="우편번호검색">
                                </div>
                                <input type="text" id="address1" name="address1" required class="input-round big-input" value="${user.address1}" >
                                <input type="text" id="address2" name="address2" required class="input-round big-input" value="${user.address2}" >
                                <!-- end input -->
                            </div>
                            
	            		    <div class="form-group">
							    <div class="ui-widget">
								  <label>좋아하는 견종: </label><span style="color:gray ; font-size:10px">**입력 또는 선택이 가능합니다.</span>
								  <select id="combobox" name="dogNo">
								    <option value="">Select one...</option>
								  </select>
								</div>
							</div>
                            
                            <div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">가입일</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="joinDate" required class="input-round big-input" value="${user.joinDate}" readonly>
                                <!-- end input -->
                            </div>                      
                            
         					<div class="form-group">
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">현재포인트</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="" name="currentPoint" required class="input-round big-input" value="${user.currentPoint}" readonly>
                                <!-- end input -->
                            </div> 
  
                            <div class="form-group col-md-12">
                                <!-- label -->
                                <label for="errortextbox" class="col-md-4">sns_Naver_Id</label>
                                <!-- end label -->
                                <!-- input -->
                                <div class="col-md-4">
                                	<input type="text" id="" name="snsNaverId" class="input-round big-input" value="${user.snsNaverId}" readonly>
                                </div>
                                 <div class="col-md-4">
                                 	<button class="btn btn-black no-margin-bottom btn-medium btn-round no-margin-top" id="changeNId">연동</button>
                                </div>
                                <!-- end input -->
                            </div> 
                            
                            <div class="form-group col-md-12">
                                <!-- label -->
                                <label for="errortextbox" class="col-md-4">sns_Kakao_Id</label>
                                <!-- end label -->
                                <!-- input -->
                                 <div class="col-md-4">
                                	<input type="text" id="" name="snsNaverId" class="input-round big-input" value="${user.snsKakaoId}" readonly >
                                </div>
                                 <div class="col-md-4">
                                 	<button class="btn btn-black no-margin-bottom btn-medium btn-round no-margin-top"  id="changeKId">연동</button>
                                </div>
                                 <!-- end input -->
                            </div> 
                            
                            <div class="form-group col-md-12">
                                <!-- label -->
                                 <label for="errortextbox" class="col-md-4">sns_Google_Id</label>
                                <!-- end label -->
                                <!-- input -->
                                <div class="col-md-4">
                                	<input type="text" id="" name="snsNaverId" class="input-round big-input" value="${user.snsGoogleId}" readonly>
                                </div>
                                 <div class="col-md-4">
                                 	<button class="btn btn-black no-margin-bottom btn-medium btn-round no-margin-top" id="changeGId">연동</button>
                                </div>
                                 <!-- end input -->
                            </div> 
                            
                            <div class="form-group col-md-12">
                                <!-- label -->
                                 <label for="errortextbox" class="col-md-4">sns_Facebook_Id</label>
                                <!-- end label -->
                                <!-- input -->
                                <div class="col-md-4">
                                	<input type="text" id="" name="snsNaverId" class="input-round big-input" value="${user.snsFacebookId}" readonly>
                                </div>
                                 <div class="col-md-4">
                                 	<button class="btn btn-black no-margin-bottom btn-medium btn-round no-margin-top" id="changeFId">연동</button>
                                </div>
                                 <!-- end input -->
                            </div> 
                    
                            
                            <c:if test="${sessionScope.user.role=='admin'}"> 
                            
	                             <div class="form-group">
		                    		 <label for="errortextbox" class="text-uppercase">계정상태</label>
		                    		  <input type="text" id="" name="userCondition" required class="input-round big-input" value="${user.userCondition}" >
	                             </div> 
                            
	                            <div class="form-group">
	                                <!-- label -->
	                                <label for="errortextbox" class="text-uppercase">최근접속일</label>
	                                <!-- end label -->
	                                <!-- input -->
	                                <input type="text" id="" name="recentlyDate" required class="input-round big-input" value="${user.recentlyDate}" >
	                                <!-- end input -->
	                            </div> 
                            	
      							<div class="form-group">
	                                <!-- label -->
	                                <label for="errortextbox" class="text-uppercase">탈퇴일</label>
	                                <!-- end label -->
	                                <!-- input -->
	                                <input type="text" id="" name="leaveDate" required class="input-round big-input" value="${user.leaveDate}" >
	                                <!-- end input -->
	                            </div> 
           
                        
	                            
	                            <div class="form-group">
	                                <!-- label -->
	                                <label for="errortextbox" class="text-uppercase">탈퇴사유</label>
	                                <!-- end label -->
	                                <!-- input -->
	                                <input type="text" id="" name="leaveReason" required class="input-round big-input" value="${user.leaveReason}" >
	                                <!-- end input -->
	                            </div> 
                            
                            	 <div class="form-group">
	                                <!-- label -->
	                                <label for="errortextbox" class="text-uppercase">권한</label>
	                                <!-- end label -->
	                                <!-- input -->
	                                <input type="text" id="" name="role" required class="input-round big-input" value="${user.role}" >
	                                <!-- end input -->
	                            </div> 
                            
                            </c:if>
                            
                        </form>
                        
                             <div class="form-group">
                                <!-- required  -->
                                <span class="required margin-three">*Please complete all fields correctly</span>
                                <!-- end required  -->
                                <!-- button  -->
                                <button class="highlight-button btn no-margin post-search" id="change">수정하기</button>
                                <!-- end button  -->
                            </div>
                        
                    </div>
                </div>
            </div>
        </section>
        <!-- end form option #2 -->   
        
        <jsp:include page="../common/js.jsp" />
        
        <jsp:include page="../layout/footer.jsp" />

</body>
</html>