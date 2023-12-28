package honk.honk_code;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Application {
	FXMLLoader titleScreen;
	FXMLLoader loadFile;
	FXMLLoader chooseTama;
	
	@FXML
	private Button newGame;
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.launcher();
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		BorderPane root = new BorderPane();
		System.out.println(getClass().getResource("load-file.fxml"));
		titleScreen = new FXMLLoader(getClass().getResource("title-screen.fxml"));
		loadFile = new FXMLLoader(getClass().getResource("load-file.fxml"));
		chooseTama = new FXMLLoader(getClass().getResource("choose-new-animal.fxml"));
		
		root.setCenter(titleScreen.load());
		
		Scene scene = new Scene(root, 768, 576);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
	
	public void launcher() {
		launch();
	}
	
	public void createNewGame(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("choose-new-animal.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("Choisissez votre Tamagotchi !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	
	public void createLoadGame(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("Choisissez votre sauvegarde !!!");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	public void createOptions(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("load-file.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setTitle("Les options");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
}