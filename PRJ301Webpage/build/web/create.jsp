<%-- 
    Document   : create
    Created on : Feb 21, 2023, 9:19:28 AM
    Author     : Thanh
--%>

<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <h1>Input your information</h1>
        <form action="MainController" method="POST">
            User ID: <input type="text" name="userID" required=""/>
            ${requestScope.USER_ERROR.userID}</br>
            Name: <input type="text" name="name" required=""/>
            ${requestScope.USER_ERROR.name}</br>
            Role ID: <input type="text" name="roleID" required=""/>
            ${requestScope.USER_ERROR.roleID}</br>
            Password: <input type="password" name="password" required=""/>
            ${requestScope.USER_ERROR.password}</br>
            Confirm: <input type="password" name="confirm" required=""/>
            ${requestScope.USER_ERROR.confirm}</br>
            Address: <input type="text" name="address" required=""/>
            ${requestScope.USER_ERROR.address}</br>
            Phone: <input type="text" name="phone" required=""/>
            ${requestScope.USER_ERROR.phone}<br>
            <br>
            <input type="submit" name="action"  value="Create"/>
            <input type="reset" value="Reset"/>
        </form>
            <br>
            <a href="login.jsp">Return</a>
    </body>
</html>
