/*
 * @文件名： PregnantAntenatalServiceImpl.java
 * @创建人: aaron
 * @创建时间: 2016-2-23
 * @包名： com.jumper.hospital.service.impl
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.PregnantAntenatalDao;
import com.jumper.hospital.entity.PregnantAntenatalInfo;
import com.jumper.hospital.service.PregnantAntenatalService;

/**
 * 类名称：PregnantAntenatalServiceImpl
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-23 下午4:33:31
 * 修改人：aaron
 * 修改时间：2016-2-23 下午4:33:31
 * 修改备注：
 * @version  1.0
 */
@Service
public class PregnantAntenatalServiceImpl extends BaseServiceImpl<PregnantAntenatalInfo, Integer> implements PregnantAntenatalService
{
    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PregnantAntenatalServiceImpl.class);

    @Autowired
    private PregnantAntenatalDao pregnantAntenatalDao;

    /**
     * @see com.jumper.hospital.service.impl.BaseServiceImpl#getBaseDAO()
     */
    @Override
    public BaseDao<PregnantAntenatalInfo, Integer> getBaseDAO()
    {
        return pregnantAntenatalDao;
    }
}
