package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerChooseTama implements Initializable {
	@FXML
	private ToggleGroup animal;
	
	/**
	 * Doit être là par défaut.
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// b'jour
	}
	
	/**
	 * Action lors de l'appui sur le bouton retour.
	 * Ferme la fenêtre du choix de tamagotchi pour revenir au menu.
	 * @param event ActionEvent : appui du bouton
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
		// Pour fermer la scène actuelle
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		// Pour charger la scène title-screen
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
	 * Action lors de l'appui sur le bouton de nouvelle partie.
	 * Ferme la fenêtre du choix de tamagotchi et crée une nouvelle fenêtre pour la nouvelle partie.
	 * @param event ActionEvent : appui du bouton
	 * @throws IOException
	 */
	public void createNewGame(ActionEvent event) throws Exception {
		if (animal.getSelectedToggle() != null) {
			// s'il y a bien une sélection, on ferme la scène actuelle,
			final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			
			// et on crée une partie avec le bon tamagotchi
			RadioButton rb = (RadioButton) animal.getSelectedToggle();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
			StackPane stackPane = fxmlLoader.load();
			Game game = fxmlLoader.getController(); // récupération du controller
			game.setTama(rb.getText().toLowerCase());// on set le Tamagotchi
			System.out.println("in controllerChooseTama : " + rb.getText().toLowerCase());
			Scene scene = new Scene(stackPane, 768, 576);
			Stage stageTama = new Stage();
			stageTama.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
			stageTama.setTitle(rb.getText().toLowerCase());
			stageTama.setScene(scene);
			stageTama.show();
		}
	}
}
