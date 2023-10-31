public class Chien extends Tamagotchi {
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
