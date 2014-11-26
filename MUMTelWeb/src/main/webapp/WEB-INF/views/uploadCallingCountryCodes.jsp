<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Calling Codes</title>
</head>
<body>
    <form:form modelAttribute="fileuploadForm" method="post" enctype="multipart/form-data" action="uploadCallingCountries">
        <form:label for="fileData" path="fileData">Select file</form:label><br/><br/>
        <form:input path="fileData" type="file"/>
        <input type="submit" />
    </form:form>
</body>
</html>