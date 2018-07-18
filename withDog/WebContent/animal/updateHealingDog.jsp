<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<jsp:include page="/common/css.jsp" />
<title>치유견정보수정</title>

<script type="text/javascript">
	
</script>

</head>

<body>

	<jsp:include page="/layout/header.jsp" />

	<!-- head section -->
	<section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/302_bg.jpg" alt="" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 text-center wow fadeInUp">
				<!-- page title -->
				<h1 class="white-text">HealingDog Registration</h1>
				<!-- end page title -->
				<!-- page title tagline -->
				<span class="white-text xs-display-none">Register HealingDog Information.</span>
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
					
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label class>치유견 아이디</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="healingDogId" id="healingDogId" value="치유견 아이디" class="big-input2" readonly />
						<!-- end input  -->
					</div>
					
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label class>치유견 이름</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="healingDogName" id="healingDogName" value="치유견이름" class="big-input2">
						<!-- end input  -->
					</div>

					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>치유견 견종</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="healingDogBreed" id="healingDogBreed" value="치유견 견종" class="big-input2">
						<!-- end input  -->
					</div>
					
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>치유견 생년월일</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="healingDogBirth" id="healingDogBirth" value="치유견 생년월일" class="big-input2">
						<!-- end input  -->
					</div>
					
					<div class="form-group">
						<!-- label  -->
						<label >치유견 성별</label>
						<!-- end label  -->
						<!-- input  -->
						<div class="checks">
							<input type="radio" id="healingDogGender" name="healingDogGender" checked />
							<label for="healingDogGender">남아</label>
							<input type="radio" id="healingDogGender" name="healingDogGender" />
							<label for="healingDogGender">여아</label>
						</div>
						<!-- end input  -->
					</div>
					
					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>치유견 특징</label>
						<!-- end label  -->
						<!-- input  -->
						<textarea name="healingDogChar" placeholder="" class="big-textarea"></textarea>
						<!-- end input  -->
					</div>

					<div class="form-group margin margin-two-bottom">
						<!-- label  -->
						<label>치유견이미지</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="file" name="healingDogImage" id=healingDogImage" class="big-input2">
						<!-- end input  -->
					</div>

					<div class="form-group no-margin-bottom">
						<!-- label  -->
						<label>담당치유사</label>
						<!-- end label  -->
						<!-- input  -->
						<input type="text" name="healingDogHealer" class="big-input2"/>
						<!-- end input  -->
					</div>

				</form>
				
				<!-- 버튼 -->
				<div class="text-center">
					<a href="#" class="highlight-button btn btn-medium" >수정</a>
					<a href="#modal-popup" class="highlight-button-dark btn btn-medium button no-margin-right popup-with-zoom-anim no-margin-bottom">삭제</a>
				</div>
				<!-- end 버튼 -->

				<!-- 삭제팝업 -->
				<div class="col-md-9 col-sm-9 no-padding margin-five">

					<div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
						<div id="modal-popup" class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
						
							<div>
								<span class="black-text">웹페이지 메시지</span>
								<p class="borderline-gray"></p>
							</div>

							<p class="text-small">삭제하시겠습니까?</p>
							
							<!-- 버튼 -->
							<div class="text-center no-margin-bottom">
								<a href="#"
									class="highlight-button btn btn-medium no-margin-bottom">CANCEL</a>
								<a href="#"
									class="highlight-button-dark btn btn-medium no-margin">OK</a>
							</div>
							<!-- end 버튼 -->

						</div>
					</div>
					<!-- end modal popup -->

				</div>
				<!-- end 삭제팝업 -->
				
				
			</div>
	</div>
	</section>
	<!-- end content section -->



	<jsp:include page="/layout/footer.jsp" />

	<jsp:include page="/common/js.jsp" />

</body>
</html>