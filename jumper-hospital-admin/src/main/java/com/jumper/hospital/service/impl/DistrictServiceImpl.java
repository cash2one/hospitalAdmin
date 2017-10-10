/*
 * @文件名： ProvinceServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-22
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.DistrictDao;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.service.DistrictService;

/**
 * 类名称：ProvinceServiceImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-22 下午3:48:53
 * 修改人：aaron
 * 修改时间：2016-2-22 下午3:48:53
 * 修改备注：
 * @version  1.0
 */
@Service
public class DistrictServiceImpl extends BaseServiceImpl<District, Integer> implements DistrictService
{
    @Autowired
    private DistrictDao districtDao;

    /**
     * @see com.jumper.hospital.service.impl.BaseServiceImpl#getBaseDAO()
     */
    @Override
    public BaseDao<District, Integer> getBaseDAO()
    {
        return districtDao;
    }

    /**
     * @see com.jumper.hospital.service.DistrictService#getDistriceListByCityID(java.lang.Integer)
     */
    @Override
    public List<District> getDistriceListByCityID(Integer id)
    {
        return districtDao.getDistriceListByCityID(id);
    }

}
