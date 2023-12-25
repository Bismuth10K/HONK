package honk.honk_code;

import static java.lang.System.currentTimeMillis;

public class Sante extends Statistique {
	private final int is_bnh;
	
	public Sante(String type) throws Exception {
		super(10, 0, 10);
		is_bnh = type.equals("bonheur") ? 1 : 0;
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
		if (is_bnh == 0) lastUpdated = currentTimeMillis();
	}
}
