package ntut.uncertainty.getOriginalData.function;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ntut.uncertainty.Property.AtFileReader;
import ntut.uncertainty.Property.FileLocation;

public class GetNameXY extends FileLocation {
	public GetNameXY() throws IOException {
		String name[] = new AtFileReader(rootDirection + "\\Location\\Locations.xml", "UTF-8").getContain();
		String id[] = new AtFileReader(rootDirection + "\\Location\\CWBAllGauge_adjust").getContain();

		FileWriter fw = new FileWriter(rootDirection + "\\Location\\CWBAllGauge_withID.json");
		JsonArray total = new JsonArray();

		for (int i = 0; i < id.length; i++) {
			int count = 0;
			for (int j = 0; j < name.length; j++) {
				String tempt[] = name[j].split("name");
				if (name[j].indexOf(id[i]) != -1 && tempt.length >= 2 && count == 0) {
					count++;
					JsonObject job = new JsonObject();
					job.addProperty("id", id[i]);
					job.addProperty("name", tempt[1].substring(2, tempt[1].length() - 2));
					for (int k = j; k < j + 5; k++) {
						if (name[k].contains("<x>")) {
							job.addProperty("Colume", name[k].split("<x>")[1].split("</x>")[0]);
						}
						if (name[k].contains("<y>")) {
							job.addProperty("Row", name[k].split("<y>")[1].split("</y>")[0]);
						}
					}
					total.add(job);
				}
			}
		}
		fw.write(new GsonBuilder().setPrettyPrinting().create().toJson(total));
		fw.close();
	}
}
