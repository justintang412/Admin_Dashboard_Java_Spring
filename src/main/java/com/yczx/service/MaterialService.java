package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.MaterialDao;
import com.yczx.domain.Material;
import com.yczx.support.OperationResult;

@Service
public class MaterialService {
	@Autowired
	private MaterialDao materialDao;

	public Material selectMaterialById(Long id) {
		return materialDao.selectMaterialById(id);
	}

	public ArrayList<Material> selectMaterials() {
		return materialDao.selectMaterials();
	}

	public ArrayList<Material> selectMaterialsByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return materialDao.selectMaterialsByPageParam(search, min, max, pageSize);
	}

	public Integer getMaterialTotalCount(@Param("search") String search) {
		return materialDao.getMaterialTotalCount(search);
	}

	public void insertMaterial(Material certificateType) {
		materialDao.insertMaterial(certificateType);
	}

	public void deleteMaterial(Long id) {
		materialDao.deleteMaterial(id);
	}

	public OperationResult<Material> insertOrUpdateMaterial(Material material) {
		OperationResult<Material> result = new OperationResult<Material>();
		result.setData(material);
		if (material.getId() != null) {
			materialDao.updateMaterial(material);
		} else {
			materialDao.insertMaterial(material);
		}
		return result;
	}
}
