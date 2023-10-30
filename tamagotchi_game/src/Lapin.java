public class Lapin extends Tamagotchi {
	public Lapin(){
		super(105, 75, 80, 140);
		this.addListeActions("cuisine", "manger");
		this.addListeActions("chambre", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("hall", "jouer");
		this.addListeActions("sdb", "toilette");
	}
}
