package honk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HonkApplication extends Application {
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 768, 576);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}