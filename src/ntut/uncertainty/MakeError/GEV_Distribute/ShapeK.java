package ntut.uncertainty.MakeError.GEV_Distribute;

import org.apache.commons.math3.special.Gamma;

public class ShapeK {
	private double tempK = 0;

	public double ShapeK(double cs, double kurtosis) {
		double K = 0;
		double minK=0;
		double checkMin = 100;
		
		
			for (int i = 1; i <= 1000000; i++) {
				this.tempK =   i * 0.00001;
				double tempCs = SkewAt(this.tempK);
				double tempKur = KurtosisAt(this.tempK);
				double check = Math.abs(tempCs - cs) * Math.abs(tempKur - kurtosis);
				
				if(checkMin > check) {
					checkMin = check;
					minK = tempK;
				}
				
				if (check < .000000001) {
					K = tempK;
					break;
				}else{
					K = minK;
				}
			}
		
		return 1/ K;
	}
	public double KurtosisAt(double K) {
		this.tempK = K;
		double tempKurT = -3*gammaF(1,4.) + 6*gammaF(2)*gammaF(1,2.)-4*gammaF(3)*gammaF(1)+gammaF(4);
		double tempKurB = Math.pow(gammaF(2)-gammaF(1,2.),2);
		double tempKur = tempKurT / tempKurB;
	
		return tempKur;
	}

	public double SkewAt(double K) {
		this.tempK = K;
		double tempCsT = 2*gammaF(1,3) - 3*gammaF(2)*gammaF(1)+gammaF(3);
		double tempCsB = Math.pow( gammaF(2) - gammaF(1,2.) , 3/2);
		double tempCs = tempCsT / tempCsB;
		
		return tempCs;
	}
	
	
/**
	public double KurtosisAt(double K) {
		this.tempK = K;
		double tempKurT = gammaF(4) - 4 * gammaF(3) * gammaF(1) + 6 * gammaF(2) * Math.pow(gammaF(1), 2)
				- 3 * Math.pow(gammaF(1), 4);
		double tempKurB = Math.pow(gammaF(2) - Math.pow(gammaF(1), 2), 2);
		double tempKur = tempKurT / tempKurB - 3;
	
		return tempKur;
	}

	public double SkewAt(double K) {
		this.tempK = K;

		double tempCsT = gammaF(3) - 3 * gammaF(2) * gammaF(1) + 2 * Math.pow(gammaF(1), 3);
		double tempCsB = Math.pow(gammaF(2) - Math.pow(gammaF(1), 2), 3 / 2);
		double tempCs = tempCsT / tempCsB;
		if(this.tempK<0){
			tempCs = tempCs*-1;
		}
		return tempCs;
	}
*/
	private double gammaF(int n) {
		return Gamma.gamma(1+ 1.*n/(this.tempK));
		//return Gamma.gamma(1 - n * (tempK));   //GEV
	}
	private double gammaF(int n,double pow) {
		return Math.pow(Gamma.gamma(1+ 1.*n/(this.tempK)) , pow);
		//return Gamma.gamma(1 - n * (tempK));   //GEV
	}
}
