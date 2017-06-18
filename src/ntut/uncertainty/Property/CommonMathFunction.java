package ntut.uncertainty.Property;

public class CommonMathFunction {

	public double BinomialCoefficient(int top, int base) {
		/**
		 * C take n form k
		 * 
		 * top: C base
		 */
		int disF = Factorial(top - base);
		if (top == 0) {
			return 0;
		} else if (base == 0) {
			return 1;
		} else if (disF == 0) {
			return 1;
		} else {
			int topF = Factorial(top);
			int baseF = Factorial(base);

			return (topF / baseF / disF);
		}
	}

	public int Factorial(int n) {
		/**
		 * n!
		 */
		int K = 1;
		for (int i = n; i > 0; i--) {
			K = K * i;
		}
		return K;
	}

}
