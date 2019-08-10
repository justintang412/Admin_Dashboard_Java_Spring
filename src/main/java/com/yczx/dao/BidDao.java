package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Bid;

@Mapper
public interface BidDao {

	ArrayList<Bid> selectBidsAsManager(Long userId);

	ArrayList<Bid> selectBidsAsEditor(Long userId);

	Bid selectBidById(Long id);

	ArrayList<Bid> selectBidsByPageParamAsManager(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId);

	Integer getBidTotalCountAsManager(@Param("search") String search, Long userId);

	ArrayList<Bid> selectBidsByPageParamAsEditor(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId);

	Integer getBidTotalCountAsEditor(@Param("search") String search, Long userId);

	void insertBid(Bid certification);

	void deleteBid(Long id);
	
	void deleteBidTaskByBidId(Long id);
	
	void updateBid(Bid bid);
	/**
	 * 
	 * @param status 新建，执行中，已完成
	 */
	void upateMainStatus(Bid bid);
}
