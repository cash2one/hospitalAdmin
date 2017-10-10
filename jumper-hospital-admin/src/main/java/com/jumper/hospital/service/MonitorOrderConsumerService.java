package com.jumper.hospital.service;
/**
 * 用户已消费订单service
 * @author rent
 * @date 2015-09-22
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.MonitorOrderConsumer;
import com.jumper.hospital.enums.RemoteType;
import com.jumper.hospital.vo.VoFinishedOrder;
import com.jumper.hospital.vo.VoRemoteData;

public interface MonitorOrderConsumerService extends BaseService<MonitorOrderConsumer, Integer> {

	/**
	 * 获取未审核的订单信息
	 * @param page 分页对象
	 * @param hospitalId 医院ID
	 * @return Page
	 */
	public Page<VoRemoteData> findNotFinishOrder(Page<MonitorOrderConsumer> page, Integer hospitalId);
	
	/**
	 * 获取监测详情生成监测报告
	 * @param consumer 消费订单信息
	 * @return VoRemoteData
	 */
	public VoRemoteData getRemoteDataDetail(MonitorOrderConsumer consumer);
	
	/**
	 * 查询报告已完成订单信息
	 * @param page 分页对象
	 * @param hospitalId 医院ID
	 * @param searchKey 搜索框
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param doctorid 审批医生id
	 * @return
	 */
	public Page<VoFinishedOrder> findFinishOrderData(Page<MonitorOrderConsumer> page, Integer hospitalId, String searchKey, String startTime, String endTime, RemoteType remoteType, Integer selectId,List<Integer> doctorIds,Admin adminName);
	
	/**
	 * 查找进行试试监测的用户
	 * @param page 分页信息
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Page<VoFinishedOrder> findRealTimeOrder(Page<MonitorOrderConsumer> page, Integer hospitalId);
	
	/***
	 * 测试远程监测是否可以继续咨询
	 * @param consumerId 消费订单
	 */
	public String checkConsumerIsValidForChat(Integer consumerId);

	/***
	 * 查找完成订单详细
	 * @param id 消费订单
	 */
	public VoRemoteData findFinishOrderDataDetail(Integer id);
	
	/**
	 * 宝安妇幼与市妇幼生成报告
	 * @param consumerId 已消费订单ID
	 * @param hospitalName 医院名称
	 * @param printType 打印方式
	 * @param reason 审核结果
	 * @param doctorName 医生名称
	 */
	public Boolean xGenerateReport(HttpServletRequest request, Integer consumerId, String remark, Admin admin, Integer offset, String items, Integer source, Integer speed, Integer start, Integer end, Integer printType, String hospitalName);
	/**
	 * 查询报告已完成订单信息
	 * @param id 
	 * @param searchKey 搜索关键子
	 * @param startTime 报告开始时间
	 * @param endTime	报告结束时间
	 * @param remoteType	
	 * @param selectId
	 * @param doctorIds
	 * @param name
	 * @return
	 */
	public Integer finishOrderCount(Integer id, String searchKey,String startTime, String endTime, RemoteType remoteType,Integer selectId, 
			List<Integer> doctorIds);
	
	/**
	 * 当前医院未完成报告数
	 * @param hospitalId
	 * @return
	 */
	public Integer countNotFinishOrder(Integer hospitalId);
	/**
	 * 当前从属医院已审核完但是没有点击查看的报告
	 * @param hospitalId
	 * @return
	 */
	public Integer totalFinishUnRedReport(Integer hospitalId);

	public Boolean updateIsViewed(Integer reportId);
	
	/**自动评分版本生成报告
	 * @param request
	 * @param id
	 * @param remark
	 * @param admin
	 * @param resultItem
	 * @param source
	 * @param speed
	 * @param start
	 * @param end
	 * @param printType
	 * @param hospitalName
	 * @return
	 */
	public Boolean autoScorGenerateReport(HttpServletRequest request,
			Integer id, String remark, Admin admin, String resultItem,
			Integer source, Integer speed, Integer start, Integer end,
			Integer printType, String hospitalName);


}
