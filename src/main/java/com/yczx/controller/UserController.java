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

import com.yczx.domain.SysUser;
import com.yczx.service.SysUserService;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class UserController {

	@Autowired
	SysUserService userservice;

	@PreAuthorize("hasAuthority('users')")
	@RequestMapping("pages/system/users.html")
	public String users(String action, String id, Model model, SysUser user) {
		if (action == null) {
			return "pages/system/users";
		}
		if (action.equals("view")) {
			model.addAttribute("sysuser", userservice.getUserById(new Long(id)));
			return "pages/system/usersView";
		}
		if (action.equals("create")) {
			user = new SysUser();
			model.addAttribute("user", user);
			model.addAttribute("positions", userservice.selectPositions());
			model.addAttribute("roles", userservice.selectRoles());
			return "pages/system/usersCreate";
		}
		if (action.equals("save")) {
			OperationResult<SysUser> result = userservice.insertOrUpdateSysUser(user);
			model.addAttribute("result", result);
			if(result.isError()) {
				model.addAttribute("user", user);
				model.addAttribute("positions", userservice.selectPositions());
				model.addAttribute("roles", userservice.selectRoles());
				return "pages/system/usersCreate";
			}
			return "pages/system/users";
		}
		if (action.equals("edit")) {
			user = userservice.getUserById(new Long(id));
			model.addAttribute("user", user);
			model.addAttribute("positions", userservice.selectPositions());
			model.addAttribute("roles", userservice.selectRoles());
			return "pages/system/usersCreate";
		}
		if (action.equals("delete")) {
			userservice.deleteUser(new Long(id));
			model.addAttribute("user", user);
			return "pages/system/users";
		}
		return "pages/system/users";
	}

	@ResponseBody
	@PreAuthorize("hasAuthority('users')")
	@RequestMapping(value = "pages/system/userList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<SysUser> users = userservice.selectUsersByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (SysUser user : users) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("usercode", user.getUsercode());
			map.put("username", user.getUsername());
			map.put("salt", user.getSalt());
			map.put("position", user.getPosition().getPositionName());
			map.put("role", user.getRole().getRoleName());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(userservice.getTotalUserCount(search).longValue());
		return data;
	}

	@PreAuthorize("hasAuthority('alerts')")
	@RequestMapping("pages/system/alerts.html")
	public String alerts() {
		return "pages/system/alerts";
	}

}
