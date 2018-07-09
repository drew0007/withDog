<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1" />
<jsp:include page="../common/css.jsp" />
<title>구매하기</title>
</head>
<body>
		
		<jsp:include page="../layout/header.jsp" />
		
		<!-- head section -->
        <section class="content-top-margin page-title page-title-small bg-gray">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-7 col-sm-12 wow fadeInUp" data-wow-duration="300ms">
                        <!-- page title -->
                        <h1 class="black-text">Checkout</h1>
                        <!-- end page title -->
                    </div>
                    <div class="col-lg-4 col-md-5 col-sm-12 breadcrumb text-uppercase wow fadeInUp xs-display-none" data-wow-duration="600ms">
                        <!-- breadcrumb -->
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li>Shopping Cart</li>
                            <li>Checkout</li>
                        </ul>
                        <!-- end breadcrumb -->
                    </div>
                </div>
            </div>
        </section>
        <!-- end head section -->
        
        
        <!-- content section -->
        <section>
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
                                <input type="text" name="userName" value="userName">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>수령인:</label>
                                <input type="text" name="receiverName">
                            </div>
                            <div class="col-md-12 no-padding">
                                <label>배송주소:</label>
                            </div>
                            <div class="col-md-12 no-padding">
                                <input type="text" name="Address1" class="col-md-8">
                                <button class="highlight-button btn no-margin pull-right post-search">우편번호 검색</button>
                                <input type="text" name="Address2">
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
                            <hr/>
                                <label>포인트:</label>
                                <input type="text" name="usePoint"><p class="pull-right">[1000 point 보유]</p>
                            </div>
                            <div class="col-md-12 no-padding">
                            <hr/>
                                <label>결제수단:</label>
                                <p class="pull-right"><input type="radio" name="kakaoPay" id="kakaoPay" value="kakaoPay" checked>카카오페이</p>
                            </div>
                        </form>
                    </div>
                    
                </div>
                <div class="row margin-five sm-margin-bottom-seven">
                    <div class="col-sm-12 shop-cart-table">
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
                                        <a href="shop-single-product.html"><img src="http://placehold.it/120x120" alt="" ></a>
                                    </td>
                                    <td class="text-left">
                                        <a href="shop-single-product.html">Chasse Wells Italian Genuine Leather Incroyable Tote</a>
                                        <span class="text-uppercase display-block text-small margin-two">상품번호: 290397</span>
                                        <a href="shop-single-product.html" class="text-small"><i class="fa fa-edit black-text"></i> Edit</a>
                                    </td>
                                    <td class="product-quantity">
                                        <div class="select-style med-input shop-shorting shop-shorting-cart no-border-round">
                                            <select>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="product-subtotal text-left">20,000원</td>
                                    <td class="product-remove text-center">
                                        <a href="javascript:void(0)"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="product-thumbnail text-left">
                                        <a href="shop-single-product.html"><img src="http://placehold.it/120x120" alt="" ></a>
                                    </td>
                                    <td class="text-left">
                                        <a href="shop-single-product.html">Tundra perforated suede boots</a>
                                        <span class="text-uppercase display-block text-small margin-two">상품번호: 450365</span>
                                        <a href="shop-single-product.html" class="text-small"><i class="fa fa-edit black-text"></i> Edit</a>
                                    </td>
                                    <td class="product-quantity">
                                        <div class="select-style med-input shop-shorting shop-shorting-cart no-border-round">
                                            <select>
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td class="product-subtotal text-left">30,000원</td>
                                    <td class="product-remove text-center">
                                        <a href="javascript:void(0)"><i class="fa fa-times"></i></a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-sm-12 pull-right">
                        <table class="table cart-total">
                            <tbody>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase font-weight-600 letter-spacing-2 text-small xs-no-padding">적립예정포인트</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right font-weight-600 black-text text-small xs-no-padding">500 point</td>
                                </tr>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase font-weight-600 letter-spacing-2 text-small xs-no-padding">사용포인트</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right font-weight-600 black-text text-small xs-no-padding">2000 point</td>
                                </tr>
                                <tr>
                                    <th class="padding-two text-right no-padding-right text-uppercase font-weight-600 letter-spacing-2 text-small xs-no-padding">배송비</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right font-weight-600 black-text text-small xs-no-padding">Free</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="padding-one no-padding-right xs-no-padding">
                                        <hr>
                                    </td>
                                </tr>
                                <tr class="total">
                                    <th class="padding-two text-uppercase text-right no-padding-right font-weight-600 text-large xs-no-padding">총 주문금액</th>
                                    <td class="padding-two text-uppercase text-right no-padding-right font-weight-600 black-text text-large no-letter-spacing xs-no-padding">50,000원</td>
                                </tr>
                                <tr>
                                    <td colspan="2" class="padding-one no-padding-right xs-no-padding">
                                        <hr class="no-margin-bottom">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <a href="shop-checkout.html" class="highlight-button-black-background btn no-margin pull-right checkout-btn xs-width-100 xs-text-center">Place Order</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- end content section -->
        
        
        
        
		
		
		<jsp:include page="../layout/footer.jsp" />
	
		<jsp:include page="../common/js.jsp" />
</body>
</html>