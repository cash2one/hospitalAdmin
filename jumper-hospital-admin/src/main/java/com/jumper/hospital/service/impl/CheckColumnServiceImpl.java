package com.jumper.hospital.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.jumper.hospital.service.CheckColumnService;
import com.jumper.hospital.utils.Const;
import com.jumper.hospital.utils.ReturnMsg;
import com.jumper.hospital.utils.TimeUtils;

import com.fasterxml.jackson.databind.JsonNode;

public class CheckColumnServiceImpl implements CheckColumnService {

	private final static Logger logger = Logger.getLogger(CheckColumnServiceImpl.class);

	@Override
	public ReturnMsg checkFiled(JsonNode rootNode, String filedName, String errorMessage) {
		ReturnMsg ret = new ReturnMsg();
		ret.setMsg(Const.SUCCESS);
		try {
			if(!rootNode.has(filedName)){
				logger.error("the filed "+filedName+" is none!");
				ret = ReturnMsg.setReturnMsg(Const.FAILED, errorMessage);
			}
		} catch (Exception e) {
			ret.setMsg(Const.FAILED);
		}
		return ret;
	}

	@Override
	public ReturnMsg checkFiledExistAndNotNull(JsonNode rootNode, String filedName, String errorMessage) {
		ReturnMsg ret = new ReturnMsg();
		ret.setMsg(Const.SUCCESS);
		try {
			if(!rootNode.has(filedName)){
				logger.error("the filed "+filedName+" is none!");
				ret = ReturnMsg.setReturnMsg(Const.FAILED, errorMessage);
			}
			if(StringUtils.isEmpty(rootNode.path(filedName).asText())){
				logger.error("the value "+filedName+" is empty!");
				ret = ReturnMsg.setReturnMsg(Const.FAILED, errorMessage);
			}
		} catch (Exception e) {
			ret.setMsg(Const.FAILED);
		}
		return ret;
	}

	@Override
	public ReturnMsg checkFiledNotNullAndIsNumeric(JsonNode rootNode, String filedName, String errorMessage) {
		ReturnMsg ret = checkFiledExistAndNotNull(rootNode, filedName, errorMessage);
		if(ret.getMsg() == Const.SUCCESS){
			if(!StringUtils.isNumeric(rootNode.path(filedName).asText())){
				ret = ReturnMsg.setReturnMsg(Const.FAILED, errorMessage);
			}
		}
		return ret;
	}

	@Override
	public ReturnMsg checkPageFiled(JsonNode rootNode) {
		ReturnMsg ret = new ReturnMsg();
		ret.setMsg(Const.SUCCESS);
		try {
			ret = checkFiledNotNullAndIsNumeric(rootNode, "page_size", "请输入每页显示条数");
			if(ret.getMsg() == Const.FAILED) {
	        	return ret;
	        }

			ret = checkFiledNotNullAndIsNumeric(rootNode, "page_index", "请输入当前页码");
	        if(ret.getMsg() == Const.FAILED) {
	        	return ret;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			ret.setMsg(Const.FAILED);
		}
		return ret;
	}

	@Override
	public ReturnMsg checkMultiValueIsNumeric(JsonNode rootNode, String filedName, String errorMessage) {
		ReturnMsg ret = checkFiledExistAndNotNull(rootNode, filedName, errorMessage);
		if(ret.getMsg() == Const.SUCCESS){
			String[] values = rootNode.path(filedName).asText().split("\\,");
			for(String value:values){
				if(!StringUtils.isNumeric(value)){
					ret = ReturnMsg.setReturnMsg(Const.FAILED, errorMessage);
					return ret;
				}
			}
		}
		return ret;
	}

	@Override
	public ReturnMsg checkIsDateField(JsonNode rootNode, String filedName, String errorMessage) {
		ReturnMsg ret = checkFiledExistAndNotNull(rootNode, filedName, errorMessage);
		if(ret.getMsg() == Const.SUCCESS){
			boolean isValidDate= TimeUtils.isValidDate(rootNode.path(filedName).asText(),1);
			if(!isValidDate){
				return ReturnMsg.setReturnMsg(Const.FAILED, "invalid date");
			}
		}
		return ret;
	}

}
