package com.yczx.form;

import java.io.Serializable;

public class TemplateAndChapterForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long templateId, templateType, templateFileId;
	private String templateName, templateDescription;

	private Long chapterId, chapterFileId, chapterSort;
	private String chapterName, chapterDescription;

	public void clear() {
		templateId = null;
		templateType = null;
		templateFileId = null;
		templateName = null;
		templateDescription = null;

		chapterId = null;
		chapterFileId = null;
		chapterSort = null;
		chapterName = null;
		chapterDescription = null;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Long templateType) {
		this.templateType = templateType;
	}

	public Long getTemplateFileId() {
		return templateFileId;
	}

	public void setTemplateFileId(Long templateFileId) {
		this.templateFileId = templateFileId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDescription() {
		return templateDescription;
	}

	public void setTemplateDescription(String templateDescription) {
		this.templateDescription = templateDescription;
	}

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getChapterFileId() {
		return chapterFileId;
	}

	public void setChapterFileId(Long chapterFileId) {
		this.chapterFileId = chapterFileId;
	}

	public Long getChapterSort() {
		return chapterSort;
	}

	public void setChapterSort(Long chapterSort) {
		this.chapterSort = chapterSort;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getChapterDescription() {
		return chapterDescription;
	}

	public void setChapterDescription(String chapterDescription) {
		this.chapterDescription = chapterDescription;
	}

}
