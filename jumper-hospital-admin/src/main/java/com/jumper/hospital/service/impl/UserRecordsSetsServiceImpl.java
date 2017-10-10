package com.jumper.hospital.service.impl;
/**
 * 监控测试数据service实现类
 * @author rent
 * @date 2015-09-29
 */
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.entity.UserRecordsSets;
import com.jumper.hospital.service.UserRecordsSetsService;

@Service
public class UserRecordsSetsServiceImpl extends BaseServiceImpl<UserRecordsSets, Integer> implements UserRecordsSetsService {

	@Override
	public BaseDao<UserRecordsSets, Integer> getBaseDAO() {
		return null;
	}

}
