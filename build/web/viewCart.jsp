
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sample.user.UserDTO"%>
<%@page import="sample.controller.shopping.Cart"%>
<%@page import="sample.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <style>
            table{
                border: 1px solid darkorange;
                margin: 0 auto;
            }
            .row td img{
                width: 100%;
                height: 150px;
            }
            .wrapper h1{
                text-align: center;
            }
            .input-form{
                margin-bottom: 20px
            }
            .input-form input{
                margin-top: 5px;
                margin-left: 657px; 
                width: 10%;
                padding: 10px 20px;
                outline: none;
                border: 1px solid #607d8b;
                font-size: 16px;
                letter-spacing: 1px;
                color: #607d8b;
                background: transparent;
                border-radius: 30px;
            }
            .input-form input[type="submit"]:hover{
                background: #607db8;
            }
            .input-form input[type="submit"]{
                margin-left: 682px;
                background: chocolate;
                color: #fff;
                outline: none;
                border: none;
                font-weight: 500;
                cursor: pointer;
                box-shadow: 0 1px 1px rgba(0,0,0,0.12),
                    0 2px 2px rgba(0,0,0,0.12),
                    0 4px 4px rgba(0,0,0,0.12),
                    0 8px 8px rgba(0,0,0,0.12),
                    0 16px 16px rgba(0,0,0,0.12);
            }
            #add_more{
                margin-left: 726px;
            }
            .row td span{
                text-align: center;
                text-decoration: bold;
                font-size: 16px;
            }
            #name{
                font-size: 35px;
                color: #f53677;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body
        <div class="wrapper">
            <div class="header">
                <img src="https://previews.123rf.com/images/irigri/irigri1802/irigri180200616/95751264-banner-bio-lebensmittel-hintergrund-lebensmittel-verschiedene-fr%C3%BCchte-obst-und-gem%C3%BCse-hintergrund-ko.jpg" width="100%" height="180px">
            </div>
            <h1>Your selected items: </h1>
        </div>
        <c:set var="error" value="${requestScope.ERROR}"> </c:set>
        <c:if test="null error">
            <c:set var="error" value=""></c:set>
        </c:if>
        <c:out value="${error}"></c:out>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
        %>


        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Product</th>
                    <th>Information</th>
                    <th>Remove</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (ProductDTO product : cart.getCart().values()) {
                        total += product.getPrice() * product.getQuantity();
                %> 
            <form action="MainController"> 
                <tr class="row">
                    <td><%= count++%></td>
                    <td><img src="<%= product.getImageUrl()%>"></td>
                    <td class="info">
                        <span id="name"> <%= product.getProductName()%></span> </br>
                        <span> Product ID:<%= product.getProductID()%> </span></br>
                        <span><%= product.getPrice()%> VNĐ  </span></br>
                        <span>Quantity<input type="number" name="quantity" value="<%= product.getQuantity()%>"/> </span>
                    </td>
                    <td >
                        <input type="hidden" name="id" value="<%= product.getProductID()%>"/>
                        <input type="submit" name="action" value="Remove"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Edit"/>
                    </td>
                </tr>
            </form>   
            <%
                }
            %>
        </tbody>
    </table>
    <h1>Total:<%= total%>VNĐ</h1>
    <form class="input-form">
        <input type="hidden" name="userID" value="<%= loginUser.getUserID()%>" />
        <input type="hidden" name="total" value="<%= total%>"/>
        <input type="hidden" name="orderDate" value="<%= java.time.LocalDate.now()%>"/></br>
        <input type="submit" name="action" value="CheckOut"/>
    </form>
    <%
        }
    %>
    <a href ="FindProductController?categoryID=" id="add_more">Add more</a>
    <%
        String error = (String) request.getAttribute("ERROR_DUPLICATE");
        if (error == null) {
            error = "";
        }
    %>
    <%= error%>
</body>
</html>
