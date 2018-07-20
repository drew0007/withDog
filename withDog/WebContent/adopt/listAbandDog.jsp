<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

<jsp:include page="/common/css.jsp" />


<title>유기견 입양 공고 리스트</title>

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
        <section id="key-person" class="no-padding-top animate slow-mo even fadeIn" data-anim-type="fadeIn" data-anim-delay="200">
        
	        	<!-- search -->
	        	<section class="bg-deep padding-three">
	            <div class="container ">
	                <div class="row sm-text-center no-margin">
	                    
	                    <div class="col-md-3 col-sm-3 pull-left no-margin" style="height:47px">
	                              <div class="select-style input-round med-input">
	                                  <select>
	                                      <option value="">견종</option>
	                                      <option value="">골든리트리버</option>
	                                      <option value="">블독</option>
	                                  </select>
	                              </div>
	                      </div>
	                      
	                      <div class="col-md-3 col-sm-3 pull-left no-margin"style="height:47px">
	                              <div class="select-style input-round med-input" >
	                                  <select>
	                                      <option value="">보호소위치</option>
	                                      <option value="">서울특별시</option>
	                                      <option value="">경기도</option>
	                                  </select>
	                              </div>
	                      </div>
	                      
	                       <a class="highlight-button btn btn-medium button xs-margin-bottom-five xs-no-margin-right input-round pull-right popup-with-zoom-anim" href="#modal-popup" >상세조건</a>

					</div>
					
	            </div>

				<!-- 상세조건 팝업 -->
				<div class="col-md-9 col-sm-9 no-padding margin-five">
			
					<div class="col-lg-3 col-md-4 col-sm-5 center-col text-center">
						<div id="modal-popup"
							class="zoom-anim-dialog mfp-hide col-lg-3 col-md-6 col-sm-7 col-xs-11 center-col bg-white text-center modal-popup-main">
			
							<div>
								<span class="black-text">웹페이지 메시지</span>
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
											<input type="radio" name="radio" id="radio1" class="checkbox">
											<label for="radio1" class="input-label radio">남아</label>
											<input type="radio" name="radio" id="radio2" class="checkbox">
											<label for="radio2" class="input-label radio">여아</label>
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
											<input type="radio" name="radio" id="radio3" class="checkbox">
											<label for="radio3" class="input-label radio">공고중</label>
											<input type="radio" name="radio" id="radio4" class="checkbox">
											<label for="radio4" class="input-label radio">보호중</label>
											</span>
										</div>
									</div>
									<!-- end input  -->
								</div>
								<!-- end 조건 2 -->
								
								
								
							</form>
			
							<!-- 버튼 -->
							<div class="text-center no-margin-bottom">
								<a href="#"
									class="highlight-button btn btn-medium no-margin-bottom">CANCEL</a>
								<a href="#" class="highlight-button-dark btn btn-medium no-margin">OK</a>
							</div>
							<!-- end 버튼 -->
			
						</div>
					</div>
			
				</div>
				<!-- end 상세조건 팝업 -->
						
	            </section>
		        <!-- end search -->
		        
				<section class="work-3col gutter work-with-title wide wide-title no-padding">
		        <div class="container-fluid margin-five no-margin-bottom">
                <div class="row">
                    <div class="col-md-10 overflow-hidden no-padding center-col" >
                        <div class="tab-content">
                            <!-- work grid -->
                            <ul class="grid masonry-items">
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="#"><img src="http://placehold.it/800x500" alt=""></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="single-project-page2.html"><img src="http://placehold.it/800x500" alt=""></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="single-project-page3.html"><img src="http://placehold.it/800x500" alt=""></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="single-project-page4.html"><img src="http://placehold.it/800x500" alt=""></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="single-project-page5.html"><img src="http://placehold.it/800x500" alt=""></a></div>
                                        <figcaption>
                                            <h3 class="dog margin-two-bottom"><a href="#">유기견 견종</a></h3>
                                            <p class="text-small">보호소위치보호소위치보호소위치보호소위치</p>
                                        </figcaption>
                                    </figure>
                                </li>
                                <!-- end work item -->
                                <!-- work item -->
                                <li>
                                    <figure>
                                        <div class="gallery-img"><a href="single-project-page1.html"><img src="http://placehold.it/800x500" alt=""></a></div>
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
                    <div class="pagination margin-ten no-margin">
                       <a href="#"><img src="../images/arrow-pre-small.png" alt=""/></a>
                       <a href="#">1</a>
                       <a href="#">2</a>
                       <a href="#" class="active">3</a>
                       <a href="#">4</a>
                       <a href="#">5</a>
                       <a href="#"><img src="../images/arrow-next-small.png" alt=""/></a>
                    </div>
                    <!-- end pagination -->
                    
                </div>
            </div>
        </section>
        <!-- end content section -->
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>