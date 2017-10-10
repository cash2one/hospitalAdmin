package com.jumper.hospital.dao;
/**
 * Dao - 管理员
 * @author rent
 * @date 2015-10-16
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;

public interface AdminDao extends BaseDao<Admin, Integer> {

	/**
	 * 监测用户名是否存在
	 * @param username 用户名
	 * @return boolean true:用户名已存在 false:用户名不存在
	 */
	public boolean usernameExists(String username);

	/**
	 * 通过用户名查询管理员
	 * @param username 用户名
	 * @return Admin管理员对象
	 */
	public Admin findByUsername(String username);

	/**
	 * 获取管理员列表，超级管理员以及医院管理员有权限
	 * @param hosptialId 医院ID
	 * @param type 0：超级管理员，1：医院管理员
	 * @param page 分页信息
	 * @param username 搜索信息
	 * @return
	 */
	public Page<Admin> getAdminList(Integer hospitalId, Integer type, Page<Admin> page, String username,Integer majorid,Integer titleid);
	
	/**
	 * 判断医院管理员是否已经添加
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Boolean checkHospitalHasOpen(Integer hospitalId);

    /**
     * getidsByHospitalID(根据医院id获取管理员id列表)
     * @param @param id
     * @param @return
     * @return List<Integer>
     * @Exception 异常对象
    */
    public List<Integer> getidsByHospitalID(Integer id);
    
	/**
	 * 通过用户名id查询管理员
	 * @param adminId 用户id
	 * @return Admin管理员对象
	 */
	public Admin findByAdminId(Integer adminId);
	/**
	 * 当前关键字搜索的账号是否为医院账号
	 * @param id
	 * @param trim
	 * @return
	 */
	public Admin matchCurrentHospitalAdmin(Integer id, String searchDoctor);
	/**
	 * 获取当前医院的账号
	 * @param hospitalId
	 * @return
	 */
	public Admin CurrentHospitalAdmin(Integer hospitalId);
	
	/**
	 * 查询所有医生信息
	 */
	public List<Admin> findAllDoctor(Integer hospitalId);
	/**
	 * 根据医生姓名模糊查询
	 */
	public List<Admin> findDoctorByName(String name,Integer hospitalId);
	
	
}