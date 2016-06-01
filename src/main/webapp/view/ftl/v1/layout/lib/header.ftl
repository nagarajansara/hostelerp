<!DOCTYPE html>
<html lang="en">
<head>

<!-- Assign global values -->

<#assign baseURL = "${rc.getContextPath()}">

<!-- Assign global values end -->


<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="images/favicon.png">

<title>Blank page</title>

<!--Core CSS -->
<link href="${baseURL}/theme/bs3/css/bootstrap.min.css" rel="stylesheet">
<link href="${baseURL}/theme/css/bootstrap-reset.css" rel="stylesheet">
<link href="${baseURL}/theme/font-awesome/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="${baseURL}/theme/css/style.css" rel="stylesheet">
<link href="${baseURL}/theme/css/style-responsive.css" rel="stylesheet" />

<link href="${baseURL}/assest/css/hostelerp.css" rel="stylesheet" />

<link href="${baseURL}/theme/css/table-responsive.css" rel="stylesheet" />

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]>
    <script src="${baseURL}/theme/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<section id="container">
		<!--header start-->
		<header class="header fixed-top clearfix">
			<!--logo start-->
			<div class="brand">
				<a href="index.html" class="logo"
					style="color: #fff; font-family: verdana; font-size: 23px; font-weight: bold;">
					<!-- <img
					src="${baseURL}/theme/images/logo.png" alt=""> --> Hostel ERP
				</a>
				<div class="sidebar-toggle-box">
					<div class="fa fa-bars"></div>
				</div>
			</div>
			<!--logo end-->

			<div class="top-nav clearfix">
				<!--search & user info start-->
				<ul class="nav pull-right top-menu">
					<li><input type="text" class="form-control search"
						placeholder=" Search"></li>
					<!-- user login dropdown start-->
					<li class="dropdown"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <img alt=""
							src="${baseURL}/theme/images/avatar1_small.jpg"> <span
							class="username"><#if Session.fName??>${Session.fName}</#if></span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<!--<li><a href="#"><i class=" fa fa-suitcase"></i>Pro	file</a></li>
							<li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>-->
							<li><a href="${baseURL}/api/login/logout"><i class="fa fa-key"></i> Log
									Out</a></li>
						</ul></li>
					<!-- user login dropdown end -->
					<!-- <li>
						<div class="toggle-right-box">
							<div class="fa fa-bars"></div>
						</div>
					</li> -->
				</ul>
				<!--search & user info end-->
			</div>
		</header>