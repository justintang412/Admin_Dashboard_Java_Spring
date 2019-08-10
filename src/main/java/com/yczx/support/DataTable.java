package com.yczx.support;

import java.io.Serializable;
import java.util.List;

public class DataTable<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> data;
	private long totalCount;
	// 请求次数
	private long draw;

	public DataTable() {

	}

	public long getDraw() {
		return draw;
	}

	public void setDraw(long draw) {
		this.draw = draw;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
