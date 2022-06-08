

<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <style>
            .body{
                margin: 0;
                padding: 0;
            }
            .wrapper{
                border: 1px solid azure;
                height: 700px;
                width: 90%;
                margin: 0 auto;
            }
            .header{
                border: 1px solid darkorange;
                height: 180px;
                width: 100%;            
            }
            .menu{
                border: 1px solid darkblue;
                height: 50px;
                width: 100%;
                background: darkblue;
            }
            ul.list_menu{
                padding: 0;
                margin: 0;
                width: 60%;
                border: 1px solid black;
                list-style: none;
                margin: 0 auto;
                line-height: 20px;
            }
            ul.list_menu li{
                float: left;
                margin: 7px;
                padding: 5px;
            }
            ul.list_menu li:hover{
                background: chartreuse;
            }
            ul.list_menu li a{
                text-decoration: none;
                text-align: center;
                color: azure;
            }
            #main{
                border: 1px solid azure;
                height: auto;
                width: 100%;
            }
            .footer{
                border: 1px solid darkblue;
                height: 150px;
                width: 100%;
                background: darkblue;
            }
            .sidebar{
                border: 1px solid darkblue;
                height: 390px;
                width: 20%;
                margin-top: 5px;
                margin-left: 5px;
                float: left;
            }
            ul.list_sidebar{
                padding: 0;
                margin: 0;
                width: 100%;
                list-style: none;
                margin: 0 auto;
                line-height: 20px;
            }
            ul.list_sidebar li{
                margin: 7px;
                padding: 5px;
            }
            ul.list_sidebar li:hover{
                background: coral;
            }
            ul.list_sidebar li a{
                text-decoration: none;
                text-align: center;
                color: brown;
            }
            .mainContent{
                border: 1px solid darkblue;
                height: auto;
                width: 78%;
                margin-top: 5px;
                margin-left: 5px;
                float: right;
            }
            .clear{
                clear: both;
            }
            ul.list_product{
                margin: 0;
                padding: 0;
                list-style: none;
                width: 100%;
            }
            ul.list_product li{
                width: 19%;
                border: 1px dashed black;
                margin: 5px 5px 5px 5px;
                float: left;
                background: honeydew;                               
                height: 220px;
            }
            ul.list_product li img{
                width: 100%;
                height: 100px;
            }
            p.title_product{
                text-align: center;
                color: black;
                font-size: 16px;
                font-weight: bold;
            }
            p.price_product{
                text-align: center;
                color: red;
                font-size: 16px;
                font-weight: bold;
            }
            .form_product {
                text-align: center;
                color: black;
                font-size: 16px;
                font-weight: bold;
            }
            ul.list_product li a{
                text-decoration: none;
            }
            p.footer_copyright{
                text-align: center;
                color: black;
            }
        </style>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
            String search = (String) request.getAttribute("FIND_PRODUCT");
            if (search == null) {
                search = "";
            }
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("FIND_LIST_PRODUCT");
        %>
        <div class="wrapper">
            <div class="header">
                <img src="https://previews.123rf.com/images/irigri/irigri1802/irigri180200616/95751264-banner-bio-lebensmittel-hintergrund-lebensmittel-verschiedene-fr%C3%BCchte-obst-und-gem%C3%BCse-hintergrund-ko.jpg" width="100%" height="180px">
            </div>
            <div class="menu">
                <ul class="list_menu">
                    <li><a href="FindProductController?categoryID=">Home Page</a></li>
                    <li><a href="viewCart.jsp">View Cart</a></li>
                    <li><a href="showUserInfo.jsp">User Profile</a></li>
                    <li><a href="MainController?action=Logout">Log out</a></li>
                    <li> Welcome <%=loginUser.getFullName()%> </li>
                </ul>
            </div>
            <div class="main">
                <div class="sidebar">
                    <ul class="list_sidebar">
                        <li><a href="FindProductController?categoryID=">Product Category</a></li>
                        <li><a href="MainController?action=FindProduct&categoryID=VEG">>>> Vegetable</a></li>
                        <li><a href="MainController?action=FindProduct&categoryID=TUB">>>> Tuber</a></li>
                        <li><a href="MainController?action=FindProduct&categoryID=FRU">>>> Fruit</a></li>
                    </ul>
                </div>
                <div class="mainContent">
                    <ul class="list_product">
                        <%
                            if (listProduct != null) {
                                if (listProduct.size() > 0) {
                                    for (ProductDTO product : listProduct) {
                        %>
                        <li>
                            <img src="<%= product.getImageUrl()%>">
                            <p class="title_product"><%=  product.getProductName()%></p>
                            <p class="price_product">Price <%=  product.getPrice()%></p>
                            <form action="MainController" class="form_product">                         
                                <input type="hidden" name="productName" value="<%= product.getProductName()%>"/>
                                <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                                <input type="hidden" name="imageUrl" value="<%= product.getImageUrl()%>"/>
                                <input type="hidden" name="price" value="<%= product.getPrice()%>"/>
                                Quantity
                                <select name="quantity">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option> 
                                </select>
                                <input type="hidden" name="categoryID" value="<%= product.getCategoryID()%>"/>
                                <input type="hidden" name="importDate" value="<%= product.getImportDate()%>"/>
                                <input type="hidden" name="usingDate" value="<%= product.getUsingDate()%>"/>
                                <input type="submit" name="action" value="Add">
                            </form>
                        </li>
                        <%
                                    }
                                }
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="clear"></div>
            <div class="footer">
                <p class="footer_copyright">Copyright by QUANG HUNG</p>
            </div>
        </div>

    </body>
</html>
