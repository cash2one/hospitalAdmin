package com.jumper.hospital.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.entity.HospitalNotice;
import com.jumper.hospital.service.HospitalNoticeService;
import com.jumper.hospital.utils.TimeUtils;

@Controller
@RequestMapping("/notice")
public class HospitalNoticeController extends BaseController{
	@Autowired
	private HospitalNoticeService hospitalNoticeService;
	
	@RequestMapping("/index")
	public String getNoticeList(ModelMap model,Page<HospitalNotice> page){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		//获取公告列表
		Page<HospitalNotice> pageData = hospitalNoticeService.findNoticeList(page,hospitalInfo.getId());
		for (HospitalNotice hospitalNotice : pageData.getResult()) {
			try {
				if(hospitalNotice.getEndTime()!=null){
					if(TimeUtils.daysBetween(hospitalNotice.getEndTime(), TimeUtils.getCurrentTime())>0){
						hospitalNotice.setStatus(-1);
						hospitalNoticeService.edit(hospitalNotice);
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("page", pageData);
		pageLocation(model, "information", "notice", "noticeList");
		return "news/noticeList";
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public String editNotice(HttpServletRequest req){
		//获取当前登陆人员信息
		Admin admin = this.getLoginAdminInfo();
		//获得医院id和医院信息
		HospitalInfo hospitalInfo = admin.getHospitalInfo();
		String ids = req.getParameter("id");
		String notice = req.getParameter("notice");
		String endTime = req.getParameter("endTime");
		if(ids!=null&&!"".equals(ids)){
			HospitalNotice hospitalNotice = hospitalNoticeService.get(Integer.parseInt(ids));
			hospitalNotice.setEndTime(TimeUtils.getTimestampDate(endTime, "yyyy-MM-dd"));
			hospitalNotice.setNotice(notice);
			hospitalNoticeService.edit(hospitalNotice);
			return "editY";
		}else{
			HospitalNotice newNotice = new HospitalNotice();
			newNotice.setAddTime(TimeUtils.getCurrentTime());
			newNotice.setEndTime(TimeUtils.getTimestampDate(endTime, "yyyy-MM-dd"));
			newNotice.setHospitalInfo(hospitalInfo);
			newNotice.setIsDelete(0);
			newNotice.setNotice(notice);
			newNotice.setStatus(0);
			hospitalNoticeService.save(newNotice);
			return "addY";
		}
			
	}
	
	@ResponseBody
	@RequestMapping("/checkNotice")
	public String checkNotice(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(ids!=null&&!"".equals(ids)){
			int id = Integer.parseInt(ids);
			HospitalNotice hospitalNotice = hospitalNoticeService.get(id);
			if(hospitalNotice.getStatus()==1){
				return "Y";
			}else{
				return "N";
			}
		}
		return "N";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(ids!=null&&!"".equals(ids)){
			int id = Integer.parseInt(ids);
			HospitalNotice hospitalNotice = hospitalNoticeService.get(id);
			hospitalNoticeService.delete(hospitalNotice);
			return "Y";
		}else{
			return "N";
		}
	}
	
	@ResponseBody
	@RequestMapping("/checkPublish")
	public String checkPublish(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(ids!=null&&!"".equals(ids)){
			HospitalNotice hospitalNotice = hospitalNoticeService.get(Integer.parseInt(ids));
			int hospitalId = hospitalNotice.getHospitalInfo().getId();
			List<HospitalNotice> list = hospitalNoticeService.findNotices(hospitalId);
			if(list!=null&&list.size()>0){
				for (HospitalNotice hospitalNotice2 : list) {
					if(hospitalNotice2.getStatus()==1){
						return "Y";
					}
				}
				return "N";
			}
		}
		return "error";
	}
	
	@ResponseBody
	@RequestMapping("/publish")
	public String publish(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(ids!=null&&!"".equals(ids)){
			HospitalNotice hospitalNotice = hospitalNoticeService.get(Integer.parseInt(ids));
			hospitalNotice.setStatus(1);
			hospitalNoticeService.edit(hospitalNotice);
			return "Y";
		}
		return "N";
	}
	
	@ResponseBody
	@RequestMapping("/backPublish")
	public String backPublish(HttpServletRequest req){
		String ids = req.getParameter("id");
		if(ids!=null&&!"".equals(ids)){
			HospitalNotice hospitalNotice = hospitalNoticeService.get(Integer.parseInt(ids));
			hospitalNotice.setStatus(0);
			hospitalNoticeService.edit(hospitalNotice);
			return "Y";
		}
		return "N";
	}
}
