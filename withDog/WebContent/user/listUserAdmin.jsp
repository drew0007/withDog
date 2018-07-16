<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="../common/css.jsp" />
<title>회원관리 리스트 </title>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

	$( function() {
		
		
		//간략한 정보 보기 클리
		$(  "td:nth-child(6)" ).on("click" , function() {
			alert(111111);
			var parents = $(this).parents();
			var userId = $(this).val();
			alert(parents);
			$.ajax({
					url : "/user/json/getUser/"+userId ,
					method : "GET" ,
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(JSONData , status) {

						var displayValue = "<h6>"
													+"아이디 : "+JSONData.userId+"<br/>"
													+"이  름 : "+JSONData.userName+"<br/>"
													+"이메일 : "+JSONData.email+"<br/>"
													+"ROLE : "+JSONData.role+"<br/>"
													+"휴대폰 : "+JSONData.regDate+"<br/>"
													+"</h6>";
						$("h6").remove();
						$( "#"+userId+"" ).html(displayValue);
					}
		 	});
		});
		
		
		//이름 클릭시 상세 정보 화면으로
		$("#userId").on("click" , function() {
			var userId = $(this).text();
			self.location = "/user/getUserAdmin?userId="+userId;
			
		});
			
			
	}); //제이쿼리
		
	

		
	
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method", "POST").attr("action", "/user/getUserListAdmin").submit();
	}
	
</script>	


</head>
<body>
		<jsp:include page="../layout/header.jsp" /> 
		
		<!-- head section -->
        	<section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/603_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <h1 class="white-text">회원관리 리스트</h1>
                        <span class="white-text xs-display-none">List User</span>
                    </div>
                </div>
            </div>
        </section> 
        <!-- end head section -->
        
        <!-- content section -->
       <section class="wow fadeIn border-top">
            <div class="container clearfix"><!-- container1 -->
            	 <!-- 상단 -->
                <div class="row  no-margin-top"><!-- row1 -->
                
                     <div class="col-md-7 col-sm-10 center-col text-center margin-ten no-margin-top xs-margin-bottom-seven">
                        <h3 class="no-margin-top margin-ten xs-margin-bottom-seven"><strong class="black-text">회원관리리스트</strong></h3>
                    </div>
                    
                </div>
                
	    		<div class="row col-md-12">
		   			 <div class="col-md-6">
		    			<p class="text-primary">
		    				전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    			</p>
		    		</div>	
		    		
		   			<div class="col-md-6">
			   			<form class="form-inline">
						  	<div class="form-group">
						    	<select class="form-control" name="searchCondition" >
									<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>회원ID</option>
									<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>회원명</option>
								</select>
						  	</div>
						  
						 	<div class="form-group">
						    	<label class="sr-only" for="searchKeyword">검색어</label>
						    	<input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
						    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
						  	</div>
						  
						  	<button class="highlight-button-dark btn no-margin post-search" id="join" type="submit">검색</button>
						  
							<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
							<input type="hidden" id="currentPage" name="currentPage" value=""/>
						</form>
	    			</div>
	    		</div>	
	    	
		</div>
                <!-- 하단 -->
                <div class="row">
                   
                    <div class="col-md-8 col-sm-12 center-col no-margin-bottom table-scroll">
                        <table class="table table-striped">
                           
                            <thead>
                                <tr>
                                	<th align="center">No</th>
                                	<th align="left">계정상태</th>
            						<th align="left" >회원 ID</th>
            						<th align="left">회원명</th>
           							<th align="left">최근접속일</th>
            						<th align="left">간략정보</th>
                                </tr>
                            </thead>
                           
                            <tbody>
                               <c:set var="i" value="0" />
		  							<c:forEach var="user" items="${list}">
										<c:set var="i" value="${ i+1 }" />
											<tr>
											  <td align="center">${ i }</td>
											  
											  <td align="left">
												  <c:choose>
														<c:when test="${user.userCondition=='0'}">휴면 </c:when>
														 <c:when test="${user.userCondition=='2'}">탈퇴 </c:when>
												  		<c:otherwise>정상</c:otherwise>	
												</c:choose>
											  </td>
											  
											  <td align="left" id="userId">${user.userId}</td>
											  <td align="left">${user.userName}</td>
											  <td align="left">${user.recentlyDate}</td>
											  <td align="left"> <i class="fa fa-fw" id="${user.userId}" >
											  	<input type="hidden" value="${user.userId}"></i>
											  </td>
											</tr>
								     </c:forEach>
							</tbody>
						</table>
                    </div>
                
                </div><!-- row1 -->
            </div><!-- container1 -->
            
            <jsp:include page="/common/pageNavigator_new.jsp"/>
            
        </section>
        <!-- end content section -->
		
		<jsp:include page="../layout/footer.jsp" /> 
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>