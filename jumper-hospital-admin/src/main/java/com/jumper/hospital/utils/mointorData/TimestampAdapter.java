package com.jumper.hospital.utils.mointorData;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 * 让Apache CXF 支持传递java.sql.Timestamp的适配器
 * @author tanqing
 *
 */
public class TimestampAdapter extends XmlAdapter<String, Timestamp>{

	@Override
	public String marshal(Timestamp timestamp) throws Exception {
		return TimeUtils.getTimeStampNumberFormat(timestamp);
	}

	@Override
	public Timestamp unmarshal(String str) throws Exception {
		return TimeUtils.getCurrentTime(str,"yyyy-MM-dd HH:mm:ss");
	}



}
