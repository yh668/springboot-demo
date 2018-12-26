/*
 * @文件名： StringUtils.java 
 * @创建人: zyl
 * @创建时间: 2014-4-23   
 * @包名： com.jn.erp.common.util
 * @版本： 1.0
 * 版权所有 © 聚能科技有限公司 
 * 描述:
*/
package com.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

/**
 * 类名称：StringUtils 类描述： 字符串工具类，提供一些常用的字符串函数
 */
public class StringUtils extends org.springframework.util.StringUtils {
	private final static MD5 md = new MD5();

	/**
	 * 是否字符
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isChar(char ch) {
		if (ch != ' ' && ch != ',' && ch != ')' && ch != '(') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得字符串的前面部分
	 * 
	 * @param src
	 * @param token
	 * @return
	 */
	public static String getFrontStr(String str, String token) {
		int pos1 = str.lastIndexOf(token);
		if (pos1 >= 0) {
			return str.substring(0, pos1);
		} else {
			return str;
		}
	}

	/**
	 * 获得字符串的后面部分
	 * 
	 * @param src
	 * @param token
	 * @return
	 */
	public static String getBackStr(String str, String token) {
		int pos1 = str.lastIndexOf(token);
		if (pos1 >= 0) {
			return str.substring(pos1 + token.length());
		} else {
			return str;
		}
	}

	/**
	 * 获得字符串的后面部分
	 * 
	 * @param src
	 * @param token
	 * @return
	 */
	public static String getBackStr(String str, char token) {
		int pos1 = str.lastIndexOf(token);
		if (pos1 >= 0) {
			return str.substring(pos1 + 1, str.length() - 1);
		} else {
			return str;
		}
	}

	/**
	 * 删除最后一个字符
	 */
	public static String deleteLastChar(String src) {
		if (src == null || src.length() <= 1) {
			return null;
		} else {
			return src.substring(0, src.length() - 1);
		}
	}

	/**
	 * 删除stringbuffer的最后一个字符
	 * 
	 * @param sb
	 */
	public static void deleteLastChar(StringBuffer sb) {
		deleteLastChar(sb, 1);
	}

	/**
	 * 删除指定长度的字符
	 * 
	 * @param sb
	 * @param len
	 */
	public static void deleteLastChar(StringBuffer sb, int len) {
		if (sb == null || sb.length() <= len) {
			return;
		} else {
			sb.delete(sb.length() - len, sb.length());
		}
	}

	/**
	 * 加入对象到lst中，若是相同的对象将不加入
	 * 
	 * @param lst
	 * @param obj
	 */
	public static void addToList(List lst, Object obj) {
		if (obj instanceof String) {
			String strValue = (String) obj;
			for (int i = 0; i < lst.size(); i++) {
				String value = (String) lst.get(i);
				if (value.equals(strValue)) {
					return;
				}
			}
			lst.add(obj);
		}
	}

	/**
	 * 加入字符串到stringlist中
	 * 
	 * @param lst
	 * @param str
	 */
	public static void addToStringList(StringList lst, String str) {
		for (int i = 0; i < lst.size(); i++) {
			if (lst.get(i).equals(str)) {
				return;
			}
		}
		lst.add(str);
	}

	/**
	 * 转化lst中数据为字符串 token 分隔标记
	 * 
	 * @param lst
	 * @param token
	 * @return
	 */
	public static String lstToSting(List lst, String token) {
		if (lst == null || token == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < lst.size(); i++) {
			String value = (String) lst.get(i);
			sb.append(value + token);
		}
		StringUtils.deleteLastChar(sb, token.length());
		return sb.toString();
	}

	/**
	 * 将lst转换为如下形式 &key1=value1&key1=value2
	 * 
	 * @param lst
	 * @param key
	 * @return
	 */
	public static String lstToValueStr(List lst, String key) {
		String str = "";
		if (lst == null) {
			return null;
		}
		for (int i = 0; i < lst.size(); i++) {
			str = str + "&" + key + "=" + (String) lst.get(i);
		}
		return str;
	}

