package com.jumper.hospital.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UsersBookBasicInfoDao;
import com.jumper.hospital.entity.UsersBookBasicInfo;

@Repository
public class UsersBookBasicInfoDaoImpl extends
		BaseDaoImpl<UsersBookBasicInfo, Integer> implements
		UsersBookBasicInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<UsersBookBasicInfo> findPregnantBookList(
			Page<UsersBookBasicInfo> page, String keyWords,Integer hospitalId) {
		Criteria c = createCriteria();
		if(StringUtils.isNotEmpty(keyWords)){
			c.add(Restrictions.or(Restrictions.like("mobile", keyWords,MatchMode.ANYWHERE),
					Restrictions.or(Restrictions.like("ID_no", keyWords,MatchMode.ANYWHERE),
									Restrictions.like("name", keyWords,MatchMode.ANYWHERE))));
		}
		/*String sql="select user_id from user_hospital_pregnant_archives where is_commit=1 and hospital_id="+hospitalId;
		logger.info("the query sql is :"+sql);
		Query query=getSession().createSQLQuery(sql);
		List<Integer> userIdList = query.list();
		if(userIdList.size()!=0&&userIdList!=null)
			c.add(Restrictions.in("userInfo.id", userIdList.toArray()));
		else {
			return new Page<UsersBookBasicInfo>();
		}*/
		String sql = "select id from user_hospital_pregnant_archives uhpa where is_commit=1 and hospital_id="+hospitalId+" and user_id=(select user_id from user_extra_info where user_id=uhpa.user_id and common_hospital="+hospitalId+")";
		Query query=getSession().createSQLQuery(sql);
		List<Integer> userIdList = query.list();
		if(userIdList.size()!=0&&userIdList!=null)
			c.add(Restrictions.in("userHospitalPregnantArchives.id", userIdList.toArray()));
		else {
			return new Page<UsersBookBasicInfo>();
		}
		return findPageByCriteria(page, c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsersBookBasicInfo findUsersBookBasicInfo(int id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("userInfo.id", id));
		List<UsersBookBasicInfo> list = c.list();
		if(list.size()>1){
			return list.get(0);
		}
		return (UsersBookBasicInfo) c.uniqueResult();
	}

	@Override
	public void updateUsersBookBasicInfo(Integer id, int id2) {
		getSession().createSQLQuery("update user_hospital_pregnant_archives set is_commit=0 where user_id="+id2+" and hospital_id="+id).executeUpdate();
	}


}
