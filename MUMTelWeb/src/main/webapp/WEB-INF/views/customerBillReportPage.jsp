<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.mumtel.utils.CommonUtility"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>

<jsp:include page="headerPanel.jsp">
	<jsp:param value="a" name="a" />
</jsp:include>

<title>MUMTel</title>
<script type="text/javascript">
	function nextPage(page){
		if(page<1 || page>parseInt("${totalPages}",10))
		{}
		else
		{
			var urlA="<%=request.getContextPath()%>/customerDetails?currentPage="+page+"&searchString="+jQuery('#searchType').val();
			window.location.href=urlA;
		}

	}
	function findCallDetails(){
		
		var urlA="<%=request.getContextPath()%>/customerDetails?currentPage=1&searchString="+jQuery('#searchType').val();
		window.location.href=urlA;
		
	}
	
	jQuery(document).ready(function(){
		jQuery('#pageSelection').val('${currentPage}');
		$('#searchType').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				findCallDetails();
				return false;
			}
		 
		});
	});
	
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div id="page-wrapper">
		<center>
			<h2>Customer Bill</h2>
		</center>
		<div class="container">
			<label>Customer Name: ${name}</label><br> <label>Phone:
				${phone}</label><br> <label>Address: ${address}</label><br> <label>Billing
				Month: ${billingMonth}</label><br> <label>Service: ${service}</label><br>
			<label>Total Due: <span id="totalCost"></span></label>
		</div>

		<div class="form-group form-group-lg">

				<a class="btn btn-default"
				href='<c:url value="/report/customer_bill_report/pdf"/>'
				role="button">Generate Customer Bill Report PDF</a>
				 <a
				class="btn btn-default"
				href='<c:url value="/report/customer_bill_report/xls"/>'
				role="button">Generate Customer Bill Report Excel</a>
		</div>

		<h3>${message}</h3>
		<hr></hr>
		<div style="overflow:scroll;height:500px">
		<table id="example" class="table table-striped table-bordered"
			cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>Call Date</th>
					<th>Call Time</th>
					<th>To Phone</th>
					<th>To Country</th>
					<th>Call Duration</th>
					<th>Call Rate($)</th>
					<th>Call Cost($)</th>
				</tr>
			<thead>
			<tbody>
				<c:set var="billAmount" value="0"></c:set>
				<c:forEach var="bill" items="${billList}" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${bill.callDate}</td>
						<td>${bill.callTime}</td>
						<td>${bill.toTelephone}</td>
						<td>${bill.toCountryName}</td>
						<td>
							<fmt:formatNumber
								pattern="##"
								value="${bill.callDuration}"
								minIntegerDigits="1"
								maxFractionDigits="2"></fmt:formatNumber>
						</td>
						<td>
						<fmt:formatNumber
								pattern="##"
								value="${bill.callRate}"
								minIntegerDigits="1"
								maxFractionDigits="2"></fmt:formatNumber>
						</td>
						<td>
						<fmt:formatNumber
								pattern="##"
								value="${bill.callCost}"
								minIntegerDigits="1"
								maxFractionDigits="2"></fmt:formatNumber>
						 <c:set var="billAmount" value="${billAmount+ bill.callCost}"></c:set>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
	<footer>
		<p>&copy; Company 2014 Developed By Yasir Mukhtar & Awais Tariq</p>
	</footer>
	</div>
	<script type="text/javascript">
		jQuery('#totalCost').html('<fmt:formatNumber pattern="##" value="${billAmount}" minIntegerDigits="1" maxFractionDigits="2"></fmt:formatNumber>$');
	</script>
</body>
</html>
