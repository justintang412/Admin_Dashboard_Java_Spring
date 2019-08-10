package com.yczx.domain;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id, positionSort, dataStatus;
	private String positionCode, positionName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getPositionSort() {
		return positionSort;
	}

	public void setPositionSort(Long positionSort) {
		this.positionSort = positionSort;
	}

	public Long getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Long dataStatus) {
		this.dataStatus = dataStatus;
	}

}
