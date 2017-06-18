package ntut.uncertainty.getOriginalData.Adjust;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;

public class OriginalData extends FileLocation {
	private DecimalFormat df = new DecimalFormat("#0.00");
/**
 *   GET the value with out text
 * @param text
 * @param inFile
 * @param OutFile
 * @throws IOException
 */
	public void GetWithout(String text, String inFile, String OutFile) throws IOException {
		String[] content = new AtFileReader(inFile).getContainWithOut(text);
		FileWriter fw = new FileWriter(OutFile);
		fw.write(content[0]);
		for (int i = 1; i < content.length; i++) {
			fw.write("\r\n" + content[i]);
		}
		fw.close();
	}
/**
 * 	GET the value  that dosent contain 0
 * @param inFile
 * @param OutFile
 * @throws IOException
 */
	public void GetNoZero(String inFile, String OutFile) throws IOException {
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(OutFile);
		for (int i = 0; i < content.length; i++) {

			if (Math.abs(Double.parseDouble(content[i][0]) - 0) > 0.0001
					&& Math.abs(Double.parseDouble(content[i][1])) - 0 > 0.0001) {
				fw.write(content[i][0] + "," + content[i][1] + "\r\n");
			}
		}
		fw.close();
	}
	/**
	 * 	Get the value that allowable  by  distance  lower than dis to column one
	 * @param dis
	 * @param inFile
	 * @param OutFile
	 * @throws IOException
	 */

	public void GetDisIn(double dis, String inFile, String OutFile) throws IOException {
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(OutFile);
		for (int i = 0; i < content.length; i++) {
			if (Math.abs(Double.parseDouble(content[i][0]) - Double.parseDouble(content[i][1])) < dis) {
				fw.write(content[i][0] + "," + content[i][1] + "\r\n");
			}
		}
		fw.close();
	}
	/**
	 * 		GET the value that between in the given value  (split by -)
	 * @param space
	 * @param inFile
	 * @param OutFile
	 * @throws IOException
	 */

	public void GetSpace(String space, String inFile, String OutFile) throws IOException {
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(OutFile);
		String tempt[] = space.split("-");
		double spaceD[] = { Double.parseDouble(tempt[0].trim()), Double.parseDouble(tempt[1].trim()) };

		for (int i = 0; i < content.length; i++) {
			if (Double.parseDouble(content[i][0]) > spaceD[0] && Double.parseDouble(content[i][1]) > spaceD[0]
					&& Double.parseDouble(content[i][0]) < spaceD[1] && Double.parseDouble(content[i][1]) < spaceD[1]) {
				fw.write(content[i][0] + "," + content[i][1] + "\r\n");
			}
		}
		fw.close();
	}
/**
 * 		GET the value which  allowable by persantage of the first column
 * @param persantage
 * @param inFile
 * @param OutFile
 * @throws IOException
 */
	public void GetDisPersantage(double persantage, String inFile, String OutFile) throws IOException {
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(OutFile);
		for (int i = 0; i < content.length; i++) {
			if (Math.abs(Double.parseDouble(content[i][0]) - Double.parseDouble(content[i][1])) < persantage
					* Double.parseDouble(content[i][0])) {
				fw.write(content[i][0] + "," + content[i][1] + "\r\n");
			}
		}
		fw.close();
	}
/**
 * 			GET the difference of  two column 
 * @param inFile
 * @param OutFile
 * @throws IOException
 */
	public void GetDifference(String inFile, String outFile) throws IOException {
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(outFile);
		for (int i = 0; i < content.length; i++) {
			String tempt = df.format(Double.parseDouble(content[i][0]) - Double.parseDouble(content[i][1]));
			fw.write(content[i][0] + "," + content[i][1] + "," + tempt + "\r\n");
		}
		fw.close();
	}
	/**
	 * 	GET  value by setting fomula
	 * @param inFile
	 * @param outFile
	 * @throws IOException
	 */
	public void GetDifferenceByFomula(String inFile , String outFile) throws IOException{
		String[][] content = new AtFileReader(inFile).getCsv();
		FileWriter fw = new FileWriter(outFile);
		for (int i = 0; i < content.length; i++) {
			String tempt = df.format(Double.parseDouble(content[i][0]) - fomula(Double.parseDouble(content[i][1])));
			fw.write(content[i][0] + "," + content[i][1] + "," + tempt + "\r\n");
		}
		fw.close();
		
	}
	private double fomula(double x){
		return 0.981*x + 2.421;
	}
	
	

}
