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
	
	
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method", "POST").attr("action", "/product/listProduct?prodType=1").submit();
	}

	//상품이미지 클릭시 이벤트 getProduct 연결
	$(function(){
		$(".prodImage").on("click", function(){
			
			var index = $(".prodImage").index(this);
			alert(index);
			var prodNo = $($("input[name='prodNo']")[index]).val();

// 			 if(role != 'admin'){
				self.location = "/product/getProduct?prodNo="+prodNo;
// 			}else{
// 				self.location = "/product/updateProductAdmin?prodNo="+prodNo;
// 			}
		});
	});


	//============= 상품소팅 처리부분 =============
	$(function(){
		
		//셀렉트박스 클릭시  
		$("#prodSort").on("change", function(){
			var sort = $("#prodSort").val();
			if(sort == 0){
				self.location = "/product/listProduct?prodSort=0&prodType=0";
			}else if(sort == 1){
				self.location = "/product/listProduct?prodSort=1&prodType=0";
			}else if(sort == 2 ){
				self.location = "/product/listProduct?prodSort=2&prodType=0";
			}else{
				self.location = "/product/listProduct?prodSort=3&prodType=0";
			}
		})
	});


</script>

<jsp:include page="/common/css.jsp" />


<title>애견식품 리스트</title>

</head>


<body>


	<jsp:include page="/layout/store-main-header.jsp" />

		<!-- head section -->
			<section class="page-title parallax3 parallax-fix page-title-blog">
			<img class="parallax-background-img" src="../images/sub/500_bg.jpg"
				alt="" />
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12 text-center animated fadeInUp">
						<div class="no-margin-top margin-one"></div>
						<!-- page title -->
						<h1 class="white-text tit_png">
							<img src="../images/sub/502_tit.png">
						</h1>
						<!-- end page title -->
					</div>
				</div>
			</div>
			</section>
		<!-- end head section -->


	<!-- content section -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12">
                    	<form>
                        <div class="shorting clearfix xs-margin-top-three">
                            <div class="col-md-8 col-sm-8 grid-nav">
                                <a href="shop-with-sidebar-list.html"><i class="fa fa-bars"></i></a>
                                <a href="#"><i class="fa fa-th"></i></a>
                                <p class="text-uppercase letter-spacing-1">전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지</p>
                            </div>
                            <div class="col-md-3 col-sm-3 pull-right">
                                <div class="select-style med-input shop-shorting-full no-border">
                                    <select id="prodSort" class="pull-right">
                                        <option value="0" selected>신상품순</option>
                                        <option value="1">인기상품순</option>
                                        <option value="2">낮은가격순</option>
                                        <option value="3">높은가격순</option>
                                        <input type="hidden" name="prodSort"  value="${search.prodSort}"/>
                                    </select>
                                </div>
                                
                                <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				  				
                            </div>
                        </div>
                        </form>
                        
                        <div class="product-listing margin-three">
                        
                            <!-- shop item -->
                            <c:forEach var="product" items="${list}">
                            <c:if test="${product.prodType == '1'}">
                            <div class="col-md-4 col-sm-6">
                                <div class="home-product text-center position-relative overflow-hidden margin-ten no-margin-top">
                                    <a href=""  class="prodImage">
                                    	<img src = "/images/uploadFiles/store/${product.prodImage}" class="gallery-img"/>
                                   	</a>
                                    <span class="product-name font-weight-600">
                                    		${product.prodName}
                                    		<input type="hidden" value="${product.prodNo}" name="prodNo" />
                                   	</span>
                                    <span class="price black-text"><del>${product.price+5000}원</del>${product.price}원</span>
                                    <span class="onsale onsale-style-2">New</span>
                                </div>
                            </div>
                            </c:if>
                            </c:forEach>
                            <!-- end shop item -->
                            
                            </div>
                            
                    </div>
                </div>
            </div>
            <jsp:include page="/common/pageNavigator_new.jsp"/>
        </section>
        <!-- end content section -->
        
        
	
	<jsp:include page="/layout/footer.jsp" />
	
	<jsp:include page="/common/js.jsp" />
	
</body>
</html>