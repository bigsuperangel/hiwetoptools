package com.fj.hiwetoptools.jfinal.jqgrid;

import java.util.List;
import java.util.Map;

public class JqGridModel {
	/**
	 * 总记录数
	 */
	private long records;
	/**
	 * 第几页
	 */
	private int page;
	/**
	 * 总页数
	 */
	private int total;
	
	private List rows;

	private Map<String,Object> userdata;

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Map<String, Object> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<String, Object> userdata) {
		this.userdata = userdata;
	}
}
