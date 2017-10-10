package com.jumper.hospital.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * APP首页维护Entity
 * @author qinxiaowei
 *
 */
@Entity
@Table(name = "app_index")
public class AppIndex implements Serializable {

	private static final long serialVersionUID = -1099303978388985865L;
	
	@Id
	private int id;
	
	private String title;
	
	private String url;
	
	private int sort;
	
	private int showPosition;
	
	private int isShow;
	
	private String description;
	
	private int hospitaId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(int showPosition) {
		this.showPosition = showPosition;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHospitaId() {
		return hospitaId;
	}

	public void setHospitaId(int hospitaId) {
		this.hospitaId = hospitaId;
	}
}
