package honk.honk_code;

import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
	
	/**
	 * On teste si on peut ne pas aller à un endroit où il n'y a pas de pièce.
	 * Ici, on teste pour la chambre, en haut / bas / droite il n'y a pas de pièce.
	 * Par contre, il y a une pièce à gauche.
	 */
	@Test
	public void directionsInconnues() throws Exception {
		Game game = new Game();
		Maison maison = game.getMaison();
		maison.goToPiece("chambre");
		maison.goBas();
		assertEquals("chambre", maison.getPiece().getPiece());
		maison.goHaut();
		assertEquals("chambre", maison.getPiece().getPiece());
		maison.goDroite();
		assertEquals("chambre", maison.getPiece().getPiece());
		maison.goGauche();
		assertNotEquals("chambre", maison.getPiece().getPiece());
	}
	
	@Test
	public void actionsPossibles() throws Exception {
		Game game = new Game();
		game.setTama("robot");
		game.getMaison().goToPiece("chambre");
		ArrayList<String> liste =  game.getTama().getActionByPiece("chambre");
		assertFalse(liste.contains("manger"));
		assertFalse(liste.contains("promenade"));
		assertTrue(liste.contains("dormir"));
		assertFalse(liste.contains("jouer"));
		assertFalse(liste.contains("toilette"));
	}
	
	
}