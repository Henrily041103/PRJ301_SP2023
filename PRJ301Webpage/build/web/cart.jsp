<<<<<<< HEAD
<%-- 
    Document   : cart
    Created on : Feb 22, 2023, 12:39:40 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link href="../../css/site.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <button class="btn btn-primary" name="action" value="back"><i class="bi bi-bag"></i>Home</button>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Amount</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="entry" items="${pageScope.cartDis}">
                                            <c:forEach var="entry" items="${pageScope.cartDis}">
                                                <tr>
                                                    <td scope="row">
                                                    <td scope="row">
                                                        <div class="p-2">
                                                            <img src="<c:url value="/WEB-INF/productPics/${image}"/>" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <img src="<c:url value="/WEB-INF/productPics/${image}"/>" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${entry.key.brandId} ${entry.key.type}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${entry.key.brandId} ${entry.key.type}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td class="align-middle">
                                                        <h5>${entry.key.sale}</h5>
                                                        <h6  style = "text-decoration: line-through">${entry.key.amount}</h6>
                                                    </td>
                                                    <td class="align-middle">
                                                        <strong>${entry.key.amount*((1-entry.key.sale)*0.01)}</strong>
                                                    </td>
                                                    <td class="align-middle"><a href="#" class="text-dark">    
                                                            <strong>${entry.value}</strong>
                                                            <button type="submit" class="btn btn-warning" name="op" value="remove"><i class="bi bi-x-lg"></i>Remove</button>
                                                        </a>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold"></div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                            <h5 class="font-weight-bold">${total}</h5>
                                        </li>
                                    </ul>
                                    <button type="submit" class="btn btn-success" name="op" value="buy"><i class="bi bi-cart-check-fill">Buy</button>                                                                                                                                                                                                                                                        
                                    <button type="submit" class="btn btn-danger" name="op" value="remove"><i class="bi bi-cart-check-fill"></i>Empty</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>

</html>
</html>
