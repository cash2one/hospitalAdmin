/*
 * @文件名： FamilyUserinfoDao.java
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
import com.jumper.hospital.entity.City;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.entity.Province;
import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;

/**
 * 类名称：FamilyUserinfoDao
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:30:40
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:30:40
 * 修改备注：
 * @version  1.0
 */
public interface FamilyUserinfoDao extends BaseDao<FamilyUserinfo, Integer>
{

    /**
     * findPageResultByIDS(查询医生下面的患者)
     * @param page
     * @param @param userids
     * @param @return
     * @return Page<FamilyUserinfo>
     * @Exception 异常对象
    */
    Page<FamilyUserinfo> findPageResultByIDS(Page<FamilyUserinfo> page, List<Integer> userids);

    /**
     * getProvices(获取省份数据)
     * @param @return
     * @return List<Province>
     * @Exception 异常对象
    */
    List<Province> getProvices();

    /**
     * getCities(获取市数据)
     * @param @return
     * @return List<City>
     * @Exception 异常对象
    */
    List<City> getCities();

    /**
     * getCitiesByProvinceId(根据省份获取对应的城市数据)
     * @param @param id
     * @param @return
     * @return List<City>
     * @Exception 异常对象
    */
    List<City> getCitiesByProvinceId(int id);

    /**
     * getDistrictsByCityId(根据城市id获取对应的区县数据)
     * @param @param id
     * @param @return
     * @return List<District>
     * @Exception 异常对象
    */
    List<District> getDistrictsByCityId(Integer id);

    /**
     * getAllFamilyUserInfos(给超级管理员使用,获取所有的家庭医生的用户数据)
     * @param condition 
     * @param @param page
     * @return void
     * @throws Exception
     * @Exception 异常对象
    */
    @SuppressWarnings("rawtypes")
	void getAllFamilyUserInfos(Page<Map> page, String condition) throws Exception;
/**
 * 根据医院id获取所在的用户数据
 * @param page
 * @param hospitalId
 * @param condition 
 * @throws Exception
 */
	@SuppressWarnings("rawtypes")
	void getFamilyUserInfo4hospitalId(Page<Map> page, Integer hospitalId, String condition) throws Exception;
/**
 * 根据医生id获取用户数据
 * @param page
 * @param id
 * @param searchName 
 * @throws Exception 
 */
@SuppressWarnings("rawtypes")
void getFamilyUserInfo4DoctorId(Page page, Integer id, String searchName) throws Exception;
/**
 * 在用户表中移除对应得乡村医生
 * @param id
 */
void removeDoctor(int id);
/**
 * 从排期表中获取当天对应任务的用户
 * @param state
 * @param searchName 
 * @param id 
 * @param page 
 * @return
 */
List<VOFamilyUserinfo> getUserInfos4Arranged(Short state, Page<VOFamilyUserinfo> page, Integer id, String searchName);

String getProvinceCityByUserInfo(FamilyUserinfo userinfo);
/**
 * 根据手机号码获取用户
 * @param mobile
 * @return
 */
List<FamilyUserinfo> getUserByMobile(String mobile);



}
