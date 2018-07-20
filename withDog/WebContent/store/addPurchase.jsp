<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<jsp:include page="../common/css.jsp" />

<title>구매하기 - 결제정보입력</title>

<script type="text/javascript">
//"결제하기" 버튼 이벤트 연결
$(function(){
	$("#add").on("click", function(){
		fncAddProduct();
	});
});

//도로명 주소  우편번호 검색 클릭시
$(function(){
		$("#searchPost").on("click" , function() {
			alert("sss")
			var pop = window.open("http://localhost:8080/user/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
		});
						
		function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
// 			alert(roadFullAddr);
			$("#address1").val("[" + zipNo + "]"+ roadAddrPart1);
			$("#address2").val(addrDetail);
		}	
});
</script>
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
                        <h1 class="white-text">Checkout</h1>
                        <!-- end page title -->
                        <!-- page title tagline -->
                        <span class="white-text xs-display-none">Payment after entering purchase information.</span>
                        <!-- end title tagline -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section class="bg-gray-light">
            <div class="container clearfix">
                <div class="row margin-seven no-margin-top">
                    <div class="col-md-6 col-xs-12 alert-style4 center-col">
                        <div class="alert alert-warning border text-uppercase black-text letter-spacing-1 text-small">Returning customer? <a href="#" class="font-weight-600 text-decoration-underline">Click here to login</a></div>
                    </div>
                </div>
                <div class="row margin-five no-margin-top">
                    <div class="col-md-6 col-sm-12 center-col sm-margin-bottom-seven">
                        <p class="black-text font-weight-600 text-uppercase text-large">배송정보</p>
                        <form id="billing-form" method="post" action="javascript:void(0)" name="billing-form">
                            <div class="col-md-12 no-padding">
                                <label>구매자이름:</label>
                                <input type="text" name="userName" value="${purchase.user.userName}">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>수령인:</label>
                                <input type="text" name="receiverName">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>배송주소:</label>
                                <span class="text-right" id="postNo"></span>
                            </div>
                            <div class="col-md-12 no-padding">
                                <input type="text" name="address1" id="address1" class="col-md-9">
                                <input type="button" class="highlight-button2 btn no-margin pull-right post-search col-md-3" id="searchPost" value="우편번호검색">
                                <input type="text" name="address2" id="address2">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>연락처:</label>
                                <input type="text" name="Phone">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>배송메시지:</label>
                                <div class="select-style">
                                    <select style="padding-top:11px; padding-bottom:11px">
                                        <option value="" selected="selected">배송시 요청사항 선택</option>
                                        <option value="message1" >부재시 경비실에 맡겨주세요.</option>
                                        <option value="message2" >부재시 휴대폰으로 연락바랍니다.</option>
                                        <option value="message3" >집 앞에 놓아주세요.</option>
                                        <option value="message4" >택배함에 놓아주세요.</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-12 no-padding">
                            	<p class="border-top"></p>
                                <label>포인트:</label>
                                <input type="text" name="usePoint"><p class="text-right display-block gray-text">[1000 point 보유]</p>
                            </div>
                            <div class="col-md-12 no-padding">
     	                       <p class="border-top"></p>
                                <label>결제수단:</label>
                                <div class="wrap pull-right">
									<input type="radio" name="radio" id="radio" class="checkbox" checked>
									<label for="radio1" class="input-label radio  no-margin-top">카카오페이</label>
								</div>
<!--                                 <p class="pull-right"><input type="radio" name="kakaoPay" id="kakaoPay" value="kakaoPay" checked>카카오페이</p> -->
                            </div>
                        </form>
                    </div>
                    
                </div>
                
                <div class="row margin-five sm-margin-bottom-seven">
                    <div class="col-sm-10 shop-cart-table center-col">
                        <table class="table shop-cart text-center">
                            <thead>
                                <tr>
                                    <th class="first"></th>
                                    <th class="text-left text-uppercase font-weight-600 letter-spacing-2 text-small black-text">상품정보</th>
                                    <th class="text-left text-uppercase font-weight-600 letter-spacing-2 text-small black-text">상품수량</th>
                                    <th class="text-left text-uppercase font-weight-600 letter-spacing-2 text-small black-text">상품금액</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="product-thumbnail text-left">
                                        <img src = "/images/store/${product.prodImage}" width="120" height="120"/>
                                    </td>
                                    <td class="text-left">
                                        <a href="#">${product.prodName}</a>
                                        <span class="text-uppercase display-block text-small margin-two">상품번호: ${product.prodNo}</span>
                                        <a href="#" class="text-small"><i class="fa fa-edit black-text"></i> Edit</a>
                                    </td>
                                    <td class="product-quantity">
                                        <div class="select-style med-input shop-shorting shop-shorting-cart no-border-round">
                                            <select classs="prodQuantity">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="product-subtotal text-left">${product.price}원</td>
                                    <td class="product-remove text-center">
                                        <a href="javascript:void(0)"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-10 col-sm-10 center-col">
                        <table class="table cart-total">
                            <tbody>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase letter-spacing-2 text-small xs-no-padding">적립예정포인트</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right black-text text-small xs-no-padding">500 point</td>
                                </tr>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase letter-spacing-2 text-small xs-no-padding">사용포인트</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right  black-text text-small xs-no-padding">2000 point</td>
                                </tr>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase letter-spacing-2 text-small xs-no-padding">배송비</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right black-text text-small xs-no-padding">Free</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="padding-one no-padding-right xs-no-padding">
                                        <hr>
                                    </td>
                                </tr>
                                <tr class="total">
                                    <th class="padding-two text-uppercase text-right no-padding-right font-weight-600 text-large xs-no-padding">총 주문금액</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right font-weight-600 black-text text-large no-letter-spacing xs-no-padding"><tr>
                                    <td colspan="2" class="padding-one no-padding-right xs-no-padding">
                                        <hr class="no-margin-bottom">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <a href="#" class="highlight-button-black-background btn no-margin pull-right checkout-btn xs-width-100 xs-text-center">결제하기</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- end content section -->
        
        
        
        
		
		
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>