/*
 * @文件名： ProvinceDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-22
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.DistrictDao;
import com.jumper.hospital.entity.District;

/**
 * 类名称：ProvinceDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-22 下午3:53:54
 * 修改人：aaron
 * 修改时间：2016-2-22 下午3:53:54
 * 修改备注：
 * @version  1.0
 */
@Repository
public class DistrictDaoImpl extends BaseDaoImpl<District, Integer> implements DistrictDao
{

    /**
     * @see com.jumper.hospital.dao.DistrictDao#getDistriceListByCityID(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<District> getDistriceListByCityID(Integer id)
    {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("city.id", id));
        c.addOrder(Order.asc("id"));
        return c.list();
    }
}
