package ntut.uncertainty.MakeError.GEV_Distribute;

import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import jdistlib.evd.GEV;

public class GEVStatistics extends ShapeK {
	/**
	 * useing the taipie universe CH8
	 * 
	 * the K here is over to the Gamma in JLIBDIS
	 * 
	 * the K in R is equal to the wiki GEV
	 **/

	private double K;
	private double location;
	private double std;
	private double skew;
	private double kurtosis;
	private DescriptiveStatistics DS;

	public GEVStatistics(double content[]) {
		this.DS = new DescriptiveStatistics(content);
		this.std = DS.getStandardDeviation();
		this.skew = DS.getSkewness();
		this.kurtosis = DS.getKurtosis();

		// GET weibull Dis   shapParameter must > 0
		if (this.skew > 0) {
			this.K = ShapeK(this.skew, this.kurtosis);
		} else {
			this.K = ShapeK(this.skew * -1, this.kurtosis) * -1;
		}

	}

	public double getShape() {
		return this.K;
	}

	public double getLocation() {
		return this.location;
	}

	public double[] getRandom(int num) {
		return new GEVRandom(this.DS.getMean(), this.std, this.K).getRandom(num);

	}

	public double getMean() {
		return this.DS.getMean();
	}

	public double getStd() {
		return this.DS.getStandardDeviation();
	}

	public double getMedium() {
		double[] sorted = this.DS.getSortedValues();
		return sorted[sorted.length / 2];
	}

	public double getSkew() {
		return this.skew;
	}

	public double getKurtosis() {
		return this.kurtosis;
	}

	public double getMax() {
		return this.DS.getMax();
	}

	public double getMin() {
		return this.DS.getMin();
	}
}
