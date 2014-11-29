<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Welcome to MUMTel</title>
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/jumbotron.css"/>" rel="stylesheet">
		<script src="<c:url value="/resources/js/jQuery.min.js"/>"></script>
    	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    	
    	<script type="text/javascript">
    	
	    	function findJobs(){
	    		
	    		var urlA="<%=request.getContextPath()%>/JobSearch?currentPage=1&searchString="+jQuery('#searchType').val()+"&searchAddress="+jQuery('#searchAddress').val();
	    		window.location.href=urlA;
	    		
	    	}
    	
    	</script>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">MUMTEL</a>
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

		<hr>

		<footer>
			<p>&copy; Company 2014 Developed By Yasir Mukhtar, Awais Tariq and Samuel Heye</p>
		</footer>
	</div>
</body>
</html>
