package com.jumper.hospital.vo.visit;

public class CloudVisitIndex {
	/**
	 * 随访内网域名地址
	 */
	public String url;
	/**
	 * 医院代码
	 */
	public String hospCode;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	@Override
	public String toString() {
		return "CloudVisitIndex [url=" + url + ", hospCode=" + hospCode + "]";
	}

}
