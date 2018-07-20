
<%@ page pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="kr">
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 달력 -->
<script type="text/javascript" src="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.js"></script>
<link href="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.css" rel="stylesheet" type="text/css" />
<!-- 달력 -->
<jsp:include page="/common/css.jsp" />


<script type="text/javascript">
function fncGetList(currentPage) {
// 	document.getElementById("currentPage").value = currentPage;
	$("#currentPage").val(currentPage)
//    	document.detailForm.submit();		
	$("form").attr("method" , "POST").attr("action" , "/ash/getMyReservationASHList").submit();
}

$(function () {
	$("#searchButton").on("click",function () {
		$("input[name=sorting]").val('0');
		console.log("클릭")
		fncGetList(1);
	})	
});

$(function () {
	$("form").keydown(function (key) {				
		if(key.keyCode ==13){
			fncGetList(1);
	}
	});			
});

$(function () {
	$("a:contains('1개월')").on("click", function () {
		$("input[name=searchStartDay]").val('');
		$("input[name=searchEndDay]").val('');
		$("input[name=sorting]").val('1');
		fncGetList(1)
	})
	$("a:contains('3개월')").on("click", function () {
		$("input[name=searchStartDay]").val('');
		$("input[name=searchEndDay]").val('');
		$("input[name=sorting]").val('2');
		fncGetList(1)
	})
	$("a:contains('6개월')").on("click", function () {
		$("input[name=searchStartDay]").val('');
		$("input[name=searchEndDay]").val('');
		$("input[name=sorting]").val('3');
		fncGetList(1)
	})
	$("a:contains('전체보기')").on("click", function () {
		$("input[name=searchStartDay]").val('');
		$("input[name=searchEndDay]").val('');
		$("input[name=sorting]").val('4');
		fncGetList(1)
	})
})
	
</script>
</head>
<body>
<jsp:include page="/layout/header.jsp" />
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/304_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/myPage_tit.png"></h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <!-- 서브타이틀 <span class="white-text">1234</span>-->
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        

<section class="wow fadeIn">
<div class="container">
		<div class="row">
	<div class="page-header text-info">
	       <h3>나의 예약 내역</h3>
	</div>
	<div class="row">
	  <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
    </div>

		<div class="col-md-6 text-right">
		<form class="form-inline" name="detailForm">
		<div class="widget">
		</div>
		<a type="button">1개월</a>
		<a type="button">3개월</a>
		<a type="button">6개월</a>
		<a type="button">전체보기</a>
		<br>	
		<span>
		주문일자별 조회 <input type="text" name="searchStartDay" id="startDay" value="${!empty search.searchStartDay?search.searchStartDay:''}" readonly/>
			<script type="text/javascript">
					var opts = {                            
					        formElements:{"startDay":"Y-ds-m-ds-d"},
					        statusFormat:"l-cc-sp-d-sp-F-sp-Y",
					        // Fill the grid...
					        fillGrid:true,
					        // ... and make all displayed dates selectable
					        constrainSelection:false        
					        };      
					datePickerController.createDatePicker(opts);
		 </script>
		 ~  <input type="text" name="searchEndDay" id="endDay" value="${!empty search.searchEndDay?search.searchEndDay:''}" readonly/>
		 			<script type="text/javascript">
					var opts = {                            
					        formElements:{"endDay":"Y-ds-m-ds-d"},
					        statusFormat:"l-cc-sp-d-sp-F-sp-Y",
					        // Fill the grid...
					        fillGrid:true,
					        // ... and make all displayed dates selectable
					        constrainSelection:false        
					        };      
					datePickerController.createDatePicker(opts);
		 </script>
		</span>
		<br>
<div class="form-group">
				
			</div>
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
				  <input type="hidden" id="sorting" name="sorting" value="0"/>
				<div class="form-group">
				  </div>
				  </form>
				  <a id="searchButton"  class="btn btn-default">검색</a>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  
				  
				
					    	</div>

		</div>




<table class="table table-hover table-striped" >
	<thead>
		<tr>
		<th class = "text-center">번호</th>
		<th class = "text-center">결제일</th>
		<th class = "text-center">예약번호</th>
		<th class = "text-center">치유견</th>
		<th class = "text-center">치유사</th>	
		<th class = "text-center">예약일</th>	
		<th class = "text-center">예약상태</th>	
		<th class = "text-center">금액</th>	
	</tr>
	</thead>
	
<tbody>
	<c:set var="i" value="0"/>
	<c:forEach var="list" items="${list}">
	<c:set var="i" value="${i+1}"/>
	<tr>
		<td align="center">${i}</td>
		<td align="center">${list.purchaseDate}</td>
		<td align="center">${list.ashReservationNo}</td>
		<td align="center">${list.healingDog.healingDogName}</td>
		<td align="center">${list.healingDog.healingDogHealer}</td>
		<td align="center">${list.ashReservationDate}  <c:if test="${list.ashReservationTime==0}">오전</c:if><c:if test="${list.ashReservationTime==1}">오후</c:if> </td>
		<td align="center">
			<c:if test="${list.ashReservationCondition==0}">예약완료</c:if>
			<c:if test="${list.ashReservationCondition==1}">출장확정</c:if>
			<c:if test="${list.ashReservationCondition==2}">출장완료</c:if>
			<c:if test="${list.ashReservationCondition==3}">예약취소</c:if>
		</td>
		<td align="center">${list.ashReservationPrice}</td>
		<td align="center">
		 </td>	
		<td align="center">
		<a href="/ash/getMyReservationASH/${list.ashReservationNo}">예약정보확인</a>
		</td>
	</tr>

</c:forEach>	
	</tbody>
</table>
		 <jsp:include page="../common/pageNavigator_new.jsp"></jsp:include>
		
<!--  페이지 Navigator 끝 -->
</div>
</section>
        
               
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>


</html>