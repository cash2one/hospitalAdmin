/*
 * @文件名： VOFamilyVersion.java
 * @创建人: aaron
 * @创建时间: 2016-2-24
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VOFamilyVersion
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-24 下午5:59:55
 * 修改人：aaron
 * 修改时间：2016-2-24 下午5:59:55
 * 修改备注：
 * @version  1.0
 */
public class VOFamilyVersion
{
    private String name;
    private String downloadUrl;
    private String remark;
    private String createTime;
    private Short forcedUpdate;

    public VOFamilyVersion(String name, String downloadUrl, String remark, String createTime, Short forcedUpdate)
    {
        this.name = name;
        this.downloadUrl = downloadUrl;
        this.remark = remark;
        this.createTime = createTime;
        this.forcedUpdate = forcedUpdate;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public Short getForcedUpdate()
    {
        return forcedUpdate;
    }

    public void setForcedUpdate(Short forcedUpdate)
    {
        this.forcedUpdate = forcedUpdate;
    }

}