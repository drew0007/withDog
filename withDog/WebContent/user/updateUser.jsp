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
	
	<script type="text/javascript">
	
	$(function () {
			
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
				
				
				//회원정보 수정 이벤트
				$("#change").on("click" , function() {
					
					$("form").attr("method","POST").attr("action","/user/updateUser").submit();
					
				});
				
				
		});//end 제이쿼리


	</script>	
	
</head>

<body>
			
	
        <!-- form option #2 -->  
        <section class="wow fadeIn" >
            <div class="container">
                <div class="row">
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
                                <input type="text" id="" name="birth" required class="input-round big-input" value="${user.birth}">
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
                                <!-- label -->
                                <label for="errortextbox" class="text-uppercase">좋아하는 견종</label>
                                <!-- end label -->
                                <!-- input -->
                                <input type="text" id="dogName" name="dogNo" required class="input-round big-input" value="${user.dogNo}" >
                                <!-- end input -->
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

</body>
</html>