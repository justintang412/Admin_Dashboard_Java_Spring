package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.TemplateChapterDao;
import com.yczx.domain.TemplateChapter;
import com.yczx.support.OperationResult;

@Service
public class TemplateChapterService {
	@Autowired
	private TemplateChapterDao templateChapterDao;
	@Autowired
	private BidFileService bidFileService;
	
	public TemplateChapter selectTemplateChapterById(Long id) {
		return templateChapterDao.selectTemplateChapterById(id);
	}

	public ArrayList<TemplateChapter> selectTemplateChapters() {
		return templateChapterDao.selectTemplateChapters();
	}

	public ArrayList<TemplateChapter> selectTemplateChaptersByTemplateId(Long templateId) {
		return templateChapterDao.selectTemplateChaptersByTemplateId(templateId);
	}

	public ArrayList<TemplateChapter> selectTemplateChaptersByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return templateChapterDao.selectTemplateChaptersByPageParam(search, min, max, pageSize);
	}

	public Integer getTemplateChapterTotalCount(@Param("search") String search) {
		return templateChapterDao.getTemplateChapterTotalCount(search);
	}

	public void insertTemplateChapter(TemplateChapter certificateType) {
		templateChapterDao.insertTemplateChapter(certificateType);
	}

	public void deleteTemplateChapter(Long id) {
		TemplateChapter templateChapter = this.selectTemplateChapterById(id);
		Long templateId = templateChapter.getTemplateId();
		bidFileService.updateBidFileToDeletedById(templateChapter.getFileId());
		templateChapterDao.deleteTemplateChapter(id);
		
		ArrayList<TemplateChapter> chapters = templateChapterDao
				.selectTemplateChaptersByTemplateId(templateId);
		int i=1;
		for(TemplateChapter chapter : chapters) {
			chapter.setSort(new Long(i));
			templateChapterDao.updateTemplateChapter(chapter);
			i++;
		}
		
		
	}

	public OperationResult<TemplateChapter> insertOrUpdateTemplateChapter(TemplateChapter templateChapter) {
		OperationResult<TemplateChapter> result = new OperationResult<TemplateChapter>();
		result.setData(templateChapter);
		templateChapterDao.addTemplateChapterSortNumberAfterSpecifiedSort(templateChapter);
		templateChapter.setSort(templateChapter.getSort() + 1);
		if (templateChapter.getId() != null) {
			templateChapterDao.updateTemplateChapter(templateChapter);
		} else {
			// brand new
			templateChapterDao.insertTemplateChapter(templateChapter);
		}

		ArrayList<TemplateChapter> chapters = templateChapterDao
				.selectTemplateChaptersByTemplateId(templateChapter.getTemplateId());
		int i=1;
		for(TemplateChapter chapter : chapters) {
			chapter.setSort(new Long(i));
			templateChapterDao.updateTemplateChapter(chapter);
			i++;
		}

		return result;
	}
}
