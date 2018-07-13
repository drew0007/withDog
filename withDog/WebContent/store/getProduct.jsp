<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

</script>
<jsp:include page="../common/css.jsp" />
<title>상품상세정보</title>
</head>

<body>

		<jsp:include page="/layout/store-sub-header.jsp" />
	
	    <!-- head section -->
         <section class="content-top-margin page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/500_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center wow fadeInUp">
                        <!-- page title -->
                        <h1 class="white-text">Product Content</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Lorem Ipsum is simply dummy text of the printing.</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->

        <!-- content section -->
        <section>
            <div class="container">
                <div class="row">
                    <!-- product images -->
                    <div class="col-md-6 col-sm-12 sm-margin-bottom-ten">
                        <a><img src = "/images/uploadFiles/store/${product.prodImage}" id="prodImage" class="gallery-img"/></a>
                    </div>
                    <!-- end product images -->
                    <div class="col-md-5 col-sm-12 col-md-offset-1">
                        <!-- product rating -->
                        <div class="rating margin-five no-margin-top">
                            <i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star-o black-text"></i>
                            <span class="rating-text text-uppercase">5 Reviews</span>
                            <span class="rating-text text-uppercase pull-right">상품번호: <span class="black-text">${product.prodNo}</span></span>
                        </div>
                        <!-- end product rating -->
                        <!-- product name -->
                        <span class="product-sub-title text-uppercase font-weight-600 letter-spacing-1 black-text">${product.prodName}</span>
                        <!-- end product name -->
                        <!-- product stock -->
                        <p class="text-uppercase margin-two"><span class="light-gray-text">배송비</span> 무료</p>
                        <!-- end product stock -->
                        <div class="separator-line bg-black no-margin-lr margin-five"></div>
                        <!-- end product short description -->
                        <span class="price black-text title-large price-text"><del class="price-del-text">${product.price+5000}원</del>${product.price}원</span>
                        <div class="col-md-3 col-sm-3 no-padding-left margin-five">
                            <div class="select-style med-input xs-med-input shop-shorting-details no-border-round">
                                <!-- product qty -->
                                <select>
                                    <option selected>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                <!-- end product qty -->
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-9 no-padding">
                            <!-- add to bag button -->
                            <a class="highlight-button btn btn-medium button" href="#" ><i class="icon-basket"></i>장바구니 담기</a>
                            <!-- end add to bag button -->
                            <!-- add to purchase button -->
                            <a class="highlight-button-dark btn btn-medium button" href="addPurchase.jsp">바로구매</a>
                            <!-- end add to purchase button -->
                        </div>
                        <div class="col-md-9 col-sm-9 product-details-social no-padding-left margin-five" style="display:block;">
                            <!-- social media sharing -->
                            <span class="black-text text-uppercase text-small margin-right-five">Share on</span>
                            <a href="https://www.facebook.com/" target="_blank"><i class="fa fa-facebook"></i></a>
                            <a href="https://twitter.com/" target="_blank"><i class="fa fa-comment"></i></a>
                            <a href="https://naver.com/" target="_blank"><i class="fa fa-coffee"></i></a>
                            <!-- end social media sharing -->
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="border-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                        <!-- tab -->
                        <div class="tab-style1">
                            <div class="col-md-12 col-sm-12 no-padding">
                                <!-- tab navigation -->
                                <ul class="nav nav-tabs nav-tabs-light text-center">
                                    <li class="active"><a href="#tab_sec1" data-toggle="tab">상품상세정보</a></li>
                                    <li><a href="#tab_sec2" data-toggle="tab">상품후기 (10)</a></li>
                                    <li><a href="#tab_sec3" data-toggle="tab">상품문의 (10)</a></li>
                                </ul>
                                <!-- tab end navigation -->
                            </div>
                            <!-- tab content section -->
                            <div class="tab-content">
                            
                            
                                <!-- tab content -->
                                <div class="tab-pane med-text fade in active text-center" id="tab_sec1">
                                    <div class="row">
                                        <div class="col-md-10 col-sm-12 center-col overflow-hidden">
                                            <img src = "/images/uploadFiles/store/${product.prodContent}"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- end tab content -->
                                
                                <!-- tab content -->
                                <div class="tab-pane fade in" id="tab_sec2">
                                    <div class="row">
                                    
                                        <div class="col-md-6 col-sm-12 review-main">
                                        
                                            <div class="review">
                                                <p class="letter-spacing-2 text-uppercase review-name"><strong>Nathan Ford,</strong> March 15, 2015</p>
                                                <p><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star-o black-text"></i><i class="fa black-text fa-star-o"></i></p>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the standard dummy text. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the standard dummy text.</p>
                                            </div>
                                            
                                            <div class="review">
                                                <p class="letter-spacing-2 text-uppercase review-name"><strong>Paul Scrivens,</strong> March 09, 2015</p>
                                                <p><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa fa-star black-text"></i><i class="fa black-text fa-star-o"></i></p>
                                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the standard dummy text. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
                                            </div>
                                            
                                            <ul class="list-inline comment-pagination">
                                                <li><a href="#" class="active">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">...</a></li>
                                                <li><a href="#">8</a></li>
                                                <li><a href="#">9</a></li>
                                            </ul>
                                            
                                        </div>
                                        
                                        <div class="col-md-5 col-sm-12 col-md-offset-1 blog-single-full-width-form sm-margin-top-seven">
                                            <div class="blog-comment-form">
                                                <!-- comment form -->
                                                <form>
                                                	<!-- input  -->
                                                    <label class="rating">평점</label>
                                                    <p class="add-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></p>
                                                    <!-- end input -->
                                                    <!-- input  -->
                                                    <input type="text" name="reviewTitle" placeholder="후기제목">
                                                    <!-- end input -->
                                                    <!-- textarea  -->
                                                    <textarea name="comment" placeholder="후기내용"></textarea>
                                                    <!-- end textarea  -->
                                                    <!-- button  -->
                                                    <input type="submit" name="send message" value="구매후기 등록" class="highlight-button-black-border btn btn-small xs-no-margin-bottom">
                                                    <!-- end button  -->
                                                </form>
                                                <!-- end comment form -->
                                            </div>
                                        </div>
                                        
                                    </div>
                                </div>
                                <!-- end tab content -->
                                
                                <!-- tab content -->
                                <div class="tab-pane fade in" id="tab_sec3">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-12 ">
                                        
					                        <!-- toggle -->
					                        <div class="panel-group toggles-style3">
					                        
					                            <!-- toggle item -->
					                            <div class="panel panel-default" id="collapse-three">
					                                <div role="tablist" id="type3-headingOne" class="panel-heading">
					                                    <a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link1"><h4 class="panel-title">Collapse Item #1<span class="pull-right"><i class="fa fa-plus"></i></span></h4></a>
					                                </div>
					                                <div id="collapse-three-link1" class="panel-collapse collapse">
					                                    <div class="panel-body">
					                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
					                                    </div>
					                                </div>
					                            </div>
					                            <!-- end toggle item -->
					                            
					                            <!-- toggle item -->
					                            <div class="panel panel-default">
					                                <div role="tablist" id="type3-headingTwo" class="panel-heading">
					                                    <a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link2"><h4 class="panel-title">Collapse Item #2<span class="pull-right"><i class="fa fa-plus"></i></span></h4></a>
					                                </div>
					                                <div id="collapse-three-link2" class="panel-collapse collapse">
					                                    <div class="panel-body">
					                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
					                                    </div>
					                                </div>
					                            </div>
					                            <!-- end toggle item -->
					                            
					                            <!-- toggle item -->
					                            <div class="panel panel-default">
					                                <div role="tablist" id="type3-headingThree" class="panel-heading">
					                                    <a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link3"><h4 class="panel-title">Collapse Item #3<span class="pull-right"><i class="fa fa-plus"></i></span></h4></a>
					                                </div>
					                                <div id="collapse-three-link3" class="panel-collapse collapse">
					                                    <div class="panel-body">
					                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
					                                    </div>
					                                </div>
					                            </div>
					                            <!-- end toggle item -->
					                            
					                        </div>
											
											<ul class="list-inline comment-pagination margin-three">
                                                <li><a href="#" class="active">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">...</a></li>
                                                <li><a href="#">8</a></li>
                                                <li><a href="#">9</a></li>
		                                     </ul>											
																                        
					                        <!-- end toggle -->
				              	      </div>
				              	      
				              	      <div class="col-md-5 col-sm-12 col-md-offset-1 blog-single-full-width-form sm-margin-top-seven">
                                            <div class="blog-comment-form">
                                                <!-- comment form -->
                                                <form>
                                                    <!-- input -->
                                                    <label class="secret">비밀글<i class="fa fa-unlock-alt"></i></label>
                                                    <label class="radio-inline secret"><input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">공개</label>
                                                    <label class="radio-inline secret"><input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">비공개</label>
                                                    <!-- end input -->
                                                    <!-- input  -->
                                                    <input type="text" name="email" placeholder="문의제목">
                                                    <!-- end input -->
                                                    <!-- textarea  -->
                                                    <textarea name="comment" placeholder="문의내용"></textarea>
                                                    <!-- end textarea  -->
                                                    <!-- button  -->
                                                    <input type="submit" name="send message" value="문의하기" class="highlight-button-black-border btn btn-small xs-no-margin-bottom">
                                                    <!-- end button  -->
                                                </form>
                                                <!-- end comment form -->
                                            </div>
                                        </div>
				              	      
                                    </div>
                                </div>
                                <!-- end tab content -->
                                 
                            </div>
                            <!-- end tab content section -->
                        </div>
                        <!-- end tab -->
                    </div>
                </div>
            </div>
        </section>

        
        
        
        


	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="../common/js.jsp" />
</body>
</html>