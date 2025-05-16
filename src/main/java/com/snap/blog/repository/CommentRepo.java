package com.snap.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snap.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
