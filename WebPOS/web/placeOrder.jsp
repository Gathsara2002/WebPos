<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Form</title>
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
                        <a class="nav-link" href="index.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="customer.html">Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="item.html">Items</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Order</a>
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

<!--Order page-->
<main class="container-fluid p-3" id="orderPage">

    <!--Order details-->
    <section class="row bg-secondary-subtle p-4 justify-content-evenly">

        <!--invoice detail-->
        <div class="col-4">
            <h3 class="fw-bold text-white text-center bg-info p-2 rounded-3"> Invoice Details</h3>
            <div class="card">
                <div class="card-body">
                    <form>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label>Order ID :</label>
                                <input class="form-control" id="orderId" type="text">
                            </div>
                            <div class="col-6">
                                <label>Date :</label>
                                <input class="form-control" id="date" type="date">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label>Customer :</label>
                                <select class="form-select" id="cusId">
                                    <option selected>- Name -</option>
                                </select>
                            </div>
                            <div class="col-6">
                                <div class="form-group">
                                    <label>Customer ID :</label>
                                    <input class="form-control" id="cId" type="text">
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label>Name :</label>
                                <input class="form-control" id="cusName" type="text">
                            </div>
                            <div class="col-6">
                                <label>Address :</label>
                                <input class="form-control" id="address" type="text">
                            </div>
                            <div class="col-12 mt-3">
                                <label>Contact :</label>
                                <input class="form-control" id="cusContact" type="text">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!--Item details-->
        <div class="col-4">
            <h3 class="fw-bold text-white text-center bg-info p-2 rounded-3"> Item Details</h3>
            <div class="card">
                <div class="card-body">
                    <div class="row mb-3">
                        <div class="col-6">
                            <label for="itemCode">Item Code</label>
                            <select class="form-select" aria-label="Default select example" id="itemCode">
                                <option selected>-Item Code-</option>
                            </select>
                        </div>
                        <div class="col-6">
                            <form>
                                <label for="itemName" class="form-label">Item Name</label>
                                <input type="text" class="form-control" id="itemName">
                            </form>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <form>
                                <label for="unitPrice" class="form-label">Unit Price</label>
                                <input type="text" class="form-control" id="unitPrice">
                            </form>
                        </div>
                        <div class="col-6">
                            <form>
                                <label for="qtyOnHand" class="form-label">Qty On</label>
                                <input type="text" class="form-control" id="qtyOnHand">
                            </form>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <form>
                                <label for="buyQrt" class="form-label">Order Qty</label>
                                <input type="text" class="form-control" id="buyQrt">
                            </form>
                        </div>
                        <div class="col-6 mt-auto">
                            <button id="btnCart" type="button" class="btn btn-danger">Add To Cart</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Purchase Detail-->
        <div class="col-3">
            <h3 class="fw-bold text-white text-center bg-info p-2 rounded-3"> Payment Details</h3>
            <div class="card">
                <div class="card-body">

                    <div class="row mb-3">
                        <div class="col-6">
                            <form>
                                <label for="total" class="form-label">Total</label>
                                <input type="text" class="form-control" id="total">
                            </form>
                        </div>
                        <div class="col-6">
                            <form>
                                <label for="subTotal" class="form-label">Sub Total</label>
                                <input type="text" class="form-control" id="subTotal">
                            </form>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <form>
                                <label for="discount" class="form-label">Discount</label>
                                <input type="number" class="form-control" id="discount" min="0" value="0">
                            </form>
                        </div>
                        <div class="col-6">
                            <form>
                                <label for="cash" class="form-label">Cash</label>
                                <input type="text" class="form-control" id="cash">
                            </form>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-6">
                            <form>
                                <label for="balance" class="form-label">Balance</label>
                                <input type="text" class="form-control" id="balance">
                            </form>
                        </div>
                        <div class="col-6">
                            <button id="btnPurchase" type="button" class="btn btn-danger mt-4">Purchase</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>

    <!--detail table-->
    <section class="row  bg-secondary-subtle p-4 justify-content-around ">
        <div class="col-10">
            <table class="table table-bordered text-white text-center">
                <thead class="bg-primary">
                <tr>
                    <th>Item Code</th>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody class="bg-light text-dark" id="orderTable">

                </tbody>
            </table>
        </div>
    </section>

</main>

<script src="assets/jquery-3.7.0.min.js"></script>
<script src="assets/js/bootstrap.js"></script>
<script src="db/db.js"></script>
<script src="model/customerModel.js"></script>
<script src="controller/customerController.js"></script>
<script src="controller/validation/customerValidation.js"></script>
<script src="controller/validation/itemValidation.js"></script>
<script src="controller/itemController.js"></script>
<script src="model/itemModel.js"></script>
<script src="controller/placeOrderController.js"></script>
<script src="model/placeOrderModel.js"></script>

</body>
</html>