<%-- 
    Document   : shopping
    Created on : Feb 21, 2023, 9:10:20 AM
    Author     : Thanh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <style>
            #z{
                font-size: 2em;
            }
            th, td, td input{
                text-align: center;
            }
            #0{
                display: inline-block;
                font-weight: bold;               
            }
        </style>
    </head>
    <body>
        <h1>Welcome</h1>
        <h1 id="z">What would you like to buy?</h1>
        <form action="MainController" method="POST">
            Search Product<input type="search" name="viewpro" value="${param.viewpro}"/>
            <input id="viewpro" type="submit" name="action" value="ViewPro" /> <br>  
            <c:if test = "${empty requestScope.LIST_USER}">
                        <a id="0"> ${EMPTY_LIST} </a>
                    </c:if>
            <c:if test = "${requestScope.VIEW_PRODUCT != null}">
                <c:if test = "${not empty requestScope.VIEW_PRODUCT}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>Product ID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Add</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var = "product" varStatus = "counter" items = "${requestScope.VIEW_PRODUCT}">
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
                                        <select name ="quantity">
                                            <option value ="1">1</option>
                                            <option value ="2">2</option>
                                            <option value ="3">3</option>
                                            <option value ="4">4</option>
                                            <option value ="5">5</option>
                                            <option value ="10">10</option>
                                            <option value ="20">20</option>
                                            <option value ="50">50</option>
                                        </select>    
                                    </td>
                                    <td>
                                        <input type ="submit" name = "action" value ="Add"/>
                                        <input type ="hidden" name = "productID" value ="${product.productID}"/>
                                        <input type ="hidden" name = "searchpro" value ="${param.searchpro}"/>
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
            <input type ="submit" name="action" value ="ViewCart"/>

            <h1>${requestScope.message}</h1>
            <c:url var = "logout" value = "MainController">
                <c:param name = "action" value = "Logout"></c:param>
            </c:url>
            <a id="logout" href = ${logout}>Logout</a>
    </body>
</html>
