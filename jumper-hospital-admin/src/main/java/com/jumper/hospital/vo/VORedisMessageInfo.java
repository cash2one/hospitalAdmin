package com.jumper.hospital.vo;
/**
 * @param id 推送消息id
 * @param content 推送消息内容
 * @param title 推送标题
 * @param language 语言类别 cn/en/doc
 * @param Msg_type 消息类型 推送消息的类型：0：表示普通提醒消息；1：医生回复推送提醒；2：新闻推送消息;3:春雨;4:添加咨询
 * @param user_type 推送类型：0：全部推送；1单推
 * @param user 用户标记（即用户的ID）
 * @return
 **/
public class VORedisMessageInfo {
	
	private int id;
	
	private String content;
	
	private String title;
	
	private String language;
	
	private int Msg_type;
	
	private int user_type;
	
	private String user;
	
	public void putValues(int id,String content,String title,String language,int Msg_type,int user_type,String user){
		this.id=id;
		this.content=content;
		this.title=title;
		this.language=language;
		this.Msg_type=Msg_type;
		this.user_type=user_type;
		this.user=user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getMsg_type() {
		return Msg_type;
	}
	public void setMsg_type(int msg_type) {
		Msg_type = msg_type;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}
