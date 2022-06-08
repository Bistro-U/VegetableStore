package sample.product;

import sample.user.*;

public class ProductError {

    private String productIDError;
    private String productNameError;
    private String imageUrlError;
    private String priceError;
    private String quantityError;
    private String categoryIDError;
    private String importDateError;
    private String usingDateError;
    private boolean status;

    public ProductError() {
        this.productIDError = "";
        this.productNameError = "";
        this.imageUrlError = "";
        this.priceError = "";
        this.quantityError = "";
        this.categoryIDError = "";
        this.importDateError = "";
        this.usingDateError = "";
        this.status = true;
    }

    public ProductError(String productIDError, String productNameError, String imageUrlError, String priceError, String quantityError, String categoryIDError, String importDateError, String usingDateError, boolean status) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.imageUrlError = imageUrlError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.categoryIDError = categoryIDError;
        this.importDateError = importDateError;
        this.usingDateError = usingDateError;
        this.status = status;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getImageUrlError() {
        return imageUrlError;
    }

    public void setImageUrlError(String imageUrlError) {
        this.imageUrlError = imageUrlError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public String getUsingDateError() {
        return usingDateError;
    }

    public void setUsingDateError(String usingDateError) {
        this.usingDateError = usingDateError;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
