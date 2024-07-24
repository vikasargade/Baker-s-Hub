package dto;

public class Product {

    private int productId;
    private String productName;
    private int productQuantity;
    private double productPrice;

    public Product() {
    }

    public Product(String productName, int productQuantity) {
        this.productName = productName;
        this.productQuantity = productQuantity;
    }

    public Product(String productName, int productQuantity, double productPrice) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public Product(int productId, String productName, int productQuantity, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
