/*
 * @文件名： VOFamilyRemind.java
 * @创建人: aaron
 * @创建时间: 2016-2-20
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VOFamilyRemind
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-20 下午5:44:40
 * 修改人：aaron
 * 修改时间：2016-2-20 下午5:44:40
 * 修改备注：
 * @version  1.0
 */
public class VOFamilyRemind
{
    private String img;
    private String text;

    public String getImg()
    {
        return img;
    }
    public void setImg(String img)
    {
        this.img = img;
    }

    public String getText()
    {
        return text;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public VOFamilyRemind(String img, String text)
    {
        this.img = img;
        this.text = text;
    }

}