<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

<jsp:include page="/common/css.jsp" />


<title>유기견 상세정보</title>

</head>


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
        <section style="padding-bottom:60px;">
            <div class="container">
            	
            	<div class="col-md-10 col-sm-8 col-xs-11 center-col text-center margin-three-bottom">
            		<p class="aband-detail-title black-text no-margin">${abandDog.abandDogBreed }&nbsp;<img src="../images/sub/${abandDog.abandDogGender=='M'?'male':'female'}.png" width="40" style="position:relative; top:-4px;"/></p>
            		<p class="text-large">${abandDog.abandDogAge }&nbsp;&nbsp;|&nbsp;&nbsp;${abandDog.abandDogWeight }</p>
            	</div>
        
        
                <div class="row col-md-10 center-col">
                
                    <!-- product images -->
                    <div class="col-md-5 col-sm-12 sm-margin-bottom-ten">
                        <img src="${abandDog.abandDogImage }" alt=""/>
                    </div>
                    <!-- end product images -->
                    
                    <div class="col-md-5 col-sm-12 col-md-offset-1">
                    
                        <!-- detail 01 -->
                        <p class="no-margin-bottom gray-text text-small3">공고번호</p>
                        <div class="aband-sub-title ">${abandDog.noticeNo }</div>
                        <!-- detail  -->
                        <!-- detail 02 -->
                        <p class="no-margin-bottom gray-text text-small3">공고기한</p>
                        <div class="aband-sub-title">${abandDog.noticeSdt } ~ ${abandDog.noticeEdt }</div>
                        <!-- detail  -->
                        <!-- detail 03 -->
                        <p class="no-margin-bottom gray-text text-small3">발견장소</p>
                        <div class="aband-sub-title">${abandDog.happenPlace }</div>
                        <!-- detail  -->
                        <!-- detail 04 -->
                        <p class="no-margin-bottom gray-text text-small3">특징</p>
                        <div class="aband-sub-title">${abandDog.abandDogFeature }</div>
                        <!-- detail  -->
                        <!-- detail 05 -->
                        <p class="no-margin-bottom gray-text text-small3">중성화여부</p>
                        <div class="aband-sub-title">${abandDog.abandDogNeuter=="Y"?"O":abandDog.abandDogNeuter=="N"?"X":abandDog.abandDogNeuter=="U"?"미상":"" }</div>
                        <!-- detail  -->
                        
                    </div>
                    
                   
                   
                </div>
            </div>
        </section>
        
        <!-- button -->
        <section class="bg-gray margin-three  no-margin-bottom" style="padding:60px 0px;">
        
        	<div class="text-center">
   	    		<a class="highlight-button-sky btn btn-large button no-margin-bottom center-col margin-lr-two" href="#"><i class="fa fa-phone small-icon2"></i>보호센터 전화하기</a>
   	    		<a class="highlight-button-sky btn btn-large button no-margin-bottom center-col  margin-lr-two popup-with-zoom-anim" href="#modal-popup"><i class="fa fa-info-circle small-icon2"></i>보호센터 상세정보</a>
   	    	</div>
   	    	
    	 		<!-- 모달팝업 -->
	           <div class="col-md-9 col-sm-9 no-padding margin-five">
	           
	               <!-- modal popup -->
	            <div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
	            
	             <!-- modal popup -->
	             <div id="modal-popup" class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
	                 <span class="fund-modal black-text no-margin-bottom">보호센터 정보</span>
	                 <div class="borderline-gray no-margin-bottom"></div>
	                 
	                  <div class="border-bottom padding-five">
	                      <span class="text-small2 gray-text">보호센터이름</span>
						  <span class="display-block margin-top-two">${abandDog.careNm }</span>		                      
	                  </div>
	                  
	                  <div class="border-bottom padding-five">
	                      <span class="text-small2 gray-text">보호센터전화번호</span>
						  <span  class="display-block margin-top-two">${abandDog.careTel }</span>	                      
	                  </div>
	                  
	                  <div class="border-bottom padding-five">
	                      <span class="text-small2 gray-text">보호센터주소</span>
						  <span  class="display-block margin-top-two">${abandDog.careAddr }</span>	                      
	                  </div>
	                  
	                  <%-- <div class="border-bottom padding-five">
	                      <span class="text-small2 gray-text">담당자</span>
						  <span  class="display-block margin-top-two">${abandDog.abandDogFeature }</span>	                      
	                  </div>
	                  
	                  <div class="border-bottom padding-five">
	                      <span class="text-small2 gray-text">담당자연락처</span>
						  <span  class="display-block margin-top-two">${abandDog.abandDogFeature }</span>	                      
	                  </div> --%>
		             	
	             
	             </div>
	             <!-- end modal popup -->
	         </div>
	            <!-- end modal popup -->
	            
	           </div>
              <!-- end 모닾팝업 --> 
              
        </section>
        <!-- end button -->

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <!-- tab -->
                        <div class="tab-style1">
                            <div class="">
                                <!-- 보호소위치 -->
                                <div class="tab-pane med-text fade in active">
                                    <div class="row">
                                        <div class="col-md-10 col-sm-12 center-col">
                                            <!-- <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1582.7835459038783!2d127.02758299999999!3d37.494541!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca15aee9ab0cb%3A0x31793fc63e0cf9d3!2z67mE7Yq47Lqg7ZSE!5e0!3m2!1sko!2skr!4v1532231935449" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe> -->
                                            <iframe src="/quick/addressSearch?careAddr=${abandDog.careAddr}" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                                        </div>
                                    </div>
                                </div>
                                <!-- end 보호소위치 -->
                            </div>
                        </div>
                        
                        
                        <!-- end tab -->
                    </div>
                </div>
            </div>
        </section>
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>