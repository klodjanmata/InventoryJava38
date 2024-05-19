package controller;

import dbconnection.DBConnection;
import entities.Product;

import java.sql.Connection;
import java.sql.Statement;

public class ProductController {
    Connection conn;

    public void insert(Product product) {
        try {
            conn = DBConnection.connect();
            String query = "Insert into product values( "
                    + product.getId() + ", '"
                    + product.getName() + "', '"
                    + product.getType() + "', "
                    + product.getQuantity() + ", "
                    + product.getPrice() + ", "
                    + product.getCategoryId() + ");";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);




        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public ProductController() {
    }
}
