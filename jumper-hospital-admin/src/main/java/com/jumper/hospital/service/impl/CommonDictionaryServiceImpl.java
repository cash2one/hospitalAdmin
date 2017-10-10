package com.jumper.hospital.service.impl;
/**
 * 字典操作Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.CommonDictionaryDao;
import com.jumper.hospital.entity.CommonDictionary;
import com.jumper.hospital.service.CommonDictionaryService;

@Service
public class CommonDictionaryServiceImpl extends BaseServiceImpl<CommonDictionary, Integer> implements CommonDictionaryService {

	@Autowired
	private CommonDictionaryDao commonDictionaryDaoImpl;
	
	@Override
	public BaseDao<CommonDictionary, Integer> getBaseDAO() {
		return commonDictionaryDaoImpl;
	}

	public List<CommonDictionary> getByRelationIdAndType(Integer relationId, Integer type){
		return commonDictionaryDaoImpl.getByRelationIdAndType(relationId, type);
	}

	@Override
	public CommonDictionary addCommonDictionary(Integer type, String description, Integer relationId) {
		if(type == null || StringUtils.isEmpty(description) || relationId == null){
			return null;
		}
		CommonDictionary cd = new CommonDictionary();
		cd.setType(type);
		cd.setDescription(description);
		cd.setRelationId(relationId);
		return commonDictionaryDaoImpl.persist(cd);
	}

	@Override
	public Integer getCommonDictionayCount(Integer type, Integer hospitalId) {
		
		return commonDictionaryDaoImpl.getCommonDictionayCount(type,hospitalId);
	}
}
