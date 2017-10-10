package com.jumper.hospital.dao;
/**
 * 根据医院获取微信配置信息
 */
import com.jumper.hospital.entity.WXpayConfig;

public interface WXpayConfigDao extends BaseDao<WXpayConfig, Integer> {

	
	/**
	 * 根据医院id，获取到该医院的微信配置信息
	 * @param hospitalId
	 * @return
	 */
	public WXpayConfig getOrderWXpayConfig(Integer hospitalId);

}
