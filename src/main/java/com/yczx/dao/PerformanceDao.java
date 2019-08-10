package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Performance;

@Mapper
public interface PerformanceDao {
	Performance selectPerformanceById(Long id);

	ArrayList<Performance> selectPerformances();

	ArrayList<Performance> selectPerformancesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getPerformanceTotalCount(@Param("search") String search);

	void insertPerformance(Performance performance);

	void deletePerformance(Long id);

	void updatePerformance(Performance performance);

}
