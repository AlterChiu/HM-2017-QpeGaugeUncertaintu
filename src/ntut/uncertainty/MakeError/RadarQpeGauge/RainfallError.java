package ntut.uncertainty.MakeError.RadarQpeGauge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import ntut.uncertainty.MakeError.Function.MergeSort;
import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.AtFileWriter;

public class RainfallError {

	private int maxRow = 50;
	private int maxSpace = 10;
	private int extraSpace = 15;
	private String[][] content;
	TreeMap<Integer, ArrayList<String[]>> treemap = new TreeMap<Integer, ArrayList<String[]>>();

	public RainfallError(String inFile) throws IOException {
		this.content = new AtFileReader(inFile).getCsv();
		this.content = new MergeSort(content, 1).getSortedArray();

		int base = 0;
		int treeCount = 0;
		ArrayList<String[]> arrayList = new ArrayList<String[]>();

		// except for the last section
		for (int i = 0; i < content.length; i++) {
			arrayList.add((String[])content[i]);
			if (Math.abs(Double.parseDouble(content[base][1]) - Double.parseDouble(content[i][1])) >= maxSpace
					|| i - base >= maxRow) {
				treemap.put(treeCount, arrayList);
				arrayList = new ArrayList<String[]>();
				treeCount++;
				base = i;
				System.out.println(base);
			}
		}

		// for the last section
		if (content.length - base <= maxRow) {
			int temp = maxRow + base - content.length;
			for (int i = base ; i >base-temp; i--) {
				arrayList.add(content[i - 1]);
				if (Math.abs(Double.parseDouble(content[base][1]) - Double.parseDouble(content[i][1])) >=extraSpace) {
					break;
				}
			}
			treemap.put(treeCount, arrayList);
		}
	}

	public String[][] getSotedArray() {
		return this.content;
	}

	public void CreateFileList(String outFile) throws IOException {
		new AtFileWriter(content, outFile+".csv").csvWriter();
		for (int i = 0; i < this.treemap.size(); i++) {
			ArrayList<String[]> temp =  this.treemap.get(i) ; 
			new AtFileWriter(temp.parallelStream().toArray(String[][] ::new), outFile + i+".csv").csvWriter();
		}
	}
}
