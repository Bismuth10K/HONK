package honk.honk_code;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
	
	public void goBack(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void createNewGame(ActionEvent event) {
		animal.getSelectedToggle();
	}
}
