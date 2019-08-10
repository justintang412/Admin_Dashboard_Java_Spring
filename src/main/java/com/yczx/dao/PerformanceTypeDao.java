package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.PerformanceType;

@Mapper
public interface PerformanceTypeDao {
	PerformanceType selectPerformanceTypeById(Long id);

	ArrayList<PerformanceType> selectPerformanceTypes();

	ArrayList<PerformanceType> selectPerformanceTypesByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getPerformanceTypeTotalCount(@Param("search") String search);

	void insertPerformanceType(PerformanceType certificationType);

	void deletePerformanceType(Long id);

	void updatePerformanceType(PerformanceType certificationType);

}
