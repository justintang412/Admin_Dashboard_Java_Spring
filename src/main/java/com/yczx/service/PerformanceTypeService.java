package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.PerformanceTypeDao;
import com.yczx.domain.PerformanceType;
import com.yczx.support.OperationResult;

@Service
public class PerformanceTypeService {
	@Autowired
	private PerformanceTypeDao performanceTypeDao;

	public PerformanceType selectPerformanceTypeById(Long id) {
		return performanceTypeDao.selectPerformanceTypeById(id);
	}

	public ArrayList<PerformanceType> selectPerformanceTypes() {
		return performanceTypeDao.selectPerformanceTypes();
	}

	public ArrayList<PerformanceType> selectPerformanceTypesByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return performanceTypeDao.selectPerformanceTypesByPageParam(search, min, max, pageSize);
	}

	public Integer getPerformanceTypeTotalCount(@Param("search") String search) {
		return performanceTypeDao.getPerformanceTypeTotalCount(search);
	}

	public void insertPerformanceType(PerformanceType certificateType) {
		if(certificateType!=null && certificateType.getId()!=null) {
			performanceTypeDao.updatePerformanceType(certificateType);
		}
		else {
			performanceTypeDao.insertPerformanceType(certificateType);
		}
	}

	public void deletePerformanceType(Long id) {
		performanceTypeDao.deletePerformanceType(id);
	}

	public OperationResult<PerformanceType> insertOrUpdatePosition(PerformanceType performanceType) {
		OperationResult<PerformanceType> result = new OperationResult<PerformanceType>();
		result.setData(performanceType);
		if (performanceType.getId() != null) {
			performanceTypeDao.updatePerformanceType(performanceType);
		} else {
			performanceTypeDao.insertPerformanceType(performanceType);
		}
		return result;
	}
}
