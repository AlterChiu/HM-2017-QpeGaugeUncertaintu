package ntut.uncertainty.MaxDepth.Cauculate;

import java.util.ArrayList;

public class checking {

	public checking(ArrayList[][] arrayList){
		
		for(int i=0;i<arrayList.length;i++){
			for(int j=0;j<arrayList[i].length;j++){
				if(arrayList[i][j].size()<28){
					System.out.println(i + "    " + j);
				}
			}
		}
		
	}
	
}
