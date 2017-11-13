import java.util.ArrayList;

public class MakeLinks implements Runnable {
	
  private ArrayList<Node> N;
  private String threadName;

  public MakeLinks(ArrayList<Node> N, String threadName) {
    this.N = N;
    this.threadName = threadName;
  }

  public void run() {
    for (Node n : N) {
    	// entrer ici le code qui permet de faire des liens
    	// en comparant les caractéristiques
    	// cf Mael
		System.out.println("Ici " + this.threadName);
    }               
  }
  
}