package com.yczx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.BidDao;
import com.yczx.dao.BidTaskDao;
import com.yczx.domain.Bid;
import com.yczx.domain.BidTask;
import com.yczx.support.OperationResult;

@Service
public class BidTaskService {
	@Autowired
	private BidTaskDao bidTaskDao;
	@Autowired
	private BidDao bidDao;
	
	public BidTask selectBidTaskById(Long id) {
		return bidTaskDao.selectBidTaskById(id);
	}

	public ArrayList<BidTask> selectBidTasks() {
		return bidTaskDao.selectBidTasks();
	}
	public ArrayList<BidTask> selectBidTasksByBidAsEditor(Long bidId, Long userId) {
		return bidTaskDao.selectBidTasksByBidAsEditor(bidId, userId);
	}
	
	public ArrayList<BidTask> selectBidTasksByBid(Long bidId) {
		return bidTaskDao.selectBidTasksByBid(bidId);
	}
	
	public ArrayList<BidTask> selectBidTasksByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId) {
		return bidTaskDao.selectBidTasksByPageParam(search, min, max, pageSize, userId);
	}

	public Integer getBidTaskTotalCount(@Param("search") String search, Long userId) {
		return bidTaskDao.getBidTaskTotalCount(search, userId);
	}

	public void insertBidTask(BidTask bidTask) {
		bidTaskDao.insertBidTask(bidTask);
	}

	public OperationResult<BidTask> insertOrUpdateBidTask(BidTask bidTask) {
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		if (bidTask.getId() != null) {
			bidTaskDao.updateBidTask(bidTask);
		} else {
			bidTaskDao.insertBidTask(bidTask);
		}
		return result;
	}
	
	public OperationResult<BidTask> updateTaskbyAdmin(BidTask bidTask) {
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		if(bidTask.getEndTime().before(bidTask.getStartTime())) {
			result.setError(true);
			result.setMessage("截止时间不能早于开始时间。请重新设置。");
			return result;
		}
		BidTask btask = bidTaskDao.selectBidTaskById(bidTask.getId());
		Bid bid = bidDao.selectBidById(btask.getBidId());
		if(bidTask.getStartTime().before(bid.getStartTime()) || bidTask.getEndTime().after(bid.getEndTime())) {
			result.setError(true);
			result.setMessage("截止时间不能晚于["+bid.getEndTime()+"]，开始时间不能早于["+bid.getStartTime()+"]。请重新设置。");
			return result;
		}
		bid.setStatus("执行中");
		bidTaskDao.updateBidTaskByAdmin(bidTask);
		bidDao.upateMainStatus(bid);
		return result;
	}
	
	public OperationResult<BidTask> updateTaskCheckin(BidTask bidTask) {
		bidTaskDao.updateTaskCheckin(bidTask);
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		return result;
	}
	
	public OperationResult<BidTask> updateTaskCheckout(BidTask bidTask) {
		bidTaskDao.updateTaskCheckout(bidTask);
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		return result;
	}
	
	public OperationResult<BidTask> updateTaskSubmit(BidTask bidTask) {
		bidTaskDao.updateTaskSubmit(bidTask);
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		return result;
	}
	public OperationResult<BidTask> updateTaskApprove(BidTask bidTask) {
		bidTaskDao.updateTaskApprove(bidTask);
		boolean isFinished = true;
		ArrayList<BidTask> tasks = bidTaskDao.selectBidTasksByBid(bidTask.getBidId());
		for(BidTask task : tasks) {
			if(task.getApproveStatus()==null || task.getApproveStatus().equals("不通过")) {
				isFinished = false;
				break;
			}
		}
		if(isFinished) {
			Bid bid  = bidDao.selectBidById(bidTask.getBidId());
			bid.setStatus("完成");
			bidDao.upateMainStatus(bid);
		}
		OperationResult<BidTask> result = new OperationResult<BidTask>();
		result.setData(bidTask);
		return result;
	}
	
	public Map<String,Object> taskStatistics(Long userId){
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("doneTaskCount", bidTaskDao.getBidTaskDoneCount(userId));
		map.put("delayedTaskCount", bidTaskDao.getBidTaskDelayedCount(userId));
		map.put("undergoingTaskCount", bidTaskDao.getBidTaskUndergoingCount(userId));
		
		return map;
	}
}
