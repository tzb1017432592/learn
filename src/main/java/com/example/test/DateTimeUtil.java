package com.example.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhangyupeng
 * @date: 2019-11-08 14:04
 * @description: tools for time or date
 **/
public class DateTimeUtil {

  private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

  public static final long WEEK_TO_MILLI = 7*24*60*60*1000;
  public static final long DAY_TO_MILLI = 24*60*60*1000;
  public static final long HOUR_TO_MILLI = 60*60*1000;
  public static final long MINUTE_TO_MILLI = 60*1000;
  public static final long SECOND_TO_MILLI = 1000;


  public static final long WEEK_TO_SECOND = 7*24*60*60;
  public static final long DAY_TO_SECOND = 24*60*60;
  public static final long HOUR_TO_SECOND = 60*60;
  public static final long MINUTE_TO_SECOND = 60;

  private static final ThreadLocal<Map<DateFormat, SimpleDateFormat>> DATE_FORMAT_MAP = new ThreadLocal<Map<DateFormat, SimpleDateFormat>>() {
    @Override
    protected Map<DateFormat, SimpleDateFormat> initialValue() {
      return new HashMap<>(40);
    }
  };

  private static SimpleDateFormat parseFormat(DateFormat format) {
    Map<DateFormat, SimpleDateFormat> map = DATE_FORMAT_MAP.get();
    SimpleDateFormat sdf = map.get(format);
    if (sdf == null) {
      sdf = new SimpleDateFormat(format.getFormatStr());
      map.put(format, sdf);
    }
    return sdf;
  }

  public static Long getTimeInMillis() {
    return System.currentTimeMillis();
  }

  public static Long getTimeInMillis(int field, int interval) {
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(System.currentTimeMillis());
    ca.add(field, interval);
    return ca.getTimeInMillis();
  }

