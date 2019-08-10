package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class BidTaskAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, actor;
	private String actionType, description;
	private Date actionTime;

	public void clear() {
		id = null;
		actor = null;
		actionType = null;
		description = null;
		actionTime = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getActor() {
		return actor;
	}

	public void setActor(Long actor) {
		this.actor = actor;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

}
