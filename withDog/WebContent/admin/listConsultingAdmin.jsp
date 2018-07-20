<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" 	content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<title>영상상담 관리</title>

<!-- 공통 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />
	  
  
<script type="text/javascript">

function fncGetList(currentPage) {
 	$("#currentPage").val(currentPage)
	$("#myConsulting").attr("method","POST").attr("action","/ash/getConsultingAdminList").submit();
}

$(function(){
	$('a[name="btnConsulting"]').on('click', function(){
		var index = $('a[name="btnConsulting"]').index(this);	

		var btnConsulting = $($('a[name="btnConsulting"]')[index]).text();
		var regDate = $($('input[name="regDate"]')[index]).val();
		var userId = $($('input[name="userId"]')[index]).val();
		var healingDogHealer = $($('input[name="healingDogHealer"]')[index]).val();
		var healingDogName = $($('input[name="healingDogName"]')[index]).val();
		var consultingNo = $($('input[name="consultingNoList"]')[index]).val();
		
		if(btnConsulting == '상담취소'){
			$('#titleModal').text('영상상담 취소');
			$('#textModal').text('영상상담 취소 처리하시겠습니까?');
			$('input[name="consultingState"]').val('3');
		}else{
			$('#titleModal').text('영상상담 완료');
			$('#textModal').text('영상상담 완료 처리하시겠습니까?');
			$('input[name="consultingState"]').val('2');
		}
		$('#regDateModal').text('신청일자 : '+regDate);
		$('#userIdModal').text('신청자 : '+userId);
		$('#healingDogHealerModal').text('상담사 : '+healingDogHealer);
		$('#healingDogNameModal').text('치유견 : '+healingDogName);
		$('#consultingNoModal').val(consultingNo);
		$('#consultingUserId').val(userId);
		$('#index').val(index);
	});
	
	$('a[name="startConsulting"]').on('click', function(){
		var index = $('a[name="startConsulting"]').index(this);

		var startConsulting = $($('a[name="startConsulting"]')[index]).text();
		var regDate = $($('input[name="regDate"]')[index]).val();
		var userId = $($('input[name="userId"]')[index]).val();
		var healingDogHealer = $($('input[name="healingDogHealer"]')[index]).val();
		var healingDogName = $($('input[name="healingDogName"]')[index]).val();
		var consultingNo = $($('input[name="consultingNoList"]')[index]).val();
		
		if(startConsulting == '상담시작'){
			$('#titleModal').text('영상상담 시작');
			$('#textModal').text('영상상담을 시작하시겠습니까?');
			$('input[name="consultingState"]').val('1');
		}else{
			$('#titleModal').text('다시연결');
			$('#textModal').text('영상상담을 다시연결하시겠습니까?');
			$('input[name="consultingState"]').val('9');
		}
		$('#regDateModal').text('신청일자 : '+regDate);
		$('#userIdModal').text('신청자 : '+userId);
		$('#healingDogHealerModal').text('상담사 : '+healingDogHealer);
		$('#healingDogNameModal').text('치유견 : '+healingDogName);
		$('#consultingNoModal').val(consultingNo);
		$('#consultingUserId').val(userId);
		$('#index').val(index);
	});
	
	$('#okModal').on('click',function(){
		var index = $('#index').val();	
		
		var consultingState = $('input[name="consultingState"]').val();
		var consultingNo = $('#consultingNoModal').val();
		var userId = $('#consultingUserId').val();
		
		if(consultingState == '1'){
			$.ajax(
					{
						url : "/ash/json/updateConsultingState/"+consultingNo+"/"+consultingState,
						method : "GET",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status){
							$($('td:nth-child(5)')[index]).text('진행');
							$($('a[name="btnConsulting"]')[index]).text('상담완료');
							$($('a[name="startConsulting"]')[index]).text('다시연결');
						}
					}
				)
		}else if(consultingState == '2'){
			$.ajax(
					{
						url : "/ash/json/updateConsultingState/"+consultingNo+"/"+consultingState,
						method : "GET",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status){
							$($('td:nth-child(5)')[index]).text('완료');
							$($('a[name="btnConsulting"]')[index]).css('display','none');
							$($('a[name="startConsulting"]')[index]).css('display','none');
						}
					}
				)
		}else if(consultingState == '3'){
			$.ajax(
					{
						url : "/ash/json/updateConsultingState/"+consultingNo+"/"+consultingState,
						method : "GET",
						dataType : "json",
						headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
						},
						success : function(JSONData , status){
							$($('td:nth-child(5)')[index]).text('취소');
							$($('a[name="btnConsulting"]')[index]).css('display','none');
							$($('a[name="startConsulting"]')[index]).css('display','none');
						}
					}
				)
		}else if(consultingState == '9'){
			popWin = window.open("https://withdog.herokuapp.com/chat/"+consultingNo+"/"+userId, "popWin", "left=100, top=100, width=1400, height=800, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
		}
	});
});

</script>

</head>

