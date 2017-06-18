package ntut.uncertainty.MakeError.GEV_Distribute;

import jdistlib.Normal;
import jdistlib.evd.GEV;

public class GEVRandom {
	private double content[];
	private double shape;
	private double location;
	private double scale;
	
// GET weibull Dis in R project  when shapParameter <0
	public GEVRandom(double location, double scale, double shape) {
		this.shape = shape;
		this.location = location;
		this.scale = scale;
	}

	public void symmetryData(double location) {
		for (int i = 0; i < content.length; i++) {
			this.content[i] = this.content[i] + 2 * (location - content[i]);
		}
	}

	public double[] getRandom(int Num){
		if (Math.abs(shape) < 0.3) {
			this.content = new Normal(location, scale).random(Num);
		} else if (shape > 0) {
			this.content = new GEV(location, scale, -1 * shape).random(Num);
			symmetryData(location);
		} else {
			this.content = new GEV(location, scale, shape).random(Num);
		}
		return this.content;
	}

}
