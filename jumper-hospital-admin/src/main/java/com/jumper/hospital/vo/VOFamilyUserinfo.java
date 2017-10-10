/*
 * @文件名： VOFamilyUserinfo.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

import java.util.List;

/**
 * 类名称：VOFamilyUserinfo
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-19 下午5:58:25
 * 修改人：aaron
 * 修改时间：2016-2-19 下午5:58:25
 * 修改备注：
 * @version  1.0
 */
public class VOFamilyUserinfo
{
    private Integer userid;
    private String img;
    private String name;
    private Short age;
    private String mobile;
    private String linkTel;
    private String identity;
    private String lastPeriod;
    private Short height;
    private Double weight;
    private Short pregnantWeek;
    private String detailAdd;
    private String lastServiceDate;
    private List<VOExaminationOrder> checkList;

    public VOFamilyUserinfo()
    {}

    public VOFamilyUserinfo(Integer userid, String img, String name, Short age, String mobile, String linkTel, String identity, String lastPeriod, Short height, Double weight, Short pregnantWeek,
            String detailAdd,String lastServiceDate)
    {
        this.userid = userid;
        this.img = img;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.linkTel = linkTel;
        this.identity = identity;
        this.lastPeriod = lastPeriod;
        this.height = height;
        this.weight = weight;
        this.pregnantWeek = pregnantWeek;
        this.detailAdd = detailAdd;
        this.lastServiceDate = lastServiceDate;
    }

    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Short getAge()
    {
        return age;
    }

    public void setAge(Short age)
    {
        this.age = age;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getLinkTel()
    {
        return linkTel;
    }

    public void setLinkTel(String linkTel)
    {
        this.linkTel = linkTel;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;
    }

    public String getLastPeriod()
    {
        return lastPeriod;
    }

    public void setLastPeriod(String lastPeriod)
    {
        this.lastPeriod = lastPeriod;
    }

    public Short getHeight()
    {
        return height;
    }

    public void setHeight(Short height)
    {
        this.height = height;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public Short getPregnantWeek()
    {
        return pregnantWeek;
    }

    public void setPregnantWeek(Short pregnantWeek)
    {
        this.pregnantWeek = pregnantWeek;
    }

    public String getDetailAdd()
    {
        return detailAdd;
    }

    public void setDetailAdd(String detailAdd)
    {
        this.detailAdd = detailAdd;
    }

    public String getLastServiceDate()
    {
        return lastServiceDate;
    }

    public void setLastServiceDate(String lastServiceDate)
    {
        this.lastServiceDate = lastServiceDate;
    }

    public List<VOExaminationOrder> getCheckList()
    {
        return checkList;
    }

    public void setCheckList(List<VOExaminationOrder> checkList)
    {
        this.checkList = checkList;
    }

}