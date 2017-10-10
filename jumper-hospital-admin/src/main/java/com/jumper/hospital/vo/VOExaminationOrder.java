/*
 * @文件名： VOExamination.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VOExamination
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-19 下午6:03:03
 * 修改人：aaron
 * 修改时间：2016-2-19 下午6:03:03
 * 修改备注：
 * @version  1.0
 */
public class VOExaminationOrder
{
    private Integer id;
    private String orderStr;
    private String timeStr;

    public VOExaminationOrder()
    {}

    public VOExaminationOrder(Integer id,String orderStr, String timeStr)
    {
        this.id = id;
        this.orderStr = orderStr;
        this.timeStr = timeStr;
    }

    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getOrderStr()
    {
        return orderStr;
    }
    public void setOrderStr(String orderStr)
    {
        this.orderStr = orderStr;
    }
    public String getTimeStr()
    {
        return timeStr;
    }
    public void setTimeStr(String timeStr)
    {
        this.timeStr = timeStr;
    }

}
