package client;

import dao.ProductDAO;
import dao.SaleDAO;
import entities.Product;
import entities.Sale;
import service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Salesman {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        ProductDAO productDAO = new ProductDAO();
        SaleDAO saleDAO = new SaleDAO();
        Scanner scanner = new Scanner(System.in);
        List<Product> productList = productService.getProductList();
        while (true){
            productService.displayAllProducts();
            System.out.println("Give the Id of the product or 0 to exit.");
            int idProduct = scanner.nextInt();
            if (idProduct == 0){
                System.out.println("Goodbye!");
                return;
            }
            Product selectedProduct = productList.stream().filter(p -> p.getId() == idProduct).findFirst().orElse(null);
            if (selectedProduct == null){
                System.out.println("Product not found");
                break;
            }

            Sale newSale = new Sale();
            newSale.setProductId(selectedProduct.getId());
            System.out.println("Please enter the quantity: ");
            int quantity = scanner.nextInt();
            if (quantity <= 0){
                System.out.println("Quantity cannot be negative or 0");
                break;
            }
            else if (selectedProduct.getQuantity() < quantity){
                System.out.println("You only have " + selectedProduct.getQuantity() + " stock");
                System.out.println("Quantity of the sale will be set to " + selectedProduct.getQuantity());
                newSale.setQuantity(selectedProduct.getQuantity());
            }
            else {
                newSale.setQuantity(quantity);
            }

            System.out.println("Please enter the price of the product: ");
            double price = scanner.nextDouble();
            if (price < 0){
                System.out.println("Price cannot be negative");
                break;
            }
            newSale.setPrice(price);
            newSale.setDate(LocalDateTime.now());

            selectedProduct.setQuantity(selectedProduct.getQuantity() - newSale.getQuantity());
            productDAO.update(selectedProduct);
            saleDAO.save(newSale);

            System.out.println("Sale added");
            System.out.println("Sale info: ");
            System.out.println("Product NAME:\t" + selectedProduct.getName() + "\n" +
                               "Quantity:\t" + newSale.getQuantity() + "\n" +
                               "Price:\t" + newSale.getPrice() + "\n" +
                               "Date:\t" + newSale.getDate() + "\n" +
                               "Total Amount:\t" + newSale.getQuantity() * newSale.getPrice() + "\n");


        }
    }
}
