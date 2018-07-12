<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

<jsp:include page="/common/css.jsp" />

<title>애견상식</title>

</head>

<body>

<script type="text/javascript">
$(function () {
	$("#addDogInfoButton").on("click", function () {
		var userId = $("input[name=userId]").val()
		if(userId==null || userId=="" || userId==" "){
			alert("로그인 하세요")
		}
		else{
		self.location="/dogInfo/addDogInfo";
		}
	})
})

$(function () {
	$(".topic").on("click", function () {
		$("input[name=searchKeyword]").val("")
		$("input[name=searchCondition]").val($(this).val())
		fncGetList(1)
	})
	
	$("#sortingByView").on("click", function () {
		$("input[name=searchKeyword]").val("")
		$("input[name=sorting]").val(10)
		fncGetList(1)
	})
	
	$("#sortingByRecom").on("click", function () {
		$("input[name=searchKeyword]").val("")
		$("input[name=sorting]").val(11)
		fncGetList(1)
	})
	
	$("#searchButton").on("click", function () {
		$("input[name=searchCondition]").val("")
		$("input[name=sorting]").val(0)	
		fncGetList(1)
	})
	
	$("input[name=searchKeyword]").keydown(function (key) {				
		if(key.keyCode ==13){
			$("input[name=searchCondition]").val("")
			$("input[name=sorting]").val(0)	
			fncGetList(1);
		}
	});			
	
})


function fncGetList(currentPage) {
 	$("#currentPage").val(currentPage)
	$("form").attr("method","POST").attr("action","/dogInfo/listDogInfo").submit();
}


</script>


	<jsp:include page="/layout/common-header.jsp" />
	
	<input type="hidden" name="userId" value="${!empty sessionUser.userId?sessionUser.userId:''}">
	 <!-- head section -->
         <section class="page-title parallax3 parallax-fix page-title-blog">
            <img class="parallax-background-img" src="../images/sub/602_bg.jpg" alt="" />
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 text-center animated fadeInUp">
                        <div class="no-margin-top margin-one"></div>
                        <!-- page title -->
                        <h1 class="white-text tit_png"><img src="../images/sub/602_tit.png"></h1>
                        <!-- end page title -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section class="wow fadeIn">
            <div class="container">
                <div class="row">
                
                    <!-- content  -->
                    <div class="col-md-8 col-sm-8">
                    
	                    <c:set var="i" value="0"/>
						<c:forEach var="list" items="${list}">
						<c:set var="i" value="${i+1}"/>
                    
                        <!-- post item -->
                        <div class="blog-listing blog-listing-classic wow fadeIn">
                        
                            <!-- post image -->
                            <div class="blog-image">
                            <a href="/dogInfo/getDogInfo?dogInfoNo=${list.dogInfoNo}">
                             <c:if test="${empty list.dogInfoImage}">
                            	<img  src="http://placehold.it/600x450" alt=""/>
                            </c:if>
                            <c:if test="${!empty list.dogInfoImage}">
                            	<img  src = "/images/uploadFiles/dogInfo/${list.dogInfoImageList[0]}" width="400px" height="200px" alt=""/>
                            </c:if>
                            </a>
                            
                            </div>
                            <!-- end post image -->
                            
                            <div class="blog-details">
                          
                                <div class="info-small-title">
                                  <c:choose>
                            	<c:when test="${list.dogInfoTopic=='1'}">
                            		<훈련>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='2'}">
                            		<번식>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='3'}">
                            		<위생>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='4'}">
                            		<음식>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='5'}">
                            		<행동>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='6'}">
                            		<미용>
                            	</c:when> 
                            	<c:when test="${list.dogInfoTopic=='7'}">
                            		<기타>
                            	</c:when> 
                            </c:choose>
                                 |  조회수 ${list.viewCount}</div>
                                <div class="info-title">${list.dogInfoTitle}</div>작성자ID : ${list.user.userId}
                                <div>${list.dogInfoContent}</div>
                                <div class="separator-line bg-black no-margin-lr margin-four"></div>
                                <div><a href="#" class="info-like"><i class="fa fa-thumbs-o-up small-icon"></i>${list.recommended}</a><a href="#" class="info-dislike"><i class="fa fa-thumbs-o-down small-icon"></i>${list.notRecommended}</a></div>
                                <a id="getDogInfo" class="highlight-button btn btn-medium xs-no-margin-bottom" href="/dogInfo/getDogInfo?dogInfoNo=${list.dogInfoNo}">자세히 보기</a>
                            </div>
                        </div>
                        <!-- end post item -->
                        </c:forEach>
                        
                     
                    </div>
                    <!-- end content  -->
                    
                    
                    <!-- sidebar  -->
                    <form>
                    <input type="hidden" id="searchCondition" name="searchCondition" value="${!empty search.searchCondition?search.searchCondition:''}"/>
                    <input type="hidden" id="sorting" name="sorting" value="${!empty search.sorting?search.sorting:''}"/>
                    <div class="col-md-3 col-sm-4 col-md-offset-1 xs-margin-top-ten sidebar">
                        <!-- widget  -->
                        <div class="widget">
                            
                                <i id="searchButton" class="fa fa-search close-search search-button"></i>
                                <input type="text" placeholder="아이디 또는 제목 입력" class="search-input" name="searchKeyword" value="${! empty search.searchKeyword ? search.searchKeyword : '' }" >
                                <input type="hidden" id="currentPage" name="currentPage" value=""/>
                            
                        </div>
                        <!-- end widget  -->
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="info-sidebar-title font-weight-200">주제</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body">
                                <ul class="category-list">
                                    <li class="topic" value="8" style="cursor:pointer"><a>전체보기<span ><!-- 건수  -->${topicCount[7]}</span></a></li>
                                    <li class="topic" value="1" style="cursor:pointer"><a>훈련<span ><!-- 건수  -->${topicCount[0]}</span></a></li>
                                    <li class="topic" value="2" style="cursor:pointer"><a>번식<span>${topicCount[1]}</span></a></li>
                                    <li class="topic" value="3" style="cursor:pointer"><a>위생<span>${topicCount[2]}</span></a></li>
                                    <li class="topic" value="4" style="cursor:pointer"><a>음식<span>${topicCount[3]}</span></a></li>
                                    <li class="topic" value="5" style="cursor:pointer"><a>행동<span>${topicCount[4]}</span></a></li>
                                    <li class="topic" value="6" style="cursor:pointer"><a>미용<span>${topicCount[5]}</span></a></li>
                                    <li class="topic" value="7" style="cursor:pointer"><a>기타<span>${topicCount[6]}</span></a></li>
                                    
                                </ul>
                            </div>
                        </div>
                        <!-- end widget  -->
                        <!-- widget  -->
                        <div class="widget">
                            <h5 class="info-sidebar-title font-weight-200">검색조건</h5>
                            <div class="thin-separator-line bg-dark-gray no-margin-lr"></div>
                            <div class="widget-body tags">
                                <a style="cursor: pointer;" id="sortingByView">조회순</a>
                                <a style="cursor: pointer;" id="sortingByRecom">추천순</a>
                                <a style="cursor: pointer;" id="addDogInfoButton">애견상식등록</a>
                            </div>
                        </div>
                        <!-- end widget  -->
                    </div>
                    <!-- end sidebar  -->
                    </form>
                    
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp">
                        <!-- pagination -->
						<jsp:include page="../common/pageNavigator_new.jsp"></jsp:include>
                        <!-- end pagination -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end content section -->
        
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>