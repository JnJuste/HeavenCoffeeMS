package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {
    List<Category> findAllCategories();
    Category saveCategory(Category category);
    Category findById(UUID categoryId);
    Category updateCategory(UUID categoryId, Category updatedCategory);
    void deleteCategory(UUID categoryId);
}
