<%-- 
    Document   : admin
    Created on : Feb 21, 2023, 8:05:29 AM
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
        <title>Admin Page</title>
        <style>
            #admin{
                display: inline-block;
                font-size: 1em; 
            }
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
            th, td, td input{
                text-align: center;
            }
            #well{
                font-weight: bold;
            }        
            #body{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100vw;
                height: 90vh;
            }
            #inbody{
                display: inline-block;
            }      
            #0{
                display: inline-block;
                font-weight: bold;               
            }
        </style>
    </head>
    <body>
        <div id = "body">
            <div id = "inbody">
                <a id="well"> Welcome:</a> <h1 id="admin">${sessionScope.LOGIN_USER.userID}</h1>  
                <c:url var = "logout" value = "MainController">
                    <c:param name = "action" value = "Logout"></c:param>
                </c:url>
                <a id="logout" href = ${logout}>Logout</a>
                <br>
                <form action="MainController" method="POST">
                    Search<input type="search" name="search" value="${param.search}"/>
                    <input type="submit" name="action" value="Search"/>
                    <c:if test = "${empty requestScope.LIST_USER}">
                        <a id="0"> ${EMPTY_LIST} </a>
                    </c:if>
                </form>
                <c:if test = "${requestScope.LIST_USER != null}">
                    <c:if test = "${not empty requestScope.LIST_USER}">
                        <br>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>User ID</th>
                                    <th>Name</th>
                                    <th>Role ID</th>
                                    <th>Password</th>
                                    <th>Address</th>
                                    <th>Phone</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var = "user" varStatus = "counter" items = "${requestScope.LIST_USER}">
                                <form action ="MainController" method ="POST">  
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${user.userID}</td>
                                        <td>
                                            <input type = "text" name = "name" value ="${user.name}"/>
                                        </td>
                                        <td>
                                            <input type = "text" name = "roleID" value ="${user.roleID}"/>
                                        </td>
                                        <td>${user.password}</td>
                                        <td>
                                            <input type ="text" name = "address" value ="${user.address}"/>
                                        </td>
                                        <td>
                                            <input type ="text" name = "phone" value ="${user.phone}"/>
                                        </td>
                                        <td>
                                            <input type ="submit" name = "action" value ="Update"/>
                                            <input type ="hidden" name = "userID" value ="${user.userID}"/>
                                            <input type ="hidden" name = "search" value ="${param.search}"/>
                                        </td>
                                        <td>
                                            <c:url var = "Delete" value = "MainController">
                                                <c:param name = "action" value = "Delete"></c:param>
                                                <c:param name = "userID" value = "${user.userID}"></c:param>
                                                <c:param name = "search" value = "${param.search}"></c:param>
                                            </c:url>
                                            <a href ="${Delete}">Delete</a>
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

                <form action="MainController" >
                    Search Product<input type="search" name="searchpro" value="${param.searchpro}"/>
                    <input type="submit" name="action" value="SearchPro"/>
                    <c:if test = "${empty requestScope.LIST_PRODUCT}">
                        <a id="0"> ${EMPTY_LISTPRO} </a>
                    </c:if>
                </form>    
                <c:if test = "${requestScope.LIST_PRODUCT != null}">
                    <c:if test = "${not empty requestScope.LIST_PRODUCT}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var = "product" varStatus = "counter" items = "${requestScope.LIST_PRODUCT}">
                                <form action ="MainController" method ="POST">  
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${product.productID}</td>
                                        <td>
                                            <input type="text" name="name" value="${product.name}" required=""/>
                                        </td>
                                        <td>
                                            <input type="double" name="price" value="${product.price}" min="0" required=""/>
                                        </td>
                                        <td>
                                            <input type="number" name="quantity" value="${product.quantity}" min="0" required=""/>
                                        </td>
                                        <td>
                                            <input type ="submit" name = "action" value ="UpdatePro"/>
                                            <input type ="hidden" name = "productID" value ="${product.productID}"/>
                                            <input type ="hidden" name = "searchpro" value ="${param.searchpro}"/>
                                        </td>
                                        <td>
                                            <c:url var = "DeletePro" value = "MainController">
                                                <c:param name = "action" value = "DeletePro"></c:param>
                                                <c:param name = "productID" value = "${product.productID}"></c:param>
                                                <c:param name = "searchpro" value = "${param.searchpro}"></c:param>
                                            </c:url>
                                            <a href ="${DeletePro}">Delete</a>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>                  
                        </table>
                        <h1> ${requestScope.ERROR}</h1>
                    </c:if>
                </c:if>
                <a href="createpro.jsp">Create new product</a>  
            </div>
        </div>
    </body>
</html>
