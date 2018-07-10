

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
	<!-- <script type="text/javascript">
		$(function(){
			
			$.ajax({
				url:"/purchase/json/addPurchase",
				method:"POST",
				dataType:"json",
				
				hearders:{
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success:function(data,status){
			
				}
			  }	
			);/* ajax end */
		});
	</script> -->

	</head>

	<body>

		<%-- <form name="updatePurchase" action="/purchase/updatePurchaseView?tranNo=${purchase.tranNo}" method="post"> --%>
		
		다음과 같이 후원 되었습니다.
		
		<table width="100%" height="37" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>펀딩이름</td>
				<td>${title}</td>
				<td></td>
			</tr>
			<tr>
				<td>후원금액</td>
				<td>${empty price? 0:price} 원</td>
				<td></td>
			</tr>
			<tr>
				<td>사용포인트</td>
				<td>
				${empty usePoint? 0:usePoint}
				</td>
				<td></td>
			</tr>
			
		</table>
		<!-- </form> -->
	
	</body>
</html>