package ntut.uncertainty.Figure;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FigureInExcel {
	private int startColume = 0;
	private int startRow = 0;

	public void FigureInExcelStart(String[][] content, String outLocate, String modelLocate ) throws JXLException, IOException {

		Workbook book = Workbook.getWorkbook(new File(modelLocate));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(outLocate), book);
		WritableSheet writeSheet = writeBook.getSheet(0);

		
		for (int i = startRow; i < content.length; i++) {
			for (int j = startColume; j < content[i].length; j++) {
				Number num = new Number(j, i, Double.parseDouble(content[i][j]));
				writeSheet.addCell(num);
			}
		}
		writeBook.write();
		writeBook.close();
	}

	public void FigureInExcelStart(int[][] content, String outLocate, String modelLocate) throws JXLException, IOException {

		Workbook book = Workbook.getWorkbook(new File(modelLocate));
		WritableWorkbook writeBook = Workbook.createWorkbook(new File(outLocate), book);
		WritableSheet writeSheet = writeBook.getSheet(0);
		
		for (int i = startRow; i < content.length; i++) {
			for (int j = startColume; j < content[i].length; j++) {
				Number num = new Number(j, i, content[i][j]);
				writeSheet.addCell(num);
			}
		}
		writeBook.write();
		writeBook.close();
	}

	public void setStarPoint(int row, int colume) {
		this.startColume = colume;
		this.startRow = row;
	}

}
