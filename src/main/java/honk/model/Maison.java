package honk.model;
public class Maison {
	private Piece piece;
	
	/**
	 * Maison représente l'environnement dans lequel le joueur évolue.
	 * Elle gèrera le déplacement entre les différentes pièces et leurs créations.
	 */
	public Maison() {
		// Nous créons les pièces, puis nous les agençons.
		Piece jardin = new Piece("jardin");
		Piece cuisine = new Piece("cuisine");
		Piece hall = new Piece("hall");
		Piece garage = new Piece("garage");
		Piece sdb = new Piece("sdb");
		Piece salon = new Piece("salon");
		Piece chambre = new Piece("chambre");
		
		jardin.setPieces(null, hall, null, null);
		cuisine.setPieces(null, null, null, hall);
		hall.setPieces(jardin, salon, cuisine, garage);
		garage.setPieces(null, null, hall, null);
		sdb.setPieces(null, null, null, salon);
		salon.setPieces(hall, null, sdb, chambre);
		chambre.setPieces(null, null, salon, null);
		
		// La pièce par défaut est choisie au hasard.
		Piece[] pieces = {jardin, cuisine, hall, garage, sdb, salon, chambre};
		java.util.Random random = new java.util.Random();
		int randomPiece = random.nextInt(pieces.length);
		piece = pieces[randomPiece];
	}
	
	/**
	 * Getter de cur pour savoir dans quelle pièce nous sommes actuellement.
	 * @return Piece : cur.
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Setter de cur pour les changements de pièces.
	 * @param newCur Piece : La nouvelle pièce si elle existe.
	 */
	public void setPiece(Piece newCur) {
		if (newCur != null) piece = newCur;
	}
	
	/**
	 * Fonctions pour se déplacer vers la pièce en haut.
	 */
	public void goHaut() { this.setPiece(piece.getHaut()); }
	
	/**
	 * Fonctions pour se déplacer vers la pièce en bas.
	 */
	public void goBas() {
		this.setPiece(piece.getBas());
	}
	
	/**
	 * Fonctions pour se déplacer vers la pièce à gauche.
	 */
	public void goGauche() {
		this.setPiece(piece.getGauche());
	}
	
	/**
	 * Fonctions pour se déplacer vers la pièce à droite.
	 */
	public void goDroite() {
		this.setPiece(piece.getDroite());
	}
}
