<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

<jsp:include page="/common/css.jsp" />

<title>견종백과 일반검색</title>

</head>


<body>

	<jsp:include page="/layout/common-header.jsp" />
		
	<!-- head section -->
	       <section class="page-title parallax3 parallax-fix page-title-blog">
	          <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
	          <img class="parallax-background-img" src="../images/sub/600_bg.jpg" alt="" />
	          <div class="container">
	              <div class="row">
	                  <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
	                      <div class="no-margin-top margin-one"></div>
	                      <!-- page title -->
	                      <h1 class="white-text tit_png"><img src="../images/sub/601_tit.png"></h1>
	                      <!-- end page title -->
	                      <!-- page title tagline -->
	                      <!-- 서브타이틀 <span class="white-text">1234</span>-->
	                      <!-- end title tagline -->
	                  </div>
	              </div>
	          </div>
	      </section>
	<!-- end head section -->
      
   <!-- 내용  section -->
	<section class="wow fadeIn">
		<div class="container"><!-- container --> 
			<div class="row marginFive"><!-- row --> 
					<div class="col-md-12 col-sm-12 center-col">
						<!-- tab   ///  tabs.html -->
							<div class="tab-style3">
								<div class="row">
									<div class="col-md-12 col-sm-12">
										<!-- tab navigation -->
										<ul class="nav nav-tabs nav-tabs-light text-left center-col">
											<li class="active"><a href="#tab3_sec1" data-toggle="tab">견종 검색</a></li>
											<li><a href="#tab3_sec2" data-toggle="tab">이미지 검색</a></li>
										</ul>
										<!-- end tab navigation -->
									</div>
								</div>
								
								<!-- tab  -->
                            	<div class="tab-content">
                            	
                                <!-- tab content 01 -->
                                <div class="tab-pane med-text fade in active" id="tab3_sec1">
                                    <div class="row">
                                    
                                        <!-- 검색 버튼 -->   
										<div class="col-md-8 col-sm-10 center-col text-center">
										<form>
											<div class="form-group">
												<!-- select -->
												<div class="col-md-10 col-sm-12 no-margin-right">
													<div class="select-style input-round big-input">
														<select>
															<option value="">골든리트리버 Golden Retriever</option>
															<option value="">그레이트 데인 Great Dane</option>
															<option value="">닥스훈트 Dachshund</option>
															<option value="">도베르만 핀셔 Doberman Pinschers</option>
															<option value="">래브라도 리트리버 Labrador dog</option>
															<option value="">로트 바일러 Rottweiler</option>
															<option value="">말티즈 Maltese</option>
														</select>
													</div>
												</div>
												<!-- end select -->
												<button class="btn btn-black no-margin-bottom btn-large2 btn-round no-margin-top" type="submit">검색</button>
											</div>
										</form>
										</div>
										<!-- end 검색 버튼 --> 
					
										<!-- 검색 후 나오는 이미지 및 정보  -->  
						                <div class="row">
						                    <!-- content  -->
						                    <div class="col-md-6 col-sm-12 sm-margin-bottom-four">
						                        <img src="http://placehold.it/600x400" alt=""/>
						                    </div>
						                    <div class="col-md-6 col-sm-12">
						                        <p class="text-large font-weight-600 text-black margin-three no-margin-top">그레이트 데인 	GREAT DANE</p>
						                        <p class="text-large text-black margin-three no-margin-top">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p>
						                        <p class="text-med">Lorem Ipsum</p>
						                        <p class="text-med">Lorem Ipsum</p>
						                    </div>
						                    <!-- end content  -->
						                </div>
			                             <!-- end 검색 후 나오는 이미지 및 정보-->   
			                             
									</div>	
								
									</div>
						            <!-- end tab content 01 -->
				            
				            <!-- tab content 02 -->
                                <div class="tab-pane fade in" id="tab3_sec2">
                                    <div class="row">
                                    	
                                    	<div class="col-md-12 text-center">
											<h3 class="title-large font-weight-400 margin-three">이미지를 드래그 또는 파일선택을 클릭해주세요</h3>
										</div>
                                    
                                    	<!-- 유저가 올린 이미지 나오는 영역 -->
                                        <div class="col-md-8 col-sm-12 wow fadeIn center-col">
					                        <!-- photo item -->
					                        <img src="http://placehold.it/1200x800" alt="" class="project-img-gallery">
					                        <!-- end photo item -->
					                    </div>
					                    <!-- end 유저가 올린 이미지 나오는 영역-->
					                    
					                    <!--일치율 결과 영역 시작-->
					                    
				                    	<!--  title -->
						            	<div class="col-md-12 text-center">
											<h3 class="title-large font-weight-400 margin-five">해당 이미지와 강아지의 일치율입니다.</h3>
										</div>
										<!--  end title -->
											
							                    <div class="text-center center-col">
							                        <!-- pie charts -->
							                        <div class="col-md-3 col-sm-3 chart-style2 wow zoomIn xs-margin-bottom-ten">
							                        	<a href=""><!-- 일치율 클릭시 해당 강아지 결과 오징어씨 맘대로 링크 걸기  -->
							                            <div class="chart-percent"><span class="chart2 black-text" data-percent="90"><span class="percent"></span> </span></div>
							                            <div class="chart-text">
							                                <h5 class="black-text text-extra-large">골든리트리버</h5>
							                                <p>Golden Retriever</p>
							                            </div>
							                            </a>
							                        </div>
							                        <!-- end pie charts -->
							                        <!-- pie charts -->
							                        <div class="col-md-3 col-sm-3 chart-style2 wow zoomIn xs-margin-bottom-ten">
							                        	<a href=""><!-- 일치율 클릭시 해당 강아지 결과 오징어씨 맘대로 링크 걸기  -->
							                            <div class="chart-percent"><span class="chart2 black-text" data-percent="75"><span class="percent"></span></span></div>
							                            <div class="chart-text">
							                                <h5 class="black-text text-extra-large">그레이트 데인</h5>
							                                <p>Great Dane</p>
							                            </div>
							                            </a>
							                        </div>
							                        <!-- end pie charts -->
							                        <!-- pie charts -->
							                        <div class="col-md-3 col-sm-3 chart-style2 wow zoomIn xs-margin-bottom-ten">
							                        	<a href=""><!-- 일치율 클릭시 해당 강아지 결과 오징어씨 맘대로 링크 걸기  -->
							                            <div class="chart-percent"><span class="chart2 black-text" data-percent="95"><span class="percent"></span> </span></div>
							                            <div class="chart-text">
							                            	<h5 class="black-text text-extra-large">닥스훈트</h5>
							                                <p>Dachshund</p>
							                            </div>
							                            </a>
							                        </div>
							                        <!-- end pie charts -->
							                        <!-- pie charts -->
							                        <div class="col-md-3 col-sm-3 chart-style2 wow zoomIn">
							                        	<a href=""><!-- 일치율 클릭시 해당 강아지 결과 오징어씨 맘대로 링크 걸기  -->
							                            <div class="chart-percent"><span class="chart2 black-text" data-percent="98"><span class="percent"></span> </span></div>
							                            <div class="chart-text">
							                                <h5 class="black-text text-extra-large">도베르만 핀셔</h5>
							                                <p>Doberman Pinschers</p>
							                            </div>
							                            </a>
							                        </div>
							                        <!-- end pie charts -->
							                    </div>
										<!--일치율 결과 영역 끝-->
										
										<!-- 검색 후 나오는 이미지 및 정보  -->  
						                <div class="row">
						                    <!-- content  -->
						                    <div class="col-md-6 col-sm-12 sm-margin-bottom-four">
						                        <img src="http://placehold.it/600x400" alt=""/>
						                    </div>
						                    <div class="col-md-6 col-sm-12">
						                        <p class="text-large font-weight-600 text-black margin-three no-margin-top">그레이트 데인 	GREAT DANE</p>
						                        <p class="text-large text-black margin-three no-margin-top">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text.</p>
						                        <p class="text-med">Lorem Ipsum</p>
						                        <p class="text-med">Lorem Ipsum</p>
						                    </div>
						                    <!-- end content  -->
						                </div>
			                             <!-- end 검색 후 나오는 이미지 및 정보-->  
			                             
			                             	
                                        
                                    </div>
                                </div>
                                <!-- end tab content 02 -->
				            
				            
							</div>
							<!-- end tab  -->

			</div><!-- end row --> 	
		</div><!-- end container --> 
	</section>
	<!-- end 내용  section -->
  
       
    <jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>