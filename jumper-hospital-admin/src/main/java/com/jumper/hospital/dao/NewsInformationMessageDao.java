package com.jumper.hospital.dao;

import com.jumper.hospital.base.Page;
import com.jumper.hospital.entity.NewsInformationMessage;

public interface NewsInformationMessageDao extends BaseDao<NewsInformationMessage, Integer> {

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
	 * 检测医院资讯自己的banner是否有超过4条，超过则不允许继续添加
	 * @param id
	 * @param hospitalId
	 * @return
	 */
	public boolean checkBannerCount(Integer id, Integer hospitalId);
	
}
