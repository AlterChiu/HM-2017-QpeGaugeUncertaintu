package ntut.uncertainty.getOriginalData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

import com.google.gson.JsonIOException;

import ntut.uncertainty.Property.FileLocation;
import ntut.uncertainty.Property.PropertyFile;
import ntut.uncertainty.getOriginalData.function.AsciiReader;
import ntut.uncertainty.getOriginalData.function.CSVReader;



public class getOriginalData extends FileLocation {
	private PropertyFile pf;
	private TreeMap<Integer, Integer[]> locationSet;
	private String[] locationId;

	public getOriginalData() throws JsonIOException, IOException, Exception {
		this.pf = new PropertyFile(locateDirection);
		this.locationSet = pf.getLocationSet();
		this.locationId = pf.getLocationID();
	}

	public void getAllDataOriginal(String outLocate) throws JsonIOException, Exception {
		// TODO Auto-generated method stub
		String fileAdd = workDirection;
		// FileWriter fw = new FileWriter(fileAdd + "out.csv");

		String[] dateFile = new File(fileAdd).list();
		FileWriter fw = new FileWriter(outLocate);

		for (int file = 0; file < dateFile.length; file++) {
			if (dateFile[file].indexOf(".") == -1) {
				TreeMap<Integer, String[]> gaugeValue = new CSVReader(fileAdd + dateFile[file] + gaugeDirection,
						locationId).getCsvValue();
				String[][] qpeValue = new AsciiReader(fileAdd + dateFile[file] + "\\", locationSet).getQpeValue();

				for (int i = 0; i < gaugeValue.size(); i++) {
					for (int j = 0; j < gaugeValue.get(i).length; j++) {
						fw.write(gaugeValue.get(i)[j] + "," + qpeValue[j][i] + "\r\n");
					}
				}
			}
		}
		fw.close();
	}

	public void getEachData(String outFile) throws IOException {
		String fileAdd = workDirection;
		// FileWriter fw = new FileWriter(fileAdd + "out.csv");

		String[] dateFile = new File(fileAdd).list();

		for (int file = 0; file < dateFile.length; file++) {
			if (dateFile[file].indexOf(".") == -1) {
				TreeMap<Integer, String[]> gaugeValue = new CSVReader(fileAdd + dateFile[file] + gaugeDirection,
						locationId).getCsvValue();
				String[][] qpeValue = new AsciiReader(fileAdd + dateFile[file] + "\\", locationSet).getQpeValue();

				for (int i = 0; i < gaugeValue.size(); i++) {
					FileWriter fw = new FileWriter(outFile + locationId[i]);
					for (int j = 0; j < gaugeValue.get(i).length; j++) {
						fw.write(gaugeValue.get(i)[j] + "," + qpeValue[j][i] + "\r\n");
					}
					fw.close();
				}

			}
		}

	}
}
