package ntut.uncertainty.MaxDepth.Cauculate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import ntut.uncertainty.Property.AtArrayFunction;
import ntut.uncertainty.Property.AtFileReader;

public class runTime {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\MaxDeth\\";
		String fileList[] = new File(fileAdd).list();
		String content[][] = new AtFileReader(fileAdd + fileList[0]).getStr(6, 0);
		ArrayList<Double>[][] depthMax = new ArrayList[content.length][content[0].length];
		TreeMap<String, Double>[][] trees = new TreeMap[content.length][content[0].length];
		// set new object
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[0].length; j++) {
				depthMax[i][j] = new ArrayList<Double>();
				trees[i][j] = new TreeMap<String, Double>();
			}
		}
		// out put array
		String outPutNormal[][] = new String[content.length][content[0].length];
		String outPutMax[][] = new String[content.length][content[0].length];
		String outPutMin[][] = new String[content.length][content[0].length];
		String outPutMean[][] = new String[content.length][content[0].length];
		String outPutRed[][] = new String[content.length][content[0].length];
		String outPutYellow[][] = new String[content.length][content[0].length];
		String outPutGreen[][] = new String[content.length][content[0].length];

		// make cube
		for (int i = 0; i < fileList.length; i++) {
			System.out.println(fileList[i]);
			content = new AtFileReader(fileAdd + fileList[i]).getStr(6, 0);
			depthMax = new ListMaker(depthMax, content).getArrayList();
		}

		// check value
		for (int i = 0; i < content.length; i++) {
			for (int j = 1; j < content[i].length; j++) {

				double[] valueList = depthMax[i][j].stream().mapToDouble(Double::doubleValue).toArray();
				// get the normal Stastics value
				if (valueList.length > 0) {
					trees[i][j] = new Stastics(valueList).getTree();

					// normal space
					if (trees[i][j].get("p50") >= 0.9) {
						outPutNormal[i][j] = "0.5";
						outPutRed[i][j] = "1";
						outPutYellow[i][j] = "1";
						outPutGreen[i][j] = "1";
						
					} else if (trees[i][j].get("p50") >= 0.8 || trees[i][j].get("p30") >= 0.9) {
						outPutNormal[i][j] = "0.3";
						outPutRed[i][j] = "-999.9990";
						outPutYellow[i][j] = "1";
						outPutGreen[i][j] = "1";
						
					} else if (trees[i][j].get("p30") >= 0.8) {
						outPutNormal[i][j] = "0.0";
						outPutRed[i][j] = "-999.9990";
						outPutYellow[i][j] = "-999.9990";
						outPutGreen[i][j] = "1";
					} else {
						outPutNormal[i][j] = "-999.9990";
						outPutRed[i][j] = "-999.9990";
						outPutYellow[i][j] = "-999.9990";
						outPutGreen[i][j] = "-999.9990";
					}
				}else{
					outPutNormal[i][j] = "-999.9990";
					outPutRed[i][j] = "-999.9990";
					outPutYellow[i][j] = "-999.9990";
					outPutGreen[i][j] = "-999.9990";
				}

			}
		}

		new FileOut(fileAdd + "normal", outPutNormal);
		new FileOut(fileAdd + "Red", outPutRed);
		new FileOut(fileAdd + "Yellow", outPutYellow);
		new FileOut(fileAdd + "Green",outPutGreen);
	}
}
