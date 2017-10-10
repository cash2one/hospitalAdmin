package com.jumper.hospital.dao.impl;
/**
 * 角色管理Dao实现类
 * @author rent
 * @date 2015-10-16
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.RoleDao;
import com.jumper.hospital.entity.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, Integer> implements RoleDao {

	public List<Role> getRoleList(){
		return getAll();
	}
	
	public Page<Role> getRolePage(Page<Role> page, String rolename){
		Criteria c = createCriteria();
		if(StringUtils.isNotEmpty(rolename)){
			c.add(Restrictions.eq("name", rolename));
		}
		c.addOrder(Order.asc("id"));
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getNotSystemRoles() {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("isSystem", false));
			c.add(Restrictions.eq("used", 0));
			return c.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleByUsed(Integer used) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("used", 1));
		return c.list();
	}
	
}
