package sample.order;

public class OrderDTO {

    private int orderID;
    private String orderDate;
    private double total;
    private String userID;
    private boolean status;

    public OrderDTO() {
        this.orderID = 0;
        this.orderDate = "";
        this.total = 0;
        this.userID = "";
        this.status = true;
    }

    public OrderDTO(int orderID, String orderDate, double total, String userID, boolean status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
