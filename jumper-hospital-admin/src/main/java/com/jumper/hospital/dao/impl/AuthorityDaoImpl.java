package com.jumper.hospital.dao.impl;
/**
 * 模块管理Dao实现类
 * @author rent
 * @date 2015-10-16
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.AuthorityDao;
import com.jumper.hospital.entity.Authority;

@Repository
public class AuthorityDaoImpl extends BaseDaoImpl<Authority, Integer> implements AuthorityDao {

	@SuppressWarnings("unchecked")
	public List<Authority> getParentAuthority(){
		Criteria c = createCriteria();
		c.add(Restrictions.isNull("parents"));
		return c.list();
	}
	
	public Page<Authority> getParentAuthority(Page<Authority> page){
		Criteria c = createCriteria();
		c.add(Restrictions.isNull("parents"));
		return findPageByCriteria(page, c);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Authority> getSetByIds(Integer[] ids){
		Criteria c = createCriteria();
		c.add(Restrictions.in("id", ids));
		List<Authority> list = c.list();
		Set<Authority> setData = new HashSet<Authority>(list);
		return setData;
	}
	
	public Page<Authority> getChildAuthority(Page<Authority> page, int auth_id){
		Criteria c = createCriteria();
		c.add(Restrictions.eq("parents.id", auth_id));
		return findPageByCriteria(page, c);
	}

}
