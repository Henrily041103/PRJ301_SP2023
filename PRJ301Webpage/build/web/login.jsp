<%-- 
    Document   : login
    Created on : Feb 21, 2023, 9:05:10 AM
    Author     : Thanh
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            #body{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 80vw;
                height: 70vh;
            }
            #inbody{
                display: inline-block;
            }                                         
        </style>
    </head>
    <body>
        <div id="body">
            <div id ="inbody">
                <h1>Welcome</h1>
                <form action="MainController" method="POST">
                    User ID <input type="text" name="userID" required=""><br>
                    Password <input type="password" name="password" required=""><br>
                    <input type="submit" name="action" value="Login"><br>
                    <input type="reset" value="Reset">
                </form>       
                <a href="create.jsp">Create new user</a>
                <h1> ${requestScope.ERROR}</h1>
            </div>
        </div>
    </body>
</html>

