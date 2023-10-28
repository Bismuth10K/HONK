public class Piece {
	private final String nom;
	// 2DImage sprite;
	private final Piece droite;
	private final Piece haut;
	private final Piece bas;
	private final Piece gauche;
	
	Piece (String name, Piece h, Piece b, Piece g, Piece d){
		nom = name;
		// et un param pour l'image
		haut = h;
		bas = b;
		gauche = g;
		droite = d;
	}
	
	public String getPiece() { return nom; }
	public Piece getHaut() { return haut; }
	public Piece getBas() { return bas; }
	public Piece getGauche() { return gauche; }
	public Piece getDroite() { return droite; }
}
