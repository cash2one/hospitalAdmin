package com.jumper.hospital.entity;
/**
 * 通用字典
 * @author rent
 * @date 2016-01-25
 */
import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class CommonDictionary extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 字典资源描述，这里字段长度为200字符 **/
	private String description;
	/** 字典类型(0:常用地址，1:注意事项) **/
	private Integer type;
	/** 关联ID **/
	private Integer relationId;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}
	
}
