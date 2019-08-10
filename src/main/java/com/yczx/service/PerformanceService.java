package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.PerformanceDao;
import com.yczx.domain.Performance;
import com.yczx.support.OperationResult;

@Service
public class PerformanceService {
	@Autowired
	private PerformanceDao performanceDao;

	public Performance selectPerformanceById(Long id) {
		return performanceDao.selectPerformanceById(id);
	}

	public ArrayList<Performance> selectPerformances() {
		return performanceDao.selectPerformances();
	}

	public ArrayList<Performance> selectPerformancesByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return performanceDao.selectPerformancesByPageParam(search, min, max, pageSize);
	}

	public Integer getPerformanceTotalCount(@Param("search") String search) {
		return performanceDao.getPerformanceTotalCount(search);
	}

	public void insertPerformance(Performance performanceType) {
		performanceDao.insertPerformance(performanceType);
	}

	public void deletePerformance(Long id) {
		performanceDao.deletePerformance(id);
	}

	public OperationResult<Performance> insertOrUpdatePerformance(Performance performance) {
		OperationResult<Performance> result = new OperationResult<Performance>();
		result.setData(performance);
		if (performance.getId() != null) {
			performanceDao.updatePerformance(performance);
		} else {
			performanceDao.insertPerformance(performance);
		}
		return result;
	}
}
