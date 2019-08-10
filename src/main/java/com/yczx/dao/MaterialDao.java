package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Material;

@Mapper
public interface MaterialDao {
	Material selectMaterialById(Long id);

	ArrayList<Material> selectMaterials();

	ArrayList<Material> selectMaterialsByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getMaterialTotalCount(@Param("search") String search);

	void insertMaterial(Material material);

	void deleteMaterial(Long id);

	void updateMaterial(Material material);

}
