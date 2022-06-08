<%-- 
    Document   : admin
    Created on : Feb 17, 2022, 9:38:42 AM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
        %>
        WELCOME ADMIN<h1> <%= loginUser.getFullName()%></h1>
        <a href="MainController?action=Logout" >Logout</a>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <a href="userManagement.jsp">User Management</a>
        <a href="productManagement.jsp">Product Management</a>
</body>
</html>
