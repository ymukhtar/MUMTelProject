<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="includeHome.jsp">
	<jsp:param value="a" name="a"/>
</jsp:include>
<title>Job Portal Welcome <sec:authentication property="principal.username" /></title>
</head>
<body>
	<h1>Welcome home <sec:authentication property="principal.username" /></h1>
	
	<a class="btn btn-default" href="<c:url value="/showCountriesUploadForm"/>" role="button">Upload Countries Codes</a>
	<a class="btn btn-default" href="<c:url value="/showUploadCallDetails"/>" role="button">Upload Call Data</a>
</body>
</html>