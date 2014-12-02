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
			<h2>Create New Service</h2>
		</center>
		<form:form class="form-horizontal" modelAttribute="serviceCountry"
			method="post" action="saveServiceCountry">
			<div class="container">
				<div class="form-group">
					<label for="country.callingCode" class="col-sm-2 control-label">Country:</label>
					<div class="col-sm-7">
						<form:select path="country.callingCode" class="form-control">
							<c:forEach var="country" items="${countryList}">
						   		 <option value="${country.callingCode}">${country.countryName}</option>
						   	 </c:forEach>
						</form:select>
					</div>
					<div class="col-sm-3">
						<form:errors path="country.callingCode" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label for="service.description" class="col-sm-2 control-label">Name:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" path="service.description" placeholder="Service Name" />
					</div>
					<div class="col-sm-3">
						<form:errors path="service.description" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">Save</button>
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
