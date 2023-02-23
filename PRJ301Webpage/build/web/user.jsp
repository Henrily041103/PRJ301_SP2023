<%-- 
Document   : user
Created on : Feb 21, 2023, 8:30:23 AM
Author     : Thanh
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;

            }
            #logout {
                display: inline-block;
                font-weight: bold;
                background-color: aqua;
                color: black;
                margin: 5px 0;
                border: 1px solid black;
                border-radius: 3px; 
            }
            #inbody{
                display: inline-block;

            }
            #body{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100vw;
                height: 90vh;
            }
            #profile{
                color: blue;
                font-weight: bold;
            }
            #user{
                display: inline-block;
            }
            th, td, td input{
                text-align: center;
            }
            #showall{
                display: inline-block;
                background-color: blue;
                font-weight: bold;
                color: white;
            }
        </style>

    </head>
    <body>
        <div id="body">
            <div id="inbody">
                <h1 id="user">Welcome ${sessionScope.LOGIN_USER.name}</h1>                    
                <c:url var = "logout" value = "MainController">
                    <c:param name = "action" value = "Logout"></c:param>
                </c:url>
                <a id="logout" href = ${logout}>Logout</a>
                <br>
                <br>
                <div id = "profile">Profile</div>
                User ID: ${sessionScope.LOGIN_USER.userID}</br>
                User Name: ${sessionScope.LOGIN_USER.name}</br>
                Address: ${sessionScope.LOGIN_USER.address}</br>
                Phone: ${sessionScope.LOGIN_USER.phone}</br>
                <br>
                <form action="MainController" >
                    <input id="showall" type="submit" name="action" value="ShowAll"/> 
                    <br>
                </form>
                <c:if test = "${requestScope.SHOW_ALL != null}">
                    <c:if test = "${not empty requestScope.SHOW_ALL}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody>                                  
                                <c:forEach var = "product" varStatus = "counter" items = "${requestScope.SHOW_ALL}">
                                <form action ="MainController" method ="POST">  
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>
                                            <input type="text" name="productID" value="${product.productID}" readonly=""/>
                                        </td>
                                        <td>
                                            <input type="text" name="name" value="${product.name}" readonly=""/>
                                        </td>
                                        <td>
                                            <input type="number" name="price" value="${product.price}" min="0" readonly=""/>
                                        </td>
                                        <td>
                                            <input type="number" name="quantity" value="${product.quantity}" min="0" readonly=""/>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                        <h1>${requestScope.ERROR}</h1>
                    </c:if>
                </c:if>
                <br>
                <a href="shopping.jsp">Shopping</a>
            </div>
        </div>
    </body>
</html>
