package com.jumper.hospital.service;
import com.fasterxml.jackson.databind.JsonNode;
import com.jumper.hospital.utils.ReturnMsg;

public abstract interface CheckColumnService {

	/**
	 * 检查是否存在key值
	 * @param rootNode jsonNode
	 * @param filedName key名称
	 * @param errorMessage 错误消息
	 * @return ReturnMsg
	 */
	public ReturnMsg checkFiled(JsonNode rootNode, String filedName, String errorMessage);

	/**
	 * 检查是否存在key及value值，都不为空校验通过
	 * @param rootNode jsonNode
	 * @param filedName key名称
	 * @param errorMessage 错误消息
	 * @return ReturnMsg
	 */
	public ReturnMsg checkFiledExistAndNotNull(JsonNode rootNode, String filedName, String errorMessage);

	/**
	 * 检查是否存在key和value值及是否为数字类型，都不为空且是数字类型校验通过(如果值为负，eg:-1 校验不通过)
	 * @param rootNode jsonNode
	 * @param filedName key名称
	 * @param errorMessage 错误消息
	 * @return ReturnMsg
	 */
	public ReturnMsg checkFiledNotNullAndIsNumeric(JsonNode rootNode, String filedName, String errorMessage);

	/**
	 * 分页字段判断
	 * @param rootNode jsonNode
	 * @return ReturnMsg
	 */
	public ReturnMsg checkPageFiled(JsonNode rootNode);

	/**
	 * 检查以逗号分隔的多值数字字段是否全为数字
	 * @param rootNode jsonNode
	 * @return ReturnMsg
	 */
	public ReturnMsg checkMultiValueIsNumeric(JsonNode rootNode, String filedName, String errorMessage);

	/**
	 * 检查是否为合法日期字段
	 * @param rootNode jsonNode
	 * @return ReturnMsg
	 */
	public ReturnMsg checkIsDateField(JsonNode rootNode, String filedName, String errorMessage);

}
