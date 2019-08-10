package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.BidFile;

@Mapper
public interface BidFileDao {
	BidFile selectBidFileById(Long id);
	
	BidFile selectBidFileByPath(String path);
	
	ArrayList<BidFile> selectBidFiles();

	ArrayList<BidFile> selectBidFilesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize);

	Integer getBidFileTotalCount(@Param("search") String search);

	void insertBidFile(BidFile certification);

	void updateBidFile(BidFile certification);

	void deleteBidFile(Long id);

	void updateBidFileToIndexed(BidFile bidFile);
	void updateBidFileToDeleted(BidFile bidFile);

	void updateBidFileToDeletedById(Long id);
	
	ArrayList<BidFile> selectUnProcessedBidFiles();
}
