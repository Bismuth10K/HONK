package honk.honk_code;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Game implements Initializable {
	final Maison house = new Maison();
	final Chronometer chronometer = new Chronometer(1);
	private Tamagotchi tama;
	private String typeTama;
	private ArrayList<String> actionsPossibles;
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
	private Text textChronometer;
	@FXML
	private Text textPiece;
	@FXML
	private Button UpButton;
	@FXML
	private Button LeftButton;
	@FXML
	private Button RightButton;
	@FXML
	private Button DownButton;
	@FXML
	private ProgressBar HungerPBar;
	@FXML
	private ProgressBar SleepPBar;
	@FXML
	private ProgressBar HygPBar;
	@FXML
	private ProgressBar EnergyPBar;
	@FXML
	private ProgressBar XPPBar;
	@FXML
	private Label textXP;
	
	
	/**
	 * Game gère tout ce qui concerne le jeu.
	 * Elle contiendra la maison dans laquelle le joueur évolue, et le Tamagotchi.
	 * @param typeTama String : nom du Tamagotchi choisi par le joueur.
	 * @throws Exception Au cas où typeTama n'est pas bon.
	 */
	public void setTama(String typeTama) throws Exception {
		this.typeTama = typeTama;
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
			case "renard":
				tama = new Chat();
				break;
			case "lynx":
				tama = new Chien();
				break;
			case "lapin de paques":
				tama = new Lapin();
				break;
			case "uwucopter":
				tama = new Robot();
				break;
			default:
				throw new Exception("Pas un Tamagotchi valide.");
		}
	}
	
	/**
	 * Lors de l'appui sur un bouton de direction.
	 * Pour aller en haut.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionUp(ActionEvent event) {
		house.goHaut();
	}
	
	/**
	 * Lors de l'appui sur un bouton de direction.
	 * Pour aller à gauche.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionLeft(ActionEvent event) {
		house.goGauche();
	}
	
	/**
	 * Lors de l'appui sur un bouton de direction.
	 * Pour aller à droite.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionRight(ActionEvent event) {
		house.goDroite();
	}
	
	/**
	 * Lors de l'appui sur un bouton de direction.
	 * Pour aller en bas.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionDown(ActionEvent event) {
		house.goBas();
	}
	
	/**
	 * Lors de l'appui sur un bouton d'action.
	 * Pour manger.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionEat(ActionEvent event) {
		tama.manger();
		chronometer.addTimeSkip(chronometer.toMillis(1));
	}
	
	/**
	 * Lors de l'appui sur un bouton d'action.
	 * Pour dormir.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionSleep(ActionEvent event) {
		tama.dormir();
		chronometer.addTimeSkip(chronometer.toMillis(8));
	}
	
	/**
	 * Lors de l'appui sur un bouton d'action.
	 * Pour jouer.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionPlay(ActionEvent event) {
		tama.jouer();
		chronometer.addTimeSkip(chronometer.toMillis(2));
	}
	
	/**
	 * Lors de l'appui sur un bouton d'action.
	 * Pour aller se promener.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionWalk(ActionEvent event) {
		tama.promenade();
		chronometer.addTimeSkip(chronometer.toMillis(3));
	}
	
	/**
	 * Lors de l'appui sur un bouton d'action.
	 * Pour se laver.
	 * @param event ActionEvent : appui du bouton.
	 */
	public void ActionWash(ActionEvent event) {
		tama.toilette();
		chronometer.addTimeSkip(chronometer.toMillis(0.75));
	}
	
	/**
	 * Initialize nous sert ici à réactualiser le jeu régulièrement avec Timeline.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Tous les 0.2 seconde, on applique le code qui est dedans.
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			if (!tama.isDead()) { // Tant que le tamagotchi n'est pas mort :
				tama.applyStatsTime(chronometer); // on voit si des statistiques peuvent baisser à cause du temps.
				
				textChronometer.setText(chronometer.toString()); // maj des textes (chronomètre et pièce).
				textPiece.setText(house.getPiece().getPiece());
				
				HungerPBar.setProgress(tama.getSat().toPercent()); // maj des progress bars.
				SleepPBar.setProgress(tama.getRep().toPercent());
				HygPBar.setProgress(tama.getHyg().toPercent());
				EnergyPBar.setProgress(tama.getNrj().toPercent());
				XPPBar.setProgress(tama.XPToPercent());
				textXP.setText("Niveau : " + tama.getPlayerLevel()); // maj du texte des niveaux.
				
				UpButton.setDisable(house.getPiece().getHaut() == null); // on disable une des directions s'il n'y a pas de pièce à cet endroit.
				LeftButton.setDisable(house.getPiece().getGauche() == null);
				RightButton.setDisable(house.getPiece().getDroite() == null);
				DownButton.setDisable(house.getPiece().getBas() == null);
				
				actionsPossibles = tama.getActionByPiece(house.getPiece().getPiece()); // on récupère les actions possibles dans la pièce.
				EatButton.setDisable(!actionsPossibles.contains("manger")); // si un String est dans la liste, dans ce cas, on renvoie false.
				SleepButton.setDisable(!actionsPossibles.contains("dormir"));
				PlayButton.setDisable(!actionsPossibles.contains("jouer"));
				WalkButton.setDisable(!actionsPossibles.contains("promenade"));
				WashButton.setDisable(!actionsPossibles.contains("toilette"));
				
				if (chronometer.getHours() == 24) { // test de création de save.
					try {
						Saver.save(chronometer, typeTama, tama);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				EatButton.setDisable(true); // Quand le tamagotchi meurt, on disable les actions.
				SleepButton.setDisable(true);
				PlayButton.setDisable(true);
				WalkButton.setDisable(true);
				WashButton.setDisable(true);
				textPiece.setText("Lol t mor uwu"); // On met un texte.
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
