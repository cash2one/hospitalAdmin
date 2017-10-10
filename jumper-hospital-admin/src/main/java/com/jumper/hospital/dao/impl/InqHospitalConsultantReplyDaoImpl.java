package com.jumper.hospital.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.InqHospitalConsultantReplyDao;
import com.jumper.hospital.entity.InqHospitalConsultant;
import com.jumper.hospital.entity.InqHospitalConsultantReply;
import com.jumper.hospital.utils.TimeUtils;

@Repository
public class InqHospitalConsultantReplyDaoImpl extends BaseDaoImpl<InqHospitalConsultantReply, Integer>
		implements InqHospitalConsultantReplyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<InqHospitalConsultantReply> findReplysById(Integer idInt) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("inqHospitalConsultant.id", idInt));
		return c.list();
	}

	@Override
	public void addHospitalConsultantReply(
			InqHospitalConsultant inqHospitalConsultant, Integer consId,
			String hospitalReply) {
		InqHospitalConsultantReply inqHospitalConsultantReply = new InqHospitalConsultantReply();
		inqHospitalConsultantReply.setInqHospitalConsultant(inqHospitalConsultant);
		inqHospitalConsultantReply.setReplyContent(hospitalReply);
		inqHospitalConsultantReply.setAddTime(TimeUtils.getCurrentTime());
		inqHospitalConsultantReply.setTalker(1);
		save(inqHospitalConsultantReply);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InqHospitalConsultantReply findHospitalReplysById(int consultId) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("inqHospitalConsultant.id", consultId));
		c.add(Restrictions.eq("talker", 1));
		c.addOrder(Order.desc("addTime"));
		List<InqHospitalConsultantReply> replys = c.list();
		if(replys!=null&&replys.size()>0){
			return replys.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InqHospitalConsultantReply> findUserReplysById(int consultId,
			String lastTime) {

		Criteria c = createCriteria();
		c.add(Restrictions.eq("inqHospitalConsultant.id", consultId));
		c.add(Restrictions.eq("talker", 0));
		c.add(Restrictions.gt("addTime",TimeUtils.getTimestampDate(lastTime,"yyyy-MM-dd HH:mm:ss")));
		List<InqHospitalConsultantReply> list = c.list();
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
		
	}
}
