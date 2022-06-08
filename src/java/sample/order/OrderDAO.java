package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import sample.product.ProductDTO;
import sample.utils.DBUtils;

public class OrderDAO {

    private static final String ORDER = "SELECT orderID, orderDate, total, userID FROM dbo.tblOrder WHERE orderID=?";
    private static final String CREATE_ORDER = "INSERT INTO tblOrder(orderDate,total,userID,status) VALUES (?,?,?,1)";
    private static final String CREATE_ORDERDETAIL = "INSERT INTO tblOrderDetail(price,quantity,orderID,productID,status) VALUES (?,?,?,?,1)";
    private static final String GET_ORDERID = "SELECT TOP 1 orderID FROM tblOrder ORDER BY orderID desc";

    public boolean createOrder(String orderDate, double total, String userID, Map<String, ProductDTO> cart) throws NamingException, SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_ORDER);
                ptm.setString(1, orderDate);
                ptm.setDouble(2, total);
                ptm.setString(3, userID);
                check = ptm.executeUpdate() > 0 ? true : false;
                ptm = conn.prepareStatement(GET_ORDERID);
                rs = ptm.executeQuery();
                if (check != false) {
                    if (rs.next()) {
                        int orderID = rs.getInt("orderID");
                        for (ProductDTO product : cart.values()) {
                            double price = product.getPrice();
                            int quantity = product.getQuantity();
                            ptm = conn.prepareStatement(CREATE_ORDERDETAIL);
                            ptm.setDouble(1, price);
                            ptm.setInt(2, quantity);
                            ptm.setInt(3, orderID);
                            ptm.setString(4, product.getProductID());
                            check = ptm.executeUpdate() > 0 ? true : false;
                        }
                    } else {
                        System.out.println("Cannot create DetailID");
                    }
                } else {
                    System.out.println("Cannot create OrderID");
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
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

    public OrderDTO checkOrder() throws SQLException {
        OrderDTO order = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERID);
                rs = ptm.executeQuery();
                int orderID = -1;
                if (rs.next()) {
                    orderID = rs.getInt("orderID");
                }
                ptm = conn.prepareStatement(ORDER);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String orderDate = rs.getString("orderDate");
                    int total = rs.getInt("total");
                    String userID = rs.getString("userID");
                    order = new OrderDTO(orderID, orderDate, total, userID, true);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return order;
    }

}
