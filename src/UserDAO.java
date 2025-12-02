
import java.sql.*;

public class UserDAO {
    public User authenticateUser(String userId, String password) {
        String sql = "SELECT name, is_admin FROM USERS WHERE user_id = ? AND password = ? AND is_active = TRUE";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userId);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    return new User(userId, name, isAdmin); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}