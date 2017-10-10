package com.jumper.hospital.vo;

import java.io.Serializable;

import com.jumper.hospital.entity.Admin;

public class VoAdminEdit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/** id **/
	private Integer id;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 姓名 */
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static VoAdminEdit getVoAdmin(Admin admin){
		if(admin != null){
			VoAdminEdit vo = new VoAdminEdit();
			vo.setId(admin.getId());
			vo.setName(admin.getName());
			vo.setPassword(admin.getPassword());
			vo.setUsername(admin.getUsername());
			return vo;
		}
		return null;
	}
}
