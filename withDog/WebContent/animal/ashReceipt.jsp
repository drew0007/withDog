

<%@ page contentType="text/html;charset=EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
	<head>
	<!-- 화면 변경 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
		<title>Insert title here</title>
	
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	
	<style>
		body{
			padding-top : 70px;
		}
	</style>
	
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script>
	$(function(){
		
		if('${state}'=='1'){
			alert("결제가 취소 되었습니다.");
			window.close();
		}
		if('${state}'=='2'){
			alert("결제실패!!! 다시시도해 주세요");
			window.close();
		}
		if('${state}'=='0'){
			window.close();
			opener.location.href='/animal/addReservationASHView.jsp';
		}
		
		
	});
	
	</script>
	</head>

	<body>
	</body>
</html>