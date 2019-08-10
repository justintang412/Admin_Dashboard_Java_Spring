package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class BidFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, uploader, dataStatus;
	private String name, description, path;
	private Date uploadTime;

	public void clear() {
		id = null;
		uploader = null;
		name = null;
		description = null;
		path = null;
		uploadTime = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUploader() {
		return uploader;
	}

	public void setUploader(Long uploader) {
		this.uploader = uploader;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Long getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Long dataStatus) {
		this.dataStatus = dataStatus;
	}

}
