
public class User {
    private String userId;
    private String name;
    private boolean isAdmin;

    public User(String userId, String name, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public boolean isAdmin() { return isAdmin; }
}