package honk.model;

public class Launcher {
	public static void main(String[] args) throws Exception {
		Game game = new Game("robot");
		game.gamifyThis();
	}
}