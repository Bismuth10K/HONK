public class Game {
	// private Timer time;
	private final Tamagotchi tama;
	private final Maison house = new Maison();
	
	/**
	 * Game gère tout ce qui concerne le jeu.
	 * Elle contiendra la maison dans laquelle le joueur évolue, et le Tamagotchi.
	 *
	 * @param typeTama String : nom du Tamagotchi choisi par le joueur.
	 * @throws Exception Au cas où typeTama n'est pas bon.
	 */
	public Game(String typeTama) throws Exception {
		switch (typeTama) {
			case "chat":
				tama = new Chat();
				break;
			case "chien":
				tama = new Chien();
				break;
			case "lapin":
				tama = new Lapin();
				break;
			case "robot":
				tama = new Robot();
				break;
			default:
				throw new Exception("Pas un Tamagotchi valide.");
		}
	}
}
