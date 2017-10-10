package com.jumper.hospital.service;
import com.jumper.hospital.entity.WXpayConfig;

public interface WXpayConfigService extends BaseService<WXpayConfig, Integer> {

	
	/**
	 * 根据医院id，获取到该医院的微信配置信息
	 * @param hospitalId
	 * @return
	 */
	public WXpayConfig getOrderWXpayConfig(Integer hospitalId);

}
