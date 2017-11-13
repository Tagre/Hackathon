package data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LectureData {
	
	static String nomFichierTest = "data-test.tab";
	static String nomFichierTrain = "data-train.tab";
	
	static HashMap<String,Integer> correspondance = new HashMap<String,Integer>();
	
	public ArrayList<ArrayList<String>> dataTrain;
	public ArrayList<ArrayList<String>> dataTest;
	
	public LectureData(){
		correspondance.put("Entry", 0);
		correspondance.put("Entry name", 1);
		correspondance.put("Gene ontology (GO)", 2);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		correspondance.put("Cross-reference (Pfam)", 4);
		correspondance.put("Cross-reference (InterPro)", 5);
		correspondance.put("Cross-reference (CDD)", 6);
		correspondance.put("Cross-reference (PIRSF)", 7);
		correspondance.put("Cross-reference (PRINTS)", 8);
		correspondance.put("Cross-reference (PROSITE)", 9);
		correspondance.put("Cross-reference (ProDom)", 10);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		correspondance.put("Taxonomic lineage (ALL)", 3);
		
	}
	
	public void Parsing(){
		try{
			// Initialisation des tableaux
			dataTrain = new ArrayList<ArrayList<String>>();
			dataTest = new ArrayList<ArrayList<String>>();
		
			// Creation fichier de lecture 
			InputStream ipsTrain = new FileInputStream(nomFichierTrain);
			InputStream ipsTest = new FileInputStream(nomFichierTest);
			if(ipsTrain != null && ipsTest != null){ // test que les fichiers ont bien été trouvé
				InputStreamReader ipsrTrain = new InputStreamReader(ipsTrain);
				InputStreamReader ipsrTest = new InputStreamReader(ipsTest);
				BufferedReader brTrain = new BufferedReader(ipsrTrain);
				BufferedReader brTest = new BufferedReader(ipsrTest);
				String ligne;
				int j;
				ArrayList<ArrayList<String>> lignes = new ArrayList<ArrayList<String>>();
				// data train
				int i = 0;
				while((ligne = brTrain.readLine())!=null){
					j = 0;
					ArrayList<String> simpleList = new ArrayList<String>();
					ligne = ligne.replace(",", ";");
					ligne = ligne.replace("\t", ",");
					String[] elements = ligne.split(",");
					while(j<elements.length){
						simpleList.add(elements[j]);
						j++;
					}
					lignes.add(simpleList);
					i++;
				}
				//System.out.print(lignes.get(1) + "\n");
				//System.out.println(lignes.get(1).get(5));
				int l, k=0;
				for(l=0; l<lignes.get(0).size(); l++){ // parcours sur les colonnes
					//System.out.println("l " + l);
					ArrayList<String> elements = new ArrayList<String>();
					k = 0;
					//System.out.println(lignes.size());
					while(k<lignes.size()){
						//System.out.println("k" + k);
						if(l<lignes.get(k).size()){
							elements.add(lignes.get(k).get(l));
						} else {
							elements.add("");
						}
						k++;
					}
					//System.out.println("k " + k);
					//System.out.println(elements);
					dataTrain.add(elements);
				}
				brTrain.close();
				//data test
				/*while((ligne = brTest.readLine())!=null){
					j = 0;
					ArrayList<String> simpleList = new ArrayList<String>();
					ligne = ligne.replace(",", ";");
					ligne = ligne.replace("\t", ",");
					String[] elements = ligne.split(",");
					while(j<elements.length){
						simpleList.add(elements[j]);
						j++;
					}
					lignes.add(simpleList);
					i++;
				}
				//System.out.print(lignes.get(1) + "\n");
				//System.out.println(lignes.get(1).get(5));
				int l, k=0;
				for(l=0; l<lignes.get(0).size(); l++){ // parcours sur les colonnes
					//System.out.println("l " + l);
					ArrayList<String> elements = new ArrayList<String>();
					k = 0;
					//System.out.println(lignes.size());
					while(k<lignes.size()){
						//System.out.println("k" + k);
						if(l<lignes.get(k).size()){
							elements.add(lignes.get(k).get(l));
						} else {
							elements.add("");
						}
						k++;
					}
					//System.out.println("k " + k);
					//System.out.println(elements);
					dataTest.add(elements);
				}*/
				brTest.close();
			} else {
				System.out.println("Error: impossible to find the files");
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void RowSelection(ArrayList<String> parameters){
		
	}

}
