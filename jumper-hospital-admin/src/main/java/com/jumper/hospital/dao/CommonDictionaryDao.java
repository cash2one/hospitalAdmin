package com.jumper.hospital.dao;
/**
 * 字典Dao
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.entity.CommonDictionary;

public interface CommonDictionaryDao extends BaseDao<CommonDictionary, Integer> {

	/**
	 * 通过常用关联ID和类型获取记录
	 * @param relationId 关联ID
	 * @param type 记录类型
	 * @return
	 */
	public List<CommonDictionary> getByRelationIdAndType(Integer relationId, Integer type);

	/**
	 * 查询医院有多少条注意事项
	 * @param type
	 * @param hospitalId
	 * @return
	 */
	public Integer getCommonDictionayCount(Integer type, Integer hospitalId);
}
