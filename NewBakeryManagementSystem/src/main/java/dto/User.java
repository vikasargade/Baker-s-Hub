package dto;

public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String userRole;

    public User() {
    }

    public User(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    public User(int userId,String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserRole() {
        return userRole;
    }
}
