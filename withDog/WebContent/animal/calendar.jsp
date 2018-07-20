<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<link href='/css/fullcalendar.css' rel='stylesheet' />
<script src='/js/moment.min.js'></script>
<script src='/js/fullcalendar.js'></script>
<style type="text/css">
#calendarTool{
	position: absolute;
	left: 50%; 
	top: 50px;
	transform: translateX(-50%);
}
#calendar{
    top: 50px;
    left: 300px;
    right: 300px;
    height: 700px;
	width: 700px;
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

$(function() {

  $('#calendar').fullCalendar({
	  header: {
	        right: 'prev,next today',
	        center: 'title',
	        left: 'viewOne'
	      },
	      selectable: true,
	      //selectOverlap :false, //선택못하게함
	      eventLimit: true,
	      eventLimitText :"더보기",
		  events : getAllEvents(), //end ajax
	      eventRender: function(event, eventElement) { if (event.imageurl) { eventElement.find("span.fc-title").prepend("<img src='" + event.imageurl +"' width='12' height='12'>"); } },
		  
	  	  dayClick: function(date) {
	  		  if($("#writeDog").val()!=""){
	  			  $.ajax({
	  				  url : "/scheduler/json/getSchedulerCount/",
	  				  method : "POST",
	  				  dataType : "json",
		 			  headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
						},
	  				  data : JSON.stringify({
	   					healingDogNo : $("#writeDogNum").val(),
	   					  start : date.format()
	  				  }),
	  				  success : function (data) {
	  					 if(data >=2){
						alert("이미 예약이 차있습니다.")
						$("#writeDate").val("")
	  					 }else{
	  						 $.ajax({
	  			  				  	url : "/scheduler/json/getSchedulerTimeCount/",
	  			  				  	method : "POST",
	  		  				  	  	dataType : "json",
	  			 			  		headers : {
			  							"Accept" : "application/json",
			  							"Content-Type" : "application/json"
			  							},
			  		  				  data : JSON.stringify({
			  		  					healingDogNo : $("#writeDogNum").val(),
			  		   					start : date.format()
			  		  				  }),
			  		  				  success : function (data) {
										if(data=="0"){
											$("#writeTime option:first").attr('disabled',true)
										}else if(data=="1"){
											$("#writeTime option:last").attr('disabled',true)
										}
									}
			  						 }) //end ajax
			  						 
	  						 
	  						 
	  						 
	  					 }
					}
	  			  })
	  		  }
		  	
		    $("#writeDate").val(date.format());

		  },
	  monthNames: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
	  dayNames: ["일요일","월요일","화요일","수요일","목요일","금요일","토요일"],
	  dayNamesShort: ["일","월","화","수","목","금","토"],
  })

  /////해당 견에 대한 예약정보만 표시
     $(".viewButton").on("click", function () {
    
    	 switch ($(this).val()){
    	 case '10000' : $("#writeDog").val("복이"); $("#writeDogNum").val($(this).val());$("#writeDogColor").val("green");
    	 break;
    	 case '10001' : $("#writeDog").val("멍멍이");$("#writeDogNum").val($(this).val());$("#writeDogColor").val("blue");
    	 break;
    	 case '10002' : $("#writeDog").val("강아지");$("#writeDogNum").val($(this).val());$("#writeDogColor").val("red");
    	 break;
    	 }
    	 
		   $('#calendar').fullCalendar ( 'removeEvents')
		 	   $('#calendar').fullCalendar({
		 		  
					   events :$.ajax(
				  	    		{
				  	    			url : "/scheduler/json/getScheduler/"+$(this).val(),
				  	    			method : "GET",
				  	    			dataType : "json",
				  	    			headers  : {
				  	    				"Accept" : "application/json",
				  						"Content-Type" :  "application/json"
				  	    			},
				  	    			success : function (data) {
				  	    				console.log(data)
				  	    				for (var i = 0; i < data.length; i++) {
				  	    				
											var date = moment(data[i].start);
												if (date.isValid()) {
													$('#calendar').fullCalendar('renderEvent', {
														title : data[i].title+" "+data[i].time,
														color: data[i].color,
														start : date,
														allDay : true
														
													});
												} else {
													alert('Invalid date.');
												}
											}
				  	    			}//end for
										}), //end ajax
					   })
		})  
		///////////
		
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
  
  ///////////예약하기 버튼 시
		$("#reserveButton").on("click", function() {
			
			if($("#writeDog").val()=="" || $("#writeDate").val()=="" ||$("#writeTime").val()=="3"){
				alert("입력사항을 모두 입력해주세요")
				return;
			}else{
	
			$(function() {
				var dateStr = $("#writeDate"). val()
				var date = moment(dateStr);

				if (date.isValid()) {
					$('#calendar').fullCalendar('renderEvent', {
						title : '예약완료',
						start : date,
						allDay : true,
					});
					alert('예약완료... DB에 저장합니다.');
				} else {
					alert('Invalid date.');
				}
			})

			$.ajax({
				url : "/scheduler/json/addScheduler",
				method : "POST",
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					healingDogNo : $("#writeDogNum").val(),
					title : $("#writeDog").val(),
					start : $("#writeDate").val(),
					end : $("#writeDate").val(),
					color : $("#writeDogColor").val(),
					time : $("#writeTime").val()
				}),
				success : function(data) {
					alert("성공")
					alert(data)
				}
			})
}
		}) //end reservation button click
		
		$("button.fc-next-button").click(function() { //다음 버튼 선택시
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
  
		$("button.fc-prev-button").click(function() { //이전 버튼 선택시
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
		
		$("button.fc-today-button").click(function() { //today 버튼 선택시
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
	}); //end Calendar Function
	

	function getEvents() {      //////// Events 선택해서 얻어오기 (next, prev, today 버튼 선택 시)
		$.ajax(
  	    		{
  	    		
  	    			url : ($("#writeDog").val()!="") ? "/scheduler/json/getScheduler/"+$("#writeDogNum").val() :  "/scheduler/json/getAllScheduler",
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
										allDay : true
										
									});
								} else {
									alert('Invalid date.');
								}
							}
  	    			}//end for
						}) //end ajax
	}
	
	function getAllEvents() { 	//////// 전체 이벤트 받아오기
		 $.ajax(
	  	    		{
	  	    			url : "/scheduler/json/getAllScheduler",
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
											imageurl: "../images/uploadFiles/pic1.PNG"
											
									});
								} else {
									alert('Invalid date.');
								}
							}
	    			}//end for
						})
		
	}

	
</script>
<div id="calendarTool">
<div id='calendar'>

	<br/>
	<br/>
	예약날짜<input type="text" id="writeDate"/>
	예약시간<select id="writeTime">
	<option value="3" selected="selected">시간선택</option>
	<option value="0" >오전(10:00~13:00)</option>
	<option value="1">오후(15:00~18:00)</option>
	</select>
	<hr/>
	치유견<input type="text" id="writeDog"/><input type="hidden" id="writeDogNum"/>
	<input type="hidden" id="writeDogColor"/>
	<hr/>
	<button  value="" id="viewAll">전체보기</a>
	<button class="viewButton" value="10000" id="viewOne">치유견1</button>
	<button class="viewButton" value="10001" id="viewTwo">치유견2</button>
	<button class="viewButton" value="10002" id="viewThree">치유견3</button>
	<button id="reserveButton" style="background-color: aqua;">예약하기</a>
</div>
</div>
</body>
</html>