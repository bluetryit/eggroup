package com.post.model;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostService {

	private PostDAO_interface dao;

	public PostService() {
		dao = new PostDAO();
	}

	public PostVO addPost(String post_memno, String post_res_no, String post_text, byte[] post_img, Timestamp post_time,
			Integer post_rate, String post_status) {

		PostVO PostVO = new PostVO();

		PostVO.setPost_memno(post_memno);
		PostVO.setPost_res_no(post_res_no);
		PostVO.setPost_text(post_text);
		PostVO.setPost_img(post_img);
		PostVO.setPost_time(post_time);
		PostVO.setPost_rate(post_rate);
		PostVO.setPost_status(post_status);
		dao.insert(PostVO);
		return PostVO;
	}

	public PostVO updatePost(String post_no, String post_memno, String post_res_no, String post_text, byte[] post_img,
			Timestamp post_time, String post_respon, Integer post_rate, String post_status) {

		PostVO PostVO = new PostVO();

		PostVO.setPost_no(post_no);
		PostVO.setPost_memno(post_memno);
		PostVO.setPost_res_no(post_res_no);
		PostVO.setPost_text(post_text);
		PostVO.setPost_img(post_img);
		PostVO.setPost_time(post_time);
		PostVO.setPost_respon(post_respon);
		PostVO.setPost_rate(post_rate);
		PostVO.setPost_status(post_status);
		dao.update(PostVO);
		return PostVO;
	}

	public void deletePost(String post_no) {
		dao.delete(post_no);
	}

	public PostVO getOnePost(String post_no) {
		return dao.findByPrimaryKey(post_no);
	}

	public List<PostVO> getAll() {
		return dao.getAll();
		
	}
	
	public List<PostVO> getAllOnlinePost() {
		return dao.getAll().stream()
			.filter(post -> post.getPost_status().equals("post1"))
			.collect(Collectors.toList());
	}

}
