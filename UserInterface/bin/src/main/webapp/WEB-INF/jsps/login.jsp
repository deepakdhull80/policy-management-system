<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

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
            <title>PAS</title>

            <style>
                .material-icons {
                    vertical-align: middle;
                }

                .login-container {
                    background: url("images/primary-header@2x.jpg");
                    background-repeat: no-repeat;
                    background-size: 100% 100%;
                }
            </style>

        </head>

        <body>
            <nav class="navbar navbar-dark bg-dark">
                <a href="#" class="navbar-brand"> Policy Login </a>
            </nav>

            <div class="container w-50">
                <div class="alert alert-danger alert-dismissible fade show mt-5 p-3 " role="alert">
                    ${msg}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>

            <div class="container login-container mt-5 bg-info text-light p-3 w-50">

                <div class="shadow-none p-3 mb-5 rounded">
                    <form action="/index" modelAttribute="login" method="POST">
                        <h1 class="text-center h1"><span class="material-icons p-2 "
                                style="font-size: 3rem;">person</span>Policy Login </h1>
                        <div class="form-group input-group px-5">
                            <div class="input-group-text input-group-append my-4 "><span class="material-icons">
                                    account_circle
                                </span></div>
                            <input type="text" name="userName" path="userName" class="form-control my-4 p-4"
                                placeholder="User name">
                        </div>
                        <sf:errors style="color:red" path="userName"></sf:errors>

                        <div class="form-group input-group px-5">
                            <div class="input-group-text input-group-append">
                                <span class="material-icons">
                                    password
                                </span>
                            </div>
                            <input type="password" name="userPassword" path="userPassword" class="form-control p-4"
                                placeholder="Enter the Password">
                        </div>
                        <sf:errors style="color:red" path="userPassword"></sf:errors>

                        <div class="form-group ml-5">
                            <button type="submit" class="btn btn-success ">Submit</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Integrated bootstrap js  -->
            <script src="js/jquery-3.6.0.js"></script>
            <script src="js/bootstrap.js"></script>
            <script src="js/popper.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
                integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
                crossorigin="anonymous"></script>
        </body>

        </html>