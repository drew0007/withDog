<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />

	<jsp:include page="/common/css.jsp" />

	<title>동물교감 치유 후기 게시판</title>
</head>

<body>

	<jsp:include page="/layout/common-header.jsp" />
	
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <!-- 딤효과 <div class="opacity-medium bg-black"></div>-->
            <img class="parallax-background-img" src="../images/sub/603_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/603_tit.png"></h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <!-- 서브타이틀 <span class="white-text">1234</span>-->
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        <!-- 검색 영역 시작 -->
        <div class="row">
	        <div id="addcomment" class="col-md-6 col-sm-12 blog-comment-form-main center-col text-center">
	            <div class="blog-comment-form">
	                <form>
	                	<!-- select -->
	                    <select class="col-md-4" style="padding-bottom:13px; padding-right:10px;">
	                        <option value="" selected="selected">주제 선택</option>
	                        <option value="message1" >ㅇㅇ</option>
	                        <option value="message2" >ㅇㅇㅇㅇ</option>
	                     </select>   
                    	<!-- end select -->
	                    <select class="col-md-4" style="padding-bottom:13px; padding-right:10px;">
	                        <option value="" selected="selected">치유견 선택</option>
	                        <option value="message1" >치유견 ㅇㅇ</option>
	                        <option value="message2" >치유견ㅇㅇㅇㅇ</option>
	                     </select>   
                    	<!-- end select -->
                        <!-- button  -->
                        	<a href="#" class="highlight-button-dark btn btn-medium btn-round button xs-margin-bottom-five">검색</a>
                        <!-- end button  -->
                     </form>
                 </div>
              </div>
          </div>
        <!-- end 검색 영역  -->
        
        <!-- 슬라이드 영역 시작 -->
            <div class="container padding-four">
                <div class="row text-center">
                    <div id="owl-demo" class="owl-carousel owl-theme dark-pagination dark-pagination-without-next-prev-arrow">
                        <div class="item"><img src="http://placehold.it/800x500" alt=""/></div>
                        <div class="item"><img src="http://placehold.it/800x500" alt=""/></div>
                        <div class="item"><img src="http://placehold.it/800x500" alt=""/></div>
                    </div>
                </div>
            </div>
        <!-- end 슬라이드 영역  -->
        
		<!-- 리스트 시작 -->
        <section class="wow fadeIn no-padding">
            <div class="container">
                <div class="row">
                
                   	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="getAfterASH.jsp"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="getAfterASH.jsp" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목1<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                       <span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div> 
                    
                   	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="#"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="#" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목2<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                       <span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div>  
                    
                   	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="#"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="#" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                        <span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div>  
                   </div><!-- end row -->
                                 <div class="row">
                
                	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="#"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="#" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                        <span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div>  
                    
                   	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="#"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="#" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                       	<span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div>  
                    
                   	<div class="col-md-4 col-sm-4">
                        <div class="blog-post">
                            <div class="blog-post-images"><a href="#"><img src="http://placehold.it/800x500"" alt=""></a></div>
                            <a href="#" class="fund-title border-bottom border-gray" style="padding-bottom:10px;">동물교감 치유후기 제목<i class="fa fa-heart-o pull-right" >15</i></a><br/>
	                      	<span class="fund-raising">회원ID aaa | Date 2018.08.17</span>
                        </div>
                    </div>  
                    
                   <!-- pagination -->
	               <div class="pagination margin-ten no-margin-bottom">
	                  <a href="#"><img src="../images/arrow-pre-small.png" alt=""/></a>
	                  <a href="#">1</a>
	                  <a href="#">2</a>
	                  <a href="#" class="active">3</a>
	                  <a href="#">4</a>
	                  <a href="#">5</a>
	                  <a href="#"><img src="../images/arrow-next-small.png" alt=""/></a>
	               </div>
               	  <!-- end pagination -->   
                    
                </div><!-- end row -->
                   
                 <div class="text-center">
					<a href="addAfterASH.jsp"><span class="highlight-button btn btn-medium pull-right">후기 등록하기 </span></a>
				</div>
				
              </div><!-- end container -->       

         </section>
    	 <!-- end 리스트 -->
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>