<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />
<script type="text/javascript">

	//문의수정
	$(function() {
		$("a[name='updateInquiry']").on("click", function() {
				var index = $("a[name='updateInquiry']").index(this);
				alert(index + "index찍기")
				var inquiryNo = $(	$("input[name='inquiryNo']")[index]).val();
				var content = $($("textarea[name='inquiryContent']")[index]).val();
				alert(content);
				alert(inquiryNo);

				self.location = "/inquiry/updateInquiry?inquiryNo=" + inquiryNo + "&inquiryContent=" + content;
		});
	});
</script>


<title>User page</title>


</head>
<body>

	<jsp:include page="/layout/common-header.jsp" />
	

	<!-- head section -->
	<section class="page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/304_bg.jpg" alt="" />
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 text-center animated fadeInUp">
				<div class="no-margin-top margin-one"></div>
				<!-- page title -->
				<h1 class="white-text tit_png">
					<img src="../images/sub/myPage_tit.png">
				</h1>
				<!-- end page title -->
			</div>
		</div>
	</div>
	</section>
	<!-- end head section -->
	

	<section class="wow fadeIn">
	<div class="container">
		<div class="row">
		
			
			<!-- sidebar  -->
			<div class="col-md-2 col-sm-3 sidebar">
				<jsp:include page="/layout/mypage-sideBar.jsp" />
			</div>
			<!-- end sidebar  -->


			<div class="col-md-7 col-sm-10 pull-right">

				<%-- <form class="pull-right">
			    
				  <div class="form-group col-md-3 no-padding">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>답변대기</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>답변완료</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>회원ID</option>
					</select>
				  </div>
				  
				  <div class="form-group col-md-6 no-padding">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="tags" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }" onkeypress="if(event.keyCode == 13){ javascript:fncGetList('1')};" >
				  </div>
				  
				  <button type="button" class="highlight-button-dark btn-medium button">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				
				</form>  --%>

			</div>


			<!-- content  -->
			<div class="col-md-9 col-sm-9 col-md-offset-1 ">

				<!-- accordion -->
				<div class="panel-group accordion-style3" id="accordion-three">
					<!-- accordion item -->
					<form>
						<c:forEach var="inquiry" items="${list}">

							<div class="panel panel-default">
								<div class="panel-heading active-accordion overflow-hidden">
									<a data-toggle="collapse" data-parent="#accordion-three" href="#accordion-three-link">

										<h4 class="panel-title">
											<p class="text-xsmall " style="position: relative; top: 30px;">
												${inquiry.product.prodName}&nbsp;&nbsp;${inquiry.regDate}
												<c:choose>
													<c:when test="${inquiry.secret == '1'}">
														<i class="fa fa-lock xsmall-icon" style="position: relative; top: 0px;"></i>
													</c:when>
													<c:otherwise>
														<i class="fa fa-unlock xsmall-icon" style="position: relative; top: 0px;"></i>
													</c:otherwise>
												</c:choose>
											</p>
											${inquiry.inquiryTitle} <input type="hidden" name="inquiryNo" value="${inquiry.inquiryNo}" />
											<c:choose>
												<c:when test="${inquiry.replyCondition == '0'}">
													<img src="/images/reply_X.png" />
												</c:when>
												<c:otherwise>
													<img src="/images/reply_O.png" />
												</c:otherwise>
											</c:choose>

											<p class="pull-right">
												<i class="fa fa-angle-up"></i>
											</p>
										</h4>
									</a>
								</div>
								<div id="accordion-three-link" 	class="panel-collapse collapse in padding-three-bottom">
									<div class="panel-body" >
										<c:choose>
											<c:when test="${inquiry.replyCondition == '0'}">
												<textarea rows="4" name="inquiryContent" value="${inquiry.inquiryContent}">${inquiry.inquiryContent}</textarea>
												<div class="text-right">
													<a class="highlight-button input-round"  name="updateInquiry">수정</a>
													<a class="highlight-button-dark input-round"  name="deleteInquiry">삭제</a>
												</div>
											</c:when>
											<c:otherwise>
												${inquiry.inquiryContent}
											</c:otherwise>
										</c:choose>
									</div>

									<c:if test="${inquiry.replyCondition == '1' }">
									<div class="panel-body bg-ddd padding-two">
										<p class="text-sxmall text-gray border-bottom"><img src="/images/reply.png" width="10" style="margin-top:-4px"/>&nbsp;답변</p>
										${inquiry.inquiryReply}
									</div>
									</c:if>

								</div>
							</div>
						</c:forEach>
					</form>
					<!-- accordion item -->
				</div>
				<!-- end accordion -->



			</div>
			<!-- end content  -->


		</div>
	</div>
	</section>






	<jsp:include page="../layout/footer.jsp" />

	<jsp:include page="../common/js.jsp" />





</body>
</html>