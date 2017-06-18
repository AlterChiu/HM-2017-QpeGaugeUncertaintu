package ntut.uncertainty.ExportQpe.Runtime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.TreeMap;

import ntut.uncertainty.Property.AtFileReader;

public class CheckingChangingRating {

	public CheckingChangingRating(String file) throws IOException {
		// TODO Auto-generated method stub
		
		

		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\";
		String original = "original\\";
		String adjusted = "adjusted\\";
		String outPutLocate = "outPut\\";
		

		TreeMap<Integer, TreeMap<String, String>> outPut = new TreeMap<Integer, TreeMap<String, String>>();

		String adjustedFileList[] = new File(fileAdd + adjusted + file).list();
		String originalFileList[] = new File(fileAdd + original + file).list();

		for (int i = 0; i < adjustedFileList.length; i++) {
			TreeMap<String, String> tempTree = new TreeMap<String, String>();
			FileWriter tempFW = new FileWriter(fileAdd+ outPutLocate +adjustedFileList[i]) ;
			String[][] adjustedContent = new AtFileReader(fileAdd + adjusted + file + adjustedFileList[i]).getStr(6, 0);
			String[][] originalContent = new AtFileReader(fileAdd + original + file + originalFileList[i]).getStr(6, 0);

			double maxPercentage = 0;
			double maxDis = 0;

			double sumDis = 0;
			double sumPercentage = 0;

			int num = adjustedContent.length * adjustedContent[0].length;
			for (int line = 0; line < adjustedContent.length; line++) {
				for (int column = 0; column < adjustedContent[line].length; column++) {
					tempFW.write(column+"");
				}
				for (int column = 0; column < adjustedContent[line].length; column++) {
					// Difference between adjusted and original
					double tempDis = Double.parseDouble(adjustedContent[line][column])
							- Double.parseDouble(originalContent[line][column]);
					tempFW.write(estimate(tempDis) + " ");
					// percentage  checking
					if (Double.parseDouble(originalContent[line][column]) > 0.1) {
						double tempPercentage = tempDis / Double.parseDouble(originalContent[line][column]);
						sumPercentage = sumPercentage + tempPercentage;
						if (Math.abs(tempPercentage) > maxPercentage) {
							maxPercentage = tempPercentage;
						}
					}
					// Dis checking
					sumDis = sumDis + tempDis;
					if (Math.abs(tempDis) > maxDis) {
						maxDis = tempDis;
					}
				}
				tempFW.write("\r\n");
			}
			tempFW.close();
			tempTree.put("maxPercentage", maxPercentage + "");
			tempTree.put("maxDis", maxDis + "");
			tempTree.put("meanDis", sumDis / num + "");
			tempTree.put("meanPercentage", sumPercentage / num + "");
			outPut.put(i, tempTree);
		}
		FileWriter fw = new FileWriter(fileAdd + adjusted + "stastics" + file);
		for (int i = 0; i < outPut.size(); i++) {
			fw.write("File" + i + "\r\n");
			fw.write("maxPercentage\t\t   = " + estimate(outPut.get(i).get("maxPercentage")) + "\r\n");
			fw.write("maxDis\t\t           = " + estimate(outPut.get(i).get("maxDis")) + "\r\n");
			fw.write("meanPercentage\t\t   = " +estimate( outPut.get(i).get("meanPercentage")) + "\r\n");
			fw.write("meanDis\t\t           = " + estimate(outPut.get(i).get("meanDis")) + "\r\n");
			fw.write("\r\n");
		}
		fw.close();

	}
	
	private static String estimate(String text){
		 return String.format("%6s",new BigDecimal(text).setScale(1, BigDecimal.ROUND_HALF_UP)+"");
	}
	private static String estimate(double text){
		 return String.format("%6s",new BigDecimal(text).setScale(1, BigDecimal.ROUND_HALF_UP)+"");
	}
}
