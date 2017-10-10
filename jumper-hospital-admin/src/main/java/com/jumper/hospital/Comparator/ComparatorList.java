package com.jumper.hospital.Comparator;

import java.util.Comparator;

import com.jumper.hospital.vo.VOConsultantReplyInfo;


@SuppressWarnings("rawtypes")
public class ComparatorList implements Comparator{

	public int compare(Object o1, Object o2) {
	    VOConsultantReplyInfo user0 = (VOConsultantReplyInfo)o1;  
	    VOConsultantReplyInfo user1 = (VOConsultantReplyInfo)o2;  
        int flag = user0.getTime().compareTo(user1.getTime());  
        return flag;  
	}

}
