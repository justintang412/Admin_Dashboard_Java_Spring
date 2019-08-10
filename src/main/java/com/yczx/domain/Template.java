package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class Template implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, templateType, fileId, creator, valid;
	private String name, description;
	private Date createTime;

	public void clear() {
		id = null;
		fileId = null;
		templateType = null;
		valid = null;
		name = null;
		description = null;
		creator = null;
		createTime = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Long templateType) {
		this.templateType = templateType;
	}

}
