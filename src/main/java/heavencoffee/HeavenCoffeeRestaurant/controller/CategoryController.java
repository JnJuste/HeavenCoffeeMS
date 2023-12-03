package heavencoffee.HeavenCoffeeRestaurant.controller;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import heavencoffee.HeavenCoffeeRestaurant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //List all Categories
    @GetMapping
    public String createCategoryForm(Model model){
        Category category = new Category();
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("category", category);
        return "Category/Category";
    }

    //Save a new Category
    @PostMapping("/new")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    //Find Category by ID
    @GetMapping("/{categoryId}/edit")
    public String editCategoryForm(@PathVariable UUID categoryId, Model model) {
        Category category = categoryService.findById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("categoryId", categoryId); // Add this line to pass categoryId to the view
        return "Category/EditCategory";
    }


    // Update Category
    @PostMapping("/{categoryId}/update")
    public String updateCategory(@PathVariable UUID categoryId, @ModelAttribute("category") Category updatedCategory, RedirectAttributes redirectAttributes) {
        try {
            categoryService.updateCategory(categoryId, updatedCategory);
            return "redirect:/categories";
        } catch (RuntimeException e) {
            // Handle the exception, e.g., log it or add an error message to the redirect attributes
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/categories";
        }
    }


    //Delete Category
    @GetMapping("/{categoryId}/delete")
    public String deleteCategory(@PathVariable UUID categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/categories";
    }
}
