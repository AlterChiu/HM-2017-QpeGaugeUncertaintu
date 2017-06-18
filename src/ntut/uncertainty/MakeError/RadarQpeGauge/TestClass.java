package ntut.uncertainty.MakeError.RadarQpeGauge;

import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;

public abstract class TestClass implements RealDistribution{
	
	public TestClass(){
		WeibullDistribution wd = new WeibullDistribution(0,1,0);
		wd.getShape();
	}
	

}
