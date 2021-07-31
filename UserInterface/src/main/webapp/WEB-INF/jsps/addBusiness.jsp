<%@page import="com.imo.ui.modal.PropertyDetails"%>
<%@page import="com.imo.ui.modal.BusinessDetails"%>
<%@page import="com.imo.ui.modal.ConsumerDetails"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Policy Administration System</title>

<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

<!-- Integrated bootstrap css style  -->
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- Integrating font awesome icons  -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<!-- Integrating google material icons  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="/css/styles.css" rel="stylesheet" />

<style>
table {
	margin-left: 25%;
}
</style>
</head>

<body>
	<div class="d-flex" id="wrapper">
		<!-- Sidebar-->
		<div class="border-end bg-white" id="sidebar-wrapper">
			<div class="sidebar-heading border-bottom bg-light"><a href="/" class="btn btn-light p-2 font-weight-bold">Policy Administration</a></div>
			<div class="list-group list-group-flush">
				<button type="button"
					class="list-group-item list-group-item-action list-group-item-light p-3"
					data-toggle="modal" data-target="#createBP">Create
					Business & Property</button>

				<a href="/consumerDetails"
					class="list-group-item list-group-item-action list-group-item-light p-3">
					Edit Business & Property </a>

				<button type="button" class="list-group-item list-group-item-action list-group-item-light p-3" 
				data-toggle="modal" data-target="#exampleModalCenter">
				Policy Management
				</button>

				<a href="/policyDetails"
					class="list-group-item list-group-item-action list-group-item-light p-3">
					View Policy </a>
			</div>
		</div>
		<!-- Page content wrapper-->
		<div id="page-content-wrapper">
			<!-- Top navigation-->
			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom p-3">
				<div class="container-fluid">
					<button class="btn btn-primary" id="sidebarToggle">Menu</button>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav ms-auto mt-2 mt-lg-0">
							<li class="nav-item active"><a class="nav-link"
								href="/logout">Logout</a></li>
						</ul>
					</div>
				</div>
			</nav>

			<!-- Page content-->
			<div class="container-fluid">

				<%
				ConsumerDetails consumer = (ConsumerDetails) request.getAttribute("consumer");
				long cid = (long) request.getAttribute("cid");
				
				%>
				<div class="display-4 m-4 text-center">Business Details</div>
				<hr>

				<% String msg=(String)request.getAttribute("msg"); if (msg !=null) { %>
					<div class="container">
						<div class="alert alert-danger alert-dismissible fade show mt-5 p-3 " role="alert">
							<%=msg %>
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
						</div>
					</div>
					<% } %>
				<div class="table-responsive ">
					<form:form action="/add-business"
						modelAttribute="businessDetails" method="POST">

						<input name="cid" value="<%=cid %>" type="hidden" />
						<%
						List<String> bussinessCategory = (List) request.getAttribute("bussinessCategory");
						List<String> bussinessType = (List) request.getAttribute("bussinessType");
						List<String> propertyType = (List) request.getAttribute("propertyType");
						List<String> insuranceType = (List) request.getAttribute("insuranceType");
						List<String> buildingType = (List) request.getAttribute("buildingType");

						%>


						<div class="modal-body">
							
							<div class="form-group">
								<label for="businessCategory">Business Category</label> <select
									name="businessCategory" class="form-control">
									<%
									for (String cc : bussinessCategory) {
									%>
									<option value="<%=cc%>"><%=cc%></option>
									<%
									}
									%>
								</select>
							</div>

							<div class="form-group">
								<label for="businessType">Business Type</label> <select
									name="businessType" class="form-control">
									<%
									for (String cc : bussinessType) {
									%>
									<option value="<%=cc%>"><%=cc%></option>
									<%
									}
									%>
								</select>
							</div>
							
							<div class="form-group">
								<label for="businessTurnover">Business Turnover</label> <input
									type="number" min="0" name="businessTurnover"
									 class="form-control"
									placeholder="Business Turnover" required>
							</div>
							
							<div class="form-group">
								<label for="capitalInvested">Capital Invested</label> <input
									type="number" min="0" name="capitalInvested"
									class="form-control"
									placeholder="Capital Invested" required>
							</div>
							
							<div class="form-group">
								<label for="totalEmployee">Total Employee</label> <input
									type="number" min="0" name="totalEmployee"
									 class="form-control"
									placeholder="Total Employee" required>
							</div>
							
							<div class="form-group">
								<label for="businessAge">Business Age</label> <input
									type="number" min="0" name="businessAge" path="businessAge"
									class="form-control" placeholder="Business Age" required>
							</div>
							
							<div class="form-group">
								<label for="propertyType">Property Type</label> <select
									name="propertyType" class="form-control">
									<%
									for (String cc : propertyType) {
									%>
									<option value="<%=cc%>"><%=cc%></option>
									<%
									}
									%>
								</select>
							</div>
							
							<div class="form-group">
								<label for="buildingsqft">Building Square Feet</label> <input
									type="text" name="buildingsqft" path="buildingsqft"
									class="form-control" placeholder="Building Square Feet"
									required>
							</div>
							
							<div class="form-group">
								<label for="buildingType">Building Type</label> <select
									name="buildingType" class="form-control">
									<%
									for (String cc : buildingType) {
									%>
									<option value="<%=cc%>"><%=cc%></option>
									<%
									}
									%>
								</select>
							</div>
							
							<div class="form-group">
								<label for="buildingStoreys">Building Storeys</label> <input
									type="text" name="buildingStoreys" path="buildingStoreys"
									class="form-control" placeholder="Building Storeys" required>
							</div>
							
							<div class="form-group">
								<label for="buildingAge">Building Age</label> <input
									type="number" min="0" name="buildingAge" path="buildingAge"
									class="form-control" placeholder="Building Age" required>
							</div>
							
							<div class="form-group">
								<label for="costoftheasset">Cost Of The Assets</label> <input
									type="number" min="0" name="costOfTheAsset"
									path="costoftheasset" class="form-control"
									placeholder="Cost Of The Assets" required>
							</div>
							
							<div class="form-group">
								<label for="usefullLifeoftheasset">Useful Life Of The
									Assets</label> <input type="number" min="0" name="lifeOfAsset"
									path="usefullLifeoftheasset" class="form-control"
									placeholder="Useful Life Of The Assets" required>
							</div>
							
							<div class="form-group">
								<label for="salvageValue">Salvage Value</label> <input
									type="number" min="0" name="salvageValue" path="salvageValue"
									class="form-control" placeholder="Salvage Value" required>
							</div>
							
						</div>

						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">Save</button>
						</div>
					</form:form>
				</div>

				<!-- Modals -->
				<!-- Create Business and Property -->
				<div class="modal fade" id="createBP" tabindex="-1" role="dialog"
					aria-labelledby="createBPLabel" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="createBPLabel">Create Business
									And Property</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<form:form action="/add-consumer"
								modelAttribute="consumerDetails" method="POST">

								<%
								List<String> bussinessCategory = (List) request.getAttribute("bussinessCategory");
								List<String> bussinessType = (List) request.getAttribute("bussinessType");
								List<String> propertyType = (List) request.getAttribute("propertyType");
								List<String> insuranceType = (List) request.getAttribute("insuranceType");
								List<String> buildingType = (List) request.getAttribute("buildingType");

								String msg = (String) request.getAttribute("msg");

								if (msg != null) {
								%>
								<script>alert("<%=msg%>
									");
								</script>
								<%
								}
								%>


								<div class="modal-body">
									<div class="form-group">
										<label for="consumerName">Consumer Name</label> <input
											type="text" name="consumerName" path="consumerName"
											class="form-control" placeholder="Consumer Name" required>
									</div>
									<sf:errors style="color:red" path="consumerName"></sf:errors>

									<div class="form-group">
										<label for="dateOfBirth">Date Of Birth</label> <input
											type="date" name="dateOfBirth" path="dateOfBirth"
											class="form-control" placeholder="Date Of Birth" required>
									</div>
									<sf:errors style="color:red" path="dateOfBirth"></sf:errors>

									<div class="form-group">
										<label for="panDetails">Pan Details</label> <input type="text"
											name="panDetails" path="panDetails" class="form-control"
											placeholder="XXXXXXX" required>
									</div>
									<sf:errors style="color:red" path="panDetails"></sf:errors>

									<div class="form-group">
										<label for="phone">Phone No.</label> <input type="tel"
											name="phone" class="form-control" placeholder="phone no"
											required>
									</div>


									<div class="form-group">
										<label for="email">Email</label> <input type="email"
											name="email" path="email" class="form-control"
											placeholder="Email Address" required>
									</div>
									<sf:errors style="color:red" path="email"></sf:errors>

									<div class="form-group">
										<label for="agentName">Agent Name</label> <input type="text"
											name="agentName" path="agentName" class="form-control"
											value="${agentId}" readonly>
									</div>
									<sf:errors style="color:red" path="agentName"></sf:errors>

									<div class="form-group">
										<label for="businessCategory">Business Category</label> <select
											name="businessCategory" class="form-control">
											<%
											for (String cc : bussinessCategory) {
											%>
											<option value="<%=cc%>"><%=cc%></option>
											<%
											}
											%>
										</select>
									</div>

									<div class="form-group">
										<label for="businessType">Business Type</label> <select
											name="businessType" class="form-control">
											<%
											for (String cc : bussinessType) {
											%>
											<option value="<%=cc%>"><%=cc%></option>
											<%
											}
											%>
										</select>
									</div>
									<sf:errors style="color:red" path="businessType"></sf:errors>

									<div class="form-group">
										<label for="businessTurnover">Business Turnover</label> <input
											type="number" min="0" name="businessTurnover"
											path="businessTurnover" class="form-control"
											placeholder="Business Turnover" required>
									</div>
									<sf:errors style="color:red" path="businessTurnover"></sf:errors>

									<div class="form-group">
										<label for="capitalInvested">Capital Invested</label> <input
											type="number" min="0" name="capitalInvested"
											path="capitalInvested" class="form-control"
											placeholder="Capital Invested" required>
									</div>
									<sf:errors style="color:red" path="capitalInvested"></sf:errors>

									<div class="form-group">
										<label for="totalEmployee">Total Employee</label> <input
											type="number" min="0" name="totalEmployee"
											path="totalEmployee" class="form-control"
											placeholder="Total Employee" required>
									</div>
									<sf:errors style="color:red" path="totalEmployee"></sf:errors>

									<div class="form-group">
										<label for="businessAge">Business Age</label> <input
											type="number" min="0" name="businessAge" path="businessAge"
											class="form-control" placeholder="Business Age" required>
									</div>
									<sf:errors style="color:red" path="businessAge"></sf:errors>

									<div class="form-group">
										<label for="propertyType">Property Type</label> <select
											name="propertyType" class="form-control">
											<%
											for (String cc : propertyType) {
											%>
											<option value="<%=cc%>"><%=cc%></option>
											<%
											}
											%>
										</select>
									</div>
									<sf:errors style="color:red" path="propertyType"></sf:errors>

									<div class="form-group">
										<label for="buildingsqft">Building Square Feet</label> <input
											type="text" name="buildingsqft" path="buildingsqft"
											class="form-control" placeholder="Building Square Feet"
											required>
									</div>
									<sf:errors style="color:red" path="buildingsqft"></sf:errors>

									<div class="form-group">
										<label for="buildingType">Building Type</label> <select
											name="buildingType" class="form-control">
											<%
											for (String cc : buildingType) {
											%>
											<option value="<%=cc%>"><%=cc%></option>
											<%
											}
											%>
										</select>
									</div>
									<sf:errors style="color:red" path="buildingType"></sf:errors>

									<div class="form-group">
										<label for="buildingStoreys">Building Storeys</label> <input
											type="text" name="buildingStoreys" path="buildingStoreys"
											class="form-control" placeholder="Building Storeys" required>
									</div>
									<sf:errors style="color:red" path="buildingStoreys"></sf:errors>

									<div class="form-group">
										<label for="buildingAge">Building Age</label> <input
											type="number" min="0" name="buildingAge" path="buildingAge"
											class="form-control" placeholder="Building Age" required>
									</div>
									<sf:errors style="color:red" path="buildingAge"></sf:errors>

									<div class="form-group">
										<label for="costoftheasset">Cost Of The Assets</label> <input
											type="number" min="0" name="costOfTheAsset"
											path="costoftheasset" class="form-control"
											placeholder="Cost Of The Assets" required>
									</div>
									<sf:errors style="color:red" path="costoftheasset"></sf:errors>

									<div class="form-group">
										<label for="usefullLifeoftheasset">Useful Life Of The
											Assets</label> <input type="number" min="0" name="lifeOfAsset"
											path="usefullLifeoftheasset" class="form-control"
											placeholder="Useful Life Of The Assets" required>
									</div>
									<sf:errors style="color:red" path="usefullLifeoftheasset"></sf:errors>

									<div class="form-group">
										<label for="salvageValue">Salvage Value</label> <input
											type="number" min="0" name="salvageValue" path="salvageValue"
											class="form-control" placeholder="Salvage Value" required>
									</div>
									<sf:errors style="color:red" path="salvageValue"></sf:errors>
								</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Save
										Consumer</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<!-- Create Business and Property Ends-->

				<!-- Issue policy Modal -->
				<!-- Modal -->
				<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLongTitle">Issue Policy</h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form action="/issue-policy" modelAttribute="detail"
										method="POST">
										<div class="modal-body">
											
											<div class="form-group">
												<label for="businessId">Policy Unique Id</label> <input
													type="number" name="uniqueId"
													class="form-control" placeholder="Policy Unique Id">
											</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary">Issue
												Policy</button>
										</div>
									</form>
							</div>
						</div>

						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="createPolicyLabel">Create
									Policy</h5>
							</div>
							<form action="/create-policy" modelAttribute="detail"
								method="POST">
								<div class="modal-body">
									<div class="form-group">
										<label for="consumerId">Consumer Id</label> <input
											type="number" name="consumerId" 
											class="form-control" placeholder="Consumer Id">
									</div>

									<div class="form-group">
										<label for="businessId">Business Id</label> <input
											type="number" name="businessId"
											class="form-control" placeholder="Business Id">
									</div>

								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Create
										Policy</button>
								</div>
							</form>
						</div>

					</div>
				</div>
				
				<!-- Issue policy ends -->

			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/js/scripts.js"></script>
	<script src="/js/jquery-3.6.0.js"></script>
	<script src="/js/bootstrap.js"></script>
	<script src="/js/popper.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
		integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
		crossorigin="anonymous"></script>
</body>

</html>