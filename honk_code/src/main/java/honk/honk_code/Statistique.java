package honk.honk_code;

import static java.lang.System.currentTimeMillis;

public class Statistique {
	// Elles sont "final" car elles ne changeront jamais.
	protected final int max;
	private final int palierMin;
	private final int palierMax;
	protected int value;
	protected long lastUpdated;
	
	/**
	 * Classe pour une statistique.
	 * @param max       int : Maximum possible pour une statistique (positif).
	 * @param palierMin int : Palier minimum de la statistique (entre 0 et 100).
	 * @param palierMax int : Palier maximum de la statistique (entre 0 et 100).
	 * @throws Exception Lorsqu'il y a des valeurs aberrantes.
	 */
	public Statistique(int max, int palierMin, int palierMax) throws Exception {
		if (max > 0) this.max = max;
		else throw new Exception("Le maximum est négatif.");
		value = max;
		if (palierMin <= palierMax) if (0 <= palierMin && palierMin <= 100 && palierMax <= 100) {
			this.palierMin = palierMin;
			this.palierMax = palierMax;
		} else throw new Exception("Les valeurs pour les paliers min ou max ne sont pas entre 0 et 100.");
		else throw new Exception("La valeur de palierMin est supérieure à celle de palierMax.");
		lastUpdated = currentTimeMillis();
	}
	
	/**
	 * Getter de value.
	 * @return int value.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Setter de value.
	 * @param newValue int : Nouvelle valeur.
	 */
	public void setValue(int newValue) {
		value = newValue;
		if (value < 0) value = 0;
		else if (value > max) value = max;
	}
	
	/**
	 * Getter du palier min.
	 * @return int palierMin.
	 */
	public int getPalierMin() {
		return palierMin;
	}
	
	/**
	 * Getter du palier max.
	 * @return int palierMax.
	 */
	public int getPalierMax() {
		return palierMax;
	}
	
	/**
	 * Ajout d'une valeur à la statistique.
	 * Si value devient négatif, on la remet à 0.
	 * Si elle devient supérieure à max, elle revient à max.
	 * De plus, on réinitialise lastUpdated.
	 * @param toAdd int : Valeur à additionner (peut être positif ou négatif).
	 */
	public void add(int toAdd) {
		value += toAdd;
		if (value < 0) value = 0;
		else if (value > max) value = max;
		lastUpdated = currentTimeMillis();
	}
	
	/**
	 * Pour convertir la valeur en pourcentage pour l'affichage des jauges.
	 * @return int : pourcentage, valeur entre 0 et 100.
	 */
	public double toPercent() {
		return (double) value / max;
	}
	
	/**
	 * Getter de lastUpdated
	 * @return long : lastUpdated
	 */
	public long getLastUpdated() {
		return lastUpdated;
	}
	
	/**
	 * Pour reset lastUpdated.
	 */
	public void resetLastUpdated() {
		lastUpdated = currentTimeMillis();
	}
	
	/**
	 * Renvoie une valeur en fonction des intervalles de stabilite.
	 * @return -1 si <= palierMin, 1 si >= palierMax, 0 sinon
	 */
	public int posIntervalleStabilite() {
		int valuePercent = (int) (toPercent() * 100);
		if (valuePercent >= palierMax) return 1;
		else if (valuePercent > palierMin) return 0;
		else return -1;
	}
	
	public String toString() {
		return value + "/" + max + " : " + toPercent();
	}
}
