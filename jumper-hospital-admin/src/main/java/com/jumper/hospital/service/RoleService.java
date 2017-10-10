package com.jumper.hospital.service;

import java.util.List;
import java.util.Map;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Role;

public interface RoleService extends BaseService<Role, Integer> {

	public List<Role> getRoleList();
	
	public Page<Role> getRolePage(Page<Role> page, String rolename);
	
	public List<Role> getNotSystemRoles();
	
	public Map<Integer, String> getRoleByUsed(Integer used);
	
}
