package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Template;

@Mapper
public interface TemplateDao {
	Template selectTemplateById(Long id);

	ArrayList<Template> selectTemplates();

	ArrayList<Template> selectTemplatesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTemplateTotalCount(@Param("search") String search);

	void insertTemplate(Template template);

	void deleteTemplate(Long id);

	void updateTemplate(Template template);

	Integer getTemplateReferenceCount(Long templateId);
}
