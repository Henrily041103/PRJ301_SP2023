<%-- 
    Document   : createpro
    Created on : Feb 21, 2023, 9:48:17 PM
    Author     : Thanh
--%>

<%@page import="sample.product.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE PRODUCT PAGE</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            #inbody{
                display: inline-block;

            }
            #body{
                display: flex;
                justify-content: center;
                align-items: center;
                width: 80vw;
                height: 70vh;
            }
        </style>
    </head>
    <div id="body">
        <div id="inbody">
            <h1>Input Product information</h1>

            <form action="MainController" method="POST">
                Product ID: <input type="text" name="productID" required=""/>
                ${requestScope.PRODUCT_ERROR.ProductID}<br>
                Product Name: <input type="text" name="name" required=""/>
                ${requestScope.PRODUCT_ERROR.name}<br>
                Price: <input type="number" name="price" required=""/>
                ${requestScope.PRODUCT_ERROR.price}<br>
                Quantity: <input type="number" name="quantity" required=""/>
                ${requestScope.PRODUCT_ERROR.quantity}<br>
                <input type="submit" name="action"  value="CreatePro"/><br>
                <input type="reset" value="Reset"/>
            </form>
        </div>
    </div>
</body>
</html>
