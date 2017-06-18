package ntut.uncertainty.getOriginalData.function;

import java.io.IOException;
import java.util.TreeMap;

import ntut.uncertainty.Property.AtFileReader;

public class CSVReader {
	private TreeMap<Integer, String[]> stationValue = new TreeMap<Integer, String[]>();

	public CSVReader(String fileAdd, String[] idArray) throws IOException {
		String content[][] = new AtFileReader(fileAdd).getCsv();
		for (int id = 0; id < idArray.length; id++) {
			
			for (int colume = 0; colume < content[0].length; colume++) {
				if (idArray[id].equals(content[0][colume])) {
					String value[] = new String[content.length - 2];
					for (int row = 2; row < content.length; row++) {
						value[row - 2] = content[row][colume];
					}
					stationValue.put(id, value);
				}
			}
		}
	}

	public TreeMap<Integer, String[]> getCsvValue(){
		return stationValue;
	}
}
