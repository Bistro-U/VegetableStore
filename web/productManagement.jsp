<%-- 
    Document   : productManagement
    Created on : Mar 10, 2022, 11:30:20 PM
    Author     : Admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Management Page</title>
    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
                loginUser = new UserDTO();
                return;
            }
            String search = (String) request.getAttribute("SEARCH_PRODUCT");
            if (search == null) {
                search = "";
            }
        %>
        WELCOME ADMIN<h1> <%= loginUser.getFullName()%></h1>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search Product<input type="text" name="search" value="<%=search%>"  placeholder="search"/>
            <input type="submit" name="action" value="SearchProduct"/>
        </form>
            <a href="createProduct.jsp">Add new product</a>
        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image Url</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category ID</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%int count = 1;
                    for (ProductDTO product : listProduct) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="productID" value="<%= product.getProductID()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>" required="" />
                    </td>
                    <td>
                        <input type="url" name="imageUrl" value="<%= product.getImageUrl()%>" required="" />
                    </td>
                    <td>
                        <img src="<%= product.getImageUrl()%>" alt="Error" style="width:50px;height:50px">
                    </td>
                    <td>
                        <input type="text" name="price" value="<%= product.getPrice()%>" required="" />
                    <td>
                        <input type="text" name="quantity" value="<%= product.getQuantity()%>" required="" />
                    </td>
                    <td>
                        <input type="text" name="categoryID" value="<%= product.getCategoryID()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="importDate" value="<%= product.getImportDate()%>" required="" />
                    </td>
                    <td>
                        <input type="text" name="usingDate" value="<%= product.getUsingDate()%>" required="" />
                    </td>
                    <td>
                        <a href="MainController?action=DeleteProduct&productID=<%=product.getProductID()%>&search=<%= search%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateProduct"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td> 
                </tr>
            </form>

            <%
                }
            %>
        </tbody>
    </table>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%= error%>
    <%
            }
        }
    %>
</body>
</html>
