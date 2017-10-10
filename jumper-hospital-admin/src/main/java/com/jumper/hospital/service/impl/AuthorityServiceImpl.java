package com.jumper.hospital.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.AuthorityDao;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AuthorityService;
import com.jumper.hospital.service.RoleService;

@Service
public class AuthorityServiceImpl extends BaseServiceImpl<Authority, Integer> implements AuthorityService {

	@Autowired
	private AuthorityDao moduleDaoImpl;
	@Autowired
	private RoleService roleServiceImpl;
	
	public BaseDao<Authority, Integer> getBaseDAO() {
		return moduleDaoImpl;
	}
	
	public List<Authority> getParentList() {
		return moduleDaoImpl.getParentAuthority();
	}

	public Set<Authority> getSetByIds(Integer[] ids) {
		return moduleDaoImpl.getSetByIds(ids);
	}

	public Page<Authority> getParentAuthority(Page<Authority> page) {
		return moduleDaoImpl.getParentAuthority(page);
	}

	public Page<Authority> getChildAuthority(Page<Authority> page, int auth_id) {
		return moduleDaoImpl.getChildAuthority(page, auth_id);
	}

	//查询是否有云随访的权限
	public Boolean getAuthorByRole(Integer roleId) {
		if(roleId != null){
			Role roleInfo = roleServiceImpl.get(roleId);
			if(roleInfo != null){
				Set<Authority> authorities = roleInfo.getAuthorities();
				if(authorities != null && authorities.size() > 0){
					for (Authority authority : authorities) {
						if(authority != null && authority.getId().equals(1000)){
							return Boolean.TRUE;
							
						}
					}
					return Boolean.FALSE;
				}
			}
		}
		return Boolean.FALSE;
	}
}
