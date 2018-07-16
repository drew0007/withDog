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
        
        	<!-- highlight section
		        <section class="bg-fast-yellow">
		            <div class="container">
		                <div class="row sm-text-center">
		                    
		                    <div class="col-md-3 col-sm-3 pull-left no-margin">
                                <div class="select-style input-round med-input no-border">
                                    <select>
                                        <option value="">견종</option>
                                        <option value="">골든리트리버</option>
                                        <option value="">블독</option>
                                    </select>
                                </div>
                        </div>
                        
                        <div class="col-md-3 col-sm-3 pull-left no-margin">
                                <div class="select-style input-round med-input no-border">
                                    <select>
                                        <option value="">보호소위치</option>
                                        <option value="">서울특별시</option>
                                        <option value="">경기도</option>
                                    </select>
                                </div>
                        </div>
		                    <div class="col-md-5 no-padding">
		                        <a class="highlight-button btn btn-medium button xs-margin-bottom-five xs-no-margin-right input-round pull-right" href="#">상세조건</a>
		                    </div>
		                </div>
		            </div>
		        </section>
		        end highlight section -->
		        
            	<div class="container">
                <div class="row margin-five no-margin-bottom">
                    <!-- model -->
                    <div class="col-md-6 col-sm-6 xs-margin-bottom-ten">
                        <div class=" model-details clearfix xs-no-margin">
                            <div class="col-md-6 no-padding">
                                <img src="http://placehold.it/700x853" alt=""/>
                            </div>
                            <div class="col-md-6 no-padding">
                                <div class="model-details-text">
                                    <div class="separator-line bg-black no-margin-lr margin-ten"></div>
                                    <span class="margin-ten display-block clearfix xs-no-margin sm-display-none dog-num"></span>
                                    <span class="font-weight-600 title-small">품종 : 진도견</span>
                                    <span class="text-small letter-spacing-2 margin-three display-block"><span class="text-small display-block margin-two-bottom">공고번호</span>경북-경주-2018-00591</span>
                                    <p class="margin-ten"><span class="text-small display-block margin-two-bottom">발견장소</span>교문화원 앞 강변도로에서발견</p>
                                    <span class="margin-ten display-block clearfix xs-no-margin"></span>
                                    <a class="highlight-button-dark btn btn-very-small no-margin" href="#">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end model -->
                    <!-- model -->
                    <div class="col-md-6 col-sm-6 xs-margin-bottom-ten">
                        <div class=" model-details clearfix xs-no-margin">
                            <div class="col-md-6 no-padding">
                                <img src="http://placehold.it/700x853" alt=""/>
                            </div>
                            <div class="col-md-6 no-padding">
                                <div class="model-details-text">
                                    <div class="separator-line bg-black no-margin-lr margin-ten"></div>
                                    <span class="margin-ten display-block clearfix xs-no-margin sm-display-none"></span>
                                    <span class="text-uppercase font-weight-600 black-text letter-spacing-2">Marika Merry</span>
                                    <span class="text-uppercase text-small letter-spacing-2 margin-three display-block">Fashion / Modeling</span>
                                    <p class="margin-ten">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                                    <span class="margin-ten display-block clearfix xs-no-margin"></span>
                                    <a class="highlight-button-dark btn btn-very-small no-margin" href="#">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end model -->
                    <!-- model -->
                    <div class="col-md-6 col-sm-6 xs-margin-bottom-ten">
                        <div class=" model-details no-margin clearfix xs-no-margin">
                            <div class="col-md-6 no-padding">
                                <img src="http://placehold.it/700x853" alt=""/>
                            </div>
                            <div class="col-md-6 no-padding">
                                <div class="model-details-text">
                                    <div class="separator-line bg-black no-margin-lr margin-ten"></div>
                                    <span class="margin-ten display-block clearfix xs-no-margin sm-display-none"></span>
                                    <span class="text-uppercase font-weight-600 black-text letter-spacing-2">Julia Trento</span>
                                    <span class="text-uppercase text-small letter-spacing-2 margin-three display-block">Fashion / Modeling</span>
                                    <p class="margin-ten">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                                    <span class="margin-ten display-block clearfix xs-no-margin"></span>
                                    <a class="highlight-button-dark btn btn-very-small no-margin" href="#">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end model -->
                    <!-- model -->
                    <div class="col-md-6 col-sm-6">
                        <div class=" model-details no-margin clearfix">
                            <div class="col-md-6 no-padding">
                                <img src="http://placehold.it/700x853" alt=""/>
                            </div>
                            <div class="col-md-6 no-padding">
                                <div class="model-details-text">
                                    <div class="separator-line bg-black no-margin-lr margin-ten"></div>
                                    <span class="margin-ten display-block clearfix xs-no-margin sm-display-none"></span>
                                    <span class="text-uppercase font-weight-600 black-text letter-spacing-2">Cassie Moldoe</span>
                                    <span class="text-uppercase text-small letter-spacing-2 margin-three display-block">Fashion / Modeling</span>
                                    <p class="margin-ten">Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                                    <span class="margin-ten display-block clearfix xs-no-margin"></span>
                                    <a class="highlight-button-dark btn btn-very-small no-margin" href="#">Read More</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end model -->
                </div>
            </div>
                
                	<!-- dog 01 -->
                    <div class="col-md-4 col-xs-4 bottom-margin text-center">
                        <div class="key-person">
                            <div class="key-dog-img"><img src="http://placehold.it/500x400" alt=""></div>
                            <div class="key-dog-details"> <span class="dog-num black-text">경북-경주-2018-00591</span> <span class="dog-receipt">접수일 2018-07-08</span>
                                <div class="separator-line bg-fast-yellow"></div>
                                <p>품종 : 진도견<br/>발견장소 : 교문화원 앞 강변도로에서발견</p>
                            </div>
                        </div>
                    </div>
                    <!-- end dog 01 -->
                    
                    <!-- dog 02 -->
                    <div class="col-md-4 col-xs-4 bottom-margin text-center">
                        <div class="key-person">
                            <div class="key-dog-img"><img src="http://placehold.it/500x400" alt=""></div>
                            <div class="key-dog-details"> <span class="dog-num black-text">경북-경주-2018-00591</span> <span class="dog-receipt">접수일 2018-07-08</span>
                                <div class="separator-line bg-fast-yellow"></div>
                                <p>품종 : 진도견<br/>발견장소 : 교문화원 앞 강변도로에서발견</p>
                            </div>
                        </div>
                    </div>
                    <!-- end dog 02 -->
                    
                    <!-- dog 03 -->
                    <div class="col-md-4 col-xs-4 text-center">
                        <div class="key-person">
                            <div class="key-dog-img"><img src="http://placehold.it/500x400" alt=""></div>
                            <div class="key-dog-details"> <span class="dog-num black-text">경북-경주-2018-00591</span> <span class="dog-receipt">접수일 2018-07-08</span>
                                <div class="separator-line bg-fast-yellow"></div>
                                <p>품종 : 진도견<br/>발견장소 : 교문화원 앞 강변도로에서발견</p>
                            </div>
                        </div>
                    </div>
                    <!-- end dog 03 -->
                    
                    
                    
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