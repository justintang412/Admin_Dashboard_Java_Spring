package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.BidTaskActionDao;
import com.yczx.domain.BidTaskAction;
import com.yczx.support.OperationResult;

@Service
public class BidTaskActionService {
	@Autowired
	private BidTaskActionDao bidTaskActionDao;

	public BidTaskAction selectBidTaskActionById(Long id) {
		return bidTaskActionDao.selectBidTaskActionById(id);
	}

	public ArrayList<BidTaskAction> selectBidTaskActions() {
		return bidTaskActionDao.selectBidTaskActions();
	}

	public ArrayList<BidTaskAction> selectBidTaskActionsByPageParam(@Param("search") String search,
			@Param("min") Integer min, @Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return bidTaskActionDao.selectBidTaskActionsByPageParam(search, min, max, pageSize);
	}

	public Integer getBidTaskActionTotalCount(@Param("search") String search) {
		return bidTaskActionDao.getBidTaskActionTotalCount(search);
	}

	public void insertBidTaskAction(BidTaskAction bidTaskAction) {
		bidTaskActionDao.insertBidTaskAction(bidTaskAction);
	}

	public OperationResult<BidTaskAction> insertOrUpdateBidTaskAction(BidTaskAction bidTaskAction) {
		OperationResult<BidTaskAction> result = new OperationResult<BidTaskAction>();
		result.setData(bidTaskAction);
		if (bidTaskAction.getId() != null) {
			bidTaskActionDao.updateBidTaskAction(bidTaskAction);
		} else {
			bidTaskActionDao.insertBidTaskAction(bidTaskAction);
		}
		return result;
	}
}
