<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="google-signin-client_id" content="730614234797-kcpkvfk21csstmbunn2hh21tcqjsik71.apps.googleusercontent.com">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 <script src="http://malsup.github.com/jquery.form.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
    #dropzone
    {
        border:2px dotted #3292A2;
        width:300px;
        height:300px;
        color:#92AAB0;
        text-align:center;
        vertical-align:middle;
        font-size:18px;
        margin-top:10px;
        display: inline-block;
        line-height: 300px;

        
    }
	#dropzone img {
		display: block;
		width: 90%;
		height: 90%;
		padding-top:15px;
		padding-bottom:15px;
		padding-left: 15px;
		padding-right: 15px;
	}


</style>



</head>

<body>
<div id="dropzone">사진을 드래그 하거나 터치하세요</div>
<input style="display: none;" type="file" id="testfile" name="testfile" >
	<div id="resultDiv">
	<table width="600px" border="1">
	<thead >
	<tr align="center">
	<th colspan="3">분석결과</th>
	</tr>
	<tr>
	<th  width="40%" class = "text-center">견종</th>
	<th width="20%" class = "text-center">일치율</th>
	</tr>
	</thead>
	<tbody id="breed" align="center">
          <!-- 이름과 확률이 들어갈 공간 -->
         
           
	</tbody>
	</table>
	<hr/>
	 <div style="border: 2px solid; padding: 3px"  id="detail">
	 상세 정보 출력 박스
	 </div>
	 <div style="border: 2px solid; padding: 3px"  id="youtube">
	 유튜브영상
	 </div>      
	
	<a href=""></a>
	<a ></a>
	</div>
<script>
$(function () {
    var obj = $("#dropzone");
    var upload = $('input:file')[0]
    
    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px solid #5272A0');
    });

    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');
    });

    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });
    
    $(obj).on("click" , function name() {
		$(upload).click();
	})
    
    /////1. 터치해서 업로드/////
    $(upload).on("change", function (e) {
		e.preventDefault();  // 기본적인 서브밋 행동을 취소합니다
		
	
		var file = upload.files[0]
		
		var reader = new FileReader();
	
		reader.onload = function (event) {
			obj.text("");
			var img = new Image();
			img.src = event.target.result;
			var a = (img.src).split(',');
			
			/////////
			googleVision(a)
			/////////
			obj.append(img);

			
			}
		reader.readAsDataURL(file);// File에서 읽어 오는 역할
		
		return false;
         
         if(file.length < 1)
              return;
    });
	////////////////////////////////////////////////
	
	
	////2. 드래그앤 드롭 업로드 /////
    obj.on('drop', function (e) {
    	console.log(obj.text())
    	obj.text("");
         e.preventDefault();
         $(this).css('border', '2px dotted #8296C2');

         var file = e.originalEvent.dataTransfer.files[0];
         var reader = new FileReader();
         
		reader.onload = function (event) {
			var img = new Image();
			img.src = event.target.result;
			var a = (img.src).split(',');
			console.log(a[1])
			
			//////////////
			
			googleVision(a)
			
			//////////////
			obj.append(img);
			
		}
         //})
		reader.readAsDataURL(file);// File에서 읽어 오는 역할
		
		return false;
         
         if(file.length < 1)
              return;

    });

});



