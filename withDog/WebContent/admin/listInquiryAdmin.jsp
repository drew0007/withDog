<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />
	<script type="text/javascript">
	
	//답변등록
	 $(function(){
		 $("input[name='reply']").on("click", function(){
			 	var index = $("input[name='reply']").index(this);
				var inquiryNo = $($("input[name='inquiryNo']")[index]).val();
				var reply = $($("textarea[name='inquiryReply']")[index]).val();
				
				if(reply == null || reply.length<1){
					alert('답변 내용은 반드시 입력하셔야 합니다.');
					return;
				}
				var btnVal = $($("input[name='reply']")[index]).val();

				if(btnVal == '답변등록'){
					self.location="/inquiry/updateInquiryReplyAdmin?inquiryNo=" + inquiryNo + "&inquiryReply=" + reply;
				}else{
					self.location="/inquiry/updateInquiryReplyAdmin?inquiryNo=" + inquiryNo + "&inquiryReply=" + reply;
				}
		 });
	}); 
	
	</script>
	
	
<title>Admin page</title>


</head>
<body>

		<jsp:include page="/layout/common-header.jsp" />
		
	 	<!-- head section -->
     	<section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/305_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/admin_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->


	<section class="wow fadeIn">
	<div class="container">
		<div class="row">

			<!-- sidebar 인크루드 -->
			<div class="col-md-2 col-sm-3 sidebar">
				<jsp:include page="/layout/admin-sideBar.jsp" />
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
                            <c:set var="i" value="0" />
                            <c:forEach var="inquiry" items="${list}">
                           	<c:set var="i" value="${i+1}" />
                            <div class="panel panel-default">
                                <div class="panel-heading active-accordion">
                                    <a data-toggle="collapse" data-parent="#accordion-three" href="#accordion-three-link${i}">
                               			
                                    	<h4 class="panel-title">
                                   		 	<p class="text-xsmall " style="position:relative; top:30px;">
                                   		 		${inquiry.user.userId}&nbsp;&nbsp;${inquiry.regDate}
                                   		 		<c:choose>
                                   		 		<c:when test="${inquiry.secret == '1'}">
                                   		 			<i class="fa fa-unlock-alt xsmall-icon" style="position:relative; top:0px;"></i>
                                   		 		</c:when>
                                   		 		<c:otherwise>
                                   		 			<i class="fa fa-unlock xsmall-icon" style="position:relative; top:0px;"></i>
                                   		 		</c:otherwise>
                                   		 		</c:choose>
                                		 	</p>
                                    		${inquiry.inquiryTitle}
                                    		<input type="hidden" name="inquiryNo" value="${inquiry.inquiryNo}"/>
                                    		<c:choose>	
												<c:when test="${inquiry.replyCondition == '0'}">
													<img src="/images/reply_X.png" />
												</c:when>
												<c:otherwise>
													<img src="/images/reply_O.png" />
												</c:otherwise>
											</c:choose>
                                    		
                                    		<span class="pull-right">
                                    			<i class="fa fa-angle-${i=='1'?'up':'down'}"></i>
                                   			</span>
                                    	</h4>
                                   	</a>
                                </div>
                                <div id="accordion-three-link${i}" class="panel-collapse collapse ${i=='1'?'in':'' }">
                                    <div class="panel-body">
                                    	<p class="text-sxmall gray-text border-bottom">${inquiry.product.prodName}&nbsp;&nbsp;|&nbsp;&nbsp;${inquiry.product.prodNo} </p>
                                       ${inquiry.inquiryContent}
                                    </div>
                                    
                                    <div class="panel-body bg-ddd padding-two">
                                    	<p class="text-sxmall text-gray"><img src="/images/reply.png" width="10" style="margin-top:-4px"/>&nbsp;답변</p>
                                    	<textarea rows="4" cols="" name="inquiryReply" placeholder="답변을 입력하세요.">${inquiry.inquiryReply}</textarea>
                                    	<input type="button" class="highlight-button col-md-2 pull-right" value="${inquiry.replyCondition == '0'?'답변등록':'답변수정'}" name="reply" />
                                    </div>
                                    <div style="height:30px;"></div>
                                    
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