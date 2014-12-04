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
			var urlA="<%=request.getContextPath()%>/salesRepDetails?currentPage="+page+"&searchString="+jQuery('#searchType').val();
			window.location.href=urlA;
		}

	}
	
	function findCustomers(){
		
		var urlA="<%=request.getContextPath()%>/salesRepDetails?currentPage=1&searchString="+jQuery('#searchType').val();
		window.location.href=urlA;
		
	}
	
	function viewBills(b){
		
	alert(b);
		var urlA="<%=request.getContextPath()%>/viewCommission?personeId="+document.getElementById('lbl'+b).innerText+"&month="+jQuery('#month'+b).val()+"&year="+jQuery('#year'+b).val();
		window.location.href=urlA;
		
	}
	
    function checkKey()
    {
        if (window.event.keyCode == 13)
        {
        	findCustomers();
            window.event.preventDefault();
        }
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

<body onkeypress="checkKey()">

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div id="page-wrapper">
	<center><h2>Sales Rep Details</h2></center>
		<div class="container">
			<div class="row">
				<h4>Search Sales Rep by Entering phone number or Name</h4>
			</div>
			<div class="row">
					<div class="form-group form-group-lg">
						<input type="text" name="searchType" id="searchType"
							value="${searchString}" placeholder="Phone Number"
							class="form-control">
					</div>
					<div class="form-group form-group-lg">
						<a class="btn btn-default" role="button"
							onclick="findCustomers();"><span
							class="glyphicon glyphicon-search"></span>&nbsp;Find Sales Rep Details</a>

					</div>
			</div>
		</div>

		<h3>${message}</h3>
		<hr></hr>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>Sales Rep ID</th>
					<th>Name</th>
					<th>Buisness Address</th>
					<th>Email</th>
					<th>Select Month</th>
					<th>Select Year</th>
					<th>View Commission</th>
				</tr>
			<thead>
			<tbody>
				
				<c:forEach var="c" items="${salesRepList}" varStatus="loop">
					<tr>
						<td>${startIndex+loop.index+1}</td>
						<td><label id="lbl${loop.index}" >${c.businesssPhone}</label></td>
						<td>${c.firstName} ${c.lastName}</td>
						<td>${c.businesssAddress.streetNo}, ${c.businesssAddress.city}, ${c.businesssAddress.state}, ${c.businesssAddress.zip}</td>
						<td>${c.emailAddress}</td>
												<td>
						<select name="month${loop.index}" id="month${loop.index}" class="form-control">
							<c:forEach var="month" items="${months}">
						   		 <option value="${month.key}">${month.value}</option>
						   	 </c:forEach>
						</select>
						</td>
						<td>
						<select name="year${loop.index}" id="year${loop.index}" class="form-control">
							<c:forEach var="year" items="${years}">
						   		 <option value="${year}">${year}</option>
						   	 </c:forEach>
						</select>
						</td>
						<td>
							<a class="btn btn-default" onclick="viewBills(${loop.index})"
									role="button">View Commission
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
