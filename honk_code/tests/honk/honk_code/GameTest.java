package honk.honk_code;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Malheureusement, tous les tests présents dans les cas de tests ne sont pas réalisables.
 * Cela peut être dû à plusieurs facteurs :
 * 		- soit nous n'avons pas le niveau ;
 * 		- soit c'est non testable en code et ça doit être testé à la main (ex : fichier corrompu) ;
 * 		- soit par manque de temps.
 * Nous nous excusons du manque de travail sur ces tests.
 */
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
	
	/**
	 * On teste les actions possibles dans la chambre pour le robot.
	 * Le robot peut juste y dormir, donc tous les autres sont des assertFalse.
	 *
	 * IMPORTANT : ce test ne marche pas car, à partir du moment où on met un Tamagotchi, il renvoie une erreur de ce genre :
	 * 		java.lang.RuntimeException: Internal graphics not initialized yet
	 * Erreur qui n'arrive pas lors du test précédent.
	 * @throws Exception
	 */
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