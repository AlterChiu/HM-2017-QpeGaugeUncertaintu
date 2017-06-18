package ntut.uncertainty.MakeError.GEV_Distribute;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import ntut.uncertainty.Property.CommonMathFunction;

public class Lmoment {
	/**
	 * LmomentFuntion used by extends
	 */
	private static CommonMathFunction cm = new CommonMathFunction();

	protected double getMoment1(double[] content) {
		DescriptiveStatistics DS = new DescriptiveStatistics(content);
		return DS.getMean();
	}

	protected double getMoment2(double[] content) {
		int n = content.length;
		double sum = 0;
		for (int i = 0; i < n; i++) {
			double temp = (cm.BinomialCoefficient(i, 1) - cm.BinomialCoefficient(n - i - 1, 1)) * content[i];
			sum = sum + temp;
		}
		return 1 / (cm.BinomialCoefficient(n, 2) * 2) * sum;
	}

	protected double getMoment3(double[] content) {
		int n = content.length;
		double sum = 0;
		for (int i = 0; i < n; i++) {
			double temp = (cm.BinomialCoefficient(i, 2)
					- 2 * cm.BinomialCoefficient(i, 1) * cm.BinomialCoefficient(n - i - 1, 1)
					+ cm.BinomialCoefficient(n - i - 1, 2)) * content[i];
			sum = sum + temp;
		}
		return 1 / (cm.BinomialCoefficient(n, 3) * 3) * sum;
	}

	protected double getMoment4(double[] content) {
		int n = content.length;
		double sum = 0;
		for (int i = 0; i < content.length; i++) {
			double temp = (cm.BinomialCoefficient(i, 3)
					- 3 * cm.BinomialCoefficient(i, 2) * cm.BinomialCoefficient(n - i - 1, 1)
					+ 3 * cm.BinomialCoefficient(i, 1) * cm.BinomialCoefficient(n - i - 1, 2)
					- cm.BinomialCoefficient(n - i - 1, 3)) * content[i];
			sum = sum + temp;
		}
		return 1 / (4 * cm.BinomialCoefficient(n, 4)) * sum;
	}

}
