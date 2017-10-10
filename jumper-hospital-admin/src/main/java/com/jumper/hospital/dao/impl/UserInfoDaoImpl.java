package com.jumper.hospital.dao.impl;
/**
 * APP用户Dao实现类
 * @author rent
 * @date 2015-09-21
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.UserExtraInfoDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.Hospital;
import com.jumper.hospital.entity.MonitorUserInfo;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Integer> implements UserInfoDao {

	@Autowired
	private UserExtraInfoDao userExtraInfoDaoImpl;
	
	@Override
	public Page<UserInfo> findUserListByHospital(Page<UserInfo> page, Integer hospitalId, String searchKey, Integer type) {
		try {
			Criteria c = createCriteria();
			Criteria c1 = c.createCriteria("userExitInfo");
			c1.add(Restrictions.eq("commonHospital", hospitalId));
			c1.add(Restrictions.eq("currentIdentity", (byte)type.intValue()));
			if(StringUtils.isNotEmpty(searchKey)){
				if(StringUtils.isNumeric(searchKey)){
					c1.add(Restrictions.like("contactPhone", searchKey, MatchMode.START));
				}else{
					c1.add(Restrictions.like("realName", searchKey, MatchMode.START));
				}
			}
			c.addOrder(Order.desc("regTime"));
			Page<UserInfo> pageData = findPageByCriteria(page, c);
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findWeekList(Integer id) {
		SQLQuery sqlQuery = getSession().createSQLQuery("SELECT DISTINCT pregnant_week FROM user_info u ,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital=? and e.current_identity=0");
		sqlQuery.setInteger(0, id);
		return sqlQuery.list();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Integer> findUserIdsByPregnantWeek(int pregnantWeek,int hospitalId,String flags) {
		String sql = "SELECT u.id FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital="+hospitalId;
		if("0".equals(flags)){
			sql += " and e.current_identity=0";
		}else if("1".equals(flags)){
			sql += " and e.current_identity=1";
		}
		SQLQuery sqlQuery = null;
		if(pregnantWeek==-1){
			sqlQuery = getSession().createSQLQuery(sql);
		}else{
			sql += " AND u.pregnant_week="+pregnantWeek;
			sqlQuery = getSession().createSQLQuery(sql);
		}
		List list=sqlQuery.list();
		if(list.size()!=0&&list!=null)
			return list;
		return null;
		/*if(pregnantWeek==-1){
			SQLQuery sqlQuery = getSession().createSQLQuery("SELECT u.id FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital="+hospitalId);
//			sqlQuery.setInteger(0, hospitalId);
			return sqlQuery.list();
		}else{
			SQLQuery sqlQuery = getSession().createSQLQuery("SELECT u.id FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND u.pregnant_week=? AND e.common_hospital=?");
			sqlQuery.setInteger(0, pregnantWeek);
			sqlQuery.setInteger(1, hospitalId);
			return sqlQuery.list();
		}*/
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findBabyList(Integer id) {
		SQLQuery sqlQuery = getSession().createSQLQuery("SELECT DISTINCT pregnant_week FROM user_info u ,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital=? and e.current_identity=1");
		sqlQuery.setInteger(0, id);
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getExpectedDateOfPregnant(Integer id) {
		SQLQuery sqlQuery = getSession().createSQLQuery("SELECT distinct expected_date_of_confinement FROM user_info u ,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital=? and e.current_identity=0 ORDER BY expected_date_of_confinement desc");
		sqlQuery.setInteger(0, id);
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getBabyBirthdays(Integer id) {
		SQLQuery sqlQuery = getSession().createSQLQuery("SELECT distinct baby_birthday FROM user_info u ,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital=? and e.current_identity=1 ORDER BY baby_birthday desc");
		sqlQuery.setInteger(0, id);
		return sqlQuery.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findUserIdsAndDate(Integer id, int flag) {
		String sql = "";
		if(flag==0){
			sql = "SELECT u.id FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital="+id;
		}else if(flag==1){
			sql = "SELECT u.id,u.expected_date_of_confinement FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital="+id+" and e.current_identity=0";
		}else if(flag==2){
			sql = "SELECT u.id,e.baby_birthday FROM user_info u,user_extra_info e WHERE u.id=e.user_id AND e.common_hospital="+id+" and e.current_identity=1";
		}
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		List<Object> lists = sqlQuery.list();
		return lists;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> findByIds(String ids) {
		String[] idList = ids.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < idList.length; i++){
			list.add(Integer.parseInt(idList[i]));
		}
		Criteria c = createCriteria();
		c.add(Restrictions.in("id", list));
		return c.list();
	}

	@Override
	public UserInfo findByContactPhone(String phone) {
		Criteria c = createCriteria();
		Criteria c1 = c.createCriteria("userExitInfo");
		if(StringUtils.isNotEmpty(phone)){
			c1.add(Restrictions.eq("contactPhone", phone));
		}
		c.setMaxResults(1);
		c.addOrder(Order.desc("regTime"));
		return (UserInfo) c.uniqueResult();
	}

	@Override
	public UserInfo findByMobile(String mobile) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("mobile", mobile));
		c.setMaxResults(1);
		UserInfo info = (UserInfo) c.uniqueResult();
		return info;
	}

	@Override
	public UserInfo createInsideAppUser(MonitorUserInfo monitorUserInfo ,String edcDate) {
		try {
			UserInfo user = new UserInfo();
			user.setStatus(1);
			user.setMobile(monitorUserInfo.getMobile());
			user.setPassword(Consts.APP_DEFAULT_PASSWORD);
			user.setIsSwitchPushMsg(1);
			if(edcDate!=null){
				user.setExpectedDateOfConfinement(TimeUtils.getTimestampDate(edcDate, Consts.FORMAT_TIME_THREE));
			}
			user.setRegTime(TimeUtils.getCurrentTime());
			user.setPregnantStage(2);
			if(monitorUserInfo.getEdc()!=null){
				int[] pregnantWeek_415 = TimeUtils.getPregnantWeek_415(monitorUserInfo.getEdc(), new Date());
				user.setPregnantWeek(pregnantWeek_415[0]);
			}
			UserInfo result = persist(user);
			
			UserExtraInfo eInfo = new UserExtraInfo();
			eInfo.setCheckStatus(0);
			eInfo.setRealName(monitorUserInfo.getRealName());
			eInfo.setAge(monitorUserInfo.getAge());
			eInfo.setCurrentIdentity((byte)0);
			eInfo.setContactPhone(monitorUserInfo.getMobile());
			eInfo.setUserInfo(result);
			eInfo.setMobileType(0);
			Date lastPeriod= TimeUtils.getCurrentStartTime(-280,monitorUserInfo.getEdc());
			eInfo.setLastPeriod(lastPeriod);
			//eInfo.setAddress(monitorUserInfo.getAddress());
			userExtraInfoDaoImpl.save(eInfo);
			
			return result;
		} catch (Exception e) {
			logger.info(Consts.ERROR_TAG+"添加院内监护订单异常，异常原因："+e.getMessage());
			return null;
		}
	}

	@Override
	public UserInfo findByUserId(Integer id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("id", id));
		if(c.list().size()>0){
			return (UserInfo) c.list().get(0);
		}else{
			return null;
		}
		
	}

	@Override
	public UserExtraInfo findUserExtra(Integer id) {
		String sql = "select * from user_extra_info where user_id = ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(UserExtraInfo.class);
		query.setInteger(0, id);
		if(query.list().size()>0){
			return (UserExtraInfo) query.list().get(0);
		}else{
			return null;
		}
	}

	@Override
	public UserExtraInfo findUserExtra(String phone) {
		String sql = "select count(*) from user_extra_info where contact_phone = ?";
		Query query = this.getSession().createSQLQuery(sql).addEntity(UserExtraInfo.class);
		query.setString(0, phone);
		return (UserExtraInfo) query.list().get(0);
	}

	//今日新增用户老版ios
	@Override
	public int todayNewUserCountbyIos(Integer id) {
		//String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ? and date_format(binding_date,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') ";
		String sql = "select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ? and date_format(this_.bind_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')";
		//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and date_format(cd.register_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, id);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	//今日新增用户
	@Override
	public int todayNewUserCount(Integer id) {
		String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ? and date_format(binding_date,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') ";
		//String sql = "select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ? and date_format(this_.bind_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d')";
		//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and date_format(cd.register_time,'%Y-%m-%d') = date_format(now(),'%Y-%m-%d') ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, id);
		return Integer.parseInt(query.uniqueResult().toString());
	}
	
	//总用户数量
	@Override
	public int allUserCountbyIos(Integer id) {
		//String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ?";
		String sql = " select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ?"; 
		//Strinng sql = "select count(cd.bind_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ?  and cd.bind_time <> ''";
		//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and cd.register_time <> '' ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, id);
		Integer i  =Integer.parseInt(query.uniqueResult().toString());
		return i;
	}
	
	//总用户数量
	@Override
	public int allUserCount(Integer id) {
		String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ?";
		//String sql = " select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ?"; 
		//Strinng sql = "select count(cd.bind_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ?  and cd.bind_time <> ''";
		//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and cd.register_time <> '' ";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, id);
		Integer i  =Integer.parseInt(query.uniqueResult().toString());
		return i;
	}

	//条件新增用户数量
	
	public int newUserCountbyIos(String startTime, String endTime,Integer id) {
		//String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ? and date_format(binding_date,'%Y-%m-%d')  between  ?  and  ?";
		String sql = "select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ?  and date_format(this_.bind_time,'%Y-%m-%d')  between  ?  and  ?";
		//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and date_format(cd.register_time,'%Y-%m-%d')  between  ?  and  ?";
		Query query = this.getSession().createSQLQuery(sql);
		query.setInteger(0, id);
			query.setString(1, startTime);
			query.setString(2, endTime);
			Integer i  =Integer.parseInt(query.uniqueResult().toString());
			return i;
	}
	
	//条件新增用户数量
	
		public int newUserCount(String startTime, String endTime,Integer id) {
			String sql = "select count(id) from bind_hospital_log  where first_binding = 1 and hospital_id = ? and date_format(binding_date,'%Y-%m-%d')  between  ?  and  ?";
			//String sql = "select count(*) as y0_ from jumper_anglesound.count_detail_info this_ inner join jumper_anglesound.count_promot_person countpromo1_ on this_.promot_id=countpromo1_.id 	where this_.is_bind=1 and countpromo1_.hospital_id = ?  and date_format(this_.bind_time,'%Y-%m-%d')  between  ?  and  ?";
			//String sql = "select count(cd.register_time) from count_promot_person c LEFT JOIN count_detail_info cd on c.id = cd.promot_id  where c.hospital_id = ? and date_format(cd.register_time,'%Y-%m-%d')  between  ?  and  ?";
			Query query = this.getSession().createSQLQuery(sql);
			query.setInteger(0, id);
				query.setString(1, startTime);
				query.setString(2, endTime);
				Integer i  =Integer.parseInt(query.uniqueResult().toString());
				return i;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Hospital> findAllHospitalInfo(String hospitalName) {
			String sql = "select * from hospital_info ";
			if(!hospitalName.equals("")){
				sql += "where name like ?";
			}
			Query query = this.getSession().createSQLQuery(sql).addEntity(Hospital.class);
			if(!hospitalName.equals("")){
				query.setString(0, "%"+hospitalName+"%");
			}
			
			return query.list();
		}
}
