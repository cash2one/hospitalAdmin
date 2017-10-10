package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalConsServiceOrderDao;
import com.jumper.hospital.dao.HospitalWithdrawalsDao;
import com.jumper.hospital.entity.HospitalConsServiceOrder;
import com.jumper.hospital.service.HospitalConsServiceOrderService;

@Service
public class HospitalConsServiceOrderServiceImpl extends BaseServiceImpl<HospitalConsServiceOrder, Integer>
		implements HospitalConsServiceOrderService {
	@Autowired
	private HospitalConsServiceOrderDao hospitalConsServiceOrderDao;
	
	@Autowired
	private HospitalWithdrawalsDao hospitalWithdrawalsDao;
	
	@Override
	public BaseDao<HospitalConsServiceOrder, Integer> getBaseDAO() {
		return hospitalConsServiceOrderDao;
	}

	@Override
	public Page<HospitalConsServiceOrder> findVisitOrderList(
			Page<HospitalConsServiceOrder> page, Integer id) {
		return hospitalConsServiceOrderDao.findVisitOrderList(page,id);
	}

	@Override
	public Page<HospitalConsServiceOrder> findVisitOrderListByConds(
			Page<HospitalConsServiceOrder> page, Integer id, String searchKey,
			String startTime, String endTime) {
		return hospitalConsServiceOrderDao.findVisitOrderListByConds(page,id,searchKey,startTime,endTime);
	}

	@Override
	public Page<HospitalConsServiceOrder> getConsServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId, Integer state,
			Integer withdrawalsId) {
		
		return hospitalConsServiceOrderDao.getConsServiceOrderList(page, hospitalId, state, withdrawalsId);
	}

	@Override
	public Page<HospitalConsServiceOrder> getUnBlanceServiceOrderList(
			Page<HospitalConsServiceOrder> page, Integer hospitalId,String startTime,String endTime) {
	
		List<Integer> list=hospitalWithdrawalsDao.getUnBlancedWithdrawals(hospitalId);//所有未结算的月度结算Id
	
		if(list==null||list.size()==0){
			return hospitalConsServiceOrderDao.getUnBlanceServiceOrderList(page,hospitalId,null,startTime,endTime);
		}
		
		return hospitalConsServiceOrderDao.getUnBlanceServiceOrderList(page,hospitalId,list.toArray(),startTime,endTime);
	}
	

}
