package honk.honk_code;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
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
	final Chronometer chronometer = new Chronometer(100);
	private Tamagotchi tama;
	private String typeTama;
	private ArrayList<String> actionsPossibles;
	private boolean gameIsPaused = false;
	
	@FXML
	private ImageView TamaImage;
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
	private Label RoomLabel;
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
	@FXML
	private ToolBar HappyHeartBar;
	@FXML
	private ToolBar LifeHeartBar;
	@FXML
	private BorderPane GamePane;
	@FXML
	private BorderPane OptionsPane;
	
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
		Saver.parse(toRead, chronometer, typeTama, tama);
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
				LifeIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/LifeFull.png"))));
			else if (nbLife == 0.5)
				LifeIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/LifeHalf.png"))));
			else
				LifeIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/LifeEmpty.png"))));
			nbLife--;
			
			if (nbHappy >= 1)
				HappyIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/HappyFull.png"))));
			else if (nbHappy == 0.5)
				HappyIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/HappyHalf.png"))));
			else
				HappyIV.setImage(new Image(String.valueOf(Textures.class.getResource("textures/gauges-and-buttons/HappyEmpty.png"))));
			nbHappy--;
		}
	}
	
	/**
	 * Lors de l'appui du bouton de pause.
	 * Si le jeu est en pause : on arrête le chrono, on disable GamePane et on affiche OptionsPane.
	 * Sinon, on cache OptionsPane et on enable GamePane pour continuer à jouer.
	 */
	public void pauseGame(ActionEvent event) {
		gameIsPaused = !gameIsPaused; // on inverse le boolean
		tama.setPlayerLevel(11);
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
		
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		AnchorPane anchorPane = fxmlLoader.load();
		Scene scene = new Scene(anchorPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stageTama.setTitle("H.O.N.K.!");
		stageTama.setScene(scene);
		stageTama.show();
	}
	
	/**
	 * Lors de l'appui de quitter sans sauvegarder dans les options.
	 */
	public void quit(ActionEvent event) throws IOException {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		AnchorPane anchorPane = fxmlLoader.load();
		Scene scene = new Scene(anchorPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stageTama.setTitle("H.O.N.K.!");
		stageTama.setScene(scene);
		stageTama.show();
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
		// booleen pour charger les sprites qu'une fois dans le Timeline
		
		// Tous les 0.2 seconde, on applique le code qui est dedans.
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.2), e -> {
			TamaImage.setImage(tama.getSprite()); // Placer l'image
			if (!tama.isDead() && !gameIsPaused) { // Tant que le tamagotchi n'est pas mort et que le n'est pas en pause :
				tama.applyStatsTime(chronometer); // on voit si des statistiques peuvent baisser à cause du temps.
				updateHearts();
				
				textChronometer.setText(chronometer.toString()); // maj des textes (chronomètre et pièce).
				RoomLabel.setText(house.getPiece().getPiece());
				
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
			} else if (tama.isDead()) {
				EatButton.setDisable(true); // Quand le tamagotchi meurt, on disable les actions.
				SleepButton.setDisable(true);
				PlayButton.setDisable(true);
				WalkButton.setDisable(true);
				WashButton.setDisable(true);
				RoomLabel.setText("Lol t mor uwu"); // On met un texte.
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
}
