package com.jumper.hospital.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorHospitalServiceTimeDao;
import com.jumper.hospital.entity.MonitorHospital;
import com.jumper.hospital.entity.MonitorHospitalServiceTime;
import com.jumper.hospital.enums.Time;
import com.jumper.hospital.service.MonitorHospitalService;
import com.jumper.hospital.service.MonitorHospitalServiceTimeService;
import com.jumper.hospital.vo.VOMonitorHospitalServiceTime;

@Service
public class MonitorHospitalServiceTimeServiceImpl extends BaseServiceImpl<MonitorHospitalServiceTime, Integer> 
																			implements MonitorHospitalServiceTimeService {
//		private static final Logger logger = Logger.getLogger(MonitorHospitalServiceTimeServiceImpl.class);
		
		@Autowired
		private MonitorHospitalServiceTimeDao monitorHospitalServiceTimeDao;
		
		@Autowired
		private MonitorHospitalService monitorHospitalService;
		
		@Override
		public BaseDao<MonitorHospitalServiceTime, Integer> getBaseDAO() {
			return monitorHospitalServiceTimeDao;
		}
		
		/**
		 * 初始化医院服务时间
		 */
		@Override
		public void initServiceTime(Integer hospitalId) {
			
			String monitorHospitalServiceTimeCountSql="select count(1) from monitor_hospital_service_time where hospital_id="+hospitalId;
			int monitorHospitalServiceTimeCount= monitorHospitalServiceTimeDao.executeCountSql(monitorHospitalServiceTimeCountSql);
			MonitorHospitalServiceTime monitorHospitalServiceTime=null;
			if(monitorHospitalServiceTimeCount<7){
				//如果是老的医院的数据，查询之前的数据用来初始化
				MonitorHospital oldServiceTimes=  monitorHospitalService.findMonitorHospitalByHospitalId(hospitalId);
				if(null!=oldServiceTimes){
					if(null!=oldServiceTimes.getStartDay()&& null!=oldServiceTimes.getEndDay()&&null!=oldServiceTimes.getStartTime()&&null!=oldServiceTimes.getEndTime()){
						for(int i=0;i<7;i++){
							monitorHospitalServiceTime=new MonitorHospitalServiceTime();
							monitorHospitalServiceTime.setHospitalId(hospitalId);
							monitorHospitalServiceTime.setDayOfWeek(i);
							if(i>=oldServiceTimes.getStartDay().ordinal()&&i<=oldServiceTimes.getEndDay().ordinal()){
								monitorHospitalServiceTime.setStartTime(oldServiceTimes.getStartTime());
								monitorHospitalServiceTime.setEndTime(oldServiceTimes.getEndTime());
								monitorHospitalServiceTime.setIsEnabled(true);
							}else{
								monitorHospitalServiceTime.setStartTime(Time.j.getDesc());
								monitorHospitalServiceTime.setEndTime(Time.s.getDesc());
								monitorHospitalServiceTime.setIsEnabled(false);
							}
							monitorHospitalServiceTimeDao.persist(monitorHospitalServiceTime);
						}
					}else{
						//如果是新开的医院，新数据直接初始化数据,默认 朝九晚五的时间
						for(int i=0;i<7;i++){
							monitorHospitalServiceTime=new MonitorHospitalServiceTime();
							monitorHospitalServiceTime.setHospitalId(hospitalId);
							monitorHospitalServiceTime.setDayOfWeek(i);
							monitorHospitalServiceTime.setStartTime(Time.j.getDesc());
							monitorHospitalServiceTime.setEndTime(Time.s.getDesc());
							monitorHospitalServiceTime.setIsEnabled(false);
							monitorHospitalServiceTimeDao.persist(monitorHospitalServiceTime);
						}
					}
				}else{
					//如果是新开的医院，新数据直接初始化数据,默认 朝九晚五的时间
					for(int i=0;i<7;i++){
						monitorHospitalServiceTime=new MonitorHospitalServiceTime();
						monitorHospitalServiceTime.setHospitalId(hospitalId);
						monitorHospitalServiceTime.setDayOfWeek(i);
						monitorHospitalServiceTime.setStartTime(Time.j.getDesc());
						monitorHospitalServiceTime.setEndTime(Time.s.getDesc());
						monitorHospitalServiceTime.setIsEnabled(true);
						monitorHospitalServiceTimeDao.persist(monitorHospitalServiceTime);
					}
				}
			}
			//monitorHospitalServiceTimeDao.persist(monitorHospitalServiceTime);
		}
		
		/**
		 * 获取当前医院的排班列表
		 */
		@Override
		public List<VOMonitorHospitalServiceTime> getServiceTime(Integer hospitalId) {
			//获取当前医院的排班
			List<MonitorHospitalServiceTime> monitorHospitalServiceTimeList=
														monitorHospitalServiceTimeDao.getHospitalDutyRoster(hospitalId);
			
			List<VOMonitorHospitalServiceTime> resultTimeList=new LinkedList<VOMonitorHospitalServiceTime>();
			VOMonitorHospitalServiceTime VOMonitorHospitalServiceTimeTemp=null;
			//第一条数据的标志
			Integer firsItemtFlag=0;
			//最后一条数据的位置
			List<Integer> lastItemPositions=new LinkedList<Integer>();
			for(MonitorHospitalServiceTime list:monitorHospitalServiceTimeList){
				if(firsItemtFlag.equals(list.getDayOfWeek())){
					VOMonitorHospitalServiceTimeTemp=new VOMonitorHospitalServiceTime();
					VOMonitorHospitalServiceTimeTemp.setDayOfWeek(list.getDayOfWeek());
					VOMonitorHospitalServiceTimeTemp.setEndTime(list.getEndTime());
					VOMonitorHospitalServiceTimeTemp.setHospitalId(list.getHospitalId());
					VOMonitorHospitalServiceTimeTemp.setId(list.getId());
					VOMonitorHospitalServiceTimeTemp.setIsFirstItem("Y");
					VOMonitorHospitalServiceTimeTemp.setStartTime(list.getStartTime());
					VOMonitorHospitalServiceTimeTemp.setIsLastItem("N");
					VOMonitorHospitalServiceTimeTemp.setIsEnabled(list.getIsEnabled());
					resultTimeList.add(VOMonitorHospitalServiceTimeTemp);
					firsItemtFlag++;
					
					Integer lastItemPosition=resultTimeList.size();
					lastItemPositions.add(lastItemPosition);
				}else{
					VOMonitorHospitalServiceTimeTemp=new VOMonitorHospitalServiceTime();
					VOMonitorHospitalServiceTimeTemp.setDayOfWeek(list.getDayOfWeek());
					VOMonitorHospitalServiceTimeTemp.setEndTime(list.getEndTime());
					VOMonitorHospitalServiceTimeTemp.setHospitalId(list.getHospitalId());
					VOMonitorHospitalServiceTimeTemp.setId(list.getId());
					VOMonitorHospitalServiceTimeTemp.setIsFirstItem("N");
					VOMonitorHospitalServiceTimeTemp.setStartTime(list.getStartTime());
					VOMonitorHospitalServiceTimeTemp.setIsLastItem("N");
					VOMonitorHospitalServiceTimeTemp.setIsEnabled(list.getIsEnabled());
					resultTimeList.add(VOMonitorHospitalServiceTimeTemp);
				}
			}
			//设置最后一条数据的标志
			if(lastItemPositions.size()>0){
				lastItemPositions.remove(0);
			}
			for( Integer p:lastItemPositions){
				resultTimeList.get(p.intValue()-2).setIsLastItem("Y");
			}
			//最后一条加上当天最后一条的标志
			if(resultTimeList.size()>0){
				resultTimeList.get(resultTimeList.size()-1).setIsLastItem("Y");
			}
/*			for(VOMonitorHospitalServiceTime list:resultTimeList){
				System.out.println("id:"+list.getId() +"        datofweek:"+list.getDayOfWeek() +"   hsptid:"+list.getHospitalId()+"     startT:"+list.getStartTime()+"   lastT:"+list.getEndTime()+"    isfirstItem:"+list.getIsFirstItem()+"     isLastItm:"+list.getIsLastItem());
			}*/
			return resultTimeList;
		}
		
		
		
}
