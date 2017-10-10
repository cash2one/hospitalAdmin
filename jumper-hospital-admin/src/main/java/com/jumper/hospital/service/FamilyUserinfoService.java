/*
 * @文件名： FamilyUserinfoService.java
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
import com.jumper.hospital.entity.City;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.entity.FamilyUserinfo;

import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;

/**
 * 类名称：FamilyUserinfoService
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午3:07:09
 * 修改人：aaron
 * 修改时间：2016-2-17 下午3:07:09
 * 修改备注：
 * @version  1.0
 */
public interface FamilyUserinfoService extends BaseService<FamilyUserinfo, Integer>
{

    /**
     * findPageResultByIDS(查询医生下面的患者)
     * @param @param page
     * @param @param userids
     * @param @return
     * @return Page<FamilyUserinfo>
     * @Exception 异常对象
    */
    Page<FamilyUserinfo> findPageResultByIDS(Page<FamilyUserinfo> page, List<Integer> userids);

    /**
     * getProvices(获取省份信息)
     * @param @return
     * @return List<Provice>
     * @Exception 异常对象
    */
    List<com.jumper.hospital.entity.Province> getProvices();

    /**
     * getCities(这里用一句话描述这个方法的作用)
     * @param @return
     * @return List<City>
     * @Exception 异常对象
    */
    List<City> getCities();

    /**
     * getCitiesByProvinceId(根据省份得到对应的城市数据)
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
     * getAllFamilyUserInfos(给超级管理员使用,获取所有的家庭医生的用户)
     * @param condition 
     * @param @param page
     * @return void
     * @throws Exception
     * @Exception 异常对象
    */
    @SuppressWarnings("rawtypes")
	void getAllFamilyUserInfos(Page<Map> page, String condition) throws Exception;
/**
 * 根据医院id获取所在该医院的用户数据
 * @param page
 * @param hospitalId
 * @param condition 
 * @throws Exception
 */
	@SuppressWarnings("rawtypes")
	void getFamilyUserInfo4hospitalId(Page<Map> page, Integer hospitalId, String condition) throws Exception;
/**
 * 根据医生id获取对应的用户数据
 * @param page
 * @param doctorid
 * @param searchName
 * @throws Exception 
 */
void getFamilyUserInfosByDoctorId(Page<VOFamilyUserinfo> page,
		Integer doctorid, String searchName) throws Exception;
/**
 * 获取省市地址
 * @param userinfo
 * @return
 */
String getProvinceCityByUserInfo(FamilyUserinfo userinfo);
/**
 * 校验手机号码
 * @param mobile
 * @return
 */
Boolean checkMobile(String mobile);




}
