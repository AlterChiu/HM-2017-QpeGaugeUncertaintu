package ntut.uncertainty.MaxDepth.Cauculate;

import java.util.ArrayList;

public class ListMaker {
	private ArrayList<Double>[][] arrayList;

	public ListMaker(ArrayList<Double>[][] arrayList , String[][] content){
		this.arrayList = arrayList;
		System.out.println(content.length+"   " +content[0].length);
		for(int i=0;i<content.length;i++){
			for(int j=0;j<content[i].length;j++){
				if(!content[i][j].equals("-999.9990") && !content[i][j].equals("")){
				this.arrayList[i][j].add(Double.parseDouble(content[i][j]));
				}
			}
		}
	}

	public ArrayList<Double>[][] getArrayList() {
		return this.arrayList;
	}

}
