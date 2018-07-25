<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<jsp:include page="/common/css.jsp" />


<title>유기견 입양 공고 리스트</title>

</head>

<script type="text/javascript">

$(function(){
	// 처음에 리스트 들고옴
	fncGetAbandDogList();
	
	// 처음 견종 리스트 들고오는 부분
	$.ajax({
		url : "/abandDog/json/dogBreed",
		method : "GET",
		datatype : "json",
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success : function (data) {		
			for(var key in data){
				$("select[name='dogBreed']").append($('<option value='+key+'>'+data[key]+'</option>'));
			}
		}
		
	}); //end of ajax	
	
	// 처음 시도 리스트 들고오는 부분
	$.ajax({
		url : "/abandDog/json/sido",
		method : "GET",
		datatype : "json",
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success : function (data) {		
			for(var key in data){
				$("select[name='sido']").append($('<option value='+key+'>'+data[key]+'</option>'));
			}
		}
		
	}); //end of ajax
	
	// 시도를 변경하면 해당 시군구를 들고오는 부분
	$("select[name='sido']").on("change", function(){
		$("select[name='sigungu']").val("");
		var sido = $("select[name='sido']").val();
		
		if(sido == ""){
			$('#sigungu').css('display', 'none');
		}
		
		$.ajax({
			url : "/abandDog/json/sigungu/"+sido,
			method : "GET",
			datatype : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function (data) {		
				$('#sigungu').css('display', 'block');
				for(var key in data){
					$("select[name='sigungu']").append($('<option value='+key+'>'+data[key]+'</option>'));
				}
			}
		}); //end of ajax
	});
});

$(function(){	
	// 페이지 네비
	$(document).on("click", ".pageNum", function(){
		var index = $(".pageNum").index(this);
		var page = $($(".pageNum")[index]).text();
		$('input[name="currentPage"]').val(page);
		fncGetAbandDogList();
	});
	
	// 견종 선택하면 조회
	$("select[name='dogBreed']").on("change", function(){
		$('input[name="currentPage"]').val('0');ㄴ
		fncGetAbandDogList();
	});
	
	// 시도 - 시군구 선택하면 조회
	$("select[name='sido']").on("change", function(){
		$('input[name="currentPage"]').val('0');
		fncGetAbandDogList();
	});
	
	// 시도 - 시군구 선택하면 조회
	$("select[name='sigungu']").on("change", function(){
		$('input[name="currentPage"]').val('0');
		fncGetAbandDogList();
	});
	
	// 상세조회 모달창에서 확인버튼
	$("#searchModal").on("click", function(){
		$('input[name="currentPage"]').val('0');
		fncGetAbandDogList();
	});
	
	// 상세조회 모달창에서 취소버튼
	$("#cancelModal").on("click", function(){
		
	});
	
	// 리스트 이미지 클릭
	$("a[name='abandImage']").on("click", function(){
		var index = $("a[name='abandImage']").index(this);
		alert(index);
	});
});


