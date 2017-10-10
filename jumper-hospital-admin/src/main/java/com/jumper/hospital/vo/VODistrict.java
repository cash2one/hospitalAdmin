/*
 * @文件名： VODistrict.java
 * @创建人: aaron
 * @创建时间: 2016-2-22
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VODistrict
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-22 下午5:11:17
 * 修改人：aaron
 * 修改时间：2016-2-22 下午5:11:17
 * 修改备注：
 * @version  1.0
 */
public class VODistrict
{

    private int districtId;
    private String districtName;

    public VODistrict()
    {}

    public VODistrict(int districtId, String districtName)
    {
        this.districtId = districtId;
        this.districtName = districtName;
    }

    public int getDistrictId()
    {
        return districtId;
    }

    public void setDistrictId(int districtId)
    {
        this.districtId = districtId;
    }

    public String getDistrictName()
    {
        return districtName;
    }

    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

}