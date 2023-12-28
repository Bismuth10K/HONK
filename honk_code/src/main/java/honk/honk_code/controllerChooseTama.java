package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controllerChooseTama implements Initializable {
	@FXML
	private Button backButton;
	@FXML
	private ToggleGroup animal;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	
	}
	
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
	
	public void createNewGame(ActionEvent event) throws Exception {
		if (animal.getSelectedToggle() != null) {
			final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			
			RadioButton rb = (RadioButton) animal.getSelectedToggle();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
			BorderPane borderPane = fxmlLoader.load();
			Game game = fxmlLoader.getController();
			game.setTama(rb.getText().toLowerCase());
			Scene scene = new Scene(borderPane, 768, 576);
			Stage stageTama = new Stage();
			stageTama.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
			stageTama.setTitle("Let's play!");
			stageTama.setScene(scene);
			stageTama.show();
		}
	}
}
