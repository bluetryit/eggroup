package com.post.model;

import java.util.List;

public interface PostDAO_interface {
	public void insert(PostVO PostVO);
	public void update(PostVO PostVO);
	public void delete(String mem_no);
	public PostVO findByPrimaryKey(String mem_no);
	public List<PostVO>getAll();

}
