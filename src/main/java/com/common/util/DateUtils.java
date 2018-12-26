package com.common.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**     
 * 类名称：DateUtils    
 * 类描述：   日期工具类
 */
public class DateUtils
{
    /**
     * 获取现在时间
     * 
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = formatter.parse(dateString, new ParsePosition(0));
        return currentTime_2;
    }

    /**    
     * main(这里用一句话描述这个方法的作用)    
     * TODO(这里描述这个方法适用条件 – 可选)     
     * TODO(这里描述这个方法的注意事项 – 可选)       
     * @param @param args     
     * @return void
     * @Exception 异常对象
    */
    public static void main(String[] args)
    {
        System.out.println(getStringDateShort());
    }
    /**
     * 
     * @return yyyy-MM-dd 获取现在时间
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     * 
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate()
    {
        setTimeZone();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd HH:MM
     */
    public static String getStringDateShortmm()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     * 
     * @return
     */
    public static String getTimeShort()
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    /**
     * 将长时间格式时间转换为字符串 MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrMonth(java.util.Date dateDate)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    
    /**
     * 将长时间格式时间转换为字符串 MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrYear(java.util.Date dateDate)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(java.util.Date dateDate)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    public static String dateToStr(java.util.Date dateDate, String fomart){
    	setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat(fomart);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate)
    {
        setTimeZone();
        SimpleDateFormat formatter;
        if (strDate == null || "".equals(strDate))
            return null;
        if (strDate.indexOf("-") > 0)
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        else
            formatter = new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     * 
     * @return
     */
    public static Date getNow()
    {
        setTimeZone();
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     * 
     * @param day
     * @return
     */
    public static Date getLastDate(long day)
    {
        setTimeZone();
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     * 
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在时间
     * 
     * @return 字符串 yyyyMMdd HH:mm:ss
     */
    public static String getStringTodayA()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 得到今天的时间的短日期形如：110425
     * @return
     */
    public static String getShortStringToday()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMM");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
       /**
     * 得到今天的时间的短日期形如：110425
     * @return
     */
    public static String getNowDateStr()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 得到今天的时间的短日期形如：20110425
     * @return
     */
    public static String getNowDateStr2()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * 得到现在时间
     * 
     * @return String 字符串 yyyyMMddHHmmss毫秒
     */
    public static String getStringTodayB()
    {
        return getStringTodayA() + Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    /**
     * 得到现在小时
     */
    public static String getHour()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     * 
     * @return
     */
    public static String getTime()
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     * 
     * @param sformat
     *            yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat)
    {
        setTimeZone();
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟 st1 要大于 st2才能返回正确值
     */
    public static String getTwoHour(String st1, String st2)
    {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else
        {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
                    / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
                    / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数 sj1>sj2 返回正数
     */
    public static String getTwoDay(String sj1, String sj2)
    {
        setTimeZone();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try
        {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e)
        {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj)
    {
        setTimeZone();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try
        {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e)
        {
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数 负数是往前推，正数是往后延
     */
    public static String getNextDay(String nowdate, String delay)
    {
        setTimeZone();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = "";
        Date d = strToDate(nowdate);
        long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60
                * 60;
        d.setTime(myTime * 1000);
        mdate = format.format(d);
        return mdate;
    }

    /**
     * 判断是否润年
     * 
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate)
    {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0)
        {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     * 
     * @param str
     * @return
     */
    public static String getEDate(String str)
    {
        setTimeZone();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天
     * 
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat)
    {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
                || mon == 10 || mon == 12)
        {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11)
        {
            str += "30";
        } else
        {
            if (isLeapYear(dat))
            {
                str += "29";
            } else
            {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 取得指定月份的第一天
     * 
     * @param strdate
     *            String
     * @return String
     */
    public static String getMonthBegin(String dat)
    {
        String str = dat.substring(0, 8);
        return str + "01";
    }

    /**
     * 判断二个时间是否在同一个周
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear)
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH))
        {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH))
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     * 
     * @return
     */
    public static String getSeqWeek()
    {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        // String year = Integer.toString(c.get(Calendar.YEAR));
        return week;
    }

    /**
     * 根据传入的一个日期，判断这个日期所在的周是年度的第几周
     * @param day
     * @return
     */
    public static String getSeqWeek(String day)
    {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(strToDate(day));
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        // String year = Integer.toString(c.get(Calendar.YEAR));
        return week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num)
    {
        // 再转换为时间
        Date dd = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     * 
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate)
    {
        // 再转换为时间
        Date date = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 两个时间之间的天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2)
    {
        setTimeZone();
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        try
        {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e)
        {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate)
    {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";
        // 得到这个月的1号是星期几
        Date date = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = DateUtils.getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    public static String[][] getCal(String sdate)
    {
        String smon = sdate.substring(5, 7);// 月份
        // 月份第一天
        String firstday = sdate.substring(0, 8) + "01";
        // 月份第一天对应所在周的第一天,周日日期
        String sone = getNowMonth(sdate);

        String endday = DateUtils.getEndDateOfMonth(sdate);
        // 找出本月跨几个周,
        String sweek = DateUtils.getSeqWeek(firstday);
        String eweek = DateUtils.getSeqWeek(endday);
        int kkkk = 0;
        if (Integer.parseInt(eweek) < Integer.parseInt(sweek))
        {
            // 表示是跨年度,这时需要用年度总周次来减前一周,再加1
            endday = DateUtils.getNextDay(endday, "-7"); // 往前推一个周
            eweek = DateUtils.getSeqWeek(endday);
            kkkk = 1;
        }
        if (kkkk == 1)
        {
            kkkk = Integer.parseInt(eweek) - Integer.parseInt(sweek) + 2;
        } else
            kkkk = Integer.parseInt(eweek) - Integer.parseInt(sweek) + 1;
        String[][] sp = new String[kkkk][7];
        // 第一层循环,跨周次
        for (int i = 1; i <= kkkk; i++)
        {
            // String tmp="";
            for (int k = 0; k < 7; k++)
            {
                // 循环当前日期
                String sc = getNextDay(sone, ((i - 1) * 7 + k) + "");
                if (smon.equals(sc.substring(5, 7)))
                {
                    sp[i - 1][k] = sc.substring(8, 10);
                } else
                {
                    sp[i - 1][k] = "&nbsp;";
                }
            }

        }
        return sp;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     * 
     * 表示是取几位随机数，可以自己定
     */

    public static String getNo(int k)
    {
        return getUserDate("yyyyMMddHHmmss") + getRandom(k);
    }

    /**
     * 返回一个随机数
     * 
     * @param i
     * @return
     */
    public static String getRandom(int i)
    {
        Random jjj = new Random();

        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++)
        {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 求下个月的10号
     */
    public static String getNextMonthDay(String sdate, int m)
    {
        int year = Integer.parseInt(sdate.substring(0, 4));
        int month = Integer.parseInt(sdate.substring(5, 7));
        month = month + m;
        if (month < 0)
        {
            month = month + 12;
            year = year - 1;
        } else if (month > 12)
        {
            month = month - 12;
            year = year + 1;
        } else if (month == 0)
        {
            year = year - 1;
            month = 12;
        }
        String smonth = "";
        if (month < 10)
            smonth = "0" + month;
        else
            smonth = "" + month;
        return year + "-" + smonth + "-10";
    }

    /**
     * 将一个整数转换为一个类型型(主要是长度)的字符串,不足位在前面补0
     * @param k    要转换的整数
     * @param le   要转换的格式
     * @return
     */
    public static String getNumToStr(int k, String le)
    {
        String kk = k + "";
        String ks = k + "";
        if (kk.length() < le.length())
        {
            for (int i = 0; i < (le.length() - ks.length()); i++)
            {
                kk = "0" + kk;
            }
        }
        return kk;
    }
    
    public static void setTimeZone()
    {
        /*if ("WINDOW".equals(Constants.OPTYPE))
        {
            TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
            TimeZone.setDefault(tz);
        }*/
    }

    /**
     * 取得几个月后的同一天，可以自动换年
     * @param strDate   开始时间
     * @param months    间隔的月数
     * @return 处理后的日期,输出格式yyyy-MM-dd
     */
    public static String getDateForMonthsLater(String strDate, int months)
    {
        setTimeZone();
        Calendar c = Calendar.getInstance();
        c.setTime(DateUtils.strToDate(strDate));
        c.add(Calendar.MONTH, months);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(c.getTime());
        return dateString;
    }

    // 获取语言时区
    public static Locale getLocal(String local)
    {
        Locale slocal = null;
        if (local == null || "".equals(local))
            local = "ZH_TW";
        if (local.indexOf("_") > 0)
        {
            slocal = new Locale(local.substring(0, 2), local.substring(3, 5));
        } else
        {
            slocal = new Locale(local);
        }
        return slocal;
    }

    
    /**
     * 当前时间的后几天
     * getDate(这里用一句话描述这个方法的作用)    
     * (这里描述这个方法适用条件 – 可选)     
     * (这里描述这个方法的注意事项 – 可选)       
     * @param @param dayNon
     * @param @param format
     * @param @return     
     * @return String
     * @Exception 异常对象
     */
    public static String getDate(int dayType,int dayNon,String format)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(dayType, dayNon);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(cal.getTime());
    }
    
    /**
     * 在指点时间上加上几天后的日期字符串
     * @param addBefore 指定日期
     * @param dateFormat 默认格式
     * @param addNumber 添加的天数
     * @return
     */
    public static String addDate(String addBefore,String dateFormat,int addNumber)
    {
        Date date = DateUtils.strToDateLong(addBefore);
        if(date == null){
            date = DateUtils.strToDate(addBefore);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, addNumber);
        if(StringUtils.isEmpty(dateFormat))
        {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    
    /**
     * 在指点时间上加上几天后的日期字符串
     * @param addBefore 指定日期
     * @param dateFormat 默认格式
     * @param addNumber 添加的天数
     * @return
     */
    public static String addHour(String addBefore,String dateFormat,int addNumber)
    {
        Date date = DateUtils.strToDateLong(addBefore);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, addNumber);
        if(StringUtils.isEmpty(dateFormat))
        {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    
    /**
     * 在指点时间上加上几天后的日期字符串
     * @param addBefore 指定日期
     * @param dateFormat 默认格式
     * @param addNumber 添加的天数
     * @return
     */
    public static Date addYear(String addBefore,int addNumber)
    {
        Date date = DateUtils.strToDateLong(addBefore);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, addNumber);
        return cal.getTime();
    }
    
    /**
     * 在指点时间上加上几月后的日期字符串
     * @param addBefore 指定日期
     * @param dateFormat 默认格式
     * @param addNumber 添加的天数
     * @return
     */
    public static Date addMonth(String addBefore,int addNumber)
    {
        Date date = DateUtils.strToDateLong(addBefore);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, addNumber);
        return cal.getTime();
    }
    
    public static String addDate2(String addBefore,String dateFormat,int addNumber,int dateType)
    {
        Date date = DateUtils.strToDate(addBefore);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (dateType)
        {
        case Calendar.DATE:
            cal.add(Calendar.DATE, addNumber);
            break;
        case Calendar.MONTH:
            cal.add(Calendar.MONTH, addNumber);
            break;
        default:
            break;
        }
        if(StringUtils.isEmpty(dateFormat))
        {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    
    public static String addDateBaseNow(String dateFormat,int addNumber,int dateType)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        switch (dateType)
        {
        case Calendar.SECOND:
            cal.add(Calendar.SECOND, addNumber);
            break;
        case Calendar.MINUTE:
            cal.add(Calendar.MINUTE, addNumber);
            break;
        case  Calendar.HOUR:
            cal.add( Calendar.HOUR, addNumber);
            break;
        case Calendar.DATE:
            cal.add(Calendar.DATE, addNumber);
            break;
        case Calendar.MONTH:
            cal.add(Calendar.MONTH, addNumber);
            break;
        default:
            break;
        }
        if(StringUtils.isEmpty(dateFormat))
        {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    
    /**
     * 获取指定时间的第几个月后，第几号
     * @param addBefore
     * @param dateFormat
     * @param monthAdd
     * @param dayAdd
     * @return
     */
    public static String addDateOfMonth(String addBefore,String dateFormat,int monthAdd,int dayAdd)
    {

        Date date = DateUtils.strToDate(addBefore);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthAdd);
        cal.set(Calendar.DATE,dayAdd);
        if(StringUtils.isEmpty(dateFormat))
        {
            dateFormat = "yyyy-MM-dd";
        }
        return new SimpleDateFormat(dateFormat).format(cal.getTime());
    }
    
    public static String converDateStrToDefineDate(String dateString, String formate)
    {
        if(StringUtils.isEmpty(dateString))
        {
            return "";
        }

        Pattern pat = Pattern.compile("^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$");
        Matcher m = pat.matcher(dateString.trim());
        boolean b = m.find();
        SimpleDateFormat formatter;
        if(b)
        {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        else
        {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        try
        {
            Date date = formatter.parse(dateString);
            SimpleDateFormat resultFormat = new SimpleDateFormat(formate);
            return resultFormat.format(date);
        }
        catch(ParseException e)
        {
            return dateString;
        }
    }
    
    /**
     * 当前时间增加一个秒数
     * 这里用一句话描述这个方法的作用
     * currentTimeAddSeconds        
     * @param @param seconds
     * @param @return     
     * @return long
     * @Exception 异常对象
     */
    public static long currentTimeAddSeconds(int seconds)
    {
        Date d1 =new Date();
        return (d1.getTime()/1000)+seconds;
    }
    
//    /**
//     * 将时间戳：System.currentTimeMillis(); 转为年月日时分秒的字符串
//     * convertCurrentTimeMillisToStr        
//     * @param @param mill
//     * @param @return     
//     * @return String
//     * @Exception 异常对象
//     */
//    public static String convertCurrentTimeMillisToStr(long mill)
//    {
//        Date date = new Date(mill);
//        String strs = "";
//        try
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            strs = sdf.format(date);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return strs;
//    }
    
    /**
     * 把时间date(时间戳)转换为固定的格式destFormat
     * @param destFormat String 要转换的目标格式
     * @param date int 时间戳(单位:秒)
     * @return  转制以后的时间字符串
     */
    public static String coverDateFormat(String destFormat, int date) {
        SimpleDateFormat format = new SimpleDateFormat(destFormat);
        return format.format(date*1000L);
    }

    /**
     * 把时间date转换为固定的格式destFormat
     * @param destFormat String 要转换的目标格式
     * @param date date 时间
     * @return  转制以后的时间字符串
     */
    public static String coverDateFormat(String destFormat, Date date) {
        String strDate = "";
        if ((date != null) && !date.equals("")) {
            SimpleDateFormat format = new SimpleDateFormat(destFormat);
            strDate = format.format(date);
        }
        return strDate;
    }

    /**
     * 把时间date从原来的格式srcFomatStr转换为新的格式destFormatStr
     * @param destFormat String 新格式
     * @param destFormat String 原格式
     * @param date String 时间
     * @return  转制以后的时间字符串
     */
    public static String coverDateFormat(String destFormatStr, String srcFomatStr,
            String date) {
        if ((date != null) && !date.equals("")) {
            try {
                SimpleDateFormat srcFormat = new SimpleDateFormat(srcFomatStr);
                Date dateTemp = srcFormat.parse(date);
                SimpleDateFormat destFormat = new SimpleDateFormat(
                        destFormatStr);
                date = destFormat.format(dateTemp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            return "000000000000";
        }
        return date;
    }
    
    /**
     * 把时间date从原来的格式srcFomatStr转换为时间戳字符串
     * @param destFormat String 原格式
     * @param date String 时间
     * @return  转制以后的时间戳字符串
     */
    public static int coverDateBCD2Int(String srcFomatStr,String dateStr) {
        int result = 0;
        if ((dateStr != null) && !dateStr.equals("")) {
            try {
                SimpleDateFormat srcFormat = new SimpleDateFormat(srcFomatStr);
                Date date = srcFormat.parse(dateStr);
                result = (int)(date.getTime() / 1000L);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 把时间date从原来的格式srcFomatStr转换为新的格式destFormatStr
     * @param fomatStr String 时间格式
     * @param date String  时间
     * @return  时间戳
     */
    public static long getTime(String fomatStr, String date) {
        long temp = 0;
        if ((date != null) && !date.equals("")) {
            try {
                SimpleDateFormat srcFormat = new SimpleDateFormat(fomatStr);
                Date dateTemp = srcFormat.parse(date);
                temp = dateTemp.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * 取得当前的系统时间(时间戳)
     */
    public static int getcurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
    
    public static long getcurrentTimeLong() {
        return System.currentTimeMillis() / 1000;
    }

    /**    
     * findDates(这里用一句话描述这个方法的作用)    
     * aimDate是否在dBegin和dEnd范围之内
     * @param @param dBegin 开始时间
     * @param @param dEnd 结束时间
     * @param @param aimDate 目的时间
     * @param @return     
     * @return boolean
     * @Exception 异常对象
    */
    public static boolean isBetweenDate(Date dBegin, Date dEnd,Date aimDate) { 
        if ((dBegin.before(aimDate) && dEnd.after(aimDate)) || dBegin.compareTo(aimDate) == 0
                || dEnd.compareTo(aimDate) == 0)
        {
            return true;
        }else
        {
            return false;
        }
    }
    
    public static Date addDays(Date date,int value){
        Calendar calendar=Calendar.getInstance();  
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+value);
        return calendar.getTime();
    }
}
