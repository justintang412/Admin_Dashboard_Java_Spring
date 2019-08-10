package com.yczx.support;

import java.io.Serializable;
import java.util.ArrayList;

import com.yczx.domain.SysPermission;

public class AuthorizedModule implements Serializable {
	private static final long serialVersionUID = 1L;
	private String module;
	private String code;
	private ArrayList<SysPermission> permissions;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public ArrayList<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<SysPermission> permissions) {
		this.permissions = permissions;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
