import java.util.ArrayList;
import java.util.HashMap;

abstract class Tamagotchi {
	// Elles sont "final" car elles ne changeront pas, juste leurs valeurs changent
	private final Statistique nrj;
	private final Statistique sat;
	private final Statistique rep;
	private final Statistique hyg;
	private final Sante vie;
	private final Sante bhr;
	private final Poids poi;
	private final int XP = 0;
	// Dictionnaire qui va contenir les actions possibles (sous forme d'ArrayList) dans une pièce.
	HashMap<String, ArrayList<String>> listeActions = new HashMap<String, ArrayList<String>>();
	// private 2DImage sprite;
	
	/**
	 * Super-classe des Tamagotchis.
	 * Ainsi, chaque sous-classe pourrait avoir ses valeurs par défaut.
	 * @param maxNrj int : Valeur max pour nrj.
	 * @param maxSat int : Valeur max pour sat.
	 * @param maxRep int : Valeur max pour rep.
	 * @param maxHyg int : Valeur max pour hyg.
	 * @throws Exception Si les statistiques ont des valeurs aberrantes.
	 */
	public Tamagotchi(int maxNrj, int maxSat, int maxRep, int maxHyg) throws Exception {
		// et l'image
		nrj = new Statistique(maxNrj, 30, 80);
		sat = new Statistique(maxSat, 50, 90);
		rep = new Statistique(maxRep, 30, 75);
		hyg = new Statistique(maxHyg, 10, 95);
		vie = new Sante();
		bhr = new Sante();
		poi = new Poids();
		// Tous les Tamagotchis peuvent faire l'action promenade depuis le jardin,
		// donc on l'ajoute dans la super-classe.
		this.addListeActions("jardin", "promenade");
	}
	
	/**
	 * Cette fonction a pour but de simplifier l'ajout d'une paire clé-valeur.
	 * @param key   String : clé dans le dictionnaire (ici un lieu).
	 * @param value String : valeur associée à la clé (ici une action).
	 */
	public void addListeActions(String key, String value) {
		// Si la clé n'a pas de valeur, on crée une arraylist.
		listeActions.computeIfAbsent(key, k -> new ArrayList<>());
		// On rajoute la valeur dans l'ArrayList.
		listeActions.get(key).add(value);
	}
	
	/**
	 * Juste pour des tests, print listeActions pour voir s'il y a bien les lieux et les actions.
	 */
	public void printListeActions() {
		System.out.println(listeActions);
	}
	
	/**
	 * Getter de nrj.
	 * @return Statistique : nrj.
	 */
	public Statistique getNrj() {
		return nrj;
	}
	
	/**
	 * Getter de sat.
	 * @return Statistique : sat.
	 */
	public Statistique getSat() {
		return sat;
	}
	
	/**
	 * Getter de rep.
	 * @return Statistique : rep.
	 */
	public Statistique getRep() {
		return rep;
	}
	
	/**
	 * Getter de hyg.
	 * @return Statistique : hyg.
	 */
	public Statistique getHyg() {
		return hyg;
	}
	
	/**
	 * Getter de vie.
	 * @return Statistique : vie.
	 */
	public Statistique getVie() {
		return vie;
	}
	
	/**
	 * Getter de bhr.
	 * @return Statistique : bhr.
	 */
	public Statistique getBhr() {
		return bhr;
	}
	
	/**
	 * Getter de poi.
	 * @return Statistique : poi.
	 */
	public Statistique getPoi() {
		return poi;
	}
}


/***************************************************\
 *	Classe pour tous les tamagotchis ci-dessous. 	*
 *	Chat / Chien / Lapin / Robot					*
 \**************************************************/

class Chat extends Tamagotchi {
	/**
	 * Classe enfant de Tamagotchi pour le chat avec les valeurs par défaut adaptées.
	 */
	public Chat() throws Exception {
		super(110, 110, 120, 100);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("salon", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
	}
}

class Chien extends Tamagotchi {
	/**
	 * Classe enfant de Tamagotchi pour le chien avec les valeurs par défaut adaptées.
	 */
	public Chien() throws Exception {
		super(125, 100, 100, 75);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("salon", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
	}
}

class Lapin extends Tamagotchi {
	/**
	 * Classe enfant de Tamagotchi pour le lapin avec les valeurs par défaut adaptées.
	 */
	public Lapin() throws Exception {
		super(105, 75, 80, 140);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("chambre", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
	}
}

class Robot extends Tamagotchi {
	/**
	 * Classe enfant de Tamagotchi pour le robot avec les valeurs par défaut adaptées.
	 */
	public Robot() throws Exception {
		super(180, 20, 150, 50);
		this.addListeActions("garage", "manger");
		this.addListeActions("chambre", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("salon", "jouer");
		this.addListeActions("cuisine", "toilette");
	}
}