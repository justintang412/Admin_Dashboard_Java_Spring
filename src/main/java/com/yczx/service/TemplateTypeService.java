package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.TemplateTypeDao;
import com.yczx.domain.TemplateType;

@Service
public class TemplateTypeService {
	@Autowired
	private TemplateTypeDao templateTypeDao;
	
	public TemplateType selectTemplateTypeById(Long id) {
		return templateTypeDao.selectTemplateTypeById(id);
	}

	public ArrayList<TemplateType> selectTemplateTypes(){
		return templateTypeDao.selectTemplateTypes();
	}

	public ArrayList<TemplateType> selectTemplateTypesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize){
		return templateTypeDao.selectTemplateTypesByPageParam(search, min, max, pageSize);
	}

	public Integer getTemplateTypeTotalCount(@Param("search") String search){
		return templateTypeDao.getTemplateTypeTotalCount(search);
	}
	public void insertTemplateType(TemplateType templateType) {
		if(templateType!=null && templateType.getId()!=null) {
			templateTypeDao.updateTemplateType(templateType);
		}
	};

	public void deleteTemplateType(Long id){
		templateTypeDao.deleteTemplateType(id);
	};

}
