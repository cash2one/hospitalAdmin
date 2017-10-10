package com.jumper.hospital.service;
import com.jumper.hospital.entity.AlipayConfig;

public interface AlipayConfigService extends BaseService<AlipayConfig, Integer> {

	
	/**
	 * 根据医院id，获取到该医院的支付配置信息
	 * @param hospitalId
	 * @return
	 */
	public AlipayConfig getOrderAlipayConfig(Integer hospitalId);

}
