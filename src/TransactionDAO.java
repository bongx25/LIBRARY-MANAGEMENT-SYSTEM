
import java.sql.*;
import java.time.LocalDate;

public class TransactionDAO {
    public boolean issueBook(String serialNo, int memberId, LocalDate issueDate, LocalDate returnDate) {
        String sqlInsert = "INSERT INTO TRANSACTIONS (serial_no, membership_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        String sqlUpdateItem = "UPDATE ITEMS SET status = 'Issued' WHERE serial_no = ?";
        
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // 1. Insert Transaction
            try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
                stmtInsert.setString(1, serialNo);
                stmtInsert.setInt(2, memberId);
                stmtInsert.setDate(3, Date.valueOf(issueDate));
                stmtInsert.setDate(4, Date.valueOf(returnDate));
                stmtInsert.executeUpdate();
            }

            // 2. Update Item Status
            try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateItem)) {
                stmtUpdate.setString(1, serialNo);
                stmtUpdate.executeUpdate();
            }

            conn.commit(); // Commit both changes
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            return false;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}