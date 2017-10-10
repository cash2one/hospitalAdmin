package com.jumper.hospital.vo;
/**
 * 监测项目详情vo
 * @author rent
 * @date 2015-11-06
 */
import java.io.Serializable;

public class VoRemoteRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 记录ID **/
	private Integer recordId;
	/** 记录类型 **/
	private Integer type;
	/** 测试时间 **/
	private String time;
	/** 测试结果 **/
	private String result;
	/** 这里是备注状态字段，目前处理血糖数据对应的测试时间段，取值来自user_sugar_record中的test_time_state，还可用于胎心的成人与胎儿区分(和记录类型type并用) **/
	private Integer state;
	
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
