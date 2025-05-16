package com.snap.blog.service.impl;

import com.snap.blog.entities.Category;
import com.snap.blog.entities.User;
import com.snap.blog.exception.ResourceNotFoundException;
import com.snap.blog.payloads.CategoryDTO;
import com.snap.blog.repository.CategoryRepo;
import com.snap.blog.repository.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ModelMapper modelMapper;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocking

        categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1);
        categoryDTO.setCategoryTitle("Travel application");
        categoryDTO.setCategoryDescription("This is Travel application");

        category = new Category();
        category.setCategoryId(1);
        category.setCategoryTitle("Travel application");
        category.setCategoryDescription("This is Travel application");



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCategory() {
        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        when(categoryRepo.save(any(Category.class))).thenReturn(category);
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.createCategory(categoryDTO);

        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Travel application", result.getCategoryTitle());
        assertEquals("This is Travel application", result.getCategoryDescription()); // The password is encoded before saving

    }

    @Test
    void updateCategory() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(2);
        categoryDTO.setCategoryTitle("Food application");
        categoryDTO.setCategoryDescription("This is food vlogging application");

        Category updatedCategory = new Category();
        updatedCategory.setCategoryId(2);
        updatedCategory.setCategoryTitle("Food application");
        updatedCategory.setCategoryDescription("This is food vlogging application");

        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepo.save(any(Category.class))).thenReturn(updatedCategory);
        when(modelMapper.map(updatedCategory, CategoryDTO.class)).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.updateCategory(categoryDTO, 1);

        assertNotNull(result);
        assertEquals(2, result.getCategoryId());
        assertEquals("Food application", result.getCategoryTitle());
        assertEquals("This is food vlogging application", result.getCategoryDescription());
    }

    @Test
    void getCategoryById() {

        when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
        when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.getCategoryById(1);

        assertNotNull(result);
        assertEquals(1, result.getCategoryId());
        assertEquals("Travel application", result.getCategoryTitle());
        assertEquals("This is Travel application", result.getCategoryDescription());
    }

    @Test
    void getCategoryNotFound() {
        when(categoryRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1));
    }

    @Test
    void deleteCategory() {

        when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

        // When
        categoryService.deleteCategory(1);
        // Then
        verify(categoryRepo, times(1)).delete(category);
    }
}