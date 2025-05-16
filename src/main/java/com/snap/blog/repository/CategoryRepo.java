package com.snap.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snap.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
