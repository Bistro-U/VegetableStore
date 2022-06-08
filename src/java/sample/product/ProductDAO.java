package sample.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.order.OrderDetailDTO;
import sample.utils.DBUtils;

public class ProductDAO {

    private static final String UPDATE_QUANTITY = "UPDATE tblProduct SET quantity=? WHERE productID=?";
    private static final String FIND_QUANTITY = "SELECT quantity FROM tblProduct WHERE productID=? AND status=1";
    private static final String FIND = "SELECT productID, productName, imageUrl, price, quantity, categoryID, importDate,usingDate, status FROM tblProduct WHERE categoryID=?";
    private static final String SEARCH = "SELECT productID, productName, imageUrl, price, quantity, categoryID, importDate,usingDate, status FROM tblProduct WHERE productName like ?";
    private static final String SEARCH_ALL = "SELECT productID, productName, imageUrl, price, quantity, categoryID, importDate,usingDate, status FROM tblProduct";
    private static final String DELETE = "UPDATE tblProduct SET status=? WHERE productID=?";
    private static final String UPDATE = "UPDATE tblProduct SET productName=?, imageUrl=?, price=?, quantity=?, categoryID=?, importDate=?, usingDate=? WHERE productID=?";
    private static final String CHECK_DUPLICATE = "SELECT productName FROM tblProduct WHERE productID=?";
    private static final String CREATE = "INSERT INTO tblProduct(productID,productName,imageUrl,price,quantity,categoryID,importDate,usingDate,status) VALUES (?,?,?,?,?,?,?,?,?)";

    public boolean updateQuantity(int totalQuantity, int quantity, String productID) throws NamingException, SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, totalQuantity - quantity);
                ptm.setString(2, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    
    public int getQuantity(String productID) throws ClassNotFoundException, SQLException, NamingException{
        int res = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(FIND_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if(rs.next()){
                    res = rs.getInt("quantity");
                }
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return res;
    }

    public List<ProductDTO> getFindListProduct(String search) throws SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (!search.equals("")) {
                    ptm = conn.prepareStatement(FIND);
                    ptm.setString(1, search);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String productID = rs.getString("productID");
                            String productName = rs.getString("productName");
                            String imageUrl = rs.getString("imageUrl");
                            double price = rs.getDouble("price");
                            int quantity = rs.getInt("quantity");
                            String categoryID = rs.getString("categoryID");
                            String importDate = rs.getString("importDate");
                            String usingDate = rs.getString("usingDate");
                            listProduct.add(new ProductDTO(productID, productName, imageUrl, price, quantity, categoryID, importDate, usingDate, status));
                        }
                    }
                } else {
                    ptm = conn.prepareStatement(SEARCH_ALL);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String productID = rs.getString("productID");
                            String productName = rs.getString("productName");
                            String imageUrl = rs.getString("imageUrl");
                            double price = rs.getDouble("price");
                            int quantity = rs.getInt("quantity");
                            String categoryID = rs.getString("categoryID");
                            String importDate = rs.getString("importDate");
                            String usingDate = rs.getString("usingDate");
                            listProduct.add(new ProductDTO(productID, productName, imageUrl, price, quantity, categoryID, importDate, usingDate, status));
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

    public boolean create(ProductDTO product) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getImageUrl());
                ptm.setDouble(4, product.getPrice());
                ptm.setInt(5, product.getQuantity());
                ptm.setString(6, product.getCategoryID());
                ptm.setString(7, product.getImportDate());
                ptm.setString(8, product.getUsingDate());
                ptm.setBoolean(9, true);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<ProductDTO> getListProduct(String search) throws SQLException {
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (search != "") {
                    ptm = conn.prepareStatement(SEARCH);
                    ptm.setString(1, "%" + search + "%");
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String productID = rs.getString("productID");
                            String productName = rs.getString("productName");
                            String imageUrl = rs.getString("imageUrl");
                            double price = rs.getDouble("price");
                            int quantity = rs.getInt("quantity");
                            String categoryID = rs.getString("categoryID");
                            String importDate = rs.getString("importDate");
                            String usingDate = rs.getString("usingDate");
                            listProduct.add(new ProductDTO(productID, productName, imageUrl, price, quantity, categoryID, importDate, usingDate, status));
                        }
                    }
                } else {
                    ptm = conn.prepareStatement(SEARCH_ALL);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status) {
                            String productID = rs.getString("productID");
                            String productName = rs.getString("productName");
                            String imageUrl = rs.getString("imageUrl");
                            double price = rs.getDouble("price");
                            int quantity = rs.getInt("quantity");
                            String categoryID = rs.getString("categoryID");
                            String importDate = rs.getString("importDate");
                            String usingDate = rs.getString("usingDate");
                            listProduct.add(new ProductDTO(productID, productName, imageUrl, price, quantity, categoryID, importDate, usingDate, status));
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProduct;
    }

    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setBoolean(1, false);
                ptm.setString(2, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getImageUrl());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getCategoryID());
                ptm.setString(6, product.getImportDate());
                ptm.setString(7, product.getUsingDate());
                ptm.setString(8, product.getProductID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
