/*
 * @文件名： ProcessReqService.java
 * @创建人: aaron
 * @创建时间: 2016-2-19
 * @包名： com.jumper.hospital.service
 * @版本： 1.0
 * 版权所有 © 深圳京柏医疗
 * 描述:
*/
package com.jumper.hospital.service;

import com.jumper.hospital.utils.ReturnMsg;

/**
 * 类名称：ProcessReqService
 * 类描述：
 * 创建人：aaron
 * 创建时间：2016-2-19 下午4:26:16
 * 修改人：aaron
 * 修改时间：2016-2-19 下午4:26:16
 * 修改备注：
 * @version  1.0
 */
public interface ProcessReqService
{

    /**
     * login(登录)
     * @param @param rootNode
     * @param @return
     * @return Object
     * @throws Exception 
     * @Exception 异常对象
    */
    public ReturnMsg login(String data) throws Exception;

    /**
     * getTaskList(获得任务列表)
     * @param @param rootNode
     * @param @return
     * @return ReturnMsg
     * @throws Exception 
     * @Exception 异常对象
    */
    public ReturnMsg getTaskList(String data) throws Exception;

 

/**
 * 家庭医生建档
 * @param data
 * @return
 * @throws Exception 
 */
public ReturnMsg saveFamilyUserInfo(String data);
/**
 * 创建或修改下次访问时间
 * @param data
 * @return
 * @throws Exception 
 */

ReturnMsg getFamilyUserInfos(String data) throws Exception;

ReturnMsg updateFamilyDoctor(String data) throws Exception;

ReturnMsg createOrUpdateNextArrangedTime(String data) throws Exception;
	
}
