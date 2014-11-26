<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<sec:authorize access="hasRole('ROLE_JOB_SEEKER')">
<jsp:include page="includeHome.jsp">
	<jsp:param value="a" name="a" />
</jsp:include>
</sec:authorize>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Job Portal Welcome</title>
<sec:authorize access="isAnonymous()">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/jumbotron.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/jQuery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</sec:authorize>
<script type="text/javascript">
	function nextPage(page){
		if(page<1 || page>parseInt("${totalPages}",10))
		{}
		else
		{
			
			var urlA="<%=request.getContextPath()%>/JobSearch?currentPage="+page+"&searchString="+jQuery('#searchType').val()+"&searchAddress="+jQuery('#searchAddress').val();
			window.location.href=urlA;
		}

	}
	function findJobs(){
		
		var urlA="<%=request.getContextPath()%>/JobSearch?currentPage=1&searchString="+jQuery('#searchType').val()+"&searchAddress="+jQuery('#searchAddress').val();
		window.location.href=urlA;
		
	}
	
	function viewAllApplication(){
		
		var urlA="<%=request.getContextPath()%>/viewAllApplication?currentPage=1";
		window.location.href=urlA;
		
	}
	
	jQuery(document).ready(function(){
		jQuery('#pageSelection').val('${currentPage}');
		
	});
	
</script>
</head>
<body>
<sec:authorize access="isAnonymous()">
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>">JobPortal</a>
			</div>
			<div class="navbar-collapse collapse">
				<form class="navbar-form navbar-right" role="form" method="POST"  action="<c:url value='j_spring_security_check'/>">
					<div class="form-group">
						<input type="text" name="j_username" placeholder="User Name" class="form-control">
					</div>
					<div class="form-group">
						<input name="j_password" type="password" placeholder="Password" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
				</form>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>
</sec:authorize>


	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<h3>
				<sec:authorize access="hasRole('ROLE_JOB_SEEKER')">
					Welcome
					<sec:authentication property="principal.username" />
					as Job Seeker
				</sec:authorize>
				</h3>
				<div class="form-group form-group-lg" style='margin-left: 85%'>
					<a class="btn btn-default" role="button" onclick="viewAllApplication()">
				<span class="glyphicon glyphicon-search"></span>View All Applications</a>
				</div>
				<p>Search from Millions of Jobs</p>
			</div>
			<div class="row">
				<form class="form-horizontal" role="form" method="POST" id="searchFrom">
					<div class="form-group form-group-lg">
						<input type="text" name="searchType" id="searchType" value="${searchString}"
							placeholder="Job Title, Keywords or Company Name"
							class="form-control">
					</div>
					<div class="form-group form-group-lg">
						<input name="searchAddress" type="text" id="searchAddress" value="${searchAddress}"
							placeholder="City and State or Zip Code" class="form-control">
					</div>
					<div class="form-group form-group-lg">
						<a class="btn btn-default" role="button" onclick="findJobs();"><span
							class="glyphicon glyphicon-search"></span>&nbsp;Find Jobs</a>
					
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
					<sec:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<th></th>
					</sec:authorize>
				</tr>
			<thead>
			<tbody>
			<c:forEach var="country" items="${countryList}" varStatus="loop">
				<tr>
					<td>${loop.index}</td>
					<td>${country.callingCode}</td>
					<td>${country.countryName}</td>
					<sec:authorize access="hasRole('ROLE_JOB_SEEKER')">
						<td><a href="<c:url value="/apply/${vaccancy.id}"/>">apply</a></td>
					</sec:authorize>
					
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
