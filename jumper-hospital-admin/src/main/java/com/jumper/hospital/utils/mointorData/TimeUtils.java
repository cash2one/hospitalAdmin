package com.jumper.hospital.utils.mointorData;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeUtils {

	public static Timestamp getCurrentTime() {
		Timestamp time = new Timestamp(new java.util.Date().getTime());
		return time;
	}
 
	public static Timestamp getCurrentTime(String date,String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);// ���Է�����޸����ڸ��?
		Timestamp time = null;
		try {
			//System.out.println((dateFormat.parse(date)).getTime());
			time = new Timestamp((dateFormat.parse(date)).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static Timestamp getTimestampDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		format.setLenient(false);
		try {
			Timestamp ts = new Timestamp(format.parse(date).getTime());
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Timestamp getTimestampDate(String date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		format.setLenient(false);
		try {
			Timestamp ts = new Timestamp(format.parse(date).getTime());
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date convertToDate(String data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// ���Է�����޸����ڸ��?
		Date ret;
		try {
			ret = dateFormat.parse(data);
			return ret;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertDate(String data, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date langDate;
		try {
			langDate = sdf.parse(data);
			return langDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatStringDate(String date, String format) {
		String time = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			time = sdf.format(sdf.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String converStringDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ʽ��ʱ�� Locale�������������в���
	 * 
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat(Timestamp formatTime,String format) {
		SimpleDateFormat m_format = new SimpleDateFormat(format,
				new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}
	public static String getTimeStampNumberFormat(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}
	public static String getDateFormat(Date date) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd",
				new Locale("zh", "cn"));
		return m_format.format(date);
	}

	public static boolean checkLimitTime(Timestamp formatTime1,
			Timestamp formatTime2, int limit_time) {
		SimpleDateFormat timeformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1))
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2))
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// ��Ϊt1-t2�õ����Ǻ��뼶,����Ҫ��3600000�ó�Сʱ.���������ͬ��?
		int hours = (int) ((t1 - t2) / 3600000);
		int minutes = (int) (((t1 - t2) / 1000 - hours * 3600) / 60);
		int second = (int) ((t1 - t2) / 1000 - hours * 3600 - minutes * 60);
		int sec = hours * 3600 + minutes * 60 + second;
		if (sec > 120)
			return true;
		return false;
	}

	/**
	 * ��ȡ��ǰ���������ڼ�<br>
	 * 
	 * @param dt
	 * @return ��ǰ���������ڼ�
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "������", "����һ", "���ڶ�", "������", "������", "������", "������" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * ��ȡһ�����ڵĵڼ���
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekDays(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return w;
	}

	public static String getInterval(String createtime, String timeStr) { // �����ʱ���ʽ����������2012-8-21
		// 17:53:20����ĸ��?
		String interval = null;

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ParsePosition pos = new ParsePosition(0);
		Date d1 = sd.parse(createtime, pos);

		// �����ھ���1970���ʱ����new
		// Date().getTime()��ȥ��ǰ��ʱ�����?970���ʱ����d1.getTime()�ó��ľ�����ǰ��ʱ��������ʱ���ʱ����?
		long time = new Date().getTime() - d1.getTime();// �ó���ʱ�����Ǻ���
		int hour = (int) (time / 3600000);
		if (time / 1000 < 10 && time / 1000 >= 0) {
			// ���ʱ����С��?0������ʾ���ոա�time/10�ó���ʱ�����ĵ�λ����
			interval = "�ո�";
		} else if (hour < 24 && hour > 0) {
			// ���ʱ����С��?4Сʱ����ʾ����Сʱǰ
			int h = (int) (time / 3600000);// �ó���ʱ�����ĵ�λ��Сʱ
			interval = h + "Сʱǰ";

		} else if (time / 60000 < 60 && time / 60000 > 0) {
			// ���ʱ����С��?0��������ʾ���ٷ���ǰ
			int m = (int) ((time % 3600000) / 60000);// �ó���ʱ�����ĵ�λ�Ƿ���
			interval = m + "����ǰ";

		} else if (time / 1000 < 60 && time / 1000 >= 0) {
			// ���ʱ����С��?0������ʾ������ǰ
			int se = (int) ((time % 60000) / 1000);
			interval = se + "��ǰ";

		} else {
			// ����24Сʱ������ʾ���ʱ�䣬���ǲ���ʾ��?
			SimpleDateFormat sdf = new SimpleDateFormat(timeStr);
			ParsePosition pos2 = new ParsePosition(0);
			Date d2 = sdf.parse(createtime, pos2);
			interval = sdf.format(d2);
		}
		return interval;
	}

	public static String getInterval(String createtime) {
		return getInterval(createtime, "yyyy-MM-dd HH:mm");
	}

	/**
	 * ��ȡ���쿪ʼʱ�� ex:2015-03-03 00:00:00
	 * 
	 * @param day
	 *  ��ǰʱ����������
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
	 * ��ȡ��ǰ����ʱ�� ex:2015-03-03 23:59:59
	 * 
	 * @param day
	 *            ��ǰʱ����������
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
	 * 
	 * @param dateStr
	 *            Ĭ�� yyyy/MM/dd HH:mm
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
	 * 
	 * @param dateStr
	 * @param flag
	 *            0:"yyyy-MM-dd HH:mm:ss";1:"yyyy-MM-dd";2:"yyyy/MM/dd"
	 * @return
	 */
	public static boolean isValidDate(String dateStr, int flag) {
		if (flag == 0) {
			return isValidDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		} else if (flag == 1) {
			return isValidDate(dateStr, "yyyy-MM-dd");
		} else if (flag == 2) {
			return isValidDate(dateStr, "yyyy/MM/dd");
		}
		return false;

	}

	public static String genericFormatDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * �õ�ĳ��ĳ�µĵ�һ��
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {

		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 00);

		return cal.getTime();
	}

	/**
	 * �õ�ĳ��ĳ�µ����һ��?
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {

		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);

		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);

		return cal.getTime();

	}
	
	public static Timestamp getBefourOrAfterDay(int day,String format){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);
		String strDate = converStringDate(c.getTime(), format);
		Timestamp time = getTimestampDate(strDate, format);
		return time;
	}
	public static String getBefourOrAfter(int day,String format){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, day);
		String strDate = converStringDate(c.getTime(), format);
		return strDate;
	}
	/**获取指定日期之前和之后多少天的 日期*/
	public static String getBefourOrAfter(int day,Date date,String format){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		String strDate = converStringDate(c.getTime(), format);
		return strDate;
	}
	/**分钟*/
	public static Timestamp getBefourOrAfterMins(int min){
		Calendar c = Calendar.getInstance();
	//	int day = 0;
		c.add(Calendar.MINUTE, min);
		String strDate = converStringDate(c.getTime(), "yyyy-MM-dd HH:mm:ss");
		Timestamp time = getTimestampDate(strDate, "yyyy-MM-dd HH:mm:ss");
		return time;
	}
	
	public static String getStringDate(String beginDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String sd = sdf.format(new Date(Long.parseLong(beginDate)));
		return sd;
	}
	
	public static void main(String[] args) {
		/*Date date = getCurrentEndTime(1, convertDate("2014-04-29", "yyyy-MM-dd"));
		System.out.println(converStringDate(date, "yyyy-MM-dd"));*/
		//System.out.println(converStringDate(getCurrentEndTime(-1, null), "yyyy-MM-dd HH:mm:ss"));
		System.out.println(getBefourOrAfterMins(-60));
		String times=TimeUtils.converStringDate(TimeUtils.getBefourOrAfterMins(-60), "yyyyMMddHH");
		System.out.println(times);
	//	System.out.println(getStringDate("1435547139895"));
		//System.out.println(getStringDate("1435547139895"));
		int i = new Date().compareTo(calculateTime(new Date(), -1));
		System.out.println(i);
	}

	/**
	 * 获取当前时间推后多少天
	 * @param day
	 * @param date
	 * @return
	 */
	public static Date getCurrentTimeByDay(int day, Date date) {
		Calendar todayStart = Calendar.getInstance();
		if (date != null) {
			todayStart.setTime(date);
		}
		todayStart.add(Calendar.DATE, day);
		return todayStart.getTime();
	}
	
	public static Date calculateTime(Date date, Integer day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}
	
	public static Timestamp getSpecifiedBeforeOrAfterDay(int day,String date,String format){
		Calendar c = Calendar.getInstance();
		Date newDate = convertDate(date, format);
		c.setTime(newDate);
		c.add(Calendar.DATE, day);
		String strDate = converStringDate(c.getTime(), format);
		Timestamp time = getTimestampDate(strDate, format);
		return time;
	}
	
	/**
	 * 计算孕周和孕天
	 * @param addTime 数据记录的时间
	 * @param expectedDate 预产期
	 * @return 存储孕周与孕天的整数数组
	 */
	public static int[] getPregnantWeekAndDay(Date addTime,Date expectedDate){
		//如果传入的参数为空 则返回空数组
		if(null == addTime || null == expectedDate){
			return new int[2];
		}
		//计算怀孕的日子
		long pregnant = getCurrentStartTime(-280, expectedDate).getTime();
		//计算目前怀孕的天数
		long interval = (addTime.getTime() - pregnant)/(3600*24*1000);
		//计算孕周
		int week = (int) (interval/7);
		//计算孕天
		int day = (int) (interval%7);
		int[] intArra = new int[2];
		intArra[0] = week;
		intArra[1] = day;
		return intArra;
	}
	/**
	 * 获取2个时间之间相隔的天数
	 * @param start 开始的时间
	 * @param end   结束的时间
	 * @return
	 */
	public static int getSeparatedDays(Date start,Date end){
		long times = end.getTime() - start.getTime();
		int days = (int) (times/(3600*24*1000)) + 1;
		return days;
	}
	
	 /**
     * 把毫秒转化成日期
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    public static String transferLongToDate(Long millSec,String dateFormat){
	     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	     Date date= new Date(millSec);
	     return sdf.format(date);
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
	 * 获取当前时间的毫秒数
	 * @return
	 */
	public static long getMillisecond(Timestamp formatTime){
		  //此处转换为毫秒数
		long millionSeconds = 0;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
			  String str = dateFormat.format(formatTime);
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
			  millionSeconds = sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  return millionSeconds;
	} 
	
	/**
	 * 判断当前时间属于那个时间段
	 * 上午 的定义为：5:00~12:00 
	 * 下午 的定义为：12:00~19:00
	 * 晚上 的定义为：19:00~5:00
	 * @param formatTime
	 */
	public static int  judgeTime(Timestamp formatTime){
		   int flag = 0; ///1上午，2 下午， 3，晚上
		  	SimpleDateFormat sdf=new SimpleDateFormat("HH,mm,ss");
		    String dateValue=sdf.format(formatTime.getTime());
		    String[] arr = dateValue.split(","); //取得当前的小时
		    int hour = Integer.valueOf(arr[0]);
		    if(hour >5 && hour <=12){
		    	flag =1;
		    }else if(hour >12 && hour <=19  ){
		    	flag=2;
		    }else if(hour >19){
		    	flag=3;
		    }
		   return flag; 
	}
	
	
}
