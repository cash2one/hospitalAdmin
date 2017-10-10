/*
 * @文件名： FamilyDoctorinfoDaoImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-17
 * @包名： com.jumper.hospital.dao.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.FamilyDoctorinfoDao;
import com.jumper.hospital.entity.FamilyDoctorinfo;
import com.jumper.hospital.vo.familyDoctor.VOCity;
import com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo;
import com.jumper.hospital.vo.familyDoctor.VOProvince;

/**
 * 类名称：FamilyDoctorinfoDaoImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-17 下午1:44:48
 * 修改人：aaron
 * 修改时间：2016-2-17 下午1:44:48
 * 修改备注：
 * @version  1.0
 */
@Repository
public class FamilyDoctorinfoDaoImpl extends BaseDaoImpl<FamilyDoctorinfo, Integer> implements FamilyDoctorinfoDao
{

    /**
     * @see com.jumper.hospital.dao.FamilyDoctorinfoDao#findPageResultByids(com.jumper.hospital.base.Page, java.util.List)
     */
    @Override
    public Page<FamilyDoctorinfo> findPageResultByids(Page<FamilyDoctorinfo> page, List<Integer> adminids)
    {
        Criteria c = createCriteria();
        if(CollectionUtils.isNotEmpty(adminids))
        {
            c.add(Restrictions.in("admin.id", adminids));
        }
        return findPageByCriteria(page, c);
    }

    /**
     * @see com.jumper.hospital.dao.FamilyDoctorinfoDao#getDoctorByAdminID(java.lang.Integer)
     */
    @Override
    public FamilyDoctorinfo getDoctorByAdminID(Integer id)
    {
        Criteria c = createCriteria();
        c.add(Restrictions.eq("admin.id", id));
        return (FamilyDoctorinfo) c.uniqueResult();
    }

    /**
     * @see com.jumper.hospital.dao.FamilyDoctorinfoDao#getAllDoctorinfo()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void  getAllDoctorinfo( Page<FamilyDoctorinfo> page,String username)
    {
    	System.out.println(page.getFirst() - 1);
        this.getDoctorinfoTotalCount(page,username,null);
        List list = this.getSession().createQuery("select doc "
        		+ "from FamilyDoctorinfo doc join fetch doc.hospitalInfo dhp "
        		+ " where doc.hospitalId=dhp.id and doc.username "
        		+ " like '%"+username+"%' order by doc.addDate desc")
        		.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize()).list();
        page.setResult(list);
    }

    /**
     * @see com.jumper.hospital.dao.FamilyDoctorinfoDao#getDoctorinfoTotalCount()
     */
    @Override
    public Long getDoctorinfoTotalCount(Page<FamilyDoctorinfo> page,String username,Integer id)
    {
        Long totalCount=(long) 0;
        if(null==id){
             totalCount= (Long) this.getSession().createQuery("select  count(*) from FamilyDoctorinfo doc join   doc.hospitalInfo dhp  where doc.hospitalId=dhp.id and doc.username like ?").setParameter(0, "%"+username+"%").uniqueResult();
            page.setTotalCount(totalCount);
        }else{
             totalCount= (Long) this.getSession().createQuery("select  count(*) from FamilyDoctorinfo doc join   doc.hospitalInfo dhp  where doc.hospitalId=dhp.id and dhp.id="+id+"  and  doc.username like '%"+username+"%'").uniqueResult();
            page.setTotalCount(totalCount);
        }
        return totalCount;
    }

