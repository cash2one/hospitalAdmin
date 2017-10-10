/*
 * @文件名： FamilyVersionService.java
 * @创建人: aaron
 * @创建时间: 2016-2-24
 * @包名： com.jumper.hospital.service
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service;

import com.jumper.hospital.entity.FamilyExaminationArranged;

/**
 * 类名称：FamilyVersionService
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-24 下午4:51:14
 * 修改人：aaron
 * 修改时间：2016-2-24 下午4:51:14
 * 修改备注：
 * @version  1.0
 */
public interface FamilyExaminationArrangedService extends BaseService<FamilyExaminationArranged, Integer>
{

	void saveFamilyExaminationArranged(
			FamilyExaminationArranged examinationArranged);

}
