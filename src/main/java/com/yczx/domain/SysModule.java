package com.yczx.domain;

import java.io.Serializable;

public class SysModule implements Serializable {
	private static final long serialVersionUID = 1L;
	private String module, code;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
