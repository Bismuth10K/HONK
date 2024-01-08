package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChooseTama implements Initializable {
	@FXML
	private ToggleGroup animal;
	
	/**
	 * Doit être là par défaut.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// b'jour
		// scanner tous les json pour voir s'il y a éventuellement une sauvegarde déjà faite pour un animal ?
	}
	
	/**
	 * Action lors de l'appui sur le bouton retour.
	 * Ferme la fenêtre du choix de tamagotchi pour revenir au menu.
	 * @param event ActionEvent : appui du bouton
	 */
	public void goBack(ActionEvent event) throws IOException {
		// Pour fermer la scène actuelle
		Menu.staticClose(event);
		
		// Pour charger la scène title-screen
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
	 * Action lors de l'appui sur le bouton de nouvelle partie.
	 * Ferme la fenêtre du choix de tamagotchi et crée une nouvelle fenêtre pour la nouvelle partie.
	 * @param event ActionEvent : appui du bouton
	 */
	public void createNewGame(ActionEvent event) throws Exception {
		if (animal.getSelectedToggle() != null) {
			// s'il y a bien une sélection, on ferme la scène actuelle,
			Menu.staticClose(event);
			
			// et on crée une partie avec le bon tamagotchi
			RadioButton rb = (RadioButton) animal.getSelectedToggle();
			HBox jigglypuff = (HBox) rb.getParent(); // oui, moi ça me fait rire.
			Label tamaname = (Label) jigglypuff.getChildren().get(2);
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
			StackPane stackPane = fxmlLoader.load();
			Game game = fxmlLoader.getController(); // récupération du controller
			game.setTama(tamaname.getText().toLowerCase());// on set le Tamagotchi
			Scene scene = new Scene(stackPane, 768, 576);
			Stage stageTama = new Stage();
			stageTama.getIcons().add(new Image(String.valueOf(getClass().getResource("textures/logo_honk.png"))));
			stageTama.setTitle("LE " + tamaname.getText().toUpperCase());
			stageTama.setScene(scene);
			stageTama.show();
		}
	}
}
