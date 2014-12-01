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
			<h2>Register New Sales Representative</h2>
		</center>
		<form:form class="form-horizontal" modelAttribute="salesRep"
			method="post"
			action="saveSalesRep">
			<div class="container">
					<div class="form-group">
						<label for="firstName" class="col-sm-2 control-label">First
							Name:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="firstName"
								path="firstName" placeholder="First Name" />
						</div>
						<div class="col-sm-3">
							<form:errors path="firstName" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="lastName" class="col-sm-2 control-label">Last
							Name:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="lastName"
								path="lastName" placeholder="Last Name" />
						</div>
						<div class="col-sm-3">
							<form:errors path="lastName" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="businesssPhone" class="col-sm-2 control-label">Business Phone:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="businesssPhone"
								path="businesssPhone" placeholder="Phone Number" />
						</div>
						<div class="col-sm-3">
							<form:errors path="businesssPhone" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="emailAddress" class="col-sm-2 control-label">Email
							Address:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="emailAddress"
								path="emailAddress" placeholder="Email Address" />
						</div>
						<div class="col-sm-3">
							<form:errors path="emailAddress" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="streetNo" class="col-sm-2 control-label">Street
							Address:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="streetNo"
								path="address.streetNo" placeholder="Street Address" />
						</div>
						<div class="col-sm-3">
							<form:errors path="address.streetNo" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="city" class="col-sm-2 control-label">City:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="city"
								path="address.city" placeholder="City" />
						</div>
						<div class="col-sm-3">
							<form:errors path="address.city" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="state" class="col-sm-2 control-label">State:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="state"
								path="address.state" placeholder="State" />
						</div>
						<div class="col-sm-3">
							<form:errors path="address.state" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="zip" class="col-sm-2 control-label">ZIP Code:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="zip"
								path="address.zip" placeholder="Zip Code" />
						</div>
						<div class="col-sm-3">
							<form:errors path="address.zip" cssClass="error" />
						</div>
					</div>
					
										<div class="form-group">
						<label for="bstreetNo" class="col-sm-2 control-label">Business Street:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="bstreetNo"
								path="businesssAddress.streetNo" placeholder="Street Address" />
						</div>
						<div class="col-sm-3">
							<form:errors path="businesssAddress.streetNo" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="bcity" class="col-sm-2 control-label">Business City:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="bcity"
								path="businesssAddress.city" placeholder="City" />
						</div>
						<div class="col-sm-3">
							<form:errors path="businesssAddress.city" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label for="bstate" class="col-sm-2 control-label">Business State:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="bstate"
								path="businesssAddress.state" placeholder="State" />
						</div>
						<div class="col-sm-3">
							<form:errors path="businesssAddress.state" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<label for="bzip" class="col-sm-2 control-label">Business ZIP Code:</label>
						<div class="col-sm-7">
							<form:input type="text" cssClass="form-control" id="bzip"
								path="businesssAddress.zip" placeholder="Zip Code" />
						</div>
						<div class="col-sm-3">
							<form:errors path="businesssAddress.zip" cssClass="error" />
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
