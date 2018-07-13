<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	  
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
  
  <script type="text/javascript">

function fncGetList(currentPage) {
 	$("#currentPage").val(currentPage)
	$("form").attr("method","POST").attr("action","/common/getMyPointList").submit();
}


</script>
<!-- 숫자증가 스크립트 -->
  <script>
  jQuery(document).ready(function( $ ) {
        $('.counter').counterUp({
            delay: 10,
            time: 1000
        });
    });
  </script>
  <!--  -->
   
</head>

<body>
	<form>
	 <%-- <input type="text" placeholder="Search..." class="search-input" name="search" value="${! empty search.searchKeyword ? search.searchKeyword : '' }" > --%>
     <input type="hidden" id="currentPage" name="currentPage" value=""/>
     </form>
	<!-- 숫자증가 스크립트 -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
    <script src="/js/jquery.counterup.min.js"></script>
    <!--end  -->
	<!-- ToolBar Start /////////////////////////////////////-->
	
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<!-- <div class="container" style="width: 921px;"> -->
	<div class="container" style="width: 921px;">
	
		
	       <h1 align="center">나의포인트내역</h1>
	    <hr/>
	    <div >
	       <h2 align="center">현재포인트 : <div class="counter" style="display:inline;"> ${currentPoint}</div> Point</h2>
	    </div>
	    <hr/>
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	  <%--   <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>회원ID</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>회원명</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="tags" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div> --%>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <td align="center">날짜</td>
            <td align="center">내역</td>
            <td align="center">포인트</td>
          </tr>
        </thead>
       
		<tbody>
		
		  
		  <c:forEach var="point" items="${resultList}">
			<tr>
			  <td align="center">${point.pointDate}</td>
			  <c:if test="${point.pointHistory=='0'}">
			  <td align="center">크라우드펀딩후원</td>
			  </c:if>
			  <c:if test="${point.pointHistory=='1'}">
			  <td align="center">동물교감치유예약</td>
			  </c:if>
			  <c:if test="${point.pointHistory=='2'}">
			  <td align="center">스토어상품구매</td>
			  </c:if>
			  <td align="center">${point.usePoint!=0? "사용포인트 : ":"적립포인트 : "} ${point.usePoint!=0? point.usePoint : point.point}</td>
			 
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
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	
	<!-- PageNavigation End... -->
	
</body>

</html>