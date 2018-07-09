<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>



</head>

<body>
	<div id="resultDiv">
		<table border="1">
			<thead>
				<tr align="center">
					<th colspan="11">분석결과</th>
				</tr>
				<tr>
					<th width="10%" class="text-center">견종번호</th>
					<th width="10%" class="text-center">영어명</th>
					<th width="10%" class="text-center">한글명</th>
					<th width="10%" class="text-center">신장</th>
					<th width="10%" class="text-center">체중</th>
					<th width="10%" class="text-center">수명</th>
					<th width="10%" class="text-center">출생지</th>
					<th width="10%" class="text-center">색상</th>
					<th width="10%" class="text-center">성격</th>
					<th width="10%" class="text-center">관련링크</th>
				</tr>
			</thead>

			<tbody id="content" align="center">

			</tbody>

		</table>
	</div>
	<script type="text/javascript">
		$(function() {

			$.ajax({
				url : "/dogBreedDic/json/getAllBreedInfoList",
				method : "GET",
				dataType : "json",
				success : function(data) {
					var detail = "<tr><td>" + data.allDogBreedInfo[0].dogNo
							+ "</td><td>" + data.allDogBreedInfo[0].dogBreedEN
							+ "</td><td>" + data.allDogBreedInfo[0].dogBreedKO
							+ "</td><td>" + data.allDogBreedInfo[0].dogHeight
							+ "</td><td>" + data.allDogBreedInfo[0].dogWeight
							+ "</td><td>" + data.allDogBreedInfo[0].dogLifeSpan
							+ "</td><td>" + data.allDogBreedInfo[0].dogPlace
							+ "</td><td>" + data.allDogBreedInfo[0].dogColor
							+ "</td><td>" + data.allDogBreedInfo[0].dogPersonality
							+ "</td><td>" + data.allDogBreedInfo[0].dogLink
							+ "</td>" + "</td></tr>";
					$("#content").append(detail)
					console.log(data)
				}

			})
		})
	</script>
</body>

</html>