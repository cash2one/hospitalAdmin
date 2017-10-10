/*
 * @文件名： FamilyDoctorinfoService.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.service
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service;

import java.util.List;
import java.util.Map;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.vo.familyDoctor.VOCity;
import com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

/**
 * 类名称：FamilyDoctorinfoService
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午3:12:36
 * 修改人：aaron
 * 修改时间：2016-2-17 下午3:12:36
 * 修改备注：
 * @version  1.0
 */
public interface FamilyDoctorinfoService extends BaseService<FamilyDoctorinfo, Integer>
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
     * getDoctorIdByAdminID(查询登录者的医生id)
     * @param @param id
     * @param @return
     * @return FamilyDoctorinfo
     * @Exception 异常对象
    */
    FamilyDoctorinfo getDoctorByAdminID(Integer id);

    /**
     * getAllDoctorinfo(这里用一句话描述这个方法的作用)
     * TODO(这里描述这个方法的注意事项 – 可选)
     * @param page
     * @param username
     * @param @return
     * @return Page<FamilyDoctorinfo>
     * @Exception 异常对象
    */
   void getAllDoctorinfo(Page<FamilyDoctorinfo> page, String username);

    /**
     * getMyHospitalDoctorinfo(获取当前医院的所有的医家庭医生)
     * TODO(这里描述这个方法的注意事项 – 可选)
     * @param @param page
     * @param @param username
     * @param @param id
     * @return void
     * @Exception 异常对象
    */
    void getMyHospitalDoctorinfo(Page<FamilyDoctorinfo> page, String username, Integer id);
/**
 * 获取所有的
 * @return
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
 * 校验用户名唯一性
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
 * 根据用户名密码校验用户是否存在
 * @param familyDoctorinfo
 * @return 存在返回 对象  否则返回null
 */
VOFamilyDoctorInfo checkFamilyDoctorInfo(FamilyDoctorinfo familyDoctorinfo);
/**
 * 获取对家庭医生对象的任务明细
 * @param page
 * @param id
 * @param state 状态:0未完成;1已完成
 * @param searchName 
 * @return
 * @throws Exception 
 */
@SuppressWarnings("rawtypes")
void getTaskList(Page page, Integer id, Short state, String searchName) throws Exception;
/**
 * 根据乡村医生的id删除医生，并从用户表中移除改医生得外键
 * @param id
 */
void deleteById(int id);
/**
 * 根据id获取省份数据集
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
 * 使用hql的方式返回doctor信息
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