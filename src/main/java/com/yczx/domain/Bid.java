package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, creator, valid;
	private Long templateId  = new Long(0);
	private String name, manager, description, status, editor;
	private Date startTime, endTime, createTime;

	private boolean templateIdChanged;

	public void clear() {
		id = null;
		templateId = null;
		creator = null;
		valid = null;
		name = null;
		manager = null;
		description = null;
		status = null;
		startTime = null;
		endTime = null;
		createTime = null;
		editor = null;
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
		if (this.templateId != null) {
			this.templateIdChanged = !(this.templateId.longValue()==templateId.longValue());
		}
		this.templateId = templateId;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public boolean isTemplateIdChanged() {
		return templateIdChanged;
	}


}
