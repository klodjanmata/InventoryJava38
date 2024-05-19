package dbconnection;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryjava38", "root", "root");
             Statement statement = connection.createStatement()) {

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM Category");
        }
    }
}
