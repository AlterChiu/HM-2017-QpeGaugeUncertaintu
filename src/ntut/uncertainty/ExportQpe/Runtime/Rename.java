package ntut.uncertainty.ExportQpe.Runtime;

import java.io.File;
import java.text.ParseException;

import ntut.uncertainty.Property.FileFunction;

public class Rename {

	public static void main(String[] args) throws NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		
		String date = "20160927110000";
		TimeTranslate tt = new TimeTranslate();
		
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\QPESUMS(50_15_20DIS)\\";
		String[] fileList = new File(fileAdd).list();
		
		FileFunction fileFunction = new FileFunction();
		
		for(int i=0;i<fileList.length;i++){
			String qpeList[] = new File(fileAdd + fileList[i]+"\\").list();
			for(int j=0;j<qpeList.length;j++){
				
				long tempDate = tt.DateToMilli(date, TimeTranslate.QPETIME) + 3600000*j ;
				String outPutDate = tt.milliToDate(tempDate, TimeTranslate.QPETIME);
				fileFunction.moveFile(fileAdd + fileList[i] + "\\" + qpeList[j], 
						fileAdd + fileList[i] + "\\qp_" +outPutDate+".asc" );

			}
			
			
			
			
			
		}
		
		
	}

}
