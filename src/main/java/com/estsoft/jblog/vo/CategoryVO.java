package com.estsoft.jblog.vo;

public class CategoryVO {
	private Long category_id;
	private Long blog_id;
	private String name;
	private String description;
	private Long post_count;
	
	public CategoryVO(){	}

	public CategoryVO(Long category_id, Long blog_id, String name, String description, Long post_count) {
		this.category_id = category_id;
		this.blog_id = blog_id;
		this.name = name;
		this.description = description;
		this.post_count = post_count;
	}

	public CategoryVO(Long blog_id, String name, String description, Long post_count) {
		this.blog_id = blog_id;
		this.name = name;
		this.description = description;
		this.post_count = post_count;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Long blog_id) {
		this.blog_id = blog_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPost_count() {
		return post_count;
	}

	public void setPost_count(Long post_count) {
		this.post_count = post_count;
	}

	@Override
	public String toString() {
		return "CategoryVO [category_id=" + category_id + ", blog_id=" + blog_id + ", name=" + name + ", description="
				+ description + ", post_count=" + post_count + "]";
	}
	
}
