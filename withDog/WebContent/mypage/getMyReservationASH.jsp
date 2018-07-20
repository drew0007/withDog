
<%@ page pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="kr">
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">	
<!-- 달력 -->
<script type="text/javascript" src="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.js"></script>
<link href="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.css" rel="stylesheet" type="text/css" />
<!-- 달력 -->


<script type="text/javascript">
function fncUpdateAsh(currentPage) {
// 	document.getElementById("currentPage").value = currentPage;
	$("#currentPage").val(currentPage)
//    	document.detailForm.submit();		
	$("form").attr("method" , "POST").attr("action" , "/ash/getMyReservationASHList").submit();
}



$(function () {
	$("#cancelReservation").on("click", function () {
		if (confirm("예약을 취소하시겠습니까?") == true){    //확인
			self.location = "/ash/updateMyReservationCondition/"+${ash.ashReservationNo};
		  }else{   //취소
		      return;
		  }
	})
	
	$("#updateReservation").on("click", function () {
		if (confirm("예약을 수정하시겠습니까?") == true){    //확인
			self.location = "/ash/updateAshMyReservation/"+${ash.ashReservationNo};
		  }else{   //취소
		      return;
		  }
	})
})
</script>
</head>
<body>

<section class="wow fadeIn">
<div class="container">
<img  src = "/images/uploadFiles/healingDog/${ash.healingDog.healingDogimage}" width="300px" height="300px" alt=""/>
<table border="1px">
	<thead>
		<tr>
			<th class = "text-center">치유견이름</th>
			<th class = "text-center">${ash.healingDog.healingDogName}</th>
			<th class = "text-center">담당치유사</th>
			<th class = "text-center">${ash.healingDog.healingDogHealer}</th>
		</tr>
		<tr>
			<th class = "text-center">예약일</th>
			<th class = "text-center">${ash.ashReservationDate}</th>
			<th class = "text-center">결제일</th>
			<th class = "text-center">${ash.purchaseDate}</th>
		</tr>
		<tr>
			<th class = "text-center">주소</th>
			<th class = "text-center">${ash.ashReservationAddress1} ${ash.ashReservationAddress2}</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		
		<tr>
			<th class = "text-center">전화번호</th>
			<th class = "text-center">${ash.ashReservationPhone}</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		<tr>
			<th class = "text-center">예약특이사항</th>
			<th class = "text-center">${ash.ashReservationEtc}</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		<tr>
			<th class = "text-center">예약상태</th>
			<th class = "text-center">
			<c:if test="${ash.ashReservationCondition==0}">예약완료</c:if>
			<c:if test="${ash.ashReservationCondition==1}">출장확정</c:if>
			<c:if test="${ash.ashReservationCondition==2}">출장완료</c:if>
			<c:if test="${ash.ashReservationCondition==3}">예약취소</c:if>
			</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
	</thead>

</table>
<table border="1px">
	<thead>
		<tr >
			<th colspan='2' class = "text-center">결제일</th>
		</tr>
		<tr>
			<th class = "text-center">결제일</th>
			<th class = "text-center">${ash.purchaseDate}</th>
		</tr>
		<tr>
			<th class = "text-center">결제금액</th>
			<th class = "text-center">${ash.ashReservationPrice}원<br> (총 금액 : 100,000, 포인트사용 ${100000-ash.ashReservationPrice})
			
			</th>
		</tr>
		<tr>
			<th class = "text-center">결제수단</th>
			<th class = "text-center">카카오페이</th>
		</tr>
<fmt:parseNumber var="test" value = "${ash.ashReservationPrice*0.01}" integerOnly="true"></fmt:parseNumber>
		<tr>
			<th class = "text-center">적립포인트</th>
			<th class = "text-center">${test}포인트</th>
		</tr>
	</thead>

</table>
<c:if test="${ash.ashReservationCondition==0}"><a id="cancelReservation">예약취소</a><br><a id="updateReservation">예약수정</a></c:if>
<c:if test="${ash.ashReservationCondition!=0}">예약 수정 및 취소가 불가능한 상태입니다.</c:if>



	


</body>


</html>