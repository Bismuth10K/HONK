package honk.honk_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
	private static String typeTama;
	
	public static void main(String[] args) {
		HelloApplication hp = new HelloApplication();
		HelloApplication.typeTama = "robot";
		hp.launcher();
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
		BorderPane borderPane = fxmlLoader.load();
		Game game = fxmlLoader.getController();
		game.setTama(HelloApplication.typeTama);
		Scene scene = new Scene(borderPane, 768, 576);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
	
	public void launcher() {
		launch();
	}
}