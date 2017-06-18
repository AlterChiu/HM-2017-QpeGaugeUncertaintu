package ntut.uncertainty.Property;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class PropertyFile {
	
	public static TreeMap<Integer,Integer[]> locationSet = new TreeMap<Integer,Integer[]>();
	
	public static String idArray[] ;
	public static JsonArray locateArray;
	public static JsonElement locateElement;
	
	public PropertyFile(String fileAdd) throws JsonIOException, Exception, IOException{
		
		locateElement = new JsonParser().parse(new JsonReader(new FileReader(fileAdd)));
		this.locateArray = locateElement.getAsJsonArray();
		this. idArray = new String[locateArray.size()];
		for(int i=0;i<locateArray.size();i++){
			
			String id = locateArray.get(i).getAsJsonObject().get("id").getAsString();
			idArray[i]=id;
			Integer[] cordinate = {
				(int) Math.floor(locateArray.get(i).getAsJsonObject().get("LocateRow").getAsInt()),				
				(int) Math.floor(locateArray.get(i).getAsJsonObject().get("LocateColume").getAsInt())}; 
			
			locationSet.put(i, cordinate);
		}
	//	Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		
	//	JsonArray locationDetail = new JsonArray(fileAdd+"locate.json");
	}
	
	public TreeMap<Integer,Integer[]> getLocationSet (){
		return locationSet;
	}
	public String[] getLocationID(){
		return idArray;
	}
}
