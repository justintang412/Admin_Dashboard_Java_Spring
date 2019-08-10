package com.yczx.support;

import java.io.Serializable;

public class OperationResult<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean error = false;
	private String message;
	private T data;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
