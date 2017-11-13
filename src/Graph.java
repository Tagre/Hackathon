import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe correspondant � un graph de prot�ines
 * @author Tagre
 *
 */

public class Graph {
	private ArrayList<Node> nodes=new ArrayList<Node>();
	
	public Graph(ArrayList<ArrayList<String>> tableau){
		int nbrItem=tableau.size();
		//cr�ation de tout les nodes pr�sent dans le document
		for (ArrayList<String> i:tableau){
			Node nodeI=new Node(i.get(0));
			nodes.add(nodeI);
			
			//ajout des labels si existant
		}
		
		//Ajout des liens
		for(int i=0;i<nbrItem-1;i++){
			Node nodeI=nodes.get(i);
			//ajout du node avec/sans label **A FAIRE**
			for(int j=i+1;j<nbrItem;j++){
				Node nodeJ=nodes.get(j);
				int value=0;
				for(int k=4;k<tableau.get(0).size();k++){
					String contentI=tableau.get(i).get(k);
					String[] elementsI = contentI.split(";");
					String contentJ=tableau.get(j).get(k);
					String[] elementsJ = contentJ.split(";");
					for(String l:elementsI){
						for(String m:elementsJ){
							if(l.equals(m))
								value++;
						}
					}
				}
				if(value>0){
					nodeI.addLink(nodeJ, value);
					nodeJ.addLink(nodeI, value);
				}
			}
		}
		
	}
	
	/**
	 * Fonction parcourant les noeud ayant un label inconnu
	 * Le parcours se fait sur plusieurs it�rations jusqu'a ce que l'int�gralit� des noeuds sont labelis�s
	 */
	public void majLabelAllNode(){
		ArrayList<Node> bufferNodes=nodes;
		
		//On enl�ve de la liste les noeuds d�j� d�finis ou n'ayant aucun voisin
		for(Node node:bufferNodes){
			if (node.getLabels().size()!=0 || node.getLinks().size()==0)
				bufferNodes.remove(node);
		}
		
		//Diff�rentes it�rations pour d�finir le label de chaque noeud
		int it�=0;//permet de limiter le nombre d'it�rations
		while(bufferNodes.size()>0 && it�<10){
			for(Node node:bufferNodes){
				if(majLabelNode(node))
					bufferNodes.remove(node);
			}
			it�++;
		}
	}
	
	
	/**
	 * Fonction mettant � jour le label(fonction) du node si son environnement (ses voisins) le permettent
	 */
	private boolean majLabelNode(Node node){
		ArrayList<Node> maxima=new ArrayList<Node>();
		Node buffNode;
		int buffValue=0;
		
		//parcours pour chercher le ou les noeuds ayant le poids maximal (stock�s dans la variable "maxima")
		int buffValue2;
		for(int i=0;i<node.getLinks().size();i++){
			if((buffValue2=node.getValues().get(i))>buffValue){
				buffValue=buffValue2;
				maxima.clear();
				maxima.add(node.getLinks().get(i));
			} else if(buffValue2==buffValue){
				maxima.add(node.getLinks().get(i));
			}
		}
		
		//V�rification si les node dans la liste maxima ont au moins un label
		boolean isLabel=false;
		for (Node i : maxima){
			if(i.getLabels().size()!=0){
				isLabel=true;
				break;
			}
		}
		//Si aucun label present return false sinon ajoute tout les label au node �tudi�
		if(!isLabel){
			return false;
		} else{
			for (Node i : maxima){
				for(String j : i.getLabels())
					node.addLabels(j);
			}
		}
		return true;
	}
	
}
