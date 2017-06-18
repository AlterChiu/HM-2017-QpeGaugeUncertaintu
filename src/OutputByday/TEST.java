package OutputByday;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.math3.special.Gamma;

import jdistlib.evd.GEV;
import ntut.uncertainty.ExportQpe.Runtime.TimeTranslate;

public class TEST {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	
		double max = 123456.;
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		System.out.println(sdf.format(System.currentTimeMillis()));
		System.out.println(System.currentTimeMillis());
		
		long date = 3600000+sdf.parse("20170606174240").getTime();
		long tt = new TimeTranslate().DateToMilli("20170606174240", TimeTranslate.QPETIME);
		long test = Long.parseLong("1474945200000");
		
		System.out.println(tt);
		System.out.println(sdf.format(test));
		
		
	         //  利用 DateFormat 來parse 日期的字串
		     
	}

}
