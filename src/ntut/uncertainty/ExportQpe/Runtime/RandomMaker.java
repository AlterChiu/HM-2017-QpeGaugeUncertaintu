package ntut.uncertainty.ExportQpe.Runtime;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomMaker {
	private Random ran = new Random();

	public RandomMaker(){
		ran.setSeed( System.currentTimeMillis());
	}

	public int RandomInt(int start, int end) {
	
		int temptI =  ran.nextInt(end-start) + start;
		return temptI;
	}

	public String RandomDoubleFormate(double start, int end, String format) {
		// (##.##)
		DecimalFormat df = new DecimalFormat(format);
		double temptI = start + (end - start) * ran.nextDouble();
		return df.format(temptI);
	}

	public double RandomDouble(double start, int end) {	
		double temptI = start + (end - start) * ran.nextDouble();
		return temptI;
	}
}
