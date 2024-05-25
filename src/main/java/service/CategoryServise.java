package service;

import dao.CategoryDAO;
import entities.Category;

import java.util.Scanner;

public class CategoryServise {
    private CategoryDAO categoryDAO=new CategoryDAO();
    public void add(){
        Category newCategory=new Category();
        Scanner scanner=new Scanner(System.in);



        System.out.println("Please entre name of category :");
        String name=scanner.next();
        newCategory.setName(name);

        System.out.println("Please entre the description : ");
        String description=scanner.next();
        newCategory.setDescription(description);

    }
}
