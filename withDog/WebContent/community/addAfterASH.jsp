<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="/common/css.jsp" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

<title>동물 교감 후기 등록</title>

<script type="text/javascript">
function fncAddAfterAsh(){
	//Form 유효성 검증
 	var healingDog = $('#healingDog option:selected').val();
	var afterASHTitle = $('input[name=afterASHTitle]').val();
	var afterASHContent = $('textarea[name=afterASHContent]').val();	


	if(afterASHTitle == null || afterASHTitle.length<1){
		alert("제목을 작성하세요.");
		return;
	}
// 	if(healingDog == null || healingDog.length<1){
// 		alert("치유견을 선택하세요.");
// 		return;
// 	}
	if(afterASHContent == null || afterASHContent.length<1){
		alert("내용을 입력하세요.");
		return;
	}

	$("form").attr("method","post").attr("action","/afterAsh/addAfterAsh").attr("enctype","multipart/form-data").submit();
}

$(function () {
	$("#submit").on("click", function () {
		if (confirm("등록하시겠습니까?") == true){    //확인
			fncAddAfterAsh();
		  }else{   //취소
		      return;
		  }
	});
})


$(function () {
	$.ajax({
		url : "/ash/json/getAllHealingDogList",
		method : "GET",
		datatype : "json",
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success : function (data) {
			console.log(data)
			for(var i = 0; i<data.healingDogs.length; i++){
				$("#healingDog").append($('<option value='+data.healingDogs[i].healingDogNo+'>'+data.healingDogs[i].healingDogName+'</option>'));
				}
		}
	})
})

</script>


</head>

<style type="text/css">
.calendar-icon { 	display: flex; align-items: center;}
.calendar-icon i { margin-left:-30px; z-index:999; margin-right:10px;}
</style>
 
<body>

	<jsp:include page="../layout/header.jsp" />

	<!-- head section -->
	<section
		class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/603_bg.jpg"
		alt="" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 text-center wow fadeInUp">
				<!-- page title -->
				<h1 class="white-text"> 동물 교감치유 후기등록</h1>
				<!-- end page title -->
				<!-- page title tagline -->
				<span class="white-text xs-display-none">review</span>
				<!-- end title tagline -->
			</div>
		</div>
	</div>
	</section>
	<!-- end head section -->

	<!-- content section -->
	<section class="wow fadeIn">
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-sm-8 col-xs-11 center-col xs-no-padding">
				<form>

					<!-- 후기제목 -->
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label class>후기제목</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="afterASHTitle" id="afterASHTitle" class="big-input">
						<!-- end input  -->
					</div>
					
					<!-- 이용날짜 -->
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>이용기간</label>
						
						<!-- end label  -->
					</div>
					<div class="form-group calendar-icon no-margin-bottom" >
						<!-- input  -->
						<input type="text" class="big-input" name="afterASHDate" id="afterASHDate">
						<i class="fa fa-calendar small-icon form-group" style="padding-left:-20px;"></i>
						<select class="big-input" name="afterASHTime" id="afterASHTime">
							<option value="0" selected="selected">오전 [10:00~13:00]</option>
                         	 <option value="1" >오후 [14:00~17:00]</option>
						</select>
						<!-- end input  -->
					</div>

					<!-- 치유견 선택 -->
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>치유견 선택</label>
						<div class="select-style">
							<select name="healingDog.healingDogNo" id="healingDog">
							</select>
						<!-- end input  -->
					</div>
					
					<!-- 후기상세 -->
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>후기상세내용</label>
						<!-- end label  -->
						<!-- input  -->
						<textarea name="afterASHContent" id="afterASHContent" placeholder="상세내용을 입력해주세요"></textarea>
						<!-- end input  -->
					</div>

					<div class="form-group margin margin-two-bottom">
						<!-- label  -->
						<label>이미지 1</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="file"  id="file" name="file" class="big-input">
						<!-- end input  -->
					</div>
					
					<div class="form-group margin margin-two-bottom">
						<!-- label  -->
						<label>이미지2</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="file"   id="file" name="file" class="big-input">
						<!-- end input  -->
					</div>
					
					<div class="form-group margin margin-two-bottom">
						<!-- label  -->
						<label>이미지3</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="file"   id="file" name="file"  class="big-input">
						<!-- end input  -->
					</div>
					
					<div class="form-group margin margin-two-bottom">
						<!-- label  -->
						<label>동영상</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="file" name="file" class="big-input">
						<!-- end input  -->
					</div>
					
                   		<!-- required  -->
                        <span class="required text-right">*Please complete all fields correctly</span>
                        <!-- end required  -->

				</form>
				<div class="text-center">
					<span id="submit" class="highlight-button btn btn-medium">등록</span>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- end content section -->





	<jsp:include page="/layout/footer.jsp" />

	<jsp:include page="/common/js.jsp" />

</body>
</html>