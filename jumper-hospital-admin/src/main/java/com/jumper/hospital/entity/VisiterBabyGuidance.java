package com.jumper.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 孕期管理中的指导意见模板
 * @author admin
 * 2016-8-12
 */
@Entity
@Table(name="visiter_baby_guidance")
public class VisiterBabyGuidance extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String name ;

    private String content;
    
    private Integer communityId;

    @Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="community_id")
	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
    
    
}
