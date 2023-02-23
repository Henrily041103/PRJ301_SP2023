<%-- 
    Document   : viewCart
    Created on : Feb 21, 2023, 9:35:14 AM
    Author     : Thanh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.product.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <style>
            th, td, td input{
                text-align: center;
            }
            #total{
                font-weight: bold;
                font-size: 2em;
            }
        </style>
    </head>
    <body>
        <h1>Enjoy your Time</h1>
        <%
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Change</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (ProductDTO pro : cart.getCart().values()) {
                        total += pro.getQuantity() * pro.getPrice();
                %>
            <form action="MainController" method="POST">
                <tr>
                    <td><%=count++%></td>
                    <td>
                        <input type ="text" name="id" value="<%= pro.getProductID()%>" readonly=""/>

                    </td>
                    <td><%=pro.getName()%></td>
                    <td><%=pro.getPrice()%></td>
                    <td>
                        <input type="number" name="quantity" value="<%=pro.getQuantity()%>"  min="1" required=""/>
                    </td>
                    <td><%=pro.getQuantity()*pro.getPrice()%></td>
                    <td>
                        <input type="submit" name="action" value="Change" />
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove" />
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
            <a id="total">Total <%=total%></a>
            <input id="checkout" type="submit" name="action" value="CheckOut"/> 

    <%
        }
    %>
    <br>
    <a href="shopping.jsp">Add more</a>   
</body>
</html>
