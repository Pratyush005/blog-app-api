package com.snap.blog.service;

import java.util.List;

import com.snap.blog.entities.Post;
import com.snap.blog.payloads.PostDTO;
import com.snap.blog.payloads.PostResponse;

public interface PostService {

	//create post
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	//update
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get post
	PostDTO getPostById(Integer postId);
	
	//get all post by category
	List<PostDTO> getAllPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDTO> getPostByUser(Integer userId);
	
	//search post
	List<PostDTO> searchPost(String keyword);
	
}
