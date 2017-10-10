package com.jumper.hospital.dao.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.FamilyExaminationDao;
import com.jumper.hospital.entity.FamilyExamination;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.familyDoctor.VOFamilyExaminationResult;

@Repository
public class FamilyExaminationDaoImpl extends BaseDaoImpl<FamilyExamination, Integer> implements FamilyExaminationDao {

	@SuppressWarnings("unchecked")
	@Override
	public void getHospitalNotExamineResultByHospitalId( Page<VOFamilyExaminationResult> page, Integer hospitalId) throws Exception {
			Object uniqueResult = this.getSession().createSQLQuery("SELECT count(*) FROM (SELECT fuser.`id`, fuser.name,fuser.age,fuser.mobile,fuser.last_period FROM `family_userinfo` fuser WHERE fuser.`family_hospital_id`=:hospitalId) AS fu ,`family_examination` fex  WHERE fu.id=fex.`userid` AND fex.`state`=0 ").setParameter("hospitalId", hospitalId).uniqueResult();
 
 Long totalCount = Long.valueOf(String.valueOf(uniqueResult));
			if(null==totalCount|| totalCount<=0) return;
			//List list = this.getSession().createSQLQuery("select result.* from (SELECT fu.id,fu.name,fu.age,fu.mobile,fu.last_period,fu.pregancy_day,fu.join_add,fex.`check_time`, fex.id as eid FROM (SELECT fuser.id, fuser.name,fuser.age,fuser.mobile,fuser.last_period,fuser.pregancy_day,fuser.join_add FROM `family_userinfo` as fuser WHERE fuser.`family_hospital_id`=:hospitalId) AS fu ,`family_examination` fex  WHERE fu.id=fex.`userid` AND fex.`state`=0 order by fex.add_time) as  result")
					List<VOFamilyExaminationResult> list = this.getSession().createSQLQuery
("SELECT "+
  "result.age AS age, "+
 " result.id AS userid, "+
 " result.name AS name, "+
 " result.mobile AS mobile, "+
 " result.last_period AS lastPeriod, "+
"  result.pregancy_day AS pregancyDay, "+
 " result.join_add AS joinAdd, "+
 " result.add_time AS checkTime, "+
 " result.eid AS examinationId  "+
"FROM "+
 " (SELECT  "+
   " fu.id, "+
   " fu.name, "+
   " fu.age, "+
   " fu.mobile, "+
   " fu.last_period, "+
  "  fu.pregancy_day, "+
  "  fu.join_add, "+
  "  fex.`add_time`, "+
  "  fex.id AS eid  "+
  " FROM "+
   "(SELECT  "+
    " fuser.id, "+
    " fuser.name, "+
    " fuser.age, "+
    " fuser.mobile, "+
    " fuser.last_period, "+
   "  fuser.pregancy_day, "+
   "   fuser.join_add  "+
 "   FROM "+
  "    `family_userinfo` AS fuser  "+
  "  WHERE fuser.`family_hospital_id` = :hospitalId) AS fu,  `family_examination` fex  "+
  "WHERE fu.id = fex.`userid`  "+
"    AND fex.`state` = 0  "+
 " ORDER BY fex.add_time) AS result ")
.setParameter("hospitalId", hospitalId)
.setFirstResult(page.getFirst()-1).setMaxResults(page.getPageSize())
.setResultTransformer(Transformers.aliasToBean(VOFamilyExaminationResult.class))
.list();
			page.setResult(list);
			for (int i = 0; i < list.size(); i++) {
				VOFamilyExaminationResult result = list.get(i);
				Date checkTime = result.getCheckTime();//在sql语句中的add_time 就是检测时间
				Date pregancyDay = result.getPregancyDay();//
				if(null!=checkTime && null!=pregancyDay){
					int[] week = TimeUtils.getPregnantWeek(pregancyDay,checkTime);
					result.setGestationalWeek(week[0]);
				}
			}
			page.setTotalCount(totalCount);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getListByUserid(Integer userid) {
		List<Map<String ,Object>> list = this.getSession().createQuery(" select new map(fe.id as id ,fe.result as result, fe.addTime as addTime) from FamilyExamination fe where fe.userid=? and fe.state=1 order by fe.addTime").setParameter(0, userid).list();
		return list;
	}

	@Override
	public FamilyExamination get(Integer id) {
		return (FamilyExamination) this.getSession().createQuery("from FamilyExamination fe where fe.id=:id").setParameter("id",id).uniqueResult();
	}

	

}
