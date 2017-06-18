package ntut.uncertainty.ExportQpe.Runtime;

import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import ntut.uncertainty.Property.FileFunction;

public class Runtime {
	

	public static void main(String[] args) throws IOException, InterruptedException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub

		String qpeLocation09 = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\adjusted\\QPE092609_092810\\";
		String gevLcation = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\adjusted\\GEV\\";
		
		String oldFile = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\original\\QPE092609_092810\\";
		String newFile = "C:\\Users\\alter\\Desktop\\test\\Uncertainty\\QPESUMS\\adjusted\\";

		

			new FileFunction().moveFolderWithOutDel(oldFile, newFile + "QPE092609_092810\\");

			new ExportQpeRuntime(gevLcation,qpeLocation09);
			// new CheckingChangingRating(qpeLocation09);
			new FileFunction().moveFolderWithOutDel(newFile + "QPE092609_092810\\", newFile + "QPEAD0"  + "\\");
		
	}
}
