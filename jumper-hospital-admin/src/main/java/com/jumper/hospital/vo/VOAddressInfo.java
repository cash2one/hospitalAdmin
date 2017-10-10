/*
 * @文件名： VOProvince.java
 * @创建人: aaron
 * @创建时间: 2016-2-22
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

import java.util.List;

/**
 * 类名称：VOProvince
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-22 下午3:38:17
 * 修改人：aaron
 * 修改时间：2016-2-22 下午3:38:17
 * 修改备注：
 * @version  1.0
 */
public class VOAddressInfo
{

    private int provId;
    private String provName;
    private int cityId;
    private String cityName;
    private List<VODistrict> districtList;

    public VOAddressInfo()
    {}

    public VOAddressInfo(int provId, String provName, int cityId, String cityName)
    {
        this.provId = provId;
        this.provName = provName;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getProvId()
    {
        return provId;
    }

    public void setProvId(int provId)
    {
        this.provId = provId;
    }

    public String getProvName()
    {
        return provName;
    }

    public void setProvName(String provName)
    {
        this.provName = provName;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public List<VODistrict> getDistrictList()
    {
        return districtList;
    }

    public void setDistrictList(List<VODistrict> districtList)
    {
        this.districtList = districtList;
    }

}