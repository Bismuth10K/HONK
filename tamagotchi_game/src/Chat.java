import java.util.ArrayList;

public class Chat extends Tamagotchi {
	public Chat(){
		super(110, 110, 120, 100);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("salon", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
		
	}
}
