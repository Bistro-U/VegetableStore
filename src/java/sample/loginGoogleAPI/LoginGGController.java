/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.loginGoogleAPI;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDTO;

@WebServlet("/login-google")
public class LoginGGController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginGGController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            String userID = googlePojo.getId();
            String fullName = googlePojo.getName();
            String email = googlePojo.getEmail();
            UserDTO loginUser = new UserDTO(userID, fullName, "", "", "", "", "", email, true);
            loginUser.setRoleID("US");
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_USER", loginUser);
            RequestDispatcher dis = request.getRequestDispatcher("FindProductController?categoryID=");
            dis.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
