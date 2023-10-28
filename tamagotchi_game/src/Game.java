public class Game {
	// private Timer time;
	private Tamagotchi tama;
	private Maison house;
	
	public Game(String typeTama) throws Exception {
		switch(typeTama) {
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
