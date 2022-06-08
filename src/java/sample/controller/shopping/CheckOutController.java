package sample.controller.shopping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.order.OrderDetailDTO;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.sendMailAPI.JavaMailUtil;

@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                OrderDAO dao = new OrderDAO();
                ProductDAO daoV2 = new ProductDAO();
                String userID = request.getParameter("userID");
                double total = Double.parseDouble(request.getParameter("total"));
                String orderDate = request.getParameter("orderDate");
                boolean createOrder = dao.createOrder(orderDate, total, userID, (Map<String, ProductDTO>) cart.getCart());
                OrderDTO checkCreateOrder = dao.checkOrder();
                if (checkCreateOrder != null) {
                    for (ProductDTO product : cart.getCart().values()) {
                        String productID = product.getProductID();
                        int totalQuantity = daoV2.getQuantity(productID);
                        int quantity = product.getQuantity();
                        boolean checkQuanlity = daoV2.updateQuantity(totalQuantity, quantity, productID);
                    }
                    session.setAttribute("CART", null);
                    session.setAttribute("ORDER", checkCreateOrder);
                }
                url = SUCCESS;
                JavaMailUtil email = new JavaMailUtil();
                email.sendMail("duongquanghung0122@gmail.com");
            }
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());
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
