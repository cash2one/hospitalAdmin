package com.jumper.hospital.service.impl;
/**
 * 用户预约信息Service实现类
 * @author rent
 * @date 2016-01-25
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.SchoolCourseAppointDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.dao.impl.SchoolCourseDetailDaoImpl;
import com.jumper.hospital.entity.SchoolCourseAppoint;
import com.jumper.hospital.entity.SchoolCourseDetail;
import com.jumper.hospital.entity.UserInfo;
import com.jumper.hospital.enums.SchoolAppointState;
import com.jumper.hospital.enums.SchoolSignState;
import com.jumper.hospital.service.SchoolCourseAppointService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.BeanUtils;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.HBSmsCodeUtils;
import com.jumper.hospital.utils.HttpRequestUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.MsgBean;
import com.jumper.hospital.vo.school.VoAppointList;
import com.jumper.hospital.vo.school.VoMonthAppointResult;

@Service
public class SchoolCourseAppointServiceImpl extends BaseServiceImpl<SchoolCourseAppoint, Integer> implements SchoolCourseAppointService {

	private static final Logger logger = Logger.getLogger(SchoolCourseAppointServiceImpl.class);
	@Autowired
	private SchoolCourseAppointDao schoolCourseAppointDaoImpl;
	@Autowired
	private UserInfoDao userInfoDaoImpl;
	
	@Autowired
	private SchoolCourseDetailDaoImpl schoolCourseDetailDaoImpl;
	
	@Override
	public BaseDao<SchoolCourseAppoint, Integer> getBaseDAO() {
		return schoolCourseAppointDaoImpl;
	}

	@Override
	public Page<SchoolCourseAppoint> findAppointAndSignListByDetailId(Page<SchoolCourseAppoint> page, Integer detailId,
			SchoolAppointState appointState, SchoolSignState signState, String searchKey) {
		if(detailId == null){
			return null;
		}
		Page<SchoolCourseAppoint> pageData = schoolCourseAppointDaoImpl.findAppointListByDetailId(page, detailId, appointState, signState, searchKey);
		List<SchoolCourseAppoint> convertList = new ArrayList<SchoolCourseAppoint>();
		if(pageData != null && ArrayUtils.isNotEmpty(pageData.getResult())){
			for(SchoolCourseAppoint appoint : pageData.getResult()){
				Integer userId = appoint.getAppointUserId();
				if(userId != null){
					UserInfo user = userInfoDaoImpl.get(userId);
					if (user.getExpectedDateOfConfinement() == null){
						convertList.add(appoint);
						continue;
					}
					try {
						int[] pregnant = TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement());
						if(pregnant.length > 2){
							appoint.setUserWeek(pregnant[0]+"周"+pregnant[1]+"天");
						}
						convertList.add(appoint);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			pageData.setResult(convertList);
		}
		return pageData;
	}

	@Override
	public String batchPay(String ids) {
	
		try {
			if(StringUtils.isEmpty(ids)){
				return Consts.PARAMS_ERROR;
			}
			int rows = schoolCourseAppointDaoImpl.batchExecute("update SchoolCourseAppoint a set a.payState=1 where a.id in("+ids+")");
			if(rows > 0){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String batchBack(String ids) {
		try {
			if(StringUtils.isEmpty(ids)){
				return Consts.PARAMS_ERROR;
			}
			int rows = schoolCourseAppointDaoImpl.batchExecute("update SchoolCourseAppoint a set a.payState=2 where a.id in("+ids+")");
			if(rows > 0){
				return Consts.SUCCESS;
			}
			return Consts.FAILED;
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public String batchPush(String content, String ids, Integer pushType) {
		try {
			if(StringUtils.isEmpty(ids) || StringUtils.isEmpty(content) || pushType == null){
				return Consts.PARAMS_ERROR;
			}
			if(pushType == 1){
				/** 短信推送 **/
				List<UserInfo> list = userInfoDaoImpl.findByIds(ids);
				StringBuffer sb = new StringBuffer();
				if(ArrayUtils.isNotEmpty(list)){
					//如果手机只有一个人，那么判断他是否有手机号,没有手机号的话则返回推送失败！
					if(list.size()==1){
						if(StringUtils.isEmpty(list.get(0).getUserExitInfo().getContactPhone())){
							return Consts.FAILED;
						}
					}
					
					for(int i = 0; i < list.size(); i++){
						UserInfo user = list.get(i);
						if(user != null && user.getUserExitInfo() != null && StringUtils.isNotEmpty(user.getUserExitInfo().getContactPhone())){
							sb.append(user.getUserExitInfo().getContactPhone());
							if(i != list.size() - 1){
								sb.append(";");
							}
						}
					}
				}
				boolean flag = HBSmsCodeUtils.sendSmsMsg(content, sb.toString());
				if(flag){
					return Consts.SUCCESS;
				}
				return Consts.FAILED;
			}else{
				/** app推送 **/
				String title = "孕妇学校";
	        	String result = HttpRequestUtils.sendPost(Consts.PUSH_MESSAGE_URL, "id=1114&content=+"+content+"&title="+title+"&language=cn&Msg_type=0&user_type=1&user_msg="+ids);
	        	MsgBean bean = JSONArray.parseObject(result, MsgBean.class);
	        	if(bean.getMsg() != 1){
	        		logger.error("推送消息失败！");
	        		return Consts.FAILED;
	        	}
				return Consts.SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}

	@Override
	public List<VoAppointList> getExportAppointData(Integer detailId) {
		List<SchoolCourseAppoint> list = schoolCourseAppointDaoImpl.findAppointListByDetailId(detailId);
		List<VoAppointList> dataList = new ArrayList<VoAppointList>();
		try {
			if(ArrayUtils.isNotEmpty(list)){
				for(SchoolCourseAppoint appoint : list){
					if(appoint != null){
						VoAppointList vo = new VoAppointList();
						BeanUtils.copy(appoint, vo);
						vo.setUserAppointTime(TimeUtils.convertTime(appoint.getAddTime()));
						vo.setUserSignTime(TimeUtils.convertTime(appoint.getSignTime()));
						vo.setPayState(appoint.getPayState().toString());
						
						UserInfo user = userInfoDaoImpl.get(appoint.getAppointUserId());
						if (user.getExpectedDateOfConfinement() == null){
							continue;
						}
						int[] pregnant = TimeUtils.getPregnantWeek(user.getExpectedDateOfConfinement());
						if(pregnant.length > 2){
							vo.setUserWeek(pregnant[0]+"周"+pregnant[1]+"天");
						}
						dataList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public VoMonthAppointResult findAppointSignByCourseId(Integer hospitalId, Integer detailId) {
		VoMonthAppointResult result = schoolCourseAppointDaoImpl.findAppointSignByCourseId(hospitalId, detailId);
		if(result == null){
			result = new VoMonthAppointResult();
			result.setAppoint(0);
			result.setSign(0);
		}
		return result;
	}

	@SuppressWarnings("static-access")
	@Override
	public String getOppointIds(Integer id) {
		List<SchoolCourseAppoint> list = schoolCourseAppointDaoImpl.findAppointListByDetailId(id);
		List<Integer> idList = new ArrayList<Integer>();
		for(SchoolCourseAppoint appoint : list){
			idList.add(appoint.getAppointUserId());
		}
	   return this.listToString(idList);
		
	}
	//将list转成逗号隔开的字符串
	public static String listToString(List<Integer> list) {  
	    StringBuilder sb = new StringBuilder();  
	    if (list != null && list.size() > 0) {  
	        for (int i = 0; i < list.size(); i++) {  
	            if (i < list.size() - 1) {  
	                sb.append(list.get(i) + ",");  
	            } else {  
	                sb.append(list.get(i));  
	            }  
	        }  
	    }  
	    return sb.toString();  
	}

	@Override
	public int deleteAppoint(Integer courseId) {
		System.out.println("_-------------------删除操作");
		int rows = schoolCourseAppointDaoImpl.batchExecute("delete SchoolCourseAppoint  where courseDetailId="+courseId+"");
		return rows;
	}

	@Override
	public Long getAppointCount(Integer courseId) {
		
		return schoolCourseAppointDaoImpl.getAppointCount(courseId);
	}

	@Override
	public List<Integer> getCostList(List<SchoolCourseAppoint> appointList) {
		List<Integer> list=new ArrayList<Integer>();
		for(SchoolCourseAppoint appoint:appointList){
			SchoolCourseDetail course=schoolCourseDetailDaoImpl.get(appoint.getCourseDetailId());
			if(course!=null){ //测试的数据库存在两边映射不正常的现象
				if(course.getCourseCost()>0){
					list.add(1);//收费课程
				}else{
					list.add(0);//免费课程
				}
			}else{
				list.add(0);
			}
		}
		return list;
	}

	@Override
	public Integer changeOutOfDateAppoint() {
		boolean flag = schoolCourseAppointDaoImpl.checkHasOutOfDateAppointToChange();
		if(flag){
			return schoolCourseAppointDaoImpl.changeOutOfDateAppoint();
		}
		return Consts.FALSE;
	}

	@Override
	public Long getSignCount(Integer detailId) {
		return schoolCourseAppointDaoImpl.getSignCount(detailId);
	}  
	
}
