package com.jumper.hospital.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumper.hospital.entity.AppIndex;
import com.jumper.hospital.service.AppIndexService;

/**
 * APP首页维护Controller
 * @author qinxiaowei
 *
 */
@Controller
@RequestMapping("appIndex")
public class AppIndexController extends BaseController {
	
	@Autowired
	private AppIndexService appIndexService;
	
	/**
	 * 查询所有app首页数据
	 * @return
	 */
	@RequestMapping("findAppIndex")
	public String findAppIndex(HttpServletRequest request) {
		//放置位置 上
		List<AppIndex> upList = new ArrayList<AppIndex>();
		//放置位置 下
		List<AppIndex> downList = new ArrayList<AppIndex>();
		//首页数据
		List<AppIndex> list = appIndexService.findAppIndex();
		for(int i=0; i<list.size(); i++) {
			AppIndex app = list.get(i);
			//显示到上面
			if(app.getShowPosition() == 1) {
				upList.add(app);
			} else {
				downList.add(app);
			}
		}
		request.setAttribute("upList", upList);
		request.setAttribute("downList", downList);
		return "hospital/appIndex";
	}
	
	/**
	 * 添加and删除
	 * @param request
	 * @return
	 */
	@RequestMapping("saveAppIndex")
	@ResponseBody
	public Map<String, Object> saveAppIndex(String id, String title, String description, String url, String showPosition, String isShow, String hospitaId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(Integer.parseInt(isShow) == 1) {
			int count = appIndexService.findIsShowCount(Integer.parseInt(showPosition));
			if(Integer.parseInt(showPosition) == 1) {
				//上面
				if(count >= 4) {
					map.put("result", "C");
					map.put("msg", "上面部分最多只显示4个");
					return map;
				}
			} else {
				//下面
				if(count >= 8) {
					map.put("result", "C");
					map.put("msg", "下面部分最多只显示8个");
					return map;
				}
			}
		}
		try {
			if(id!=null && !id.equals("")) {
				AppIndex appIndex = appIndexService.get(Integer.parseInt(id));
				appIndex.setTitle(title);
				appIndex.setUrl(url);
				appIndex.setShowPosition(Integer.parseInt(showPosition));
				appIndex.setIsShow(Integer.parseInt(isShow));
				appIndex.setDescription(description);
				appIndex.setHospitaId(Integer.parseInt(hospitaId));
				//更新
				appIndexService.updateAppIndex(appIndex);
				map.put("msg", "更新成功");
			} else {
				AppIndex ai = new AppIndex();
				ai.setTitle(title);
				ai.setUrl(url);
				ai.setSort(appIndexService.findLastAppIndex(Integer.parseInt(showPosition)).getSort()+1);
				ai.setShowPosition(Integer.parseInt(showPosition));
				ai.setIsShow(Integer.parseInt(isShow));
				ai.setDescription(description);
				ai.setHospitaId(Integer.parseInt(hospitaId));
				//添加
				appIndexService.saveAppIndex(ai);
				map.put("msg", "添加成功");
			}
			map.put("result", "Y");
		} catch(Exception ex) {
			map.put("result", "N");
			map.put("msg", "操作异常，请联系管理员！");
			ex.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public Map<String, Object> del(@RequestParam("id") String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean bool = appIndexService.deleteAppIndex(Integer.parseInt(id));
		if(bool) {
			map.put("result", "Y");
		} else {
			map.put("result", "N");
		}
		return map;
	}
	
	/**
	 * 通过id查询数据
	 * @param id
	 * @return
	 */
	@RequestMapping("findAppIndexById")
	@ResponseBody
	public Map<String, Object> findAppIndexById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		AppIndex  appIndex = appIndexService.findAppIndexById(Integer.parseInt(id));
		//查询医院所在省份、城市
		Object[] obj = appIndexService.findHospitalInfoById(appIndex.getHospitaId());
		map.put("result", appIndex);
		map.put("proId", obj[0]);
		map.put("cityId", obj[1]);
		return map;
	}
	
	/**
	 * 查询所有省份
	 * @return
	 */
	@RequestMapping("findProvince")
	@ResponseBody
	public Map<String, Object> findProvince() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", appIndexService.findProvince());
		return map;
	}
	
	/**
	 * 查询对应省份下所有城市
	 * @param proId
	 * @return
	 */
	@RequestMapping("findCity")
	@ResponseBody
	public Map<String, Object> findCity(String proId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", appIndexService.findCity(Integer.parseInt(proId)));
		return map;
	}
	
	/**
	 * 查询对应城市下所有医院
	 * @param proId
	 * @param cityId
	 * @return
	 */
	@RequestMapping("findHospitalInfo")
	@ResponseBody
	public Map<String, Object> findHospitalInfo(String proId, String cityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", appIndexService.findHospitalInfo(Integer.parseInt(proId), Integer.parseInt(cityId)));
		return map;
	}
}
