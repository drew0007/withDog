<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>나의 구매내역 상세정보</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<link rel="stylesheet" href="../css/purchase.css" />

<!-- 공통 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />
<script type="text/javascript">
	function fncGetList(currentPage) {
		$("#currentPage").val(currentPage)
		$("form").attr("method", "POST").attr("action",
				"/common/getMyPointList").submit();
	}
</script>

<style type="text/css">
table th, td {
	text-align: center;
	font-size: 13px;
}

table td {
	font-size: 12px;
}
</style>

</head>

<body>

	<jsp:include page="/layout/common-header.jsp" />

	<!-- head section -->
	<section class="page-title parallax3 parallax-fix page-title-blog">
	<img class="parallax-background-img" src="../images/sub/304_bg.jpg"
		alt="" />
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

			
			<!-- content -->
			<div class="col-md-9 col-sm-9 col-xs-12 col-md-offset-1">
			
				<h1 class="margin-four-bottom" align="center">나의 구매내역 상세정보</h1>

					<div class="row margin-five sm-margin-bottom-seven">
	                    <div class="col-sm-10 shop-cart-table center-col">
	                        <table class="table shop-cart text-center">
	                            <thead class="border-tb">
	                                <tr>
	                                    <td colspan="2" class="text-left text-uppercase letter-spacing-1 text-small ">구매번호 : ${purchase.purchaseNo}</td>
	                                    <td colspan="3" class="text-right text-uppercase letter-spacing-1 text-small">${purchase.purchaseDate} 구매</td>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr class="border-bottom">
	                                    <td class="product-thumbnail text-center no-padding-right">
	                                        <img src = "/images/store/${purchase.product.prodImage}" width="120" height="120" class="product-thumbnail-round"/>
	                                    </td>
	                                    <td colspan="2" class="text-left no-padding-left">
	                                        <span class="display-block text-xsmall margin-two">주문번호: ${purchase.cartNo}</span>
	                                        <a href="#" class="text-med">${purchase.product.prodName}</a>
	                                         <span class="display-block margin-two">수량 : ${purchase.purchaseQuantity}</span>
	                                    </td>
	                                    <td class="border-right"></td>
	                                    <td>
		                                    <div class="my-order-unit__status my-font--15">
												<span class="my-color--black">
													<strong class="sky-text" style="font-size:15px;">
														<c:choose>
			                                        		<c:when test="${purchase.purchaseCondition == '1'}">
			                                        			구매완료
			                                        		</c:when>
			                                        		<c:when test="${purchase.purchaseCondition == '2'}">
			                                        			배송중
			                                        		</c:when>
			                                        		<c:otherwise>
			                                        			배송완료
			                                        		</c:otherwise>
			                                        	</c:choose>
													</strong>
													<div class="my-color--green">목요일 11/30 도착</div>
												</span>
											</div>
											<div class="my-order-unit__btn-row">
												<a href="#" class="box-sky">
													구매취소
												</a>
											</div>
											<div class="my-order-unit__btn-row">
												<a href="#" class="highlight-box-sky" style="padding:8px 15px 9px">
													배송정보 수정
												</a>
											</div>
										</td>
	                                </tr>
	                            </tbody>
	                        </table>
	                        
	                        <div class="blog-single-full-width-form">
		                        <div class="col-md-12 no-padding">
	                                <label>수령인:</label>
	                                <input type="text" name="receiverName" value="${purchase.receiverName}" style="background-color:#fff;" disabled />
	                            </div>
	                            <div class="col-md-12 no-padding">
	                                <label>배송주소:</label>
	                            </div>
	                            <div class="col-md-12 no-padding">
	                                <input type="text" name="receiverAddr1" id="address1" class="col-md-9" value="${purchase.receiverAddr1}" style="background-color:#fff;" disabled />
	                                <input type="text" name="receiverAddr2" id="address2" value="${purchase.receiverAddr2}" style="background-color:#fff;" disabled />
	                            </div>
	                            <div class="col-md-12 no-padding">
	                                <label>사용포인트:</label>
	                                <input type="text" name="usePoint" value="${purchase.usePoint}" style="background-color:#fff;" disabled />
	                            </div>
                            </div>
                            
			                            
			                            	
	                        
	                    </div>
	                    
					</div>
					
					

				</div>

			</div>
			<!--  end content -->

		</div>
	</div>
	</section>

	<jsp:include page="../layout/footer.jsp" />

	<jsp:include page="../common/js.jsp" />

</body>

</html>