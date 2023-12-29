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
	
	public void ActionEat(ActionEvent event){
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
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			if (!tama.isDead()) {
				tama.applyStatsTime(chronometer);
				
				textChronometer.setText(chronometer.toString());
				textPiece.setText(house.getPiece().getPiece());
				HungerPBar.setProgress(tama.getSat().toPercent());
				SleepPBar.setProgress(tama.getRep().toPercent());
				HygPBar.setProgress(tama.getHyg().toPercent());
				EnergyPBar.setProgress(tama.getNrj().toPercent());
				XPPBar.setProgress(tama.XPToPercent());
				textXP.setText("Niveau : " + tama.getPlayerLevel());
				
				UpButton.setDisable(house.getPiece().getHaut() == null);
				LeftButton.setDisable(house.getPiece().getGauche() == null);
				RightButton.setDisable(house.getPiece().getDroite() == null);
				DownButton.setDisable(house.getPiece().getBas() == null);
				
				actionsPossibles = tama.getActionByPiece(house.getPiece().getPiece());
				EatButton.setDisable(true);
				SleepButton.setDisable(true);
				PlayButton.setDisable(true);
				WalkButton.setDisable(true);
				WashButton.setDisable(true);
				if (actionsPossibles != null) {
					for (String piece : actionsPossibles) {
						switch (piece) {
							case "manger":
								EatButton.setDisable(false);
								break;
							case "dormir":
								SleepButton.setDisable(false);
								break;
							case "jouer":
								PlayButton.setDisable(false);
								break;
							case "promenade":
								WalkButton.setDisable(false);
								break;
							case "toilette":
								WashButton.setDisable(false);
								break;
						}
					}
				}
				if(chronometer.getHours() >= 24) {
					try {
						Saver.save(chronometer, typeTama, tama);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				EatButton.setDisable(true);
				SleepButton.setDisable(true);
				PlayButton.setDisable(true);
				WalkButton.setDisable(true);
				WashButton.setDisable(true);
				textPiece.setText("Lol t mor uwu");
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
