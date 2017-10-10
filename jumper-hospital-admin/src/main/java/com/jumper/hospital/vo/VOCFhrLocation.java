package com.jumper.hospital.vo;

import java.io.Serializable;
import java.util.List;

/**
 *自动评分Bean
 */
public class VOCFhrLocation implements Serializable {
	private static final long serialVersionUID = -1046262850400662481L;

	private int x;//x轴
	private int y;//y轴
	private boolean flag; //true 为加速  ，false 为减速
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
