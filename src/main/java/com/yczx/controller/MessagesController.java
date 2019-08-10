package com.yczx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yczx.domain.Message;
import com.yczx.domain.SysUser;
import com.yczx.service.SysUserService;
import com.yczx.support.BidUtils;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class MessagesController {
	@Autowired
	SysUserService userservice;

	@PreAuthorize("hasAuthority('messages')")
	@RequestMapping("pages/system/messages.html")
	public String roles(String action, String id, Model model, Message message) {
		if (action == null) {
			return "pages/system/messages";
		}
		if (action.equals("view")) {
			message.clear();
			HashMap<String, Object> map = new HashMap<String, Object>();
			Message m = userservice.selectMessageById(new Long(id));
			SysUser user = userservice.getUserByName(BidUtils.getLoginedUsername());
			OperationResult<String> result = new OperationResult<String>();
			if (m.getCreator() != user.getId()) {
				if (m.getToPosition() != null && m.getToPosition() != user.getPosition().getId()) {
					result.setError(true);
					result.setMessage("无权查看此条信息");
					model.addAttribute("result", result);
					return "pages/system/messages";
				}
				if (m.getToUser() != null && m.getToUser() != user.getId()) {
					result.setError(true);
					result.setMessage("无权查看此条信息");
					model.addAttribute("result", result);
					return "pages/system/messages";
				}
			}

			map.put("id", m.getId());
			map.put("createTime", m.getCreateTime());
			map.put("creator", userservice.getUserById(m.getCreator()).getSalt());
			map.put("message", m.getMessage());
			map.put("title", m.getTitle());
			boolean isMyMessage = false;
			if (m.getToPosition() != null) {
				map.put("toPosition", userservice.selectPositionById(m.getToPosition()).getPositionName());
				isMyMessage = m.getToPosition() == user.getPosition().getId();
			} else {
				map.put("toPosition", "-");
			}
			if (m.getToUser() != null) {
				map.put("toUser", userservice.getUserById(m.getToUser()).getSalt());
				isMyMessage = m.getToUser() == user.getId();
			} else {
				map.put("toUser", "-");
			}
			if (isMyMessage) {
				map.put("isMyNewMessage", userservice.isThisMessageRead(m.getId(), user.getId()));
			}

			model.addAttribute("messageMap", map);
			userservice.updateMessageReadStatus(m.getId(), user.getId());

			return "pages/system/messagesView";
		}
		if (action.equals("create")) {
			message.clear();
			model.addAttribute("users", userservice.getUses());
			model.addAttribute("positions", userservice.selectPositions());
			return "pages/system/messagesCreate";
		}
		if (action.equals("save")) {
			OperationResult<Message> result = userservice.insertOrUpdateMessage(message);
			model.addAttribute("result", result);
			if (result.isError()) {
				return "pages/system/messagesCreate";
			}
			return "pages/system/messages";
		}
		if (action.equals("edit")) {
			message.clear();
			Message m = userservice.selectMessageById(new Long(id));
			message.setCreateTime(m.getCreateTime());
			message.setCreator(m.getCreator());
			message.setId(m.getId());
			message.setMessage(m.getMessage());
			message.setTitle(m.getTitle());
			message.setToPosition(m.getToPosition());
			message.setToUser(m.getToUser());
			model.addAttribute("users", userservice.getUses());
			model.addAttribute("positions", userservice.selectPositions());
			return "pages/system/messagesCreate";
		}

		if (action.equals("delete")) {
			Long messageid = new Long(id);
			OperationResult<Long> result = userservice.deleteMessageById(messageid);
			model.addAttribute("result", result);
			return "pages/system/messages";
		}
		return "pages/system/messages";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('messages')")
	@RequestMapping(value = "pages/system/messageList.html")
	public DataTable<Map<String, Object>> messageList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pageSize = pageParam.getPagesize();
		SysUser loginedUser = userservice.getUserByName(BidUtils.getLoginedUsername());
		ArrayList<Message> messages = userservice.selectMessageByPageParam(search, min, max, pageSize,
				loginedUser.getId());
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Message message : messages) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", message.getId());
			map.put("title", message.getTitle());
			map.put("message", message.getMessage());
			map.put("creator", userservice.getUserById(message.getCreator()).getSalt());
			map.put("createTime", message.getCreateTime());
			boolean isMyMessage = false;
			SysUser user = userservice.getUserByName(BidUtils.getLoginedUsername());
			if (message.getToPosition() != null) {
				map.put("toPosition", userservice.selectPositionById(message.getToPosition()).getPositionName());
				isMyMessage = message.getToPosition() == user.getPosition().getId();
			} else {
				map.put("toPosition", "-");
			}
			if (message.getToUser() != null) {
				map.put("toUser", userservice.getUserById(message.getToUser()).getSalt());
				isMyMessage = message.getToUser() == user.getId();
			} else {
				map.put("toUser", "-");
			}
			map.put("isMyNewMessage", isMyMessage && !userservice.isThisMessageRead(message.getId(), user.getId()));
			map.put("readTimes", userservice.getMessageReadCount(message.getId()));

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(userservice.getTotalMessageCount(search, min, max, pageSize, loginedUser.getId()));
		return data;
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('messages')")
	@RequestMapping(value = "pages/system/unreadMessageList.html")
	public DataTable<Map<String, Object>> unreadMessageList() {
		SysUser loginedUser = userservice.getUserByName(BidUtils.getLoginedUsername());

		ArrayList<Message> messages = userservice.getMyUnReadMessages(loginedUser.getId());
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (Message message : messages) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", message.getId());
			map.put("title", message.getTitle());
			map.put("message", message.getMessage());
			map.put("creator", userservice.getUserById(message.getCreator()).getSalt());
			map.put("createTime", message.getCreateTime());

			if (message.getToPosition() != null) {
				map.put("toPosition", userservice.selectPositionById(message.getToPosition()).getPositionName());

			} else {
				map.put("toPosition", "-");
			}
			if (message.getToUser() != null) {
				map.put("toUser", userservice.getUserById(message.getToUser()).getSalt());

			} else {
				map.put("toUser", "-");
			}

			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setTotalCount(rowsList.size());
		return data;
	}
}
