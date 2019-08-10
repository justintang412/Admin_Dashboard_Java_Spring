package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.BidDao;
import com.yczx.dao.BidTaskDao;
import com.yczx.dao.SysUserDao;
import com.yczx.domain.Bid;
import com.yczx.domain.BidTask;
import com.yczx.domain.SysUser;
import com.yczx.domain.TemplateChapter;
import com.yczx.support.BidUtils;
import com.yczx.support.OperationResult;

@Service
public class BidService {
	@Autowired
	private BidDao bidDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private BidTaskDao bidTaskDao;
	@Autowired
	private TemplateChapterService templateChapterService;

	public Bid selectBidById(Long id) {
		return bidDao.selectBidById(id);
	}

	public ArrayList<Bid> selectBidsAsManager(Long userId) {
		return bidDao.selectBidsAsManager(userId);
	}

	public ArrayList<Bid> selectBidsAsEditor(Long userId) {
		return bidDao.selectBidsAsEditor(userId);
	}

	public ArrayList<Bid> selectBidsByPageParamAsManager(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId) {
		return bidDao.selectBidsByPageParamAsManager(search, min, max, pageSize, userId);
	}

	public Integer getBidTotalCountAsManager(@Param("search") String search, Long userId) {
		return bidDao.getBidTotalCountAsManager(search, userId);
	}

	public ArrayList<Bid> selectBidsByPageParamAsEditor(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, Long userId) {
		return bidDao.selectBidsByPageParamAsEditor(search, min, max, pageSize, userId);
	}

	public Integer getBidTotalCountAsEditor(@Param("search") String search, Long userId) {
		return bidDao.getBidTotalCountAsEditor(search, userId);
	}

	public void insertBid(Bid bid) {
		bidDao.insertBid(bid);
	}

	public void deleteBid(Long id) {
		bidDao.deleteBidTaskByBidId(id);
		bidDao.deleteBid(id);
	}

	public OperationResult<Bid> insertOrUpdateBid(Bid bid) {
		OperationResult<Bid> result = new OperationResult<Bid>();
		result.setData(bid);
		if (bid.getManager() == null || bid.getManager().length() == 0 || bid.getStartTime() == null
				|| bid.getEndTime() == null || bid.getEndTime().before(bid.getStartTime())) {
			result.setError(true);
			result.setMessage("数据填写不合理，请检查空值和时间设置");
			return result;
		}

		if (bid.getId() != null ) {
			// if some tasks had been assigned, update would be not allowed.
			if( bid.isTemplateIdChanged()) {
				ArrayList<BidTask> tasks = bidTaskDao.selectBidTasksByBid(bid.getId());
				for (BidTask task : tasks) {
					if (task.getEditorIds() != null && task.getEditorIds().length() > 0) {
						result.setError(true);
						result.setMessage("任务已经分配，不允许修改。");
						return result;
					}
				}
				bidTaskDao.deleteBidTaskByBidId(bid.getId());
				ArrayList<TemplateChapter> chapters = templateChapterService
						.selectTemplateChaptersByTemplateId(bid.getTemplateId());
				for (TemplateChapter chapter : chapters) {
					BidTask bidTask  = new BidTask();
					bidTask.setStartTime(bid.getStartTime());
					bidTask.setEndTime(bid.getEndTime());
					bidTask.setChapterId(chapter.getId());
					bidTask.setBidId(bid.getId());
					bidTaskDao.insertBidTask(bidTask);
				}
			}
			bidDao.updateBid(bid);
		} else {
			SysUser user = sysUserDao.selectUserByName(BidUtils.getLoginedUsername());
			bid.setCreator(user.getId());
			bidDao.insertBid(bid);
			ArrayList<TemplateChapter> chapters = templateChapterService
					.selectTemplateChaptersByTemplateId(bid.getTemplateId());
			for (TemplateChapter chapter : chapters) {
				BidTask bidTask  = new BidTask();
				bidTask.setStartTime(bid.getStartTime());
				bidTask.setEndTime(bid.getEndTime());
				bidTask.setChapterId(chapter.getId());
				bidTask.setBidId(bid.getId());
				bidTaskDao.insertBidTask(bidTask);
			}
		}
	
		return result;
	}
}
