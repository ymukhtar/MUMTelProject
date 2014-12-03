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
	function getServices(){
		var urlA="<%=request.getContextPath()%>/fetchCountryServices?country="+ jQuery('#countrySS').val();
		window.location.href = urlA;
	}
	
	jQuery(document).ready(function() {
		<c:if test="${serviceSelected!=null}">
			jQuery('#serviceSS').val('${serviceSelected}');
		</c:if>
		<c:if test="${countrySelected!=null}">
			jQuery('#countrySS').val('${countrySelected}');
		</c:if>
	});
</script>
</head>
<body>

	<!-- Main jumbotron for a primary marketing message or call to action -->

	<div id="page-wrapper">
		<center>
			<h2>Upload Customer</h2>
		</center>
		<div class="container">
			<fieldset>
			<legend>Upload Customers:</legend>
			<form:form modelAttribute="fileuploadForm" method="post" enctype="multipart/form-data" action="uploadCustomers">
				<div class="container">
					<form:input class="btn btn-default" path="fileData" type="file" />
				</div>
				<div class="container">
					<input class="btn btn-default" type="submit" />
				</div>
			</form:form>
		</fieldset>
		
		</div>
		<form:form class="form-horizontal" modelAttribute="customer"
			method="post" action="saveCustomer">
			<div class="container">
				<div class="form-group">
					<label for="serviceCountry.country.callingCode" class="col-sm-2 control-label">Country:</label>
					<div class="col-sm-7">
						<form:select id="countrySS" path="serviceCountry.country.callingCode" cssClass="form-control" onchange="getServices();">
							<c:forEach var="country" items="${countriesList}">
								<form:option value="${country.callingCode}">${country.countryName}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label for="serviceCountry.serviceCountryID" class="col-sm-2 control-label">Service:</label>
					<div class="col-sm-7">
						<form:select id="serviceSS" path="serviceCountry.serviceCountryID" cssClass="form-control">
							<c:forEach var="serviceCountry" items="${countryServiceList}">
								<form:option value="${serviceCountry.serviceCountryID}">${serviceCountry.service.description}</form:option>
							</c:forEach>
						</form:select>
					</div>
				</div>
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
					<label for="telephone" class="col-sm-2 control-label">Phone
						Number:</label>
					<div class="col-sm-7">
						<form:input type="text" cssClass="form-control" id="telephone"
							path="telephone" placeholder="Phone Number" />
					</div>
					<div class="col-sm-3">
						<form:errors path="telephone" cssClass="error" />
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
					<label for="salesRepAssigned" class="col-sm-2 control-label">Sales
						Representative:</label>
					<div class="col-sm-7">
						<form:select cssClass="form-control" path="salesRepAssigned"
							id="salesRepAssigned" placeholder="Select Sales Representative">
							<c:forEach var="salesRep" items="${allSalesRep}">
								<form:option cssClass="form-control"
									value="${salesRep.personID}">${salesRep}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div class="col-sm-3">
						<form:errors path="salesRepAssigned" cssClass="error" />
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
