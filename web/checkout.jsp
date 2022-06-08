<%-- 
    Document   : chechout
    Created on : Mar 12, 2022, 5:54:31 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.order.OrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>
        <div class="header">
            <img src="https://previews.123rf.com/images/irigri/irigri1802/irigri180200616/95751264-banner-bio-lebensmittel-hintergrund-lebensmittel-verschiedene-fr%C3%BCchte-obst-und-gem%C3%BCse-hintergrund-ko.jpg" width="100%" height="180px">
        </div>
        <h1>Congratulation!</h1
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
            OrderDTO order = (OrderDTO) session.getAttribute("ORDER");
        %>
        <h1>Here is your order: </h1>
        Order ID : <%= order.getOrderID()%></br>
        Order Date : <%= order.getOrderDate()%></br>
        Total : <%= order.getTotal()%> VNĐ</br>
        User ID : <%= order.getUserID()%></br>
        
    </body>
</html>
