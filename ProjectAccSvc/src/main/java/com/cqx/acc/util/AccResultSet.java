package com.cqx.acc.util;

import java.util.List;

public class AccResultSet {
	private List<Object> result;
	private int totalcount = 0;
	public List<Object> getResult() {
		return result;
	}
	public void setResult(List<Object> result) {
		this.result = result;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
}
