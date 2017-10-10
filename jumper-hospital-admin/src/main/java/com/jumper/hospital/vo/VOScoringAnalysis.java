package com.jumper.hospital.vo;

import java.io.Serializable;

/**
 *自动评分分析字段
 */
public class VOScoringAnalysis implements Serializable {
	private static final long serialVersionUID = 1469598344414452313L;
	
	private String basalAnalysis; // 胎心基线
	private String bpmVarAnalysis; // 振幅变异
	private String freqVarAnalysis; // 周期变异
	private String numAccAnalysis; // 加速次
	private String numDecAnalysis; // 减速次
	private String numFetalMoveAnalysis; //胎动次数
	
	
	public String getBasalAnalysis() {
		return basalAnalysis;
	}
	public void setBasalAnalysis(String basalAnalysis) {
		this.basalAnalysis = basalAnalysis;
	}
	public String getBpmVarAnalysis() {
		return bpmVarAnalysis;
	}
	public void setBpmVarAnalysis(String bpmVarAnalysis) {
		this.bpmVarAnalysis = bpmVarAnalysis;
	}
	public String getFreqVarAnalysis() {
		return freqVarAnalysis;
	}
	public void setFreqVarAnalysis(String freqVarAnalysis) {
		this.freqVarAnalysis = freqVarAnalysis;
	}
	public String getNumAccAnalysis() {
		return numAccAnalysis;
	}
	public void setNumAccAnalysis(String numAccAnalysis) {
		this.numAccAnalysis = numAccAnalysis;
	}
	public String getNumDecAnalysis() {
		return numDecAnalysis;
	}
	public void setNumDecAnalysis(String numDecAnalysis) {
		this.numDecAnalysis = numDecAnalysis;
	}
	public String getNumFetalMoveAnalysis() {
		return numFetalMoveAnalysis;
	}
	public void setNumFetalMoveAnalysis(String numFetalMoveAnalysis) {
		this.numFetalMoveAnalysis = numFetalMoveAnalysis;
	}
	
}
