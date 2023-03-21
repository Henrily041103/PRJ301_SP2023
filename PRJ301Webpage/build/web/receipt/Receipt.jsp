<%-- 
    Document   : test
    Created on : Feb 28, 2023, 9:18:39 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receipt</title>
        <link href="../../css/site.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    </head>    
    <body>
        <button class="btn btn-primary" name="action" value="back"><i class="bi bi-bag"></i>Home</button>
        <h2 style = "text-align: center; color:red; font-weight:bold">Shop Name Here</h2>
        <h3 style = "text-align: center; color:blue; font-weight:bold">---Receipt---</h3>
            <table class="table table-striped">
                <tr>
                    <th>No.</th>
                    <th>Product</th>
                    <th>Amount</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="entry" items="${pageScope.cart}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${entry.key.brandId}, ${entry.key.type}</td>
                        <td>${entry.value}</td>
                        <td>${entry.key.amount*((1-entry.key.sale)*0.01)}</td>
                    </tr>
                </c:forEach>
            </table>
            <p>Total: ${total}</p>
    </body>
</html>

