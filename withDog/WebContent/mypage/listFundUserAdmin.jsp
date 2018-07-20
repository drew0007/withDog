<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" 	content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	
<title>회원별 후원내역</title>
	  
  <script type="text/javascript">
function fncGetList(currentPage) {
 	$("#currentPage").val(currentPage)
	$("form").attr("method","POST").attr("action","/fund/getFundUserListAdmin").submit();
}
</script>

</head>

<body>
	
	<jsp:include page="/layout/common-header.jsp" />
     
     
     <!-- head section -->
	<section class="page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/304_bg.jpg" alt="" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 text-center animated fadeInUp">
				<div class="no-margin-top margin-one"></div>
				<!-- page title -->
				<h1 class="white-text tit_png">
					<img src="../images/sub/myPage_tit.png">
				</h1>
				<!-- end page title -->
			</div>
		</div>
	</div>
	</section>
	<!-- end head section -->
	
	
	<section class="wow fadeIn">
	<div class="container">
	<div class="row">
		
			<!-- sidebar  -->
			<div class="col-md-2 col-sm-3 sidebar">
				<jsp:include page="/layout/admin-sideBar.jsp" />
			</div>
			<!-- end sidebar  -->
			
			
		    
		    
		    <!-- content -->
		    <div class="col-md-9 col-sm-9 col-md-offset-1">
		    
			    <h1 align="center">회원별후원내역</h1>
			    <hr/>
		    
			   <form>
			    
				  <div class="form-group col-md-3">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>회원ID</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>펀딩제목</option>
					</select>
				  </div>
				  
				  <div class="form-group col-md-3">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="tags" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }" onkeypress="if(event.keyCode == 13){ javascript:fncGetList('1')};" >
				  </div>
				  
				  <button type="button" class="highlight-button btn-medium button margin-five">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>  
				
				<hr/>
		
				<!--  table Start /////////////////////////////////////-->
			      <table class="table table-hover table-striped" >
			      
			        <thead>
			          <tr>
			            <td align="center">회원ID</td>
			            <td align="center">후원날짜</td>
			            <td align="center">펀딩제목</td>
			            <td align="center">결제금액</td>
			            <td align="center">포인트</td>
			         </tr>
			        </thead>
			       
					<tbody>
					
					  
					  <c:forEach var="list" items="${list}">
						<tr>
						  <td align="center" style="white-space: nowrap;">${list.user.userId}</td>
						  <td align="center" style="white-space: nowrap;">${list.fundMyDate}</td>
						  <td align="center" style="white-space: nowrap;">${list.fundTitle}</td>
						  <td align="center" style="white-space: nowrap;">${list.fundMyPrice}원</td>
						  <td align="center" style="white-space: nowrap;">${list.point.usePoint} Point 사용</td>
						  			 
						</tr>
			          </c:forEach>
			        
			        </tbody>
			      
			      </table>
				  <!--  table End /////////////////////////////////////-->
	  
	  
				<div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp" align="center">
					<!-- pagination -->
					<jsp:include page="../common/pageNavigator_new.jsp"></jsp:include>
					<!-- end pagination -->
				</div> 
				
		
			</div>
			<!-- end content  -->
	
	</div>
	</div>
	</section>	
     
     <jsp:include page="../layout/footer.jsp" />

	<jsp:include page="../common/js.jsp" />
	    
	
</body>

</html>