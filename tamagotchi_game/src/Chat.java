public class Chat extends Tamagotchi {
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
