package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Position;

@Mapper
public interface PositionDao {
	Position selectPositionById(Long id);

	ArrayList<Position> selectPositions();

	ArrayList<Position> selectByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getTotalCount(@Param("search") String search);

	void insertPosition(Position position);

	void updatePosition(Position position);

	void deletePosition(Long id);
}
