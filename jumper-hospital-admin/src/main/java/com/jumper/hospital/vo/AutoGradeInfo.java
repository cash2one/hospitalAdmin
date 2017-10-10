package com.jumper.hospital.vo;

import java.io.Serializable;

/**
 * 自动评分保存评分结果
 */
public class AutoGradeInfo implements Serializable {

	private static final long serialVersionUID = -3843435626965678824L;
	/**
     * monitor_heartrate_record表主键ID
     */
    private String recordId;
    /**
     * 胎心基线
     */
    private Integer autoBaseLine;
    private Integer finalBaseLine;
    /**
     * 振幅变异
     */
    private Integer autoBpmVar;
    private Integer finalBpmVar;
    /**
     * 周期变异
     */
    private Integer autoFreqVar;
    private Integer finalFreqVar;
    /**
     * 加速次数
     */
    private String autoNumAcc;
    private String finalNumAcc;
    /**
     * 减速次数
     */
    private String autoNumDec;
    private String finalNumDec;
    /**
     * 胎动次数
     */
    private String autoNumFetalMove;
    private String finalNumFetalMove;
    /**
     * 胎心基线评分
     */
    private String autoBaseLineScore;
    private String finalBaseLineScore;
    /**
     * 变异振幅评分
     */
    private String autoBpmVarScore;
    private String finalBpmVarScore;
    /**
     * 变异频率评分
     */
    private String autoFreqVarScore;
    private String finalFreqVarScore;
    /**
     * 胎心率加速评分
     */
    private String autoNumAccScore;
    private String finalNumAccScore;
    /**
     * 胎心率减速评分
     */
    private String autoNumDecScore;
    private String finalNumDecScore;
    /**
     * 胎动次数评分
     */
    private String autoFetalMoveScore;
    private String finalFetalMoveScore;
    /**
     * 结果
     */
    private String autoResult;
    private String finalResult;
    /**
     * 备注
     */
    private String remark;
    /**
     * 有反应 1:勾选  以下8个字段1代表同样的意思
     */
    private String response;
    /**
     * 无反应 
     */
    private String adiaphoria;
    /**
     * 正弦曲线 
     */
    private String sinusoid;
    /**
     * NST中的不满意
     */
    private String nstYawp;
    /**
     * CST或NS-CST
     */
    private String cstNsCst;
    /**
     * 阳性
     */
    private String positive;
    /**
     * 阴性
     */
    private String feminine;
    /**
     * 可疑 
     */
    private String suspicious;
    /**
     * OCT中的不满意
     */
    private String octYawp;
    /**
     * 1:krebs报告 2:改良Fischer
     */
    private String type;

    
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public Integer getAutoBaseLine() {
		return autoBaseLine;
	}

	public void setAutoBaseLine(Integer autoBaseLine) {
		this.autoBaseLine = autoBaseLine;
	}

	public Integer getFinalBaseLine() {
		return finalBaseLine;
	}

	public void setFinalBaseLine(Integer finalBaseLine) {
		this.finalBaseLine = finalBaseLine;
	}

	public Integer getAutoBpmVar() {
		return autoBpmVar;
	}

	public void setAutoBpmVar(Integer autoBpmVar) {
		this.autoBpmVar = autoBpmVar;
	}

	public Integer getFinalBpmVar() {
		return finalBpmVar;
	}

	public void setFinalBpmVar(Integer finalBpmVar) {
		this.finalBpmVar = finalBpmVar;
	}

	public Integer getAutoFreqVar() {
		return autoFreqVar;
	}

	public void setAutoFreqVar(Integer autoFreqVar) {
		this.autoFreqVar = autoFreqVar;
	}

	public Integer getFinalFreqVar() {
		return finalFreqVar;
	}

	public void setFinalFreqVar(Integer finalFreqVar) {
		this.finalFreqVar = finalFreqVar;
	}

	public String getAutoNumAcc() {
		return autoNumAcc;
	}

	public void setAutoNumAcc(String autoNumAcc) {
		this.autoNumAcc = autoNumAcc;
	}

	public String getFinalNumAcc() {
		return finalNumAcc;
	}

	public void setFinalNumAcc(String finalNumAcc) {
		this.finalNumAcc = finalNumAcc;
	}

	public String getAutoNumDec() {
		return autoNumDec;
	}

	public void setAutoNumDec(String autoNumDec) {
		this.autoNumDec = autoNumDec;
	}

	public String getFinalNumDec() {
		return finalNumDec;
	}

