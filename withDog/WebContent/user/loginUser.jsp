<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
	<jsp:include page="../common/css.jsp" />
	<title>로그인</title>
	
	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
		$( function() {
			
			$("#userId").focus();
			
			//회원가입 연결
			$("#join").on("click" , function() {
				self.location = "/user/addUser"
			});
			
			//로그인 연결
			$("#login").on("click" , function() {
				/*
				var userId=$("#userId").val();
				var passWord=$("#password").val();
				
				if(id == null || id.length <1) {
					alert('ID 를 입력하지 않으셨습니다.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('패스워드를 입력하지 않으셨습니다.');
					$("#password").focus();
					return;
				}
				*/
				$("form").attr("method","POST").attr("action","/user/loginUser").submit();
			});
			
			
		});

	</script>	
	
</head>

<body>

		<jsp:include page="../layout/header.jsp" />
<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/300_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">login page </h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">with Dog</span>
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
                         
	                        <div class="col-md-12 no-padding">
	                            <label>아이디</label>
	                            <input type="userId" name="userId" id="userId">
	                        </div>
	                        
	                        <div class="col-md-12 no-padding">
	                            <label>비밀번호</label>
	                            <input type="password" name="password" id="password">
	                        </div>
                            
                         </form> 
                            
                         <button class="btn highlight-button-dark btn-small btn-round margin-five no-margin-right" type="submit" id="login">Login</button>
                        
                         <a href="#" class="display-block text-uppercase"> >> 아이디 비빌번호 찾기</a> 
                         
                         <div>
                         
                          <a class="highlight-button-black-border btn btn-medium" href="#" id ="join">회원가입</a>
                         
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- end content section -->
        
<jsp:include page="../layout/footer.jsp" />

<jsp:include page="../common/js.jsp" />
</body>
</html>