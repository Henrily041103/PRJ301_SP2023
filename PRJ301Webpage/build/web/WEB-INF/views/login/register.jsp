<%-- 
    Document   : register
    Created on : Mar 20, 2023, 2:10:31 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="logreg-form">
    <form action="signup" method="post" class="form-signup">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
        
        <label for="user">Username: </label>
        <input name="user" type="text" id="user" class="form-control" placeholder="User name" required="" autofocus="">
        <label for="user">Password: </label>
        <input name="pass" type="password" id="pass" class="form-control" placeholder="Password" required autofocus="">
        <label for="user">Retype Password: </label>
        <input name="repass" type="password" id="repass" class="form-control" placeholder="Repeat Password" required autofocus="">

        <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
        <button class="btn btn-primary btn-block" type="button" id="btn-signup"><i class="fas fa-user-plus"></i> <a href="<c:url value="/login/login.do"/>">Sign in</a></button>
        <hr>
    </form>
    <br>
</div>
