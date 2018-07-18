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
.fc-sun {color:#e31b23; background-color: } /*�Ͽ���*/
.fc-sat {color:#007dc3;background-color: } /*�����*/

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
	      //selectOverlap :false, //���ø��ϰ���
	      eventLimit: true,
	      eventLimitText :"������",
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
						alert("�̹� ������ ���ֽ��ϴ�.")
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
	  monthNames: ["1��","2��","3��","4��","5��","6��","7��","8��","9��","10��","11��","12��"],
	  dayNames: ["�Ͽ���","������","ȭ����","������","�����","�ݿ���","�����"],
	  dayNamesShort: ["��","��","ȭ","��","��","��","��"],
  })

  /////�ش� �߿� ���� ���������� ǥ��
     $(".viewButton").on("click", function () {
    
    	 switch ($(this).val()){
    	 case '10000' : $("#writeDog").val("����"); $("#writeDogNum").val($(this).val());$("#writeDogColor").val("green");
    	 break;
    	 case '10001' : $("#writeDog").val("�۸���");$("#writeDogNum").val($(this).val());$("#writeDogColor").val("blue");
    	 break;
    	 case '10002' : $("#writeDog").val("������");$("#writeDogNum").val($(this).val());$("#writeDogColor").val("red");
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
		
		/////////��ü���� Ŭ�� �� �ٽ� ��ü �̺�Ʈ ��������
		$("#viewAll").on("click", function () { 

			$('#writeDate').val("")
			$('#writeDog').val("")
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar ({
				events : getAllEvents(),
				

			})
		})
		////////////////////
  
  ///////////�����ϱ� ��ư ��
		$("#reserveButton").on("click", function() {
			
			if($("#writeDog").val()=="" || $("#writeDate").val()=="" ||$("#writeTime").val()=="3"){
				alert("�Է»����� ��� �Է����ּ���")
				return;
			}else{
	
			$(function() {
				var dateStr = $("#writeDate"). val()
				var date = moment(dateStr);

				if (date.isValid()) {
					$('#calendar').fullCalendar('renderEvent', {
						title : '����Ϸ�',
						start : date,
						allDay : true,
					});
					alert('����Ϸ�... DB�� �����մϴ�.');
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
					alert("����")
					alert(data)
				}
			})
}
		}) //end reservation button click
		
		$("button.fc-next-button").click(function() { //���� ��ư ���ý�
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
  
		$("button.fc-prev-button").click(function() { //���� ��ư ���ý�
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
		
		$("button.fc-today-button").click(function() { //today ��ư ���ý�
			$('#calendar').fullCalendar ( 'removeEvents')
			$('#calendar').fullCalendar({
				   events : getEvents()
				   })
	    });
	}); //end Calendar Function
	

	function getEvents() {      //////// Events �����ؼ� ������ (next, prev, today ��ư ���� ��)
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
	
	function getAllEvents() { 	//////// ��ü �̺�Ʈ �޾ƿ���
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
	���೯¥<input type="text" id="writeDate"/>
	����ð�<select id="writeTime">
	<option value="3" selected="selected">�ð�����</option>
	<option value="0" >����(10:00~13:00)</option>
	<option value="1">����(15:00~18:00)</option>
	</select>
	<hr/>
	ġ����<input type="text" id="writeDog"/><input type="hidden" id="writeDogNum"/>
	<input type="hidden" id="writeDogColor"/>
	<hr/>
	<button  value="" id="viewAll">��ü����</a>
	<button class="viewButton" value="10000" id="viewOne">ġ����1</button>
	<button class="viewButton" value="10001" id="viewTwo">ġ����2</button>
	<button class="viewButton" value="10002" id="viewThree">ġ����3</button>
	<button id="reserveButton" style="background-color: aqua;">�����ϱ�</a>
</div>
</div>
</body>
</html>