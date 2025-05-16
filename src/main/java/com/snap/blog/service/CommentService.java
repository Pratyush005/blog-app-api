package com.snap.blog.service;

import com.snap.blog.payloads.CommentDTO;

public interface CommentService {
	
	//create comment
	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	//delete comment
	void deleteComment(Integer commentId);
}
