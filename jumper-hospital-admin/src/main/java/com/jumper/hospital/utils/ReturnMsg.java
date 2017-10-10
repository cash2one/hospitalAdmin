package com.jumper.hospital.utils;

import org.apache.log4j.Logger;
/**
 * 返回消息
 * @author win
 *
 */
public class ReturnMsg
{
    @SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ReturnMsg.class);

    private int msg;
    private String msgbox;
    /*给个默认的空数组为初始值，是方便失败后直接可以返回空数组，其他时候，该对象引用会被替换为返回的数据*/
    private Object data = new Object[] {};

    public int getMsg()
    {
        return msg;
    }
    public void setMsg(int msg)
    {
        this.msg = msg;
    }

    public String getMsgbox()
    {
        return msgbox;
    }
    public void setMsgbox(String msgbox)
    {
        this.msgbox = msgbox;
    }

    public Object getData()
    {
        return data;
    }
    public void setData(Object data)
    {
        this.data = data;
    }

    public static ReturnMsg setReturnMsg(int flag, String message)
    {
        ReturnMsg msg = new ReturnMsg();
        msg.setMsg(flag);
        msg.setMsgbox(message);
        return msg;
    }

}