package com.jumper.hospital.vo;

import java.io.Serializable;
import java.util.List;

public class VoMonitorSetting implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 监控类型 **/
	private Integer type;
	/** 监控详情 **/
	private List<VoMonitorSettingOption> options;

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<VoMonitorSettingOption> getOptions() {
		return options;
	}
	public void setOptions(List<VoMonitorSettingOption> options) {
		this.options = options;
	}
	
	
}
