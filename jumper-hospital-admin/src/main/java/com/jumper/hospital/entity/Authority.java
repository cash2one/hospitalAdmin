package com.jumper.hospital.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="monitor_authority")
public class Authority extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String url;
	
	private String authority;
	
	private String description;

	private Set<Role> roles = new HashSet<Role>();
	
	private Authority parents;
	
	private Set<Authority> childs = new HashSet<Authority>();
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="parent_id")
	public Authority getParents() {
		return parents;
	}

	public void setParents(Authority parents) {
		this.parents = parents;
	}

	@OneToMany(mappedBy = "parents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Authority> getChilds() {
		return childs;
	}

	public void setChilds(Set<Authority> childs) {
		this.childs = childs;
	}
	
}
