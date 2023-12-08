public class Actions {
	Tamagotchi tama;
	Chronometer chronometer;
	
	/**
	 * La classe Actions reçoit en paramètre le tamagotchi créé.
	 * À chaque fois que le joueur fait une action,
	 * nous appliquerons des modifications aux statistiques du Tamagotchi.
	 * <p>
	 * Toutes les fonctions suivantes sont associées à une action possible et
	 * appliquent les modifications sur les statistiques selon la matrice d'incidence.
	 * @param tama Tamagotchi : Le tamagotchi sur lequel nous appliquerons les modifications.
	 */
	public Actions(Tamagotchi tama, Chronometer chronometer) {
		this.tama = tama;
		this.chronometer = chronometer;
	}
	
	/**
	 * Convertir un nombre d'heure en millisecondes.
	 * @param hour int
	 * @return long : heures en millisecondes.
	 */
	public long toMillis(int hour) {
		return (long) hour * 60 * 60 * 1000;
	}
	
	/**
	 * Fonction manger.
	 */
	public void manger() {
		tama.getNrj().add(30);
		tama.getSat().add(75);
	}
	
	/**
	 * Fonction dormir.
	 */
	public void dormir() {
		tama.getNrj().add(80);
		tama.getSat().add(-50);
		tama.getRep().add(100);
	}
	
	/**
	 * Fonction jouer.
	 */
	public void jouer() {
		tama.getNrj().add(-30);
		tama.getSat().add(-20);
		tama.getRep().add(-20);
		tama.getHyg().add(-15);
		tama.getPoi().add(-5);
		tama.getBhr().add(1);
	}
	
	/**
	 * Fonction se promener.
	 */
	public void promenade() {
		tama.getNrj().add(-60);
		tama.getSat().add(-45);
		tama.getRep().add(-50);
		tama.getHyg().add(-40);
		tama.getPoi().add(-10);
		tama.getBhr().add(3);
	}
	
	/**
	 * Fonction se laver.
	 */
	public void toilette() {
		tama.getHyg().add(100);
	}
	
	/**
	 * Fonction de l'influence du temps sur les statistiques.
	 */
	public void applyStatsTime() {
		if (chronometer.getTimeStats(tama.getNrj().getLastUpdated()) >= toMillis(1)) {
			tama.getNrj().add(-5);
		}
		if (chronometer.getTimeStats(tama.getSat().getLastUpdated()) >= toMillis(2)) {
			tama.getSat().add(-15);
		}
		if (chronometer.getTimeStats(tama.getRep().getLastUpdated()) >= toMillis(2)) {
			tama.getRep().add(-10);
		}
		if (chronometer.getTimeStats(tama.getHyg().getLastUpdated()) >= toMillis(2)) {
			tama.getHyg().add(-5);
		}
		if (chronometer.getTimeStats(tama.getPoi().getLastUpdated()) >= toMillis(24)) {
			if (tama.getPoi().posIntervalleStabilite() == -1) tama.getPoi().add(-10);
			else if (tama.getPoi().posIntervalleStabilite() == 0) tama.getPoi().add(-5);
			else tama.getPoi().add(+10);
			tama.getPoi().resetLastUpdated();
		}
		if (chronometer.getTimeStats(tama.getBhr().getLastUpdated()) >= toMillis(24)) {
			tama.getBhr().add(-4);
			tama.getBhr().resetLastUpdated();
		}
	}
}
