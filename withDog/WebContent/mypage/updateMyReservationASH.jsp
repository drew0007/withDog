
<%@ page pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="kr">
<head>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- �޷� -->
<script type="text/javascript" src="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.js"></script>
<link href="http://www.blueb.co.kr/data/201010/IJ12873724731095/datepicker.css" rel="stylesheet" type="text/css" />
<!-- �޷� -->
<jsp:include page="/common/css.jsp" />


<script type="text/javascript">

$(function () {
	var ashReservationNo = ${ash.ashReservationNo};
	$("#updateReservation").on("click", function () {
		if (confirm("������ �����Ͻðڽ��ϱ�?") == true){    //Ȯ��
			$("form").attr("method","post").attr("action","/ash/updateAshMyReservation/"+ashReservationNo).submit();
		  }else{   //���
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
			<th class = "text-center">ġ�����̸�</th>
			<th class = "text-center">${ash.healingDog.healingDogName}</th>
			<th class = "text-center">���ġ����</th>
			<th class = "text-center">${ash.healingDog.healingDogHealer}</th>
		</tr>
		<tr>
			<th class = "text-center">������</th>
			<th class = "text-center">${ash.ashReservationDate}</th>
			<th class = "text-center">������</th>
			<th class = "text-center">${ash.purchaseDate}</th>
		</tr>
		<tr>
			<th class = "text-center">�ּ�</th>
			<th class = "text-center">
			<input type="text" name="ashReservationAddress1" value="${ash.ashReservationAddress1}">
			<input type="text" name="ashReservationAddress2" value="${ash.ashReservationAddress2}">
			</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		
		<tr>
			<th class = "text-center">��ȭ��ȣ</th>
			<th class = "text-center"><input type="text" name="ashReservationPhone" value="${ash.ashReservationPhone}"></th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		<tr>
			<th class = "text-center">����Ư�̻���</th>
			<th class = "text-center"><input type="text" name="ashReservationEtc" value="${ash.ashReservationEtc}"></th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
		<tr>
			<th class = "text-center">�������</th>
			<th class = "text-center">
			<c:if test="${ash.ashReservationCondition==0}">����Ϸ�</c:if>
			<c:if test="${ash.ashReservationCondition==1}">����Ȯ��</c:if>
			<c:if test="${ash.ashReservationCondition==2}">����Ϸ�</c:if>
			<c:if test="${ash.ashReservationCondition==3}">�������</c:if>
			</th>
			<th class = "text-center"></th>
			<th class = "text-center"></th>
		</tr>
	</thead>
</table>
</form>
<c:if test="${ash.ashReservationCondition==0}"><br><a id="updateReservation">�������</a></c:if>






</body>


</html>