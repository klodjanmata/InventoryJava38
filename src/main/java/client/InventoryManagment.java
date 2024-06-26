package client;

import service.CategoryService;
import service.ProductService;

import java.util.Scanner;

public class InventoryManagment {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        CategoryService categoryService = new CategoryService();
        Scanner sc = new Scanner(System.in);
        while(true){
            printOptions();
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    productService.add();
                    break;
                case 2:
                    System.out.println("Displaying Products:");
                    productService.displayAllProducts();
                    break;
                case 3:
                    System.out.println("Notifying low stock:");
                    productService.notifyLowStock();
                    break;
                case 4:
                    System.out.println("Adding Category:");
                    categoryService.add();
                    break;
                case 5:
                    System.out.println("Displaying Categories:");
                    categoryService.displayAllCategories();
                    break;
                case 6:
                    System.out.println("Display the full value of product stock: ");
                    productService.displayProductStockValue();
                    break;
                case 7:
                    System.out.println("Change Product:");
                    productService.changeProduct();
                    break;
                case 8:
                    System.out.println("Deleting Product:");
                    productService.deleteProduct();
                    break;
                case 9:
                    System.out.println("Displaying products in categories:");
                    productService.findProductsByCategory();
                    break;
                case 0:
                    System.out.println("Exiting....");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void printOptions(){
        System.out.println("\n\nInventory Manager\n" +
                "Chose an option:\n");
        System.out.println("1. Add Product");
        System.out.println("2. Display Products");
        System.out.println("3. Notify low stock");
        System.out.println("4. Add Category");
        System.out.println("5. Display Categories");
        System.out.println("6. Display product stock value");
        System.out.println("7. Change Product");
        System.out.println("8. Delete Product");
        System.out.println("9. Display products in categories");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

    }
}
