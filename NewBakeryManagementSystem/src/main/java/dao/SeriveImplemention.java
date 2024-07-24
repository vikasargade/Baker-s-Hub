package dao;

import dto.Order;
import dto.Product;
import dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeriveImplemention implements Service {
    static Connection conn = null ;

    static {
        String url = "jdbc:mysql://localhost:3306/bakerydb";
        String user = "root" ;
        String password = "tiger";

        try {
            conn = DriverManager.getConnection(url , user , password);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    public String login(User u) {

        String role = "";
        int id=0;
        String query = "Select uId , uRole from user_info where uName = ? and uPassword = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,u.getUserName());
            pstmt.setString(2, u.getUserPassword());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                role =rs.getString(2);
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return role;
    }

    @Override
    public int signUp(User u) {
        String query = "INSERT INTO USER_INFO (uName, uPassword, uRole) VALUES (?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, u.getUserName());
            pstmt.setString(2, u.getUserPassword());
            pstmt.setString(3, u.getUserRole());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("PROBLEM OCCURRED IN SIGN_UP");
        }
        return 0;
    }

    @Override
    public int updateProduct(String pName, double pPrice, int pQty) {

        String updateQuery = "UPDATE PRODUCT_INFO SET pPrice = "+pPrice+" , pQuantity="+pQty+" WHERE pName = '"+pName+"'";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(updateQuery);

        } catch (SQLException e) {
            System.out.println("UNABLE TO UPDATE PRODUCT");
        }
        return 0;
    }

    @Override
    public int addProduct(Product p) {

            String addQuery = "INSERT INTO PRODUCT_INFO(pName,pQuantity,pPrice) VALUES(?,?,?)";

            try {
                PreparedStatement pstmt = conn.prepareStatement(addQuery);

                pstmt.setString(1,p.getProductName());
                pstmt.setInt(2,p.getProductQuantity());
                pstmt.setDouble(3,p.getProductPrice());

                return pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("UNABLE TO ADD PRODUCT");
            }
            return 0;
    }

    @Override
    public List<Product> displayAllProduct() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT_INFO";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Product product = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("UNABLE TO SHOW ALL PRODUCTS");
        }
        return productList;
    }

    @Override
    public List<Product> ShowProductById(int pId) {
        List<Product> productList =new ArrayList<>();
        String query = "SELECT * FROM PRODUCT_INFO WHERE pId = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,pId);
            ResultSet rs =pstmt.executeQuery();

            while (rs.next())
            {
                Product product = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("UNABLE TO SHOW PRODUCT BY ID");
        }
        return productList;
    }

    @Override
    public int deleteProduct(int pId) {
        String deleteQuery = "DELETE FROM PRODUCT_INFO WHERE pId = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1 ,pId);
            return  pstmt.executeUpdate() ;
        } catch (SQLException e) {
            System.err.println("UNABLE TO DELETE PRODUCT");
        }
        return 0;
    }

    public boolean placeOrder(Order ord) {
        System.out.println(ord.getOrderId());
        String insertUserProcedure = "{call insertUser(?)}";
        String placeOrderProcedure = "{call placeOrder(? , ? , ?)}";
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(insertUserProcedure);
            cstmt.setInt(1 , ord.getUser().getUserId());
            cstmt.execute() ;
            ResultSet rs = cstmt.getResultSet();
            int ordId = 0 ;
            while (rs.next())
            {
                ordId = rs.getInt(1);
            }
            cstmt = conn.prepareCall(placeOrderProcedure);

            for (Product p : ord.getProductList()) {
                cstmt.setInt(1, ordId);
                cstmt.setString(2, p.getProductName() );
                cstmt.setInt(3 , p.getProductQuantity());
                cstmt.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true ;
    }

    @Override
    public int cancelOrder(Order order, int pId) {

        int deleteProduct  = deleteProduct(pId);
        if(deleteProduct>0)
        {
            String cancelOrder = "delete from order_info where order_id = ?";
            try {
                PreparedStatement pstmt = conn.prepareStatement(cancelOrder);
                pstmt.setInt(1,order.getOrderId());
                return pstmt.executeUpdate();
            }
            catch (SQLException e)
            {
                System.out.println("PROBLEM OCCUR DURING CANCEL ORDER");
            }
        }
        return 0;
    }

    @Override
    public List<Order> displayAllOrder() {
        List<Order> orderList = new ArrayList<>();
        String query = "SELECT * FROM ORDER_INFO";

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Order order = new Order(rs.getInt(1),rs.getDouble(3));
                orderList.add(order);
                return  orderList;
            }
        } catch (SQLException e) {
            System.out.println("UNABLE TO SHOW ALL PRODUCTS");
        }
        return null;
    }
}
