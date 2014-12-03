<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
	function findCodes(){
		var urlA="<%=request.getContextPath()%>/serviceAndRatesDetails?currentPage=1&searchString="+ jQuery('#searchType').val();
		window.location.href = urlA;
	}
	
	function showRateList(serviceCode,countryCode,type,index){
		var urlA="<%=request.getContextPath()%>/report/rate_sheet/pdf?serviceCode="+serviceCode+"&countryCode="+countryCode+"&month="+jQuery('#month'+index).val()+"&year="+jQuery('#year'+index).val();
		window.location.href = urlA;
	}

	jQuery(document).ready(function() {
		jQuery('#searchType').val('${selectedCountryCode}');
	});
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->

	<div id="page-wrapper">
		<center>
			<h2>Upload Services and Rates</h2>
		</center>
		<div class="container">
			<fieldset>
			<legend>Upload Services And Peak Times:</legend>
			<form:form modelAttribute="fileuploadForm" method="post" enctype="multipart/form-data" action="uploadServicesAndPeakTimes">
				<div class="container">
					<form:input class="btn btn-default" path="fileData" type="file" />
				</div>
				<div class="container">
					<input class="btn btn-default" type="submit" />
				</div>
			</form:form>
		</fieldset>
		
		</div>
		<div class="container">
			<fieldset>
			<legend>Upload Rate Sheets:</legend>
			<form:form modelAttribute="fileuploadForm" method="post" enctype="multipart/form-data" action="uploadServicesAndRates">
				<div class="container">
					<form:input class="btn btn-default" path="fileData" type="file" />
				</div>
				<div class="container">
					<input class="btn btn-default" type="submit" />
				</div>
			</form:form>
		</fieldset>
		</div>
		<div class="container">
			<div class="row">
				<h4>Search Services</h4>
			</div>
			<div class="row">
				<form class="form-horizontal" role="form" method="POST"
					id="searchFrom">
					<div class="form-group form-group-lg">
						<select name="searchType" id="searchType" class="form-control"
							onchange="findCodes();">
							<c:forEach var="country" items="${allCountries}">
								<option value="${country.callingCode}">${country.countryName}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group form-group-lg">
						<a class="btn btn-default" href='<c:url value="/createService"/>'
							role="button"> Create New Service </a>
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
					<th>Service Name</th>
					<th>Date Created</th>
					<th>Peak Times(Start-End)</th>
					<th>Rate Sheet</th>
				</tr>
			<thead>
			<tbody>
				<c:forEach var="CountryService" items="${countryServiceList}"
					varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${CountryService.service.description}</td>
						<td>${CountryService.dateCreated}</td>
						<td><fmt:formatNumber pattern="##"
								value="${CountryService.peakTime.peakPeriodStartHr}"
								minIntegerDigits="2"></fmt:formatNumber> :<fmt:formatNumber
								pattern="##"
								value="${CountryService.peakTime.peakPeriodStartMin}"
								minIntegerDigits="2"></fmt:formatNumber> - <fmt:formatNumber
								pattern="##"
								value="${CountryService.peakTime.offPeakPeriodStartHr}"
								minIntegerDigits="2"></fmt:formatNumber> :<fmt:formatNumber
								pattern="##"
								value="${CountryService.peakTime.offPeakPeriodStartMin}"
								minIntegerDigits="2"></fmt:formatNumber> <c:if
								test="${CountryService!=null }">
								<a class="btn btn-default"
									href='<c:url value="/updatePeakTime?serviceCode=${CountryService.service.serviceCode}&countryCode=${CountryService.country.callingCode}"/>'
									role="button">Update</a>
							</c:if></td>
						<td>
							<select name="month${loop.index}" id="month${loop.index}" >
								<c:forEach var="month" items="${months}">
							   		 <option value="${month.key}">${month.value}</option>
							   	 </c:forEach>
							</select>
							&nbsp;&nbsp;
							<select name="year${loop.index}" id="year${loop.index}" >
							<c:forEach var="year" items="${years}">
						   		 <option value="${year}">${year}</option>
						   	 </c:forEach>
						</select>
						<a class="btn btn-default"
							href='#'
							onclick='javascript:showRateList("${CountryService.service.serviceCode}","${CountryService.country.callingCode}","pdf","${loop.index}")'
							role="button">PDF</a> &nbsp;&nbsp; <a class="btn btn-default"
							href='#'
							onclick='javascript:showRateList("${CountryService.service.serviceCode}","${CountryService.country.callingCode}","xls","${loop.index}")'
							role="button">XLS</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
