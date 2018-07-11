    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>애견상식등록</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

<script type="text/javascript">

function fncAddDogInfo(){
	//Form 유효성 검증
 	var dogInfoTopic = $('#dogInfoTopic option:selected').val();
	alert(dogInfoTopic)
	var dogInfoTitle = $('#dogInfoTitle').val();
	var dogInfoContent = $('#dogInfoContent').val();	

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
	$("button.btn.btn-primary").on("click", function () {
		fncAddDogInfo();
	});
});

$(function () {
	$("a[href='#' ]").on("click", function () {
		if (confirm("정말 취소하시겠습니까??") == true){    //확인
		      history.go(-1)
		  }else{   //취소
		      return;
		  }
	});
})


</script>
</head>


<body>

	<div class="navbar  navbar-default">
   	</div>
	<div class="content-wrapper">
        <%-- Content Header (Page header) --%>
    
        <%-- Main content --%>
        <section class="content container-fluid">
<div class="container">
	<div class="page-header text-center">  
		<h3 class="text-info">애견상식 등록</h3>
</div>
	
<form class="form-horizontal">
	<div class="form-group">
		    <span class="col-sm-4">
		    <select id="dogInfoTopic" name="dogInfoTopic">
		    	<option>주제</option>
		    	<option value="1" >훈련</option>
		    	<option value="2" >번식</option>
		    	<option value="3" >위생</option>
		    	<option value="4" >음식</option>
		    	<option value="5" >행동</option>
		    	<option value="6" >미용</option>
		    	<option value="7" >기타</option>
		    </select>
		    </span>
		     <span class="col-sm-4">
		      <input type="text" class="form-control" id="dogInfoTitle" name="dogInfoTitle" placeholder="애견상식 제목">
		    </span>
	</div>
		
	<div class="form-group">
		    <div class="col-sm-4">
		    <textarea rows="40" cols="40"  id="dogInfoContent" name="dogInfoContent" placeholder="애견상식 내용"></textarea>
		    </div>
	</div>
	
	<div class="form-group">
		    <label for="quantity" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
		    <div class="col-sm-4">
		      <input type="file" class="form-control"  id="file" name="file" placeholder="상품이미지">
		      <input type="file" class="form-control"  id="file" name="file" placeholder="상품이미지">
		      <input type="file" class="form-control"  id="file" name="file" placeholder="상품이미지">
		    </div>
		    
	 <hr/>
	</div>
			    
	<div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >등 &nbsp;록</button>
			  <a class="btn btn-primary btn" href="#" role="button">취 &nbsp;소</a>
		    </div>
	</div>
</form>
	
	  
 	</div>
        </section>
    </div>
               

</body>
</html>