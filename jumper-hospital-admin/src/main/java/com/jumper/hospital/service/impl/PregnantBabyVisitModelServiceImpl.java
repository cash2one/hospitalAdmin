package com.jumper.hospital.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.PregnantBabyVisitModelDao;
import com.jumper.hospital.entity.VisiterBabyGuidance;
import com.jumper.hospital.service.PregnantBabyVisitModelService;

@Service
public class PregnantBabyVisitModelServiceImpl extends BaseServiceImpl<VisiterBabyGuidance, Integer> implements PregnantBabyVisitModelService{

	@Autowired
	private PregnantBabyVisitModelDao pregnantBabyVisitModelDao ;
		
	@Override
	public BaseDao<VisiterBabyGuidance, Integer> getBaseDAO() {
		return pregnantBabyVisitModelDao;
	}

	@Override
	public boolean updateModelById(VisiterBabyGuidance visitBaby) {
		boolean result = true;
		try {
			pregnantBabyVisitModelDao.update(visitBaby);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VisiterBabyGuidance> findModelByType(String type) {
		List<VisiterBabyGuidance>  list = null ;
		try {
			Criteria c = this.pregnantBabyVisitModelDao.createCriteria();
			c.add(Restrictions.eq("type", type));
			list = c.list();
//			String sql = "SELECT * FROM visiter_baby_guidance WHERE TYPE ="+type;
//			list =  (List<VisiterBabyGuidance>)pregnantBabyVisitModelDao.executeNativeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public  String queryContentById(String[] adviceArr,String adviceType) {
		StringBuffer adviceBuffer = new StringBuffer();
		VisiterBabyGuidance guidance = null;
		if(adviceArr.length > 0 ){
			for(int i=0;i<adviceArr.length;i++){
				Criteria c = this.pregnantBabyVisitModelDao.createCriteria();
				c.add(Restrictions.eq("id", Integer.valueOf(adviceArr[i])));
				c.add(Restrictions.eq("type", adviceType));
				guidance = (VisiterBabyGuidance) c.uniqueResult();
				adviceBuffer.append(guidance.getName()+",");
			}
		}
		return adviceBuffer.toString();
	}

}
