package bakeryApplication;

import dao.SeriveImplemention;
import dao.Service;
import dto.User;

import java.net.Socket;
import java.util.Scanner;

public class MainApp {

    static Scanner sc = new Scanner(System.in);
    static Service service = new SeriveImplemention();

    public static void main(String[] args) {

        System.out.println("-------------------------------------------------------");
        System.out.println("***WELCOME TO BAKERY MANAGEMENT SYSTEM***");
        System.out.println("1. LOGIN/ SIGN IN");
        System.out.println("2.SIGN UP");
        System.out.println("3.EXIT");
        System.out.println("--------------------------------------------------------");

        int ch = sc.nextInt();

        switch (ch)
        {
            case 1:
                login();
                break;
            case 2:
                signUp();
                break;
            case 3:
                System.out.println("APPLICATION CLOSED");
                System.exit(0);
                break;
            default:
                System.out.println("INVALID OPTION");
        }
        main(args);

    }

    private static void signUp() {

        System.out.println("ENTER USERNAME: ");
        String userName = sc.next();
        userName = userName.toLowerCase();
        System.out.println("ENTER PASSWORD: ");
        String userPassword = sc.next();
        System.out.println("ENTER ROLE : ");
        String userRole = sc.next();
        User u = new User(userName, userPassword, userRole);
        int n= service.signUp(u);
        System.out.println("--------------------------------------------------------------");
        if(n==1)
            System.out.println("SIGN_UP SUCCESSFUL");
        else
            System.out.println("SIGN_UP FAIL");
    }

    private static void login() {

        System.out.println("ENTER USERNAME: ");
        String userName = sc.next();
        System.out.println("ENTER PASSWORD: ");
        String userPassword = sc.next();
        System.out.println("---------------------------------------------------------------");

        User u  = new User(userName,userPassword);

        String  role = service.login(u);

         if(role.equalsIgnoreCase("admin"))
         {
             AdminMainApp.main(u);
         }
         else if(role.equalsIgnoreCase("customer"))
         {
             CustomerMainApp.main(u);
         }
         else
             System.out.println("INVALID CREDENTIALS");

    }

}
