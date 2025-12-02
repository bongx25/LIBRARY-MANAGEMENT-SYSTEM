import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    public List<Item> searchItems(String nameQuery, String authorQuery) {
        List<Item> results = new ArrayList<>();
        String sql = "SELECT serial_no, name, author_name, status FROM ITEMS WHERE 1=1 ";
        if (nameQuery != null && !nameQuery.trim().isEmpty()) {
            sql += " AND name LIKE ?";
        }
        if (authorQuery != null && !authorQuery.trim().isEmpty()) {
            sql += " AND author_name LIKE ?";
        }
        sql += " AND status = 'Available' ORDER BY name";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;
            if (nameQuery != null && !nameQuery.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + nameQuery.trim() + "%");
            }
            if (authorQuery != null && !authorQuery.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + authorQuery.trim() + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Item(
                        rs.getString("serial_no"),
                        rs.getString("name"),
                        rs.getString("author_name"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}