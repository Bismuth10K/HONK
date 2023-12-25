package honk.honk_code;

public class Piece {
	private final String nom;
	// 2DImage sprite;
	private Piece haut;
	private Piece bas;
	private Piece gauche;
	private Piece droite;
	
	/**
	 * La classe pour une pièce.
	 * Le fonctionnement est similaire à celle d'une liste chaînée,
	 * elles pointent vers la pièce en haut, en bas, à gauche et à droite.
	 * @param name String : le nom de la pièce.
	 */
	Piece(String name) {
		nom = name;
		// et un param pour l'image
	}
	
	/**
	 * Getter pour nom.
	 * @return String nom.
	 */
	public String getPiece() {
		return nom;
	}
	
	/**
	 * Setter pour les pièces alentour,
	 * possibilité de les mettre à null lorsqu'il n'y a pas de pièces à cet emplacement.
	 * @param h Piece : pièce en haut.
	 * @param b Piece : pièce en bas.
	 * @param g Piece : pièce à gauche.
	 * @param d Piece : pièce à droite.
	 */
	public void setPieces(Piece h, Piece b, Piece g, Piece d) {
		haut = h;
		bas = b;
		gauche = g;
		droite = d;
	}
	
	/**
	 * Getter pour la pièce en haut.
	 * @return Piece : la pièce associée à la direction
	 */
	public Piece getHaut() {
		return haut;
	}
	
	/**
	 * Getter pour la pièce en bas.
	 * @return Piece : la pièce associée à la direction
	 */
	public Piece getBas() {
		return bas;
	}
	
	/**
	 * Getter pour la pièce à gauche.
	 * @return Piece : la pièce associée à la direction
	 */
	public Piece getGauche() {
		return gauche;
	}
	
	/**
	 * Getter pour la pièce à droite.
	 * @return Piece : la pièce associée à la direction
	 */
	public Piece getDroite() {
		return droite;
	}
}