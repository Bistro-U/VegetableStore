package sample.controller.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            boolean status = true;
            boolean checkValidation = true;
            UserDAO dao = new UserDAO();
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                userError.setUserIDError("Duplicate UserID");
                checkValidation = false;
            }
            if (userID.length() < 2 || userID.length() > 20) {
                userError.setUserIDError("UserID must be in [2,20]");
                checkValidation = false;
            }
            if (fullName.length() < 5 || fullName.length() > 30) {
                userError.setFullNameError("Full Name must be in [5,30]");
                checkValidation = false;
            }
            if (password.length() < 1 || password.length() > 30) {
                userError.setPasswordError("Password must be in [1,30]");
                checkValidation = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmError("2 passwords are different");
                checkValidation = false;
            }
            if (address.length() < 5 || address.length() > 50) {
                userError.setAddressError("Address must be in [5,50]");
                checkValidation = false;
            }   
            if (!checkValidPhone(phone)) {
                userError.setPhoneError("Invalid Phone Number");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, fullName, password, roleID, address, birthday, phone, email, status);
                boolean checkCreate = dao.create(user);
                if (checkCreate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                userError.setUserIDError("Duplicate User ID");
            }
            request.setAttribute("USER_ERROR", userError);
            log("Error at Create User Controller: " + e.toString());
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


    public static boolean checkValidPhone(String phone) {
        String regex = "^[F][0-9]{3}";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(phone);
        return phone.matches(regex);
    }

}