	public void setFinalNumDec(String finalNumDec) {
		this.finalNumDec = finalNumDec;
	}

	public String getAutoNumFetalMove() {
		return autoNumFetalMove;
	}

	public void setAutoNumFetalMove(String autoNumFetalMove) {
		this.autoNumFetalMove = autoNumFetalMove;
	}

	public String getFinalNumFetalMove() {
		return finalNumFetalMove;
	}

	public void setFinalNumFetalMove(String finalNumFetalMove) {
		this.finalNumFetalMove = finalNumFetalMove;
	}

	public String getAutoBaseLineScore() {
		return autoBaseLineScore;
	}

	public void setAutoBaseLineScore(String autoBaseLineScore) {
		this.autoBaseLineScore = autoBaseLineScore;
	}

	public String getFinalBaseLineScore() {
		return finalBaseLineScore;
	}

	public void setFinalBaseLineScore(String finalBaseLineScore) {
		this.finalBaseLineScore = finalBaseLineScore;
	}

	public String getAutoBpmVarScore() {
		return autoBpmVarScore;
	}

	public void setAutoBpmVarScore(String autoBpmVarScore) {
		this.autoBpmVarScore = autoBpmVarScore;
	}

	public String getFinalBpmVarScore() {
		return finalBpmVarScore;
	}

	public void setFinalBpmVarScore(String finalBpmVarScore) {
		this.finalBpmVarScore = finalBpmVarScore;
	}

	public String getAutoFreqVarScore() {
		return autoFreqVarScore;
	}

	public void setAutoFreqVarScore(String autoFreqVarScore) {
		this.autoFreqVarScore = autoFreqVarScore;
	}

	public String getFinalFreqVarScore() {
		return finalFreqVarScore;
	}

	public void setFinalFreqVarScore(String finalFreqVarScore) {
		this.finalFreqVarScore = finalFreqVarScore;
	}

	public String getAutoNumAccScore() {
		return autoNumAccScore;
	}

	public void setAutoNumAccScore(String autoNumAccScore) {
		this.autoNumAccScore = autoNumAccScore;
	}

	public String getFinalNumAccScore() {
		return finalNumAccScore;
	}

	public void setFinalNumAccScore(String finalNumAccScore) {
		this.finalNumAccScore = finalNumAccScore;
	}

	public String getAutoNumDecScore() {
		return autoNumDecScore;
	}

	public void setAutoNumDecScore(String autoNumDecScore) {
		this.autoNumDecScore = autoNumDecScore;
	}

	public String getFinalNumDecScore() {
		return finalNumDecScore;
	}

	public void setFinalNumDecScore(String finalNumDecScore) {
		this.finalNumDecScore = finalNumDecScore;
	}

	public String getAutoFetalMoveScore() {
		return autoFetalMoveScore;
	}

	public void setAutoFetalMoveScore(String autoFetalMoveScore) {
		this.autoFetalMoveScore = autoFetalMoveScore;
	}

	public String getFinalFetalMoveScore() {
		return finalFetalMoveScore;
	}

	public void setFinalFetalMoveScore(String finalFetalMoveScore) {
		this.finalFetalMoveScore = finalFetalMoveScore;
	}

	public String getAutoResult() {
		return autoResult;
	}

	public void setAutoResult(String autoResult) {
		this.autoResult = autoResult;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getAdiaphoria() {
		return adiaphoria;
	}

	public void setAdiaphoria(String adiaphoria) {
		this.adiaphoria = adiaphoria;
	}

	public String getSinusoid() {
		return sinusoid;
	}

	public void setSinusoid(String sinusoid) {
		this.sinusoid = sinusoid;
	}

	public String getNstYawp() {
		return nstYawp;
	}

	public void setNstYawp(String nstYawp) {
		this.nstYawp = nstYawp;
	}

	public String getCstNsCst() {
		return cstNsCst;
	}

	public void setCstNsCst(String cstNsCst) {
		this.cstNsCst = cstNsCst;
	}

	public String getPositive() {
		return positive;
	}

	public void setPositive(String positive) {
		this.positive = positive;
	}

	public String getFeminine() {
		return feminine;
	}

	public void setFeminine(String feminine) {
		this.feminine = feminine;
	}

	public String getSuspicious() {
		return suspicious;
	}

	public void setSuspicious(String suspicious) {
		this.suspicious = suspicious;
	}

	public String getOctYawp() {
		return octYawp;
	}

	public void setOctYawp(String octYawp) {
		this.octYawp = octYawp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
