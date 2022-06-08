package sample.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String CREATE_USER = "CreateUser";
    private static final String CREATE_USER_CONTROLLER = "CreateUserController";
    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";
    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";
    private static final String UPDATE_USER = "UpdateUser";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";
    private static final String CREATE_PRODUCT = "CreateProduct";
    private static final String CREATE_PRODUCT_CONTROLLER = "CreateProductController";
    private static final String SEARCH_PRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    private static final String FIND_PRODUCT = "FindProduct";
    private static final String FIND_PRODUCT_CONTROLLER = "FindProductController";
    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";
    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";
    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";
    private static final String CHECKOUT = "CheckOut";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (CREATE_USER.equals(action)) {
                url = CREATE_USER_CONTROLLER;
            } else if (SEARCH_USER.equals(action)) {
                url = SEARCH_USER_CONTROLLER;
            } else if (DELETE_USER.equals(action)) {
                url = DELETE_USER_CONTROLLER;
            } else if (UPDATE_USER.equals(action)) {
                url = UPDATE_USER_CONTROLLER;
            } else if (CREATE_PRODUCT.equals(action)) {
                url = CREATE_PRODUCT_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (FIND_PRODUCT.equals(action)) {
                url = FIND_PRODUCT_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            }else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
