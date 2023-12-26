package honk.honk_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println(HelloApplication.class.getResource("honk.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("honk.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 768, 576);
		Game game = fxmlLoader.getController();
		game.setTama("robot");
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
	
	public void launcher() {
		launch();
	}
	
	public static void main(String[] args) {
		HelloApplication hp = new HelloApplication();
		hp.launcher();
		
	}
}