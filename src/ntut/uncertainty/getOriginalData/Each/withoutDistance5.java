package ntut.uncertainty.getOriginalData.Each;

import java.io.FileWriter;
import java.io.IOException;

import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;



public class withoutDistance5 extends FileLocation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileAdd = workDirection + "adjusted.csv";
		String[][] content = new AtFileReader(fileAdd).getCsv();

		for (int i = 0; i < content.length; i++) {
			if (content[i][0].equals("0") ||content[i][1].equals("0")) {
				content[i][0] = "-999";
				content[i][1] = "-999";
			}
		}

		FileWriter fw = new FileWriter(workDirection + "tempt.csv");

		for (String t[] : content) {
				fw.write(t[0]+ "," + t[1] + "\r\n");
		}
		fw.close();
		
		fw = new FileWriter(workDirection + "expect0.csv");
		String[] content_5 = new AtFileReader(workDirection + "tempt.csv").getContainWithOut("-999");
		for(String d :content_5){
			fw.write(d+"\r\n");
		}
		fw.close();
	}
	


}
