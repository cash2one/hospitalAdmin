package com.jumper.hospital.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.HospitalDescriptionDao;
import com.jumper.hospital.entity.HospitalDescription;
import com.jumper.hospital.entity.HospitalInfo;
import com.jumper.hospital.service.HospitalDescriptionService;
import com.jumper.hospital.service.HospitalInfoService;
import com.jumper.hospital.utils.TimeUtils;
@Service
public class HospitalDescriptionServiceImpl extends BaseServiceImpl<HospitalDescription, Integer> implements HospitalDescriptionService{
	@Autowired
	private HospitalDescriptionDao hospitalDescriptionDao;
	@Autowired
	private HospitalInfoService hospitalInfoService;
	/**
	 * 通过类型描述类型和医院id获得医院的描述信息
	 * @param id
	 * @param type
	 * @return
	 */
	@Override
	public List<HospitalDescription> getHospDescByType(Integer id, int type) {
		return this.hospitalDescriptionDao.getHospDescByType(id, type);
	}

	@Override
	public BaseDao<HospitalDescription, Integer> getBaseDAO() {
		return this.hospitalDescriptionDao;
	}

	@Override
	public String addOrUpdate(String type, String hospitalId, String content,
			String imgUrl, String id, String addTime) {
		HospitalDescription hospitalDesc = new HospitalDescription();
		if(StringUtils.isNumeric(type)){//判断类型，并设置
			int typeInt=Integer.parseInt(type);
			hospitalDesc.setType(typeInt);
		}
		if(StringUtils.isNumeric(hospitalId) ){//判断医院id，并设置
			Integer hospInt=Integer.parseInt(hospitalId);
			HospitalInfo hospitalInfo = this.hospitalInfoService.get(hospInt);
			hospitalDesc.setHospitalId(hospitalInfo);
		}
		if(StringUtils.isNotBlank(content)){// 判断描述的内容，并设置
			hospitalDesc.setContent(content);
		}
		if(StringUtils.isNotBlank(imgUrl)){ //判断图片的内容，并设置
			hospitalDesc.setImgUrl(imgUrl);
		}
		if(StringUtils.isNotBlank(addTime)){//添加时间如果不为空，就将传进来的时间转换成Stamptime
			hospitalDesc.setAddTime(TimeUtils.getTimestampDate(addTime, "yyyy-MM-dd HH:mm:ss"));
		}else{//如果为空，就设置为当前时间
			hospitalDesc.setAddTime(TimeUtils.getCurrentTime());
		}
		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){//id不为空且为数字，则更新
			Integer idInt = Integer.parseInt(id);
			if(0 != idInt){
				hospitalDesc.setId(idInt);
				this.edit(hospitalDesc);
			}
		}else{//否则就保存
			this.save(hospitalDesc);
		}
		return "redirect:/hospital/hospitalInfo";
	}

}
