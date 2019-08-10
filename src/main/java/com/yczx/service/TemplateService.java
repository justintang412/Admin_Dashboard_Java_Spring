package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.BidFileDao;
import com.yczx.dao.TemplateChapterDao;
import com.yczx.dao.TemplateDao;
import com.yczx.domain.Template;
import com.yczx.domain.TemplateChapter;
import com.yczx.support.OperationResult;

@Service
public class TemplateService {
	@Autowired
	private TemplateDao templateDao;
	@Autowired
	private TemplateChapterDao templateChapterDao;
	@Autowired
	private BidFileDao bidFileDao;
	
	public Template selectTemplateById(Long id) {
		return templateDao.selectTemplateById(id);
	}

	public ArrayList<Template> selectTemplates() {
		return templateDao.selectTemplates();
	}

	public ArrayList<Template> selectTemplatesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return templateDao.selectTemplatesByPageParam(search, min, max, pageSize);
	}

	public Integer getTemplateTotalCount(@Param("search") String search) {
		return templateDao.getTemplateTotalCount(search);
	}

	public void insertTemplate(Template template) {
		templateDao.insertTemplate(template);
	}

	public Integer getTemplateReferenceCount(Long templateId) {
		return templateDao.getTemplateReferenceCount(templateId);
	}
	
	public OperationResult<Long> deleteTemplate(Long id) {
		OperationResult<Long> result = new OperationResult<Long>();
		if(templateDao.getTemplateReferenceCount(id)>0) {
			result.setError(true);
			result.setMessage("模板已被引用，不能删除。");
			return result;
		}
		ArrayList<TemplateChapter> chapters = templateChapterDao.selectTemplateChaptersByTemplateId(id);
		for(TemplateChapter chapter : chapters) {
			bidFileDao.updateBidFileToDeletedById(chapter.getFileId());
		}
		templateChapterDao.deleteTemplateChaptersByTemplateId(id);
		templateDao.deleteTemplate(id);
		return result;
	}

	public OperationResult<Template> insertOrUpdateTemplate(Template template) {
		OperationResult<Template> result = new OperationResult<Template>();
		result.setData(template);
		if (template.getId() != null) {
			templateDao.updateTemplate(template);
		} else {
			templateDao.insertTemplate(template);
		}
		return result;
	}
}
