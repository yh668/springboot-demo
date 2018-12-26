/*
 * @文件名： MobileUtils.java 
 * @创建人: admin
 * @创建时间: 2017-10-9   
 * @包名： com.jn.erp.api.utils
 * @版本： 1.0
 * 版权所有 © 聚能科技有限公司 
 * 描述:
*/
package com.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**     
 * 类名称：MobileUtils    
 * 类描述：    
 * @version  1.0    
 */
public class MobileUtils
{

    /** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String mobileNo) throws PatternSyntaxException
    {
        return isChinaPhoneLegal(mobileNo) || isHKPhoneLegal(mobileNo);
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    private static boolean isPhoneShortNum(String str) throws PatternSyntaxException
    {
    	String regExp="(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +  
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)"; 
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
  
    
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    private static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException
    {
        String regExp = "^(1[0-9][0-9])\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    private static boolean isHKPhoneLegal(String str) throws PatternSyntaxException
    {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    } 
    
    public static void main(String[] args)
    {
        String mobileNo = "13307093675";
        System.out.println(isPhoneLegal(mobileNo));
    }
}
