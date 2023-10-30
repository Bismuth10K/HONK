public class Chien extends Tamagotchi {
	public Chien(){
		super(125, 100, 100, 75);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("salon", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
	}
}
