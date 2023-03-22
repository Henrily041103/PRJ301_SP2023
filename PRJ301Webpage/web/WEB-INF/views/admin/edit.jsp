<%-- 
    Document   : edit
    Created on : Feb 16, 2023, 12:32:26 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Toy Edit</h2>
<hr/>
<div class="row">
    <div class="col">
        <form action="<c:url value="/shop/edit_handler.do" />">
            <div class="mb-3 mt-3">
                <label for="id" class="form-label">Id:</label>
                <input disabled type="text" class="form-control" id="id" placeholder="${product.id}" value="${product.id}">
                <input type="hidden" name="id" value="${product.id}" />
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Type</label>
                <input type="text" class="form-control" id="name" placeholder="Product type" name="type" value="${product.type}">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price:</label>
                <input type="number" step="0.1" class="form-control" id="price" placeholder="Product price" name="price" value="${product.price}">
            </div>
            <div class="mb-3">
                <label for="sale" class="form-label">Sale</label>
                <input type="number" step="1" class="form-control" id="sale" placeholder="Product sale" name="sale" value="${product.sale}">
            </div>
            <div class="mb-3">
                <label for="stock" class="form-label">Stock</label>
                <input type="number" step="1" class="form-control" id="stock" placeholder="Product stock" name="stock" value="${product.stock}">
            </div>
            <div class="mb-3">
                <label for="ageGroup" class="form-label">Age group</label>
                <input type="text" class="form-control" id="age-group" placeholder="Product age group" name="ageGroup" value="${product.ageGroup}">
            </div>
            <div class="mb-3">
                <label for="size" class="form-label">Size</label>
                <input type="text" class="form-control" id="size" placeholder="Product size" name="size" value="${product.size}">
            </div>
            <div class="mb-3">
                <label for="size" class="form-label">Color</label>
                <input type="text" class="form-control" id="color" placeholder="Product color" name="color" value="${product.color}">
            </div>
            <div class="mb-3">
                <label for="brand" class="form-label">Brand:</label>
                <select name="brand" class="form-control">
                    <c:forEach var="brand" items="${list}">
                        <option value="${product.brand}" ${product.brand==product.brand?"selected":""}>${product.brand}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-outline-success" name="op" value="update"><i class="bi bi-check-lg"></i> Update</button>
            <button type="submit" class="btn btn-outline-danger" name="op" value="cancel"><i class="bi bi-x-lg"></i> Cancel</button>
        </form>
    </div>
    <div class="col">
        <img src="<c:url value="/images/mickey.gif" />" alt=""/>
    </div>
</div>

