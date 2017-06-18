package ntut.uncertainty.MakeError.RadarQpeGauge;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import jdistlib.evd.GEV;
import net.sf.javaml.utils.GammaFunction;
import ntut.uncertainty.MakeError.GEV_Distribute.GEVLmoment;
import ntut.uncertainty.MakeError.GEV_Distribute.GEVRandom;
import ntut.uncertainty.MakeError.GEV_Distribute.GEVStatistics;
import ntut.uncertainty.MakeError.GEV_Distribute.ShapeK;
import ntut.uncertainty.Property.AtArrayFunction;
import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.AtFileWriter;

public class test  {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	/**  
	    create the different file by spacing
	*/
		/**
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\";
		String[][] content = new AtFileReader(fileAdd+"tempt_disPD").getCsv();
		//String[][] sorted = new AtArrayFunction(content).sortArryaByOrder(1);
		new RainfallError(fileAdd+"tempt_disPD").CreateFileList("C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\P50\\Distribute");
		
//*/
   
		/**
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\";
		String[][] content = new AtFileReader(fileAdd+"tempt_disPD").getCsv();
		double[] gevValue = new AtArrayFunction(content).getColumnByOrderInDouble(2);
		GEVStatistics gevSta = new GEVStatistics(gevValue);
		
		double  random[] = gevSta.getRandom(50);
		
		for(double d :random){
		System.out.println(d);
		}
		*/
		
	
		//String[] fileList = new File(fileAdd).list();
		
		
		// Checking File and Random test
		/**
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\P50\\";
		String[] fileList = new File(fileAdd).list();
		FileWriter fw = new FileWriter("C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\checkFile");
		
		for(int i=1;i<fileList.length;i++){
			FileWriter rfw = new FileWriter("C:\\Users\\alter\\Desktop\\test\\Uncertainty\\Persentage\\RandomTest\\" +fileList[i]);
			String[][]  content = new AtFileReader(fileAdd+fileList[i]).getCsv();
			double[] gevValue = new AtArrayFunction(content).getColumnByOrderInDouble(2);
			GEVStatistics gev = new GEVStatistics(gevValue);
			double[] random = gev.getRandom(1000);
			System.out.println(fileList[i]);
			
			fw.write(fileList[i] + "\r\n");
			fw.write("Mean\t:\t" + gev.getMean()+"\r\n");
			fw.write("Kurtosis\t:\t" + gev.getKurtosis()+"\r\n");
			fw.write("Skweness\t:\t" + gev.getSkew()+"\r\n");
			fw.write("Shape\t:\t" + gev.getShape()+"\r\n");
			fw.write("Std\t:\t" + gev.getStd()+"\r\n");
			fw.write("\r\n\r\n");
			
			for(double d : random){
				rfw.write(d + "\r\n");
			}
			rfw.close();
		}
		fw.close();
		//**/
		}
        
}
