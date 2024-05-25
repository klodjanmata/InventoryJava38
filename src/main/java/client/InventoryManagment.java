package client;

import service.ProductService;

import java.util.Scanner;

public class InventoryManagment {

    public static void printOptions(){
        System.out.println("\n\nInventory Manager\n" +
                "Chose an option:\n");
        System.out.println("1. Add Product");
        System.out.println("2. Display Products");
        System.out.println("3. Notify low stock");
        System.out.println("4. Add Category");
        System.out.println("5. Display Categories");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

    }

    public static void main(String[] args) {
        ProductService productService = new ProductService();
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
                    //displayAllProducts();
                    break;
                case 3:
                    System.out.println("Notifying low stock:");
                    //notifyLowStock()
                    break;
                case 4:
                    System.out.println("Adding Category:");
                    //addCategory()
                    break;
                case 5:
                    System.out.println("Displaying Categories:");
                    //displayCategories()
                    break;
                case 0:
                    System.out.println("Exiting....");
                    return;
                default:
                    System.out.println("Invalid choice");
                    sc.close();
            }
        }


    }
}