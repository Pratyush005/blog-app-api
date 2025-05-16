package com.snap.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snap.blog.payloads.ApiResponse;
import com.snap.blog.payloads.CategoryDTO;
import com.snap.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//CREATE
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO category = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
	}
	
	//UPDATE
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
		CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
	}
	
	
	//GET ALL
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDTO>>(allCategory, HttpStatus.OK);
	}
	
	//GET
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer categoryId) {
		CategoryDTO categoryById = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryById, HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted successfully...", true), HttpStatus.OK);
	}

}
