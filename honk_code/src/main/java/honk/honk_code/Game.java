package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Game {
	private Tamagotchi tama;
	private final Maison house = new Maison();
	private final Chronometer chronometer = new Chronometer(1);
	
	@FXML
	private Button WalkButton;
	@FXML
	private Button PlayButton;
	@FXML
	private Button WashButton;
	@FXML
	private Button SleepButton;
	@FXML
	private Button EatButton;
	@FXML
	private Button UpButton;
	@FXML
	private Button LeftButton;
	@FXML
	private Button RightButton;
	@FXML
	private Button DownButton;
	
	public Game(){}
	
	/**
	 * Game gère tout ce qui concerne le jeu.
	 * Elle contiendra la maison dans laquelle le joueur évolue, et le Tamagotchi.
	 * @param typeTama String : nom du Tamagotchi choisi par le joueur.
	 * @throws Exception Au cas où typeTama n'est pas bon.
	 */
	public void setTama(String typeTama) throws Exception {
		switch (typeTama) {
			case "chat":
				tama = new Chat();
				break;
			case "chien":
				tama = new Chien();
				break;
			case "lapin":
				tama = new Lapin();
				break;
			case "robot":
				tama = new Robot();
				break;
			default:
				throw new Exception("Pas un Tamagotchi valide.");
		}
	}
	
	public void gamifyThis() throws InterruptedException {
		ArrayList<String> actionsPossibles;
		
		tama.printListeActions();
		
		while (!tama.isDead()) {
			Thread.sleep(100);
			tama.applyStatsTime(chronometer);
			
			// gv.chrono.setText(chronometer + "\nPièce actuelle : " + house.getPiece().getPiece());
			
			actionsPossibles = tama.getActionByPiece(house.getPiece().getPiece());
			EatButton.setVisible(false);
			SleepButton.setVisible(false);
			PlayButton.setVisible(false);
			WalkButton.setVisible(false);
			WashButton.setVisible(false);
			if (actionsPossibles != null) {
				for (String piece : actionsPossibles) {
					switch (piece) {
						case "manger":
							EatButton.setVisible(true);
							break;
						case "dormir":
							SleepButton.setVisible(true);
							break;
						case "jouer":
							PlayButton.setVisible(true);
							break;
						case "promenade":
							WalkButton.setVisible(true);
							break;
						case "toilette":
							WashButton.setVisible(true);
							break;
					}
				}
			}
		}
		EatButton.setVisible(false);
		SleepButton.setVisible(false);
		PlayButton.setVisible(false);
		WalkButton.setVisible(false);
		WalkButton.setVisible(false);
	}
	
	public void ActionUp(ActionEvent event) {
		house.goHaut();
	}
	
	public void ActionLeft(ActionEvent event) {
		house.goGauche();
	}
	
	public void ActionRight(ActionEvent event) {
		house.goDroite();
	}
	
	public void ActionDown(ActionEvent event) {
		house.goBas();
	}
	
	public void ActionEat(ActionEvent event) {
		tama.manger();
		chronometer.addTimeSkip(chronometer.toMillis(1));
	}
	
	public void ActionSleep(ActionEvent event) {
		tama.dormir();
		chronometer.addTimeSkip(chronometer.toMillis(8));
	}
	
	public void ActionPlay(ActionEvent event) {
		tama.jouer();
		chronometer.addTimeSkip(chronometer.toMillis(2));
	}
	
	public void ActionWalk(ActionEvent event) {
		tama.promenade();
		chronometer.addTimeSkip(chronometer.toMillis(3));
	}
	
	public void ActionWash(ActionEvent event) {
		tama.toilette();
		chronometer.addTimeSkip(chronometer.toMillis(0.75));
	}
}
