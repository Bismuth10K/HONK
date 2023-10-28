public class Actions {
	
	Tamagotchi tama;
	
	public Actions(Tamagotchi tama){
		this.tama = tama;
	}
	
	public void manger(){
		tama.getNrj().add(30);
		tama.getSat().add(75);
	}
	
	public void dormir(){
		tama.getNrj().add(80);
		tama.getSat().add(-50);
		tama.getRep().add(100);
	}
	
	public void jouer(){
		tama.getNrj().add(-30);
		tama.getSat().add(-20);
		tama.getRep().add(-20);
		tama.getHyg().add(-15);
		tama.getPoi().add(-5);
		tama.getBhr().add(1);
	}
	
	public void promenade(){
		tama.getNrj().add(-60);
		tama.getSat().add(-45);
		tama.getRep().add(-50);
		tama.getHyg().add(-40);
		tama.getPoi().add(-10);
		tama.getBhr().add(3);
	}
	
	public void toilette(){
		tama.getHyg().add(100);
	}
}
