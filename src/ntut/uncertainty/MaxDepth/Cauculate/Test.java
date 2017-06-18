package ntut.uncertainty.MaxDepth.Cauculate;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.math3.distribution.NormalDistribution;

import ntut.uncertainty.Property.AtFileReader;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String fileAdd = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\";
		String or[][] = new AtFileReader(fileAdd+"29.asc").getStr(6, 0);
		String test[][] = new AtFileReader(fileAdd+"max").getStr(6, 0);
		
		System.out.println(or.length + "   " + or[0].length);
		System.out.println(test.length + "   " + test[0].length);
	}

}
