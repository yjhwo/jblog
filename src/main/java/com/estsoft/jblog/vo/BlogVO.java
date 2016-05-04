package com.estsoft.jblog.vo;

public class BlogVO {
	private Long blog_id;
	private String user_id;
	private String title;
	private String logo;
	
	public BlogVO(){	}

	public BlogVO(Long blog_id, String user_id, String title, String logo) {
		this.blog_id = blog_id;
		this.user_id = user_id;
		this.title = title;
		this.logo = logo;
	}
	
	public Long getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String toString() {
		return "BlogVO [blog_id=" + blog_id + ", user_id=" + user_id + ", title=" + title + ", logo=" + logo + "]";
	}
	
	
}
