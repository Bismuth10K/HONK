abstract class Tamagotchis {
	// Dict Actions : Pièces
	private Statistiques nrj;
	private Statistiques sat;
	private Statistiques rep;
	private Statistiques hyg;
	// private Vie vie;
	// private Bonheur bonh
	private int niv = 0;
	// private 2DImage sprite;
	
	public Tamagotchis(int maxNrj, int maxSat, int maxRep, int maxHyg){
		nrj = new Statistiques(maxNrj, 25, 75);
		sat = new Statistiques(maxSat, 25, 75);
		rep = new Statistiques(maxRep, 25, 75);
		hyg = new Statistiques(maxHyg, 25, 75);
	}
	
	public int getNrjValue(){
		return nrj.getValue();
	}
	public int getSatValue(){
		return sat.getValue();
	}
	public int getRepValue(){
		return rep.getValue();
	}
	public int getHygValue(){
		return hyg.getValue();
	}
}
