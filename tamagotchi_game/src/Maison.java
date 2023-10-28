public class Maison {
	private Piece piece;
	
	public Maison(Piece debut){
		piece = debut;
	}
	
	public Piece getPiece() { return piece; }
	
	public void setPiece(Piece newCur) { piece = newCur; }
	
	public void goHaut() { this.setPiece(piece.getHaut()); }
	public void goBas() { this.setPiece(piece.getBas()); }
	public void goGauche() { this.setPiece(piece.getGauche()); }
	public void goDroite() { this.setPiece(piece.getDroite()); }
}
