/*
 * @文件名： VOHistoryData.java
 * @创建人: aaron
 * @创建时间: 2016-2-26
 * @包名： com.jumper.hospital.vo
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.vo;

/**
 * 类名称：VOHistoryData
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-26 上午9:19:23
 * 修改人：aaron
 * 修改时间：2016-2-26 上午9:19:23
 * 修改备注：
 * @version  1.0
 */
public class VOHistoryData
{
	private int id;
	private int userID;
	private String checkDate;
	private String checkWeek;
    private int state;

    public VOHistoryData()
    {}

    public VOHistoryData(int id, int userID, String checkDate, String checkWeek, int state)
    {
        this.id = id;
        this.userID = userID;
        this.checkDate = checkDate;
        this.checkWeek = checkWeek;
        this.state = state;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckWeek() {
		return checkWeek;
	}

	public void setCheckWeek(String checkWeek) {
		this.checkWeek = checkWeek;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
    

}