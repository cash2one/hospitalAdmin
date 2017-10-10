/*
 * @文件名： FamilyUserinfoDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.FamilyUserinfoDao;
import com.jumper.hospital.entity.City;
import com.jumper.hospital.entity.District;
import com.jumper.hospital.entity.FamilyUserinfo;
import com.jumper.hospital.entity.Province;
import com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo;

/**
 * 类名称：FamilyUserinfoDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:33:46
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:33:46
 * 修改备注：
 * @version  1.0
 */
@Repository
public class FamilyUserinfoDaoImpl extends BaseDaoImpl<FamilyUserinfo, Integer> implements FamilyUserinfoDao
{
/*	 static {
		    //注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
		    ConvertUtils.register(new SqlDateConverter(), java.util.Date.class);
		    //ConvertUtils.register(new SqlTimestampConverter(), java.sql.Timestamp.class);
		  }
*/
    public FamilyUserinfoDaoImpl() {
    }
	public FamilyUserinfoDaoImpl(Class<FamilyUserinfo> entityClass) {
		super(entityClass);
	}

	/**
     * @see com.jumper.hospital.dao.FamilyUserinfoDao#findPageResultByIDS(java.util.List)
     */
    @Override
    public Page<FamilyUserinfo> findPageResultByIDS(Page<FamilyUserinfo> page,List<Integer> userids)
    {
        Criteria c = createCriteria();
        if(CollectionUtils.isNotEmpty(userids))
        {
            c.add(Restrictions.in("id", userids));
        }
        return findPageByCriteria(page, c);
    }

    /**
     * @see com.jumper.hospital.dao.FamilyUserinfoDao#getProvices()
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<Province> getProvices()
    {
    	List list = this.getSession().createQuery("select new Province(p.id,p.proName)  from Province p").list();
        return list;
    }

    /**
     * @see com.jumper.hospital.dao.FamilyUserinfoDao#getCities()
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<City> getCities()
    {
        return this.getSession().createQuery("select new City (c.id,c.cityName) from City c").list();
    }

    /**
     * @see com.jumper.hospital.dao.FamilyUserinfoDao#getCitiesByProvinceId(int)
     */
    @SuppressWarnings("unchecked")
	@Override
    public List<City> getCitiesByProvinceId(int id)
    {
        return this.getSession().createQuery("select new City(c.id,c.cityName) from City c where c.province.id=?").setParameter(0, id).list();
    }

