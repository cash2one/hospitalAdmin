package com.jumper.hospital.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 时间转换工具类
 * @author rent
 * @date 2015-09-18
 *
 */
public class TimeUtils {

	/**
	 * 格式化当前时间
	 * @param format 格式化规则
	 * @return String 格式化以后的Time字符串
	 */
	public static String getCurrentTime(String format){
		if(StringUtils.isEmpty(format)){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	
	/**
	 * 返回当前时间的Timestamp格式
	 * @return 当前时间
	 */
	public static Timestamp getTCurrentTime(){
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 时间转换
	 * @param date 时间
	 * @param format 转换格式
	 * @return String 转换后的String类型时间字符串
	 */
	public static String convertTime(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 时间转换
	 * @param date
	 * @return
	 */
	public static String convertTime(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
    *
    * @param dateStr
    * @param flag
    *            0:"yyyy-MM-dd HH:mm:ss";1:"yyyy-MM-dd";2:"yyyy/MM/dd"
    * @return
    */
   public static boolean isValidDate(String dateStr, int flag) {
       if (flag == 0)
       {
           return isValidDate(dateStr, "yyyy-MM-dd HH:mm:ss");
       } else if (flag == 1)
       {
           return isValidDate(dateStr, "yyyy-MM-dd");
       } else if (flag == 2)
       {
           return isValidDate(dateStr, "yyyy/MM/dd");
       }
       return false;

   }

   /**
   *
   * @param dateStr
   *            默认 yyyy/MM/dd HH:mm
   * @return
   */
  public static boolean isValidDate(String dateStr, String fomart) {
      boolean convertSuccess = true;
      SimpleDateFormat format = new SimpleDateFormat(fomart);
      try {
          format.setLenient(false);
          format.parse(dateStr);
      } catch (ParseException e) {
          convertSuccess = false;
      }
      return convertSuccess;
  }

	/**
	 * 获取当天开始时间 ex:2015-03-03 00:00:00
	 * @param day 当前时间往后多少天
	 * @return
	 */
	public static Date getCurrentStartTime(int day, Date date) {
		Calendar todayStart = Calendar.getInstance();
		if (date != null) {
			todayStart.setTime(date);
		}
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		todayStart.add(Calendar.DATE, day);
		return todayStart.getTime();
	}

	/**
	 * 获取当前结束时间 ex:2015-03-03 23:59:59
	 * @param day 当前时间往后多少天
	 * @return
	 */
	public static Date getCurrentEndTime(int day, Date date) {
		Calendar todayStart = Calendar.getInstance();
		if (date != null) {
			todayStart.setTime(date);
		}
		todayStart.set(Calendar.HOUR_OF_DAY, 23);
		todayStart.set(Calendar.MINUTE, 59);
		todayStart.set(Calendar.SECOND, 59);
		todayStart.set(Calendar.MILLISECOND, 59);
		todayStart.add(Calendar.DATE, day);
		return todayStart.getTime();
	}

	/**
	 * 根据当前时间计算往前或后推移多少天
	 * @param day
	 * @return
	 */
	public static Date getDayOfTime(int day){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 根据当前生成订单ID，规则:当前时间年月日时分秒+2位随机数
	 * @return String
	 */
	public static String randomOrderId(){
		String currentTime = getCurrentTime("yyyyMMddHHmmss");
		String random = RandomStringUtils.randomNumeric(2);
		return currentTime + random;
	}

	public static String getOffLineNo(){
		String currentTime = getCurrentTime("yyMMddHHmm");
		String random = RandomStringUtils.randomNumeric(2);
		return "XC"+currentTime+random;
	}

	public static String getOnLineNo(){
		String currentTime = getCurrentTime("yyMMddHHmm");
		String random = RandomStringUtils.randomNumeric(2);
		return "OC"+currentTime+random;
	}

	/**
	 * @param date1
	 * @param date2
	 * @return返回日期相隔的天数
	 * @throws ParseException
	 */
	public static long getcomparedatedays(String date1, String date2) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dt1 = df.parse(date1);
		Date dt2 = df.parse(date2);
		return (Math.abs(dt2.getTime() - dt1.getTime())) / (3600 * 1000 * 24);

	}

	/**
	 * 将时间戳格式转换为Date格式时间
	 * @param timeStamp
	 * @return
	 */
	public static Date convertTimeStampToDate(long timeStamp){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date(timeStamp*1000));
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取两个时间差
	 * @return
	 */
	public static String getTimeDifferent(long timeStamp){
		Date nowDate = new Date();
		long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long ns = 1000;
	    long diff = nowDate.getTime() - timeStamp*1000;
	    //long day = diff / nd;
	    //long hour = diff % nd / nh;
	    long min = diff % nd % nh / nm;
	    long sec = diff % nd % nh % nm / ns;
	    return min + ":" + sec;
	}

	/**
	 * 计算怀孕了几周 、孕周计算
	 * @param date 预产期
	 * @return 数组 [周，天，离预产期的天数]
	 * @throws ParseException
	 */
	public static int[] getPregnantWeek(Date date) throws ParseException {
		int[] data = new int[3];
	    Date nowDate = new Date();
	    if(nowDate.after(date)){
	    	return new int[] {39,6,280};
	    }
	    long days = getcomparedatedays(TimeUtils.convertTime(date, "yyyy-MM-dd HH:mm:ss"), TimeUtils.convertTime(nowDate, "yyyy-MM-dd 00:00:00"));
	    int week = (int)(days / 7L);
	    int day = (int)((week + 1) * 7 - days);
	    if(day == 7){
	    	day = 0;
	    	week-=1;
	    }
	    data[0] = (39 - week);
	    data[1] = day;
	    data[2] = ((int)days);
	    return data;
	}
	/**
	 * 计算怀孕了几周 、孕周计算
	 * @param date1 预产期
	 * @param nowDate 监测的日期
	 * @return 数组 [周，天，离预产期的天数]
	 * @throws ParseException
	 */
	public static int[] getPregnantWeek(Date date1,Date nowDate) throws ParseException {
		int[] data = new int[3];
		//Date nowDate = new Date();
		if(nowDate.after(date1)){
			return new int[] {39,6,280};
		}
		long days = getcomparedatedays(TimeUtils.convertTime(date1, "yyyy-MM-dd HH:mm:ss"), TimeUtils.convertTime(nowDate, "yyyy-MM-dd 00:00:00"));
		int week = (int)(days / 7L);
		int day = (int)((week + 1) * 7 - days);
		if(day == 7){
			day = 0;
			week-=1;
		}
		data[0] = (39 - week);
		data[1] = day;
		data[2] = ((int)days);
		return data;
	}
	
	/**
	 * 新版计算孕周方法   getPregnantWeek_415
	 * @param date
	 * @param nowDate
	 * @return
	 * [0]孕周 [1]孕天[3]距离预产期的天数
	 */
	public static int[] getPregnantWeek_415(Date date, Date nowDate)  {
		int[] data = new int[3];
		//预产期+21
		date = TimeUtils.getCurrentStartTime(21, date);
	    //传递过来的时间大于预产期
	    if(nowDate.after(date)){
	    	return new int[] {43, 0, 301};
	    }
	    long days = getDaySub(TimeUtils.converStringDate(nowDate, "yyyy-MM-dd"), TimeUtils.converStringDate(date, "yyyy-MM-dd"));
	    if (days > 301){
	    	return new int[] {0, 0, 0};
	    }
	    int week = (int)(days / 7L);
	    int day = (int)((week + 1) * 7 - days);
	    if(day == 7){
	    	day = 0;
	    	week -= 1;
	    }
	    data[0] = (42 - week);
	    data[1] = day;
	    //距预产期的天数，由于要显示43周数据所有该天数要减21天
	    data[2] = ((int)days - 21);
	    return data;
	}
	
	/**
	 * 新版计算孕周方法   getDaySub
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0L;
		if ((StringUtils.isEmpty(beginDateStr)) || (StringUtils.isEmpty(endDateStr))) {
			return day;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = format.parse(beginDateStr);
			Date endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / 86400000L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}
	/**
	 * 新版计算孕周方法  converStringDate
	 * @param date
	 * @param format
	 * @return
	 */
	public static String converStringDate(Date date, String format) {
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}

	/**
	 * 将字符按指定的格式转换成Timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp getTimestampDate(String date,String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		sformat.setLenient(false);
		try {
			Timestamp ts = new Timestamp(sformat.parse(date).getTime());
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getIntFromStringDate(String Date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(Date);
            return (int)(date.getTime() / 1000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 * 将字符按指定的格式转换成Timestamp
	 * @param date
	 * @return
	 */
	public static Date getString2Date(String date,String format) {
	    SimpleDateFormat sformat = new SimpleDateFormat(format);
	    sformat.setLenient(false);
	    try {
	        return sformat.parse(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * 计算时间差，用户验证码是否过期校验
	 * @param formatTime1
	 * @param formatTime2
	 * @param limit_time
	 * @return
	 */
	public static boolean checkLimitTime(Timestamp formatTime1, Timestamp formatTime2, int limit_time) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
		int hours = (int) ((t1 - t2) / 3600000);
		int minutes = (int) (((t1 - t2) / 1000 - hours * 3600) / 60);
		int second = (int) ((t1 - t2) / 1000 - hours * 3600 - minutes * 60);
		int sec = hours * 3600 + minutes * 60 + second;
		if (sec > limit_time){
			return true;
		}
		return false;
	}

	/**
	 * 根据秒获取多少小时分钟秒
	 * @param seconds 秒数
	 * @return
	 */
	public static String getTimeStrBySeconds(Integer seconds){
		if(seconds == null || seconds == 0){
			return String.valueOf("");
		}

		int hour = (int)Math.floor(seconds / 3600);
		int minute = (int)Math.floor((seconds % 3600) / 60);
		int second = seconds % 3600 % 60;
		return hour+"小时"+minute+"分"+second+"秒";
	}

	/**
	 * 当前时间加上多少秒以后的时间
	 * @param second 秒数
	 * @return
	 */
	public static String calculateTime(Timestamp date, Integer second){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(second == null){
			second = 0;
		}
		calendar.add(Calendar.SECOND, second);
		return convertTime(calendar.getTime());
	}

	/**
	 * 获取两个日期相隔的天数
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	 public static int daysBetween(Date smdate,Date bdate) throws ParseException
	    {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	    smdate=sdf.parse(sdf.format(smdate));
	    bdate=sdf.parse(sdf.format(bdate));
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(smdate);
	    long time1 = cal.getTimeInMillis();
	    cal.setTime(bdate);
	    long time2 = cal.getTimeInMillis();
	    long between_days=(time2-time1)/(1000*3600*24);

	   return Integer.parseInt(String.valueOf(between_days));
	}

	 public static String getInterval(String createtime, String timeStr) { // 传入的时间格式必须类似于2012-8-21
		// 17:53:20这样的格式
		String interval = null;

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ParsePosition pos = new ParsePosition(0);
		Date d1 = sd.parse(createtime, pos);

		// 用现在距离1970年的时间间隔new
		// Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
		long time = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒
		int hour = (int) (time / 3600000);
		if (time / 1000 < 10 && time / 1000 >= 0) {
			// 如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒
			interval = "刚刚";
		} else if (hour < 24 && hour > 0) {
			// 如果时间间隔小于24小时则显示多少小时前
			int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
			interval = h + "小时前";

		} else if (time / 60000 < 60 && time / 60000 > 0) {
			// 如果时间间隔小于60分钟则显示多少分钟前
			int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
			interval = m + "分钟前";

		} else if (time / 1000 < 60 && time / 1000 >= 0) {
			// 如果时间间隔小于60秒则显示多少秒前
			int se = (int) ((time % 60000) / 1000);
			interval = se + "秒前";

		} else {
			// 大于24小时，则显示正常的时间，但是不显示秒
			SimpleDateFormat sdf = new SimpleDateFormat(timeStr);
			ParsePosition pos2 = new ParsePosition(0);
			Date d2 = sdf.parse(createtime, pos2);
			interval = sdf.format(d2);
		}
		return interval;
	}

	/**
	 * 获取宝宝多少岁多少月
	 */
	private static final long ONE_DAY = 86400000L;
	public static int[] getBabyAgeAndMonth(Date birthday) {
		long delta = Math.abs(new Date().getTime() - birthday.getTime());
   	 	long years = 0;
   	 	long months = 0;
   	 	int[] ageAndMonth = new int[2];
	   	if (delta >= 365 * ONE_DAY) {
		    years = delta / (365 * ONE_DAY);
		}
	   	months = (delta/ONE_DAY - 365 * years)/30;
	   	ageAndMonth[0] = (int)years;
	   	ageAndMonth[1] = (int)months;
	   	return ageAndMonth;

	}

	public static String getInterval(String createtime) {
		return getInterval(createtime, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 *
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat1(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}

	/**
	 * 计算订单临时表中的记录是否过期，过期则删除(过期时间2小时)
	 * @param time
	 * @return
	 */
	public static boolean isExpire(Timestamp time){
		Calendar current = Calendar.getInstance();
		Calendar applyTime = Calendar.getInstance();
		current.setTime(getCurrentTime());
		applyTime.setTime(time);
		long result = current.getTimeInMillis() - applyTime.getTimeInMillis();
		long second = result / 1000 / 60;
		if(second >= 120){
			return true;
		}
		return false;
	}

	
	
	/** 计算一个时间是星期几 **/
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

	/**
	 * 获取当前月第一天是星期几
	 * @return
	 */
	public static Integer getWeekOfMonthFirstDay(String date){
		Date time = getMonthStartTime(date);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int week = c.get(Calendar.DAY_OF_WEEK);
		/** 计算出来的星期为，星期日：1，星期一：2.....星期六：7 **/
		if(week == 1){
			return 7;
		}else{
			return week - 1;
		}
	}

	/**
	 * 获取一个月的开始时间，2016-02-01
	 * @return
	 */
	public static Date getMonthStartTime(String date){
		Calendar calendar = Calendar.getInstance();
		if(StringUtils.isEmpty(date)){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(getTimestampDate(date, Consts.FORMAT_TIME_THREE));
		}
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
	}

	/**
	 * 获取一个月的结束时间，2016-02-29
	 * @return
	 */
	public static Date getMonthEndTime(String date){
		Calendar calendar = Calendar.getInstance();
		if(StringUtils.isEmpty(date)){
			calendar.setTime(new Date());
		}else{
			calendar.setTime(getTimestampDate(date, Consts.FORMAT_TIME_THREE));
		}
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTime();
	}

	/**
	 * 获取天
	 * @param date 当前时间
	 * @return
	 */
	public static Integer getDayByDate(String date){
		if(StringUtils.isEmpty(date)){
			return 0;
		}
		Timestamp time = getTimestampDate(date, "yyyy-MM-dd");
		String day = convertTime(time, "dd");
		return Integer.valueOf(day);
	}

	/**
	 * 获取天
	 * @param date 时间参数
	 * @return
	 */
	public static Integer getMonthByDate(Date date){
		if(date == null){
			return 0;
		}
		String day = convertTime(date, "MM");
		return Integer.valueOf(day);
	}
	/**
	 * 获取年
	 * @param date 时间参数
	 * @return
	 */
	public static Integer getYearByDate(String date){
		if(date == null){
			String year = convertTime(new Date(), "yyyy");
			return Integer.valueOf(year);
		}
		String year = convertTime(getTimestampDate(date, Consts.FORMAT_TIME_THREE), "yyyy");
		return Integer.valueOf(year);
	}

	/**
	 * 获取一个月的总天数
	 * @return
	 */
	public static int getMaxDayOfMonth(String date){
		Calendar aCalendar = Calendar.getInstance();
		if(StringUtils.isEmpty(date)){
			aCalendar.setTime(new Date());
		}else{
			aCalendar.setTime(getTimestampDate(date, Consts.FORMAT_TIME_THREE));
		}
		int day = aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	public static Calendar addUnitOfNumOnInputDate(int startDate,int unitType,int day,int month,int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(startDate*1000L));
        if(unitType==1) {
            calendar.add(Calendar.DAY_OF_YEAR, day);
        } else if(unitType==2) {
            calendar.add(Calendar.MONTH, month);
        } else if(unitType==3) {
            calendar.add(Calendar.YEAR, year);
        }
        return calendar;
    }

	/**
	 * 咨询列表是否已过期
	 * @param time
	 * @return
	 */
	public static String isExpired(Date time){
		//Timestamp tp = Timestamp.valueOf(time); 
		Calendar current = Calendar.getInstance();
		Calendar applyTime = Calendar.getInstance();
		current.setTime(getCurrentTime());
		applyTime.setTime(time);
		long result = current.getTimeInMillis() - applyTime.getTimeInMillis();
		long hour = result / 1000 / 60 / 60;
		if(hour >= 24 && hour <48){
			return "退款";
		}if(hour >= 24 && hour >=48){
			return "过期";
		}
		return "true";
	}
	
	public static void main(String[] args) throws ParseException {
		/*Integer day = getMaxDayOfMonth("2016-03-21");
		System.out.println(day);
		SimpleDateFormat m_format = new SimpleDateFormat("yyyyMMddHHmmsssss", new Locale("zh", "cn"));
		String s= m_format.format(new Date());
		System.out.println(s);
		System.out.println(System.currentTimeMillis());*/
		getCurrentEndTime(-1,new Date());
		System.out.println(convertTime(getCurrentEndTime(-1,new Date()),"yyyyMMdd"));
		System.out.println(getCurrentTimeStr("yyyyMMdd"));
		System.out.println(getCurrentTime());
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
		String s= "2017-02-07 09:53:45.344"; 
		Date date =  formatter.parse(s);
	}
	
	
	public static String dateFormatStr(){
		SimpleDateFormat m_format = new SimpleDateFormat("yyyyMMddHHmmsssss", new Locale("zh", "cn"));
		return m_format.format(new Date());
	}
	
	/***
	 * 返回给定时间的毫秒数
	 * 
	 * @param date
	 * @param strFormat
	 * @return
	 */
	public static Long parseDate(String date, String strFormat)
	{
		if (date == null)
			return null;

		if (strFormat == null)
			strFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try
		{

			sdf.parse(date);
			newDate = sdf.parse(date);
		}
		catch (ParseException pe)
		{
			newDate = null;
		}
		return newDate.getTime() / 1000;
	}
	
	/**
	 * 根据末次月经时间计算预产期
	 * @param lastPeriod 末次月经时间
	 * @return 
	 */
	public static Date getPregancyDay(Date d1){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar Calendarborn = Calendar.getInstance();
			Calendarborn.setTime(d1);
			
			Calendarborn.add(Calendar.DAY_OF_MONTH, 280);
			int bornyear = Calendarborn.get(Calendar.YEAR);
			int bornmonth = 1 + Calendarborn.get(Calendar.MONTH);
			int bornday = Calendarborn.get(Calendar.DAY_OF_MONTH);
			
			String borndayString = (String.valueOf(bornyear) + "-" + String.valueOf(bornmonth) + "-" + String.valueOf(bornday));
			return sdf.parse(borndayString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 格式化当前时间
	 * @param format 格式化规则
	 * @return String 格式化以后的Time字符串
	 */
	public static String getCurrentTimeStr(String format){
		if(StringUtils.isEmpty(format)){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	public static Timestamp getCurrentTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		Timestamp time = new Timestamp(new java.util.Date().getTime());
		return time;
	}
	
	//两个时间之间的小时数
	public static Long getHourDifferent(Timestamp newtime ,Timestamp oldtime) {
		Long newTime=newtime.getTime();
		Long oldTime=oldtime.getTime();
		Long diff=newTime-oldTime;
		Long diffHours=(diff/1000/60/60);
		return diffHours;

	}
	
}
