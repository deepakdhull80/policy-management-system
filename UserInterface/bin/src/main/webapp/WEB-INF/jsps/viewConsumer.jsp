<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <meta name="description" content="" />
            <meta name="author" content="" />
            <title>Policy Administration System</title>

            <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

            <!-- Integrated bootstrap css style  -->
            <link rel="stylesheet" href="css/bootstrap.css">
            <!-- Integrating font awesome icons  -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
                integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
                crossorigin="anonymous" referrerpolicy="no-referrer" />
            <!-- Integrating google material icons  -->
            <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
            <link href="css/styles.css" rel="stylesheet" />

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
                    <div class="sidebar-heading border-bottom bg-light">Policy Administration</div>
                    <div class="list-group list-group-flush">
                        <button type="button" class="list-group-item list-group-item-action list-group-item-light p-3"
                            data-toggle="modal" data-target="#createBP">
                            Create Business & Property
                        </button>

                        <a href="consumerDetails"
                            class="list-group-item list-group-item-action list-group-item-light p-3">
                            Edit Business & Property
                        </a>

                        <button type="button" class="list-group-item list-group-item-action list-group-item-light p-3"
                            data-toggle="modal" data-target="#createPolicy">
                            Create Policy
                        </button>

                        <button type="button" class="list-group-item list-group-item-action list-group-item-light p-3"
                            data-toggle="modal" data-target="#issuePolicy">
                            Issue Policy
                        </button>

                        <a href="/policyDetails" class="list-group-item list-group-item-action list-group-item-light p-3">
                            View Policy
                        </a>
                    </div>
                </div>
                <!-- Page content wrapper-->
                <div id="page-content-wrapper">
                    <!-- Top navigation-->
                    <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                        <div class="container-fluid">
                            <button class="btn btn-primary" id="sidebarToggle">Menu</button>
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation"><span
                                    class="navbar-toggler-icon"></span></button>
                            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                                <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                    <li class="nav-item active"><a class="nav-link" href="login">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>

                    <!-- Page content-->
                    <div class="container-fluid">

                        <div class="display-4 m-4 text-center">Consumer Details </div>
                        <hr>
                        <div class="table-responsive ">
                            <table class="table table-hover w-50 ">
                                <tr>
                                    <th class="text-center">CONSUMER</th> 
                                </tr>
                                <tr>
                                    <th>ID</th>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <th>Name</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Date Of Birth</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>PAN Number</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Email</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Phone Number</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Agent Name</th>
                                    <td></td>
                                </tr>

                                <tr>
                                    <th class="text-center">BUSINESS</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Business Category</th>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <th>Business Type</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Business Turnover</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Capital Investment</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Total Employee</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Business Age</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th class="text-center">PROPERTY</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Property Type</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Building Square Feet</th>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <th>Building Type</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Building Storeys</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Building Age</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Cost Of The Assets</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Usefull Life Of The Assets</th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th>Salvage Value</th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>

                        <!-- Modals -->
                        <!-- Create Business and Property -->
                        <div class="modal fade" id="createBP" tabindex="-1" role="dialog"
                            aria-labelledby="createBPLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="createBPLabel">Create Business And Property</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="#" modelAttribute="createBP" method="POST">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="consumerName">Consumer Name</label>
                                                <input type="text" name="consumerName" path="consumerName"
                                                    class="form-control" placeholder="Consumer Name">
                                            </div>
                                            <sf:errors style="color:red" path="consumerName"></sf:errors>

                                            <div class="form-group">
                                                <label for="dateOfBirth">Date Of Birth</label>
                                                <input type="date" name="dateOfBirth" path="dateOfBirth"
                                                    class="form-control" placeholder="Date Of Birth">
                                            </div>
                                            <sf:errors style="color:red" path="dateOfBirth"></sf:errors>

                                            <div class="form-group">
                                                <label for="panDetails">Pan Details</label>
                                                <input type="text" name="panDetails" path="panDetails"
                                                    class="form-control" placeholder="XXXXXXX">
                                            </div>
                                            <sf:errors style="color:red" path="panDetails"></sf:errors>

                                            <div class="form-group">
                                                <label for="email">Email</label>
                                                <input type="email" name="email" path="email" class="form-control"
                                                    placeholder="Email Address">
                                            </div>
                                            <sf:errors style="color:red" path="email"></sf:errors>

                                            <div class="form-group">
                                                <label for="agentName">Agent Name</label>
                                                <input type="text" name="agentName" path="agentName"
                                                    class="form-control" placeholder="Agent Name">
                                            </div>
                                            <sf:errors style="color:red" path="agentName"></sf:errors>

                                            <div class="form-group">
                                                <label for="agentId">Agent Id</label>
                                                <input type="number" name="agentId" path="agentId" min="0"
                                                    class="form-control" placeholder="Agent Id">
                                            </div>
                                            <sf:errors style="color:red" path="agentId"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessCategory">Business Category</label>
                                                <input type="text" name="businessCategory" path="businessCategory"
                                                    class="form-control" placeholder="Business Category">
                                            </div>
                                            <sf:errors style="color:red" path="businessCategory"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessType">Business Type</label>
                                                <input type="text" name="businessType" path="businessType"
                                                    class="form-control" placeholder="Business Type">
                                            </div>
                                            <sf:errors style="color:red" path="businessType"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessTurnover">Business Turnover</label>
                                                <input type="number" min="0" name="businessTurnover"
                                                    path="businessTurnover" class="form-control"
                                                    placeholder="Business Turnover">
                                            </div>
                                            <sf:errors style="color:red" path="businessTurnover"></sf:errors>

                                            <div class="form-group">
                                                <label for="capitalInvested">Capital Invested</label>
                                                <input type="number" min="0" name="capitalInvested"
                                                    path="capitalInvested" class="form-control"
                                                    placeholder="Capital Invested">
                                            </div>
                                            <sf:errors style="color:red" path="capitalInvested"></sf:errors>

                                            <div class="form-group">
                                                <label for="totalEmployee">Total Employee</label>
                                                <input type="number" min="0" name="totalEmployee" path="totalEmployee"
                                                    class="form-control" placeholder="Total Employee">
                                            </div>
                                            <sf:errors style="color:red" path="totalEmployee"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessAge">Business Age</label>
                                                <input type="number" min="0" name="businessAge" path="businessAge"
                                                    class="form-control" placeholder="Business Age">
                                            </div>
                                            <sf:errors style="color:red" path="businessAge"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessType">Property Type</label>
                                                <input type="text" name="propertyType" path="propertyType"
                                                    class="form-control" placeholder="Property Type">
                                            </div>
                                            <sf:errors style="color:red" path="propertyType"></sf:errors>

                                            <div class="form-group">
                                                <label for="buildingsqft">Building Square Feet</label>
                                                <input type="text" name="buildingsqft" path="buildingsqft"
                                                    class="form-control" placeholder="Building Square Feet">
                                            </div>
                                            <sf:errors style="color:red" path="buildingsqft"></sf:errors>

                                            <div class="form-group">
                                                <label for="buildingType">Building Type</label>
                                                <input type="text" name="buildingType" path="buildingType"
                                                    class="form-control" placeholder="Building Type">
                                            </div>
                                            <sf:errors style="color:red" path="buildingType"></sf:errors>

                                            <div class="form-group">
                                                <label for="buildingStoreys">Building Storeys</label>
                                                <input type="text" name="buildingStoreys" path="buildingStoreys"
                                                    class="form-control" placeholder="Building Storeys">
                                            </div>
                                            <sf:errors style="color:red" path="buildingStoreys"></sf:errors>

                                            <div class="form-group">
                                                <label for="buildingAge">Building Age</label>
                                                <input type="number" min="0" name="buildingAge" path="buildingAge"
                                                    class="form-control" placeholder="Building Age">
                                            </div>
                                            <sf:errors style="color:red" path="buildingAge"></sf:errors>

                                            <div class="form-group">
                                                <label for="costoftheasset">Cost Of The Assets</label>
                                                <input type="number" min="0" name="costoftheasset" path="costoftheasset"
                                                    class="form-control" placeholder="Cost Of The Assets">
                                            </div>
                                            <sf:errors style="color:red" path="costoftheasset"></sf:errors>

                                            <div class="form-group">
                                                <label for="usefullLifeoftheasset">Usefull Life Of The Assets</label>
                                                <input type="number" min="0" name="usefullLifeoftheasset"
                                                    path="usefullLifeoftheasset" class="form-control"
                                                    placeholder="Useful Life Of The Assets">
                                            </div>
                                            <sf:errors style="color:red" path="usefullLifeoftheasset"></sf:errors>

                                            <div class="form-group">
                                                <label for="salvageValue">Salvage Value</label>
                                                <input type="number" min="0" name="salvageValue" path="salvageValue"
                                                    class="form-control" placeholder="Salvage Value">
                                            </div>
                                            <sf:errors style="color:red" path="salvageValue"></sf:errors>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Save Policy</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- Create Business and Property Ends-->

                        <!-- Create Policy Modal -->
                        <div class="modal fade" id="createPolicy" tabindex="-1" role="dialog"
                            aria-labelledby="createPolicyLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="createPolicyLabel">Create Policy</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="/createPolicy" modelAttribute="createPolicy" method="POST">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="propertyType">Property Type</label>
                                                <input type="text" name="propertyType" path="propertyType"
                                                    class="form-control" placeholder="Property Type">
                                            </div>
                                            <sf:errors style="color:red" path="propertyType"></sf:errors>

                                            <div class="form-group">
                                                <label for="propertyType">Property Type</label>
                                                <input type="text" name="propertyType" path="propertyType"
                                                    class="form-control" placeholder="Property Type">
                                            </div>
                                            <sf:errors style="color:red" path="propertyType"></sf:errors>

                                            <div class="form-group">
                                                <label for="consumerType">Consumer Type</label>
                                                <input type="text" name="consumerType" path="consumerType"
                                                    class="form-control" placeholder="Consumer Type">
                                            </div>
                                            <sf:errors style="color:red" path="consumerType"></sf:errors>

                                            <div class="form-group">
                                                <label for="assuredSum">Assured Sum</label>
                                                <input type="text" name="assuredSum" path="assuredSum"
                                                    class="form-control" placeholder="Assured Sum">
                                            </div>
                                            <sf:errors style="color:red" path="assuredSum"></sf:errors>

                                            <div class="form-group">
                                                <label for="tenure">Tenure</label>
                                                <input type="text" name="tenure" path="tenure" class="form-control"
                                                    placeholder="Tenure">
                                            </div>
                                            <sf:errors style="color:red" path="tenure"></sf:errors>

                                            <div class="form-group">
                                                <label for="businessValue"> Business Value</label>
                                                <input type="number" name="businessValue" path="businessValue" min="0"
                                                    class="form-control" placeholder="Business Value">
                                            </div>
                                            <sf:errors style="color:red" path="businessValue"></sf:errors>

                                            <div class="form-group">
                                                <label for="propertyValue"> Property Value</label>
                                                <input type="number" name="propertyValue" path="propertyValue" min="0"
                                                    class="form-control" placeholder="Property Value">
                                            </div>
                                            <sf:errors style="color:red" path="propertyValue"></sf:errors>

                                            <div class="form-group">
                                                <label for="baseLocation">Base Location</label>
                                                <input type="text" name="baseLocation" path="baseLocation"
                                                    class="form-control" placeholder="Base Location">
                                            </div>
                                            <sf:errors style="color:red" path="baseLocation"></sf:errors>

                                            <div class="form-group">
                                                <label for="type">Type</label>
                                                <input type="text" name="type" path="type" class="form-control"
                                                    placeholder="Type">
                                            </div>
                                            <sf:errors style="color:red" path="type"></sf:errors>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Save Policy</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- create policy ends -->

                        <!-- Issue policy Modal -->
                        <div class="modal fade" id="issuePolicy" tabindex="-1" role="dialog"
                            aria-labelledby="issuePolicyLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="issuePolicyLabel">Issue Policy</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <form action="#" modelAttribute="issuePolicy" method="POST">
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <label for="consumerId">Consumer Id</label>
                                                <input type="number" name="consumerId" path="consumerId" min="0"
                                                    class="form-control" placeholder="Consumer Id">
                                            </div>
                                            <sf:errors style="color:red" path="consumerId"></sf:errors>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Save Policy</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- create policy ends -->

                    </div>
                </div>
            </div>
            <!-- Bootstrap core JS-->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
            <!-- Core theme JS-->
            <script src="js/scripts.js"></script>
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