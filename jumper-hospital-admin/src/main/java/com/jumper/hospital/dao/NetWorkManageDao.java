
package com.jumper.hospital.dao;

import java.util.List;

import com.jumper.hospital.entity.Admin;


/**
 * 类名称：NetWorkManageDao
 * 类描述：
 * 创建人：wy
 * 创建时间：2016-8-20 下午1:43:31
 */
public interface NetWorkManageDao extends BaseDao<Admin, Integer>
{

    

/**
* 获取医生职称
* @return
*/
public List<Object> getHospitalDoctorTitleList(Integer hospitalId);


/**
 * 根据科室关联表id 得到科室ID
 * @param adminId
 * @return
 */
public List<Integer> getDoctorMajorIdByMajorInfoId(Integer id);



}
