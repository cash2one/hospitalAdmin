package com.jumper.hospital.vo;

import java.util.List;

/**
 * @author tiedan200
 */
public class DataMsg{

	private int pageNo;
	private int pageSize;
	private int total;
	private int pages;
	public List<VOConsultantReplyInfo> dataList;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<VOConsultantReplyInfo> getDataList() {
		return dataList;
	}

	public void setDataList(List<VOConsultantReplyInfo> dataList) {
		this.dataList = dataList;
	}

}
