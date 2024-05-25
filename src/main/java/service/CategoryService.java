package service;

import dao.CategoryDAO;
import entities.Category;

import java.util.Scanner;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();
    public void add() {
        Category newCategory = new Category();
        Scanner scanner = new  Scanner(System.in);
        System.out.println("Plese enter the name of category");
        String name = scanner.next();
        newCategory.setName(name);
        System.out.println("Please enter the discription");
        String discription = scanner.next();
        newCategory.setName(discription);

        categoryDAO.save(newCategory);
        scanner.close();
    }
}
