<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	  
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
  
  <script type="text/javascript">

function fncGetList(currentPage) {
 	$("#currentPage").val(currentPage)
	$("form").attr("method","POST").attr("action","/common/getMyPointList").submit();
}


</script>
<!-- �������� ��ũ��Ʈ -->
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
	<!-- �������� ��ũ��Ʈ -->
	<script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
    <script src="/js/jquery.counterup.min.js"></script>
    <!--end  -->
	<!-- ToolBar Start /////////////////////////////////////-->
	
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<!-- <div class="container" style="width: 921px;"> -->
	<div class="container" style="width: 921px;">
	
		
	       <h1 align="center">��������Ʈ����</h1>
	    <hr/>
	    <div >
	       <h2 align="center">��������Ʈ : <div class="counter" style="display:inline;"> ${currentPoint}</div> Point</h2>
	    </div>
	    <hr/>
	    <!-- table ���� �˻� Start /////////////////////////////////////-->
	  <%--   <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>ȸ��ID</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>ȸ����</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">�˻���</label>
				    <input type="text" class="form-control" id="tags" name="searchKeyword"  placeholder="�˻���"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">�˻�</button>
				  
				  <!-- PageNavigation ���� ������ ���� ������ �κ� -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div> --%>
		<!-- table ���� �˻� Start /////////////////////////////////////-->
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <td align="center">��¥</td>
            <td align="center">����</td>
            <td align="center">����Ʈ</td>
          </tr>
        </thead>
       
		<tbody>
		
		  
		  <c:forEach var="point" items="${resultList}">
			<tr>
			  <td align="center">${point.pointDate}</td>
			  <c:if test="${point.pointHistory=='0'}">
			  <td align="center">ũ�����ݵ��Ŀ�</td>
			  </c:if>
			  <c:if test="${point.pointHistory=='1'}">
			  <td align="center">��������ġ������</td>
			  </c:if>
			  <c:if test="${point.pointHistory=='2'}">
			  <td align="center">������ǰ����</td>
			  </c:if>
			  <td align="center">${point.usePoint!=0? "�������Ʈ : ":"��������Ʈ : "} ${point.usePoint!=0? point.usePoint : point.point}</td>
			 
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
 	<!--  ȭ�鱸�� div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	
	<!-- PageNavigation End... -->
	
</body>

</html>