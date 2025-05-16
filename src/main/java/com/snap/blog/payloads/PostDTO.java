package com.snap.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.snap.blog.entities.Category;
import com.snap.blog.entities.Comment;
import com.snap.blog.entities.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDTO category;
	
	private UserDto user;
	
	private Set<CommentDTO> comments = new HashSet<>();
}
