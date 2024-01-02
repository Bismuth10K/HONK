package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerLoadGame implements Initializable {
		
	/**
	 * Doit être là par défaut.
	 * @param url
	 * @param resourceBundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Comment ça va ?
	}
	
	/**
	 * Action lors de l'appui sur le bouton retour.
	 * Ferme la fenêtre load pour revenir au menu.
	 * @param event ActionEvent : appui du bouton
	 * @throws IOException
	 */
	public void goBack(ActionEvent event) throws IOException {
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
	
	public void loadGame(ActionEvent event) throws Exception {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
		StackPane stackPane = fxmlLoader.load();
		Game game = fxmlLoader.getController(); // récupération du controller
		game.setTama("chien");
		try (FileReader json = new FileReader("savechien.json"))
        {
			game.setJSON(json); // on set le Tamagotchi
		} catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			throw new RuntimeException(e);
		}
		Scene scene = new Scene(stackPane, 768, 576);
		Stage stageTama = new Stage();
		stageTama.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stageTama.setTitle("Let's play!");
		stageTama.setScene(scene);
		stageTama.show();
	}
}
