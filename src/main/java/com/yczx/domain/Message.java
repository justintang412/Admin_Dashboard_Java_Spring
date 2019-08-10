package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title, message;
	private Long creator, toUser;
	private Long toPosition;
	private Date createTime;
	
	public void clear() {
		id = null;
		title = null;
		message = null;
		creator  = null;
		toUser = null;
		toPosition = null;
		createTime = null;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getToUser() {
		return toUser;
	}

	public void setToUser(Long toUser) {
		this.toUser = toUser;
	}

	public Long getToPosition() {
		return toPosition;
	}

	public void setToPosition(Long toPosition) {
		this.toPosition = toPosition;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
