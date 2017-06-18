package ntut.uncertainty.ExportQpe.Runtime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import ntut.uncertainty.MakeError.GEV_Distribute.GEVStatistics;
import ntut.uncertainty.Property.AtArrayFunction;
import ntut.uncertainty.Property.AtFileReader;

public class ExportQpeRuntime {

	public ExportQpeRuntime(String gevLocation , String qpeLocation) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub
	
		int RandomNumber = 1000;

		DecimalFormat df = new DecimalFormat("##.#");
		String[] gevFileList = new File( gevLocation).list();
		String[] qpeFileList = new File( qpeLocation).list();
		TreeMap<Integer, TreeMap<String, Double>> gevTree = new TreeMap<Integer, TreeMap<String, Double>>();
		TreeMap<Integer, double[]> gevRandom = new TreeMap<Integer, double[]>();

		// Setting GEV property
		System.out.println("Start GEV Setting");
		for (int i = 0; i < gevFileList.length; i++) {
			System.out.println(gevFileList[i]);
			TreeMap<String, Double> gevTemp = new TreeMap<String, Double>();
			String[][] value = new AtFileReader(gevLocation + gevFileList[i]).getCsv();
			
			double[] qpe = new AtArrayFunction(value).getColumnByOrderInDouble(1);
			double[] gev = new AtArrayFunction(value).getColumnByOrderInDouble(2);
		

			GEVStatistics gevStatistic = new GEVStatistics(gev);
			DescriptiveStatistics DS = new DescriptiveStatistics(qpe);
			gevTemp.put("max", DS.getMax());
			gevTemp.put("min", DS.getMin());
			gevTemp.put("kurtosis", gevStatistic.getKurtosis());
			gevTemp.put("skewness", gevStatistic.getSkew());
			gevTemp.put("shape", gevStatistic.getShape());
			gevTemp.put("location", gevStatistic.getLocation());
			gevTemp.put("mean", gevStatistic.getMean());
			gevTemp.put("std", gevStatistic.getStd());

			gevRandom.put(i, gevStatistic.getRandom(RandomNumber));

			gevTree.put(i, gevTemp);
		}
		
		
		/**  //  GEV statistic output
		FileWriter gevWrite = new FileWriter(fileAdd + "fitting");
		for (int i = 0; i < gevTree.size(); i++) {
			gevWrite.write("file " + i + " \r\n");
			gevWrite.write("kurtosis\t\t     = " + gevTree.get(i).get("kurtosis") + "\r\n");
			gevWrite.write("skewness\t\t  = " + gevTree.get(i).get("skewness") + "\r\n");
			gevWrite.write("shape\t\t           = " + gevTree.get(i).get("shape") + "\r\n");
			gevWrite.write("location\t\t      = " + gevTree.get(i).get("location") + "\r\n");
			gevWrite.write("mean\t\t            = " + gevTree.get(i).get("mean") + "\r\n");
			gevWrite.write("std\t\t                  = " + gevTree.get(i).get("std") + "\r\n");
			gevWrite.write("max\t\t               = " + gevTree.get(i).get("max") + "\r\n");
			gevWrite.write("min\t\t                = " + gevTree.get(i).get("min") + "\r\n");
			gevWrite.write("\r\n");
		}
		gevWrite.close();
		//**/
		
		
		
			// outPut RandomValue to File
			/**
			FileWriter randomValue = new FileWriter(fileAdd + "random" + i);
			for (double d : gevRandom.get(i)) {
				randomValue.write(d + "\r\n");
			}
			randomValue.close();
		}
		gevWrite.close();
        //**/
			
		// loading file text
		for (int i = 0; i < qpeFileList.length; i++) {
			String[] content = new AtFileReader(qpeLocation + qpeFileList[i]).getContain(6, 0);
			String[][] outPut = new AtFileReader(qpeLocation + qpeFileList[i]).getStr();
			RandomMaker random = 	new RandomMaker();
			// loading line text
			for (int k = 0; k < content.length; k++) {
				String[] tempt = content[k].split(" +");
				// loading column text
				for (int j = 0; j < tempt.length; j++) {
					// loading gev setting check bigger than the smallest value
					if (Double.parseDouble(tempt[j]) >= gevTree.get(0).get("min")) {
						// check which section of gev the value is
						for (int g = 0; g < gevTree.size(); g++) {
							if (Double.parseDouble(tempt[j]) >= gevTree.get(g).get("min")
									&& Double.parseDouble(tempt[j]) <= gevTree.get(g).get("max")) {
								// Check the adjusted value that is in the allow

								// make start of gevRandom array
								int base = random .RandomInt(1, RandomNumber);
								String check = df.format(Double.parseDouble(tempt[j]) + gevRandom.get(g)[base]);
								while (Double.parseDouble(check) < 0 || Math.abs(gevRandom.get(g)[base]) > Double.parseDouble(tempt[j]) * 0.5) {
									base++;
									if (base >= RandomNumber)
										base = new RandomMaker().RandomInt(1, RandomNumber);
									check = df.format(Double.parseDouble(tempt[j]) + gevRandom.get(g)[base]);
								}
								tempt[j] = new BigDecimal(check).setScale(1, BigDecimal.ROUND_HALF_UP)+"";
								break;
							}
						}
					}
				}
				outPut[k + 6] = tempt;
	//			System.out.println("File\t" + i + "\tline\t" + k);
			}

			FileWriter fw = new FileWriter(qpeLocation + qpeFileList[i] );
			for (String[] line : outPut) {
				for (String column : line) {
					fw.write(column + " ");
				}
				fw.write("\r\n");
			}
			fw.close();
			System.out.println(" QPE Creating  " + i + "  completed");
		}

	}

}
