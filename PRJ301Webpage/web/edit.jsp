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
        <form action="<c:url value="/toy/edit_handler.do" />">
            <div class="mb-3 mt-3">
                <label for="id" class="form-label">Id:</label>
                <input disabled type="text" class="form-control" id="id" placeholder="Toy id" value="${toy.id}">
                <input type="hidden" name="id" value="${toy.id}" />
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name:</label>
                <input type="text" class="form-control" id="name" placeholder="Toy name" name="name" value="${toy.name}">
            </div>
            <div class="mb-3">
                <label for="price" class="form-label">Price:</label>
                <input type="number" step="0.1" class="form-control" id="price" placeholder="Toy price" name="price" value="${toy.price}">
            </div>
            <div class="mb-3">
                <label for="expDate" class="form-label">Expired date:</label>
                <input type="date" class="form-control" id="expDate" placeholder="Toy expired date" name="expDate" value="${toy.expDate}">
            </div>
            <div class="mb-3">
                <label for="brandId" class="form-label">Brand id:</label>
                <input type="text" class="form-control" id="brandId" placeholder="Toy brand id" name="brandId" value="${toy.brandId}">
            </div>
            <button type="submit" class="btn btn-outline-success" name="op" value="update"><i class="bi bi-check-lg"></i> Update</button>
            <button type="submit" class="btn btn-outline-danger" name="op" value="cancel"><i class="bi bi-x-lg"></i> Cancel</button>
        </form>
    </div>
    <div class="col">
        <img src="<c:url value="/images/mickey.gif" />" alt=""/>
    </div>
</div>

