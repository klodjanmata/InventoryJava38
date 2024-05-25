package service;

import dao.CategoryDAO;
import entities.Category;
import java.util.Scanner;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public void add() {
        Category newCategory = new Category();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name of the category: ");
        String name = sc.next();
        newCategory.setName(name);

        System.out.println("Please enter the description of the category: ");
        String description = sc.next();
        newCategory.setDescription(description);

        categoryDAO.save(newCategory);
        sc.close();
    }
}