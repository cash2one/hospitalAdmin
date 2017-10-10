package com.jumper.hospital.utils;

public enum HBSmsErrorCode {
	
	/*9002	未知命令
	
	9012	短信消息内容错误
	9013	目标地址错误
	9014	短信内容太长
	9015	路由错误
	9016	没有下发网关
	9017	定时时间错误
	9018	有效时间错误
	9019	无法拆分或者拆分错误
	9020	号码段错误
	9021	消息编号错误，这个和PacketIndex参数有关
		
	9101	验证失败，一般和用户名/密码/IP地址相关
	9102	没有填写用户名

	9104	IP地址不对
	9105	超过最大连接数，就是tcp连接数，http也是一样的
	9106	协议版本错误
	9107	帐号无效，比如过期/禁用
		
	9902	网关无此能力
	9903	二进制数据太长了；如网关没有特别说明，一般不能超过140，
	9904	网关不支持EsmClass字段，或等同字段
	9905	网关不支持ProtocolID字段，或等同字段
	9906	网关不支持UDHI字段，或等同字段
	9907	网关支持Letter字段发送，但短信记录没有letter
	9908	网关不存在
	9909	网关没有应答
	9910	网关不支持该短信编码
		
	9401	计费错误
	9402	非法内容
	9403	黑名单
	9404	
	9405	Api帐号丢失
	9406	配置拒绝，就是帐号设置了拒绝标记
	9407	帐号没有生成时间,这个属于非法帐号
	9408	消息超时，超过短信或帐号或系统设置的生存时间
	9409	由约束规则拒绝
	9410	状态报告超时
	9411	
	9412	帐号无效
	9413	重发拦截
	9414	转发时丢弃，比如该通道已经废弃
	9415	人工审核失败
	9416	可能是诈骗信息
	9417	不匹配模板
	9418	拒绝审核（审核功能可能关闭）
	9419	超过该手机号码的日发送次数限制
		
	9501	非法目标地址，即手机号
	9502	消息无法投入队列
		
	9601	上行路由失败
	9602	超过最大重试
		
	9701	通知失败
		
	9801	投递地址错
		
		
		*/
	ERROR1(9804, "投递接收结果失败"),
	ERROR2(9803, "投递发送数据失败"),
	ERROR3(9802, "无法连接到服务器"),
	ERROR4(9103, "名字没找到");
	private int type;
	private String name;

	private HBSmsErrorCode(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static String getName(int index){
		HBSmsErrorCode[] sms = HBSmsErrorCode.values();
		for(HBSmsErrorCode type : sms){
			if(type.type == index){
				return type.name;
			}
		}
		return null;
		
	}
	
}
