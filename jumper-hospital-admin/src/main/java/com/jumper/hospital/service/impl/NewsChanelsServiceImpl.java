package com.jumper.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.dao.NewsChanelsDao;
import com.jumper.hospital.entity.NewsChanels;
import com.jumper.hospital.service.NewsChanelsService;
import com.jumper.hospital.utils.Consts;
import com.jumper.hospital.utils.TimeUtils;
import com.jumper.hospital.vo.VOBaseResult;
import com.jumper.hospital.vo.VONewsChanelAdd;

@Service
public class NewsChanelsServiceImpl extends BaseServiceImpl<NewsChanels, Integer> implements NewsChanelsService {

	@Autowired
	private NewsChanelsDao newsChanelsDaoImpl;
	
	@Override
	public BaseDao<NewsChanels, Integer> getBaseDAO() {
		return newsChanelsDaoImpl;
	}

	@Override
	public Page<NewsChanels> findChanelsByPageHospital(Page<NewsChanels> page, Integer hospitalId) {
		return newsChanelsDaoImpl.findChanelsByPageHospital(page, hospitalId);
	}

	@Override
	public VONewsChanelAdd editChanelInfo(Integer id, String chanelName, Integer hospitalId) {
		try {
			NewsChanels chanel;
			long defaultNum = newsChanelsDaoImpl.getDefaultSubNum(hospitalId);
			long showNum = newsChanelsDaoImpl.getShowStateNum(hospitalId);
			if(id == null || id == 0){
				Integer orderBy = newsChanelsDaoImpl.findMaxOrderByByHospital(hospitalId, "asc");
				if(orderBy == null){
					orderBy = 1;
				}else{
					orderBy = orderBy - 1;
				}
				boolean defalutFlag = defaultNum >= 4 ? false : true;
				boolean showFlag = showNum >= 10 ? false : true;
				chanel = new NewsChanels(chanelName, 2, TimeUtils.getCurrentTime(), orderBy, hospitalId, defalutFlag, showFlag);
			}else{
				chanel = newsChanelsDaoImpl.get(id);
				chanel.setChanelName(chanelName);
			}
			NewsChanels result = newsChanelsDaoImpl.save(chanel);
			if(result == null){
				return new VONewsChanelAdd(Consts.FAILED, 0, 0);
			}
			return new VONewsChanelAdd(Consts.SUCCESS, defaultNum, showNum);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new VONewsChanelAdd(Consts.ERROR, 0, 0);
		}
	}

	@Override
	public Integer findMaxOrderByByHospital(Integer hospitalId, String order) {
		return newsChanelsDaoImpl.findMaxOrderByByHospital(hospitalId, order);
	}

	@Override
	public long findChanelNumByHospital(Integer hospitalId) {
		return newsChanelsDaoImpl.findChanelNumByHospital(hospitalId);
	}

	@Override
	public VOBaseResult changeChanelState(Integer id, Integer operate, Integer type, Integer hospitalId) {
		try {
			if(id == null || id == 0 || type == null || type == 0){
				return new VOBaseResult(Consts.PARAMS_ERROR, "处理失败，参数错误");
			}
			NewsChanels chanel = newsChanelsDaoImpl.get(id);
			if(chanel != null){
				switch (type) {
					case 1:
						/** 默认订阅 **/
						boolean defaultSub = operate == 0 ? false : true;
						if(defaultSub){
							long defaultNum = newsChanelsDaoImpl.getDefaultSubNum(hospitalId);
							if(defaultNum >= 4){
								return new VOBaseResult(Consts.FAILED, "处理失败，默认订阅不超过4个频道");
							}
							chanel.setIsDefaultSub(defaultSub);
						}else{
							chanel.setIsDefaultSub(defaultSub);
						}
						break;
					case 2:
						boolean showFlag = operate == 0 ? false : true;
						if(showFlag){
							long showNum = newsChanelsDaoImpl.getShowStateNum(hospitalId);
							if(showNum >= 10){
								return new VOBaseResult(Consts.FAILED, "处理失败，app端显示不超过10个频道");
							}
							chanel.setState(showFlag);
						}else{
							chanel.setState(showFlag);
						}
						break;
					default:
						break;
				}
			}
			NewsChanels result = newsChanelsDaoImpl.save(chanel);
			if(result == null){
				return new VOBaseResult(Consts.FAILED, "处理失败");
			}
			return new VOBaseResult(Consts.SUCCESS, "处理成功");
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new VOBaseResult(Consts.ERROR, "处理异常");
		}
	}

	@Override
	public List<NewsChanels> findChanelByHospitalId(Integer hospitalId) {
		return newsChanelsDaoImpl.findChanelByHospitalId(hospitalId);
	}

}
