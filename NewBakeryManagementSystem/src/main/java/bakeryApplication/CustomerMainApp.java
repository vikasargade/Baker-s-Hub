package bakeryApplication;

import dao.SeriveImplemention;
import dao.Service;
import dto.Order;
import dto.Product;
import dto.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMainApp {
    static int n = 0;

    static Scanner sc =  new Scanner(System.in);
    static Service service = new SeriveImplemention();
    public static void main(User u) {
        if (n == 0) {
            System.out.println("WELCOME, " + u.getUserName().toUpperCase() + " TO OUR BAKERY");
            n++;
        }


        System.out.println("\nSELECT OPERATION");
        System.out.println("1. DISPLAY ALL PRODUCTS");
        System.out.println("2. PLACE ORDER ");
        System.out.println("3. CANCEL ORDER ");
        System.out.println("4. DISPLAY ALL ORDERS ");
        System.out.println("5. SIGN OUT ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                displayAllProducts();
                break;
            case 2:
                placeOrder(u);
                break;
            case 3:
                // cancelOrder() ;
                break;
            case 4:
              //  displayAllOrders();
                break;
            case 5:
                u = null;
                return;
            default:
                System.out.println("INVALID INPUT ");
        }

        main(u);
    }


    private static void displayAllProducts() {
        List<Product> productList = service.displayAllProduct();

        System.out.println("NAME  \t\t  PRICE ");
        for (Product p : productList) {
            System.out.println(p.getProductName() + "\t  " + p.getProductPrice());
        }
    }

    private static void placeOrder(User user) {

        Order ord = new Order(user);

        do {
            System.out.println("ENTER PRODUCT NAME ");
            String productName = sc.next();
            System.out.println("ENTER ORDER QTY ");
            int orderQty = sc.nextInt();
            Product product = new Product(productName, orderQty);
            ord.addProduct(product);

            System.out.println("ADD MORE PRODUCT (Y/N)");
            char ch = sc.next().charAt(0);
            if (ch == 'n' || ch == 'N')
                break;

        } while (true);

        boolean status = false;
        status = service.placeOrder(ord);
        if (status)
            System.out.println("ORDER PLACED !!");
        else
            System.out.println("ORDER NOT PLACED !!");

    }




}
