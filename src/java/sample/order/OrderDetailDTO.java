package sample.order;

public class OrderDetailDTO {

    private int detailID;
    private double price;
    private int quantity;
    private int orderID;
    private String productID;
    private boolean status;

    public OrderDetailDTO() {
        this.detailID = 0;
        this.price = 0;
        this.quantity = 0;
        this.orderID = 0;
        this.productID = "";
        this.status = true;
    }

    public OrderDetailDTO(int detailID, double price, int quantity, int orderID, String productID, boolean status) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
        this.status = status;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
