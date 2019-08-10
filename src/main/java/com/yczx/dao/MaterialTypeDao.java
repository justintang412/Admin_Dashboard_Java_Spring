package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.MaterialType;

@Mapper
public interface MaterialTypeDao {
	MaterialType selectMaterialTypeById(Long id);

	ArrayList<MaterialType> selectMaterialTypes();

	ArrayList<MaterialType> selectMaterialTypesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getMaterialTypeTotalCount(@Param("search") String search);

	void insertMaterialType(MaterialType materialType);

	void deleteMaterialType(Long id);

	void updateMaterialType(MaterialType materialType);

}
