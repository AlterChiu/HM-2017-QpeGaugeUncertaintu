package ntut.uncertainty.ExportQpe.Runtime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTranslate {
	public static final String DMY = "dd-MM-yy";
	public static final String MDY = "MM-dd-yy";
	public static final String YMD = "yy-MM-dd";
	public static final String YMDH = "yyyy-MM-dd_HH";
	public static final String YMD_HMS = "yyyy-MM-dd_HH:mm:ss";
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMDHMS3 = "yyyyMMddHHmmss";
	public static final String YMDAHMS_SLASH = "yyyy/MM/dd a HH:mm:ss";
	public static final String YMDAHMS_DASH = "yyyy-MM-dd a HH:mm:ss";
	public static final String QPETIME = "yyyyMMddHHmmss";
	private SimpleDateFormat  dateFormat;
	
	public String milliToDate(long time , String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}
	public String getCurrentTime(String format){
		return new TimeTranslate().milliToDate(System.currentTimeMillis(), format);
	}
	public long  DateToMilli(String time , String format) throws NumberFormatException, ParseException{
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(time).getTime();
	}
	
	
	public String[] getFormate(){
		String[] content = {"dd-MM-yy","MM-dd-yy","yy-MM-dd","yyyy-MM-dd_HH","yyyy-MM-dd_HH:mm:ss",
				"yyyy-MM-dd HH:mm:ss","yyyyMMddHHmmss","yyyy/MM/dd a HH:mm:ss","yyyy-MM-dd a HH:mm:ss"};
		return content;
		}
	
	
}
