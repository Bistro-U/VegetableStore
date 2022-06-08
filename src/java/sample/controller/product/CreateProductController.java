package sample.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;

@WebServlet(name = "CreateProductController", urlPatterns = {"/CreateProductController"})
public class CreateProductController extends HttpServlet {

    private static final String ERROR = "createProduct.jsp";
    private static final String SUCCESS = "productManagement.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String imageUrl = request.getParameter("imageUrl");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            boolean status = true;
            boolean checkValidation = true;
            ProductDAO dao = new ProductDAO();
            boolean checkDuplicate = dao.checkDuplicate(productID);
            if (checkDuplicate) {
                productError.setProductIDError("Duplicate ProductID");
                checkValidation = false;
            }
            if (productID.length() < 1 || productID.length() > 10) {
                productError.setProductIDError("Product ID must be in [1,10]");
                checkValidation = false;
            }
            if (productName.length() < 1 || productName.length() > 30) {
                productError.setProductNameError("Product Name must be in [1,30]");
                checkValidation = false;
            }
            if (imageUrl.length() < 1 || imageUrl.length() > 150) {
                productError.setImageUrlError("Image Url must be in [1,150]");
            }
            if (checkADouble(price)) {
                productError.setPriceError("Price must be a number greater than 0");
                checkValidation = false;
            }
            if (checkAInteger(quantity)) {
                productError.setQuantityError("Quantity must be a number greater than 0");
                checkValidation = false;
            }
            if (checkValidation) {
                ProductDTO product = new ProductDTO(productID, productName, imageUrl, price, quantity, categoryID, importDate, usingDate, status);
                boolean checkCreate = dao.create(product);
                if (checkCreate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            if (e.toString().contains("duplicate")) {
                productError.setProductIDError("Duplicate Product ID");
            }
            request.setAttribute("PRODUCT_ERROR", productError);
            log("Error at Create Product Controller: " + e.toString());
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

    public static boolean checkADouble(double number) {
        do {
            try {
                if (number <= 0) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
        } while (number <= 0);
        return false;
    }

    public static boolean checkAInteger(int number) {
        do {
            try {
                if (number <= 0) {
                    return true;
                }
            } catch (Exception e) {
                return true;
            }
        } while (number <= 0);
        return false;
    }
}
