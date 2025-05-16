package com.snap.blog.payloads;

import java.util.List;

import com.snap.blog.entities.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalSize;
	private boolean isLastPage;

}
