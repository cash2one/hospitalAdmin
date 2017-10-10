package com.jumper.hospital.service;

import java.util.List;
import java.util.Set;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Authority;

public interface AuthorityService extends BaseService<Authority, Integer> {

	public List<Authority> getParentList();
	
	public Set<Authority> getSetByIds(Integer[] ids);
	
	public Page<Authority> getParentAuthority(Page<Authority> page);
	
	public Page<Authority> getChildAuthority(Page<Authority> page, int auth_id);
	/**
	 * 
		 * @author yanghz
		 * @date 2016-8-27 下午3:42:26
		 * @Description: 根据角色Id查询云随访权限
		 * @param @param roleId
		 * @return void    返回类型 
		 * @throws
	 */
	public Boolean getAuthorByRole(Integer roleId);

}
