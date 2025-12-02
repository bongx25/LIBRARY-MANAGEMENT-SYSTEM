import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // !! IMPORTANT: ADJUST THESE CREDENTIALS !!
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USER = "root";
    private static final String PASS = "your_db_password";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found. Ensure the JAR is in WEB-INF/lib.");
            throw new SQLException("Driver not found.", e);
        }
    }
}