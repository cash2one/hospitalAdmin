/*
 * @文件名： ProvinceDao.java
 * @创建人: aaron
 * @创建时间: 2016-2-22
 * @包名： com.jumper.hospital.dao
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.District;

/**
 * 类名称：ProvinceDao
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-22 下午3:51:55
 * 修改人：aaron
 * 修改时间：2016-2-22 下午3:51:55
 * 修改备注：
 * @version  1.0
 */
public interface DistrictDao extends BaseDao<District, Integer>
{

    /**
     * getDistriceListByCityID(根据城市id获得城市下面的区县id列表)
     * @param @param id
     * @param @return
     * @return List<District>
     * @Exception 异常对象
    */
    List<District> getDistriceListByCityID(Integer id);

}
