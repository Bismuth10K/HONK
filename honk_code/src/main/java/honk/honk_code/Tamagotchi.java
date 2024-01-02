package honk.honk_code;

import java.util.ArrayList;
import java.util.HashMap;

abstract class Tamagotchi {
	// Elles sont "final" car elles ne changeront pas, juste leurs valeurs changent
	private final Statistique nrj;
	private final Statistique sat;
	private final Statistique rep;
	private final Statistique hyg;
	private final Poids poi;
	private final Sante vie;
	private final Sante bhr;
	private final double rateXP = 1.1; // le taux qui va faire augmenter maxXP à chaque passage de level
	// Dictionnaire qui va contenir les actions possibles (sous forme d'ArrayList) dans une pièce.
	HashMap<String, ArrayList<String>> listeActions = new HashMap<String, ArrayList<String>>();
	private int playerLevel = 0; // niveau actuel du tama
	private double currentXP = 0; // nombre de XP collectés
	private double maxXP = 100; // le maximum à atteindre avant de passer au niveau suivant
	
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
		vie = new Sante("sante");
		bhr = new Sante("bonheur");
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
	 * Renvoie l'ArrayList des actions par rapport au paramètre piece.
	 * @param piece Piece : une pièce dont on veut savoir quelles actions sont possibles là.
	 * @return ArrayList<String> : liste des actions
	 */
	public ArrayList<String> getActionByPiece(String piece) {
		return listeActions.get(piece);
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
	
	/**
	 * Return whether the Tamagotchi is dead.
	 * @return true if the value of bhr or vie is 0, else false.
	 */
	public boolean isDead() {
		return bhr.getValue() == 0 || vie.getValue() == 0;
	}
	
	/**
	 * Fonction manger.
	 */
	public void manger() {
		nrj.add(30);
		sat.add(75);
	}
	
	/**
	 * Fonction dormir.
	 */
	public void dormir() {
		nrj.add(80);
		sat.add(-50);
		rep.add(100);
	}
	
	/**
	 * Fonction jouer.
	 */
	public void jouer() {
		nrj.add(-30);
		sat.add(-20);
		rep.add(-20);
		hyg.add(-15);
		poi.add(-5);
		bhr.add(1);
	}
	
	/**
	 * Fonction se promener.
	 */
	public void promenade() {
		nrj.add(-60);
		sat.add(-45);
		rep.add(-50);
		hyg.add(-40);
		poi.add(-10);
		bhr.add(3);
	}
	
	/**
	 * Fonction se laver.
	 */
	public void toilette() {
		hyg.add(100);
	}
	
	/**
	 * Fonction de l'influence du temps sur les statistiques.
	 */
	public void applyStatsTime(Chronometer chronometer) {
		long toMillis24 = chronometer.toMillis(24);
		long toMillis2 = chronometer.toMillis(2);
		if (chronometer.getTimeStats(nrj.getLastUpdated()) >= chronometer.toMillis(1)) {
			nrj.add(-5);
		}
		if (chronometer.getTimeStats(sat.getLastUpdated()) >= toMillis2) {
			sat.add(-15);
		}
		if (chronometer.getTimeStats(rep.getLastUpdated()) >= toMillis2) {
			rep.add(-10);
		}
		if (chronometer.getTimeStats(hyg.getLastUpdated()) >= toMillis2) {
			hyg.add(-5);
		}
		if (chronometer.getTimeStats(poi.getLastUpdated()) >= toMillis24) {
			if (poi.posIntervalleStabilite() == -1) poi.add(-10);
			else if (poi.posIntervalleStabilite() == 0) poi.add(-5);
			else poi.add(+10);
			poi.resetLastUpdated();
		}
		if (chronometer.getTimeStats(bhr.getLastUpdated()) >= toMillis24) {
			bhr.add(-3);
			bhr.resetLastUpdated();
		}
		if (chronometer.getTimeStats(vie.getLastUpdated()) >= toMillis24) {
			int conditionsVie = nrj.posIntervalleStabilite() + sat.posIntervalleStabilite() + rep.posIntervalleStabilite() + hyg.posIntervalleStabilite() + (poi.posIntervalleStabilite() == 0 ? 1 : -1);
			vie.add(conditionsVie);
			
			currentXP += ((double) (vie.getValue() + bhr.getValue()) / 2) * 10;
			
			if (currentXP >= maxXP) {
				double reste = currentXP - maxXP;
				playerLevel++;
				currentXP = reste;
				maxXP *= rateXP;
			}
		}
	}
	
	/**
	 * Renvoie le pourcentage de currentXP par rapport à maxXP en valeur comprise entre 0 et 1.
	 * @return nombre décimal compris entre 0 et 1.
	 */
	public double XPToPercent() {
		return currentXP / maxXP;
	}
	
	/**
	 * Getter playerLevel.
	 * @return int : playerLevel
	 */
	public int getPlayerLevel() {
		return playerLevel;
	}
	
	/**
	 * Setter de playerLevel.
	 * @param playerLevel int : new value
	 */
	public void setPlayerLevel(int playerLevel) {
		this.playerLevel = playerLevel;
	}
	
	/**
	 * Getter currentXP.
	 * @return double : currentXP
	 */
	public double getCurrentXP() {
		return currentXP;
	}
	
	/**
	 * Setter de currentXP.
	 * @param currentXP double : new value
	 */
	public void setCurrentXP(double currentXP) {
		this.currentXP = currentXP;
	}
	
	/**
	 * Getter de maxXP.
	 * @return double : maxXP
	 */
	public double getMaxXP() {
		return maxXP;
	}
	
	/**
	 * Setter de maxXP.
	 * @param maxXP double : new value
	 */
	public void setMaxXP(double maxXP) {
		this.maxXP = maxXP;
	}
	
	/**
	 * toString est une fonction qui s'appelle automatiquement lorsqu'on veut print cet objet.
	 * Il sert à des fins de débogage afin de voir sans interface graphique si les valeurs fonctionnent correctement.
	 * @return String : les infos du Tamagotchi.
	 */
	public String toString() {
		return "Energie : \t" + nrj + " ;\n" + "Satiete : \t" + sat + " ;\n" + "Repos : \t" + rep + " ;\n" + "Hygiene : \t" + hyg + " ;\n" + "Vie : \t" + vie + " ;\n" + "Bonheur : \t" + bhr + " ;\n" + "Poids : \t" + poi + " ;\n" + "Expérience : \t" + (int) currentXP + "/" + (int) maxXP + " - Level " + playerLevel + " ;\n";
	}
}

/**
 * Classe pour tous les tamagotchis ci-dessous.
 * Chat / Chien / Lapin / Robot
 */


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
	
	public String toString() {
		return "--Chat--\n" + super.toString();
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
	
	public String toString() {
		return "--Chien--\n" + super.toString();
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
	
	public String toString() {
		return "--Lapin--\n" + super.toString();
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
	
	public String toString() {
		return "--Robot--\n" + super.toString();
	}
}