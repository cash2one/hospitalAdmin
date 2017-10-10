package com.jumper.hospital.service.impl;
/**
 * 临时订单Service
 * @author rent
 * @date 2015-11-24
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.MonitorOrderConsumerTempDao;
import com.jumper.hospital.dao.UserInfoDao;
import com.jumper.hospital.entity.MonitorOrder;
import com.jumper.hospital.entity.MonitorOrderConsumerTemp;
import com.jumper.hospital.entity.UserExtraInfo;
import com.jumper.hospital.service.MonitorOrderConsumerTempService;
import com.jumper.hospital.utils.ArrayUtils;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VoFinishedOrder;

@Service
public class MonitorOrderConsumerTempServiceImpl extends BaseServiceImpl<MonitorOrderConsumerTemp, Integer> implements MonitorOrderConsumerTempService {

	@Autowired
	private MonitorOrderConsumerTempDao monitorOrderConsumerTempDaoImpl;
	
	@Autowired
	private UserInfoDao userInfo;
	
	@Override
	public BaseDao<MonitorOrderConsumerTemp, Integer> getBaseDAO() {
		return monitorOrderConsumerTempDaoImpl;
	}

	@Override
	public Page<VoFinishedOrder> findRealTimeOrder(Page<MonitorOrderConsumerTemp> page, Integer hospitalId) {
		try {
			Page<MonitorOrderConsumerTemp> pageData = monitorOrderConsumerTempDaoImpl.findRealTimeOrder(page, hospitalId);
			Page<VoFinishedOrder> voPage = converRealTimeData(pageData);
			return voPage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Page<VoFinishedOrder> converRealTimeData(Page<MonitorOrderConsumerTemp> page){
		Page<VoFinishedOrder> pageData = new Page<VoFinishedOrder>();
		List<VoFinishedOrder> dataList = new ArrayList<VoFinishedOrder>();
		try {
			if(page != null && ArrayUtils.isNotEmpty(page.getResult())){
				for(MonitorOrderConsumerTemp consumer : page.getResult()){
					if(consumer != null){
						
						VoFinishedOrder voOrder = new VoFinishedOrder();
						voOrder.setId(consumer.getId());
						voOrder.setUserId(consumer.getUserId().getId());
						if(consumer.getUserId()!= null){
							UserExtraInfo user =  userInfo.findUserExtra(consumer.getUserId().getId());
							voOrder.setUserName(user.getRealName());
							voOrder.setAge(user.getAge());
						}else{
							voOrder.setUserName(consumer.getMonitorOrderId().getMonitorUserId().getRealName());
							voOrder.setAge(consumer.getMonitorOrderId().getMonitorUserId().getAge());
						}
						voOrder.setApplyTime(TimeUtils.convertTime(consumer.getApplyTime()));
						voOrder.setMobile(consumer.getMonitorOrderId().getMonitorUserId().getMobile());
						voOrder.setPreganyWeek(consumer.getUserId().getExpectedDateOfConfinement() == null ? 0 : TimeUtils.getPregnantWeek(consumer.getUserId().getExpectedDateOfConfinement())[0]);
						voOrder.setRemoteType(consumer.getMonitorOrderId().getRemoteType().name());
						voOrder.setQuestionId(consumer.getJid());
						
						if(consumer.getMonitorOrderId() != null){
							MonitorOrder monitorOrder = consumer.getMonitorOrderId();
							int monitorTimes = 1;
							if(monitorOrder.getMonitorCount() != null && monitorOrder.getLeftCount() != null){
								monitorTimes = monitorOrder.getMonitorCount() - monitorOrder.getLeftCount() + 1;
							}
							voOrder.setOrderId(monitorOrder.getId());
							voOrder.setMonitorTimes(monitorTimes);
						}
						
						//实时监护的MonitorId
						if(consumer.getMonitorOrderId() != null){
							voOrder.setMonitorOrderId(consumer.getMonitorOrderId().getId());
						}
						dataList.add(voOrder);
					}
				}
				int size = dataList.size();
				if(dataList.size() < page.getPageSize()){
					for(int j = 0;j < page.getPageSize() - size;j++){
						dataList.add(null);
					}
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}else{
				for(int j = 0;j < page.getPageSize();j++){
					dataList.add(null);
				}
				pageData.setPageNo(page.getPageNo());
				pageData.setPageSize(page.getPageSize());
				pageData.setResult(dataList);
				pageData.setTotalCount(page.getTotalCount());
			}
			return pageData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
