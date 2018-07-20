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

//"바로구매" 버튼 이벤트 연결
$(function(){
	$("#purchase").on("click", function(){
		var prodNo = ${product.prodNo};
		alert(prodNo);
		self.location = "/purchase/addPurchase?prodNo=${product.prodNo}" ;
	});
});

//문의등록실행
function fncAddInquiry(){
	//Form 유효성 검증
	var inquiryTitle = $("input[name='inquiryTitle']").val();
	var inquiryContent = $("textarea[name='inquiryContent']").val();
	var prodNo = $('input[name="prodNo"]').val();
	var secret = $('#secret').val();
	
	
	if(inquiryTitle == null || inquiryTitle.length<1){
		alert("문의 제목은 반드시 입력하여야 합니다.");
		return;
	}
	
	if(inquiryContent == null || inquiryContent.length<1){
		alert("문의 내용은 반드시 입력하셔야 합니다.");
		return;
	}
	
	$.ajax(
			{
				url : "/inquiry/json/addInquiry/"+prodNo,
				method : "POST",
				dataType : "json",
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				data : JSON.stringify({
					//필드명 : 보낼데이타,
					inquiryTitle : inquiryTitle,
					inquiryContent : inquiryContent,
					secret : secret
				}),
				success : function(JSONData , status){
					$('input[name="inquiryTitle"]').val('');
					$('textarea[name="inquiryContent"]').val('');
					$('#secret1').trigger('click');
					
					$('input[name="currentPage"]').val('0');
					fncGetInquiryList();
				}
			}
		)
}

//문의리스트 에이젝스 조회
function fncGetInquiryList(){
	var prodNo = $('input[name="prodNo"]').val();
	var currentPage = $('input[name="currentPage"]').val();

	$.ajax( 
			{
				url : "/inquiry/json/getInquiryList/"+prodNo+"/"+currentPage ,
				method : "GET" ,
				dataType : "json" ,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(JSONData , status) {
					$('input[name="currentPage"]').val(JSONData.resultPage.currentPage);
					
					$("#inquiryList").html("");
					for(i=0; i<JSONData.list.length; i++){
						str='<div class="panel panel-default" id="collapse-three'+i+'">'
							+'<div role="tablist" id="type3-heading'+i+'" class="panel-heading">'
							+'<a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link'+i+'">'
							+'<h4 class="panel-title">'+JSONData.list[i].inquiryTitle;		//	제목
						if(JSONData.list[i].replyCondition == '0'){
							str=str+'&nbsp;&nbsp;<img src="/images/reply_X.png" />';
						}else{
							str=str+'&nbsp;&nbsp;<img src="/images/reply_O.png" />';
						}
						str=str+'<span class="pull-right">'
							+'<i class="fa fa-plus"></i></span><p class="text-xsmall margin-two">'
							+JSONData.list[i].user.userId+'&nbsp;&nbsp;'+JSONData.list[i].regDate;
						if(JSONData.list[i].secret == '0'){
							str=str+'&nbsp;&nbsp;<i class="fa fa-unlock xsmall-icon" style="position:relative; top:0px;"></i>';
						}else{
							str=str+'&nbsp;&nbsp;<i class="fa fa-lock xsmall-icon" style="position:relative; top:0px;"></i>';
						}
						str=str+'</p></h4></a></div>'
							+'<div id="collapse-three-link'+i+'" class="panel-collapse collapse">'
							+'<div class="panel-body">'+JSONData.list[i].inquiryContent		//	내용
							+'<div class="pull-right margin-top-three">'
							+'<a class="highlight-button3-dark" style="margin-right:5px;">수정</a>'
							+'<a class="highlight-button3-dark" >삭제</a>'
							+'</div>'
							+'</div>';
						if(JSONData.list[i].replyCondition == '1'){
						str=str+'<div class="panel-body padding-four-bottom">'
							+'<p width="90%" class="border-top"></p>'
							+'<p class="text-xsmall margin-two"><img src="/images/reply.png" width="15"/>&nbsp;&nbsp;withDog</p>'
							+JSONData.list[i].inquiryReply
							+'</div>';
						}
						str=str+'</div></div>';
			
						$("#inquiryList").append(str);
					}
					
					$("#inquiryListPage").html("");		
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

					$("#inquiryListPage").append(str);
					

					$('.pagePre').on('click', function(){
						if($(".pagePre").hasClass("disabled") == false){
							$('input[name="currentPage"]').val(JSONData.resultPage.currentPage-1);
							fncGetInquiryList();
						}
					});
					$('.pageNxt').on('click', function(){
						if($(".pageNxt").hasClass("disabled") == false){
							$('input[name="currentPage"]').val(JSONData.resultPage.endUnitPage+1);
							fncGetInquiryList();
						}
					});
				}
			}
		);
	
	
}

