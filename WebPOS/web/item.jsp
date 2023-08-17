<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.ItemDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Item Page</title>
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
                        <a class="nav-link" href="customer">Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Items</a>
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

<!--Item page-->
<main class="container" id="itemPage">

    <!--Item Form and Buttons-->
    <section class="row mt-4 d-flex justify-content-between">

        <!--Item Form-->
        <div class="col-6">

            <h2 class="fw-bold text-start mb-3">Item Form</h2>

            <form id="itemForm">
                <div class="mb-3">
                    <label for="itemCode1" class="form-label">Item Code</label>
                    <input type="text" class="form-control" id="itemCode1" name="code">
                    <small class="control-error text-danger" id="lblItemCode"></small>
                </div>
                <div class="mb-3">
                    <label for="itemName1" class="form-label">Item Name</label>
                    <input type="text" class="form-control" id="itemName1" name="itemName">
                    <small class="control-error text-danger" id="lblItemName"></small>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Unit Price</label>
                    <input type="text" class="form-control" id="price" name="price">
                    <small class="control-error text-danger" id="lblItemPrice"></small>
                </div>
                <div class="mb-3">
                    <label for="qty" class="form-label">Qty On Hand</label>
                    <input type="number" class="form-control" id="qty" min="0" name="qty">
                    <small class="control-error text-danger" id="lblItemQty"></small>
                </div>
            </form>

        </div>

        <!--Buttons-->
        <div class="col-4 mt-5">
            <%--<button id="btnSaveItem" form="itemForm" formmethod="post" formaction="item?option=ADD"
                    class="btn btn-outline-primary btn-lg d-grid  col-8 mb-3 ">Save Item
            </button>--%>

            <button id="btnSaveItem" class="btn btn-outline-primary btn-lg d-grid  col-8 mb-3 ">Save Item</button>

           <%-- <button id="btnDeleteItem" form="itemForm" formmethod="post" formaction="item?option=DELETE"
                    class="btn btn-outline-success btn-lg d-grid col-8 mb-3">Delete Item
            </button>--%>

                <button id="btnDeleteItem"
                        class="btn btn-outline-success btn-lg d-grid col-8 mb-3">Delete Item
                </button>
      <%--      <button id="btnUpdateItem" form="itemForm" formmethod="post" formaction="item?option=UPDATE"
                    class="btn btn-outline-danger btn-lg d-grid  col-8 mb-3">Update Item
            </button>--%>

                <button id="btnUpdateItem"
                        class="btn btn-outline-danger btn-lg d-grid  col-8 mb-3">Update Item
                </button>
            <button id="btnGetAllItems" form="itemForm" formmethod="get" formaction="item"
                    class="btn btn-outline-warning btn-lg d-grid  col-8 mb-3">Get All
            </button>
        </div>

    </section>

    <!--Item Table-->
    <section class="row container-fluid">
        <table class="table table-bordered table-hover">
            <thead class="bg-danger text-black text-center">
            <tr>
                <th>Item Code</th>
                <th>Item Name</th>
                <th>Unit Price</th>
                <th>Qty On Hand</th>
            </tr>
            </thead>

            <tbody id="tblItem">

            <%
                ArrayList<ItemDTO> items = (ArrayList<ItemDTO>) request.getAttribute("keyTwo");

                if (items != null) {

                    for (ItemDTO item : items) {
            %>
            <tr>
                <td><%=item.getCode()%>
                </td>
                <td><%=item.getName()%>
                </td>
                <td><%=item.getPrice()%>
                </td>
                <td><%=item.getQty()%>
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
<script src="controller/validation/itemValidation.js"></script>
<script>
    function bindEventToItem() {

        $("#tblItem>tr").click(function () {

            let col1 = $(this).children().eq(0).text();
            let col2 = $(this).children().eq(1).text();
            let col3 = $(this).children().eq(2).text();
            let col4 = $(this).children().eq(3).text();

            /*set values to input fields*/
            $("#itemCode1").val(col1.trim());
            $("#itemName1").val(col2.trim());
            $("#price").val(col3.trim());
            $("#qty").val(col4.trim());

            console.log(col1, col2, col3, col4)

        });
    }

    bindEventToItem();

    /*add item*/
    $("#btnSaveItem").click(function () {

        let formData = $("#itemForm").serialize();
        $.ajax({
            url: "item?option=ADD",
            method: "post",
            data: formData,
            success: function (resp) {
                console.log(resp);
            }
        });
    });

    /*delete item*/
    $("#btnDeleteItem").click(function () {

        let formData = $("#itemForm").serialize();
        $.ajax({
            url: "item?option=DELETE",
            method: "post",
            data: formData,
            success: function (resp) {
                console.log(resp);
            }
        });
    });

    /*update item*/
    $("#btnUpdateItem").click(function () {

        let formData = $("#itemForm").serialize();
        $.ajax({
            url: "item?option=UPDATE",
            method: "post",
            data: formData,
            success: function (resp) {
                console.log(resp);
            }
        });
    });


</script>


</body>
</html>