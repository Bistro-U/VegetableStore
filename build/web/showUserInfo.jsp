<%-- 
    Document   : showInfo
    Created on : Mar 10, 2022, 11:23:08 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show User Information Page</title>
    </head>
    <body>
        <div class="header">
            <img src="https://previews.123rf.com/images/irigri/irigri1802/irigri180200616/95751264-banner-bio-lebensmittel-hintergrund-lebensmittel-verschiedene-fr%C3%BCchte-obst-und-gem%C3%BCse-hintergrund-ko.jpg" width="100%" height="180px">
        </div>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
        %>
        <h1>Here is your information: </h1>
        User ID : <%= loginUser.getUserID()%></br>
        Full Name : <%= loginUser.getFullName()%></br>
        Role ID : <%= loginUser.getRoleID()%></br>
        Address : <%= loginUser.getAddress()%></br>
        Birthday : <%= loginUser.getBirthday()%></br>
        Phone : <%= loginUser.getPhone()%></br>
        Email : <%= loginUser.getEmail()%></br>
    </body>
</html>
