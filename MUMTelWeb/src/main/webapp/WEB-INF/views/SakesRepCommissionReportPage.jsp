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
			<h2>Sales Rep Commission</h2>
		</center>
		<div class="container">
			<label>Sales Rep Name: ${name}</label><br> <label>Sales Rep ID:
				${phone}</label><br> <label>Buisness Address: ${address}</label><br> <label>Commission
				Month: ${billingMonth}</label>
			<label>Total Commission: <span id="totalComm"></span></label>
		</div>

		<div class="form-group form-group-lg">

				<a class="btn btn-default"
				href='<c:url value="/report/monthly_sales_rep_comission/pdf?id=${phone}&month=${month}&year=${year}"/>'
				role="button">Generate Commission Report PDF</a>
				
				<a class="btn btn-default"
				href='<c:url value="/report/monthly_sales_rep_comission/xls?id=${phone}&month=${month}&year=${year}"/>'
				role="button">Generate Commission Report XLS</a>
				
				<a class="btn btn-default" role="button" onclick="window.history.back();"><span
			class="glyphicon glyphicon-arrow-left"></span>&nbsp;Go Back</a>
		</div>

		<h3>${message}</h3>
		<hr></hr>
		<div style="overflow:scroll;height:500px">
		<table id="example" class="table table-striped table-bordered"
			cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>ID</th>
					<th>Phone</th>
					<th>Call Duration</th>
					<th>Call Cost($)</th>
					<th>Commission($)</th>
				</tr>
			<thead>
			<tbody>
				<c:set var="commAmount" value="0"></c:set>
				<c:forEach var="comm" items="${commList}" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${comm.name}</td>
						<td>${comm.telephone}</td>
						<td>${comm.callsDuration}</td>
						<td><fmt:formatNumber
								pattern="##"
								value="${comm.callCost}"
								minIntegerDigits="1"
								maxFractionDigits="2"></fmt:formatNumber>
						</td>
						<td>
						<fmt:formatNumber
								pattern="##"
								value="${comm.commission}"
								minIntegerDigits="1"
								maxFractionDigits="2"></fmt:formatNumber>
						<c:set var="commAmount" value="${commAmount+ comm.commission}"></c:set>
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
		jQuery('#totalComm').html('<fmt:formatNumber pattern="##" value="${commAmount}" minIntegerDigits="1" maxFractionDigits="2"></fmt:formatNumber>$');
	</script>
</body>
</html>