    /**
     * @see com.jumper.hospital.dao.FamilyUserinfoDao#getDistrictsByCityId(java.lang.Integer)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<District> getDistrictsByCityId(Integer id)
    {
        List list = this.getSession().createQuery("select new District(d.id,d.name) from District d where d.cityId=?").setParameter(0, id).list();
        return list;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getAllFamilyUserInfos(Page<Map> page,String condition) throws Exception
    {
    	 Long totalCount= (Long) this.getSession().createQuery("select count(*) from FamilyUserinfo fu where fu.name like '%"+condition+"%' or fu.mobile like '%"+condition+"%'" ).uniqueResult();
    	 if(null!=totalCount){ 
    		List<Map>  list1 = this.getSession().createQuery
    				("select new map(fu.name as name," +
    						"fu.age as age," +
    						"fu.mobile as mobile," +
    						"fu.lastPeriod as lastPeriod," +
    						"fu.id as id," +
    						"fu.joinAdd as joinAdd," +
    						"fu.pregancyDay as pregancyDay,fu.state as state)" +
    						" from FamilyUserinfo fu where fu.name like '%"+condition+"%' or fu.mobile like '%"+condition+"%'" )
    				.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize()).list();
    				page.setResult(list1);
    	 }
       page.setTotalCount(totalCount);
    }
	@SuppressWarnings({ "rawtypes", "unused" })
	private <T> void map2BeanList(List<T> list, List<Map> listMap,Class<T> clazz)throws Exception {
		for (int i = 0; i < listMap.size(); i++) {
			T instance = clazz.newInstance();
			BeanUtils.copyProperties(instance, listMap.get(i));
			list.add(instance);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getFamilyUserInfo4hospitalId(Page<Map> page,
			Integer hospitalId,String condition) throws Exception{
		List<Map>  listMap=null;
		try {
			Long totalCount= (Long) this.getSession().createQuery
("select count(*) from FamilyUserinfo fu where fu.familyHospitalId=:id and (fu.name like '%"+condition+"%' or fu.mobile like '%"+condition+"%')")
.setParameter("id", hospitalId).uniqueResult();
			if(null!=totalCount){
				   listMap= this.getSession().createQuery(
						"select new map(fu.name as name,fu.id as id,"
								+ "fu.mobile as mobile,"
								+ "fu.age as age,"
								+ "date_format(fu.lastPeriod,'%Y-%m-%d') as lastPeriod,"
								+ "fu.pregancyDay as pregancyDay,"
								+ "fu.joinAdd as joinAdd,fu.state as state) "
								+ "from FamilyUserinfo fu where fu.familyHospitalId=? and (fu.name like '%"+condition+"%' or fu.mobile like '%"+condition+"%')").setParameter(0, hospitalId)
								.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize()).list();
			}
			page.setTotalCount(totalCount);
			page.setResult(listMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	@SuppressWarnings({ "unchecked", "null", "rawtypes" })
	@Override
	public void getFamilyUserInfo4DoctorId(Page page, Integer id,String searchName) throws Exception{
		List<VOFamilyUserinfo> list=new ArrayList<VOFamilyUserinfo>();
		Long totalCount= (Long) this.getSession().createQuery("select count(*) from FamilyUserinfo fu where fu.familyDoctorId=? and fu.name like '%"+searchName+"%'").setParameter(0, id).uniqueResult();
		if(null!=totalCount || 0!=totalCount){
				list = this.getSession().createQuery(
					"select new com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo( fu.id as id,fu.img as img,fu.name as name, fu.age as age, fu.mobile as mobile,fu.identity as identity,fu.lastPeriod as lastPeriod,fu.pregancyDay as pregancyDay,fu.provinceId as provinceId,fu.cityId as cityId ,fu.detailAdd as detailAdd, fu.familyDoctorId as familyDoctorId,fu.familyHospitalId as familyHospitalId,fu.regTime as regTime,fu.lastTime as lastTime) from FamilyUserinfo fu where fu.familyDoctorId=?  and fu.name like '%"+searchName+"%'").setParameter(0, id)
							.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize()).list();
		     
		
		}
		
		page.setTotalCount(totalCount);
	    page.setResult(list);
		
	}
	@Override
	public void removeDoctor(int id) {
		this.getSession().createQuery("update FamilyUserinfo fu set fu.familyDoctorId=null where fu.familyDoctorId=?").setParameter(0, id).executeUpdate();
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<VOFamilyUserinfo> getUserInfos4Arranged(Short state, Page<VOFamilyUserinfo> page, Integer doctorid, String searchName) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = format.format(new Date());
		//先从FamilyExaminationArranged找到当天未完成的排期的userid 在拿这些id到用户表中找对应的用户数据
		List list = this.getSession().createQuery("select new com.jumper.hospital.vo.familyDoctor.VOFamilyUserinfo" +
				"( fu.id as id,fu.img as img,fu.name as name, fu.age as age," +
				" fu.mobile as mobile,fu.identity as identity," +
				"fu.lastPeriod as lastPeriod,fu.pregancyDay as pregancyDay," +
				"fu.provinceId as provinceId,fu.cityId as cityId ," +
				"fu.detailAdd as detailAdd, fu.familyDoctorId as familyDoctorId," +
				"fu.familyHospitalId as familyHospitalId,fu.regTime as regTime," +
				"fu.lastTime as lastTime) " +
				"from FamilyUserinfo fu where fu.id in(" +
				"select fea.userid from FamilyExaminationArranged fea where fea.homeVisitsDate=:homeVisitsDate and fea.state=:state and fea.doctorid=:doctorid group by fea.userid) " +
				"and fu.name like '%"+searchName+"%'")
		.setTimestamp("homeVisitsDate", java.sql.Date.valueOf(currentDate))
		.setParameter("state",state)
		.setParameter("doctorid", doctorid)
		.setFirstResult(page.getFirst()-1)
		.setMaxResults(page.getPageSize()).list();
		page.setResult(list);
		return list;
	}
	@Override
	public String getProvinceCityByUserInfo(FamilyUserinfo userinfo) {
		String str = (String) this.getSession().createSQLQuery("SELECT case when p.proName != c.cityName then CONCAT(p.`proName`,c.`cityName`) else p.proName end AS address " +
				"FROM family_userinfo u ,province p ,`city` c WHERE u.`city_id`=c.`id` AND c.`proId`=p.`id` AND u.id=?")
				.setParameter(0, userinfo.getId()).uniqueResult();
		return str;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FamilyUserinfo> getUserByMobile(String mobile) {
		List<FamilyUserinfo> list = this.getSession().createQuery("from FamilyUserinfo u where u.mobile=?").setParameter(0, mobile).list();
		return list;
	}
	
	 
}