<body>

	<jsp:include page="/layout/common-header.jsp" />

	<!-- head section -->
	<section class="page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/305_bg.jpg" alt="" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 text-center animated fadeInUp">
				<div class="no-margin-top margin-one"></div>
				<!-- page title -->
				<h1 class="white-text tit_png">
					<img src="../images/sub/admin_tit.png">
				</h1>
				<!-- end page title -->
			</div>
		</div>
	</div>
	</section>
	<!-- end head section -->
	
	<section class="wow fadeIn">
	<div class="container">
	<div class="row">
	
		<!-- sidebar  -->
		<div class="col-md-2 col-sm-3 sidebar">
			<jsp:include page="/layout/mypage-sideBar.jsp" />
		</div>
		<!-- end sidebar  -->
		
		
		<!-- content -->
		<div class="col-md-9 col-sm-9 col-md-offset-1">

			<form id="myConsulting">
				<input type="hidden" id="currentPage" name="currentPage" value="" />
				<input type="hidden" id="consultingNoModal" name="consultingNo" value="" />
				<input type="hidden" id="consultingUserId" name="consultingUserId" value="" />
				<input type="hidden" name="consultingState" value="" />
				<input type="hidden" id="index" value=""/>
			</form>
			
			<h1 align="center">영상상담 관리</h1>
		    <hr/>
		    
		    <!--  table Start-->
		     <table class="table table-hover table-striped" >
		     
		       <thead>
		         <tr>
		           <th class="text-center">신청날짜</th>
		           <th class="text-center">신청자</th>
		           <th class="text-center">상담사</th>
		           <th class="text-center">치유견</th>
		           <th class="text-center">상태</th>
		           <th class="text-center"></th>
		         </tr>
		       </thead>
		      
			<tbody>
			
			  
			  <c:forEach var="list" items="${list}">
				<tr>
				  <td align="center" style="white-space: nowrap;">${list.regDate}</td>
				  <td align="center" style="white-space: nowrap;">${list.user.userId}</td>
				  <td align="center" style="white-space: nowrap;">${list.healingDog.healingDogHealer}</td>
				  <td align="center" style="white-space: nowrap;">${list.healingDog.healingDogName}</td>
				  <td align="center" style="white-space: nowrap;">${list.consultingState=='0'?"대기":list.consultingState=='1'?"진행":list.consultingState=='2'?"완료":"취소"}</td>
				  <td align="center" style="white-space: nowrap;">
				  	<div><a href="#modal-popup" class="popup-with-zoom-anim highlight-button-dark margin-three" name="btnConsulting" style='display: ${list.consultingState=="2"||list.consultingState=="3"?"none":""};'>${list.consultingState=="0"?"상담취소":"상담완료"}</a></div>
				  	<div><a href="#modal-popup" class="popup-with-zoom-anim highlight-button-dark" name="startConsulting" style='display: ${list.consultingState=="0"||list.consultingState=="1"?"":"none"};'>${list.consultingState=="0"?"상담시작":"다시연결"}</a></div>
			  		
			  		<input type="hidden" name="userId" value="${list.user.userId }"/>
			  		<input type="hidden" name="regDate" value="${list.regDate }"/>
			  		<input type="hidden" name="healingDogHealer" value="${list.healingDog.healingDogHealer}"/>
			  		<input type="hidden" name="healingDogName" value="${list.healingDog.healingDogName}"/>
			  		<input type="hidden" name="consultingNoList" value="${list.consultingNo }"/>
				  	
			  	  </td>
				 
				</tr>
		         </c:forEach>
		       
		       </tbody>
		     
		     </table>
		     <!-- end table -->
		     
		     <div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp" align="center">
                <!-- pagination -->
				<jsp:include page="../common/pageNavigator_new.jsp" />
                <!-- end pagination -->
            </div> 
            
            
             <!-- 알림팝업 -->
			<div class="col-md-9 col-sm-9 no-padding margin-five">
		
				<div class="col-lg-3 col-md-4 col-sm-5 center-col">
					<div id="modal-popup" class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white modal-popup-main">
			
					<div class="text-center">
						<span id="titleModal" class="black-text">모달제목</span>
						<p class="borderline-gray"></p>
					</div>
		
					<div class="text-center">
						<span id="regDateModal" class="text-small">신청날짜 : </span><br/>
						<span id="userIdModal" class="text-small">신청자 : </span><br/>
						<span id="healingDogHealerModal" class="text-small">상담사 : </span><br/>
						<span id="healingDogNameModal" class="text-small">치유견 : </span>
						<br/><br/>
					</div>
						
					
					<div class="text-center">
						<p id="textModal">모달내용</p>
					</div>
					
					
					<!-- 버튼 -->
					<div class="text-center no-margin-bottom">
						<a href="#" id="okModal"
							class="highlight-button-dark btn btn-medium no-margin popup-modal-dismiss">OK</a>
						<a href="#" id="cancelModal"
							class="highlight-button btn btn-medium no-margin-bottom popup-modal-dismiss">CANCEL</a>
					</div>
					<!-- end 버튼 -->
		
				</div>
			  </div>
			</div>
			<!-- end 알림팝업 -->


		</div>
		<!-- end content  -->
	
	</div>
	</div>
	</section>
	
	<jsp:include page="../layout/footer.jsp" />

	<jsp:include page="../common/js.jsp" />

</body>

</html>