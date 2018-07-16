<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

<jsp:include page="../common/css.jsp" />
<title>회원가입</title>
	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	
	<script type="text/javascript">
	
	$( function() {

		
		$("#userId").focus();
		
		//아이디 중복검사 
		$("input[name=userId]").on("keyup", function(){
			
			var userId = $("input[name=userId]").val();
			
			if(userId == ""){
				$("#userId").focus();
			}else{
				$.ajax(
						{
							url : "/user/json/checkUserId/"+userId,
							method : "GET",
							dataType : "json",
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
						success : function(JSONData , status){
							var check = JSONData;
							if(check){
								$(".spanId").html("사용 가능한 아이디입니다.").css('color','blue');
							}else{
								$(".spanId").html("중복 아이디입니다.").css('color','red');
							}
						}
					});
			}//end 아이디 중복 검사
		});
		
		//이메일 입력방식 선택 
		$("#email2").change(function(){ 
			$("#email2 option:selected").each(function () { 
				if($(this).val()== '1'){ //직접입력일 경우 
					$("#emailText").val(''); //값 초기화
					$("#emailText").attr("disabled",false); //활성화 
				}else{ //직접입력이 아닐경우 
					$("#emailText").val($(this).text()); //선택값 입력
					$("#emailText").attr("disabled",true); //비활성화
				} 		
				
			}); 
			
		});//end 이메일 입력방식
		
// 		document.domain = "localhost:8080";
		//도로명 주소  우편번호 검색 클릭시
		$("#searchPost").on("click" , function() {
			var pop = window.open("http://localhost:8080/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		});
						
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			//alert(roadFullAddr);
			$("#postNo").val(zipNo);
			$("#address1").val(roadAddrPart1);
			$("#address2").val(addrDetail);
		}	
		window.jusoCallBack = jusoCallBack;
		//회원가입 연결
		$("#join").on("click" , function() {
			 
			//가입으로 넘기기전 체크사항   email
			///1.이메일 입력 :: 직접 입력 선택시 option value값이 1=> emailText 입력값으로 
			if($("#email2").val()=='1' ){
				var email2= '@'+$("#emailText").val();
				$("#email2 option:selected").val(email2);
			}
			
			///2.좋아하는 견종 :: option value값이 String => 도메인이 int 이므로 바꿔서
			var dogNo = $("#selecttt").val()*1;
			$("#selecttt option:selected").val(dogNo);
			
			$("form").attr("method","POST").attr("action","/user/addUser").submit();
			
		});
		
		
	});//end 제이쿼리

	</script>

</head>
<body>

	<jsp:include page="/layout/common-header.jsp" />
		
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/join_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/join_tit.png"></h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <!-- 서브타이틀 <span class="white-text">1234</span>-->
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
         
        <!-- content section -->
        <section>
            <div class="container clearfix"><!-- container1 -->
                <div class="row margin-five no-margin-top"><!-- row1 -->
                    <div class="col-md-6 col-sm-12 center-col sm-margin-bottom-seven">
                        <p class="black-text font-weight-600 text-uppercase text-large">가입 정보</p>
                       
                       
                       <form>
                            <div class="col-md-12 no-padding">
                                <label>아이디:</label>
                                <input type="text" name="userId" id="userId">
                                <span class="spanId"></span>
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>비밀번호:</label>
                                <input type="password" name="password">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>비밀번호 확인:</label>
                                <input type="password" name="passwordCheck">
                            </div>
              				
              				 <div class="col-md-12 no-padding">
                                <label>이름:</label>
                                <input type="text" name="userName">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>생년월일:</label>
                                <input type="text" name="birth" placeholder="예)1995-10-02" class="selector" id="selector">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>휴대전화:</label> 
                                <input type="text" name="phone" placeholder="예)010-1234-5678">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <p style="margin-bottom:10px">이메일:</p>
                                <input type="text" name="email1" class="col-md-3" id="email1">
                                <span>@</span>	
                                <input type="text" name="emailText"class="col-md-3" id="emailText">
                             	<div class="col-md-3 input-round">
									<select name="email2" id="email2">
										<option value="1">직접입력</option>
										<option value="@naver.com">@naver.com</option>
										<option value="@daum.net">@daum.net</option>
										<option value="@gmail.com">@gmail.com</option>
										<option value="@hotmail.com">@hotmail.com</option>
										<option value="@nate.com">@nate.com</option>
									</select>
                            	 </div>
                            </div> 
                            
                            <div class="col-md-12 no-padding">
                                <label>주소:</label>
                            </div>
                            
                            <div class="col-md-12 no-padding">
                             	<input type="text" name="postNo" class="col-md-8" id="postNo">
                                <input type="text" name="address1" class="col-md-8" id="address1">
                                <button class="highlight-button btn no-margin pull-right post-search" id="searchPost">우편번호 검색</button>
                                <input type="text" name="address2" id="address2">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                            	 <label>좋아하는 견종:</label>
								<input type="text" name="dogNo" id="dog">
							</div>
                          
                        </form> 
                        
                       <div class="text-center">
	                          <button class="highlight-button-dark btn no-margin post-search" id="join" type="submit">회원가입하기</button>
	                    </div>
                    </div>
                </div><!-- row1 -->
            </div><!-- container1 -->
            
        </section>
        <!-- end content section -->
        
		
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>