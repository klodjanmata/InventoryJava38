package service;

import dao.CategoryDAO;
import entities.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    private List<Category> categories;

    public CategoryService() {
        init();
    }

    public void add() {
        Category newCategory = new Category();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the name of the category: ");
        String name = sc.nextLine();
        newCategory.setName(name);

        System.out.println("Please enter the description of the category: ");
        String description = sc.nextLine();
        newCategory.setDescription(description);

        categoryDAO.save(newCategory);
    }

    public void displayAllCategories() {
        List<Category> categories = categoryDAO.findAll(); // Retrieve all categories
        System.out.println("ID\tName\t\t\tDescription");
        for (Category category : categories) {
            System.out.println(category.toString());
        }
    }

    private void init(){
        categories = categoryDAO.findAll();
    }

    public List<Category> getCategories() {
        return categories;
    }
}