/*
 * @文件名： VOExamination.java
 * @创建人: aaron
 * @创建时间: 2016-2-26
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
 * 创建时间：2016-2-26 下午5:06:51
 * 修改人：aaron
 * 修改时间：2016-2-26 下午5:06:51
 * 修改备注：
 * @version  1.0
 */
public class VOExamination
{

    private int id;
    private String dateStr;
    private String pdf1Url;
    private String pdf2Url;
    public VOExamination(int id, String dateStr, String pdf1Url, String pdf2Url)
    {
        this.id = id;
        this.dateStr = dateStr;
        this.pdf1Url = pdf1Url;
        this.pdf2Url = pdf2Url;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getDateStr()
    {
        return dateStr;
    }
    public void setDateStr(String dateStr)
    {
        this.dateStr = dateStr;
    }
    public String getPdf1Url()
    {
        return pdf1Url;
    }
    public void setPdf1Url(String pdf1Url)
    {
        this.pdf1Url = pdf1Url;
    }
    public String getPdf2Url()
    {
        return pdf2Url;
    }
    public void setPdf2Url(String pdf2Url)
    {
        this.pdf2Url = pdf2Url;
    }

}