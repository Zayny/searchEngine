package searchengine.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	/**
	 * 根据date 返回20150526103731格式
	 * @param date
	 * @return
	 */
	public static String fomertDate(Date date) {
		return new SimpleDateFormat("YYYYMMddHHmmss").format(date);
	}
}
