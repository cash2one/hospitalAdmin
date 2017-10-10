package com.jumper.hospital.entity;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Locale;

/**
 * CustodyAdmin entity. @author MyEclipse Persistence Tools
 */

public class MonitorAdmin  extends BaseEntity implements java.io.Serializable {

    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private HospitalInfo hospitalInfo;
     private String email;
     private Boolean isEnabled;
     private Boolean isLocked;
     private Timestamp lockedDate;
     private Timestamp loginDate;
     private Integer loginFailureCount;
     private String loginIp;
     private String name;
     private String password;
     private String username;
     private Integer isFather;
     private Timestamp addTime;
     private String mobile;
     
    // Constructors

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/** default constructor */
    public MonitorAdmin() {
    }

	/** minimal constructor */
    public MonitorAdmin(HospitalInfo hospitalInfo, String email, Boolean isEnabled, Boolean isLocked, Integer loginFailureCount, String password, String username) {
        this.hospitalInfo = hospitalInfo;
        this.email = email;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
        this.loginFailureCount = loginFailureCount;
        this.password = password;
        this.username = username;
    }
    
    public int length() {
		return mobile.length();
	}

	public boolean isEmpty() {
		return mobile.isEmpty();
	}

	public char charAt(int index) {
		return mobile.charAt(index);
	}

	public int codePointAt(int index) {
		return mobile.codePointAt(index);
	}

	public int codePointBefore(int index) {
		return mobile.codePointBefore(index);
	}

	public int codePointCount(int beginIndex, int endIndex) {
		return mobile.codePointCount(beginIndex, endIndex);
	}

	public int offsetByCodePoints(int index, int codePointOffset) {
		return mobile.offsetByCodePoints(index, codePointOffset);
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		mobile.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	@SuppressWarnings("deprecation")
	public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
		mobile.getBytes(srcBegin, srcEnd, dst, dstBegin);
	}

	public byte[] getBytes(String charsetName)
			throws UnsupportedEncodingException {
		return mobile.getBytes(charsetName);
	}

	public byte[] getBytes(Charset charset) {
		return mobile.getBytes(charset);
	}

	public byte[] getBytes() {
		return mobile.getBytes();
	}

	public boolean equals(Object anObject) {
		return mobile.equals(anObject);
	}

	public boolean contentEquals(StringBuffer sb) {
		return mobile.contentEquals(sb);
	}

	public boolean contentEquals(CharSequence cs) {
		return mobile.contentEquals(cs);
	}

	public boolean equalsIgnoreCase(String anotherString) {
		return mobile.equalsIgnoreCase(anotherString);
	}

	public int compareTo(String anotherString) {
		return mobile.compareTo(anotherString);
	}

	public int compareToIgnoreCase(String str) {
		return mobile.compareToIgnoreCase(str);
	}

	public boolean regionMatches(int toffset, String other, int ooffset, int len) {
		return mobile.regionMatches(toffset, other, ooffset, len);
	}

	public boolean regionMatches(boolean ignoreCase, int toffset, String other,
			int ooffset, int len) {
		return mobile.regionMatches(ignoreCase, toffset, other, ooffset, len);
	}

	public boolean startsWith(String prefix, int toffset) {
		return mobile.startsWith(prefix, toffset);
	}

	public boolean startsWith(String prefix) {
		return mobile.startsWith(prefix);
	}

	public boolean endsWith(String suffix) {
		return mobile.endsWith(suffix);
	}

	public int hashCode() {
		return mobile.hashCode();
	}

	public int indexOf(int ch) {
		return mobile.indexOf(ch);
	}

	public int indexOf(int ch, int fromIndex) {
		return mobile.indexOf(ch, fromIndex);
	}

	public int lastIndexOf(int ch) {
		return mobile.lastIndexOf(ch);
	}

	public int lastIndexOf(int ch, int fromIndex) {
		return mobile.lastIndexOf(ch, fromIndex);
	}

	public int indexOf(String str) {
		return mobile.indexOf(str);
	}

	public int indexOf(String str, int fromIndex) {
		return mobile.indexOf(str, fromIndex);
	}

	public int lastIndexOf(String str) {
		return mobile.lastIndexOf(str);
	}

	public int lastIndexOf(String str, int fromIndex) {
		return mobile.lastIndexOf(str, fromIndex);
	}

	public String substring(int beginIndex) {
		return mobile.substring(beginIndex);
	}

	public String substring(int beginIndex, int endIndex) {
		return mobile.substring(beginIndex, endIndex);
	}

	public CharSequence subSequence(int beginIndex, int endIndex) {
		return mobile.subSequence(beginIndex, endIndex);
	}

	public String concat(String str) {
		return mobile.concat(str);
	}

	public String replace(char oldChar, char newChar) {
		return mobile.replace(oldChar, newChar);
	}

	public boolean matches(String regex) {
		return mobile.matches(regex);
	}

	public boolean contains(CharSequence s) {
		return mobile.contains(s);
	}

	public String replaceFirst(String regex, String replacement) {
		return mobile.replaceFirst(regex, replacement);
	}

	public String replaceAll(String regex, String replacement) {
		return mobile.replaceAll(regex, replacement);
	}

	public String replace(CharSequence target, CharSequence replacement) {
		return mobile.replace(target, replacement);
	}

	public String[] split(String regex, int limit) {
		return mobile.split(regex, limit);
	}

	public String[] split(String regex) {
		return mobile.split(regex);
	}

	public String toLowerCase(Locale locale) {
		return mobile.toLowerCase(locale);
	}

	public String toLowerCase() {
		return mobile.toLowerCase();
	}

	public String toUpperCase(Locale locale) {
		return mobile.toUpperCase(locale);
	}

	public String toUpperCase() {
		return mobile.toUpperCase();
	}

	public String trim() {
		return mobile.trim();
	}

	public String toString() {
		return mobile.toString();
	}

	public char[] toCharArray() {
		return mobile.toCharArray();
	}

	public String intern() {
		return mobile.intern();
	}

	/** full constructor */
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public MonitorAdmin(Integer id, HospitalInfo hospitalInfo, String email,
			Boolean isEnabled, Boolean isLocked, Timestamp lockedDate,
			Timestamp loginDate, Integer loginFailureCount, String loginIp,
			String name, String password, String username, Integer isFather,
			Timestamp addTime,String mobile) {
		this.id = id;
		this.hospitalInfo = hospitalInfo;
		this.email = email;
		this.isEnabled = isEnabled;
		this.isLocked = isLocked;
		this.lockedDate = lockedDate;
		this.loginDate = loginDate;
		this.loginFailureCount = loginFailureCount;
		this.loginIp = loginIp;
		this.name = name;
		this.password = password;
		this.username = username;
		this.isFather = isFather;
		this.addTime = addTime;
		this.mobile=mobile;
	}

	public void setId(Integer id) {
        this.id = id;
    }

    public HospitalInfo getHospitalInfo() {
        return this.hospitalInfo;
    }
    
    public void setHospitalInfo(HospitalInfo hospitalInfo) {
        this.hospitalInfo = hospitalInfo;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }
    
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsLocked() {
        return this.isLocked;
    }
    
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Timestamp getLockedDate() {
        return this.lockedDate;
    }
    
    public void setLockedDate(Timestamp lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Timestamp getLoginDate() {
        return this.loginDate;
    }
    
    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getLoginFailureCount() {
        return this.loginFailureCount;
    }
    
    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public String getLoginIp() {
        return this.loginIp;
    }
    
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

	public Integer getIsFather() {
		return isFather;
	}

	public void setIsFather(Integer isFather) {
		this.isFather = isFather;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
    


}