package com.jumper.hospital.service;
/**
 * 用户预约课程Service
 * @author rent
 * @date 2016-01-25
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.SchoolCourseAppoint;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolSignState;
import com.jumper.hospital.vo.school.VoAppointList;
import com.jumper.hospital.vo.school.VoMonthAppointResult;

public interface SchoolCourseAppointService extends BaseService<SchoolCourseAppoint, Integer> {

	/**
	 * 查询当前课程的预约及签到信息
	 * @param page
	 * @param detailId
	 * @param appointState
	 * @param signState
	 * @param searchKey
	 * @return
	 */
	public Page<SchoolCourseAppoint> findAppointAndSignListByDetailId(Page<SchoolCourseAppoint> page, 
			Integer detailId, SchoolAppointState appointState, SchoolSignState signState, String searchKey);
	/**
	 * 批量缴费
	 * @param ids id列表
	 * @return
	 */
	public String batchPay(String ids);
	/**
	 * 批量退费
	 * @param ids
	 * @return
	 */
	public String batchBack(String ids);
	/**
	 * 批量推送
	 * @param ids
	 * @return
	 */
	public String batchPush(String content, String ids, Integer pushType);
	/**
	 * 导出excel数据
	 * @return
	 */
	public List<VoAppointList> getExportAppointData(Integer detailId);
	/**
	 * 查询一个课程的预约及签到量
	 * @param hospitalId
	 * @param detailId
	 * @return
	 */
	public VoMonthAppointResult findAppointSignByCourseId(Integer hospitalId, Integer detailId);
	/**
	 * 查询出所有预约人的id
	 * @param id
	 * @return
	 */
	public String getOppointIds(Integer id);
	/**
	 * 删除指定课程里所有的预约人员
	 * @param id
	 * @return
	 */
	public int deleteAppoint(Integer courseId);
	/**
	 * 查询出预约人数，用来判断是否已经有人预约了
	 * @param courseId
	 * @return
	 */
	public Long getAppointCount(Integer courseId);
	/**
	 * 查询出所有预约课程的费用
	 * @param result
	 * @return
	 */
	public List<Integer> getCostList(List<SchoolCourseAppoint> appontList);
	/**
	 * 更新过期用户预约信息
	 * @return
	 */
	public Integer changeOutOfDateAppoint();
	/**
	 * 查询该课程的总签到量
	 * @param detailId
	 * @return
	 */
	public Long getSignCount(Integer detailId);
}
