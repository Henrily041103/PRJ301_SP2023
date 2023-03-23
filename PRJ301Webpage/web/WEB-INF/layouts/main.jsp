<%-- 
    Document   : main
    Created on : 21-03-2023, 22:45:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Baby Products Webapp</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    </head>
    <body>
        <header class="container" >
            <c:if test="${current-user!=null}">
                <div class="row">
                    <div class="col-2" id="logo">
                        Baby Shop
                    </div>
                    <div class="col-6" id="search-bar">
                        <form action="<c:url value="/shop/search.do"/>">
                            <input type="text" name="search" id="search" placeholder="Search a product">
                            <button type="submit" id="search-icon">
                                <i class='fa-solid fa-magnifying-glass'>

                                </i>
                            </button>
                        </form>
                    </div>
                    <div class="col-2" id="cart">
                        <a href="<c:url value="/cart/show.do"/>">
                            <i class='fa fa-shopping-cart'>
                            </i>
                        </a>
                    </div>
                    <div class="col-2" id="account-info">
                        <c:if test="${current-user==null}">
                            <div id="account-name">Current user: ${current-user.username}</div>
                        </c:if>
                        
                        <div id="account-logout">
                            <c:choose>
                                <c:when test="${current-user==null}">
                                    <a class="btn btn-danger" href="login/logout.do" id="logout">Log out</a>
                                </c:when>
                                <c:when test="${current-user!=null}">
                                    <a class="btn btn-primary" href="login/login.do" id="logout">Log in</a>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </c:if >

            <c:if test="${current-user==null}">
                <div id="logo-alone">
                    Baby Shop
                </div>
            </c:if>
        </header>

        <section>
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp"/>
        </section>

        <div>${error}</div>

        <footer>
            FPT-PRJ301-GROUP 5
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    </body>
</html>
