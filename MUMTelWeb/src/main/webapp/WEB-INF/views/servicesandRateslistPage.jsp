<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<script language="javascript" type="text/javascript">
	
	function findCodes(){
		var urlA="<%=request.getContextPath()%>/serviceAndRatesDetails?currentPage=1&searchString="+ jQuery('#searchType').val();
		window.location.href = urlA;
	}

	$(document).ready(function() {
		jQuery('#searchType').val('${selectedCountryCode}');
	});
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->

	<div id="page-wrapper">
		<h1>Rate Sheet Uploader</h1>
		<form:form modelAttribute="fileuploadForm" method="post"
			enctype="multipart/form-data" action="uploadServicesAndRates">
			<div class="container">
				<form:label class="lbl lbl-default" for="fileData" path="fileData">Upload File</form:label>
				<br /> <br />
			</div>
			<div class="container">
				<form:input class="btn btn-default" path="fileData" type="file" />
			</div>
			<div class="container">
				<input class="btn btn-default" type="submit" />
			</div>
		</form:form>
		<div class="container">
			<div class="row">
				<h2>Search Services</h2>
			</div>
			<div class="row">
				<form class="form-horizontal" role="form" method="POST"
					id="searchFrom">
					<div class="form-group form-group-lg">
						<select name="searchType" id="searchType" class="form-control" onchange="findCodes();">
							<c:forEach var="country" items="${allCountries}">
						   		 <option value="${country.callingCode}">${country.countryName}</option>
						   	 </c:forEach>
						</select>
					</div>
					
					<div class="form-group form-group-lg">
						<a class="btn btn-default"
							href='<c:url value="/report/country_list_report/pdf"/>'
							role="button">Generate Countries Report</a>
					</div>
				</form>
			</div>
		</div>

		<h3>${message}</h3>
		<hr></hr>
		<table id="example" class="table table-striped table-bordered" cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>Service Name</th>
					<th>Date Created</th>
					<th>View Current Rate Sheet</th>
				</tr>
			<thead>
			<tbody>
				<c:forEach var="CountryService" items="${countryServiceList}"
					varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${CountryService.service.description}</td>
						<td>${CountryService.dateCreated}</td>
						<td><a class="btn btn-default"
							   href='<c:url value="/report/rate_sheet/pdf?countryCode=${CountryService.country.callingCode}&serviceCode=${CountryService.service.serviceCode}"/>' role="button">Rate Sheet</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
</body>
</html>
