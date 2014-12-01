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
<script type="text/javascript">
	
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->

	<div id="page-wrapper">
		<center>
			<h2>Update Peak Time</h2>
		</center>
		<form:form class="form-horizontal" modelAttribute="peakTime" method="post" action="updatePeakTime">
			<div class="container">
				<div class="form-group">
					<label for="serviceCountry.country.countryName" class="col-sm-2 control-label">Country:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" path="serviceCountry.country.countryName"  disabled="true"/>	 
					</div>
					<div class="col-sm-3">
						<form:errors path="serviceCountry.country.countryName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label for="serviceCountry.service.description" class="col-sm-2 control-label">Service Name:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" path="serviceCountry.service.description" disabled="true"/>
					</div>
					<div class="col-sm-3">
						<form:errors path="serviceCountry.service.description" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label for="peakPeriodStart" class="col-sm-2 control-label">Peak Time Start:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" path="peakPeriodStart" />	 
					</div>
					<div class="col-sm-3">
						<form:errors path="peakPeriodStart" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label for="offPeakPeriodStart" class="col-sm-2 control-label">Peak Time End:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" path="offPeakPeriodStart" />
					</div>
					<div class="col-sm-3">
						<form:errors path="offPeakPeriodStart" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Update</button>
					</div>
				</div>
			</div>
		</form:form>

	</div>

	<footer>
		<p>&copy; Company 2014 Developed By Yasir Mukhtar & Awais Tariq</p>
	</footer>
	</div>
</body>
</html>
