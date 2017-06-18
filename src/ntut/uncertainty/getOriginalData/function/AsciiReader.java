package ntut.uncertainty.getOriginalData.function;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;

public class AsciiReader extends FileLocation {
	private String[][] value;

	public AsciiReader(String fileAdd, TreeMap<Integer, Integer[]> locationSet) throws IOException {

		String[] qpeFileName = new File(fileAdd + qpeDirection).list();
		this.value = new String[qpeFileName.length][locationSet.size()];

		for (int row = 0; row < value.length; row++) {
			String QpeContent[][] = new AtFileReader(fileAdd + qpeDirection + qpeFileName[row]).getStr();

			for (int colume = 0; colume < value[0].length; colume++) {
				Integer[] cordinate = locationSet.get(colume);

				this.value[row][colume] = QpeContent[cordinate[0] + 5][cordinate[1] - 1];
			}

		}
	}

	public String[][] getQpeValue() {
		return value;
	}

}
