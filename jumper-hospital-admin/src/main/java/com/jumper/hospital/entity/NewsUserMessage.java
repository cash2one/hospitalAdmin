package com.jumper.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="news_user_message")
public class NewsUserMessage extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	/** 消息id	 */
	private Integer msgId;
	/** 用户id	 */
	private Integer userId;
	/**
	 * 消息类型:0表示医院消息，1代表系统消息，2代表退费消息
	 */
	private Integer type;
	
	public NewsUserMessage() {
	}
	public NewsUserMessage(Integer msgId, Integer userId, Integer type) {
		this.msgId = msgId;
		this.userId = userId;
		this.type = type;
	}
	@Column(name="msg_id")
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
