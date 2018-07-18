<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

	
<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
		$( function() {
			
			$(".test").on("click",function(){
				alert(2222);
				$.ajax({
						url : "/user/json/test",
						method : "get",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status) {
							alert(JSONData);
							var a = JSONData.key[0];
							alert("0번째"+a);
																			 												
								$("#ddd").html(<jsp:include page="test2.jsp" />);
														
						}
					
					});// end of ajax
				
				});
			
		});// end 제이쿼리 	
	</script>
</head>
<body>

		<!-- 장원이 징 태스트 -->
		
		<!--일치율 결과 영역 시작-->
       	<!--  title -->
          	<div class="col-md-12 text-center"> <button class= "test">결과보기</button>
			<h3 class="title-large font-weight-400" style="margin-top: 50px">해당 이미지와 강아지의 일치율입니다.</h3> 
			<h6 class="font-weight-400" style="margin-top: 20px;margin-bottom: 50px;"><분석 결과를 선택해보세요></h6>
		</div>
		<!--  end title -->
			
                   <div  id="ddd" class="text-center center-col">
                 

                       



                   </div>
		<!--일치율 결과 영역 끝-->
		<!-- 장원이 테스트 끝 -->
		


</body>
</html>