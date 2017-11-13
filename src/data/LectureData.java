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
	public ArrayList<ArrayList<String>> dataTrainFinal;
	public ArrayList<ArrayList<String>> dataTestFinal;
	
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
		correspondance.put("Cross-reference (SFLD)", 11);
		correspondance.put("Cross-reference (SMART)", 12);
		correspondance.put("Cross-reference (SUPFAM)", 13);
		correspondance.put("Cross-reference (TIGRFAMs)", 14);
		correspondance.put("Cross-reference (HAMAP)", 15);
		correspondance.put("Cross-reference (Gene3D)", 16);
		correspondance.put("Cross-reference (PANTHER)", 17);
	}
	
	public void Parsing(){
		try{
			// Initialisation des tableaux
			dataTrain = new ArrayList<ArrayList<String>>();
			dataTest = new ArrayList<ArrayList<String>>();
		
			// Création fichier de lecture 
			InputStream ipsTrain = new FileInputStream(nomFichierTrain);
			InputStream ipsTest = new FileInputStream(nomFichierTest);
			
			if (ipsTrain != null && ipsTest != null) { // teste que les fichiers ont bien été trouvés
				InputStreamReader ipsrTrain = new InputStreamReader(ipsTrain);
				InputStreamReader ipsrTest = new InputStreamReader(ipsTest);
				BufferedReader brTrain = new BufferedReader(ipsrTrain);
				BufferedReader brTest = new BufferedReader(ipsrTest);
				String ligne;
				ArrayList<ArrayList<String>> lignes = new ArrayList<ArrayList<String>>();
				
				// data train
				int j = 0;
				while ((ligne = brTrain.readLine()) != null) {
					j = 0;
					ArrayList<String> simpleList = new ArrayList<String>();
					ligne = ligne.replace(",", ";");
					ligne = ligne.replace("\t", ",");
					String[] elements = ligne.split(",");
					while (j < elements.length) {
						simpleList.add(elements[j]);
						j++;
					}
					lignes.add(simpleList);
				}
				
				int l, k=0;
				for (l=0; l<lignes.get(0).size(); l++) { // parcours sur les colonnes
					
					ArrayList<String> elements = new ArrayList<String>();
					k = 0;
					
					while (k<lignes.size()) {						
						if (l<lignes.get(k).size()) {
							elements.add(lignes.get(k).get(l));
						} else {
							elements.add("");
						}
						k++;
					}
					
					dataTrain.add(elements);
				}
				brTrain.close();
				
				
				//data test
				
				/*while ((ligne = brTest.readLine()) != null) {
					j = 0;						 * 
					ArrayList<String> simpleList = new ArrayList<String>();
					ligne = ligne.replace(",", ";");
					ligne = ligne.replace("\t", ",");
					String[] elements = ligne.split(",");
					while (j<elements.length) {
						simpleList.add(elements[j]);
						j++;
					}
					lignes.add(simpleList);
				}
				 
				int l, k=0;
				for (l=0; l<lignes.get(0).size(); l++) { // parcours sur les colonnes
					 
					ArrayList<String> elements = new ArrayList<String>();
					k = 0;
					 
					while (k<lignes.size()) {
						if (l<lignes.get(k).size()) {
							elements.add(lignes.get(k).get(l));
						} else {
							elements.add("");
						}
						k++;
					}					 
					dataTest.add(elements);
				}
				*/
				
				brTest.close();
			} else {
				System.out.println("Error: impossible to find the files");
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void RowSelection(ArrayList<String> parameters) {
		ArrayList<Integer> parametersInt = new ArrayList<Integer>();
		
		for (int i=0; i<parameters.size(); i++) {
			parametersInt.add(correspondance.get(parameters.get(i)));
		}
		
		dataTrainFinal = new ArrayList<ArrayList<String>>();
		dataTestFinal = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> dataTrainInter = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> dataTestInter = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<parametersInt.size(); i++) {
			dataTrainInter.add(dataTrain.get(parametersInt.get(i)));
			//dataTestInter.add(dataTest.get(parametersInt.get(i)));
		}
		
		// inversion train
		for (int j=0; j<dataTrainInter.get(0).size(); j++) { // parcours colonne
			ArrayList<String> liste = new ArrayList<String>();
			for (int i=0; i<dataTrainInter.size(); i++) { // parcours ligne
				liste.add(dataTrainInter.get(i).get(j));
			}
			dataTrainFinal.add(liste);
		}
		
		// inversion test
		/*for (int j=0; j<dataTestInter.get(0).size(); j++) { // parcours colonne
			ArrayList<String> liste = new ArrayList<String>();
			for (int i=0; i<dataTestInter.size(); i++) { // parcours ligne
				liste.add(dataTestInter.get(i).get(j));
			}
			dataTestFinal.add(liste);
		}*/
	}

}
