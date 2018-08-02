<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>나의 구매내역</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" 	content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<link rel="stylesheet" href="../css/purchase.css" />

<!-- 공통 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />
<script type="text/javascript">

//pageNavigation
function fncGetList(currentPage) {
	$("#currentPage").val(currentPage)
	alert(currentPage)
	$("form").attr("method", "POST").attr("action", 	"/purchase/getMyPurchaseList").submit();
}

	//상세정보버튼
	$(function() {
		$(".getPurchase").on("click", function() {
				var index = $(".getPurchase").index(this);
				alert(index + "index찍기")
				var purchaseNo = $($("input[name='purchaseNo']")[index]).val();
				alert(purchaseNo)
				
				self.location = "/purchase/getMyPurchase?purchaseNo=" + purchaseNo;
// 				("form").attr("method", "POST").attr("action", "/purchase/getMyPurchase?purchaseNo=" + purchaseNo).submit();
		});
	});
	
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
				
				<form>
					<!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
					<input type="hidden" id="currentPage" name="currentPage" value=""/>
				
			
				<h1 class="margin-four-bottom" align="center">나의 구매내역</h1>
				
				<c:forEach var="purchase" items="${list}">
				<div class="my-purchase-list__item">
					<div class="my-purchase-list__item-head">
							<span class="my-purchase-list__item-sub-info">
								주문일&nbsp;${purchase.purchaseDate}
							</span>
							
							<span style="float:right">
								<a href="#" class="getPurchase">
									주문상세보기
									<span class="fa fa-chevron-right"></span>
								</a>
							</span>

					</div>
				</div>


					<div class="my-purchase-list__item-units" style="border:1px solid #e7e7e7; border-top:0px;">

						<table class="my-order-unit ">
							<colgroup>
								<col class="my-order-unit__col-item">
								<col class="my-order-unit__col-opt" />
							</colgroup>
							<tbody>
								<tr>
									<td colspan="2"></td>
								</tr>
								<tr>
									<td class="my-order-unit__area-item-group col-md-9">

										<div class="my-order-unit__area-item">

											<div>
												<div class="product-thumbnail">
													<a href="#" target="_blank" class="my-order-unit__link-thumb js_myEventLog">
														<img 	src="/images/store/${purchase.product.prodImage}" width="120" height="120" alt="" class="my-order-unit__thumb-img">
													</a>
												</div>

												<div class="my-order-unit__item-info">
													<a href="#" target="_blank" class="my-order-unit__link-info js_myEventLog">
															
														<div class="my-order-unit__info-name my-font--13 text-left">
																<img src="https://image15.coupangcdn.com/image/my/common/badge/rocket_logo.png" 	width="56" height="14" class="my-icon-badge" />
																${purchase.product.prodName}
														</div>
														
													</a>

													<div class="my-order-unit__info-ea">
														<p class="gray-text" style="margin-bottom:5px; font-size:12px">구매번호 : ${purchase.purchaseNo}</p>
														<input type="hidden" name="purchaseNo" value="${purchase.purchaseNo}" />
														${purchase.purchasePrice}<span class="text-xsmall">원</span> / ${purchase.purchaseQuantity}<span class="text-xsmall">개</span>
													</div>

												</div>
												
											</div>
											
										</div>
									</td>

									<td class="my-order-unit__area-opt">
									
										<div class="my-order-unit__status my-font--15">
											<span class="my-color--black">
												<strong style="font-size:15px; color:#709dbd; ">배송완료</strong>
												<div class="my-color--green">목요일 11/30 도착</div>
											</span>
										</div>
										
										<div class="my-order-unit__btn-group">
											<div class="my-order-unit__btn-row">
												<a href="#" class="box-sky">
													배송조회
												</a>
											</div>

											<div class="my-order-unit__btn-row">
												<a href="#" class="highlight-box-sky"> 교환신청 </a>
											</div>

											<div class="my-order-unit__btn-row">
												<a href="#"  class="highlight-box-sky" style="padding:8px 15px 9px">구매후기 쓰기</a>
											</div>

										</div>
									</td>
								</tr>
							</tbody>
						</table>
						
					</div>
					
				</c:forEach>
				
				<div class="col-md-12 col-sm-12 col-xs-12 wow fadeInUp" align="center">
					<!-- pagination -->
					<jsp:include page="../common/pageNavigator_new.jsp"></jsp:include>
					<!-- end pagination -->
				</div>
				
				</form>
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