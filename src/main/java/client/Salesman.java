package client;

import Data.InvoiceData;
import dao.InvoiceDAO;
import dao.ProductDAO;
import dao.SaleDAO;
import entities.Invoice;
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
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        Scanner scanner = new Scanner(System.in);
        List<Product> productList = productService.getProductList();

        while (true) {
            productService.displayAllProducts();
            InvoiceData invoiceData = new InvoiceData();
            invoiceData.setTotal(0.0);
            while (true) {
                System.out.println("Give the Id of the next product or 0 to finish.");
                int idProduct = scanner.nextInt();
                if (idProduct == 0) {
                    break;
                }
                Product selectedProduct = productList.stream().filter(p -> p.getId() == idProduct).findFirst().orElse(null);
                if (selectedProduct == null) {
                    System.out.println("Product not found");
                    break;
                }
                Sale savedSale = null;
                if (!invoiceData.getSales().isEmpty()) {
                    savedSale = invoiceData.getSales().stream().filter(sale -> sale.getProductId() == selectedProduct.getId()).findFirst().orElse(null);

                }
                if (savedSale != null && savedSale.getProductId() == selectedProduct.getId()) {
                    System.out.println("Sale already exists");
                    System.out.println("Give the Quantity that you are adding: ");
                    int quantity = scanner.nextInt();
                    if (quantity < 0) {
                        if ((quantity * -1) > savedSale.getQuantity()) {
                            System.out.println("Quantity out of range");
                            System.out.println("Invoice won't be added");
                            break;
                        } else {
                            if (selectedProduct.getQuantity() < quantity + savedSale.getQuantity()) {
                                System.out.println("You only have " + selectedProduct.getQuantity() + " stock");
                                System.out.println("Quantity of the sale will be set to " + selectedProduct.getQuantity());
                                savedSale.setQuantity(selectedProduct.getQuantity());
                            } else {
                                savedSale.setQuantity(savedSale.getQuantity() + quantity);
                            }
                            invoiceData.setTotal(invoiceData.getTotal() + savedSale.getQuantity() * savedSale.getPrice());
                        }
                    }
                } else {
                    Sale newSale = new Sale();
                    newSale.setProductId(selectedProduct.getId());
                    System.out.println("Please enter the quantity: ");
                    int quantity = scanner.nextInt();
                    if (quantity <= 0) {
                        System.out.println("Quantity cannot be negative or 0");
                        break;
                    } else if (selectedProduct.getQuantity() < quantity) {
                        System.out.println("You only have " + selectedProduct.getQuantity() + " stock");
                        System.out.println("Quantity of the sale will be set to " + selectedProduct.getQuantity());
                        newSale.setQuantity(selectedProduct.getQuantity());
                    } else {
                        newSale.setQuantity(quantity);
                    }

                    System.out.println("Please enter the price of the product: ");
                    double price = scanner.nextDouble();
                    if (price < 0) {
                        System.out.println("Price cannot be negative");
                        break;
                    }
                    newSale.setPrice(price);
                    invoiceData.setTotal(invoiceData.getTotal() + newSale.getPrice() * newSale.getQuantity());
                    invoiceData.addSale(newSale);
                }

            }

            if(!invoiceData.getSales().isEmpty()){
                invoiceData.setDate(LocalDateTime.now());
                Invoice invoice = invoiceData.getInvoice();
                invoiceDAO.save(invoice);
                for (Sale sale : invoiceData.getSales()) {
                    Product product = productList.stream().filter(p -> p.getId() == sale.getProductId()).findFirst().orElse(null);
                    product.setQuantity(product.getQuantity() - sale.getQuantity());
                    productDAO.update(product);
                    sale.setInvoiceId(invoice.getId());
                    saleDAO.save(sale);
                }


                System.out.println("Invoice added");
                System.out.println("Sale info: ");
                for (Sale sale : invoiceData.getSales()) {
                    Product product = productList.stream().filter(p -> p.getId() == sale.getProductId()).findFirst().orElse(null);
                    if (product != null) {
                        System.out.println("Product NAME:\t" + product.getName() + "\n" +
                                "Quantity:\t" + sale.getQuantity() + "\n" +
                                "Price:\t" + sale.getPrice() + "\n");
                    }
                }

                System.out.println("Date:\t" + invoice.getDate() + "\n" +
                        "Total Amount:\t" + invoice.getTotal() + "\n");
            }
            System.out.println("Type 0 to stop!");
            int choice = scanner.nextInt();
            if (choice == 0){
                System.out.println("Goodbye!");
                return;
            }
        }
    }
}