  public static Long getTimeInMillis(Long time, int field, int interval) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    ca.add(field, interval);
    return ca.getTimeInMillis();
  }

  public static long getRecentDayStartInMillis(int interval){
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(System.currentTimeMillis());
    ca.add(Calendar.DAY_OF_YEAR, -interval);
    ca.set(Calendar.HOUR_OF_DAY, 0);
    ca.set(Calendar.MINUTE, 0);
    ca.set(Calendar.SECOND, 0);
    ca.set(Calendar.MILLISECOND, 0);
    return ca.getTimeInMillis();
  }


  public static List<Long> genTimestampListPerHour(Long start, Long end) {
    if (start < 9999999999L) {
      start = start * 1000;
    }
    if (end < 9999999999L) {
      end = end * 1000;
    }
    if (start > end) {
      Long tmp = start;
      start = end;
      end = tmp;
    }
    start = DateTimeUtil.getHourInMillis(start);
    end = DateTimeUtil.getHourInMillis(end);
    Long interval = end - start;
    Long hours = new Double(Math.ceil(interval.doubleValue() / (3600 * 1000))).longValue();
    List<Long> timestampList = new ArrayList<>();
    for (int i = 1; i <= hours; i++) {
      timestampList.add(DateTimeUtil.getTimeInMillis(start, Calendar.HOUR, i));
    }
    return timestampList;
  }

  /**
  * 生成过去${pastHours}小时的小时时间列表，[08:00, 09:00, 10:00, 11:00, 12:00]
  * <br/>
  * @param pastHours	
  * @return java.util.List<java.lang.String>
  * @author zhangyupeng
  * @date 2020-03-06 12:33
  */
  public static List<String> genHourListOfPast(Integer pastHours){
    Calendar ca = Calendar.getInstance();
    ca.set(Calendar.MINUTE,0);
    ca.set(Calendar.SECOND,0);
    ca.set(Calendar.MILLISECOND, 0);
    List<String> hourList = new ArrayList<>();
    SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
    for(int i=0;i<pastHours;i++){
      Long time = ca.getTimeInMillis();
      String hour = formater.format(time);
      hourList.add(hour);
      ca.add(Calendar.HOUR, -1);
    }
    Collections.reverse(hourList);
    return hourList;
  }

  public static int getYear(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.YEAR);
  }

  public static int getMonth(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.MONTH) + 1;
  }

  public static int getDay(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.DATE);
  }

  public static int getHour(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.HOUR_OF_DAY);
  }

  public static int getMinute(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.MINUTE);
  }

  public static int getSecond(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    return ca.get(Calendar.SECOND);
  }

  public static Long getDayInMillis(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    ca.set(Calendar.HOUR_OF_DAY, 0);
    ca.set(Calendar.MINUTE, 0);
    ca.set(Calendar.SECOND, 0);
    ca.set(Calendar.MILLISECOND, 0);
    return ca.getTimeInMillis();
  }

  public static Long getHourInMillis(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    Calendar ca = Calendar.getInstance();
    ca.setTimeInMillis(time);
    ca.set(Calendar.MINUTE, 0);
    ca.set(Calendar.SECOND, 0);
    ca.set(Calendar.MILLISECOND, 0);
    return ca.getTimeInMillis();
  }


  /**
   * format timestamp: yyyy-MM-dd HH:mm:ss
   *
   * @param time
   * @return formated timestamp
   */
  public static String formatTimeInMillis(Long time) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    return parseFormat(DateFormat.YYYY_MM_DD_HH_MM_SS).format(time);
  }
  /**
   * format timestamp: 格式为传来的枚举参数
   *
   * @param time
   * @return formated timestamp
   */
  public static String formatTimeInMillis(Long time,DateFormat dateFormat) {
    if (time < 9999999999L) {
      time = time * 1000;
    }
    return parseFormat(dateFormat).format(time);
  }

  /**
   * parse formated time: yyyy-MM-dd HH:mm:ss
   *
   * @param formatTime
   * @return timestamp of length 13
   */
  public static Long parseFormatedTime(String formatTime) {
    long time = 0l;
    if (formatTime == null || "".equals(formatTime)) {
      return time;
    }
    try {
      time = parseFormat(DateFormat.YYYY_MM_DD_HH_MM_SS).parse(formatTime).getTime();
    } catch (ParseException e) {
      return time;
    }
    return time;
  }

  /**
  * get today date
  * <br/>
  * @param
  * @return java.lang.String yyyyMMdd
  * @author zhangyupeng
  * @date 2020-05-24 18:46
  */
  public static String getTodayDate(){
    return parseFormat(DateFormat.YYYYMMDD).format(System.currentTimeMillis());
  }

  public static String getNowTime(){
    return parseFormat(DateFormat.YYYYMMDDHHMMSS).format(System.currentTimeMillis());
  }
  /**
   * parse formated time: yyyyMMdd
   *
   * @param formatTime
   * @return timestamp of length 13
   */
  public static Long parseDate1Format(String formatTime) {
    long time = 0l;
    if (formatTime == null || "".equals(formatTime)) {
      return time;
    }
    try {
      time = parseFormat(DateFormat.YYYYMMDD).parse(formatTime).getTime();
    } catch (ParseException e) {
      return time;
    }
    return time;
  }


  public static long getRecentlyMonth(int month){
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-month);
    return cal.getTimeInMillis();
  }

  /**
   * change time unit of millis to minutes
   *
   * @param time
   * @return time in minutes
   */
  public static Double ToMinute(Long time) {
    if (time >= 0) {
      return time / (60.0 * 1000);
    } else {
      return 0.0;
    }
  }

  /**
   * new date
   * @return
   */
  public static Date getDate(){
    return new Date();
  }


  /**
  * 最近的日期列表
  * <br/>
  * @param recentDays
* @param containToday
  * @return java.util.List<java.lang.String>
  * @author zhangyupeng
  * @date 2020-06-30 14:20
  */
  public static List<String> getRecentDateList(int recentDays, boolean containToday, DateFormat dateFormat){
    Calendar ca = Calendar.getInstance();
    if(!containToday){
      ca.add(Calendar.DAY_OF_YEAR,-1);
    }
    List<String> recentDateList = new ArrayList<>(recentDays);
    for (int i = 0; i < recentDays; i++) {
      long timeInMillis = ca.getTimeInMillis();
      String date = parseFormat(dateFormat).format(timeInMillis);
      recentDateList.add(date);
      ca.add(Calendar.DAY_OF_YEAR, -1);
    }
    Collections.reverse(recentDateList);
    return recentDateList;
  }

  /**
   * @description: 创建出一个键为最近日期，Long类型初始值的map
   * @author: lixiang
   * @date: 2021/9/13 11:00
   * @param recentDays: 最近多少天
   * @param containToday: 是否包含今天
   * @param dateFormat: 日期格式
   * @param initValue: 初始值
   * @return: java.util.Map<java.lang.String,java.lang.Long>
   */
  public static Map<String,Number> getRecentDateMap(int recentDays, boolean containToday, DateFormat dateFormat,int initValue){
    List<String> recentDateList = getRecentDateList(recentDays, containToday, dateFormat);
    LinkedHashMap<String, Number> recentDateMap = new LinkedHashMap<>();
    for (String dateStr : recentDateList) {
      recentDateMap.put(dateStr,initValue);
    }
    //recentDateList.stream().collect(Collectors.toMap(String::toString, s -> initValue));
    return recentDateMap;
  }


  public enum DateFormat
  {
    /**
     * 日期格式
     */
    YYYY_MM("yyyy-MM", false),
    YYYY_MM_DD("yyyy-MM-dd", false),
    DD("dd", false),
    YYYYMMDD("yyyyMMdd", false),
    YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm", false),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", false),
    YYYY_MM_EN("yyyy/MM", false),
    YYYY_MM_DD_EN("yyyy/MM/dd", false),
    YYYY_MM_DD_HH_MM_EN("yyyy/MM/dd HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_EN("yyyy/MM/dd HH:mm:ss", false),

    YYYYMMDDHHMMSS("yyyyMMddHHmmss",false),

    YYYY_MM_CN("yyyy年MM月", false),
    YYYY_MM_DD_CN("yyyy年MM月dd日", false),
    YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm", false),
    YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss", false),

    HH_MM("HH:mm", true),
    HH_MM_SS("HH:mm:ss", true),

    MM_DD("MM-dd", true),
    MM_DD_HH_MM("MM-dd HH:mm", true),
    MM_DD_HH_MM_SS("MM-dd HH:mm:ss", true),

    MM_DD_EN("MM/dd", true),
    MM_DD_HH_MM_EN("MM/dd HH:mm", true),
    MM_DD_HH_MM_SS_EN("MM/dd HH:mm:ss", true),

    MM_DD_CN("MM月dd日", true),
    MM_DD_HH_MM_CN("MM月dd日 HH:mm", true),
    MM_DD_HH_MM_SS_CN("MM月dd日 HH:mm:ss", true),

    E_MMM_DD_KK_MM_SS_Z_Y("E MMM dd HH:mm:ss Z yyyy", false);



    private String formatStr;
    private boolean isShowOnly;

    DateFormat(String formatStr, boolean isShowOnly){
      this.formatStr = formatStr;
      this.isShowOnly = isShowOnly;
    }

    public String getFormatStr(){
      return formatStr;
    }

    public boolean isShowOnly() {
      return isShowOnly;
    }
  }




}






