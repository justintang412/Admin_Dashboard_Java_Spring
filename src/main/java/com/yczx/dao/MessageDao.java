package com.yczx.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yczx.domain.Message;

@Mapper
public interface MessageDao {
	Message selectMessageById(Long id);

	ArrayList<Message> selectMessagesByMyUserId(Long myUserId);

	ArrayList<Message> selectMessagesSentToMe(Long myUserId);

	ArrayList<Message> selectMessagesByPageParam(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, @Param("myUserId") Long myUserId);

	Integer getTotalCount(@Param("search") String search, @Param("min") Integer min,
			@Param("max") Integer max, @Param("pageSize") Integer pageSize, @Param("myUserId") Long myUserId);

	void insertMessage(Message message);

	void deleteMessage(Long id);

	void deleteMessageRead(Long id);

	void updateMessage(Message message);

	void updateMessageReadStatus(Long myUserId, Long messageId);
	
	Integer getReadCount(Long messageId);
	
	Integer isThisMessageRead(Long messageId, Long readerId);
}
