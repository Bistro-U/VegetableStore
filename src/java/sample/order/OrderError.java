package sample.order;

public class OrderError {

    private String orderIDError;
    private String orderDetailIDError;

    public OrderError() {
        this.orderIDError = "";
        this.orderDetailIDError = "";
    }

    public OrderError(String orderIDError, String orderDetailIDError) {
        this.orderIDError = orderIDError;
        this.orderDetailIDError = orderDetailIDError;
    }

    public String getOrderIDError() {
        return orderIDError;
    }

    public void setOrderIDError(String orderIDError) {
        this.orderIDError = orderIDError;
    }

    public String getOrderDetailIDError() {
        return orderDetailIDError;
    }

    public void setOrderDetailIDError(String orderDetailIDError) {
        this.orderDetailIDError = orderDetailIDError;
    }

}
