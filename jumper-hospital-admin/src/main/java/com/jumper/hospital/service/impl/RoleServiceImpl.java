package com.jumper.hospital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.RoleDao;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.RoleService;
import com.jumper.hospital.utils.ArrayUtils;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	@Autowired
	private RoleDao roleDaoImpl;
	
	public BaseDao<Role, Integer> getBaseDAO() {
		return roleDaoImpl;
	}
	
	public List<Role> getRoleList() {
		return roleDaoImpl.getRoleList();
	}

	public Page<Role> getRolePage(Page<Role> page, String rolename) {
		return roleDaoImpl.getRolePage(page, rolename);
	}

	@Override
	public List<Role> getNotSystemRoles() {
		return roleDaoImpl.getNotSystemRoles();
	}

	@Override
	public Map<Integer, String> getRoleByUsed(Integer used) {
		List<Role> list = roleDaoImpl.getRoleByUsed(used);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if(ArrayUtils.isNotEmpty(list)){
			for(Role role : list){
				map.put(role.getId(), role.getName());
			}
		}
		return map;
	}
	
}
