package com.jumper.hospital.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.base.PageHibernateDao;
import com.jumper.hospital.dao.AdminDao;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDoctorInfoDao;
import com.jumper.hospital.dao.NetWorkManageDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.Authority;
import com.jumper.hospital.entity.HospitalDoctorInfo;
import com.jumper.hospital.entity.Role;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.AuthorityService;
import com.jumper.hospital.service.HospitalCloudVisitorService;
import com.jumper.hospital.vo.Principal;
import com.jumper.hospital.vo.visit.Msg;

/**
 * Service - 管理员
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer> implements AdminService {

	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private NetWorkManageDao netWorkManageDao;
	@Autowired
	HospitalCloudVisitorService cloudVisitorService;
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private HospitalDoctorInfoDao hospitalDoctorInfoDao;
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return adminDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Admin findByUsername(String username) {
		return adminDao.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public List<Authority> findAuthorities(int id) {
		List<Authority> authorities = new ArrayList<Authority>();
		Admin admin = adminDao.get(id);
		if (admin != null) {
			for (Role role : admin.getRoles()) {
				authorities.addAll(role.getAuthorities());
			}
		}
		return authorities;
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			return subject.isAuthenticated();
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Admin getCurrent() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return adminDao.get(principal.getId());
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Admin update(Admin admin) {
		return adminDao.save(admin);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Integer id) {
		adminDao.delete(id);
	}

	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Integer... ids) {
		if(ids != null && ids.length > 0){
			for(Integer id : ids){
				adminDao.delete(id);
			}
		}
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Admin admin) {
		super.delete(admin);
	}

	public List<Authority> findAuthorities(Integer id) {
		List<Authority> authorities = new ArrayList<Authority>();
		Admin admin = adminDao.get(id);
		if (admin != null) {
			for (Role role : admin.getRoles()) {
				authorities.addAll(role.getAuthorities());
			}
		}
		return authorities;
	}
	/**
	 * 通过医院id获取管理员信息
	 * @param hospId
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Admin> getAdminByHospId(Integer hospId,String keywords) {
		if(null != hospId && hospId != 0){
			Criteria c = this.adminDao.createCriteria();
			c.add(Restrictions.eq("hospitalInfo.id", hospId));
			if(StringUtils.isNotBlank(keywords)){
				c.add(Restrictions.or(Restrictions.ilike("name", keywords,MatchMode.ANYWHERE),Restrictions.ilike("username", keywords,MatchMode.ANYWHERE)));
			}
			PageHibernateDao<Admin, Integer> pageDao = new PageHibernateDao<Admin, Integer>();
			@SuppressWarnings("rawtypes")
			Page<Admin> list = pageDao.findPageByCriteria(new Page(), c);
			if(null != list){
				return list;
			}
		}
		return null;
	}
	@Override
	public BaseDao<Admin, Integer> getBaseDAO() {
		return adminDao;
	}

	public Page<Admin> getAdminList(Integer hospitalId, int type, Page<Admin> page, String username,Integer majorid,Integer titleid) {
		return adminDao.getAdminList(hospitalId, type, page, username, majorid, titleid);
	}

	@Override
	public Boolean checkHospitalHasOpen(Integer hospitalId) {
		return adminDao.checkHospitalHasOpen(hospitalId);
	}
	/**
	 * 查询医生职称
	 */
		@Override
		public List<Object> getHospitalDoctorTitleList(Integer hospitalId) {
			return netWorkManageDao.getHospitalDoctorTitleList(hospitalId);
		}


    /**
     * @see com.jumper.hospital.service.AdminService#getidsByHospitalID(java.lang.Integer)
     */
    @Override
    public List<Integer> getidsByHospitalID(Integer id)
    {
        return adminDao.getidsByHospitalID(id);
    }

	@Override
	public List<Integer> getDoctorMajorIdByMajorInfoId(Integer id) {
		return netWorkManageDao.getDoctorMajorIdByMajorInfoId(id);
	}

	@Override
	public Msg saveAdmin(Admin admin,Integer visitorRole, String doctorWorkNum) {
		Admin persist = persist(admin);
		//有云随访权限的话就走云随访业务
		Set<Role> roles = persist.getRoles();
		Boolean isCloudVisit = null;
		if(roles != null && roles.size() > 0){
			for(Role role : roles){
				if(role != null){
					isCloudVisit = authorityService.getAuthorByRole(role.getId());
					if(isCloudVisit){
						break;
					}
				}
			}
		}
//		Msg msg = null;
		logger.info("isCloudVisit is "+isCloudVisit);
/*		if (isCloudVisit) {//有云随访权限
			msg = new Msg();
			if (doctorWorkNum != null && doctorWorkNum.length() > 0) {
				// 云随访
				Map<String, Object> params = new HashMap<String, Object>();
				Integer docId = persist.getId();
				Integer hosId = null;
				if (persist.getHospitalInfo() != null && persist.getHospitalInfo().getId() != null) {
					hosId = persist.getHospitalInfo().getId();
				}
				params.put("roleId", visitorRole);// 随访角色
				params.put("worknumber", doctorWorkNum);// 工号
				if (StringUtils.isNotBlank(persist.getName())) {
					params.put("doctorName", persist.getName());// 用户姓名
				}
				params.put("password", persist.getPassword());// 随访密码:加密后的
				CloudVisitorAccount visitorAccount = null;
				logger.info("docId is "+docId+",hosId is "+hosId);
				if (docId != null && hosId != null) {
					try {
						visitorAccount = cloudVisitorService.bindHosVisitorAccount(params, docId, hosId);
						visitorAccount.setMonitorAdminId(docId);
						visitorAccount.setVisitorRole(visitorRole);// 随访角色
						visitorAccount.setHostpitalId(hosId);
						visitorAccount.setDoctorWorkNum(doctorWorkNum);// 工号
						logger.info("visitorAccount is "+visitorAccount);
						if (visitorAccount.getMsg().equals("0")) {
							cloudVisitorService.save(visitorAccount);
							msg.setMsgBox(visitorAccount.getAccount());
							msg.setMsg(0);
						}else {
							Admin adminOld = get(persist.getId());
							delete(adminOld.getId());
							msg.setMsg(Integer.parseInt(visitorAccount.getMsg()));
							msg.setMsgBox(visitorAccount.getMsgbox());
							log.info("绑定随访失败信息：",visitorAccount.getMsgbox());
						}
						
					} catch (Exception e) {
						Admin adminOld = get(persist.getId());
						CloudVisitorAccount cloudVisitorOld = cloudVisitorService.get(persist.getId());
						delete(adminOld.getId());
						if(cloudVisitorOld != null){
							cloudVisitorService.delete(cloudVisitorOld.getMonitorAdminId());
						}
						log.error("绑定云随访账号失败：",e);
					}
				}
				
			}
			return msg;
		}*/
		return null;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Msg edit(Admin targetAdmin, Integer visitorRole, String doctorWorkNum) {
		// 有云随访权限的话就走云随访业务
		Set<Role> roles = targetAdmin.getRoles();
		Boolean isCloudVisit = null;
//		Integer cloudRoleId =null;
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				if (role != null) {
					isCloudVisit = authorityService.getAuthorByRole(role.getId());
					if(isCloudVisit){
						role.getId();
						break;
					}
				}
			}
		}
		Msg msg = null;
		logger.info("isCloudVisit is "+isCloudVisit);
		if (!isCloudVisit){
			logger.info("save targetAdmin is"+targetAdmin);
			save(targetAdmin);
		}else {// 云随访
			/*msg = new Msg();
			CloudVisitorAccount visitInfo = cloudVisitorService.get(targetAdmin.getId());
			if(visitInfo == null){//绑定信息为空时：进行绑定操作
				logger.info("doctorWorkNum is "+doctorWorkNum);
				if (doctorWorkNum != null && doctorWorkNum.length() > 0) {
					Map<String, Object> params = new HashMap<String, Object>();
					Integer docId = targetAdmin.getId();
					Integer hosId = null;
					if (targetAdmin.getHospitalInfo() != null && targetAdmin.getHospitalInfo().getId() != null) {
						hosId = targetAdmin.getHospitalInfo().getId();
					}
					params.put("roleId", visitorRole);// 随访角色
					params.put("worknumber", doctorWorkNum);// 工号
					if (StringUtils.isNotBlank(targetAdmin.getName())) {
						params.put("doctorName", targetAdmin.getName());// 用户姓名
					}
					params.put("password", targetAdmin.getPassword());// 随访密码：加密后的
					CloudVisitorAccount visitorAccount = null;
					if (docId != null && hosId != null) {
						try {
							visitorAccount = cloudVisitorService.bindHosVisitorAccount(params, docId, hosId);
							visitorAccount.setMonitorAdminId(docId);
							visitorAccount.setVisitorRole(visitorRole);// 随访角色
							visitorAccount.setHostpitalId(hosId);
							visitorAccount.setDoctorWorkNum(doctorWorkNum);// 工号
							
							logger.info("visitorAccount is "+visitorAccount);
							if (visitorAccount.getMsg().equals("0")) {//绑定成功
								save(targetAdmin);
								cloudVisitorService.save(visitorAccount);
								msg.setMsgBox(visitorAccount.getAccount());
								msg.setMsg(0);
							}else {
								msg.setMsg(Integer.parseInt(visitorAccount.getMsg()));
								msg.setMsgBox(visitorAccount.getMsgbox());
								log.info("绑定随访失败信息：",visitorAccount.getMsgbox());
							}
						} catch (Exception e) {
							log.error("绑定云随访账号异常：",e);
						}
					}
					
				}
				
			}else{//有绑定信息进行角色修改操作
				logger.info("visitInfo is "+visitInfo);
				String sourseId = "";
				Integer docId = null;
				Integer hosId = null;
				if(visitInfo != null){
					sourseId = visitInfo.getSourseId();
					docId = visitInfo.getMonitorAdminId();
				}
				if(targetAdmin.getHospitalInfo() != null && targetAdmin.getHospitalInfo().getId() != null){
					 hosId = targetAdmin.getHospitalInfo().getId();
				}
				Map<String, Object> params = new HashMap<String, Object>();
				if(visitInfo != null){
					params.put("sourseId", visitInfo.getSourseId());
					params.put("roleId", visitorRole);
				}
				EditRoleMsg roleMsg = new EditRoleMsg();
				try {
					roleMsg = cloudVisitorService.editVisitRole(params,docId,hosId);
					logger.info("roleMsg is "+roleMsg);
					if(roleMsg.getMsg().equals(0)){
						visitInfo.setVisitorRole(visitorRole);
						cloudVisitorService.save(visitInfo);
						msg.setMsg(1);//随访角色编辑成功
					}else{
						msg.setMsg(2);//随访角色编辑失败
						log.info(roleMsg.getMsgbox());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}*/
			
		}
		logger.info("msg is "+msg);
		return msg;
	}

	/**
     * <strong>重置密码</strong>
     * @param id(Integer) 重置密码的用户id password(String) 密码,默认123456加密
     * @return
     */
	@Override
	public void resetPas(Integer id, String password) {
		logger.info("---------- resetPas id:"+id+",password:"+password);
		if(null == id){
			throw new NullPointerException("id["+id+"]");
		}
		if(StringUtils.isBlank(password)){
			throw new NullPointerException("password["+password+"]");
		}
		Admin admin = adminDao.findByAdminId(id);
		admin.setPassword(password);
		try {
			adminDao.update(admin);
		} catch (Exception e) {
			logger.error("---------- resetPas id:"+id+",password:"+password,e);
		}
	}

	@Override
	public void updateIsHospitalNst(Admin admin, Boolean statue) {
		
		HospitalDoctorInfo hospitalDoctorInfo=hospitalDoctorInfoDao.findUniqueBy("phone", admin.getMobile());
		if(null!=hospitalDoctorInfo && null!=hospitalDoctorInfo.getPhone()){
			//如果电话号码不为空则更新状态
			hospitalDoctorInfo.setIsHospitalNst(statue);
			if(Integer.valueOf(0).equals(hospitalDoctorInfo.getStatus())){
				hospitalDoctorInfo.setStatus(1);//设置为认证状态
			}else if(Integer.valueOf(2).equals(hospitalDoctorInfo.getStatus())){
				hospitalDoctorInfo.setStatus(1);//设置为认证状态
			}
			//更新姓名
			if(StringUtils.isEmpty(admin.getUsername())){
				hospitalDoctorInfo.setName(admin.getUsername());
			}
			
			hospitalDoctorInfoDao.save(hospitalDoctorInfo);
		}else{
			//初始化创建一个新用户
			//Admin admin= adminDao.findUniqueBy("mobile", mobile);
			HospitalDoctorInfo hosDoctorInfo=new HospitalDoctorInfo();
			hosDoctorInfo.setAchievement("");
			hosDoctorInfo.setAddTime( new Timestamp(System.currentTimeMillis()));
			hosDoctorInfo.setApplyDate(null);
			hosDoctorInfo.setApplyTimes(null);
			hosDoctorInfo.setCertificationUrl(null);
			hosDoctorInfo.setDoctorId(null);
			hosDoctorInfo.setEducation(null);
			hosDoctorInfo.setExpert(null);
			hosDoctorInfo.setFansNumber(0);
			hosDoctorInfo.setHospitalId(admin.getHospitalInfo().getId());
			//hosDoctorInfo.setId(id)
			hosDoctorInfo.setImgUrl(null);
			hosDoctorInfo.setIntroduction(admin.getIntroduction());
			hosDoctorInfo.setIsHospitalNst(statue);
			hosDoctorInfo.setMajorid(admin.getDoctormajor_id());
			hosDoctorInfo.setMajorPhone(null);
			hosDoctorInfo.setName(admin.getName());
			hosDoctorInfo.setPassTime(new Timestamp(System.currentTimeMillis()));
			hosDoctorInfo.setPassword(admin.getPassword());
			hosDoctorInfo.setPhone(admin.getMobile());
			hosDoctorInfo.setPhysiciaPraticeLicenseUrl(null);
			hosDoctorInfo.setQrCodeUrl(null);
			hosDoctorInfo.setStatus(1);//设置为认证状态
			hosDoctorInfo.setTitle(null);
			hosDoctorInfo.setTitleId(admin.getTitleid());
			hosDoctorInfo.setType(0);
			hospitalDoctorInfoDao.persist(hosDoctorInfo);
			
		}
	}

	/**
		 * @author huangcr
		 * @date 2017-7-25 上午11:15:51
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@Override
	public List<Admin> findAllDoctor(Integer hospitalId) {
		// TODO Auto-generated method stub
		List<Admin> doctors = adminDao.findAllDoctor(hospitalId);
		return doctors;
	}

	/**
		 * @author huangcr
		 * @date 2017-7-25 上午11:15:51
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @param @param name
		 * @param @param hospitalId
		 * @param @return
		 * @throws
		 */ 
	@Override
	public List<Admin> findDoctorByName(String name, Integer hospitalId) {
		// TODO Auto-generated method stub
		return adminDao.findDoctorByName(name,hospitalId);
	}

}