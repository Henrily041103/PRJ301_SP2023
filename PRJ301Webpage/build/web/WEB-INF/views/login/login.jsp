<%-- 
    Document   : login
    Created on : 21-03-2023, 22:25:31
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="logreg-form">
    <form class="form-signin" action="login" method="post">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>
        
        <label for="user">Username: </label>
        <input name="user"  type="text" id="user" class="form-control" placeholder="Username" required autofocus>
        <label for="pass">Password: </label>
        <input name="pass"  type="password" id="pass" class="form-control" placeholder="Password" required>

        <div class="form-group form-check">
            <input name="remember" value="1" type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Remember me</label>
        </div>

        <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
        <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> <a href="<c:url value="/login/register.do"/>">Sign up</a></button>
        <hr>
    </form>
</div>
