<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<jsp:include page="../common/css.jsp" />
	<title>회원정보조회</title>
	
	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
			
		$(function () {
			//좋아하는 견종 dogNo => dogBreedKO
			var dogNum  = "${user.dogNo}";
			
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
			
			//회원정보 수정화면  연결
			$("#changeGo").on("click" , function() {
				
				var role ="${sessionScope.user.role}";
				var userId ="${user.userId}";
				
				
				if(role=='user'){
					self.location = "/user/updateUser";
				}else{
					self.location = "/user/updateUserAdmin?userId="+userId;	
				}
			});
			
				
	
		});//end 제이쿼리
	</script>
	
</head>

<body>

	<%-- <jsp:include page="../layout/header.jsp" /> --%>
	<!-- head section -->
	<!-- <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	   <img class="parallax-background-img" src="../images/sub/300_bg.jpg" alt="" />
	   <div class="container">
	       <div class="row">
	           <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
	               page title
	                <h1 class="white-text">User Information</h1>
	               end page title
	               page title tagline
	               <span class="white-text xs-display-none">Register and modify user information.</span>
	               end title tagline
	            </div>
	        </div>
	    </div>
	</section> -->
	<!-- end head section -->
  
	<!-- content section -->
	<section>
		<div class="container clearfix"><!-- container1 -->
			<div class="container">
				<h2 style="margin-bottom:20px">회원정보</h2>
				<hr/>
				<div class="row">
				  		<div class="col-xs-4 col-md-2 "><strong>아이디</strong></div>
						<div class="col-xs-8 col-md-4">${user.userId}</div>
				</div>
				<hr/>
				
				<div class="row">
				  		<div class="col-xs-4 col-md-2 "><strong>이 름</strong></div>
						<div class="col-xs-8 col-md-4">${user.userName}</div>
				</div>
			
				<hr/>
			
				<div class="row">
					<div class="col-xs-4 col-md-2 "><strong>생년월일</strong></div>
					<div class="col-xs-8 col-md-4">${user.birth}</div>
				</div>
			
				<hr/>
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2 "><strong>이메일</strong></div>
					<div class="col-xs-8 col-md-4">${user.email1}</div>
					<div class="col-xs-8 col-md-4">${user.email2}</div>
				</div>
			
				<hr/>
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2 "><strong>전화번호</strong></div>
					<div class="col-xs-8 col-md-4">${user.phone}</div>
				</div>
			
				<hr/>
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2 "><strong>주소</strong></div>
			 		<div class="col-xs-2 col-md-2">${user.postNo}</div>
					<div class="col-xs-2 col-md-2">${user.address1}</div>
					<div class="col-xs-2 col-md-2">${user.address2}</div>
				</div>
			
				<hr/>
			
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>좋아하는 견종</strong></div>
					<div class="col-xs-8 col-md-4" id="dogName">${user.dogNo}</div>
				</div>
			
				<hr/>
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>가입일</strong></div>
					<div class="col-xs-8 col-md-4">${user.joinDate}</div>
				</div>
			
				<hr/>
				
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>현재포인트</strong></div>
					<div class="col-xs-8 col-md-4">${user.currentPoint}point</div>
				</div>
			
				<hr/>
				
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>snsNaverId</strong></div>
					<div class="col-xs-8 col-md-4">${user.snsNaverId}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>snsKakaoId</strong></div>
					<div class="col-xs-8 col-md-4">${user.snsKakaoId}</div>
				</div>
				
				<hr/>
			
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>snsGoogleId</strong></div>
					<div class="col-xs-8 col-md-4">${user.snsGoogleId}</div>
				</div>
				
				<hr/>
				
				<div class="row">
			 		<div class="col-xs-4 col-md-2"><strong>snsFacebookId</strong></div>
					<div class="col-xs-8 col-md-4">${user.snsFacebookId}</div>
				</div>
				
				<hr/>
			 
				<c:if test="${sessionScope.user.role=='admin'}"> 
					<div class="row">
				 		<div class="col-xs-4 col-md-2"><strong>계정상태</strong></div>
				 		<div class="col-xs-8 col-md-4" >
					 		 <c:choose>
								<c:when test="${user.userCondition=='0'}">휴면 </c:when>
								<c:when test="${user.userCondition=='2'}">탈퇴 </c:when>
						  		<c:otherwise>정상</c:otherwise>	
						  	</c:choose>
						</div>
					</div>
					<hr/>
					<div class="row">
				 		<div class="col-xs-4 col-md-2"><strong>최근접속일</strong></div>
						<div class="col-xs-8 col-md-4" >${user.recentlyDate}</div>
					</div>
				
					<hr/>
					
					<div class="row">
				 		<div class="col-xs-4 col-md-2"><strong>탈퇴일</strong></div>
						<div class="col-xs-8 col-md-4">${user.leaveDate}</div>
					</div>
				
					<hr/>
					
					<div class="row">
						<div class="col-xs-4 col-md-2"><strong>탈퇴사유</strong></div>
				  
				    	<div class="col-xs-8 col-md-4">
				    	  <c:choose>
							<c:when test="${user.leaveReason=='1'}">동물교감치유 서비스 불만 </c:when>
							<c:when test="${user.leaveReason=='2'}">이용빈도 낮음 </c:when>
							<c:when test="${user.leaveReason=='3'}">개인정보유출 우려 </c:when>
							<c:when test="${user.leaveReason=='4'}">스토어상품 배송 지연 </c:when>
					  		<c:otherwise></c:otherwise>	
					  	  </c:choose>
				  		</div>
					
					</div>
									
					<hr/>
					
					<div class="row">
				 		<div class="col-xs-4 col-md-2"><strong>권한</strong></div>
						<div class="col-xs-8 col-md-4">${user.role}</div>
					</div>
				
					<hr/>
				
				
				</c:if>
				
				<div class="text-center">
					<button class="highlight-button btn no-margin post-search pull-left" id="changeGo">회원정보수정</button>
				</div>
			
			</div>
		</div><!-- container1 -->
		

		
		
	</section>
	<!-- end content section -->
        
	<%-- <jsp:include page="../layout/footer.jsp" /> --%>
	<jsp:include page="../common/js.jsp" />

</body>
</html>