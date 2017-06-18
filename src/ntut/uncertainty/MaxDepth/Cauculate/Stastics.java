package ntut.uncertainty.MaxDepth.Cauculate;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Stastics {
	private DescriptiveStatistics DS;
	private TreeMap<String, Double> tree = new TreeMap<String, Double>();

	public Stastics(double[] value) {
		int p50 = 0;
		int p30 = 0;
		this.DS = new DescriptiveStatistics(value);
		
		for (int i = 0; i < value.length; i++) {
			if (value[i] > 0.3) {
				p30++;
			}
			if (value[i] > 0.5) {
				p50++;
			}
		}
		tree.put("min", this.DS.getMin());
		tree.put("mean", this.DS.getMean());
		tree.put("max", this.DS.getMax());
		tree.put("p50", p50/30.);
		tree.put("p30", p30/30.);
	}

	public TreeMap<String, Double> getTree() {
		return this.tree;
	}
}
