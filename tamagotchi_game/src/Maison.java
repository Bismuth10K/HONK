public class Maison {
	private Piece piece;
	
	public Maison() {
		Piece jardin = new Piece("jardin");
		Piece cuisine = new Piece("cuisine");
		Piece hall = new Piece("hall");
		Piece garage = new Piece("garage");
		Piece sdb = new Piece("sdb");
		Piece salon = new Piece("salon");
		Piece chambre = new Piece("chambre");
		
		piece = hall;
		
		jardin.setPieces(null, hall, null, null);
		cuisine.setPieces(null, null, null, hall);
		hall.setPieces(jardin, salon, cuisine, garage);
		garage.setPieces(null, null, hall, null);
		sdb.setPieces(null, null, null, salon);
		salon.setPieces(hall, null, sdb, chambre);
		chambre.setPieces(null, null, salon, null);
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece newCur) {
		piece = newCur;
	}
	
	public void goHaut() {
		this.setPiece(piece.getHaut());
	}
	
	public void goBas() {
		this.setPiece(piece.getBas());
	}
	
	public void goGauche() {
		this.setPiece(piece.getGauche());
	}
	
	public void goDroite() {
		this.setPiece(piece.getDroite());
	}
}
