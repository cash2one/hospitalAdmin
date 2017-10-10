package com.jumper.hospital.service;

import com.jumper.hospital.entity.CloudVisitorAccount;

/**
 * 云随访
 */
public interface HospitalCloudVisitorService extends BaseService<CloudVisitorAccount, Integer>{
	
	/**
	 * @param integer 
	 * @param thirdId 
	 * 
	 * @author yanghz
	 * @date 2016-8-20 下午6:09:34
	 * @Description: 2. 随访用户注册并绑定天使医生用户
	 * @param @param visit
	 * @return void 返回类型
	 * @throws
	 *//*
	CloudVisitorAccount bindHosVisitorAccount(Map<String, Object> params, Integer docId, Integer hosId) throws Exception;
	
	*//**
	 * 
		 * @author yanghz
		 * @date 2016-8-23 上午10:47:30
		 * @Description: 判断工号是否存在
		 * @param @param workNum
		 * @param @return
		 * @return Boolean    返回类型 
		 * @throws
	 *//*
	//public Boolean checkWorkNumHasExist(String workNum);

	*//**
	 * @return 
	 * 
		 * @author yanghz
		 * @date 2016-8-23 下午4:52:51
		 * @Description: 获取随访首页地址
		 * @param @param id
		 * @param @param hospitalId
		 * @return void    返回类型 
		 * @throws
	 *//*
	CloudVisitIndex getCloudIndex(Integer id, String hospitalId)  throws Exception;
	
	*//**
	 * 
		 * @author yanghz
		 * @date 2016-9-2 上午10:34:02
		 * @Description: 胎心电话
		 * @param @param id
		 * @param @param hospitalId
		 * @param @return
		 * @param @throws Exception
		 * @return CloudVisitIndex    返回类型 
		 * @throws
	 *//*
	CloudVisitIndex callToPatient(Integer id, String hospitalId)  throws Exception;
	
	*//**
	 * 
		 * @author yanghz
		 * @date 2016-8-26 上午11:31:30
		 * @Description: 修改随访角色
		 * @param @param params
		 * @param @param docId
		 * @param @param hosId
		 * @param @return
		 * @param @throws Exception
		 * @return EditRoleMsg    返回类型 
		 * @throws
	 *//*
	public EditRoleMsg editVisitRole(Map<String, Object> params, Integer docId, Integer hosId) throws Exception;*/
}
