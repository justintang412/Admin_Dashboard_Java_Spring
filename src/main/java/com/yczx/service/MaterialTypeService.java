package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.MaterialTypeDao;
import com.yczx.domain.MaterialType;
import com.yczx.support.OperationResult;

@Service
public class MaterialTypeService {
	@Autowired
	private MaterialTypeDao materialTypeDao;

	public MaterialType selectMaterialTypeById(Long id) {
		return materialTypeDao.selectMaterialTypeById(id);
	}

	public ArrayList<MaterialType> selectMaterialTypes() {
		return materialTypeDao.selectMaterialTypes();
	}

	public ArrayList<MaterialType> selectMaterialTypesByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return materialTypeDao.selectMaterialTypesByPageParam(search, min, max, pageSize);
	}

	public Integer getMaterialTypeTotalCount(@Param("search") String search) {
		return materialTypeDao.getMaterialTypeTotalCount(search);
	}

	public void insertMaterialType(MaterialType materialType) {
		materialTypeDao.insertMaterialType(materialType);
	}

	public void deleteMaterialType(Long id) {
		materialTypeDao.deleteMaterialType(id);
	}

	public OperationResult<MaterialType> insertOrUpdatePosition(MaterialType materialType) {
		OperationResult<MaterialType> result = new OperationResult<MaterialType>();
		result.setData(materialType);
		if (materialType.getId() != null) {
			materialTypeDao.updateMaterialType(materialType);
		} else {
			materialTypeDao.insertMaterialType(materialType);
		}
		return result;
	}
}
