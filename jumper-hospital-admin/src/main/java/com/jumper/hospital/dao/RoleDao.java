package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Role;

public interface RoleDao extends BaseDao<Role, Integer> {

	public List<Role> getRoleList();
	
	public Page<Role> getRolePage(Page<Role> page, String rolename);
	/**
	 * 获取非系统内置的角色列表
	 * @return
	 */
	public List<Role> getNotSystemRoles();
	
	public List<Role> getRoleByUsed(Integer used);
	
}
