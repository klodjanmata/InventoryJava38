package service;

import controller.ProductController;
import dao.ProductDAO;
import entities.Category;
import entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductService {

    private List<Product> products;

    private ProductDAO productDAO = new ProductDAO();
    private CategoryService categoryService = new CategoryService();

    public ProductService() {
        init();
    }

    public void add() {
        Product newProduct = new Product();
        Scanner sc = new Scanner(System.in);

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
        productDAO.save(newProduct);
        init();
    }

    public void displayAllProducts(){
        products = productDAO.findAll();
        System.out.println("ID\tName\tType\tQuantity\tPrice");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    public void displayProductStockValue() {
        init();
        for (Product product : products) {
            double stockValue = product.getQuantity() * product.getPrice();
            System.out.println("Product: " + product.getName() + ", Stock Value: " + stockValue);
        }
    }

    public void notifyLowStock() {
        init();
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

    public void changeProduct(){

        displayAllProducts();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the id of the product: ");
        int idProduct = sc.nextInt();
        Product selectedProduct = null;
        selectedProduct = products.stream().filter(p -> p.getId() == idProduct).findFirst().orElse(null);
        if (selectedProduct == null){
            System.out.println("Product not found");
            return;
        }
        System.out.println("Selected Product: \n" + "ID:\t" + selectedProduct.getId() + "\n" +
                                                    "Name:\t" + selectedProduct.getName() + "\n" +
                                                    "Type:\t" + selectedProduct.getType() + "\n" +
                                                    "Quantity:\t" + selectedProduct.getQuantity() + "\n" +
                                                    "Price:\t"+ selectedProduct.getPrice() + "\n" +
                                                    "Cat ID:\t" + selectedProduct.getCategoryId());

        System.out.print("Please enter the name of the product: ");
        String name = sc.next();
        selectedProduct.setName(name);

        System.out.print("Please enter type of the product: ");
        String type = sc.next();
        selectedProduct.setType(type);

        System.out.print("Please enter the quantity of the product: ");
        int quantity = sc.nextInt();
        selectedProduct.setQuantity(quantity);

        System.out.print("Please enter the price of the product: ");
        double price = sc.nextDouble();
        selectedProduct.setPrice(price);

        System.out.print("Please enter product category code: ");
        int category = sc.nextInt();
        selectedProduct.setCategoryId(category);
        productDAO.update(selectedProduct);
    }

    public void deleteProduct(){
        displayAllProducts();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the id of the product to delete: ");
        int idProduct = sc.nextInt();
        Product selectedProduct = null;
        selectedProduct = products.stream().filter(p -> p.getId() == idProduct).findFirst().orElse(null);
        if (selectedProduct == null){
            System.out.println("Product not found");
            return;
        }
        productDAO.delete(selectedProduct);

    }

    public void findProductsByCategory() {
        init();
        categoryService.displayAllCategories();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the category ID to search for products: ");
        int categoryId = sc.nextInt();
        List<Product> productsCategory;// = productDAO.findByCategory(categoryId);
//         Finding by searching on the list

        productsCategory = products.stream().filter(product -> product.getCategoryId() == categoryId).collect(Collectors.toList());

        System.out.println("ID\tName\tType\tQuantity\tPrice");
        for (Product product : productsCategory) {
            System.out.println(product.toString());
        }
    }

    private void init(){
        products = new ArrayList<>();
        products = productDAO.findAll();
    }

    public List<Product> getProductList(){
        return products;
    }

}
