package com.yczx.form;

import java.io.Serializable;
import java.util.ArrayList;

public class RoleForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private String roleCode, roleName;
	private Long id;
	private ArrayList<Long> permissionIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public ArrayList<Long> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(ArrayList<Long> permissionIds) {
		this.permissionIds = permissionIds;
	}

}
