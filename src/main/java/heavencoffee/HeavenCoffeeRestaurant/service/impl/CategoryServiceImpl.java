package heavencoffee.HeavenCoffeeRestaurant.service.impl;

import heavencoffee.HeavenCoffeeRestaurant.model.Category;
import heavencoffee.HeavenCoffeeRestaurant.repository.CategoryRepository;
import heavencoffee.HeavenCoffeeRestaurant.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl (CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(UUID categoryId, Category updatedCategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryCode(updatedCategory.getCategoryCode());
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category with ID " + categoryId + " not found");
        }
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category findById(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
