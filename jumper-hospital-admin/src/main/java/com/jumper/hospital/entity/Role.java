package com.jumper.hospital.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Entity - 角色
 */
@Entity
@Table(name = "monitor_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -6614052029623997372L;

	/** 名称 */
	private String name;

	/** 是否内置 */
	private Boolean isSystem;

	/** 描述 */
	private String description;
	/** 角色所属项目 **/
	private int used;

	/** 权限 */
	private Set<Authority> authorities = new HashSet<Authority>();

	/** 管理员 */
	private Set<Admin> admins = new HashSet<Admin>();

	/**
	 * 获取名称
	 * @return 名称
	 */
	@Column(nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取是否内置
	 * @return 是否内置
	 */
	@Column(nullable = false, updatable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	/**
	 * 设置是否内置
	 * @param isSystem 是否内置
	 */
	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	/**
	 * 获取描述
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取管理员
	 * @return 管理员
	 */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	public Set<Admin> getAdmins() {
		return admins;
	}

	/**
	 * 设置管理员
	 * @param admins 管理员
	 */
	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "monitor_role_authority")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}
	
}