package honk.model;

public class Poids extends Statistique {
	public Poids() throws Exception {
		super(100, 33, 66);
		// Au début, le poids est à 50%.
		this.setValue(50);
	}
	
	/**
	 * Ajout d'une valeur à la statistique.
	 * Si value devient négatif, on la remet à 0.
	 * Si elle devient supérieure à max, elle revient à max.
	 * Contrairement à la version super, lastUpdated n'est pas réinitialisée.
	 * @param toAdd int : Valeur à additionner (peut être positif ou négatif).
	 */
	public void add(int toAdd) {
		value += toAdd;
		if (value < 0) value = 0;
		else if (value > max) value = max;
	}
}
