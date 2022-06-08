
<%@page import="sample.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <h1>Input your information:</h1>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID" required=""/> <%= userError.getUserIDError()%> </br>
            Full Name<input type="text" name="fullName" required=""/> <%= userError.getFullNameError()%> </br>
            Password<input type="password" name="password" required=""/> <%= userError.getPasswordError()%> </br>
            Confirm<input type="password" name="confirm" required=""/> <%= userError.getConfirmError()%> </br>
            Role ID<input type="text" name="roleID" value="US" readonly=""/> <%= userError.getRoleIDError()%> </br>
            Address<input type="text" name="address" required=""/> <%= userError.getAddressError()%> </br>
            Birthday<input type="date" name="birthday" required=""/> </br>
            Phone<input type="text" name="phone" required=""/> <%= userError.getPhoneError()%> </br>
            Email<input type="email" name="email" required=""/> <%= userError.getEmailError()%> </br>
            <input type="submit" name="action" value="CreateUser"/>
            <input type="reset"  value="Reset"/>
        </form>
    </body>