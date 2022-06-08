<%-- 
    Document   : userManagement
    Created on : Mar 10, 2022, 11:30:08 PM
    Author     : Admin
--%>

<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management Page</title>
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
        <%  String search = (String) request.getAttribute("SEARCH_USER");
            if (search == null) {
                search = "";
            }
        %>
        WELCOME ADMIN<h1> <%= loginUser.getFullName()%></h1>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <form action="MainController">
            Search<input type="text" name="search" value="<%=search%>"  placeholder="search"/>
            <input type="submit" name="action" value="SearchUser"/>
        </form>        
        <%
            List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (listUser != null) {
                if (listUser.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Password</th>
                    <th>Role ID</th>
                    <th>Address</th>
                    <th>Birthday</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%int count = 1;
                    for (UserDTO user : listUser) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="userID" value="<%= user.getUserID()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="fullName" value="<%= user.getFullName()%>" required="" />
                    </td>
                    <td><%= user.getPassword()%></td>
                    <td>
                        <input type="text" name="roleID" value="<%= user.getRoleID()%>" required="" />
                    </td>
                    <td>
                        <input type="text" name="address" value="<%= user.getAddress()%>" required="" />
                    <td>
                        <input type="text" name="birthday" value="<%= user.getBirthday()%>" required="" />
                    </td>
                    <td>
                        <input type="text" name="phone" value="<%= user.getPhone()%>" required="" />
                    </td>
                    <td>
                        <input type="text" name="email" value="<%= user.getEmail()%>" required="" />
                    </td>
                    <td>
                        <a href="MainController?action=DeleteUser&userID=<%=user.getUserID()%>&search=<%= search%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateUser"/>
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
