package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.BidTaskAction;

@Mapper
public interface BidTaskActionDao {
	BidTaskAction selectBidTaskActionById(Long id);

	ArrayList<BidTaskAction> selectBidTaskActions();

	ArrayList<BidTaskAction> selectBidTaskActionsByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getBidTaskActionTotalCount(@Param("search") String search);

	void insertBidTaskAction(BidTaskAction bidTaskAction);

	void updateBidTaskAction(BidTaskAction bidTaskAction);

}