    /**
     * @see com.jumper.hospital.dao.FamilyDoctorinfoDao#getMyHospitalDoctorinfo(com.jumper.hospital.base.Page, java.lang.String, java.lang.Integer)
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void getMyHospitalDoctorinfo(Page<FamilyDoctorinfo> page, String username, Integer id)
    {
			List list = this.getSession().createQuery("select doc from FamilyDoctorinfo doc join fetch doc.hospitalInfo dhp  where doc.hospitalId=dhp.id and  dhp.id="+id+"   and doc.username like '%"+username+"%' order by doc.addDate desc").setFirstResult(page.getFirst() - 1).setMaxResults(page.getPageSize()).list();
			page.setResult(list);
			this.getDoctorinfoTotalCount(page, username, id);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<VOProvince> getAllProvinces() throws Exception {
		List<VOProvince> list = this.getSession().createQuery("select new com.jumper.hospital.vo.familyDoctor.VOProvince( p.id as id,p.proName as proName) from  Province p where p.id<500").list();
		 return list;
	}

	@Override
	public int updateById(FamilyDoctorinfo familyDoctorinfo,
			Map<String, Object> propertyMap) {
		StringBuffer setClause=new StringBuffer();//存储动态生成的set子句
		 Set<Entry<String,Object>> propertySet = propertyMap.entrySet();
		 for (Entry<String, Object> entry : propertySet) {
			 String key = entry.getKey();
			 if(setClause.length()>0){//如果set子句有值的话就在前面加逗号
					setClause.append(","+key+"=:"+key);
				}else{//没有值就不加逗号
					setClause.append(key+"=:"+key);
				}
		 }
//获取query实例
	 Query query = this.getSession().createQuery("update FamilyDoctorinfo set "+setClause.toString()+" where id=:id");
//遍历entry给参数赋值
	 for (Entry<String, Object> entry : propertySet) {
		 query.setParameter(entry.getKey(), entry.getValue());
	 }
	 query.setParameter("id",familyDoctorinfo.getId());
	 return query.executeUpdate();	 
	}

	@Override
	public boolean checkUsername(String username) {
		boolean flag=false;
		Long count= (Long) this.getSession().createQuery(" select count(*) from FamilyDoctorinfo f where f.username=? group by f.username").setParameter(0, username).uniqueResult();
		if(null==count){
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean checkMobile(String phone) {
		boolean flag=false;
		Long count= (Long) this.getSession().createQuery(" select count(*) from FamilyDoctorinfo f where f.phone=? group by f.phone").setParameter(0, phone).uniqueResult();
		if(null==count){
			flag=true;
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VOFamilyDoctorInfo checkFamilyDoctorInfo(
			FamilyDoctorinfo familyDoctorinfo) {
		 
		 List<VOFamilyDoctorInfo> list = this.getSession().createQuery("select new com.jumper.hospital.vo.familyDoctor.VOFamilyDoctorInfo(" +
		 		"fdc.id,fdc.hospitalId," +
		 		"fdc.provinceId," +
		 		"fdc.cityId," +
		 		"fdc.districtId," +
		 		"fdc.detailAdd," +
		 		"fdc.majorId," +
		 		"fdc.position," +
		 		"fdc.phone," +
		 		"fdc.remark," +
		 		"fdc.username," +
		 		"fdc.password," +
		 		"fdc.enabled," +
		 		"fdc.responsibleArea," +
		 		"fdc.doctorName) " +
		 		"from FamilyDoctorinfo fdc where fdc.username=? and fdc.password=?")
		 .setParameter(0, familyDoctorinfo.getUsername())
		 .setParameter(1, familyDoctorinfo.getPassword()).list();
		 if(null!=list && list.size()>0){
			 return list.get(0);
		 }else{
			 return null;
		 }
		
	}

	@Override
	public void deleteDoctorById(int id) {
		this.getSession().createQuery("delete from FamilyDoctorinfo fdc where fdc.id=?").setParameter(0, id).executeUpdate();
		
	}

	@Override
	public VOProvince getProvinceById(Integer provinceId) {
		return (VOProvince) this.getSession().createQuery("select new com.jumper.hospital.vo.familyDoctor.VOProvince(p.id,p.proName) from Province p where p.id=?").setParameter(0, provinceId).uniqueResult();
	}

	@Override
	public VOCity getCityById(Integer cityId) {
		return (VOCity) this.getSession().createQuery("select new com.jumper.hospital.vo.familyDoctor.VOCity(c.id,c.cityName) from City c where c.id=?").setParameter(0, cityId).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDoctorByid(int id) {
		
		Map<String, Object>  map=(Map<String, Object>) this.getSession().createQuery("select new map(fd.id as id,fd.hospitalId as hospitalId,fd.provinceId as provinceId," +
				" fd.cityId as cityId,fd.districtId as districtId,fd.detailAdd as detailAdd,fd.majorId as majorId,fd.position as position, " +
				"fd.phone as phone,fd.remark as remark,fd.username as username,fd.password as password,fd.responsibleArea as responsibleArea, " +
				"fd.doctorName as doctorName) from FamilyDoctorinfo fd where fd.id=:id").setParameter("id", id).uniqueResult();
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDoctorTitles() {
		List<String> list = this.getSession().createQuery("select ht.name from HospitalDoctorTitle ht").list();
		return list;
	}
	
	
	
}
















