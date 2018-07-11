<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="/common/css.jsp" />

<title>애견상식 등록</title>

<script type="text/javascript">
function fncAddDogInfo(){
	//Form 유효성 검증
 	var dogInfoTopic = $('#dogInfoTopic option:selected').val();
	var dogInfoTitle = $('input[name=dogInfoTitle]').val();
	var dogInfoContent = $('textarea[name=dogInfoContent]').val();	

	if(dogInfoTopic == null || dogInfoTopic.length<1){
		alert("주제를 선택하세요.");
		return;
	}
	if(dogInfoTitle == null || dogInfoTitle.length<1){
		alert("제목을 작성하세요.");
		return;
	}
	if(dogInfoContent == null || dogInfoContent.length<1){
		alert("내용을 입력하세요.");
		return;
	}

	$("form").attr("method","post").attr("action","/dogInfo/addDogInfo").attr("enctype","multipart/form-data").submit();
}


$(function () {
	$("#asdf").on("click", function () {
		if (confirm("등록하시겠습니까?") == true){    //확인
			fncAddDogInfo();
		  }else{   //취소
		      return;
		  }
	});
})

</script>

</head>

<body>


	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/602_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/602_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section class="wow fadeIn">
            <div class="container">
 
                <!-- comment form -->
                <div class="row">
                <form>
                    <div id="addcomment" class="col-md-8 col-sm-12 blog-comment-form-main center-col text-center">
                        <h5 class="info-title margin-five no-margin-top">애견상식 등록하기</h5>
                        <div class="blog-comment-form">
                            	<!-- select -->
                                <select id="dogInfoTopic" name="dogInfoTopic" class="big-input col-md-4" style="padding-bottom:13px; padding-right:10px;">
                                    <option value="" selected="selected">주제 선택</option>
                                    <option value="1" >훈련</option>
                                    <option value="2" >번식</option>
                                    <option value="3" >위생</option>
                                    <option value="4" >음식</option>
                                    <option value="5" >행동</option>
                                    <option value="6" >미용</option>
                                    <option value="7" >기타</option>
                                </select>
                                <!-- end select -->
                                <!-- input  -->
                                <input type="text" name="dogInfoTitle" placeholder="애견상식제목" class="big-input col-md-8 pull-right">
                                <!-- end input -->
                                <!-- textarea  -->
                                <textarea name="dogInfoContent" placeholder="애견상식내용" class="info-textarea" ></textarea>
                                <!-- end textarea  -->
                                <!-- input  -->
                                <input type="file"  id="file" name="file" class="big-input">
                                <input type="file"  id="file" name="file" class="big-input">
                                <input type="file"  id="file" name="file" class="big-input">
                                <!-- end input -->
                                <!-- required  -->
                                <span class="required text-right">*Please complete all fields correctly</span>
                                <!-- end required  -->
                                
                            </form>
                            
                            <!-- button  -->
                            <input id="asdf" type="submit" name="send" value="등록하기" class="highlight-button-dark btn btn-medium no-margin-bottom">
<!--                             <button id="asdf" type="button" class="btn btn-primary"  >등 &nbsp;록</button> -->
                            <!-- end button  -->
                        </div>
                    </div>
                    <!-- end comment form -->
                </div>
                <!-- end content  -->


		    </div>
        </section>
        <!-- end content section -->
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>