function fncGetAbandDogList(){
	var currentPage = $('input[name="currentPage"]').val();
	var dogBreed = $("select[name='dogBreed']").val();
	var sido = $("select[name='sido']").val();
	var sigungu = $("select[name='sigungu']").val();
	var abandDogGender = $('input[name="abandDogGender"]:checked').val();
	var abandDogState = $('input[name="abandDogState"]:checked').val();
	
	$.ajax({
		url : "/abandDog/json/getAbandDogList?currentPage="+currentPage+"&dogBreed="+dogBreed+"&sido="+sido+"&sigungu="+sigungu+"&abandDogGender="+abandDogGender+"&abandDogState="+abandDogState,
		method : "GET",
		datatype : "json",
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		success : function(JSONData , status) {							
			$("#abandDogList").html("");
			for(i=0; i<JSONData.list.length; i++){
				console.log(JSONData.list[i].desertionNo);
				var str='<li>'
                	+'<figure>'
                	+'<div class="gallery-img" style="height: 200px"><a href="#" style="width:100%; height:100%;"><img src="'+JSONData.list[i].abandDogImage+'" alt="" style="width:100%; height:100%; margin:0 auto;"></a></div>'
                	+'<figcaption>'
                    +'<h3 class="dog margin-two-bottom"><a href="#">'
                    /* +'<img src="../images/sub/'+(JSONData.list[i].abandDogGender == "M"?"male":"female")+' .png" width="40"/>' */
                    +JSONData.list[i].abandDogBreed+' - '+JSONData.list[i].abandDogAge+JSONData.list[i].abandDogGender+'</a></h3>'
                    +'<p class="text-small">'+JSONData.list[i].orgNm+'</p>'
                	+'</figcaption>'
            		+'</figure>'
        			+'</li>';
        			
				$("#abandDogList").append(str);
			}
			
			$("#abandDogListPage").html("");		
			str = '';
			
			 if (JSONData.resultPage.currentPage <= JSONData.resultPage.pageUnit){
				str = '<a class="disabled pagePre"><img src="../images/arrow-pre-small.png" alt="" /></a>';
			}else{
				str = '<a class="pagePre" aria-label="Previous"><img src="../images/arrow-pre-small.png" alt="" style="cursor:pointer;"/></a>';
			}
			
			for(i=JSONData.resultPage.beginUnitPage; i<=JSONData.resultPage.endUnitPage; i++){
				if(JSONData.resultPage.currentPage == i){
					str = str + '<a style="cursor:pointer;" class="active pageNum">'+i+'</a>';
				}else{
					str = str + '<a style="cursor:pointer;" class="pageNum">'+i+'</a>';
				}
			}

			 if (JSONData.resultPage.endUnitPage >= JSONData.resultPage.maxPage){
				 str = str + '<a class="disabled pageNxt"><img src="../images/arrow-next-small.png" alt="" /></a>';
				
			}else{
				str = str + '<a class="pageNxt" aria-label="Previous"><img src="../images/arrow-next-small.png" alt="" style="cursor:pointer;"/></a>';
			}

			$("#abandDogListPage").append(str);
			
			$('.pagePre').on('click', function(){
				if($(".pagePre").hasClass("disabled") == false){
					$('input[name="currentPage"]').val(JSONData.resultPage.currentPage-1);
					fncGetAbandDogList();
				}
			});
			$('.pageNxt').on('click', function(){
				if($(".pageNxt").hasClass("disabled") == false){
					$('input[name="currentPage"]').val(JSONData.resultPage.endUnitPage+1);
					fncGetAbandDogList();
				}
			});
		}
		
	}); //end of ajax	
}

</script>


