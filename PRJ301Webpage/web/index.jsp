<%-- 
    Document   : index
    Created on : Feb 13, 2023, 1:03:05 PM
    Author     : PHT
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<h2>Toy CRUD</h2>
<hr/>
<a href="<c:url value="/toy/create.do" />"><i class="bi bi-pencil-square"></i> Create</a>
<a href="<c:url value="/toy/cart.do" />">Cart</a>
<a href="<c:url value="/toy/receipt.do" />">Receipt</a>

<div class="container">
    <div class="row">
        <c:forEach var="toy" items="${list}" varStatus="loop">
            <div class="col-md-3 my-3">
                <div class="container w-100" style="width: 50rem;">  
                    <img class="card-img-top" src="<c:url value="/images/saul.gif" />" alt=""/>
                    <div class="card-body">
                        <h5 class="card-title">Name: ${toy.name}</h5>
                        <h6 style="margin-top:5px" class="id">Id: ${toy.id}</h6>
                        <h6 style="margin-top:5px" class="price">Price: ${toy.price}</h6>
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="<c:url value="#" />" class="btn btn-outline-dark"> Add to cart</a> 
                            <a href="<c:url value="#" />" class="btn btn-outline-primary"> Buy now</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="index.do?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers. 
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>  
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="index.do?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="index.do?page=${currentPage + 1}">Next</a></td>
</c:if>

<!--<div class="text-xs-center">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</div>-->


