package ntut.uncertainty.MakeError.GEV_Distribute;

import org.apache.commons.math3.special.Gamma;

import ntut.uncertainty.Property.AtArrayFunction;

public class GEVLmoment extends Lmoment {
	private double[] content;
	private double lMoment1;
	private double lMoment2;
	private double lMoment3;
	private double lMoment4;
	private double cParameter;
	private double shapeParameter;
	private double scaleParameter;
	private double locationParameter;

	/**
	 * 
	 * @param the
	 *            value which is sorted
	 */

	public GEVLmoment(double[] content) {
		this.content = content;
		this.lMoment1 = getMoment1(this.content);
		this.lMoment2 = getMoment2(this.content);
		this.lMoment3 = getMoment3(this.content);
		this.lMoment4 = getMoment4(this.content);
		this.cParameter = setCParameter();
		this.shapeParameter = setShapeParameter();
		this.scaleParameter = setScaleParameter();
		this.locationParameter = setLocationParameter();
	}

	private double setShapeParameter() {
		double first = 7.8590 * this.cParameter;
		double second = 2.9554 * Math.pow(this.cParameter, 2);
		return first + second;
	}
	private double setScaleParameter(){
		double top = this.lMoment2*this.shapeParameter;
		double base = Gamma.gamma(1+this.shapeParameter) * (1-Math.pow(2,this.shapeParameter*-1));
		return top/base;
	}
	private double setLocationParameter(){
		double first = this.scaleParameter/this.shapeParameter;
		double second = Gamma.gamma(1+this.shapeParameter) - 1;
		return this.lMoment1 + first*second;
	}

	private double setCParameter() {
		double first = 2 * lMoment2 / (lMoment3 + 3 * lMoment2);
		double second = Math.log(2) / Math.log(3);
		return first - second;
	}
	
	public double getShapeParameter(){
		return this.shapeParameter;
	}
	public double getLocationParameter(){
		return this.locationParameter;
	}
	public double getScaleParameter(){
		return this.scaleParameter;
	}

}
