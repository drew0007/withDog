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
		
		//이메일 입력방식 선택 
		$("#selectEmail").change(function(){ 
			$("#selectEmail option:selected").each(function () { 
				if($(this).val()== '1'){ //직접입력일 경우 
					$("#email02").val(''); //값 초기화
					$("#email02").attr("disabled",false); //활성화 
					}else{ //직접입력이 아닐경우 
						$("#email02").val($(this).text()); //선택값 입력
						$("#email02").attr("disabled",true); //비활성화 
				} 
			}); 
			
		});//end 이메일 입력방식
		
		
		//회원가입 연결
		$("#join").on("click" , function() {
			
		
			$("form").attr("method","POST").attr("action","/user/addUser").submit();
			
			
		});
		
	});//end 제이쿼리
	

	</script>


</head>
<body>
		
		<jsp:include page="../layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/100_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">User Registration</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Register and modify user information.</span>
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
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>비밀번호:</label>
                                <input type="password" name="password">
                            </div>
              				
              				 <div class="col-md-12 no-padding">
                                <label>이름:</label>
                                <input type="text" name="userName">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>생년월일:</label>
                                <input type="text" name="birth" placeholder="예)1995.10.02">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>휴대전화:</label> 
                                <input type="text" name="phone">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>주소:</label>
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <input type="text" name="address" class="col-md-8">
                                <button class="highlight-button btn no-margin pull-right post-search">우편번호 검색</button>
                                <!-- <input type="text" name="address2">-->
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