package com.yczx.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class SysRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String roleCode;
	private String roleName;
	private ArrayList<SysPermission> permissions;

	public SysRole() {
	}

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

	public ArrayList<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<SysPermission> permissions) {
		this.permissions = permissions;
	}

}