	/**
	 * 将lst中数据转换为字符串数组
	 * 
	 * @param lst
	 * @return
	 */
	public static String[] lstToStringArr(List lst) {
		if (lst == null) {
			return null;
		} else {
			String[] arr = new String[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				arr[i] = lst.get(i).toString();
			}
			return arr;
		}
	}

	/**
	 * 描述：字符串数组转化成字符串 创建时间：2006-10-16，14:58:04
	 * 
	 * @param arr
	 * @return
	 */
	public static String arrToString(Object[] arr) {
		String result = ArrayUtils.toString(arr);
		result = result.substring(1, result.length() - 1);
		return result;
	}

	/**
	 * 去掉多余空格
	 * 
	 * @return
	 */
	public static String deleteExceptBlank(String psql) {
		String sql = StringUtils.deletHtml(psql);
		StringBuffer sb = new StringBuffer(sql + " $");
		int blankCount = 0;
		char ch = sb.charAt(0);
		int i = 0;
		int k = 0;
		int[] pos = new int[sb.length()];
		while (ch != '$') {
			ch = sb.charAt(i);
			if (ch == ' ') {
				blankCount++;
			} else {
				blankCount = 0;
			}
			if (blankCount > 1) {
				pos[k] = i;
				k++;
			}
			i++;
		}
		StringBuffer sb1 = new StringBuffer();
		boolean isBlank = false;
		for (int i1 = 0; i1 < sb.length(); i1++) {
			isBlank = false;
			if (k > 0 && i1 <= pos[k - 1]) {
				for (int i2 = 0; i2 < k; i2++) {
					if (i1 == pos[i2]) {
						isBlank = true;
					}
				}
				if (!isBlank) {
					sb1.append(sb.charAt(i1));
				}
			} else {
				sb1.append(sb.charAt(i1));
			}
		}
		// 去掉最后的$
		sb1.deleteCharAt(sb1.lastIndexOf("$"));
		sql = sb1.toString();
		return sql;
	}

	/**
	 * 反转字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String covertStr(String str) {
		StringBuffer sb = new StringBuffer(str);
		StringBuffer sb1 = new StringBuffer();
		int len = sb.length();
		for (int i = len - 1; i >= 0; i--) {
			sb1.append(sb.charAt(i));
		}
		return sb1.toString();
	}

	/**
	 * 删除\r\n等html字符
	 * 
	 * @param psql
	 * @return
	 */
	public static String deletHtml(String psql) {
		// 加结束标记
		psql = psql + "$";
		StringBuffer sb = new StringBuffer(psql);
		for (int i = 0; i < psql.length() - 1; i++) {
			char ch = psql.charAt(i);
			if (ch == '\r' || ch == '\n') {
				sb.replace(i, i + 1, " ");
			}
		}
		StringUtils.deleteLastChar(sb);
		String sql2 = sb.toString();
		return sql2;
	}

	/**
	 * 去掉所有空格
	 * 
	 * @param str
	 */
	public static String deleteAllBlank(String str) {
		// 检查是否有$号，没有则加上
		String sql1 = str;
		StringBuffer sb = new StringBuffer(sql1);
		if (sql1.indexOf("$") == -1) {
			sb.append("$");
		}
		char ch = sb.charAt(0);
		int i = 0;
		int k = 0;
		int[] pos = new int[sb.length() + 1];
		while (ch != '$') {
			if (ch == ' ') {
				pos[k] = i;
				k++;
			}
			i++;
			ch = sb.charAt(i);
		}
		for (i = 0; i < k; i++) {
			sb.deleteCharAt(pos[i] - i);
		}
		// 去掉最后的$
		sb.deleteCharAt(sb.lastIndexOf("$"));
		return sb.toString();
	}

	public static StringList conCatStrList(StringList lst1, StringList lst2) {
		for (int i = 0; i < lst2.size(); i++) {
			lst1.add(lst2.get(i));
		}
		return lst1;
	}

