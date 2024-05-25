package service;

import controller.ProductController;
import dao.ProductDAO;
import entities.Product;

import java.util.Scanner;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();
    public void add() {
        Product newProduct = new Product();
        Scanner sc = new Scanner(System.in);

//        System.out.println("Enter Product Code: ");
//        int code = sc.nextInt();
//        newProduct.setId(code);

        System.out.println("Please enter the name of the product: ");
        String name = sc.next();
        newProduct.setName(name);

        System.out.println("Please enter type of the product: ");
        String type = sc.next();
        newProduct.setType(type);

        System.out.println("Please enter the quantity of the product: ");
        int quantity = sc.nextInt();
        newProduct.setQuantity(quantity);

        System.out.println("Please enter the price of the product: ");
        double price = sc.nextDouble();
        newProduct.setPrice(price);

        System.out.println("Please enter product category code: ");
        int category = sc.nextInt();
        newProduct.setCategoryId(category);
        sc.close();
        productDAO.save(newProduct);
    }
}
