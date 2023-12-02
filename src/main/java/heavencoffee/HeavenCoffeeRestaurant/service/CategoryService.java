package heavencoffee.HeavenCoffeeRestaurant.service;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public interface CategoryService {
    List<Category> findAllCategories();
    Category findById(UUID categoryId);
    Category saveCategory(Category category);
    Category updateCategory(UUID categoryId, Category updatedCategory);
    void deleteCategory(UUID categoryId);
}
