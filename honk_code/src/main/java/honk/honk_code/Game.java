package honk.honk_code;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Game implements Initializable {
	final Maison house = new Maison();
	final Chronometer chronometer = new Chronometer(1);
	private final String[] colPBar = {"#B21030", "#2800BA", "#51A200"};
	private Tamagotchi tama;
	private String typeTama;
	private ArrayList<String> actionsPossibles;
	private boolean gameIsPaused = false;
	private boolean endGameNotLaunched = false;
	private boolean activateAutoSave = true;
	@FXML
	private ImageView TamaImage;
	@FXML
	private Button WalkButton, PlayButton, WashButton, SleepButton, EatButton;
	@FXML
	private Button UpButton, LeftButton, RightButton, DownButton;
	@FXML
	private Button RevenirBouton;
	@FXML
	private Label textChronometer;
	@FXML
	private Label RoomLabel, textXP;
	@FXML
	private ProgressBar HungerPBar, SleepPBar, HygPBar, EnergyPBar, PoiPBar;
	@FXML
	private ProgressBar XPPBar;
	@FXML
	private ToolBar HappyHeartBar, LifeHeartBar;
	@FXML
	private BorderPane GamePane, OptionsPane;
	@FXML
	private AnchorPane YouDiedPane;
	@FXML
	private ToggleButton AutoSaveToggle;
	private int waitTenMinutes = 1; // s'incrémente dans la Timeline pour effectuer une action tous les x temps.
	
	/**
	 * Pour changer le Tamagotchi lorsque la partie est créée.
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
			default:
				throw new Exception("Pas un Tamagotchi valide.");
		}
		TamaImage.setImage(tama.getSprite());
	}
	
	/**
	 * Pour charger un JSON lorsque la partie est créée
	 */
	public void setJSON(FileReader toRead) throws IOException, ParseException {
		Saver.parse(toRead, chronometer, tama);
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
	 * Met à jour les cœurs sur la scène.
	 * À savoir, une unité de valeur dans les statistiques Vie et Bonheur correspondent à un demi-coeur.
	 */
	public void updateHearts() {
		double nbLife = (double) tama.getVie().getValue() / 2;
		double nbHappy = (double) tama.getBhr().getValue() / 2;
		ImageView LifeIV;
		ImageView HappyIV;
		for (int i = 4; i >= 0; i--) {
			LifeIV = (ImageView) LifeHeartBar.getItems().get(i);
			HappyIV = (ImageView) HappyHeartBar.getItems().get(i);
			if (nbLife >= 1)
				LifeIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/LifeFull.png"))));
			else if (nbLife == 0.5)
				LifeIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/LifeHalf.png"))));
			else
				LifeIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/LifeEmpty.png"))));
			nbLife--;
			
			if (nbHappy >= 1)
				HappyIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/HappyFull.png"))));
			else if (nbHappy == 0.5)
				HappyIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/HappyHalf.png"))));
			else
				HappyIV.setImage(new Image(String.valueOf(getClass().getResource("textures/gauges-and-buttons/HappyEmpty.png"))));
			nbHappy--;
		}
	}
	
	/**
	 * Met à jour les progress bars des statistiques globales
	 * et change la couleur en fonction de la position dans l'intervalle de stabilité.
	 * Inférieur à palierMin = rouge
	 * Supérieur à palierMax = vert
	 * Neutre = bleu
	 * Pour le poids : vert si neutre, rouge sinon.
	 */
	private void updatePBars() {
		HungerPBar.setProgress(tama.getSat().toPercent());
		HungerPBar.setStyle("-fx-accent: " + colPBar[tama.getSat().posIntervalleStabilite() + 1]);
		SleepPBar.setProgress(tama.getRep().toPercent());
		SleepPBar.setStyle("-fx-accent: " + colPBar[tama.getRep().posIntervalleStabilite() + 1]);
		HygPBar.setProgress(tama.getHyg().toPercent());
		HygPBar.setStyle("-fx-accent: " + colPBar[tama.getHyg().posIntervalleStabilite() + 1]);
		EnergyPBar.setProgress(tama.getNrj().toPercent());
		EnergyPBar.setStyle("-fx-accent: " + colPBar[tama.getNrj().posIntervalleStabilite() + 1]);
		PoiPBar.setProgress(tama.getPoi().toPercent());
		PoiPBar.setStyle("-fx-accent: " + colPBar[(tama.getPoi().posIntervalleStabilite() == 0 ? 2 : 0)]);
	}
	
	/**
	 * Lors de l'appui du bouton de pause.
	 * Si le jeu est en pause : on arrête le chrono, on disable GamePane et on affiche OptionsPane.
	 * Sinon, on cache OptionsPane et on enable GamePane pour continuer à jouer.
	 */
	public void pauseGame(ActionEvent event) {
		gameIsPaused = !gameIsPaused; // on inverse le boolean
		if (gameIsPaused) { // si le jeu s'arrête
			chronometer.stop();
			chronometer.addTimeSkip(chronometer.getEndMinusBegin());
			GamePane.setDisable(true);
			OptionsPane.setDisable(false);
			OptionsPane.setVisible(true);
		} else { // si je le jeu reprend
			chronometer.start();
			GamePane.setDisable(false);
			OptionsPane.setDisable(true);
			OptionsPane.setVisible(false);
		}
	}
	
	/**
	 * Lors de l'appui de sauvegarder et continuer dans les options.
	 */
	public void saveAndPlay(ActionEvent event) throws Exception {
		Saver.save(chronometer, typeTama, tama);
		pauseGame(event);
	}
	
	/**
	 * Lors de l'appui de quitter en sauvegardant dans les options.
	 */
	public void saveAndQuit(ActionEvent event) throws Exception {
		Saver.save(chronometer, typeTama, tama);
		
		Menu.staticClose(event);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		AnchorPane anchorPane = fxmlLoader.load();
		Scene scene = new Scene(anchorPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(getClass().getResource("textures/logo_honk.png"))));
		stageTama.setTitle("H.O.N.K.!");
		stageTama.setScene(scene);
		stageTama.show();
	}
	
	/**
	 * Lors de l'appui de quitter sans sauvegarder dans les options.
	 */
	public void quit(ActionEvent event) throws IOException {
		Menu.staticClose(event);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		AnchorPane anchorPane = fxmlLoader.load();
		Scene scene = new Scene(anchorPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(getClass().getResource("textures/logo_honk.png"))));
		stageTama.setTitle("H.O.N.K.!");
		stageTama.setScene(scene);
		stageTama.show();
	}
	
	/**
	 * Setter pour l'attribut d'activation de la sauvegarde automatique.
	 */
	public void setActivateAutoSave(ActionEvent event) {
		if (activateAutoSave) {
			activateAutoSave = false;
			AutoSaveToggle.setText("Off");
		} else {
			activateAutoSave = true;
			AutoSaveToggle.setText("On");
		}
	}
	
	
	/**
	 * Lors de l'appui de retour, pour retourner au jeu.
	 */
	public void retour(ActionEvent event) {
		pauseGame(event);
	}
	
	/**
	 * Initialize nous sert ici à réactualiser le jeu régulièrement avec Timeline.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Tous les 0.2 seconde, on applique le code qui est dedans.
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			TamaImage.setImage(tama.getSprite()); // Placer l'image
			if (!tama.isDead() && !gameIsPaused) { // Tant que le tamagotchi n'est pas mort et que le n'est pas en pause :
				tama.applyStatsTime(chronometer); // on voit si des statistiques peuvent baisser à cause du temps.
				updateHearts();
				
				textChronometer.setText(chronometer.toString()); // maj des textes (chronomètre et pièce).
				RoomLabel.setText(house.getPiece().getPiece());
				
				updatePBars();
				XPPBar.setProgress(tama.XPToPercent());
				textXP.setText("Niveau : " + tama.getPlayerLevel()); // maj du texte des niveaux.
				
				UpButton.setDisable(house.getPiece().getHaut() == null); // on disable une des directions s'il n'y a pas de pièce à cet endroit.
				LeftButton.setDisable(house.getPiece().getGauche() == null);
				RightButton.setDisable(house.getPiece().getDroite() == null);
				DownButton.setDisable(house.getPiece().getBas() == null);
				
				actionsPossibles = tama.getActionByPiece(house.getPiece().getPiece()); // on récupère les actions possibles dans la pièce.
				if (actionsPossibles != null) {
					EatButton.setDisable(!actionsPossibles.contains("manger")); // si un String est dans la liste, dans ce cas, on renvoie false.
					SleepButton.setDisable(!actionsPossibles.contains("dormir"));
					PlayButton.setDisable(!actionsPossibles.contains("jouer"));
					WalkButton.setDisable(!actionsPossibles.contains("promenade"));
					WashButton.setDisable(!actionsPossibles.contains("toilette"));
				} else {
					EatButton.setDisable(true); // si un String est dans la liste, dans ce cas, on renvoie false.
					SleepButton.setDisable(true);
					PlayButton.setDisable(true);
					WalkButton.setDisable(true);
					WashButton.setDisable(true);
				}
				
				// AUTO SAVE
				if (activateAutoSave && waitTenMinutes == 3000) {
					try {
						Saver.save(chronometer, typeTama, tama);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}
					waitTenMinutes = 0;
				}
				waitTenMinutes++;
				
			} else if (tama.isDead() && !endGameNotLaunched) {
				endGameNotLaunched = true;
				GamePane.setDisable(true);
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
				
				YouDiedPane.setDisable(false);
				YouDiedPane.setVisible(true);
				
				FadeTransition fadeIn = new FadeTransition();
				fadeIn.setDuration(Duration.millis(2500));
				fadeIn.setNode(YouDiedPane);
				fadeIn.setFromValue(0);
				fadeIn.setToValue(1);
				fadeIn.play();
				
				fadeIn.setOnFinished((ActionEvent event) -> {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
					RevenirBouton.setDisable(false);
					RevenirBouton.setVisible(true);
				});
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
