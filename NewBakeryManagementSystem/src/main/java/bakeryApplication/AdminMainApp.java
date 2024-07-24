package bakeryApplication;

import dao.SeriveImplemention;
import dao.Service;
import dto.Product;
import dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMainApp {

    static Scanner sc = new Scanner(System.in);
    static Service service = new SeriveImplemention();
    public static void main(User user) {
        System.out.println("THIS IS ADMIN PAGE "+ user.getUserName().toUpperCase());


        System.out.println("--------------------------------------------------------------");
        System.out.println("***WELCOME TO ADMIN SECTION***\n***ENTER YOUR CHOICE***");
        System.out.println("1.ADD PRODUCT");
        System.out.println("2.UPDATE PRODUCT");
        System.out.println("3.DELETE PRODUCT");
        System.out.println("4.SHOW PRODUCT BY ID");
        System.out.println("5.DISPLAY ALL PRODUCT");
        System.out.println("6.EXIT");
        System.out.println("--------------------------------------------------------------");

        int ch = sc.nextInt();

        switch (ch)
        {
            case 1:
                addProduct();
                break;
            case 2:
                updateProduct();
                break;
            case 3:
                deleteProduct();
                break;
            case 4:
                showProductById();
                break;
            case 5:
                displayAllProduct();
                break;
            case 6:
                System.out.println("ADMIN SECTION EXITED");
                return;
            default:
                System.out.println("INVALID CHOICE");
        }
        main(user);
    }

    private static void displayAllProduct() {
        List<Product> list = service.displayAllProduct();

        System.out.printf("%-18s %-20s %-20s %-15s%n", "ProductId", "Product Name", "product Qty","Product Price");

        for(Product p:list)
            System.out.println(p.getProductId()+"                   "+p.getProductName()+"                  "
                    +p.getProductQuantity()+"              "+p.getProductPrice());

    }

    private static void addProduct() {

        System.out.println("ENTER PRODUCT NAME");
        String pName = sc.nextLine();
        pName = sc.nextLine();
        System.out.println("ENTER PRODUCT QUANTITY "+pName.toUpperCase());
        int pQty = sc.nextInt();
        System.out.println("ENTER PRODUCT PRICE");
        double pPrice = sc.nextDouble();

        Product p = new Product(pName,pQty,pPrice);
        int n =service.addProduct(p);
        System.out.println("--------------------------------------------------------------");
        if(n==1)
            System.out.println(n+" PRODUCT ADDED SUCCESSFULLY");
        else
            System.out.println("PRODUCT NOT ADDED");
    }

    private static void updateProduct() {
        System.out.println("ENTRE PRODUCT NAME");
        String pName = sc.nextLine();
        pName = sc.nextLine();
        System.out.println("ENTER NEW QUANTITY OF "+pName.toUpperCase());
        int pQty = sc.nextInt();
        System.out.println("ENTER NEW PRICE OF "+pName.toUpperCase());
        double pPrice = sc.nextDouble();

        int n = service.updateProduct(pName,pPrice,pQty);
        System.out.println("--------------------------------------------------------------");
        if(n==1)
            System.out.println(n+" PRODUCT UPDATED SUCCESSFULLY");
        else
            System.out.println("PRODUCT NOT UPDATED");
    }

    private static void deleteProduct() {
        System.out.println("ENTER PRODUCT ID FOR DELETE PRODUCT");
        int pId = sc.nextInt();

        int n = service.deleteProduct(pId);
        System.out.println("--------------------------------------------------------------");
        if(n==1)
            System.out.println(n+" PRODUCT DELETED SUCCESSFULLY");
        else
            System.out.println("PRODUCT NOT DELETED");
    }

    private static void showProductById() {
        System.out.println("ENTER PRODUCT ID");
        int pId = sc.nextInt();

        List<Product> products = service.ShowProductById(pId);
        System.out.printf("%-18s %-20s %-20s %-15s%n", "ProductId", "Product Name", "product Qty","Product Price");

        for(Product p:products)
            System.out.println("%-18s %-20s %-20s %-15s%n"+p.getProductId()+" "+p.getProductName()+" "+p.getProductQuantity()+" "+p.getProductPrice());
    }

}
