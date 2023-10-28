import java.util.HashMap;

abstract class Tamagotchi {
	HashMap<Piece, String> map = new HashMap<Piece, String>();
	private final Statistique nrj;
	private final Statistique sat;
	private final Statistique rep;
	private final Statistique hyg;
	private final Sante vie;
	private final Sante bhr;
	private final Poids poi;
	private int XP = 0;
	// private 2DImage sprite;
	
	public Tamagotchi(int maxNrj, int maxSat, int maxRep, int maxHyg){
		// et l'image
		nrj = new Statistique(maxNrj, 30,80);
		sat = new Statistique(maxSat, 50, 90);
		rep = new Statistique(maxRep, 30, 75);
		hyg = new Statistique(maxHyg, 10, 95);
		vie = new Sante();
		bhr = new Sante();
		poi = new Poids();
	}
	
	public Statistique getNrj(){ return nrj; }
	public Statistique getSat(){ return sat; }
	public Statistique getRep(){ return rep; }
	public Statistique getHyg(){ return hyg; }
	public Statistique getVie(){ return vie; }
	public Statistique getBhr(){ return bhr; }
	public Statistique getPoi(){ return poi; }
}
