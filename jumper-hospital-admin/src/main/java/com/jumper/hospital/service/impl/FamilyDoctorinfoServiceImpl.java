/*
 * @文件名： FamilyDoctorinfoServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.FamilyDoctorinfoDao;
import com.jumper.hospital.dao.FamilyExaminationArrangedDao;
import com.jumper.hospital.dao.FamilyUserinfoDao;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.service.FamilyDoctorinfoService;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.familyDoctor.VOCity;
import com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationArranged;
import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

/**
 * 类名称：FamilyDoctorinfoServiceImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午3:13:47
 * 修改人：aaron
 * 修改时间：2016-2-17 下午3:13:47
 * 修改备注：
 * @version  1.0
 */
@Service
public class FamilyDoctorinfoServiceImpl extends BaseServiceImpl<FamilyDoctorinfo, Integer> implements FamilyDoctorinfoService
{

    @Autowired
    private FamilyDoctorinfoDao familyDoctorinfoDao;
    @Autowired
    private FamilyUserinfoDao familyUserinfoDao;
    @Autowired
    private FamilyExaminationArrangedDao familyExaminationArrangedDao;
    /**
     * @see com.jumper.hospital.service.impl.BaseServiceImpl#getBaseDAO()
     */
    @Override
    public BaseDao<FamilyDoctorinfo, Integer> getBaseDAO()
    {
        return familyDoctorinfoDao;
    }

    /**
     * @see com.jumper.hospital.service.FamilyDoctorinfoService#findPageResultByids(com.jumper.hospital.base.Page, java.util.List)
     */
    @Override
    public Page<FamilyDoctorinfo> findPageResultByids(Page<FamilyDoctorinfo> page, List<Integer> adminids)
    {
        return familyDoctorinfoDao.findPageResultByids(page,adminids);
    }

    /**
     * @see com.jumper.hospital.service.FamilyDoctorinfoService#getDoctorIdByAdminID(java.lang.Integer)
     */
    @Override
    public FamilyDoctorinfo getDoctorByAdminID(Integer id)
    {
        return familyDoctorinfoDao.getDoctorByAdminID(id);
    }

    /**
     * @see com.jumper.hospital.service.FamilyDoctorinfoService#getAllDoctorinfo()
     */
    @Override
    public void getAllDoctorinfo(Page<FamilyDoctorinfo> page,String username)
    {
        familyDoctorinfoDao.getAllDoctorinfo( page,username);
    }

    /**
     * @see com.jumper.hospital.service.FamilyDoctorinfoService#getMyHospitalDoctorinfo(com.jumper.hospital.base.Page, java.lang.String, java.lang.Integer)
     */
    @Override
    public void getMyHospitalDoctorinfo(Page<FamilyDoctorinfo> page, String username, Integer id)
    {
        familyDoctorinfoDao.getMyHospitalDoctorinfo(page,username,id);
    }

	@Override
	public List<VOProvince> getAllProvinces() throws Exception {
		return familyDoctorinfoDao.getAllProvinces();
	}

	@Override
	public int updateById(FamilyDoctorinfo familyDoctorinfo,Map<String, Object> propertyMap) {
		return   familyDoctorinfoDao.updateById(familyDoctorinfo,propertyMap);
	}

	@Override
	public boolean checkUsername(String username) {
		return familyDoctorinfoDao.checkUsername(username);
	}

	@Override
	public boolean checkMobile(String phone) {
		return familyDoctorinfoDao.checkMobile(phone);
	}

	@Override
	public VOFamilyDoctorInfo checkFamilyDoctorInfo(
			FamilyDoctorinfo familyDoctorinfo) {
		 
		return familyDoctorinfoDao.checkFamilyDoctorInfo(familyDoctorinfo);
	}
@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional
	@Override
	public void getTaskList(Page page, Integer id,Short state,String searchName) throws Exception{
		// 获取乡村医生对应的用户数据
		familyUserinfoDao.getFamilyUserInfo4DoctorId(page, id,searchName);
		List<VOFamilyUserinfo> result = page.getResult();
		for (int i = 0; i < result.size(); i++) {
			VOFamilyUserinfo familyUserinfo = result.get(i);
			//末次月经时间
		  Date day = new SimpleDateFormat("yyyy-MM-dd").parse(familyUserinfo.getPregancyDay());
			//设置孕周
		  if(null==day)continue;
		 Integer pregnantWeek = TimeUtils.getPregnantWeek(day)[0];
			familyUserinfo.setPregnantWeek(pregnantWeek.shortValue());
			//到排期表中查找数据
			List<VOFamilyExaminationArranged> list=familyExaminationArrangedDao.getFamilyExaminationArrangedsByUserInfoId(familyUserinfo.getId(),state);
			familyUserinfo.setTask(list);
		}
	}

@Override
public void deleteById(int id) {
	familyDoctorinfoDao.deleteDoctorById(id);
	familyUserinfoDao.removeDoctor(id);
}

@Override
public VOProvince getProvinceById(Integer provinceId) {
	return familyDoctorinfoDao.getProvinceById(provinceId);
}

@Override
public VOCity getCityById(Integer cityId) {
	return familyDoctorinfoDao.getCityById(cityId);
}

@Override
public Map<String, Object> getDoctorByid(int id) {
	return familyDoctorinfoDao.getDoctorByid(id);
}

@Override
public List<String> getDoctorTitles() {
	return familyDoctorinfoDao.getDoctorTitles();
}
}
