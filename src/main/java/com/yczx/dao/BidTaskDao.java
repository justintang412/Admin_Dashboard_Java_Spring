package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.BidTask;

@Mapper
public interface BidTaskDao {
	BidTask selectBidTaskById(Long id);

	ArrayList<BidTask> selectBidTasks();

	ArrayList<BidTask> selectBidTasksByBid(Long bidId);

	ArrayList<BidTask> selectBidTasksByBidAsEditor(Long bidId, Long userId);

	ArrayList<BidTask> selectBidTasksByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId);

	Integer getBidTaskTotalCount(@Param("search") String search, Long userId);

	Integer getBidTaskDoneCount(Long userId);

	Integer getBidTaskUndergoingCount(Long userId);

	Integer getBidTaskDelayedCount(Long userId);

	void insertBidTask(BidTask bidTask);

	void updateBidTask(BidTask bidTask);

	void deleteBidTaskByBidId(Long bidId);

	void updateBidTaskByAdmin(BidTask bidTask);

	void updateTaskCheckin(BidTask bidTask);

	void updateTaskCheckout(BidTask bidTask);

	void updateTaskSubmit(BidTask bidTask);

	void updateTaskApprove(BidTask bidTask);
}
