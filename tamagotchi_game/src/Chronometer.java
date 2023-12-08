import static java.lang.Math.floor;

public final class Chronometer {
	private long begin, end;
	private final int cheat_vit;
	
	/**
	 * Classe du chronometre. Sera utile pour tenir compte du temps passe depuis le debut du jeu.
	 * @param cheat int : pour accelerer le temps par la valeur de cheat.
	 */
	public Chronometer(int cheat) {
		begin = System.currentTimeMillis();
		cheat_vit = cheat;
	}
	
	/**
	 * Pour réinitialiser le chrono.
	 */
	public void start() {
		begin = System.currentTimeMillis();
	}
	
	/**
	 * Pour qu'end prenne l'heure actuelle en valeur.
	 */
	public void stop() {
		end = System.currentTimeMillis();
	}
	
	/**
	 * @return temps écoulé de begin à end en millisecondes.
	 */
	public long getMilliseconds() {
		return end - begin;
	}
	
	/**
	 * @return temps écoulé de begin à end en secondes.
	 */
	public long getSeconds() { return (long)(floor(getMilliseconds() * cheat_vit / 1000.0))  % 60; }
	
	/**
	 * @return temps écoulé de begin à end en minutes.
	 */
	public long getMinutes() {
		return (long)(floor(getMilliseconds() * cheat_vit / 60000.0)) % 60;
	}
	
	/**
	 * @return temps écoulé de begin à end en heures.
	 */
	public long getHours() {
		return (long)floor(getMilliseconds() * cheat_vit / 3600000.0);
	}
	
	/**
	 * Convertis la durée depuis begin en String sous le format h:m:s
	 * @return String durée
	 */
	public String toString() {
		stop();
		return getHours() + ":" + getMinutes() + ":" + getSeconds();
	}
	
	/**
	 * Pour savoir depuis combien de temps une stat a été actualisé.
	 * @param lastUpdatedStats long : la date dont on veut connaitre la durée jusqu'à maintenant.
	 * @return long : la durée en millisecondes de lastUpdatedStats a maintenant.
	 */
	public long getTimeStats(long lastUpdatedStats) {
		stop();
		return end - lastUpdatedStats;
	}
	
	/**
	 * Retourne le temps au'il sera dans X secondes, X etant le temps passe en parametre
	 * @param seconds int
	 * @return
	 */
	/*public long getTimeFromNow(long seconds) {
		return begin + seconds * 1000;
	} */
}