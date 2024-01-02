package honk.honk_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

	private FXMLLoader fxmlLoader;

	public void startMainMenu(Stage stage) throws IOException {
		fxmlLoader = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
	}

	public void startChooseMenu(Stage stage){

	}

	public void startGame(Stage stage){

	}

	@Override
	public void start(Stage stage) throws Exception {
		startMainMenu(stage);
	}
	
	public void launcher() {
		launch();
	}
	
	public static void main(String[] args) {
		HelloApplication hp = new HelloApplication();
		hp.launcher();
	}
}