<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>
<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">

  <title>Counter Up Demo</title>
  <meta name="description" content="Counter Up - jQuery Plugin Demo">
  <meta name="author" content="Benjamin Intal">
  <link rel="author" href="https://plus.google.com/113101541449927918834"/>
  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600' rel='stylesheet' type='text/css'>
  <style>
    body {
        margin: 40px 100px 150px;
    }
    * {
        font-family: 'Open Sans', sans-serif;
        -webkit-transition: all 400ms ease;
        -moz-transition: all 400ms ease;
        -o-transition: all 400ms ease;
        transition: all 400ms ease;
        text-align: center;
    }
    h1, h3 {
        font-weight: 600;
        text-transform: uppercase;
    }
    h3 {
        margin-bottom: 30px;
        color: #999;
    }
    span {
        font-size: 66px;
        color: #555;
        margin-bottom: 350px;
        display: inline-block;
        font-weight: 400;
        text-align: center;
    }
    span > span {
        margin-bottom: 0;
    }
    .circle {
        background: #2980b9;
        width: 200px;
        line-height: 200px;
        display: inline-block;
        color: #fff;
        border-radius: 100%;
    }
    code, code > span {
        text-align: left;
        display: block;
        font-family: Monaco, monospace;
        background: #444;
        color: #fff;
        padding: 20px;
        font-size: 14px;
        margin-bottom: 100px;
    }
    code > span {
        padding: 0;
        margin: 0;
    }
    @media only screen and (max-width: 1024px) {
        span {
            font-size: 33px;
            margin-bottom: 200px;
        }
    }
    @media only screen and (max-width: 800px) {
        div > span {
            font-size: 66px;
            display: block;
            width: 100% !important;
            margin-bottom: 100px;
        }
        span {
            font-size: 66px;
        }
        code {
            margin-bottom: 100px;
        }
    }
  </style>

  <!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script>
    jQuery(document).ready(function( $ ) {
        $('.counter').counterUp({
            delay: 10,
            time: 1000
        });
    });
  </script>
</head>

<body>

    <div>
        <span class="counter" style="display: inline-block; width: 32%">52,147</span>
        <span class="counter" style="display: inline-block; width: 32%">1.9583</span>
        <span class="counter" style="display: inline-block; width: 32%">12345</span>
    </div>
    <div>
        <span style="display: inline-block; width: 32%"><span>$</span><span class="counter">43,753</span></span>
        <span class="counter" style="display: inline-block; width: 32%">1,734,195.10</span>
        <span class="counter" style="display: inline-block; width: 32%">849203</span>
    </div>
    <div>
        <span class="counter" style="display: inline-block; width: 32%">0.8412</span>
        <span class="counter" style="display: inline-block; width: 32%">958393.10</span>
        <span class="counter" style="display: inline-block; width: 32%">5,123,348</span>
    </div>
    <div>
        <span class="counter" style="display: inline-block; width: 32%">52,147</span>
        <span class="counter" style="display: inline-block; width: 32%">1.9583</span>
        <span class="counter" style="display: inline-block; width: 32%">12345</span>
    </div>
    <div>
        <span style="display: inline-block; width: 32%"><span>$</span><span class="counter">43,753</span></span>
        <span class="counter" style="display: inline-block; width: 32%">1,734,195.10</span>
        <span class="counter" style="display: inline-block; width: 32%">849203</span>
    </div>
    <div>
        <span class="counter" style="display: inline-block; width: 32%">0.8412</span>
        <span class="counter" style="display: inline-block; width: 32%">958393.10</span>
        <span class="counter" style="display: inline-block; width: 32%">5,123,348</span>
    </div>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>
    <script src="/js/jquery.counterup.min.js"></script>
</body>
</html>