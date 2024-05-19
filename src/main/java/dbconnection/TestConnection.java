package dbconnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.connect();
            if (conn != null) {
                System.out.println("Connection established successfully!");

                // Execute a simple SELECT query
                String query = "SELECT * FROM Category";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                // Print out the results
                while (rs.next()) {
                    // Assuming Product table has columns: id, name, price
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    System.out.println("ID: " + id + ", Name: " + name + ", Price: " + description);
                }
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while trying to connect to the database or execute the query.");
        } finally {
            // Close the ResultSet, Statement, and Connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
