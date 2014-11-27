<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Upload Calling Codes</title>
    <jsp:include page="includeHome.jsp">
	<jsp:param value="a" name="a"/>
</jsp:include>
</head>
<body>
<br>
    <form:form modelAttribute="fileuploadForm" method="post" enctype="multipart/form-data" action="uploadCallingCountries">
    	<div class="container">
        <form:label class="lbl lbl-default" for="fileData" path="fileData">Select file</form:label><br/><br/>
        </div>
        <div class="container">
        <form:input class="btn btn-default" path="fileData" type="file"/>
        </div>
        <div class="container">
        <input class="btn btn-default" type="submit" />
        </div>
    </form:form>
    
    <div class="container">
    	<a class="btn btn-default" href='<c:url value="/countries?currentPage=1&searchString="/>' role="button">View Countries Codes</a>
    </div>
</body>
</html>