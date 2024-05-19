package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryjava38", "root", "root");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
