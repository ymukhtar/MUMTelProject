<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	function viewBills(i){
		alert(i);
		var urlA="<%=request.getContextPath()%>/viewBills?personeId="+i+"&month="+jQuery('#month').val()+"&year="+jQuery('#year').val();
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
	<center><h2>Customer Details</h2></center>
		<div class="container">
			<div class="row">
				<h4>Search Customers by Entering phone number or Name</h4>
			</div>
			<div class="row">
				<form class="form-horizontal" role="form" method="POST"
					id="searchFrom">
					<div class="form-group form-group-lg">
						<input type="text" name="searchType" id="searchType"
							value="${searchString}" placeholder="Phone Number"
							class="form-control">
					</div>
					<div class="form-group form-group-lg">
						<a class="btn btn-default" role="button"
							onclick="findCallDetails();"><span
							class="glyphicon glyphicon-search"></span>&nbsp;Find Customer Details</a>

					</div>
				</form>
			</div>
		</div>

		<h3>${message}</h3>
		<hr></hr>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Email</th>
					<th>Select Month</th>
					<th>Select year</th>
					<th>View Bill</th>
				</tr>
			<thead>
			<tbody>
				
				<c:forEach var="c" items="${customerList}" varStatus="loop">
					<tr>
						<td>${startIndex+loop.index+1}</td>
						<td>${c.firstName} ${c.lastName}</td>
						<td>${c.address.streetNo}, ${c.address.city}, ${c.address.state}, ${c.address.zip}</td>
						<td>${c.telephone}</td>
						<td>${c.emailAddress}</td>
						<td>
						<select name="month" id="month" class="form-control">
							<c:forEach var="month" items="${months}">
						   		 <option value="${month.key}">${month.value}</option>
						   	 </c:forEach>
						</select>
						</td>
						<td>
						<select name="year" id="year" class="form-control">
							<c:forEach var="year" items="${years}">
						   		 <option value="${year}">${year}</option>
						   	 </c:forEach>
						</select>
						</td>
						
						<td>
							<a class="btn btn-default" onclick="viewBills(${c.personID})"
									role="button">View Bills
							</a>
					   </td>
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
