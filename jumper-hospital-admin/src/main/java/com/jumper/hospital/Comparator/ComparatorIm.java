package com.jumper.hospital.Comparator;

import java.util.Comparator;

import com.jumper.hospital.vo.VOConsultantReplyInfo;


@SuppressWarnings("rawtypes")
public class ComparatorIm implements Comparator{

	public int compare(Object o1, Object o2) {
		int flag = 0;
		if(o1!=null && o2!=null){
			VOConsultantReplyInfo user0 = (VOConsultantReplyInfo)o1;  
		    VOConsultantReplyInfo user1 = (VOConsultantReplyInfo)o2;  
	         flag = user0.getSendTime().compareTo(user1.getSendTime());  
		}
		 return flag;  
	}
	
}
