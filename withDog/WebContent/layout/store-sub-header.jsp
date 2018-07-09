<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
</head>
<body>
<!-- navigation panel -->
        <nav class="navbar navbar-default navbar-fixed-top nav-transparent overlay-nav sticky-nav nav-border-bottom bg-white" role="navigation">
            <div class="container">
                <div class="row">    
					 <!-- logo -->
                    <div class="col-md-2 pull-left">
                    	<a class="logo-light" href="../common/index.jsp"><img alt="" src="../images/logo-black.png" class="logo" /></a>
                    	<a class="logo-dark" href="../common/index.jsp"><img alt="" src="../images/logo-black.png" class="logo" /></a>
                    </div>
                    <!-- end logo -->                
                    
                    <!-- search and cart  -->
                    <div class="col-md-2 no-padding-left search-cart-header pull-right">
                        <div id="top-search">
                            <!-- nav search -->
                            <a href="#search-header" class="header-search-form"><i class="fa fa-search search-button"></i></a>
                            <!-- end nav search -->
                        </div>
                        <!-- search input-->
                        <form id="search-header" method="post" action="#" name="search-header" class="mfp-hide search-form-result">
                            <div class="search-form position-relative">
                                <button type="submit" class="fa fa-search close-search search-button"></button>
                                <input type="text" name="search" class="search-input" placeholder="Enter your keywords..." autocomplete="off">
                            </div>
                        </form>
                        <!-- end search input -->
                        <div class="top-cart">
                            <!-- nav shopping bag -->
                            <a href="#" class="shopping-cart">
                                <i class="fa fa-shopping-cart"></i>
                                <div class="subtitle">(1)</div>
                            </a>
                            <!-- end nav shopping bag -->
                            <!-- shopping bag content -->
                            <div class="cart-content">
                                <ul class="cart-list">
                                    <li>
                                        <a title="Remove item" class="remove" href="#">×</a>
                                        <a href="#">
                                            <img width="90" height="90" alt="" src="http://placehold.it/90x90">상품명
                                        </a>
                                        <span class="quantity">1 × <span class="amount">20,000원</span></span>
                                        <a href="#">Edit</a>
                                    </li>
                                </ul>
                                <p class="total">금액: <span class="amount">20,000원</span></p>
                                <p class="buttons">
                                    <a href="listcart.jsp" class="btn btn-very-small-white no-margin-bottom margin-seven no-margin-lr">장바구니</a>
                                    <a href="addPurchase.jsp" class="btn btn-very-small-white no-margin-bottom margin-seven no-margin-right">구매하기</a>
                                </p>
                            </div>
                            <!-- end shopping bag content -->
                        </div>
                    </div>
                    <!-- end search and cart  -->
                    <!-- toggle navigation -->
                    <div class="navbar-header col-sm-8 col-xs-2 pull-right">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        	<span class="sr-only">Toggle navigation</span> 
                        	<span class="icon-bar"></span>
                        	<span class="icon-bar"></span>
                        	<span class="icon-bar"></span>
                        </button>
                    </div>
                    <!-- toggle navigation end -->
                    
                    <!-- main menu -->
                    <div class="col-md-8 no-padding-right accordion-menu">
                        <div class="navbar-collapse collapse">
                            <ul id="accordion" class="nav navbar-nav panel-group">
                            
                                <!-- menu item 회사소개-->
                                <li class="dropdown panel">
                                    <a href="#collapse1" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">회사소개 <i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu -->
                                    <ul id="collapse1" class="dropdown-menu mega-menu ">
                                        <!-- sub menu column  -->
                                        <li class="mega-menu-column col-sm-3">
                                            <!-- sub menu item  -->
                                            <ul>
                                                <li class="dropdown-header">회사소개</li>
                                                <li><a href="../company/getCoInfo.jsp">회사소개</a></li>
                                                <li><a href="../company/getCoActivity.jsp">활동내역</a></li>

                                            </ul>
                                            <!-- end sub menu item  -->
                                        </li>
                                        <!-- sub menu column end -->
                                    </ul>
                                    <!-- end sub menu -->
                                </li>
                                <!-- end menu item -->
                                
                                <!-- menu item  동물교감치유서비스-->
                                <li class="dropdown panel">
                                    <a href="#collapse2" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">동물교감치유<i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu -->
                                    <ul id="collapse2" class="dropdown-menu mega-menu panel-collapse collapse">
                                        <li class="mega-menu-column col-sm-6">
                                            <!-- sub menu item  -->
                                            <ul>
                                                <li class="dropdown-header">동물교감치유 서비스</li>
                                                <li><a href="../animal/getASHInfo.jsp">동물교감치유 서비스란</a></li>
                                                <li><a href="../animal/listHealingDog.jsp">치유견 소개</a></li>
                                                <li><a href="../animal/listReservationASH.jsp">예약하기</a></li>
                                                <li><a href="../animal/addConsulting.jsp">영상상담 신청<span class="menu-new">new</span></a></li>
                                            </ul>
                                            <!-- end sub menu item  -->
                                        </li>
                                    </ul>
                                    <!-- end sub menu -->
                                </li>
                                <!-- end menu item -->
                                
                                <!-- menu item 유기견 분양-->
                                <li class="dropdown panel">
                                    <a href="#collapse3" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">유기견 입양<i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu -->
                                    <ul id="collapse3" class="dropdown-menu mega-menu panel-collapse collapse">
                                        <li class="mega-menu-column col-sm-3">
                                            <!-- sub menu item  -->
                                            <ul>
                                                <li class="dropdown-header">유기견 입양</li>
                                                <li><a href="../adopt/getAbandDogInfo.jsp">유기견 입양 안내</a></li>
                                                <li><a href="../adopt/listAbandDog.jsp">유기견 분양 공고</a></li>
                                            </ul>
                                            <!-- end sub menu item  -->
                                        </li>
                                    </ul>
                                    <!-- end sub menu -->
                                </li>
                                <!-- end menu item -->
                                
                                <!-- menu item 스토어-->
                                <li class="dropdown panel">
                                    <a href="#collapse4" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">스토어<i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu -->
                                    <ul id="collapse4" class="dropdown-menu mega-menu panel-collapse collapse">
                                        <!-- sub menu column  -->
                                        <li class="mega-menu-column col-sm-3">
                                            <!-- sub menu item  -->
                                            <ul>
                                                <li class="dropdown-header">스토어</li>
                                                <li><a href="../store/listProduct.jsp">애견용품 </a></li>
                                                <li><a href="../store/listProduct2.jsp">애견식품<span class="menu-new">new</span></a></li>
                                            </ul>
                                            <!-- end sub menu item  -->
                                        </li>
                                        <!-- end sub menu column  -->
                                    </ul>
                                    <!-- end sub menu -->
                                </li>
                                <!-- end menu item -->
                                
                                <!-- menu item 커뮤니티-->
                                <li class="dropdown panel simple-dropdown">
                                    <a href="#collapse5" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">커뮤니티<i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu -->
                                    
                                            <!-- sub menu item  -->
                                            <ul id="collapse5" class="dropdown-menu mega-menu panel-collapse">
		                                        <li class="dropdown-header">커뮤니티</li>
		                                        <li><a href="../community/dogBreedDicSearch.jsp">견종백과</a></li>
		                                        <li><a href="../community/listDogInfo.jsp">애견상식</a></li>
		                                        <li><a href="../community/listAfterASH.jsp">동물교감치유후기</a></li>
		                                        <li><a href="../community/listOutSideSNS.jsp">SNS게시판</a></li>
		                                        <li><a href="../community/listChatRoom.jsp">채팅방</a></li>
		                                    </ul>
                                            <!-- end sub menu item  -->
                                    <!-- end sub menu -->
                                </li>
                                <!-- end menu item -->
                                
                                <!-- menu item 크라우드 펀딩-->
                                <li class="dropdown panel simple-dropdown">
                                    <a href="#collapse6" class="dropdown-toggle collapsed" data-toggle="collapse" data-parent="#accordion" data-hover="dropdown">크라우드펀딩 <i class="fa fa-angle-down"></i></a>
                                    <!-- sub menu single -->
                                    <!-- sub menu item  -->
                                    <ul id="collapse6" class="dropdown-menu panel-collapse collapse" role="menu">
                                        <li class="dropdown-header">크라우드펀딩</li>
                                        <li><a href="../fund/fundGuid.jsp">크라우드펀딩이란?</a></li>
                                        <li><a href="../fund/listFund.jsp">크라우드펀딩 목록</a></li>
                                        <li><a href="../fund/listFund2.jsp">펀딩완료목록</a></li>
                                        <li><a href="../fund/fundReq.jsp"fundReq.jsp">크라우드펀딩 신청 문의</a></li>
                                    </ul>
                                    <!-- end sub menu item  -->
                                    <!-- end sub menu single -->
                                </li>
                                <!-- end menu item -->
                            </ul>
                        </div>
                    </div>
                    <!-- end main menu -->
                </div>
            </div>
        </nav>
        <!-- end navigation panel -->
</body>
</html>