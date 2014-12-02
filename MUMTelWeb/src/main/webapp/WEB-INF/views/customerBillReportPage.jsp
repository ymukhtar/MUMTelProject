<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.mumtel.utils.CommonUtility"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<center><h2>Customer Bill</h2></center>
		<div class="container">
					<label>Customer Name: ${name}</label><br>
					<label>Phone: ${phone}</label><br>
					<label>Address: ${address}</label><br>
					<label>Billing Month: ${billingMonth}</label><br>
					<label>Service: ${service}</label><br>
		</div>

		<h3>${message}</h3>
		<hr></hr>
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
					<th>Call Rate</th>
					<th>Call Cost</th>
				</tr>
			<thead>
			<tbody>
				
				<c:forEach var="bill" items="${billList}" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${bill.callDate}</td>
						<td>${bill.callTime}</td>
						<td>${bill.toTelephone}</td>
						<td>${bill.toCountryName}</td>
						<td>${bill.callDuration}</td>
						<td>${bill.callRate}</td>
						<td>${bill.callCost}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>

		<ul class="pager">
			<li class="previous"><a onclick="nextPage('${currentPage-1}');" title="Previous">&larr;
					Previous</a></li>
			<li><select id="pageSelection" onchange="nextPage(this.value);">
					<c:forEach begin="1" end="${totalPages}" varStatus="loop">
						<option value="${loop.index}">${loop.index}</option>
					</c:forEach>
			</select></li>
			<li class="next"><a onclick="nextPage('${currentPage+1}');" title="Next">Next&rarr;</a></li>
		</ul>
		<br>
	</div>

	<footer>
		<p>&copy; Company 2014 Developed By Yasir Mukhtar & Awais Tariq</p>
	</footer>
	</div>
</body>
</html>