<body>

	<jsp:include page="/layout/common-header.jsp" />
	
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/400_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/402_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section id="key-person" class="no-padding-top animate slow-mo even fadeIn" data-anim-type="fadeIn" data-anim-delay="200">
        
	        	<!-- search -->
	        	<section class="bg-deep padding-three">
	            <div class="container ">
	                <div class="row sm-text-center no-margin">
	                                  	
	                                  	
	                    
	                    <div class="col-md-3 col-sm-3 pull-left no-margin" style="height:47px">
	                              <div class="select-style input-round med-input">
	                                  <select name="dogBreed">
	                                      <option value="">견종</option>	                                  	
	                                  </select>
	                              </div>
	                      </div>
	                      
	                      <div class="col-md-3 col-sm-3 pull-left no-margin"style="height:47px">
	                              <div class="select-style input-round med-input" >
	                                  <select name="sido">
	                                      <option value="">시도</option>
	                                  </select>
	                              </div>
	                      </div>
	                      
	                      <div class="col-md-3 col-sm-3 pull-left no-margin" style="height:47px; display:none" id="sigungu">
	                              <div class="select-style input-round med-input">
	                                  <select name="sigungu" >
	                                      <option value="">시군구</option>
	                                  </select>
	                              </div>
	                      </div>
	                      
	                       <a class="highlight-button btn btn-medium button xs-margin-bottom-five xs-no-margin-right input-round pull-right popup-with-zoom-anim" href="#modal-popup" >상세조건</a>

					</div>
					
	            </div>

				<!-- 상세조건 팝업 -->
				<div class="col-md-12 col-sm-9 no-padding margin-five">
			
					<div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
						<div id="modal-popup"
							class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
			
							<div>
								<span class="black-text">상세조건 검색</span>
								<p class="borderline-gray"></p>
							</div>
							
							<form>
							
								<!-- 조건 1 -->
								<div class="form-group no-margin-bottom">
									<!-- input  -->
									<div class="text-center col-md-12 clearfix">
										<div class="no-padding pull-left">
										<label class="font-weight-600" style="margin-top:14px;">성별</label>
										</div>
										<div class="pull-right no-padding" style="display: flex;  justify-content: center; align-items: center;	">
											<input type="radio" name="abandDogGender" id="abandDogGender1" class="checkbox" value="" checked>
											<label for="abandDogGender1" class="input-label radio">전체</label>
											<input type="radio" name="abandDogGender" id="abandDogGender2" class="checkbox" value="M">
											<label for="abandDogGender2" class="input-label radio">남아</label>
											<input type="radio" name="abandDogGender" id="abandDogGender3" class="checkbox" value="F">
											<label for="abandDogGender3" class="input-label radio">여아</label>
										</div>
									</div>
									<!-- end input  -->
								</div>
								<!-- end 조건 1 -->
								
								<!-- 조건 2 -->
								<div class="form-group no-margin-bottom">
									<!-- input  -->
									<div class="text-center col-md-12 clearfix">
										<div class="no-padding pull-left">
										<label class="font-weight-600" style="margin-top:14px;">상태</label>
										</div>
										<div class="pull-right no-padding" style="display: flex;  justify-content: center; align-items: center;">
											<span class="pull-right text-right">
												<input type="radio" name="abandDogState" id="abandDogState1" class="checkbox" value="" checked>
												<label for="abandDogState1" class="input-label radio">전체</label>
												<input type="radio" name="abandDogState" id="abandDogState2" class="checkbox" value="notice">
												<label for="abandDogState2" class="input-label radio">공고중</label>
												<input type="radio" name="abandDogState" id="abandDogState3" class="checkbox" value="protect">
												<label for="abandDogState3" class="input-label radio">보호중</label>
											</span>
										</div>
									</div>
									<!-- end input  -->
								</div>
								<!-- end 조건 2 -->
								
								<!-- 조건 3 -->
								<div class="form-group no-margin-bottom">
									<!-- input  -->
									<div class="text-center col-md-12 clearfix">
										<div class="no-padding pull-left">
										<label class="font-weight-600" style="margin-top:14px;">출생년도(추정)</label>
										</div>
										<div class="slidecontainer">
										  <input type="range" min="1" max="100" value="50" class="slider" id="myRange" data-slider-step="2">
										</div>
									</div>
									<!-- end input  -->
								</div>
								<!-- end 조건 3 -->
								
								
							</form>
			
							<!-- 버튼 -->
							<div class="text-center no-margin-bottom">
								<a href="#" class="highlight-button btn btn-medium no-margin-bottom popup-modal-dismiss" id="searchModal">적용</a>
								<a href="#" class="highlight-button-dark btn btn-medium no-margin" id="cancelModal">취소</a>
							</div>
							<!-- end 버튼 -->
			
						</div>
					</div>
			
				</div>
				<!-- end 상세조건 팝업 -->
						
	            </section>
		        <!-- end search -->
		        
				<section class="work-4col gutter work-with-title wide wide-title no-padding">
		        <div class="container-fluid margin-five no-margin-bottom">
                <div class="row">
                    <div class="col-md-10 overflow-hidden no-padding center-col" >
                        <div class="tab-content">
                            <!-- work grid -->
                            <ul class="grid masonry-items" id="abandDogList">
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img" style="width:300px; height:200px; margin:0 auto;"><a href="#" name="abandImage"><img src="http://placehold.it/500x400" alt="" ></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <div class="gallery-img" style="width:300px; height:200px; margin:0 auto;"><a href="#" name="abandImage"><img src="http://placehold.it/300x200" alt="" ></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <div class="gallery-img" style="width:300px; height:200px; margin:0 auto;"><a href="#" name="abandImage"><img src="http://placehold.it/300x200" alt="" ></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <div class="gallery-img" style="width:300px; height:200px; margin:0 auto;"><a href="#" name="abandImage"><img src="http://placehold.it/300x200" alt="" ></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                            </ul>
                            <!-- end work grid -->
                        </div>
                    </div>
                </div>
            </div>
	        </section>
                
     		<!-- pagination -->
            <input type="hidden" id="currentPage" name="currentPage" value="0"/>	
            <div class="pagination margin-ten no-margin" id="abandDogListPage">

            </div>
            <!-- end pagination -->
        </section>
        <!-- end content section -->
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>