public class Lapin extends Tamagotchi {
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
