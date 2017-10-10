package com.jumper.hospital.dao;

import java.util.List;
import java.util.Set;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Authority;

public interface AuthorityDao extends BaseDao<Authority, Integer> {

	public List<Authority> getParentAuthority();
	
	public Page<Authority> getParentAuthority(Page<Authority> page);
	
	public Set<Authority> getSetByIds(Integer[] ids);
	
	public Page<Authority> getChildAuthority(Page<Authority> page, int auth_id);
	
}
