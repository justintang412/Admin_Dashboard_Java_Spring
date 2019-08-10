package com.yczx.form;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class BidForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long bidId, bidTemplateId;
	private ArrayList<Long> bidManager;
	private String bidName, bidDescription;
	private Date bidStartTime, bidEndTime;

	private Long taskId, taskChapterId, taskApprover;
	private ArrayList<Long> taskEditorIds;
	private String taskSubmitComment, taskApproveComment, taskApproveStatus;
	private Date taskStartTime, taskEndTime;

	private Long fileId;
	private String fileDescription;

	private String submitDescription;

	public void clear() {
		bidId = null;
		bidTemplateId = null;
		bidManager = null;
		bidName = null;
		bidDescription = null;
		bidStartTime = null;
		bidEndTime = null;
		taskId = null;
		taskChapterId = null;
		taskApprover = null;
		taskEditorIds = null;
		taskSubmitComment = null;
		taskApproveComment = null;
		taskApproveStatus = null;
		taskStartTime = null;
		taskEndTime = null;
		fileId = null;
		fileDescription = null;
		submitDescription = null;
	}

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public Long getBidTemplateId() {
		return bidTemplateId;
	}

	public void setBidTemplateId(Long bidTemplateId) {
		this.bidTemplateId = bidTemplateId;
	}

	public ArrayList<Long> getBidManager() {
		return bidManager;
	}

	public void setBidManager(ArrayList<Long> bidManager) {
		this.bidManager = bidManager;
	}

	public String getBidName() {
		return bidName;
	}

	public void setBidName(String bidName) {
		this.bidName = bidName;
	}

	public String getBidDescription() {
		return bidDescription;
	}

	public void setBidDescription(String bidDescription) {
		this.bidDescription = bidDescription;
	}

	public Date getBidStartTime() {
		return bidStartTime;
	}

	public void setBidStartTime(Date bidStartTime) {
		this.bidStartTime = bidStartTime;
	}

	public Date getBidEndTime() {
		return bidEndTime;
	}

	public void setBidEndTime(Date bidEndTime) {
		this.bidEndTime = bidEndTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getTaskChapterId() {
		return taskChapterId;
	}

	public void setTaskChapterId(Long taskChapterId) {
		this.taskChapterId = taskChapterId;
	}

	public Long getTaskApprover() {
		return taskApprover;
	}

	public void setTaskApprover(Long taskApprover) {
		this.taskApprover = taskApprover;
	}

	public ArrayList<Long> getTaskEditorIds() {
		return taskEditorIds;
	}

	public void setTaskEditorIds(ArrayList<Long> taskEditorIds) {
		this.taskEditorIds = taskEditorIds;
	}

	public String getTaskSubmitComment() {
		return taskSubmitComment;
	}

	public void setTaskSubmitComment(String taskSubmitComment) {
		this.taskSubmitComment = taskSubmitComment;
	}

	public String getTaskApproveComment() {
		return taskApproveComment;
	}

	public void setTaskApproveComment(String taskApproveComment) {
		this.taskApproveComment = taskApproveComment;
	}

	public String getTaskApproveStatus() {
		return taskApproveStatus;
	}

	public void setTaskApproveStatus(String taskApproveStatus) {
		this.taskApproveStatus = taskApproveStatus;
	}

	public Date getTaskStartTime() {
		return taskStartTime;
	}

	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}

	public Date getTaskEndTime() {
		return taskEndTime;
	}

	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getSubmitDescription() {
		return submitDescription;
	}

	public void setSubmitDescription(String submitDescription) {
		this.submitDescription = submitDescription;
	}

}
