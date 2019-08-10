package com.yczx.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yczx.dao.BidFileDao;
import com.yczx.domain.BidFile;
import com.yczx.support.OperationResult;

@Service
public class BidFileService {
	@Autowired
	private BidFileDao bidFileDao;

	public BidFile selectBidFileById(Long id) {
		return bidFileDao.selectBidFileById(id);
	}
	public BidFile selectBidFileByPath(String path) {
		return bidFileDao.selectBidFileByPath(path);
	}
	
	public ArrayList<BidFile> selectBidFiles() {
		return bidFileDao.selectBidFiles();
	}

	public ArrayList<BidFile> selectUnProcessedBidFiles() {
		return bidFileDao.selectUnProcessedBidFiles();
	}

	public ArrayList<BidFile> selectBidFilesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize) {
		return bidFileDao.selectBidFilesByPageParam(search, min, max, pageSize);
	}

	public Integer getBidFileTotalCount(@Param("search") String search) {
		return bidFileDao.getBidFileTotalCount(search);
	}

	public void insertBidFile(BidFile bidFile) {
		bidFileDao.insertBidFile(bidFile);
	}

	public void deleteBidFile(Long id) {
		bidFileDao.deleteBidFile(id);
	}

	public void updateBidFileToIndexed(BidFile bidFile) {
		bidFileDao.updateBidFileToIndexed(bidFile);
	}

	public void updateBidFileToDeleted(BidFile bidFile) {
		bidFileDao.updateBidFileToDeleted(bidFile);
	}

	public void updateBidFileToDeletedById(Long id) {
		bidFileDao.updateBidFileToDeletedById(id);
	}

	public OperationResult<BidFile> insertOrUpdateBidFile(BidFile bidFile) {
		OperationResult<BidFile> result = new OperationResult<BidFile>();
		result.setData(bidFile);
		if (bidFile.getId() != null) {
			bidFileDao.updateBidFile(bidFile);
		} else {
			bidFileDao.insertBidFile(bidFile);
		}
		return result;
	}
}
