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

import com.yczx.domain.SysPermission;
import com.yczx.domain.SysRole;
import com.yczx.form.RoleForm;
import com.yczx.service.SysUserService;
import com.yczx.support.DataTable;
import com.yczx.support.OperationResult;
import com.yczx.support.PageParam;

@Controller
public class RolesController {
	@Autowired
	SysUserService userservice;

	@PreAuthorize("hasAuthority('roles')")
	@RequestMapping("pages/system/roles.html")
	public String roles(String action, String id, Model model, RoleForm roleForm) {
		if (action == null) {
			return "pages/system/roles";
		}
		if (action.equals("view")) {
			model.addAttribute("role", userservice.selectRoleById(new Long(id)));
			return "pages/system/rolesView";
		}
		if (action.equals("create")) {
			roleForm = new RoleForm();
			model.addAttribute("permissions", userservice.selectPermissions());
			return "pages/system/rolesCreate";
		}
		if (action.equals("save")) {
			OperationResult<SysRole> result = userservice.insertOrUpdateSysRole(roleForm);
			model.addAttribute("result", result);
			if(result.isError()) {
				model.addAttribute("permissions", userservice.selectPermissions());
				return "pages/system/rolesCreate";
			}
			return "pages/system/roles";
		}
		if (action.equals("edit")) {
			SysRole role = userservice.selectRoleById(new Long(id));
			roleForm.setId(role.getId());
			roleForm.setRoleCode(role.getRoleCode());
			roleForm.setRoleName(role.getRoleName());
			roleForm.setPermissionIds(new ArrayList<Long>());
			for(SysPermission p : role.getPermissions()) {
				roleForm.getPermissionIds().add(p.getId());
			}
			
			model.addAttribute("permissions", userservice.selectPermissions());
			return "pages/system/rolesCreate";
		}
		
		if (action.equals("delete")) {
			Long roleid  = new Long(id);
			OperationResult<SysRole> result = userservice.deleteRoleByRoleId(roleid);
			model.addAttribute("result", result);
			return "pages/system/roles";
		}
		return "pages/system/roles";
	}
	
	@ResponseBody
	@PreAuthorize("hasAuthority('roles')")
	@RequestMapping(value = "pages/system/roleList.html")
	public DataTable<Map<String, Object>> userList(PageParam pageParam) {
		String search = pageParam.getSearch();
		if (search == null || search.length() == 0) {
			search = null;
		}
		int min = pageParam.getStart();
		int max = pageParam.getPagesize() + min;
		int pagesize = pageParam.getPagesize();
		ArrayList<SysRole> roles = userservice.selectSysRoleByPageParam(search, min, max, pagesize);
		List<Map<String, Object>> rowsList = new ArrayList<>();
		for (SysRole role : roles) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId());
			map.put("roleCode", role.getRoleCode());
			map.put("roleName", role.getRoleName());
			rowsList.add(map);
		}

		DataTable<Map<String, Object>> data = new DataTable<Map<String, Object>>();
		data.setData(rowsList);
		data.setDraw(pageParam.getDraw());
		data.setTotalCount(userservice.getTotalSysRoleCount(search).longValue());
		return data;
	}
}
