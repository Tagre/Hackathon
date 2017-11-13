import java.util.ArrayList;
import java.util.HashMap;
/**
 * Classe correspondant à un noeud du graph
 * @author Tagre
 *
 */
public class Node {
	

	private String name;
	private ArrayList<String> labels;
	private ArrayList<Node> links= new ArrayList<Node>();
	private ArrayList<Integer> values=new ArrayList<Integer>();
	
	public Node(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getLabels() {
		return labels;
	}

	public void addLabels(String label) {
		labels.add(label);
	}

	public ArrayList<Node> getLinks() {
		return links;
	}

	public ArrayList<Integer> getValues() {
		return values;
	}
	
	public void addLink(Node node, int value){
		links.add(node);
		values.add(value);
	}
	
	
	
}
