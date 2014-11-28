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

</head>

<body>

	<div id="page-wrapper" style="margin-left: 17%">
		<h4>${errorMessage}</h4>
		<a class="btn btn-default" role="button" onclick="window.history.back();"><span
			class="glyphicon glyphicon-search"></span>&nbsp;Go Back</a>
	</div>


</body>

</html>
