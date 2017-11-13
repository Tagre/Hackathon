import java.util.ArrayList;

import data.LectureData;

public class main {
	
	public static void main(String[] args){
	LectureData ld = new LectureData();
	ld.Parsing();
	//System.out.println(ld.dataTrain.get(1));
	ArrayList<String> champs = new ArrayList<String>();
	champs.add("Entry");
	champs.add("Cross-reference (ProDom)");
	ld.RowSelection(champs);
	System.out.println(ld.dataTrainFinal.get(0).size());
	}
}
