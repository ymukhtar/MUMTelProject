<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MUMTel</title>

<!-- Bootstrap Core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resources/css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resources/css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="resources/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript">

$(document).ready(function() {
    $(".nav navbar-nav side-nav li a").on("click", function() {
    	alert("Hello");
        $(".nav navbar-nav side-nav li a.active").removeClass("active");
        $(this).addClass("active");
    });
});

</script>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/home"/>">MUMTel
					Admin</a>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> <sec:authentication
							property="principal.username" /> <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="divider"></li>
						<li><a href="<c:url value="j_spring_security_logout"/>"><i
								class="fa fa-fw fa-power-off"></i> Log Out</a></li>
					</ul></li>
			</ul>
			<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li><a
						href="<c:url value="/countries?currentPage=1&searchString="/>"><i
							class="fa fa-fw fa-dashboard"></i> Upload Country Codes</a></li>
					<li><a
						href="<c:url value="/serviceAndRatesDetails?currentPage=1&searchString="/>"><i
							class="fa fa-fw fa-edit"></i> Upload Services and Rates</a></li>
					<li><a
						href="<c:url value="/callDetails?currentPage=1&searchString="/>"><i
							class="fa fa-fw fa-bar-chart-o"></i> Upload Call Details</a></li>
					<li><a
						href="<c:url value="/registerCustomer?currentPage=1&searchString="/>"><i
							class="fa fa-edit fa-fw"></i> Register Customer</a></li>
					<li><a
						href="<c:url value="/registerSalesRep?currentPage=1&searchString="/>"><i
							class="fa fa-sitemap fa-fw"></i> Register Sales Rep</a></li>
					<li><a
						href="<c:url value="/enterPeakTime?currentPage=1&searchString="/>"><i
							class="fa fa-fw fa-desktop"></i> Create Peak Time</a></li>
							
					<li><a
						href="<c:url value="/registerCustomer?currentPage=1&searchString="/>"><i
							class="fa fa-files-o fa-fw"></i> Register Customer</a></li>		
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

	</div>
	<!-- /#wrapper -->
	<script src="<c:url value="/resources/js/jQuery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<!-- Morris Charts JavaScript -->
	<!-- <script src="resources/js/plugins/morris/raphael.min.js"></script>
    <script src="resources/js/plugins/morris/morris.min.js"></script>
    <script src="resources/js/plugins/morris/morris-data.js"></script>-->
</body>

</html>
