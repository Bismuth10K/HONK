package honk.honk_code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
	FXMLLoader titleScreen;
	FXMLLoader loadFile;
	FXMLLoader chooseTama;
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.launcher();
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		BorderPane root = new BorderPane();
		titleScreen = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		root.setCenter(titleScreen.load());
		Scene scene = new Scene(root, 768, 576);
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("H.O.N.K.!");
		stage.setScene(scene);
		stage.show();
	}
	
	public void launcher() {
		launch();
	}
	
	public void close(ActionEvent event) {
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
	
	public void createNewGame(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("choose-new-animal.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Choisissez votre Tamagotchi !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	
	public void createLoadGame(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Choisissez votre sauvegarde !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	public void createOptions(ActionEvent event) throws IOException {
		close(event);
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.getIcons().add(new Image(String.valueOf(Textures.class.getResource("textures/logo_honk.png"))));
		stage.setTitle("Les options");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
}