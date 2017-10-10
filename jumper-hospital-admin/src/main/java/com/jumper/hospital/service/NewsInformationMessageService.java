package com.jumper.hospital.service;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.Admin;
import com.jumper.hospital.entity.NewsInformationMessage;

public interface NewsInformationMessageService extends BaseService<NewsInformationMessage, Integer> {
   	
	/**
	 * 医院资讯首页获取数据
	 * @param hospId 医院ID
	 * @param keywords 搜索关键字
	 * @param page 分页信息
	 * @param operate 操作类型，1：已发布列表，2：未发布列表，3：广告列表
	 * @return
	 */
	public Page<NewsInformationMessage> getInformationList(Integer hospId, String keywords, Page<NewsInformationMessage> page, Integer operate);
	/**
	 * 编辑医院资讯，包括保存并发送，保存至未发布和保存
	 * @param message
	 * @return
	 */
	public String updateInformation(NewsInformationMessage message, Integer type, Admin admin);
	/**
	 * 发布资讯
	 * @param id 记录ID
	 * @param admin 后台管理员
	 * @return
	 */
	public String publishInformation(Integer id, Admin admin);
	/**
	 * 广告页的添加与取消
	 * @param id
	 * @param hospitalId
	 * @param operate
	 * @return
	 */
	public String handlerBanner(Integer id, Integer hospitalId, Integer operate);
}
