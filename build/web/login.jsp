<%-- 
    Document   : login
    Created on : Feb 17, 2022, 9:02:18 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <title>Login Page</title>
        <link rel="stylesheet" href="styleLogin.css" type="text/css">
    </head>
    <body>
        <div class="wrapper">
            <div class="header">
                <img src="https://previews.123rf.com/images/irigri/irigri1802/irigri180200616/95751264-banner-bio-lebensmittel-hintergrund-lebensmittel-verschiedene-fr%C3%BCchte-obst-und-gem%C3%BCse-hintergrund-ko.jpg" width="100%" height="180px">
            </div>
            <h1>VEGETABLE STORE</h1>
            <form action="MainController" method="POST">
                <div class="input-form">
                    <span>User ID</span>
                    <input type="text" name="userID" required="" placeholder="Enter User ID"/>
                </div>
                <div class="input-form">
                    <span>Password</span>
                    <input type="password" name="password" required="" placeholder="Enter password"/>
                </div>  
                <div class="input-form">
                    <input type="submit" value="Login" name="action" required="" placeholder=""/>
                </div>
                <div class="input-form">
                    <input type="reset" value="Reset">
                </div>
                <div class="input-form">
                    <p>Don't have account?<a href="createUser.jsp"> Register!</a></p>
                </div>
                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/VegetableStore/LoginGGController&response_type=code
                   &client_id=635566642213-da1mihklj4lmogoqc55bvm5ap0vm6gdf.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  
            </form>

            <c:set var="error" value="${requestScope.ERROR}"> </c:set>
            <c:if test="null error">
                <c:set var="error" value=""></c:set>
            </c:if>
            <c:out value="${error}"></c:out>
        </div>
    </body>
</html>
