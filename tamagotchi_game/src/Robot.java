public class Robot extends Tamagotchi {
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
