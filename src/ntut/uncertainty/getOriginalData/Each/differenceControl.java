package ntut.uncertainty.getOriginalData.Each;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;
import ntut.uncertainty.Property.PropertyFile;


public class differenceControl extends FileLocation {
	private static double dis  = 15;

	public static void main(String[] args) throws JsonParseException, Exception {
		// TODO Auto-generated method stub
		// get  station difference of value and the accurate

		String fileAdd = root0927Direction + "\\EachStation\\";
		String[] fileList = new File(fileAdd + "Ordinate\\").list();
		PropertyFile pf = new PropertyFile(locateDirection);

		String[] locationId = pf.getLocationID();
		for (int i = 1; i < fileList.length; i++) {
			int count=0;
			String[] content = new AtFileReader(fileAdd + "Ordinate\\" + fileList[i]).getContainWithOut("-999");
			FileWriter fw = new FileWriter(fileAdd + "\\Expect0\\" + locationId[i] + ".Expect0");
			for (String d : content) {
				String tempt[] = d.split(",");
			//	if ((Double.parseDouble(tempt[0]) - 0) > 0.0001 && (Double.parseDouble(tempt[1]) - 0) > 0.0001) {
				if (Math.abs(Double.parseDouble(tempt[0]) - Double.parseDouble(tempt[1]) ) < Double.parseDouble(tempt[0])*0.3) {
				fw.write(d + "\r\n");
				count++;
				}
			}
			System.out.println(fileList[i] + "\t" + count/(content.length+1.) );
			fw.close();
		}
	}
}
