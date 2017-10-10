package com.jumper.hospital.vo;

/**
 * @author tiedan200
 */
public class PageData {
	private String busCode;
	private String fromUserId;
	private String fromAccountType;
	private String toUserId;
	private String toAccountType;
	private DataMsg pages;

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromAccountType() {
		return fromAccountType;
	}

	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getToAccountType() {
		return toAccountType;
	}

	public void setToAccountType(String toAccountType) {
		this.toAccountType = toAccountType;
	}

	public DataMsg getPages() {
		return pages;
	}

	public void setPages(DataMsg pages) {
		this.pages = pages;
	}

}
