package ntut.uncertainty.MaxDepth.Cauculate;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class FileOut {
	public FileOut(String fileAdd, String[][] content) throws IOException {
		FileWriter fw = new FileWriter(fileAdd);
		fw.write("ncols             1145\r\n" + "nrows              518\r\n" + "xllcorner        154862.500\r\n"
				+ "yllcorner       2545474.500\r\n" + "cellsize             40.000\r\n"
				+ "NODATA_value       -999.9990\r\n");

		for (String[] line : content) {
			for (String str : line) {
				if (str != null && !str.equals("")) {
					String temp = new BigDecimal(str).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
					fw.write(String.format("%14s", temp));
				}
			}
			fw.write("\r\n");
		}
		fw.close();
		System.out.println(fileAdd);
	}
}
