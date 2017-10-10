/*
 * @文件名： FamilyDoctorinfoDao.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao;

import java.util.List;
import java.util.Map;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.vo.familyDoctor.VOCity;
import com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

/**
 * 类名称：FamilyDoctorinfoDao
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:43:31
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:43:31
 * 修改备注：
 * @version  1.0
 */
public interface FamilyDoctorinfoDao extends BaseDao<FamilyDoctorinfo, Integer>
{

    /**
     * findPageResultByids(查询出当前管理员下面所有的医生)
     * @param @param page
     * @param @param adminids
     * @param @return
     * @return Page<FamilyDoctorinfo>
     * @Exception 异常对象
    */
    Page<FamilyDoctorinfo> findPageResultByids(Page<FamilyDoctorinfo> page, List<Integer> adminids);

    /**
     * getDoctorByAdminID(查询登录者的医生id)
     * @param @param id
     * @param @return
     * @return FamilyDoctorinfo
     * @Exception 异常对象
    */
    FamilyDoctorinfo getDoctorByAdminID(Integer id);

    /**
     * getAllDoctorinfo(给超级管理员使用,获取所有的医生数据)
     * TODO(这里描述这个方法的注意事项 – 可选)
     * @param page
     * @param username
     * @param @return
     * @return Page<FamilyDoctorinfo>
     * @Exception 异常对象
    */
  void getAllDoctorinfo(Page<FamilyDoctorinfo> page, String username);

   Long getDoctorinfoTotalCount(Page<FamilyDoctorinfo> page, String username,Integer id);

/**
 * getMyHospitalDoctorinfo(获取当前医院下面的所有的医生)
 * TODO(这里描述这个方法的注意事项 – 可选)
 * @param @param page
 * @param @param username
 * @param @param id
 * @return void
 * @Exception 异常对象
*/
void getMyHospitalDoctorinfo(Page<FamilyDoctorinfo> page, String username, Integer id);
/**
 * 获取所有的省份数据
 * @return
 * @throws Exception 
 * @throws Exception 
 * @throws Exception 
 */
List<VOProvince> getAllProvinces() throws Exception;
/**
 * 根据id更新数据
 * @param familyDoctorinfo   要更新的对象
 * @param propertyMap   要更新的参数
 */
int updateById(FamilyDoctorinfo familyDoctorinfo,
		Map<String, Object> propertyMap);
/**
 * 家庭医生账号唯一性校验
 * @param username
 * @return
 */
boolean checkUsername(String username);
/**
 * 手机号码唯一性校验
 * @param phone
 * @return
 */
boolean checkMobile(String phone);
/**
 * (根据账户密码)校验登陆的用户是否存在
 * @param familyDoctorinfo
 * @return
 */
VOFamilyDoctorInfo checkFamilyDoctorInfo(FamilyDoctorinfo familyDoctorinfo);
/**
 * 删除乡村医生
 * @param id
 */
void deleteDoctorById(int id);
/**
 * 根据id获取省份
 * @param provinceId
 * @return
 */
VOProvince getProvinceById(Integer provinceId);
/**
 * 根据id获取市数据
 * @param cityId
 * @return
 */
VOCity getCityById(Integer cityId);
/**
 * 使用hql方式获取doctor数据
 * @param id
 * @return
 */
Map<String, Object> getDoctorByid(int id);
/**
 * 获取医生职称
 * @return
 */
List<String> getDoctorTitles();

}
