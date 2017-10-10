/*
 * @文件名： FamilyUserinfoServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.FamilyUserinfoDao;
import com.jumper.hospital.entity.City;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.entity.Province;
import com.jumper.hospital.service.FamilyUserinfoService;
import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;

/**
 * 类名称：FamilyUserinfoServiceImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午3:08:11
 * 修改人：aaron
 * 修改时间：2016-2-17 下午3:08:11
 * 修改备注：
 * @version  1.0
 */
@Service
public class FamilyUserinfoServiceImpl extends BaseServiceImpl<FamilyUserinfo, Integer> implements FamilyUserinfoService
{

    @Autowired
    private FamilyUserinfoDao familyUserinfoDao;

    /**
     * @see com.jumper.hospital.service.impl.BaseServiceImpl#getBaseDAO()
     */
    @Override
    public BaseDao<FamilyUserinfo, Integer> getBaseDAO()
    {
        return familyUserinfoDao;
    }

    /**
     * @see com.jumper.hospital.service.FamilyUserinfoService#findPageResultByIDS(com.jumper.hospital.base.Page, java.util.List)
     */
    @Override
    public Page<FamilyUserinfo> findPageResultByIDS(Page<FamilyUserinfo> page, List<Integer> userids)
    {
        return familyUserinfoDao.findPageResultByIDS(page,userids);
    }

    /**
     * @see com.jumper.hospital.service.FamilyUserinfoService#getProvices()
     */
    @Override
    public List<Province> getProvices()
    {

        return familyUserinfoDao.getProvices();
    }

    /**
     * @see com.jumper.hospital.service.FamilyUserinfoService#getCities()
     */
    @Override
    public List<City> getCities()
    {

        return familyUserinfoDao.getCities();
    }

    /**
     * @see com.jumper.hospital.service.FamilyUserinfoService#getCitiesByProvinceId()
     */
    @Override
    public List<City> getCitiesByProvinceId(int id)
    {

        return familyUserinfoDao.getCitiesByProvinceId(id);
    }

    /**
     * @see com.jumper.hospital.service.FamilyUserinfoService#getDistrictsByCityId(java.lang.Integer)
     */
    @Override
    public List<District> getDistrictsByCityId(Integer id)
    {
        return familyUserinfoDao.getDistrictsByCityId(id);
    }

    /**
     * @throws Exception
     * @see com.jumper.hospital.service.FamilyUserinfoService#getAllFamilyUserInfos(com.jumper.hospital.base.Page)
     */
    @SuppressWarnings("rawtypes")
	@Override
    public void getAllFamilyUserInfos(Page<Map> page,String condition) throws Exception
    {
        familyUserinfoDao.getAllFamilyUserInfos(page, condition);
    }

	@SuppressWarnings("rawtypes")
	@Override
	public void getFamilyUserInfo4hospitalId(Page<Map> page,
			Integer hospitalId,String condition) throws Exception {
		familyUserinfoDao.getFamilyUserInfo4hospitalId(page,hospitalId,condition);
	}

	@Override
	public void getFamilyUserInfosByDoctorId(Page<VOFamilyUserinfo> page,
			Integer doctorid, String searchName) throws Exception {
		familyUserinfoDao.getFamilyUserInfo4DoctorId(page,doctorid,searchName);
	}

	@Override
	public String getProvinceCityByUserInfo(FamilyUserinfo userinfo) {
		return familyUserinfoDao.getProvinceCityByUserInfo(userinfo);
	}

	@Override
	public Boolean checkMobile(String mobile) {
		List<FamilyUserinfo> list=familyUserinfoDao.getUserByMobile(mobile);
		return  list.size()>0?false:true;
	}


}
