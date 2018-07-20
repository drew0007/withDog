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
			
			var userId = $(this).children().find($('input[type="hidden"]')).val();
		
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
													+"생년월일 : "+JSONData.birth+"<br/>"
													+"휴대폰 : "+JSONData.phone+"<br/>"
													+"현재 포인트 : "+JSONData.currentPoint+"<br/>"
													+"</h6>";
						$("h6").remove();
						$( "#"+userId+"" ).html(displayValue);
					}
		 	});
		});
		
		
		//이름 클릭시 상세 정보 화면으로
		$("td:nth-child(3)").on("click" , function() {
			
			var userId =$(this).text();
			alert(userId);
			
			self.location = "/user/getUserAdmin?userId="+userId;
			
		});
			
		
		//검색버튼 이벤트
		 $( "#search" ).on("click" , function() {
			 fncGetList(1);
		});
		
		//휴면회원 설정
		 $( "#updateUserCon" ).on("click" , function() {
			 
			self.location = "/user/updateUserList";
						 
		});
		
	}); //제이쿼리 끝
		
	
		
	//하단 페이지 클릭시
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method", "POST").attr("action", "/user/getUserListAdmin").submit();
	}
	
</script>	


</head>
<body>
        
        <!-- content section -->
       <section class="wow fadeIn">
            <div class="container clearfix"><!-- container1 -->
				<a class="highlight-button-black-border btn btn-medium pull-left" href="#" id ="updateUserCon">휴면 회원 설정</a>
	    		<div class="row col-md-12">
	    			<h3>회원관리리스트</h3>
		   			 <div class="col-md-6" style="margin-top:50px">
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
									<option value="00"  ${ ! empty search.searchCondition && search.searchCondition==00 ? "selected" : "" }>계정상태 :휴면</option>
									<option value="11"  ${ ! empty search.searchCondition && search.searchCondition==11 ? "selected" : "" }>계정상태 :정상</option>
									<option value="22"  ${ ! empty search.searchCondition && search.searchCondition==22 ? "selected" : "" }>계정상태 :탈퇴</option>
								</select>
						  	</div>
						  
						 	<div class="form-group">
						    	<label class="sr-only" for="searchKeyword">검색어</label>
						    	<input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
						    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
						  	
							  	<input type="button" class="highlight-button-dark btn no-margin post-search" id="search" value="검색"/>
							  
								<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
								<input type="hidden" id="currentPage" name="currentPage" value=""/>
							</div>
						</form>
	    			</div>
	    		</div>	
	    	
		</div>
                <!-- 하단 -->
        <div class="container clearfix">      
                <div class="row">
                   
                    <div class="col-md-12 col-sm-12 no-margin-bottom table-scroll">
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
											  
											  <td align="left" value="${user.userId}">${user.userId}</td>
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
           
            
            	<jsp:include page="/common/pageNavigator_new.jsp"/>
             </div><!-- container1 -->
        </section>
        <!-- end content section -->
		

</body>
</html>