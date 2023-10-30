public class Robot extends Tamagotchi {
	public Robot(){
		super(180, 20, 150, 50);
		this.addListeActions("garage", "manger");
		this.addListeActions("chambre", "dormir");
		this.addListeActions("jardin", "jouer");
		this.addListeActions("salon", "jouer");
		this.addListeActions("cuisine", "toilette");
	}
}
