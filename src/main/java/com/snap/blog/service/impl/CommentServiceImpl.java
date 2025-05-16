package com.snap.blog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snap.blog.entities.Comment;
import com.snap.blog.entities.Post;
import com.snap.blog.exception.ResourceNotFoundException;
import com.snap.blog.payloads.CommentDTO;
import com.snap.blog.repository.CommentRepo;
import com.snap.blog.repository.PostRepo;
import com.snap.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setContent(commentDTO.getContent());
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
		this.commentRepo.delete(comment);
		
	}

}
