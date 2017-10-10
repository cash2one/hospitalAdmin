package com.jumper.hospital.service;
/**
 * Service - 管理员
 * @author rent
 * @date 2015-10-16
 */
import java.util.List;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.vo.visit.Msg;

public interface AdminService extends BaseService<Admin, Integer> {
	
	/**
	 * 新增管理员（和云随访账户）
	 */
	public Msg saveAdmin(Admin admin, Integer visitorRole, String doctorWorkNum);
	/**
	 * 判断用户名是否存在
	 * @param username 用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	public boolean usernameExists(String username);

	/**
	 * 根据用户名查找管理员
	 * @param username 用户名(忽略大小写)
	 * @return 管理员，若不存在则返回null
	 */
	public Admin findByUsername(String username);

	/**
	 * 根据ID查找权限
	 * @param id ID
	 * @return 权限,若不存在则返回null
	 */
	public List<Authority> findAuthorities(Integer id);

	/**
	 * 判断管理员是否登录
	 * @return 管理员是否登录
	 */
	public boolean isAuthenticated();

	/**
	 * 获取当前登录管理员
	 * @return 当前登录管理员,若不存在则返回null
	 */
	public Admin getCurrent();

	/**
	 * 获取当前登录用户名
	 * @return 当前登录用户名,若不存在则返回null
	 */
	public String getCurrentUsername();
	
	/**
	 * 获取管理员列表
	 * @param hospitalId 医院ID
	 * @param type 0:admin登陆，其它为医院用户
	 * @return
	 */
	public Page<Admin> getAdminList(Integer hospitalId, int type, Page<Admin> page, String username,Integer majorid,Integer titleid);
	/**
	 * 通过医院id和关键字获取管理员信息
	 * @param hospId
	 * @return
	 */
	public Page<Admin> getAdminByHospId(Integer hospId,String keywords);
	
	/**
	 * 判断医院管理员是否已经添加
	 * @param hospitalId 医院ID
	 * @return
	 */
	public Boolean checkHospitalHasOpen(Integer hospitalId);

    /**
     * getidsByHospitalID(根据医院id获取管理员列表)
     * @param @param id
     * @param @return
     * @return List<Integer>
     * @Exception 异常对象
    */
    public List<Integer> getidsByHospitalID(Integer id);
    /**
    * 获取医生职称
    * @return
    */
    public List<Object> getHospitalDoctorTitleList(Integer hospitalId);
    
    /**
     * 根据科室关联表id 得到科室ID
     * @param adminId
     * @return
     */
    public List<Integer> getDoctorMajorIdByMajorInfoId(Integer id);
    
    /**
     * 修改管理员信息（和随访角色）
     * @return 
     */
	public Msg edit(Admin targetAdmin, Integer visitorRole,String doctorWorkNum);
	
	/**
     * <strong>重置密码</strong>
     * @param id(Integer) 重置密码的用户id password(String) 密码,默认123456加密
     */
	public void resetPas(Integer id, String password);
	
	/**
	 * 
	 */
	public void updateIsHospitalNst(Admin admin,Boolean statue);
	
	
	/**
	 * 查询所有医生信息
	 */
	List<Admin> findAllDoctor(Integer hospitalId);
	/**
	 * 根据医生姓名模糊查询
	 */
	List<Admin> findDoctorByName(String name,Integer hospitalId);
	
	
	
	
	
	
	

}