$(function(){
	//문의등록버튼
	$('#addInquiry').on('click', function(){
		fncAddInquiry();
	});
	//문의탭
	$('#tabInquiry').on('click', function(){
		$('input[name="currentPage"]').val('0');
 		fncGetInquiryList();
	});
	
	//비밀글
	$("input[name='radioSecret']").on('change', function(){
		if($("#secret1").is(":checked")){
			$("#secret").val("0");
		}else{
			$("#secret").val("1");
		}
	});
	
	//문의페이지 네비
	$(document).on("click", ".pageNum", function(){
		var index = $(".pageNum").index(this);
		var page = $($(".pageNum")[index]).text();
		$('input[name="currentPage"]').val(page);
		fncGetInquiryList();
	});
});

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
                        <a><img src = "/images/store/${product.prodImage}" id="prodImage" class="gallery-img"/></a>
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
                            <a class="highlight-button-dark btn btn-medium button" href="#" id="purchase">바로구매</a>
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
                                    <li><a href="#tab_sec3" data-toggle="tab"  id="tabInquiry">상품문의 (10)</a></li>
                                </ul>
                                <!-- tab end navigation -->
                            </div>
                            <!-- tab content section -->
                            <div class="tab-content">
                            
                            
                                <!-- tab content -->
                                <div class="tab-pane med-text fade in active text-center" id="tab_sec1">
                                    <div class="row">
                                        <div class="col-md-10 col-sm-12 center-col overflow-hidden">
                                            <img src = "/images/store/${product.prodContent}"/>
                                        </div>
                                    </div>
                                </div>
                                <!-- end tab content -->
                                
                                <!-- tab content -->
                                <div class="tab-pane fade in" id="tab_sec2">
                                    <div class="row">
                                    
                                        <div class="col-md-6 col-sm-12 review-main">
                                        
                                            <div class="review">
                                                <p class="letter-spacing-1 text-uppercase review-name"><strong>Nathan Ford,</strong> March 15, 2015</p>
                                                <p><i class="fa fa-star yellow-light-text text-extra-large"></i><i class="fa fa-star yellow-light-text text-extra-large"></i><i class="fa fa-star yellow-light-text text-extra-large"></i><i class="fa fa-star-o yellow-light-text text-extra-large"></i><i class="fa fa-star-o yellow-light-text text-extra-large"></i></p>
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
                                                    <p class="add-rating"><i class="fa fa-star text-extra-large"></i><i class="fa fa-star text-extra-large"></i><i class="fa fa-star text-extra-large"></i><i class="fa fa-star text-extra-large"></i><i class="fa fa-star text-extra-large"></i></p>
                                                    <!-- end input -->
                                                    <!-- input  -->
                                                    <input type="text" name="reviewTitle" placeholder="후기제목">
                                                    <!-- end input -->
                                                    <!-- textarea  -->
                                                    <textarea name="comment" placeholder="후기내용"></textarea>
                                                    <!-- end textarea  -->
                                                    <!-- button  -->
                                                    <input type="submit" name="send message" value="구매후기 등록" class="highlight-button btn btn-medium xs-no-margin-bottom">
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
				                        <div class="panel-group toggles-style3" id="inquiryList">
											<div class="panel panel-default" id="collapse-three0">
												<div role="tablist" id="type3-heading0" class="panel-heading">
													<a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link0">
														<h4 class="panel-title">
															xxx&nbsp;&nbsp;
															<img src="/images/reply_X.png">
															<span class="pull-right">
																<i class="fa fa-plus">
																</i>
															</span>
															<p class="text-xsmall margin-two">
																aaa&nbsp;&nbsp;2018-07-17
																<i class="fa fa-lock xsmall-icon" style="position:relative; top:0px;">
																</i>
															</p>
														</h4>
													</a>
												</div>
												<div id="collapse-three-link0" class="panel-collapse collapse">
													<div class="panel-body">
														zzz
														<div class="pull-right margin-top-three">
															<a class="highlight-button3-dark" style="margin-right:5px;">수정</a>
															<a class="highlight-button3-dark">삭제</a>
														</div>
													</div>
												</div>
											</div>
											<div class="panel panel-default" id="collapse-three1">
												<div role="tablist" id="type3-heading1" class="panel-heading">
													<a data-toggle="collapse" data-parent="#collapse-one" href="#collapse-three-link1">
														<h4 class="panel-title">zzz&nbsp;&nbsp;<img src="/images/reply_X.png">
															<span class="pull-right">
																<i class="fa fa-plus">
																</i>
															</span>
															<p class="text-xsmall margin-two">aaa&nbsp;&nbsp;2018-07-17
																<i class="fa fa-unlock xsmall-icon" style="position:relative; top:0px;">
																</i>
															</p>
														</h4>
													</a>
												</div>
												<div id="collapse-three-link1" class="panel-collapse collapse">
													<div class="panel-body">
														xxx
														<div class="pull-right margin-top-three">
															<a class="highlight-button3-dark" style="margin-right:5px;">수정</a>
															<a class="highlight-button3-dark">삭제</a>
														</div>
													</div>
												</div>
											</div>
										</div>
											<div id="inquiryListPage" class="pagination">
												<ul class="list-inline comment-pagination margin-three">
	                                                <li><a href="#" class="active">1</a></li>
	                                                <li><a href="#">2</a></li>
	                                                <li><a href="#">...</a></li>
	                                                <li><a href="#">8</a></li>
	                                                <li><a href="#">9</a></li>
			                                     </ul>	
		                                     </div>
					                        
					                        
					                         <input type="hidden" id="currentPage" name="currentPage" value="0"/>											
																                        
					                        <!-- end toggle -->
				              	      </div>
				              	      
				              	      <div class="col-md-5 col-sm-12 col-md-offset-1 blog-single-full-width-form sm-margin-top-seven">
                                            <div class="blog-comment-form">
                                                <!-- comment form -->
                                                <form>
                                                    <!-- input -->
                                                    <label class="secret">비밀글<i class="fa fa-unlock-alt"></i></label>
                                                    <label class="radio-inline secret"><input type="radio" id="secret1" name="radioSecret" checked="checked">공개</label>
                                                    <label class="radio-inline secret"><input type="radio" id="secret2" name="radioSecret" >비공개</label>
                                                    <input type="hidden" id="secret" name="secret" value="0">
                                                    <input type="hidden" name="prodNo" value="${product.prodNo}">
                                                    <!-- end input -->
                                                    <!-- input  -->
                                                    <input type="text" name="inquiryTitle" placeholder="문의제목">
                                                    <!-- end input -->
                                                    <!-- textarea  -->
                                                    <textarea name="inquiryContent" placeholder="문의내용"></textarea>
                                                    <!-- end textarea  -->
                                                    <!-- button  -->
                            						<button id="addInquiry" type="button" class="highlight-button btn btn-medium">문의하기</button>
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