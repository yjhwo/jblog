package com.estsoft.jblog.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class PostVO {

	private Long post_id;
	private Long category_id;
	@NotEmpty
	private String title;
	private String content;
	private String reg_date;
	
	public PostVO(){	}

	public PostVO(Long post_id, Long category_id, String title, String content, String reg_date) {
		this.post_id = post_id;
		this.category_id = category_id;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
	}

	
	public PostVO(Long category_id, String title, String content) {
		this.category_id = category_id;
		this.title = title;
		this.content = content;
	}
	
	public PostVO(Long category_id, Long post_id) {
		this.category_id = category_id;
		this.post_id = post_id;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "PostVO [post_id=" + post_id + ", category_id=" + category_id + ", title=" + title + ", content="
				+ content + ", reg_date=" + reg_date + "]";
	}
	
}