function googleVision(a) {
	var b = new Array();
	var API_KEY = "AIzaSyCpd2pSdt8bSgAda5GLaVjeL8xxv4-Ma9M"
		//console.log("Base64 : " + result)
			$(function () {
				var p = {
						"requests" : [ {
							"image" : {
								"content" : a[1]
							},
							"features" : [ {
								"type" : "LABEL_DETECTION", //Web detection 어떨지
								"maxResults" : 20
							} ]
						} ]
					}
				$.ajax(
						{
							url : "https://vision.googleapis.com/v1/images:annotate?key="+API_KEY,
							method : "POST",
							dataType : "json",
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							data : JSON.stringify(p),
							success : function(data,status) {
								console.log(data)
								$("#breed").empty();
								for (var i = 0; i < data.responses[0].labelAnnotations.length; i++) {
									var kind = data.responses[0].labelAnnotations[i].description
									var rate = Math.floor((data.responses[0].labelAnnotations[i].score)*100)
									console.log(kind)
									console.log(rate)
									console.log((kind.indexOf("dog")))
	
										///////자체번역
										$.ajax(
												{
													url : "/dictionary/json/getTranslate",
													method : "POST",
													dataType : "json",
													async: false,
													headers : {
														"Accept" : "application/json",
														"Content-Type" : "application/json"
													},
													data : JSON.stringify({
														en : kind
													}),
													success : function(data,status) {
														if (data.key==null || data.key==""){
														}else{
														
														var inputData = "<tr><td>"+data.key+"</td> <td>"+rate+"%</td>"						
														$("#breed").append(inputData)
														
														}
													},//end success function
													fail : function (error) {
														alert(error)
													}
												});
										//자체번역
								} //end for
								
								////////////////////////////// 정보출력 
								$(function () {
									$("td:nth-child(1)").on("click", function () {
										$("#detail").empty();
										//$("#youtube").empty();
										$("#youtube").html("")
										$("#youtube").html("<div id='youtube2'></div>")
										
										var  index = $("td:nth-child(1)").index(this);
										var name = $($("td:nth-child(1)")[index]).text();
										$.ajax({
										url : "/dictionary/json/getDetail",
										method : "post",
										dataType : "json",
										headers : {
											"Accept" : "application/json",
											"Content-Type" :  "application/json"
										},
										data : JSON.stringify({
											ko : name
										}),
										success : function (data) {
											console.log(data)
											var inputData = "이름 : "+data.detail[0].ko+"<br/> 신장 : " + data.detail[0].height + "<br/> 체중 : " + data.detail[0].weight
											+ "색깔 : " + data.detail[0].color + "<br/> 출생지 : " + data.detail[0].placeOfBirth + "<br/> 성격 : " + data.detail[0].personality
											+ "<br/> 링크 : " + "<a href="+data.detail[0].link+">" + data.detail[0].link+"</a>"
											$("#detail").append(inputData)
											
											/////////////////유튜브
											$.ajax(
													{
													  dataType: "json",
													  url: 
													    'https://www.googleapis.com/youtube/v3/search'+
													    '?part=snippet'+
													    '&maxResults=5'+
													    '&order=viewCount'+
													    '&q='+data.detail[0].en+"'"+
											//		    '&q=태연'+
													    '&type=video'+
													    '&videoDefinition=high'+
													    '&regionCode =ISO 3166-1 alpha-2'+
													    '&key=AIzaSyDY67FTw3lBX8Xc3oIFei_nXw4vsTS6ib8',
													success : function(data){
														
														console.log(data)
														console.log(data.items[0].id.videoId)
														var tag = document.createElement('script');
														tag.src = "https://www.youtube.com/iframe_api";
														var firstScriptTag = document.getElementsByTagName('script')[0];
														firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
														var player;
														onYouTubeIframeAPIReady = function(event){
															   player = new YT.Player('youtube2', {
															     height: '360',
															     width: '640',
															     videoId: data.items[0].id.videoId,
															     events: {
															       'onReady': onPlayerReady,
															       //'onStateChange': onPlayerStateChange
															     }
															   });
															 }
													}
													});
											
											/////////유튜브
											
										}
										})
										
									})
								})
								////////////////////////////정보출력
							},//end success function
							fail : function (error) {
								alert(error)
							}
						}); // end ajax
			})
	
}


 function onPlayerReady(event) {
     event.target.playVideo();
   }
//  var done = false;
//  function onPlayerStateChange(event) {
//    if (event.data == YT.PlayerState.PLAYING && !done) {
//      setTimeout(stopVideo, 6000);
//      done = true;
//    }
//  }
 function stopVideo() {
   player.stopVideo();
 }


</script>






</body>

</html>