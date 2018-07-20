<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
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
				
				//유효성체크
				var userId=$("#userId").val();
				var passWord=$("#password").val();
				
				if(userId == null || userId.length <1) {
					$(".spanClass").html("**ID 를  입력해주세요").css('color','red');	
					$("#userId").focus();
					$("input").on("click",function(){
						$(".spanClass").html("");
					});
					return;
				}
				
				if(passWord == null || passWord.length <1) {
					$(".spanClass").html("**비밀번호를 입력해주세요").css('color','red');	
					$("#password").focus();
					$("input").on("click",function(){
						$(".spanClass").html("");
					});
					return;
				}
							
				//로그인 
				$.ajax({
							url : "/user/json/loginUser",
							method : "POST",
							datatype : "json",
							data: JSON.stringify({
								userId:$("#userId").val(),
								password:$("#password").val()
							}),
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							success : function(JSONData , status){
								
								var userCondition = JSONData.userCondition;
								
								if(userCondition=='1'){
									self.location = "/common/mainPage";
								}else if(userCondition=='0'){
									//휴면회원
									alert("아이디로 1년 이상 로그인 되지 않아 휴면 상태로 전환되었습니다.");
									self.location = "/user/changeUserCon";
								}else{
									//탈퇴한 회원 로그인시도시, 또는  비번  블일치
									$(".spanClass").html("**아이디 또는 비밀번호가 일치하지 않습니다.").css('color','red');	
									
									$("input").on("click",function(){
										$(".spanClass").html("");
									});
								}
			
							}
						
		 				});//end of Ajax
		 				
			});// end 로그인
			
	
			//엔터키 이벤트 ( 로그인 클릭한것처럼)
			$("#password").keydown(function(event){
		       if(event.keyCode==13){
		    	   $('#login').trigger('click');
		        }
		    });
			
			$("#userId").keydown(function(event){
			       if(event.keyCode==13){
			    	   $('#login').trigger('click');
			        }
			    });
			

		});
		
		////////////////SNS로그인////////////////
		//카카오 로그인
		$(function(){
			$("img[src='/images/login/kakao_login.png']").on("mouseover" , function() {
				$(this).attr("src", "/images/login/kakao_login_ov.png");
			});
			
			$("img[src='/images/login/kakao_login.png']").on("mouseout" , function() {
				$(this).attr("src", "/images/login/kakao_login.png");
			});
			
			$("img[src='/images/login/kakao_login.png']").on("click" , function() {
				
				loginWithKakao();
			});
		});
		
		
		Kakao.init('1d3fddc61b788ab458254eb1f4ea00ae');
	    function loginWithKakao() {
	      // 로그인 창을 띄웁니다.
	      Kakao.Auth.login({
	        success: function(authObj) {
	            // 로그인 성공시, API를 호출합니다.
	            Kakao.API.request({
	              url: '/v1/user/me',
	              success: function(res) {
	                //alert(JSON.stringify(res));
	                checkUser(res);
	              },
	              fail: function(error) {
	                alert(JSON.stringify(error));
	              }
	            });
	          },
	          fail: function(err) {
	            alert(JSON.stringify(err));
	          }
	      });
	    };

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
                         	<span class="spanClass"></span>
	                        <div class="col-md-12 no-padding">
	                            <label>아이디</label>
	                            <input type="userId" name="userId" id="userId">
	                              <span class="spanId"></span>
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
                         
                        <div class="form-group">
                        	<p>SNS 로그인</p>
						    <div class="col-sm-offset-4 col-sm-6">
								<img src="../images/login/kakao_login.png" />
						    </div>
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