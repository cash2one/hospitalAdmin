package com.jumper.hospital.vo;

import java.io.Serializable;
/**
 *自动评分Bean
 */
public class VOFhrScore implements Serializable  {
	private static final long serialVersionUID = 4645615832435142786L;
	
	private int totalScore; // 总评分
	private int basalScore; // 胎心基线评分
	private int bpmVScore; // 变异振幅评分
	private int freqVScore; // 变异频率评分
	private int numAccScore; // 胎心率加速评分
	private int numDecScore; // 胎心率减速评分
	private int numFetalMoveScore; // 胎动次数评分
	
	private int basal; // 胎心基线
	private int bpmVar; // 振幅变异
	private int freqVar; // 周期变异
	private int numAcc; // 加速次
	private int numDec; // 减速次
	private int numFetalMove; //胎动次数

	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getBasalScore() {
		return basalScore;
	}
	public void setBasalScore(int basalScore) {
		this.basalScore = basalScore;
	}
	public int getBpmVScore() {
		return bpmVScore;
	}
	public void setBpmVScore(int bpmVScore) {
		this.bpmVScore = bpmVScore;
	}
	public int getFreqVScore() {
		return freqVScore;
	}
	public void setFreqVScore(int freqVScore) {
		this.freqVScore = freqVScore;
	}
	public int getNumAccScore() {
		return numAccScore;
	}
	public void setNumAccScore(int numAccScore) {
		this.numAccScore = numAccScore;
	}
	public int getNumDecScore() {
		return numDecScore;
	}
	public void setNumDecScore(int numDecScore) {
		this.numDecScore = numDecScore;
	}
	public int getNumFetalMoveScore() {
		return numFetalMoveScore;
	}
	public void setNumFetalMoveScore(int numFetalMoveScore) {
		this.numFetalMoveScore = numFetalMoveScore;
	}
	public int getBasal() {
		return basal;
	}
	public void setBasal(int basal) {
		this.basal = basal;
	}
	public int getBpmVar() {
		return bpmVar;
	}
	public void setBpmVar(int bpmVar) {
		this.bpmVar = bpmVar;
	}
	public int getFreqVar() {
		return freqVar;
	}
	public void setFreqVar(int freqVar) {
		this.freqVar = freqVar;
	}
	public int getNumAcc() {
		return numAcc;
	}
	public void setNumAcc(int numAcc) {
		this.numAcc = numAcc;
	}
	public int getNumDec() {
		return numDec;
	}
	public void setNumDec(int numDec) {
		this.numDec = numDec;
	}
	public int getNumFetalMove() {
		return numFetalMove;
	}
	public void setNumFetalMove(int numFetalMove) {
		this.numFetalMove = numFetalMove;
	}
	
}
