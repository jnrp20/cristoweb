package com.cristoweb.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cristoweb.backend.entity.Category;
import com.cristoweb.backend.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 🟢 Crear categoría
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // 🟢 Obtener categoría por ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    // 🟢 Obtener todas las categorías
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 🟢 Actualizar categoría
    public Category updateCategory(Long id, Category categoryData) {
        Category category = getCategoryById(id);

        if (categoryData.getName() != null) {
            category.setName(categoryData.getName());
        }

        if (categoryData.getDescription() != null) {
            category.setDescription(categoryData.getDescription());
        }

        if (categoryData.getDenomination() !=null) {
            category.setDenomination(categoryData.getDenomination());
        }

        return categoryRepository.save(category);
    }

    // 🟢 Eliminar categoría
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("No existe categoría con ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

}
