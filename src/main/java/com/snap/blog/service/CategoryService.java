package com.snap.blog.service;

import java.util.List;

import com.snap.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	//create
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	//update
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
	
	//get all
	List<CategoryDTO> getAllCategory();
	
	//get
	CategoryDTO getCategoryById(Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);

}
