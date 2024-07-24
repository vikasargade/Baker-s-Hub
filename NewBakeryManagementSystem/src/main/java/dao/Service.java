package dao;

import dto.Order;
import dto.Product;
import dto.User;

import java.util.ArrayList;
import java.util.List;

public interface Service {
        List<Product> displayAllProduct() ;
        //List<Product> displayProductByName(String productName );

        String login(User u);

    int signUp(User u);

    int addProduct(Product p);

    int updateProduct(String pName, double pPrice, int pQty);

    int deleteProduct(int pId);

    List<Product> ShowProductById(int pId);

    boolean placeOrder(Order ord);

    int cancelOrder(Order order,int productId);

    List<Order> displayAllOrder();
}

