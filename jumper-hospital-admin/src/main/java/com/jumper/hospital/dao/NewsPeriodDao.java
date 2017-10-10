package com.jumper.hospital.dao;

import com.jumper.hospital.entity.NewsPeriod;

public interface NewsPeriodDao extends BaseDao<NewsPeriod, Integer> {

	/**
	 * 编辑资讯时保存对应的阶段
	 * @param np
	 */
	public void saveNewsPeriod(NewsPeriod np);
}
