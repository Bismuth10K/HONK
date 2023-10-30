import java.util.ArrayList;
import java.util.HashMap;

abstract class Tamagotchi {
	private final Statistique nrj;
	private final Statistique sat;
	private final Statistique rep;
	private final Statistique hyg;
	private final Sante vie;
	private final Sante bhr;
	private final Poids poi;
	HashMap<String, ArrayList<String>> listeActions = new HashMap<String, ArrayList<String>>();
	private final int XP = 0;
	// private 2DImage sprite;
	
	public Tamagotchi(int maxNrj, int maxSat, int maxRep, int maxHyg) {
		// et l'image
		nrj = new Statistique(maxNrj, 30, 80);
		sat = new Statistique(maxSat, 50, 90);
		rep = new Statistique(maxRep, 30, 75);
		hyg = new Statistique(maxHyg, 10, 95);
		vie = new Sante();
		bhr = new Sante();
		poi = new Poids();
		this.addListeActions("jardin", "promenade");
	}
	
	public void addListeActions(String key, String value){
		// Si la clé n'a pas de valeur, on crée une arraylist
		listeActions.computeIfAbsent(key, k -> new ArrayList<>());
		listeActions.get(key).add(value);
	}
	
	public void printListeActions(){
		System.out.println(listeActions);
	}
	
	public Statistique getNrj() {
		return nrj;
	}
	
	public Statistique getSat() {
		return sat;
	}
	
	public Statistique getRep() {
		return rep;
	}
	
	public Statistique getHyg() {
		return hyg;
	}
	
	public Statistique getVie() {
		return vie;
	}
	
	public Statistique getBhr() {
		return bhr;
	}
	
	public Statistique getPoi() {
		return poi;
	}
}
