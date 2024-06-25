package com.enviro.grad001.mduduzijelemduduzi.service;

import com.enviro.grad001.mduduzijelemduduzi.dto.payload.request.CategoryRequestDto;
import com.enviro.grad001.mduduzijelemduduzi.dto.payload.response.CategoryResponseDto;
import com.enviro.grad001.mduduzijelemduduzi.exception.CategoryNotFoundException;
import com.enviro.grad001.mduduzijelemduduzi.model.Category;
import com.enviro.grad001.mduduzijelemduduzi.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponseDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryResponseDto(category.getId(), category.getName()))
                .collect(Collectors.toList());
    }

    public Category getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found"));
        return category;
    }

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto categoryDetails){
        if(categoryDetails.getName() == null || categoryDetails.getName().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        Category createdCategory = categoryRepository.save(new Category(categoryDetails.getName()));
        return new CategoryResponseDto(createdCategory.getId(), createdCategory.getName());
    }

    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryDetails){
        if(categoryDetails.getName() == null || categoryDetails.getName().isEmpty()){
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id "+ id));
        category.setName(categoryDetails.getName());
        Category updatedCategory = categoryRepository.save(category);
        return new CategoryResponseDto(updatedCategory.getId(), updatedCategory.getName());
    }

    @Transactional
    public void deleteCategory(Long id){
        if(!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException("Category not found with id " + id);
        }
        categoryRepository.deleteById(id);
    }

}
