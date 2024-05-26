package service;

import controller.ProductController;
import dao.ProductDAO;
import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    public void add() {
        Product newProduct = new Product();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name of the product: ");
        String name = sc.nextLine();
        newProduct.setName(name);

        System.out.println("Please enter type of the product: ");
        String type = sc.nextLine();
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
        productDAO.save(newProduct);
    }

    public void displayAllProducts(){
        List<Product> productsList = productDAO.findAll();
        System.out.println("ID\tName\tType\tQuantity\tPrice");
        for (Product product : productsList) {
            System.out.println(product.toString());
        }
    }

    public void displayProductStockValue() {
        List<Product> products = productDAO.findAll();
        for (Product product : products) {
            double stockValue = product.getQuantity() * product.getPrice();
            System.out.println("Product: " + product.getName() + ", Stock Value: " + stockValue);
        }
    }

    public void notifyLowStock() {
        List<Product> products = productDAO.findAll();
        int lowStockLimit = 5;
        boolean foundLowStock = false;

        for (Product product : products) {
            if (product.getQuantity() <= lowStockLimit) {
                System.out.println("Low stock: " + product.getName() + ", " + product.getQuantity() + " left");
                foundLowStock = true;
            }
        }

        if (!foundLowStock) {
            System.out.println("All products have enough stock.");
        }
    }
        public void deleteProduct() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the name of the product to delete: ");
            String name = sc.nextLine();
            productDAO.deleteProduct(name);
            System.out.println("Product with name \"" + name + "\" has been deleted.");
        }


}
