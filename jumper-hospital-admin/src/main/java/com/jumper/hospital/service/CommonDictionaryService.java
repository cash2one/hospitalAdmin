package com.jumper.hospital.service;
/**
 * 字典操作类Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.entity.CommonDictionary;

public interface CommonDictionaryService extends BaseService<CommonDictionary, Integer> {

	/**
	 * 通过关连ID和类型来获取字典记录
	 * @param relationId 关连ID
	 * @param type 字典记录类型
	 * @return
	 */
	public List<CommonDictionary> getByRelationIdAndType(Integer relationId, Integer type);
	
	/**
	 * 添加常用字典信息
	 * @param type 字典类型
	 * @param description 字典描述字段
	 * @param relationId 关联ID
	 * @return
	 */
	public CommonDictionary addCommonDictionary(Integer type, String description, Integer relationId);

	/**
	 * 查询已经添加了多少条注意事项模板
	 * @param type
	 * @param id
	 * @return
	 */
	public Integer getCommonDictionayCount(Integer type, Integer hospitalId);
}
