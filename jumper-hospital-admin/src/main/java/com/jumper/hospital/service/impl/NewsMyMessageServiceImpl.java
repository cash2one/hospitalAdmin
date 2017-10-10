package com.jumper.hospital.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalInfoDao;
import com.jumper.hospital.dao.NewsMessageDao;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.NewsMessage;
import com.jumper.hospital.service.AdminService;
import com.jumper.hospital.service.NewsMyMessageSevice;
import com.jumper.hospital.utils.TimeUtils;

@Service
public class NewsMyMessageServiceImpl  extends BaseServiceImpl<NewsMessage, Integer> implements NewsMyMessageSevice {
	@Autowired
	private NewsMessageDao newsMessageDao;
	@Autowired
	private AdminService adminServiceImpl;
	@Autowired
	private HospitalInfoDao hospitalInfoDao;
	
	@Override
	public String addOrUpdate(NewsMessage news,String id) {
		NewsMessage newsEntity=new NewsMessage();
		
		//获取当前登录人的信息，并设置
		Subject currentUser = SecurityUtils.getSubject();
		String user = currentUser.getPrincipal().toString();
		Admin admin = adminServiceImpl.findByUsername(user);
		newsEntity.setAdminId(admin);
		// 获得医院id和医院信息，并设置
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		Integer hospId = hospitalInfo.getId();
		HospitalInfo hospital=hospitalInfoDao.get(hospId);
		
		newsEntity.setHospitalInfo(hospital);
		if(StringUtils.isNotBlank(news.getTitle())){// 判断描述的标题，并设置
			newsEntity.setTitle(news.getTitle());
		}else{
			newsEntity.setTitle("");
		}
		if(StringUtils.isNotBlank(news.getContent())){// 判断描述的内容，并设置
			newsEntity.setContent(news.getContent());
		}
		newsEntity.setIsDelete(0);//设置默认为非删除
		if(StringUtils.isNumeric(id)&&!StringUtils.isEmpty(id)){//id不为空且为数字，则更新
			Integer idInt = Integer.parseInt(id);
			if(0 != idInt){
				newsEntity.setId(idInt);    
				/*newsEntity.setModifyTime(TimeUtils.getCurrentTime());//设置修改时间
				newsEntity.setModifyEmp(admin.getName());//设置修改人
*/				//修改
				this.edit(newsEntity);
			}
		}else if(StringUtils.isNumeric(id)&&StringUtils.isEmpty(id)){
			//否则就保存
			newsEntity.setAddEmp(admin.getName());//设置添加人
			newsEntity.setAddTime(TimeUtils.getCurrentTime());//设置添加时间
			if(!newsEntity.equals("")&&newsEntity!=null){
				newsMessageDao.save(newsEntity);
			}
		}
		
		return "redirect:/news/newsMessageList";
	}

	@Override
	public Page<NewsMessage> list(Page<NewsMessage> page,Integer hospId, String keywords) {
		Page<NewsMessage> pageList=newsMessageDao.newsList(page,hospId, keywords);
		
		
		
		return pageList;
	}

	@Override
	public BaseDao<NewsMessage, Integer> getBaseDAO() {
		return null;
	}

	@Override
	public boolean deleteNewsMyMessage(Integer id) {
		return newsMessageDao.deleteNewsMyMessage(id);		
	}

	@Override
	public NewsMessage editNewsMessage(Integer id) {
		return this.newsMessageDao.get(id);
	}

	@Override
	public List<NewsMessage> findMessagesByPregnantWeek(String pregnantWeek) {
		return newsMessageDao.findMessagesByPregnantWeek(pregnantWeek);
	}



	@Override
	public NewsMessage saveMyMessage(String content, Admin admin,
			HospitalInfo hospitalInfo, int pregnantWeek,String pregnant_Week) {
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setAddTime(TimeUtils.getCurrentTime());
		newsMessage.setContent(content);
		newsMessage.setIsDelete(0);
		newsMessage.setTitle("aaa");
		newsMessage.setAddEmp(admin.getName());
		newsMessage.setAdminId(admin);
		newsMessage.setHospitalInfo(hospitalInfo);
		newsMessage.setType(0);
		if(pregnantWeek==-1){
			newsMessage.setPregnantWeek("全部用户");
		}else{
			newsMessage.setPregnantWeek(pregnant_Week);
		}
		newsMessage.setPushTime(TimeUtils.getCurrentTime());
		return newsMessageDao.persist(newsMessage);
	}

	/**
	 * @param @return
	 * @throws
	 */
	@Override
	public NewsMessage saveMyMessageforHttp(NewsMessage newsMessage) {
		// TODO Auto-generated method stub
		Admin admin = adminServiceImpl.findByUsername("admin");
		if(newsMessage.getHospitalInfo() == null){
			newsMessage.setHospitalInfo(admin.getHospitalInfo());
		}
		newsMessage.setAddTime(new Timestamp((new Date()).getTime()));
		newsMessage.setPushTime(new Timestamp((new Date()).getTime()));
		newsMessage.setAdminId(admin);
		newsMessage.setType(0);
		return newsMessageDao.persist(newsMessage);
	}
}
