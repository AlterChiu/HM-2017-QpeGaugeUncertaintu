package ntut.uncertainty.Runtimes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeMap;

import com.google.gson.JsonIOException;

import ntut.uncertainty.Figure.FigureInExcel;
import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;
import ntut.uncertainty.Property.PropertyFile;
import ntut.uncertainty.getOriginalData.getOriginalData;
import ntut.uncertainty.getOriginalData.Adjust.OriginalData;
import ntut.uncertainty.getOriginalData.function.CSVReader;

public class main extends FileLocation{

	public static void main(String[] args) throws JsonIOException, Exception {
		// TODO Auto-generated method stub
		
		
		/*/  Gauge  difference to QPE
		String jsonLocation = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Location.json";
		String gaugeValueLocation = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\originalData\\hour\\0605\\Gauge\\061200AhnanGauge.csv";
		String qpeHourlyLocation = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\originalData\\hour\\0605\\QPEAD\\";
		String outPutFileLocation = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\originalData\\hour\\0605\\out";
		String qpeFileList[] = new File(qpeHourlyLocation).list();
		
		PropertyFile property = new PropertyFile(jsonLocation);
		String stationIDArray[] = property.getLocationID();
		ArrayList<String[]> outPutContent = new ArrayList<String[]>();
		
		TreeMap<Integer,Integer[]> stationCordinate = property.getLocationSet(); 
		
		TreeMap<Integer,String[]> eachGaugeValue = new CSVReader(gaugeValueLocation , stationIDArray).getCsvValue();
		
		for(int i=0;i<qpeFileList.length;i++){
			String content[][]  = new AtFileReader(qpeHourlyLocation + qpeFileList[i]).getStr(6,1);
			for(int station=0;station<eachGaugeValue.size();station++){
				Integer cordinate[] = stationCordinate.get(station);
				String[] temp = new String[2];
				temp[0] = eachGaugeValue.get(station)[i]+"";
				temp[1] = content[cordinate[0]][cordinate[1]];
				outPutContent.add(temp);
			}
		}
		String[][] outPutArray = outPutContent.parallelStream().toArray(String[][]::new);
		FileWriter fw = new FileWriter(outPutFileLocation);
		for(int i=0;i<outPutArray.length;i++){
			fw.write(outPutArray[i][0] + "," + outPutArray[i][1]);
			if(i!=outPutArray.length-1){
				fw.write("\r\n");
			}
		}
		fw.close();
		
		//**/
		
		
	/**	//                 Get All station Ordinate data
		new getOriginalData().getAllDataOriginal(workDirection + "\\OrdinateData.csv");
		new OriginalData().GetWithout("-999", workDirection + "\\OrdinateData.csv", workDirection + "\\OrdinateData.Expect999");
	//*/	
		
		
		
		
		/**                                Get Each station OrdinateData to selected location 
		String fileAdd = root0927Direction + "\\EachStation\\";
		new getOriginalData().getEachData(fileAdd);;
		**/
		
		/**                                Get Dis Under xx with All Ordinate Data
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Fig\\";
		OriginalData od = new OriginalData();
		new getOriginalData().getAllDataOriginal(fileAdd+"tempt");
		od.GetWithout("-999", fileAdd+"tempt",  fileAdd+"tempt");
		od.GetDisIn(5, fileAdd+"tempt",  fileAdd+"tempt_disP");
		
		String content[][] = new AtFileReader( fileAdd+"tempt_dis").getCsv();
		new FigureInExcel(content, fileAdd+"tempt_disP_Fig.csv",excelFigureModelDirection);
		//String[][] content = new  AtFileReader( fileAdd+"tempt").getCsv();
	 //  new FigureInExcel(content, fileAdd+"tempt_Fig.csv",excelFigureModelDirection);
//**/
		
		
		
		
		///**                                Get Dis Under xx% with All Ordinate Data
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\";
		OriginalData od = new OriginalData();
	//	new getOriginalData().getAllDataOriginal(fileAdd+"tempt");
		od.GetWithout("-999", fileAdd+"tempt",  fileAdd+"tempt");
		od.GetWithout(",0", fileAdd+"tempt",  fileAdd+"tempt");
		od.GetDisIn(5, fileAdd+"tempt",  fileAdd+"tempt_disP");
		od.GetDifferenceByFomula(fileAdd+"tempt_disP",  fileAdd+"tempt_disPD");
		
		//String content[][] = new AtFileReader( fileAdd+"tempt_disP").getCsv(); 
		//new FigureInExcel(content, fileAdd+"tempt_disP_Fig.csv",excelFigureModelDirection);
		//String[][] content = new  AtFileReader( fileAdd+"tempt").getCsv();
	 //  new FigureInExcel(content, fileAdd+"tempt_Fig.csv",excelFigureModelDirection);
//**/
	
	}

}
