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
	<jsp:param value="a" name="a"/>
</jsp:include>

<title>MUMTel</title>
<script type="text/javascript">
	function nextPage(page){
		if(page<1 || page>parseInt("${totalPages}",10))
		{}
		else
		{
			var urlA="<%=request.getContextPath()%>/countries?currentPage="+page+"&searchString="+jQuery('#searchType').val();
			window.location.href=urlA;
		}

	}
	function findCodes(){
		
		var urlA="<%=request.getContextPath()%>/countries?currentPage=1&searchString="+jQuery('#searchType').val();
		window.location.href=urlA;
		
	}
	
	jQuery(document).ready(function(){
		jQuery('#pageSelection').val('${currentPage}');
		
	});
	
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->

    <div id="page-wrapper">
    	<center><h2>Upload Countries Calling Code</h2></center>
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
			<div class="row">
				<h4>Regester New Customer</h4>
			</div>

		</div>
	

	<footer>
		<p>&copy; Company 2014 Developed By Yasir Mukhtar & Awais Tariq</p>
	</footer>
	</div>
</body>
</html>
