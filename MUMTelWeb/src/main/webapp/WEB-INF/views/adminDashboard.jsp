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

<jsp:include page="headerPanel.jsp">
	<jsp:param value="a" name="a" />
</jsp:include>

<script type="text/javascript">

	function gernerateReport(type){
		var urlA="<%=request.getContextPath()%>/report/monthly_traffic_report/"+type+"?&month="+jQuery('#month').val()+"&year="+jQuery('#year').val();
		window.location.href=urlA;
	}
	
</script>

</head>
<div id="page-wrapper" style="margin-left: 17%">

	<div class="container-fluid">

		<!-- Page Heading -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Dashboard <small>Statistics Overview</small>
				</h1>
				<ol class="breadcrumb">
					<li class="active"><i class="fa fa-dashboard"></i> Dashboard</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-12">
				<div class="alert alert-info alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">&times;</button>
					<i class="fa fa-info-circle"></i> <strong>MUMTel Admin
						Page!</strong> Stay Connected
				</div>
			</div>
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-tasks fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">${totalcodes}</div>
								<div>Country Codes!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><span class="pull-left"><a
									href="<c:url value="/countries?currentPage=1&searchString="/>">View
										Details</a></span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-shopping-cart fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">${totalcallDetails}</div>
								<div>Call Details!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><span class="pull-left"><a
									href="<c:url value="/callDetails?currentPage=1&searchString="/>">View
										Details</a></span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
						</div>
					</a>
				</div>

			</div>
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-red">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-users fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">${totalCustomers}</div>
								<div>Customers!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><span class="pull-left"><a
									href="<c:url value="/customerDetails?currentPage=1&searchString="/>">View
										Details</a></span> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-yellow">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-cloud fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">${totalServices}</div>
								<div>Services!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><a
								href="<c:url value="/serviceAndRatesDetails?currentPage=1&searchString="/>">View
									Details</a> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			<!-- ADDED By Yasir to show Monthly Call Summary -->
			
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-new1">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-support fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="huge">${totalSalesRep}</div>
								<div>Sales Rep!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><a
								href="<c:url value="/salesRepDetails?currentPage=1&searchString="/>">View
									Details</a> <span class="pull-right"><i
									class="fa fa-arrow-circle-right"></i></span>
								<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
			
			
			<div class="col-lg-3 col-md-6">
				<div class="panel panel-new">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-files fa-5x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="row">
									<select name="month" id="month" style="color:black" >
										<c:forEach var="month" items="${months}">
											<option value="${month.key}">${month.value}</option>
										</c:forEach>
									</select> 
									<select name="year" id="year" style="color:black" >
										<c:forEach var="year" items="${years}">
											<option value="${year}">${year}</option>
										</c:forEach>
									</select>
								</div>
								<div style="width:200px;margin-left:-60px;margin-top:9px">Generate Traffic Summary!</div>
							</div>
						</div>
					</div>
					<a href="#">
						<div class="panel-footer">
							<span class="pull-left"><span class="pull-left">
							<a onclick="gernerateReport('pdf')" role="button">PDF</a></span>
								<span class="pull-right"><span class="pull-left"><a
										onclick="gernerateReport('xls')" role="button">XLS</a></span>
									<div class="clearfix"></div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->

<body>


</body>

</html>
