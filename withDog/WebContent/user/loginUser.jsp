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
			
			//아이디/비밀번호찾기 연결 			
			$("#findUser").on("click" , function() {
				self.location = "/user/findUser"
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
	
			//엔터키 이벤트 ( 로그인 클릭한것처럼)
			$("#password").keydown(function(event){
		       if(event.keyCode==13){
		    	   $('#login').trigger('click');
		        }
		    });

		});

	</script>	
	
</head>

<body>

	<jsp:include page="/layout/common-header.jsp" />
		
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/login_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/login_tit.png"></h1>
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
                                                  
                         <button class="btn highlight-button-dark btn-small btn-round margin-five no-margin-right" type="submit" id="login"}">Login</button>
                        
                         <a href="#" class="display-block text-uppercase" id="findUser"> >> 아이디 비밀번호 찾기</a> 
                         
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