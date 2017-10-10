/*
 * @文件名： VOExaminationDetail.java
 * @创建人: aaron
 * @创建时间: 2016-2-29
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VOExaminationDetail
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-29 上午9:04:16
 * 修改人：aaron
 * 修改时间：2016-2-29 上午9:04:16
 * 修改备注：
 * @version  1.0
 */
public class VOExaminationDetail
{
    private Integer id;
    private Integer checkid;
    private String weight;
    private Short weightState;
    private Short sugar;
    private Short sugarState;
    private Short testTimeState;
    private String temperature;
    private Short temperatureState;
    private Short pressureLow;
    private Short pressureHeight;
    private Short pressureState;
    private String oxygenLow;
    private String oxygenAverage;
    private String oxygenHeight;
    private Short averagePulse;
    private Short fetalMoveValue;
    private String recordFile;
    private String rawFile;
    private String startTime;
    private Short timeLength;
    private Short heartrateLow;
    private Short heartrateHeight;
    private Short heartrateAverage;
    private String imgUrl;

    public VOExaminationDetail()
    {}

    public VOExaminationDetail(Integer id, Integer checkid, String weight, Short weightState, Short sugar, Short sugarState, Short testTimeState, String temperature, Short temperatureState, Short pressureLow,
            Short pressureHeight, Short pressureState, String oxygenLow, String oxygenAverage, String oxygenHeight, Short averagePulse, Short fetalMoveValue, String recordFile, String rawFile,
            String startTime, Short timeLength, Short heartrateLow, Short heartrateHeight, Short heartrateAverage, String imgUrl)
    {
        this.id = id;
        this.checkid = checkid;
        this.weight = weight;
        this.weightState = weightState;
        this.sugar = sugar;
        this.sugarState = sugarState;
        this.testTimeState = testTimeState;
        this.temperature = temperature;
        this.temperatureState = temperatureState;
        this.pressureLow = pressureLow;
        this.pressureHeight = pressureHeight;
        this.pressureState = pressureState;
        this.oxygenLow = oxygenLow;
        this.oxygenAverage = oxygenAverage;
        this.oxygenHeight = oxygenHeight;
        this.averagePulse = averagePulse;
        this.fetalMoveValue = fetalMoveValue;
        this.recordFile = recordFile;
        this.rawFile = rawFile;
        this.startTime = startTime;
        this.timeLength = timeLength;
        this.heartrateLow = heartrateLow;
        this.heartrateHeight = heartrateHeight;
        this.heartrateAverage = heartrateAverage;
        this.imgUrl = imgUrl;
    }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCheckid()
    {
        return checkid;
    }

    public void setCheckid(Integer checkid)
    {
        this.checkid = checkid;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public Short getWeightState()
    {
        return weightState;
    }

    public void setWeightState(Short weightState)
    {
        this.weightState = weightState;
    }

    public Short getSugar()
    {
        return sugar;
    }

    public void setSugar(Short sugar)
    {
        this.sugar = sugar;
    }

    public Short getSugarState()
    {
        return sugarState;
    }

    public void setSugarState(Short sugarState)
    {
        this.sugarState = sugarState;
    }

    public Short getTestTimeState()
    {
        return testTimeState;
    }

    public void setTestTimeState(Short testTimeState)
    {
        this.testTimeState = testTimeState;
    }

    public String getTemperature()
    {
        return temperature;
    }

    public void setTemperature(String temperature)
    {
        this.temperature = temperature;
    }

    public Short getTemperatureState()
    {
        return temperatureState;
    }

    public void setTemperatureState(Short temperatureState)
    {
        this.temperatureState = temperatureState;
    }

    public Short getPressureLow()
    {
        return pressureLow;
    }

    public void setPressureLow(Short pressureLow)
    {
        this.pressureLow = pressureLow;
    }

    public Short getPressureHeight()
    {
        return pressureHeight;
    }

    public void setPressureHeight(Short pressureHeight)
    {
        this.pressureHeight = pressureHeight;
    }

    public Short getPressureState()
    {
        return pressureState;
    }

    public void setPressureState(Short pressureState)
    {
        this.pressureState = pressureState;
    }

    public String getOxygenLow()
    {
        return oxygenLow;
    }

    public void setOxygenLow(String oxygenLow)
    {
        this.oxygenLow = oxygenLow;
    }

    public String getOxygenAverage()
    {
        return oxygenAverage;
    }

    public void setOxygenAverage(String oxygenAverage)
    {
        this.oxygenAverage = oxygenAverage;
    }

    public String getOxygenHeight()
    {
        return oxygenHeight;
    }

    public void setOxygenHeight(String oxygenHeight)
    {
        this.oxygenHeight = oxygenHeight;
    }

    public Short getAveragePulse()
    {
        return averagePulse;
    }

    public void setAveragePulse(Short averagePulse)
    {
        this.averagePulse = averagePulse;
    }

    public Short getFetalMoveValue()
    {
        return fetalMoveValue;
    }

    public void setFetalMoveValue(Short fetalMoveValue)
    {
        this.fetalMoveValue = fetalMoveValue;
    }

    public String getRecordFile()
    {
        return recordFile;
    }

    public void setRecordFile(String recordFile)
    {
        this.recordFile = recordFile;
    }

    public String getRawFile()
    {
        return rawFile;
    }

    public void setRawFile(String rawFile)
    {
        this.rawFile = rawFile;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public Short getTimeLength()
    {
        return timeLength;
    }

    public void setTimeLength(Short timeLength)
    {
        this.timeLength = timeLength;
    }

    public Short getHeartrateLow()
    {
        return heartrateLow;
    }

    public void setHeartrateLow(Short heartrateLow)
    {
        this.heartrateLow = heartrateLow;
    }

    public Short getHeartrateHeight()
    {
        return heartrateHeight;
    }

    public void setHeartrateHeight(Short heartrateHeight)
    {
        this.heartrateHeight = heartrateHeight;
    }

    public Short getHeartrateAverage()
    {
        return heartrateAverage;
    }

    public void setHeartrateAverage(Short heartrateAverage)
    {
        this.heartrateAverage = heartrateAverage;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

}