	/**
	 * 解析字符串，返回其中的第一个年月字符串
	 * 
	 * @param s
	 *            待解析的字符串
	 * @return 一个由4位数字的年份和2位数字的月份组成的字符串
	 */
	public static String parseFirstYYYYMM(String s) {
		String ret = "";
		String sRegex = "(\\d{4})(\\d{2})";
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(sRegex);
		java.util.regex.Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			String yyyy = matcher.group(1);
			String mm = matcher.group(2);
			int year = Integer.parseInt(yyyy);
			int month = Integer.parseInt(mm);
			if ((1900 < year && 2050 > year) && (1 <= month && 12 >= month)) {
				ret = yyyy + mm;
				break;
			}
		}
		return ret;
	}

	public static String blankIfNull(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * 获取以某种符号分隔的重复字符串
	 * 
	 * @param element
	 * @param split
	 * @return
	 */
	public static String repeatSymbolSplittedString(String element, int num, String split) {
		StringBuffer sb = new StringBuffer(250);
		for (int i = 0; i < num; i++) {
			if (i != 0) {
				sb.append(split);
			}
			sb.append(element);
		}
		return sb.toString();
	}

	/**
	 * 获取以{逗号+空格}分隔的重复字符串
	 * 
	 * @param element
	 * @param split
	 * @return
	 */
	public static String repeatSymbolSplittedString(String element, int num) {
		return repeatSymbolSplittedString(element, num, ", ");
	}

	/**
	 * 获取字符在源串中的出现次数
	 * 
	 * @param src
	 * @param c
	 * @return
	 */
	public static int getRepeatCount(String src, char c) {
		int count = 0;
		for (int i = 0; i < src.length(); i++) {
			if (src.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}

	public static boolean isNotEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isNotEmpty(str);
	}

	public static boolean isEmpty(String str) {
		return org.apache.commons.lang.StringUtils.isEmpty(str);
	}

	public static boolean equalsIgnoreCase(String a, String b) {
		return org.apache.commons.lang.StringUtils.equalsIgnoreCase(a, b);
	}

	/**
	 * 比较两个字符串是否相等（处理了两个参数为空的情况） a=null b=null 返回true 其他情况返回值和两个字符串的比较一致
	 * equals(这里用一句话描述这个方法的作用)
	 * 
	 * @param @param
	 *            a
	 * @param @param
	 *            b
	 * @param @return
	 * @return boolean
	 * @Exception 异常对象
	 */
	public static boolean equals(String a, String b) {
		if (a == null) {
			return a == b;
		}
		return a.equals(b);
	}

	public static boolean isEmpty(String[] strs) {
		return (null == strs || strs.length == 0);
	}

	public static boolean isNotEmpty(String[] strs) {
		return !(null == strs || strs.length == 0);
	}

	public static boolean isNotEmpty(int[] strs) {
		return !(null == strs || strs.length == 0);
	}

	/**
	 * 替换
	 * 
	 * @param src
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String src, String regex, String replacement) {
		int indexOf = src.indexOf(regex);
		if (indexOf == -1)
			return src;
		return replaceAll(src.substring(0, indexOf) + replacement + src.substring(indexOf + regex.length()), regex,
				replacement);
	}

	/**
	 * 分割
	 * 
	 * @param src
	 * @param regex
	 * @return
	 */
	public static String[] split(String src, char regex) {
		return org.apache.commons.lang.StringUtils.split(src, regex);
	}

	/**
	 * 分割
	 * 
	 * @param src
	 * @param regex
	 * @return
	 */
	public static String[] split(String src, String regex) {
		return org.apache.commons.lang.StringUtils.split(src, regex);
	}

	/**
	 * 描述:给字符串加引号，，主要用于象，aa,bb,cc 变成 'aa','bb','cc',符号可自定义
	 * 创建时间：2006-12-9,17:06:48
	 * 
	 * @param inputStr
	 * @param token
	 * @return
	 */
	public static String addQuoteToStr(final String inputStr, String token, String quote) {
		String result = inputStr;
		String[] splitStr = inputStr.split(token);
		for (int i = 0; i < splitStr.length; i++) {
			splitStr[i] = quote + splitStr[i] + quote;
		}
		result = arrToString(splitStr);
		return result;
	}

	/**
	 * 描述:给字符串加引号，，主要用于象，aa,bb,cc 变成 'aa','bb','cc' 创建时间：2006-12-9,17:06:42
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String addQuoteToStr(String inputStr) {
		return addQuoteToStr(inputStr, ",", "'");
	}

	/**
	 * 给数组中的每个元素加上单引号 元素为1 2 => '1','2' 应用：在页面得到元素的数组,变化为sql中的in
	 * 如果str为null或空返回''
	 * 
	 * @param str
	 *            字符数组
	 * @return
	 */
	public static String addQuoteToStr(String[] str) {
		String inputStr = "";
		if (str != null && str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				inputStr += str[i] + ",";
			}
			return addQuoteToStr(inputStr, ",", "'");
		} else {
			return "''";
		}
	}

	public static String addQuoteToStr(int[] str) {
		String inputStr = "";
		if (str != null && str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				inputStr += str[i] + ",";
			}
			return addQuoteToStr(inputStr, ",", "");
		} else {
			return "";
		}
	}

	public static String addQuoteToStr(List<String> list) {
		String inputStr = "";
		if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				inputStr += list.get(i) + ",";
			}
			return addQuoteToStr(inputStr, ",", "'");
		} else {
			return "''";
		}
	}

	public static String addQuoteToStr(List<String> list, String quote) {
		String inputStr = "";
		if (!org.springframework.util.CollectionUtils.isEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				inputStr += list.get(i) + ",";
			}
			return addQuoteToStr(inputStr, ",", quote);
		} else {
			return "''";
		}
	}

	/**
	 * 将lst中数据转换为二维数组
	 * 
	 * @param lst
	 *            待转化的list对象,其中的元素的类型为list
	 * @return
	 * @author zyl
	 */
	public static String[][] lstToTwoDimStringArr(List lst) {
		if (lst == null) {
			return null;
		} else {
			String[][] arr = new String[lst.size()][];
			for (int i = 0; i < lst.size(); i++) {
				List _lst = (List) lst.get(i);
				String[] str = lstToStringArr(_lst);
				arr[i] = str;
			}
			return arr;
		}
	}

	/**
	 * 给字符串数组中的元素添加逗号 返回String StringUtils.addCommaToArray(null) = 空字符串
	 * StringUtils.addCommaToArray(['']) = 空字符串
	 * StringUtils.addCommaToArray(['','a','b']) = a,b
	 * StringUtils.addCommaToArray(['a', 'b']) = a,b
	 * 
	 * @param str
	 * @return
	 */
	public static String addCommaToArray(Object[] obj) {
		String inputStr = "";
		if (obj != null && obj.length > 0) {
			for (int i = 0; i < obj.length; i++) {
				if (null != obj[i] && !"".equals(obj[i])) {
					inputStr += obj[i].toString() + ",";
				}
			}
			return "".equals(inputStr) ? "" : inputStr.substring(0, inputStr.length() - 1);
		} else {
			return "";
		}
	}

	/**
	 * 取指定长度的前面字符串
	 * 
	 * @param src
	 * @param byteLength
	 *            安字节算, 中文算两个字节
	 * @return
	 */
	public static String ellipse(String src, int byteLength, String tail) {
		if (src == null)
			return src;

		StringBuffer s = new StringBuffer();
		for (int i = 0; i < src.length() && byteLength > 0; i++) {
			char c = src.charAt(i);
			s.append(c);
			byteLength -= String.valueOf(c).getBytes().length;
		}
		if (tail != null && byteLength <= 0) {
			byteLength = tail.getBytes().length;
			for (int i = s.length() - 1; i >= 0 && byteLength > 0; i--) {
				char c = s.charAt(i);
				s.deleteCharAt(i);
				byteLength -= String.valueOf(c).getBytes().length;
			}
			return s.append(tail).toString();
		} else {
			return s.toString();
		}
	}

	/**
	 * 产生随机数字符串：参数不要大于15 getRadomStr(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
	 * (这里描述这个方法的注意事项 – 可选)
	 * 
	 * @param @param
	 *            len
	 * @param @return
	 * @return String
	 * @Exception 异常对象
	 */
	public static String getRadomStr(int len) {
		String res = null;
		double createNum = 0;
		String base = "1";
		for (int i = 0; i < len; i++) {
			base += "0";
		}
		createNum = Math.random() * Integer.parseInt(base);
		res = String.valueOf(createNum);
		if (res.indexOf('.') != -1) {
			res = res.replaceAll("\\.", "");
		}
		if (res.length() >= len) {
			res = res.substring(0, len);
		}
		return res;
	}

	/**
	 * getUserPassword(得到加密后的密码) (这里描述这个方法适用条件 – 可选) (这里描述这个方法的注意事项 – 可选)
	 * 
	 * @param @param
	 *            user
	 * @param @return
	 * @return String
	 * @Exception 异常对象
	 */
	public static String getEncryptPassword(String userNo, String userPassword) {
		userNo = userNo.toLowerCase();
		String password = md.getMD5Encode(userNo + "StringUtils.getEncryptPassword.Encrypt" + userPassword);
		return md.getMD5Encode(password);
	}

	/**
	 * 给文件名增加随机的数字以免迅雷老是说下载过了 getFileWithRadomName(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选) (这里描述这个方法的注意事项 – 可选)
	 * 
	 * @param @param
	 *            fileName
	 * @param @return
	 * @return String
	 * @Exception 异常对象
	 */
	public static String getFileWithRadomName(String fileName) {
		if (isEmpty(fileName)) {
			return fileName;
		}

		if (fileName.indexOf(".") == -1) {
			return fileName;
		}
		String fileNamePfix = fileName.substring(0, fileName.indexOf("."));
		String fileTyle = fileName.substring(fileName.indexOf("."));
		return fileNamePfix + "_" + DateUtils.getStringTodayA() + fileTyle;
	}

	public static String getInputStr(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str;
		}
	}

	/**
	 * 如果字符串的长度小于lenght，则高位补0
	 * 
	 * @param str
	 *            String 要补充的字符串
	 * @param lenght
	 *            int 长度
	 * @return 补0以后的字符串
	 */
	public static String supplement(String str, int lenght) {
		int strLength = str.length();
		StringBuffer sb = new StringBuffer();
		while (strLength < lenght) {
			sb.append("0");
			strLength++;
		}
		sb.append(str);
		return sb.toString();
	}

	public static void main(String[] args) {

		// System.out.println(supplement("1", 3));
		List<String> list = new ArrayList<String>();
		list.add("001");
		list.add("002");
		list.add("003");
		System.out.println(addQuoteToStr(list));
	}

	/**
	 * 如果字符串不为空则返回字符串，为空时返回"";
	 */
	public static String getDefaultStr(String validateMsg) {
		return isEmpty(validateMsg) ? "" : validateMsg;
	}

	/**
	 * 字符串转码
	 * 
	 * @param string
	 *            需要转码的字符串
	 * @param srcCode
	 *            原来编码
	 * @param desCode
	 *            需要转成的编码
	 * @return String 转码后的字符串
	 * @Exception 异常对象
	 */
	public static String transCodeCharSet(String string, String srcCode, String desCode) {
		if (null == string) {
			return null;
		}
		String result = null;
		try {
			result = new String(string.getBytes(srcCode), desCode);
		} catch (UnsupportedEncodingException e) {
			// 转码失败返回原字符串
			return string;
		}
		return result;
	}

	public static boolean isCharLegal(String str) {
		String pattern = "[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*\", \"";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
}
