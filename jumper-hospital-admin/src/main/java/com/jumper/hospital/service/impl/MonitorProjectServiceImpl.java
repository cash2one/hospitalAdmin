package com.jumper.hospital.service.impl;
/**
 * 远程监控项目Service实现类
 * @author rent
 * @date 2015-09-30
 */
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.entity.MonitorProject;
import com.jumper.hospital.service.MonitorProjectService;

@Service
public class MonitorProjectServiceImpl extends BaseServiceImpl<MonitorProject, Integer> implements MonitorProjectService {

	@Override
	public BaseDao<MonitorProject, Integer> getBaseDAO() {
		return null;
	}

}
