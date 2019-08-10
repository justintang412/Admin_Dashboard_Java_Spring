package com.yczx.domain;

import java.io.Serializable;

public class MaterialType implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title, description, type;
	private Long valid;

	public void clear() {
		id = null;
		title = null;
		description = null;
		type = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}

}
