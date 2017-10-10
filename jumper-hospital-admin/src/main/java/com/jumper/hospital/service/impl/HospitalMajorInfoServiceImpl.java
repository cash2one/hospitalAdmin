package com.jumper.hospital.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalMajorInfoDao;
import com.jumper.hospital.entity.HospitalMajorInfo;
import com.jumper.hospital.service.HospitalMajorInfoService;
import com.jumper.hospital.utils.Consts;

@Service
public class HospitalMajorInfoServiceImpl extends BaseServiceImpl<HospitalMajorInfo, Integer>
		implements HospitalMajorInfoService {
	@Autowired
	private HospitalMajorInfoDao hospitalMajorInfoDao;
	@Override
	public BaseDao<HospitalMajorInfo, Integer> getBaseDAO() {
		return hospitalMajorInfoDao;
	}
	@Override
	public void addMajorInfo(int hospitalId, int[] majors) {
		hospitalMajorInfoDao.addMajorInfo(hospitalId,majors);
	}
	@Override
	public List<HospitalMajorInfo> findHosMajors(Integer id) {
		return hospitalMajorInfoDao.findHosMajors(id);
	}
	@Override
	public List<HospitalMajorInfo> findHosMajorsByIsnetwork(Integer id,Integer isnetwork) {
		return hospitalMajorInfoDao.findHosMajorsByIsnetwork(id,isnetwork);
	}
	/**
	 * 开通or关闭网络该科室网络诊室 executeNativeSql
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	@Transactional
	public String openMajorNetwork(Integer majorId,String status,Integer hospitalId,Integer userId) {
		List<Object> visitmodeList=null;
		List<Object> shiftmodeList=null;
		try{
			String sql="update hospital_major_info set is_network='"+status+"' where id="+majorId;
			int i=hospitalMajorInfoDao.executeNonQuerySql(sql);
			
			 sql="select * from network_visitmode WHERE majorid="+majorId;
			 visitmodeList=hospitalMajorInfoDao.executeNativeSql(sql);
			 sql="select * from network_shiftid WHERE majorid="+majorId;
			 shiftmodeList=hospitalMajorInfoDao.executeNativeSql(sql);
			 //如果一开始就有 则只把状态改为 0 如果没有直接添加
		     if(visitmodeList!=null && visitmodeList.size()>0 && shiftmodeList!=null && shiftmodeList.size()>0){
				 sql="update network_shiftid set status='0' where majorid="+majorId;
				 hospitalMajorInfoDao.executeNonQuerySql(sql);
				 return Consts.SUCCESS;
		     }else{
				Date date=new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String nowdate=sdf.format(date);
				//再默认添加
			   sql="insert into network_shiftid (name,strat_time,end_time,status,add_time,update_time,hospitalid,majorid,founderid,nvtype) values" +
						"('上午','00:00','00:00','0','"+nowdate+"','"+nowdate+"','"+hospitalId+"','"+majorId+"','"+userId+"','1')";
				i=hospitalMajorInfoDao.executeNonQuerySql(sql);
			   sql="insert into network_shiftid (name,strat_time,end_time,status,add_time,update_time,hospitalid,majorid,founderid,nvtype) values" +
						"('下午','00:00','00:00','0','"+nowdate+"','"+nowdate+"','"+hospitalId+"','"+majorId+"','"+userId+"','2')";
				i=hospitalMajorInfoDao.executeNonQuerySql(sql);
			   sql="insert into network_shiftid (name,strat_time,end_time,status,add_time,update_time,hospitalid,majorid,founderid,nvtype) values" +
						"('晚上','00:00','00:00','0','"+nowdate+"','"+nowdate+"','"+hospitalId+"','"+majorId+"','"+userId+"','3')";
				i=hospitalMajorInfoDao.executeNonQuerySql(sql);
				
	            sql="insert into network_visitmode (name,chargeamount,numbers,status,add_time,update_time,hospitalid,majorid,founderid,nvtype) values" +
	            		"('专科','0','0','1','"+nowdate+"','"+nowdate+"','"+hospitalId+"','"+majorId+"','"+userId+"','1')";
	            i=hospitalMajorInfoDao.executeNonQuerySql(sql);
	            
	            sql="insert into network_visitmode (name,chargeamount,numbers,status,add_time,update_time,hospitalid,majorid,founderid,nvtype) values" +
	            		"('专家','0','0','1','"+nowdate+"','"+nowdate+"','"+hospitalId+"','"+majorId+"','"+userId+"','2')";
	            i=hospitalMajorInfoDao.executeNonQuerySql(sql);
	            
				return Consts.SUCCESS;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return Consts.ERROR;
		}
	}
	
	
	
	

}
