package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    public static Connection connect(String[] args) throws SQLException {
        try(
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventoryjava38", "root", "root")){
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
