

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductError"%>
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product Page</title>
    </head>
    <body>
        <h1>Input product's information:</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
        %>
        <form action="MainController" method="POST">
            Product ID<input type="text" name="productID" required=""/><%= productError.getProductIDError()%>  </br>
            Product Name<input type="text" name="productName" required=""/> <%= productError.getProductNameError()%> </br>
            Image Url<input type="url" name="imageUrl" required=""/> <%= productError.getImageUrlError()%> </br>
            Price<input type="text" name="price" required=""/> (VNĐ) <%= productError.getPriceError()%> </br>
            Quantity<input type="text" name="quantity"  required=""/> <%= productError.getQuantityError()%> </br>
            Category ID
            <select name="categoryID">
                <option value="VEG">VEG</option>
                <option value="TUB">TUB</option>
                <option value="FRU">FRU</option>
            </select> </br>
            Import Date<input type="date" name="importDate" required=""/> </br>
            Using Date<input type="date" name="usingDate" required=""/> </br>
            <input type="submit" name="action" value="CreateProduct"/>
            <input type="reset"  value="Reset"/>
        </form>
    </body>
</html>


