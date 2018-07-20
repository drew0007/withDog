<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link href='/css/fullcalendar.css' rel='stylesheet' />

<jsp:include page="/common/css.jsp" />


<title>동물교감치유 서비스 예약</title>
<style type="text/css">
#calendarTool{
	position: absolute;
	left: 50%; 
	top: 50px;
	transform: translateX(-50%);
}
#writeField{
    top: 300px;
    left: 10px;
    right: 10px;
    bottom: 300px;
    border-color: black;
    border-style: solid;
}
.fc-sun {color:#e31b23; background-color: } /*일요일*/
.fc-sat {color:#007dc3;background-color: } /*토요일*/

</style>
</head>


<body>
<script type="text/javascript">
$(function () {
	window.close();
	
	$(".popup-with-zoom-anim").on("click", function () {
		var index = $(".popup-with-zoom-anim").index(this);
		$("#selectDogName").html($($(".name")[index]).text()+ "<br /> 동물교감치유를 예약하시겠습니까?")
	})
	
})


$(function() {

	  $('#calendar').fullCalendar({
		  header: {
		        right: 'prev,next today',
		        left: 'title',
		      },
		      selectable: true,
		      //selectOverlap :false, //선택못하게함
		      eventLimit: true,
		      eventLimitText :"더보기",
			  events : getAllReservation(), //end ajax
		      eventRender: function(event, eventElement) { if (event.imageurl) { eventElement.find("span.fc-title").prepend("<img src='" + event.imageurl +"' width='20' height='20'>"); } },
			  
		  	  dayClick: function(date) {
		  		$($(".goModal")).html('')
		  			  $.ajax({
		  				  url : "/ash/json/getHealingDogListByDate/"+date.format(),
		  				  method : "GET",
		  				  dataType : "json",
			 			  headers : {
							"Accept" : "application/json",
							"Content-Type" : "application/json"
							},
		  				  success : function (data) {
		  					$("input[name=reservationDate]").val(date.format())
		  					  console.log(data)
		  					for(var i = 0; i<data.healingDogs.length; i++){
			  					  $($(".goModal")[i]).html('<input class="healingDogNo" type="hidden" value="'+data.healingDogs[i].healingDogNo+'"> <img src="/images/uploadFiles/healingDog/'+data.healingDogs[i].healingDogimage+'" width="400px" height="400px" alt=""><span class="name black-text">'+data.healingDogs[i].healingDogName+'</span> <p id="aaa" class="breed center-col width-90">'+data.healingDogs[i].healingDogBreed.dogBreedKO+' &nbsp; | &nbsp; '+data.healingDogs[i].healingDogBirth+'살 &nbsp; | &nbsp; '+data.healingDogs[i].healingDogChar+'</p> </a>');
		  					}
						} //end success
		  			  })
			  },
		  monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		  dayNames: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
		  dayNamesShort: ["일","월","화","수","목","금","토"],
	  })
	  
	  $(".goModal").on("click", function() { //동물 선택 시 도그넘버 form -> input에 입력
		  var index = $(".goModal").index(this);
		  var dogNo =$($(".healingDogNo")[index]).val() 
		  $("input[name=healingDogNo]").val(dogNo)
		  
	})
	
	  $("#ok").on("click", function() {
		  var userId = "${user.userId}";
			if(userId==null || userId=="" || userId==" "){
				alert("로그인 후 이용가능합니다.")
				return;
			}
			else{
				  if($("input[name=reservationDate]").val()==""){
					  alert("예약하실 날짜를 선택하셔야 합니다.")
				  }else{
					  $("form").attr("method","post").attr("action","/ash/getCheckReservationASH").attr("enctype","multipart/form-data").submit();
				  }
			}
	})
	
			/////////전체보기 클릭 시 다시 전체 이벤트 가져오기
			$("#viewAll").on("click", function () { 

				$('#writeDate').val("")
				$('#writeDog').val("")
				$('#calendar').fullCalendar ( 'removeEvents')
				$('#calendar').fullCalendar ({
					events : getAllEvents(),
					

				})
			})
			////////////////////
			
			$("button.fc-next-button").click(function() { //다음 버튼 선택시
// 				$('#calendar').fullCalendar ( 'removeEvents')
				$('#calendar').fullCalendar({
					   events : getAllReservation()
					   })
		    });
	  
			$("button.fc-prev-button").click(function() { //이전 버튼 선택시
// 				$('#calendar').fullCalendar ( 'removeEvents')
				$('#calendar').fullCalendar({
					   events : getAllReservation()
					   })
		    });
			
			$("button.fc-today-button").click(function() { //today 버튼 선택시
// 				$('#calendar').fullCalendar ( 'removeEvents')
				$('#calendar').fullCalendar({
					   events : getAllReservation()
					   })
		    });
		}); //end Calendar Function
		
		function getAllReservation() { 	//////// 전체 이벤트 받아오기
			 $.ajax(
		  	    		{
		  	    			url : "/ash/json/getAllReservationASHList",
		  	    			method : "GET",
		  	    			dataType : "json",
		  	    			success : function (data) {
		    					console.log(data)
			    				for (var i = 0; i < data.length; i++) {
			    				
									var date = moment(data[i].start);
										if (date.isValid()) {
											$('#calendar').fullCalendar('renderEvent', {
												title : data[i].title+" "+data[i].time,
												color: data[i].color,
												start : date,
												allDay : true,
												imageurl: "/images/uploadFiles/healingDog/"+data[i].image
												
										});
									} else {
										alert('Invalid date.');
									}
								}
		    			}//end for
							})
			
		}

</script>



	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/303_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/303_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section>
            <div class="container">
            
            	<!-- 동물교감치유 -->
            	<div class="row margin-ten-bottom">
            	
            		<!-- section title  -->
                   	<div class="col-md-12 text-center margin-five-bottom">
                      		<span class="title-small black-text  font-weight-600">동물교감치유 예약 <br/>(Animal Sympathetic Healing Reservation)</span>
                       	<div class="separator-line-thick bg-black margin-one xs-margin-top-five"></div>
                	   </div>
                   	<!-- end section title -->
                    	
          		  	<!-- 캘린더 -->
            		<div id="calendar" class="col-md-11 col-sm-12 center-col text-center">
            			
            		</div>
            		<!-- end 캘린더 -->

				</div>
            	<!-- end 동물교감치유  -->
            	
            	
            	
            	<!-- 치유견 리스트 -->
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                    	
                    	<!-- section title  -->
                    	<div class="col-md-12 text-center margin-five-bottom">
                       		<span class="title-small black-text font-weight-600">예약 가능한 치유견 <br>(HealingDog List)</span>
                        	<div class="separator-line-thick bg-black margin-one xs-margin-top-five"></div>
                 	   </div>
                    	<!-- end section title -->
                    
                        <!-- dog 01 -->
                        <c:forEach var="list" items="${list}">
	                    <div class="info col-md-3 col-sm-3 healingdog-style text-center xs-margin-bottom-ten">
	                        <a href="#modal-popup"class="goModal popup-with-zoom-anim">
	                        	<input class="healingDogNo" type="hidden" value="${list.healingDogNo}">
		                        <img  src = "/images/uploadFiles/healingDog/${list.healingDogimage}" width="400px" height="400px" alt=""/>
		                        <span class="name black-text">${list.healingDogName}</span>
	                        	<p class="breed center-col width-90">${list.healingDogBreed.dogBreedKO} &nbsp; | &nbsp; ${list.healingDogBirth}살 &nbsp; | &nbsp; ${list.healingDogChar}</p>
	                        </a>
	                    </div>
	                    </c:forEach>
	                    <!-- end dog 01 -->
						<form>
                  			<input name="healingDogNo" type="hidden" value="">
                  			<input name="reservationDate" type="hidden" value="">
                  		</form>
	                    
	                    <!-- 알림팝업 -->
						<div class="modal fade col-md-9 col-sm-9 no-padding margin-five">
			
							<div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
								<div id="modal-popup" class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
						
								<div>
									<span class="black-text">예약 확인</span>
									<p class="borderline-gray"></p>
								</div>

								<p id="selectDogName" class="text-small">[치유견이름] <br /> 동물교감치유를 예약하시겠습니까?</p>
								
								<!-- 버튼 -->
								<div class="text-center no-margin-bottom modal-footer">
									<a id="ok" style="cursor: pointer;" class="highlight-button-dark btn btn-medium no-margin">OK</a>
								</div>
								<!-- end 버튼 -->

						</div>
					</div>

				</div>
				<!-- end 알림팝업 -->
	                    
	                    
                        </div>
                    </div>
                    <!-- end 치유견 리스트 -->
                    
                </div>
        </section>
        <!-- end content section -->
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>