<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.CustomerDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Page</title>
    <link rel="stylesheet" href="assets/css/bootstrap.css">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary shadow p-3 mb-2 bg-body-tertiary rounded">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img alt="Logo" class="d-inline-block align-text-center" height="44" src="assets/images/cart.gif"
                     width="50">POS System</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="item">Items</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="placeOrder.jsp">Orders</a>
                    </li>
                </ul>
                <div class="d-grid gap-3 d-md-flex justify-content-md-end">
                    <button type="button" class="btn btn-outline-primary">Sign Up</button>
                    <button type="button" class="btn btn-outline-danger">Log Out</button>
                </div>
            </div>
        </div>
    </nav>
</header>

<!--Customer Page-->
<main class="container" id="customerPage">

    <!--Customer Form and Buttons-->
    <section class="row mt-4 d-flex justify-content-between">

        <!--Customer Form-->
        <div class="col-6">

            <h2 class="fw-bold text-start mb-3">Customer Form</h2>

            <form id="customerForm">
                <div class="mb-3">
                    <label for="customerId" class="form-label">Customer ID</label>
                    <input type="text" class="form-control" id="customerId" name="cusId">
                    <small class="control-error text-danger" id="lblCusId"></small>
                </div>
                <div class="mb-3">
                    <label for="customerName" class="form-label">Customer Name</label>
                    <input type="text" class="form-control" id="customerName" name="cusName">
                    <small class="control-error text-danger" id="lblCusName"></small>
                </div>
                <div class="mb-3">
                    <label for="addressCus" class="form-label">Address</label>
                    <input type="text" class="form-control" id="addressCus" name="cusAddress">
                    <small class="control-error text-danger" id="lblCusAddress"></small>
                </div>
                <div class="mb-3">
                    <label for="tpNo" class="form-label">Tele No</label>
                    <input type="number" class="form-control" id="tpNo" name="cusTp">
                    <small class="control-error text-danger" id="lblTp"></small>
                </div>
            </form>

        </div>

        <!--Buttons-->
        <div class="col-4 mt-5">

            <%--refactored actions with ajax--%>

            <%--<button id="btnSaveCustomer" formaction="customer?option=ADD" formmethod="post" form="customerForm"
                    class="btn btn-outline-primary btn-lg d-grid  col-8 mb-3 ">Save Customer
            </button>--%>

            <button id="btnSaveCustomer" class="btn btn-outline-primary btn-lg d-grid  col-8 mb-3" type="button">
                Save Customer
            </button>

            <button id="btnDeleteCustomer" formaction="customer?option=DELETE" formmethod="post" form="customerForm"
                    class="btn btn-outline-success btn-lg d-grid col-8 mb-3">Delete Customer
            </button>
            <%-- <button id="btnUpdateCustomer" formaction="customer?option=UPDATE" formmethod="post" form="customerForm"
                     class="btn btn-outline-danger btn-lg d-grid  col-8 mb-3">Update Customer
             </button>--%>

            <button id="btnUpdateCustomer" type="button" class="btn btn-outline-danger btn-lg d-grid  col-8 mb-3">
                Update Customer
            </button>
            <button id="btnGetAllCustomers" formaction="customer" formmethod="get" form="customerForm"
                    class="btn btn-outline-warning btn-lg d-grid  col-8 mb-3">Get All
            </button>
        </div>

    </section>

    <!--Customer Table-->
    <section class="row container-fluid">
        <table class="table table-bordered table-hover">
            <thead class="bg-info text-black text-center">
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Customer Address</th>
                <th>Contact</th>
            </tr>
            </thead>

            <tbody id="tblCustomer">

            <%
                ArrayList<CustomerDTO> customers = (ArrayList<CustomerDTO>) request.getAttribute("keyOne");

                if (customers != null) {
                    for (CustomerDTO customer : customers) {
            %>

            <tr>
                <td><%=customer.getId()%>
                </td>
                <td><%=customer.getName()%>
                </td>
                <td><%=customer.getAddress()%>
                </td>
                <td><%=customer.getTp()%>
                </td>
            </tr>

            <%
                    }
                }
            %>
            </tbody>
        </table>
    </section>


</main>

<script src="assets/jquery-3.7.0.min.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="controller/validation/customerValidation.js"></script>
<script>
    /*click table row and get values from it*/
    function bindEventToCustomer() {
        /*get customer detail from table*/
        $("#tblCustomer>tr").click(function () {

            let col1 = $(this).children().eq(0).text();
            let col2 = $(this).children().eq(1).text();
            let col3 = $(this).children().eq(2).text();
            let col4 = $(this).children().eq(3).text();

            /*set values to input fields*/
            $("#customerId").val(col1.trim());
            $("#customerName").val(col2);
            $("#addressCus").val(col3);
            $("#tpNo").val(col4.trim());

            console.log(col1, col2, col3, col4);

        });
    }

    bindEventToCustomer();

    /*add customer with ajax*/
    $("#btnSaveCustomer").click(function () {

        /*get input values from frontend*/
        let formData = $("#customerForm").serialize();
        $.ajax({
            url: "customer?option=ADD",
            method: "post",
            data: formData,
            success: function (res) {
                console.log("customer saved successfully!")
            }
        });
    });

    /*update customer with ajax*/
    $("#btnUpdateCustomer").click(function () {

        /*get input values from frontend*/
        let formData = $("#customerForm").serialize();
        $.ajax({
            url: "customer?option=UPDATE",
            method: "post",
            data: formData,
            success: function (res) {
                console.log("customer updated successfully!")
            }
        });
    });

</script>

</body>
</html>