package com.snap.blog.repository;

import java.util.List;
import com.snap.blog.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snap.blog.entities.Post;
import com.snap.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> seachByTitle(@Param("key") String title);

}
