
<%@ page pageEncoding="EUC-KR"%>
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
<jsp:include page="/common/css.jsp" />


<script type="text/javascript">

$(function () {
	var ashReservationNo = ${ash.ashReservationNo};
	$("#updateReservation").on("click", function () {
		if (confirm("예약을 수정하시겠습니까?") == true){    //확인
			$("form").attr("method","post").attr("action","/ash/updateAshMyReservation/"+ashReservationNo).submit();
		  }else{   //취소
		      return;
		  }
	})
})
</script>
</head>


<body>
<form>
<input type="hidden" name=ashReservationNo value="${ash.ashReservationNo}">
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
			<th class = "text-center">
			<input type="text" name="ashReservationAddress1" value="${ash.ashReservationAddress1}">
			<input type="text" name="ashReservationAddress2" value="${ash.ashReservationAddress2}">
			</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		
		<tr>
			<th class = "text-center">전화번호</th>
			<th class = "text-center"><input type="text" name="ashReservationPhone" value="${ash.ashReservationPhone}"></th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		<tr>
			<th class = "text-center">예약특이사항</th>
			<th class = "text-center"><input type="text" name="ashReservationEtc" value="${ash.ashReservationEtc}"></th>
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
</form>
<c:if test="${ash.ashReservationCondition==0}"><br><a id="updateReservation">예약수정</a></c:if>






</body>


</html>