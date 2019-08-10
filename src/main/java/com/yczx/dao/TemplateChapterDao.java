package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.TemplateChapter;

@Mapper
public interface TemplateChapterDao {
	TemplateChapter selectTemplateChapterById(Long id);

	ArrayList<TemplateChapter> selectTemplateChapters();

	ArrayList<TemplateChapter> selectTemplateChaptersByTemplateId(Long templateId);

	ArrayList<TemplateChapter> selectTemplateChaptersByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTemplateChapterTotalCount(@Param("search") String search);

	void insertTemplateChapter(TemplateChapter templateChapter);

	void deleteTemplateChapter(Long id);
	void deleteTemplateChaptersByTemplateId(Long id);
	void updateTemplateChapter(TemplateChapter templateChapter);

	void addTemplateChapterSortNumberAfterSpecifiedSort(TemplateChapter templateChapter);
}
