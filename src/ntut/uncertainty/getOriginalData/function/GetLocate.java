package ntut.uncertainty.getOriginalData.function;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import ntut.uncertainty.Property.FileLocation;

public class GetLocate extends FileLocation {
	public GetLocate() throws IOException{
		String fileAdd = rootDirection + "\\Location\\CWBAllGauge_withID.json";
		FileWriter fw = new FileWriter( rootDirection + "\\Location\\CWBAllGauge_withXY.json");
	

		JsonElement locateElement = new JsonParser().parse(new JsonReader(new FileReader(fileAdd)));
		JsonArray locateArray = locateElement.getAsJsonArray();
		JsonArray total = new JsonArray();
		
		for(int i=0;i<locateArray.size();i++){
			JsonObject job = locateArray.get(i).getAsJsonObject();
			Double row = job.get("Row").getAsDouble();
			Double colume =  job.get("Colume").getAsDouble();
			if(row>2518600 && row < 2611100  && colume >125200  && colume <236700){
				job.addProperty("LocateRow", (int)Math.ceil((row-2518600)/500));
				job.addProperty("LocateColume", (int) Math.ceil((colume-125200)/500));
				total.add(job);
			}
		}
		fw.write(new GsonBuilder().setPrettyPrinting().create().toJson(total));
		fw.close();
		
	}
	}


