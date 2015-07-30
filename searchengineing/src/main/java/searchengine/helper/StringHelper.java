package searchengine.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 去除空格
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String result = "";
		if (str != null) {
			Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
			Matcher matcher = pattern.matcher(str);
			result = matcher.replaceAll("");
		}
		return result;
	}

	/**
	 * 功能: 按给定size大小分隔数组，分隔结果放在list中
	 * 
	 * @param arr
	 *            源数组
	 * @param size
	 *            分隔数组的size
	 * @return
	 */
	public static List<String[]> splitArrBySize(String[] arr, int size) {
		List<String[]> list = new ArrayList<String[]>();
		if (arr != null) {
			int from = 0;
			int length = arr.length;
			int to = size < length ? size : length;
			String[] tmp;
			while (from < length) {
				tmp = Arrays.copyOfRange(arr, from, to);
				list.add(tmp);
				from = to;
				to = (to + size) < length ? (to + size) : length;
			}
		}
		return list;
	}

	/**
	 * 功能: 将String[]中字符串以逗号分割后拼成一个字符串,带有单引号，去掉空格
	 *
	 * @param strArray
	 * @return
	 */
	public static String parseToSQLStringNoBlank(String[] strArray) {
		if (strArray == null || strArray.length == 0)
			return "'notExistId'";
		String myStr = "";
		for (int i = 0; i < strArray.length - 1; i++) {
			if (strArray[i] != null && strArray[i].length() > 0)
				myStr += "'" + strArray[i] + "'" + ",";
		}
		if (strArray[strArray.length - 1] != null
				&& strArray[strArray.length - 1].length() > 0) {
			myStr += "'" + strArray[strArray.length - 1] + "'";
		} else {
			myStr = myStr.substring(0, myStr.length() - 1);
		}
		return myStr;
	}

	/**
	 * 功能: 把"123,234,567"转为new String[]{"123", "234", "567"}
	 *
	 * @param str
	 * @return
	 */
	public static String[] parseStringToArray(String str) {
		return parseStringToArray(str, ",");
	}

	/**
	 * 功能: 把"123,234,567"转为new String[]{"123", "234", "567"}
	 *
	 * @param str
	 * @param splitKey
	 * @return
	 */
	public static String[] parseStringToArray(String str, String splitKey) {
		String[] returnStrArray = null;
		if (str != null && str.length() > 0) {
			returnStrArray = str.split(",", -1);
		}
		if (returnStrArray == null) {
			returnStrArray = new String[0];
		}
		return returnStrArray;
	}

	/**
	 * 功能: 把"id1:zhangsan;id2:lisi"转为Map<String,String>
	 *
	 * @param str
	 * @return
	 */
	public static Map<String, String> parseStringToMap(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String[] strArray = null;
		if (str != null && str.length() > 0) {
			strArray = str.split(";", -1);
			if (strArray != null && strArray.length > 0) {
				String[] strArray2 = null;
				for (int i = 0; i < strArray.length; i++) {
					strArray2 = strArray[i].split(":", -1);
					if (strArray2 != null && strArray2.length == 2) {
						map.put(strArray2[0], strArray2[1]);
					}
				}
			}
		}
		return map;
	}

	public static String encode2Encode(String original, String encode1,
			String encode2) {
		if (original != null) {
			try {
				return new String(original.getBytes(encode1), encode2);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 显示数据前过滤掉null
	 * 
	 * @param myString
	 * @return
	 */
	public static String replaceNull(String myString) {
		if ("null".equals(myString)) {
			return "";
		}
		if (myString != null) {
			return myString;
		} else {
			return "";
		}
	}

	/**
	 * 判断一个数组是否包含一个字符串
	 * 
	 * @param arrayString
	 * @param str
	 * @return
	 */
	public static boolean ArrayContainString(String[] arrayString, String str) {
		if (arrayString == null || arrayString.length == 0) {
			return false;
		}
		for (int i = 0; i < arrayString.length; i++) {
			if (arrayString[i].equals(str))
				return true;
		}
		return false;
	}

	/**
	 * 功能: 把Map中的值依次取出来，以URL传值的方式拼成字符串
	 *
	 * @param mValue
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String encodeUrlParameter(Map mValue) {
		return encodeUrlParameter(mValue, new String[0]);
	}

	/**
	 * 功能: 把Map中的值依次取出来，以URL传值的方式拼成字符串
	 *
	 * @param mValue
	 * @param ignoreName
	 *            忽略的field
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String encodeUrlParameter(Map mValue, String[] ignoreName) {
		String str = "";
		for (Iterator itMValue = mValue.keySet().iterator(); itMValue.hasNext();) {
			String tempKey = String.valueOf(itMValue.next());
			String tempValue = (mValue.get(tempKey) == null) ? "" : String
					.valueOf(mValue.get(tempKey));
			if (tempKey.startsWith("VENUS") || tempKey.startsWith("RANMIN")) {
				continue;
			}
			if (ArrayContainString(ignoreName, tempKey)) {
				continue;
			}
			if (str.length() > 0) {
				str += "&";
			}
			str += tempKey + "=" + encodeUrl(tempValue);
		}
		return str;
	}

	/**
	 * 功能: 对url编码
	 * 
	 * @param url
	 * @return
	 */
	public static String encodeUrl(String url) {
		String rtStr = "";
		try {
			if (url != null && url.length() >= 0) {
				rtStr = URLEncoder.encode(url, "GBK");
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		return rtStr;
	}

	/**
	 * 功能: 从一个文件中读出字符串
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readStringFromFile(File file) {
		String rtStr = "";
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			String tempStr = "";
			while ((tempStr = in.readLine()) != null) {
				rtStr += tempStr + "\n";
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rtStr;
	}

	/**
	 * 功能: 得到str的首字母大写
	 *
	 * @param str
	 * @return
	 */
	public static String toFirstUpperCase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		} else {
			String firstStr = str.substring(0, 1);
			return firstStr.toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 功能: 得到百分比的显示
	 *
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static String getPercentage(int numerator, int denominator) {
		return getPercentage(numerator * 1.00, denominator * 1.00);
	}

	/**
	 * 功能: 得到百分比的显示
	 *
	 * @param numerator
	 * @param denominator
	 * @return
	 */
	public static String getPercentage(double numerator, double denominator) {
		double percentage = numerator * 1.00 / denominator;
		if (String.valueOf(percentage).endsWith(String.valueOf(Double.NaN))) {
			return "空";
		}
		percentage = percentage * 100;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(percentage) + "%";
	}

	/**
	 * 功能: 将BigDecimal类型的数据转换成带百分号显示
	 * 
	 * @param BigDecimal
	 *            value 需要转换成百分比的数
	 * @return String 转换后的结果
	 */
	public static String getPercenage(BigDecimal value) {
		double numerator = 0.00;
		double percentage = 100.000;
		// 第一步判断是否大于零或等于零
		if (value != null && value.compareTo(new BigDecimal(0)) != -1) {
			// 第二步如果是大于1直接取
			if (value.compareTo(new BigDecimal(1)) != 1) {
				// 如果是小于或等于1就需要乘以百分百
				numerator = (value.multiply(new BigDecimal(100))).doubleValue();
			} else {
				// 如果大于1就直接使用
				numerator = value.doubleValue();
			}
		} else {
			if (value == null || value.equals("")) {
				return "";
			} else {
				percentage = 1.000;
			}
		}
		return getPercentage(numerator, percentage);
	}

	/**
	 * 功能: 将BigDecimal类型的数据转换成带百分号显示
	 * 
	 * @author 迟启龙　不过滤大于1的情况
	 * @param BigDecimal
	 *            value 需要转换成百分比的数
	 * @return String 转换后的结果
	 */
	public static String getPercenageNew(BigDecimal value) {
		double numerator = 0.00;
		double percentage = 100.000;
		// 第一步判断是否大于零或等于零
		if (value != null && value.compareTo(new BigDecimal(0)) != -1) {
			// 第二步如果是大于1直接取
			numerator = (value.multiply(new BigDecimal(100))).doubleValue();
		} else {
			if (value == null || value.equals("")) {
				return "";
			} else {
				percentage = 1.000;
			}
		}
		return getPercentage(numerator, percentage);
	}

	/**
	 * 
	 * 功能: 将BigDecimal类型的数据转换成带百分号显示
	 * 
	 * @param Object
	 *            value 需要转换成百分比的数
	 * @return String 转换后的结果
	 */
	public static String getPercenage(Object obj) {
		if (obj != null && !obj.equals("")) {
			return getPercenage((BigDecimal) (obj));
		} else {
			return "";
		}
	}

	/**
	 * 把金额数字用,隔开 。 如 1000.00 ==> 1,000.00
	 * 
	 * @param val
	 * @return
	 * @author huipan
	 */
	public static String addDotMark(BigDecimal val) {
		if (null == val) {
			return "0";
		}
		String s;
		try {
			s = new DecimalFormat("#,##0.00").format(val);
		} catch (Exception e) {
			return val.toString();
		}
		return s;
	}

	/**
	 * 去掉, 1,000.00 ==>1000.00
	 * 
	 * @param val
	 * @return
	 * @author huipan
	 */
	public static BigDecimal removeDotMark(String val) {
		if (1 > val.length()) {
			return new BigDecimal(0);
		}
		val = val.replace(',', ' ');
		return new BigDecimal(trimAllWhitespace(val));
	}

	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return buf.toString();
	}

	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 去掉换行回车
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceBlankAndSpace(String src) {
		src = src.replaceAll("<br>", "");
		return src.replaceAll("\r\n", "");

	}

	/**
	 * 功能: 取十位随机数
	 *
	 * @param vo
	 * @return
	 */
	public static String randomNum() {
		String no = "";
		int a[] = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (10 * (Math.random()));
			no += String.valueOf(a[i]);
		}
		return no;
	}

	// 测试
	public static void main(String[] args) {
		System.out.println(getPercenage(new BigDecimal(10)));
	}

}
