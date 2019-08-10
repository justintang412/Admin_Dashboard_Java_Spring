package com.yczx.domain;

import java.io.Serializable;

public class TemplateChapter implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, templateId, fileId, sort;
	private String name, description;

	public void clear() {
		id = null;
		templateId = null;
		fileId = null;
		name = null;
		description = null;
		sort = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
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

}
