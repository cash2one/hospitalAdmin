package com.jumper.hospital.Comparator;

import java.util.Comparator;

import com.jumper.hospital.entity.Authority;

@SuppressWarnings("rawtypes")
public class AuthorityComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		Authority auth1 = (Authority) o1;
		Authority auth2 = (Authority) o2;
		int result = auth1.getId().compareTo(auth2.getId());
		return result;
	}

}
