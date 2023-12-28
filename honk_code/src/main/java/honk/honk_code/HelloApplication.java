package honk.honk_code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
	public static void main(String[] args) {
		HelloApplication hp = new HelloApplication();
		hp.launcher();
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println(HelloApplication.class.getResource("honk.fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("honk.fxml"));
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
}