package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.TemplateType;

@Mapper
public interface TemplateTypeDao {
	TemplateType selectTemplateTypeById(Long id);

	ArrayList<TemplateType> selectTemplateTypes();

	ArrayList<TemplateType> selectTemplateTypesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTemplateTypeTotalCount(@Param("search") String search);

	void insertTemplateType(TemplateType templateType);

	void deleteTemplateType(Long id);

	void updateTemplateType(TemplateType templateType);

}
