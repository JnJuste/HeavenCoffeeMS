package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import heavencoffee.HeavenCoffeeRestaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //List all Categories
    @GetMapping("/categories")
    public String createCategoryForm(Model model){
        Category category = new Category();
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "Category/Category";
    }

    //Save a new Category
    @PostMapping("/categories/new")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    //Find Category by ID
    @GetMapping("/categories/edit/{categoryId}")
    public String editCategoryForm(@PathVariable UUID categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        return "Category/EditCategory";
    }

    //Update Category
    @PostMapping("/categories/update/{categoryId}")
    public String updateCategory(@PathVariable UUID categoryId, @ModelAttribute("category") Category updatedCategory) {
        categoryService.updateCategory(categoryId, updatedCategory);
        return "redirect:/categories";
    }

    //Delete Category
    @GetMapping("/categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories";
    }
}
