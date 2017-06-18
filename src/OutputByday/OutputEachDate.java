package OutputByday;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeMap;

public class OutputEachDate {
	
	public  OutputEachDate(String fileAdd, TreeMap<Integer, String[]> gaugeValue, String[][] qpeValue,String[] locationId)
			throws IOException {
	
		for (int i = 0; i < gaugeValue.size(); i++) {
			FileWriter fw = new FileWriter(fileAdd+"\\"+locationId[i]+".ordinate");
			fw.write(gaugeValue.get(i)[0] + "," + qpeValue[0][i]);
			for (int j = 1; j < gaugeValue.get(i).length; j++) {
				fw.write("\r\n" + gaugeValue.get(i)[j] + "," + qpeValue[j][i]);
			}
			fw.close();
		}
	

	}

}
