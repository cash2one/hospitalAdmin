package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.AppIndexDao;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.entity.AppIndex;
import com.jumper.hospital.service.AppIndexService;
/**
 * APP首页维护ServiceImpl
 * @author qinxiaowei
 *
 */
@Service
public class AppIndexServiceImpl extends BaseServiceImpl<AppIndex, Integer> implements AppIndexService {

	@Autowired
	private AppIndexDao appIndexDao;
	
	@Override
	public BaseDao<AppIndex, Integer> getBaseDAO() {
		return appIndexDao;
	}
	
	/**
	 * 查询所有app首页数据
	 * @param showPosition 显示位置
	 * @return
	 */
	public List<AppIndex> findAppIndex() {
		return appIndexDao.findAppIndex();
	}
	
	/**
	 * 添加
	 * @param app
	 */
	public void saveAppIndex(AppIndex app) {
		appIndexDao.save(app);
	}
	
	/**
	 * 查询出最后一条数据
	 * @param showPosition 位置
	 * @return
	 */
	public AppIndex findLastAppIndex(int showPosition) {
		return appIndexDao.findLastAppIndex(showPosition);
	}
	
	/**
	 * 删除数据
	 * @param id
	 */
	public boolean deleteAppIndex(int id) {
		try {
			appIndexDao.delete(id);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	public AppIndex findAppIndexById(int id) {
		return appIndexDao.get(id);
	}
	
	/**
	 * 修改数据
	 * @param ai
	 * @return
	 */
	public boolean updateAppIndex(AppIndex ai) {
		try {
			appIndexDao.update(ai);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}
	
	/**
	 * 查询显示个数
	 * @param showPosition
	 * @return
	 */
	public int findIsShowCount(int showPosition) {
		return appIndexDao.findIsShowCount(showPosition);
	}
	
	/**
	 * 查询所有省份
	 * @return
	 */
	public List<Map<String, Object>> findProvince() {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Object[]> list = appIndexDao.findProvince();
		for(int i=0; i<(list!=null && list.size()>0?list.size():0); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			map.put("id", obj[0]);
			map.put("proName", obj[1]);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 查询对应省份下所有城市
	 * @param proId
	 * @return
	 */
	public List<Map<String, Object>> findCity(int proId) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Object[]> list = appIndexDao.findCity(proId);
		for(int i=0; i<(list!=null && list.size()>0?list.size():0); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			map.put("id", obj[0]);
			map.put("cityName", obj[1]);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 查询对应城市下所有医院
	 * @param proId
	 * @param cityId
	 * @return
	 */
	public List<Map<String, Object>> findHospitalInfo(int proId, int cityId) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Object[]> list = appIndexDao.findHospitalInfo(proId, cityId);
		for(int i=0; i<(list!=null && list.size()>0?list.size():0); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Object[] obj = (Object[]) list.get(i);
			map.put("id", obj[0]);
			map.put("name", obj[1]);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 查询医院信息
	 * @param hospitalId
	 * @return
	 */
	public Object[] findHospitalInfoById(int hospitalId) {
		return appIndexDao.findHospitalInfoById(hospitalId);
	}
}
