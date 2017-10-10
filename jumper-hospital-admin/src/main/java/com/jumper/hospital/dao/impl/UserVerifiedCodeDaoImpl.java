package com.jumper.hospital.dao.impl;
/**
 * 验证码操作Dao实现类
 * @author rent
 * @date 2015-09-18
 */
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.UserVerifiedCodeDao;
import com.jumper.hospital.entity.UserVerifiedCode;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class UserVerifiedCodeDaoImpl extends BaseDaoImpl<UserVerifiedCode, Integer> implements UserVerifiedCodeDao {

	private static final Logger logger = Logger.getLogger(UserVerifiedCodeDaoImpl.class);
	
	@Override
	public boolean doCheckCode(String mobile, String code) {
		try {
			Criteria c = createCriteria();
			c.add(Restrictions.eq("mobile", mobile));
			c.add(Restrictions.eq("code", code));
			c.setProjection(Projections.rowCount());
			Long rowCount = (Long) c.uniqueResult();
			if(rowCount == 1){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(TimeUtils.getCurrentTime("")+"--校验验证码失败！异常信息："+e.getMessage());
		}
		return false;
	}
	
	/**
	 * 通过手机号码查询验证码
	 * @param mobile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findUserVerifiedCodeList(String mobile) {
//		Criteria c = createCriteria();
//		c.add(Restrictions.like("mobile", mobile, MatchMode.ANYWHERE));
//		c.addOrder(Order.desc("addTime"));
//		List<UserVerifiedCode> list = c.list();
		Query query = this.getSession().createSQLQuery("select id, mobile, add_time, code from user_verified_code where mobile=? and add_time>=date_sub(now(), interval 3 minute) ORDER BY add_time DESC limit 1");
		if(mobile!=null && !mobile.equals("")) {
			query.setString(0, mobile);
		}
		return query.list();
	}
}