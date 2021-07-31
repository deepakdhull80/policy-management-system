<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">

			<!-- Integrated bootstrap css style  -->
			<link rel="stylesheet" href="css/bootstrap.css">

			<!-- Integrating font awesome icons  -->
			<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
				integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
				crossorigin="anonymous" referrerpolicy="no-referrer" />
			<!-- Integrating google material icons  -->
			<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
			<link href="css/login.css" rel="stylesheet" />
			<title>PAS</title>

		</head>

		<body>
			<div id="particles-js">
				<% String msg=(String) request.getAttribute("msg"); %>
					<nav class="navbar navbar-dark bg-dark">
						<a href="#" class="navbar-brand"> Policy Login </a>
					</nav>

					<% if (msg !=null) { %>
						<div class="container ">
							<div class="alert alert-warning alert-dismissible fade show p-3 " role="alert">
								<%=msg %>
									<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
							</div>
						</div>
						<% } %>

					<form:form method="POST" modelAttribute="user">

						<div class="login-box">
							<h1 class="text-center h1 text-light">
								<span class="material-icons p-2 " style="font-size: 3rem;">person</span>Policy
								Login
							</h1>

							<div class="user-box">
								<input type="text" name="username" required>
								<label>Username</label>
							</div>
							<div class="user-box">
								<input type="password" name="password" required>
								<label>Password</label>
							</div>
							<button type="submit" class="btn btn-outline-light">
								
								Submit
							</button>
						</div>
					</form:form>
			</div>

			<!-- Integrated bootstrap js  -->
			<script src="js/jquery-3.6.0.js"></script>
			<script src="js/bootstrap.js"></script>
			<script src="js/particles.js"></script>
			<script src="js/popper.js"></script>
			<script src="js/app.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
				integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
				crossorigin="anonymous"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
				integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
				crossorigin="anonymous"></script>
		</body>

		</html>