public class Piece {
	private final String nom;
	// 2DImage sprite;
	private Piece droite;
	private Piece haut;
	private Piece bas;
	private Piece gauche;
	
	Piece(String name) {
		nom = name;
		// et un param pour l'image
	}
	
	public String getPiece() {
		return nom;
	}
	
	public Piece getHaut() {
		return haut;
	}
	
	public Piece getBas() {
		return bas;
	}
	
	public Piece getGauche() {
		return gauche;
	}
	
	public Piece getDroite() {
		return droite;
	}
	
	public void setPieces(Piece h, Piece b, Piece g, Piece d) {
		haut = h;
		bas = b;
		gauche = g;
		droite = d;
	}
}
