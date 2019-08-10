package com.yczx.domain;

import java.io.Serializable;
import java.sql.Date;

public class BidTask implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, bidId, chapterId, draftFileId, approver, checkedOut, checker, submiter;
	private String submitComment, approveComment, approveStatus, editorIds;
	private Date startTime, endTime, submitTime, approveTime, checkTime;

	public void clear() {
		id = null;
		bidId = null;
		editorIds = null;
		chapterId = null;
		draftFileId = null;
		approver = null;
		checkedOut = null;
		submitComment = null;
		approveComment = null;
		approveStatus = null;
		startTime = null;
		endTime = null;
		submitTime = null;
		approveTime = null;
		checker = null;
		checkTime = null;
		submiter = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public String getEditorIds() {
		return editorIds;
	}

	public void setEditorIds(String editorIds) {
		this.editorIds = editorIds;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getDraftFileId() {
		return draftFileId;
	}

	public void setDraftFileId(Long draftFileId) {
		this.draftFileId = draftFileId;
	}

	public Long getApprover() {
		return approver;
	}

	public void setApprover(Long approver) {
		this.approver = approver;
	}

	public Long getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(Long checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getSubmitComment() {
		return submitComment;
	}

	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}

	public String getApproveComment() {
		return approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
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

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public Long getChecker() {
		return checker;
	}

	public void setChecker(Long checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Long getSubmiter() {
		return submiter;
	}

	public void setSubmiter(Long submiter) {
		this.submiter = submiter;
	}

}
