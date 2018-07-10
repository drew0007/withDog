<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="../common/css.jsp" />
<title>비밀번호 변경</title>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
	
		var pwcheck = false;
		
		$( function() {
			
			//비밀번호 수정 연결
			$("#change").on("click" , function() {
				
				
				chk_pw();

				//기존 비밀번호 일치여부
				if(check){
					
				
					//새 비밀번호 일치 여부
					var pwd = $("#Password").val();
					alert("바꿀비밀번호"+pwd);
					var pwd1 = $("#PasswordCheck").val();
					alert("바꿀비밀번호2"+pwd1);
					
					if( pwd != pwd1 ) {	//	비밀번호 와 비밀번호 확인이 다르다면 ... 
						
						alert("111비밀번호를 다르게 입력하였습니다.");
					
						$("#PasswordCheck").focus();
						$("#PasswordCheck").append( "<p>비밀번호를 다르게 입력하였습니다.</p>" );
						
						//	이벤트 중지하기
						event.preventDefault();
					}
					
					$("form").attr("method","POST").attr("action","/user/updateUser").submit();

				}else{
					
					alert("2222비밀번호를 다르게 입력하였습니다.");
					$("#beforePassword").focus();
				}
				
				
			});
			
		});
		
		//기존 비밀번호 맞는지 Ajax 확인
		function chk_pw() { 
			
			//var param = $("form").serialize();
			//var userId = $("#userId").val();
			
			$.ajax(
					{
						url : "/user/json/checkPassword/",
						method : "POST",
						dataType : "json",
						data: JSON.stringify({
							userId:$("#userId").val(),
							password:$("#beforePassword").val()
						}),
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
					success : function(JSONData , status){
						var check = JSONData;
						
						if(check){
							pwcheck = true;
							
						}else{
							pwcheck = false;
						}
					}
						
				}); //end of .ajax
			
		} //end of  chk_pw()
		

	</script>	


</head>
<body>
		
		<jsp:include page="../layout/header.jsp" />
		
		<!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/603_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">Change Password</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">고객님의 소중한 개인정보 보호를 위해 비밀번호를 변경해주세요</span>
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
                        <p class="black-text font-weight-600 text-uppercase text-large">비밀번호 변경</p>
                        <form id="billing-form" method="post" action="javascript:void(0)" name="billing-form">
                            <div class="col-md-12 no-padding">
                                <label>기존 비밀번호:</label>
                                <input type="password" name="beforePassword" id="beforePassword">
                            </div>
                            
                            <div class="col-md-12 no-padding">
                                <label>새 비밀번호 :</label>
                                <input type="password" name="password" id="password">
                            </div>
 
                            <div class="col-md-12 no-padding">
                                <label>새 비밀번호 확인:</label>
                                <input type="password" name="PasswordCheck" id="passwordCheck">
                            </div>
                            
                            <input type="hidden" name="userId" value="${sessionScope.user.userId}" id="userId">
                            
                          </form> 
                          
                          <div class="text-center">
                          <button class="highlight-button btn no-margin post-search" id="change">비밀번호 수정</button>
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