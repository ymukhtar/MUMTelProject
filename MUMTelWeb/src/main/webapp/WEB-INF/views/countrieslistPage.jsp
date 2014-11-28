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

    <div id="page-wrapper" style="margin-left: 17%">
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
				<h2>Search Countries Calling Code</h2>
			</div>
			<div class="row">
				<form role="form" method="POST" id="searchFrom">
					<div class="form-group form-group-lg">
						<input type="text" name="searchType" id="searchType" value="${searchString}"
							placeholder="Country Name"
							class="form-control">
					</div>
					<div class="form-group form-group-lg">
						<a class="btn btn-default" role="button" onclick="findCodes();"><span
							class="glyphicon glyphicon-search"></span>&nbsp;Find Codes</a>
					    <a class="btn btn-default" href='<c:url value="/report/country_list_report/pdf"/>' role="button">Generate Countries Report</a>
					
					</div>
					<div class="form-group form-group-lg">
					</div>
				</form>
			</div>
		</div>

		<h3>${message}</h3><hr></hr>
		<table id="example" class="table table-striped table-bordered" cellspacing="20" width="100%">
			<thead>
				<tr>
					<th>Sr #</th>
					<th>Calling Code</th>
					<th>Country Name</th>
				</tr>
			<thead>
			<tbody>
			<c:forEach var="country" items="${countryList}" varStatus="loop">
				<tr>
					<td>${loop.index+1}</td>
					<td>${country.callingCode}</td>
					<td>${country.countryName}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>

		<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    
    <ul class="pager">
	  <li class="previous"><a onclick="nextPage('${currentPage-1}');">&larr; Previous</a></li>
	  <li>
	  		<select id="pageSelection" onchange="nextPage(this.value);">
				<c:forEach begin="1" end="${totalPages}" varStatus="loop">
					<option value="${loop.index}">${loop.index}</option>
				</c:forEach>
			</select>
	  </li>
	  <li class="next"><a onclick="nextPage('${currentPage+1}');">Next &rarr;</a></li>
	</ul>
		<br>

	</div>

	<footer>
		<p>&copy; Company 2014 Developed By Yasir Mukhtar & Awais Tariq</p>
	</footer>
	</div>
</body>
</html>
