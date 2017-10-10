package com.jumper.hospital.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jumper.hospital.dao.NewsPeriodDao;
import com.jumper.hospital.entity.NewsPeriod;

@Repository
public class NewsPeriodDaoImpl extends BaseDaoImpl<NewsPeriod, Integer> implements NewsPeriodDao {

	@Override
	public void saveNewsPeriod(NewsPeriod np) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("newsId.id", np.getNewsId().getId()));
		c.add(Restrictions.eq("period", np.getPeriod()));
		c.setMaxResults(1);
		NewsPeriod result = (NewsPeriod) c.uniqueResult();
		if(result != null){
			return;
		}
		this.save(np);
	}

	
}
